package br.gov.jfrj.siga.vraptor;

import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.persistence.Query;
import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.json.JSONException;
import org.json.JSONObject;

import br.com.caelum.vraptor.Controller;
import br.com.caelum.vraptor.Get;
import br.com.caelum.vraptor.Result;
import br.com.caelum.vraptor.view.Results;
import br.gov.jfrj.siga.base.AplicacaoException;
import br.gov.jfrj.siga.dp.DpPessoa;
import br.gov.jfrj.siga.dp.dao.CpDao;
import br.gov.jfrj.siga.ex.ExDocumento;
import br.gov.jfrj.siga.ex.ExFormaDocumento;

@Controller
public class ExTjpaController extends ExController {
	private JSONObject jsonObject = new JSONObject();
	public static final String CACHE_QUERY_CONFIGURACAO = "queryConfiguracao";

	
	/**
	 * @deprecated CDI eyes only
	 */
	public ExTjpaController() {
		super();
	}

	@Inject
	public ExTjpaController(HttpServletRequest request, HttpServletResponse response, ServletContext context,
			Result result, CpDao dao, SigaObjects so, EntityManager em) {
		super(request, response, context, result, dao, so, em);
	}
	
	@Get("/public/app/pessoa/complemento")
	public void listaPessoa(final String matricula) throws JSONException {

		List<DpPessoa> listaPessoa = em()
				.createQuery("From DpPessoa Where matricula = :matricula and data_fim_pessoa is null", DpPessoa.class)
				.setParameter("matricula", Long.parseLong(matricula)).getResultList();

		jsonObject = new JSONObject();
		
		for (DpPessoa dpPessoa : listaPessoa) {
			jsonObject.put("matricula", dpPessoa.getMatricula().toString());
			jsonObject.put("nomeMae", dpPessoa.getNomeMae());
			jsonObject.put("nomePai", dpPessoa.getNomePai());
			jsonObject.put("naturalidade", dpPessoa.getNaturalidade());
			jsonObject.put("comarca", dpPessoa.getComarca());
			jsonObject.put("titulo", dpPessoa.getTituloEleitoral());
			jsonObject.put("cpf", dpPessoa.getCpfPessoa().toString());
			jsonObject.put("nomePessoa", dpPessoa.getNomePessoa());
			jsonObject.put("dataNascimento", dpPessoa.getDtNascimentoDDMMYYYY());
			jsonObject.put("email", dpPessoa.getEmailPessoa());
			jsonObject.put("identidade", dpPessoa.getIdentidade());
			jsonObject.put("dataInicio", dpPessoa.getDataInicioPessoaDDMMYYYY());
			jsonObject.put("data_posse", dpPessoa.getDataPosseDDMMYYYY());
			jsonObject.put("logradouro", dpPessoa.getEndereco());
			jsonObject.put("bairro", dpPessoa.getBairro());
			jsonObject.put("cep", dpPessoa.getCep());
			jsonObject.put("cidade", dpPessoa.getCidade());
			jsonObject.put("telefones", dpPessoa.getTelefone());
			jsonObject.put("lotacao", dpPessoa.getLotacao().getNomeLotacao());
			jsonObject.put("cargo", dpPessoa.getCargo() != null && dpPessoa.getCargo().getIdCargo() > 0 ? dpPessoa.getCargo().getNomeCargo() : "");
			jsonObject.put("tipoSanguineo", dpPessoa.getTipoSanguineo());
			jsonObject.put("funcao", dpPessoa.getFuncaoConfianca() != null && dpPessoa.getFuncaoConfianca().getIdFuncao() > 0 ? dpPessoa.getFuncaoConfianca().getNomeFuncao() : ""); 
				 
			result.use(Results.http()).body(jsonObject.toString());

		}
		
	}

	@Get("/public/app/themaGRP/validaNumeroSIGADOC")
	public void validaNumeroSigaDoc(final String sigla) throws JSONException  {
		ExDocumento documento = null;
		
		String msg = null;
		
		if ("".equals(sigla) || sigla == null) {
			msg = "Informe o documento(SigaDoc) para pesquisar.";
		}

		if (msg == null) {
			final Pattern p1 = Pattern.compile("^(?<orgao>TJPA|PA)-(?<forma>[A-Z]{3})-(?<ano>[0-9]{4})/(?<numero>[0-9]{1,6})$");
			Matcher m1 = null;

			m1 = p1.matcher(sigla.trim().toUpperCase());
		    if (!m1.find()) {
		    	msg = "O formato do documento está inválido: " + sigla;
			}

		    if (msg == null) {
			    String orgao  = m1.group("orgao");
				String forma  = m1.group("forma");
				String ano    = m1.group("ano");
				String numero = m1.group("numero");

				ExFormaDocumento exFormaDocumento = dao().consultarPorSiglaForma(forma);
				if (exFormaDocumento == null) {
					msg = "O documento: " + sigla + ", está com o tipo de documento inválido (" + forma + ").";
				}
				
				if (msg == null) {
					Query query = dao().em().createQuery("select d from ExDocumento as d join d.exFormaDocumento as fd "
							+ "where fd.siglaFormaDoc = :forma "
							+ "  and d.anoEmissao = :ano "
							+ "  and d.numExpediente = :numero");

					query.setHint("org.hibernate.cacheable", true);
					query.setHint("org.hibernate.cacheRegion", CACHE_QUERY_CONFIGURACAO);

					query.setParameter("forma", forma);
					query.setParameter("numero", Long.parseLong(numero));
					query.setParameter("ano", Long.parseLong(ano));
					
					try {
						documento = (ExDocumento) query.getSingleResult();
					} catch (Exception e) {
						documento = null;
						msg = "O documento: " + sigla + ", não encontrado.";
					}
				}
		    }
		}

		jsonObject = new JSONObject();

		if (documento != null) {
			jsonObject.put("existeDocumento", true);
			jsonObject.put("sigiloso", documento.getExNivelAcesso().getIdNivelAcesso() != 1 && documento.getExNivelAcesso().getIdNivelAcesso() != 6 ? true : false);
			jsonObject.put("descricaoDocumento", documento.getDescrDocumento().equals(null) ? "" : (documento.getExNivelAcesso().getIdNivelAcesso() != 1 && documento.getExNivelAcesso().getIdNivelAcesso() != 6 ? "Descrição Inacessível" : documento.getDescrDocumento()));	
		}else{
			jsonObject.put("existeDocumento", false);
			jsonObject.put("sigiloso", false);
			jsonObject.put("descricaoDocumento", msg != null ? msg : "");	
		}
		
		result.use(Results.http()).body(jsonObject.toString());
	}

	public JSONObject getJsonObject() {
		return jsonObject;
	}

	public void setJsonObject(JSONObject jsonObject) {
		this.jsonObject = jsonObject;
	}


}
