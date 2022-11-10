package br.gov.jfrj.siga.vraptor;

import java.io.ByteArrayInputStream;
import java.util.List;

import javax.persistence.EntityManager;
import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.jboss.logging.Logger;

import com.lowagie.text.pdf.codec.Base64;

import br.com.caelum.vraptor.Controller;
import br.com.caelum.vraptor.Get;
import br.com.caelum.vraptor.Result;
import br.com.caelum.vraptor.observer.download.Download;
import br.com.caelum.vraptor.observer.download.InputStreamDownload;
import br.gov.jfrj.itextpdf.Documento;
import br.gov.jfrj.siga.base.AplicacaoException;
import br.gov.jfrj.siga.dp.dao.CpDao;
import br.gov.jfrj.siga.ex.ExDocumento;
import br.gov.jfrj.siga.ex.ExMobil;
import br.gov.jfrj.siga.ex.ExMovimentacao;
import nl.captcha.Captcha;

@Controller
public class ExConsultaPublicaController extends ExController {

	public ExConsultaPublicaController(HttpServletRequest request, HttpServletResponse response, ServletContext context,
			Result result, CpDao dao, SigaObjects so, EntityManager em) {
		super(request, response, context, result, dao, so, em);
	}

	
	private static final String TEXT_HTML = "text/html";
	private static final String APPLICATION_PDF = "application/pdf";
	private static final String TEXT_PLAIN = "text/plain";
	private static final String APPLICATION_OCTET_STREAM = "application/octet-stream";

	private static final Long CINCO_MINUTOS_EM_MS = 5*60*1000l;
	private static final String VERIFICADOR_ACESSO = "CSP:Consulta pública";
	private static final String UTF8 = "utf-8";
	private static final Logger LOGGER = Logger
			.getLogger(ExConsultaPublicaController.class);

	String uf;
	String siglaFormaDocumento;
	String anoEmissao;
	String numeroExpediente;

	ExDocumento doc = new ExDocumento();
	
	@Get("consultaPublica")
	public void lista(final String sigla, final String resposta) throws Exception {
		String uf = "";
		String mensagemDocumento = null;
		String mensagemCaptcha = null;
		String siglaFormaDocumento = "";
		String anoEmissao = "";
		String numeroExpediente = "";

		doc = null;
		List<ExMovimentacao> movimentacoes = null;
		if (resposta != null && !"".equals(resposta)) {
			Long timeCaptcha = (Long) request.getSession().getAttribute("timeCaptcha");
			Captcha captcha = (Captcha) request.getSession().getAttribute(Captcha.NAME);	
			request.setCharacterEncoding(UTF8); 
			try {
				if ((timeCaptcha == null && captcha.isCorrect(resposta)) || timeCaptcha != null && System.currentTimeMillis()-timeCaptcha < CINCO_MINUTOS_EM_MS) {
					request.getSession().setAttribute("timeCaptcha", System.currentTimeMillis());
					if (sigla != null && !"".equals(sigla)) {
						try {
							uf = sigla.substring(0,2);  
							siglaFormaDocumento = sigla.substring(3, 6);
							anoEmissao = sigla.substring(7, 11);
							numeroExpediente = sigla.substring(12, 17);
							if(!"PA".equals(uf)) {
								mensagemDocumento = "Documento não é do estado do Pará";
							}
							if(!"EXT".equals(siglaFormaDocumento)) {
								if ("".equals(mensagemDocumento)) {
									mensagemDocumento = "Tipo de documento não é externo(EX)";
								} else {
									mensagemDocumento = mensagemDocumento + "\n Tipo de documento não é externo(EX)";
								}
							}
							//assertAcesso(VERIFICADOR_ACESSO);
							if (!"".equals(mensagemDocumento) && mensagemDocumento != null) {
								result.include("mensagemDocumento", mensagemDocumento);
							} else {
								doc = dao().recuperarDocumentoConsultaPublica(siglaFormaDocumento, anoEmissao, numeroExpediente);
								if (doc != null) {
									movimentacoes = dao().recuperarMovimentacaoConsultaPublica(doc.getIdDoc());
								} else {
									mensagemDocumento = "Documento não encontrado.";
								}
							}
						} catch (AplicacaoException e) {
							throw new AplicacaoException(e.getMessage(), 0, e);
						} catch (Exception ex) {
							LOGGER.error(ex.getMessage(), ex);
							throw new AplicacaoException(ex.getMessage(), 0, ex);
						}
					}
				}
			} catch (NullPointerException e) {
				mensagemDocumento = null;
				mensagemCaptcha = null;
				doc = null;
				movimentacoes = null;
			}
			if (captcha != null && !captcha.isCorrect(resposta)) {
				mensagemCaptcha =  "Sequência de codigo invalida";
			}
		}
		result.include("sigla", sigla);
		result.include("mensagemDocumento", mensagemDocumento);
		result.include("mensagemCaptcha", mensagemCaptcha);
		result.include("doc", doc);
		result.include("mov", movimentacoes);
	}

