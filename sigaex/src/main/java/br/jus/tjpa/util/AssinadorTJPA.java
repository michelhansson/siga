package br.jus.tjpa.util;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.math.BigInteger;
import java.net.URL;
import java.security.InvalidKeyException;
import java.security.KeyStore;
import java.security.NoSuchAlgorithmException;
import java.security.PrivateKey;
import java.security.Security;
import java.security.Signature;
import java.security.SignatureException;
import java.security.cert.Certificate;
import java.security.cert.X509Certificate;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.bouncycastle.jce.provider.BouncyCastleProvider;
import org.jboss.logging.Logger;

import sun.misc.BASE64Decoder;
import sun.misc.BASE64Encoder;
import sun.security.pkcs.ContentInfo;
import sun.security.pkcs.PKCS7;
import sun.security.pkcs.SignerInfo;
import sun.security.util.DerOutputStream;
import sun.security.x509.AlgorithmId;
import sun.security.x509.X500Name;

@SuppressWarnings("restriction")
public class AssinadorTJPA {
	
	private PrivateKey chavePrivada;
	private X509Certificate certificado;
	
	private String urlCertificado;
	
	private static AssinadorTJPA instancia;
	
	private static final String NOME_ALGORITMO_SHA1_WITH_RSA = "SHA1withRSA";
	private static final String NOME_ALGORITMO_SHA256_WITH_RSA = "SHA256withRSA";
	private static final String NOME_KEYSTORE_PKCS12 = "PKCS12";
	
	private static final Logger LOGGER = Logger.getLogger(AssinadorTJPA.class);
	
	static {
		try {
			instancia = new AssinadorTJPA();
			instancia.urlCertificado = System.getProperty("tjpa.tkn.url");
			String pin = System.getProperty("tjpa.tkn.pin");
			Security.addProvider(new BouncyCastleProvider());
			
			KeyStore ks = KeyStore.getInstance(NOME_KEYSTORE_PKCS12);
			LOGGER.info("Carregando o certificado TJPA..");
			InputStream is = instancia.getCertificadoA1();
			ks.load(is, pin.toCharArray());
			String alias = ks.aliases().nextElement();
			LOGGER.info("Lendo chave privada...");
			instancia.chavePrivada = (PrivateKey) ks.getKey(alias, pin.toCharArray());
			Certificate[] certsChain = ks.getCertificateChain(alias);
			instancia.certificado = (X509Certificate) ks.getCertificate(alias);
			
			LOGGER.info("Lendo cadeia de certificados...");
			List<Certificate> certList = new ArrayList<Certificate>();
			if (certsChain != null && certsChain.length != 0) {
	            certList.addAll(Arrays.asList(certsChain));
			} else {
				certList.add(instancia.certificado);
			}
			LOGGER.info(" ASSINADOR TJPA - OK!");
		} catch (Exception e) {
			LOGGER.info(" ASSINADOR TJPA - ERRO!");
			e.printStackTrace();
		}
	}
	
	public static AssinadorTJPA getInstancia() {
		return instancia;
	}
	
	public X509Certificate getCertificado() {
		return certificado;
	}
	
	private InputStream getCertificadoA1() throws IOException {
		return new URL(urlCertificado).openStream();
	}
	
	/*
	 * Executa a assinatura e retorna o array de bytes do objeto Signature
	 */
	public byte[] assinarComCertificadoTJPA(String conteudoB64)
			throws InvalidKeyException, NoSuchAlgorithmException, IOException, SignatureException {
		Signature sig = Signature.getInstance(NOME_ALGORITMO_SHA256_WITH_RSA);
		sig.initSign(chavePrivada);
	
		BASE64Decoder b64dec = new BASE64Decoder();
		BASE64Encoder b64enc = new BASE64Encoder();
		byte[] decodeOrig = b64dec.decodeBuffer(conteudoB64);
		sig.update(decodeOrig);
		byte[] dataSignature = sig.sign();
	
		String result = b64enc.encode(dataSignature);
		LOGGER.info("Assinatura: " + result);
	
		// Verify signature
		Signature verificacion = Signature.getInstance(NOME_ALGORITMO_SHA256_WITH_RSA);
		verificacion.initVerify(certificado);
		verificacion.update(decodeOrig);
		if (verificacion.verify(dataSignature)) {
			LOGGER.info("Signature verification Succeeded!");
		} else {
			LOGGER.info("Signature verification FAILED!");
		}
		return dataSignature;
	}

	/*
	 * Executa a assinatura e retorna o array de bytes do pacote p7s.
	 */
	public byte[] assinarParaPKCS7(String conteudoB64) throws Exception {
		
		// Sign data
		Signature sig = Signature.getInstance(NOME_ALGORITMO_SHA1_WITH_RSA);
		// sha1 only
		sig.initSign(chavePrivada);
		BASE64Decoder b64dec = new BASE64Decoder();
		//BASE64Encoder b64enc = new BASE64Encoder();
		sig.update(b64dec.decodeBuffer(conteudoB64));
		byte[] signedData = sig.sign();

		// load X500Name
		X500Name xName = X500Name.asX500Name(certificado.getSubjectX500Principal());
		// load serial number
		BigInteger serial = certificado.getSerialNumber();
		// laod digest algorithm
		AlgorithmId digestAlgorithmId = new AlgorithmId(AlgorithmId.SHA_oid);
		// load signing algorithm
		AlgorithmId signAlgorithmId = new AlgorithmId(AlgorithmId.RSAEncryption_oid);

		// Create SignerInfo:
		SignerInfo sInfo = new SignerInfo(xName, serial, digestAlgorithmId,
				signAlgorithmId, signedData);
		// Create ContentInfo:
		//ContentInfo cInfo = new ContentInfo(ContentInfo.DATA_OID, new DerValue(DerValue.tag_OctetString, conteudoB64));
		ContentInfo cInfo = new ContentInfo(ContentInfo.DIGESTED_DATA_OID, null);
		// Create PKCS7 Signed data
		PKCS7 p7 = new PKCS7(new AlgorithmId[] { digestAlgorithmId }, cInfo,
				new X509Certificate[] { certificado },
				new SignerInfo[] { sInfo });
		// Write PKCS7 to bYteArray
		ByteArrayOutputStream bOut = new DerOutputStream();
		p7.encodeSignedData(bOut);
		
		return bOut.toByteArray();
		//byte[] encodedPKCS7 = bOut.toByteArray();
		//return b64enc.encode(encodedPKCS7);
	}
	
}
