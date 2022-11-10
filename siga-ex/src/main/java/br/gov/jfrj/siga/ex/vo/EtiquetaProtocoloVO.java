package br.gov.jfrj.siga.ex.vo;

import java.io.Serializable;

public class EtiquetaProtocoloVO implements Serializable{

	private static final long serialVersionUID = 1L;

	private String origem;
	private String numeroOriginal;
	private String dataOriginal;
	private String subscritorOriginal;
	private String numeroProtocolo;
	private String dataProtocolo;
	
	public EtiquetaProtocoloVO(){
		
	}

	public String getOrigem() {
		return origem;
	}

	public void setOrigem(String origem) {
		this.origem = origem;
	}
	
	public String getNumeroOriginal() {
		return numeroOriginal;
	}

	public void setNumeroOriginal(String numeroOriginal) {
		this.numeroOriginal = numeroOriginal;
	}	

	public String getDataOriginal() {
		return dataOriginal;
	}

	public void setDataOriginal(String dataOriginal) {
		this.dataOriginal = dataOriginal;
	}

	public String getSubscritorOriginal() {
		return subscritorOriginal;
	}

	public void setSubscritorOriginal(String subscritorOriginal) {
		this.subscritorOriginal = subscritorOriginal;
	}

	public String getNumeroProtocolo() {
		return numeroProtocolo;
	}

	public void setNumeroProtocolo(String numeroProtocolo) {
		this.numeroProtocolo = numeroProtocolo;
	}

	public String getDataProtocolo() {
		return dataProtocolo;
	}

	public void setDataProtocolo(String dataProtocolo) {
		this.dataProtocolo = dataProtocolo;
	}


	
}