	@Get("consultaPublica/exibir")
	public Download exibir(final Long idMov, final String sigla) {
		try {
			ExMovimentacao mov = dao().recuperarMovimentacaoPorId(idMov);

			if (mov != null) {
				final Documento documento = new Documento();
			    byte[] pdf = documento.getDocumento(mov.getExMobil(), mov);	
			    String arquivo = sigla+":"+idMov+".pdf";
			    if (mov.getNmArqMov() != null && !"".equals(mov.getNmArqMov())) {
			    	arquivo = mov.getNmArqMov();
			    }

				return new InputStreamDownload(makeByteArrayInputStream(pdf, false), APPLICATION_PDF, arquivo);
			}
		} catch (Exception e) {
			if (e.getClass().getSimpleName().equals("ClientAbortException")) {
				return new InputStreamDownload(makeByteArrayInputStream((new byte[0]), false), TEXT_PLAIN, "arquivo inválido");
			}
			throw new RuntimeException("erro na geração do documento.", e);
		}	
		return null;
	}

	@Get("consultaPublica/exibirTodos")
	public Download exibirTodos(final String sigla) {
		try {
		    String arquivoPesquisa = sigla.replace("-", "").replace("/", "");
		    arquivoPesquisa = arquivoPesquisa + "A.pdf";
			final Documento documento = new Documento();
			final ExMobil mob = Documento.getMobil(arquivoPesquisa);
			if (mob != null) {
			    byte[] pdf = documento.getDocumentoCompletoComEstampa(mob);	
			    String arquivo = sigla+"-A.pdf";

				return new InputStreamDownload(makeByteArrayInputStream(pdf, false), APPLICATION_PDF, arquivo);
			}
		} catch (Exception e) {
			if (e.getClass().getSimpleName().equals("ClientAbortException")) {
				return new InputStreamDownload(makeByteArrayInputStream((new byte[0]), false), TEXT_PLAIN, "arquivo inválido");
			}
			throw new RuntimeException("erro na geração do documento.", e);
		}	
		return null;
	}

	private ByteArrayInputStream makeByteArrayInputStream(final byte[] content, final boolean fB64) {
		final byte[] conteudo = (fB64 ? Base64.encodeBytes(content).getBytes() : content);
		return (new ByteArrayInputStream(conteudo));
	}
	
	public String getUf() {
		return uf;
	}

	public void setUf(String uf) {
		this.uf = uf;
	}

	public String getSiglaFormaDocumento() {
		return siglaFormaDocumento;
	}

	public void setSiglaFormaDocumento(String siglaFormaDocumento) {
		this.siglaFormaDocumento = siglaFormaDocumento;
	}

	public String getAnoEmissao() {
		return anoEmissao;
	}

	public void setAnoEmissao(String anoEmissao) {
		this.anoEmissao = anoEmissao;
	}

	public String getNumeroExpediente() {
		return numeroExpediente;
	}

	public void setNumeroExpediente(String numeroExpediente) {
		this.numeroExpediente = numeroExpediente;
	}

	public ExDocumento getDoc() {
		return doc;
	}

	public void setDoc(ExDocumento doc) {
		this.doc = doc;
	}

	
	
}
