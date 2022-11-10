package br.jus.tjpa.siga;

import javax.xml.bind.JAXBElement;
import javax.xml.bind.annotation.XmlElementDecl;
import javax.xml.bind.annotation.XmlRegistry;
import javax.xml.namespace.QName;

/**
 * This object contains factory methods for each Java content interface and Java
 * element interface generated in the tj.pa.gov.br.autenticacao package.
 * <p>
 * An ObjectFactory allows you to programatically construct new instances of the
 * Java representation for XML content. The Java representation of XML content
 * can consist of schema derived interfaces and classes representing the binding
 * of schema type definitions, element declarations and model groups. Factory
 * methods for each of these are provided in this class.
 * 
 */
@XmlRegistry
public class ObjectFactory {

	private final static QName _AutenticarResponse_QNAME = new QName(
			"http://autenticacao.tjpa.jus.br/", "autenticarResponse");
	private final static QName _Usuario_QNAME = new QName(
			"http://autenticacao.tjpa.jus.br/", "usuario");

	/**
	 * Create a new ObjectFactory that can be used to create new instances of
	 * schema derived classes for package: tj.pa.gov.br.autenticacao
	 * 
	 */
	public ObjectFactory() {
	}

	/**
	 * Create an instance of {@link UsuarioWS }
	 * 
	 */
	public UsuarioWS createUsuario() {
		return new UsuarioWS();
	}

	/**
	 * Create an instance of {@link JAXBElement }{@code <}{@link Boolean }{@code >}}
	 * 
	 */
	@XmlElementDecl(namespace = "http://autenticacao.tjpa.jus.br/", name = "autenticarResponse")
	public JAXBElement<Boolean> createAutenticarResponse(Boolean value) {
		return new JAXBElement<Boolean>(_AutenticarResponse_QNAME,
				Boolean.class, null, value);
	}

	/**
	 * Create an instance of {@link JAXBElement }{@code <}{@link UsuarioWS }{@code >}}
	 * 
	 */
	@XmlElementDecl(namespace = "http://autenticacao.tjpa.jus.br/", name = "usuario")
	public JAXBElement<UsuarioWS> createUsuario(UsuarioWS value) {
		return new JAXBElement<UsuarioWS>(_Usuario_QNAME, UsuarioWS.class, null,
				value);
	}

}
