/*******************************************************************************
 * Copyright (c) 2006 - 2011 SJRJ.
 * 
 *     This file is part of SIGA.
 * 
 *     SIGA is free software: you can redistribute it and/or modify
 *     it under the terms of the GNU General Public License as published by
 *     the Free Software Foundation, either version 3 of the License, or
 *     (at your option) any later version.
 * 
 *     SIGA is distributed in the hope that it will be useful,
 *     but WITHOUT ANY WARRANTY; without even the implied warranty of
 *     MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 *     GNU General Public License for more details.
 * 
 *     You should have received a copy of the GNU General Public License
 *     along with SIGA.  If not, see <http://www.gnu.org/licenses/>.
 ******************************************************************************/
package br.gov.jfrj.siga.ex;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.MappedSuperclass;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.SequenceGenerator;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import br.gov.jfrj.siga.cp.CpIdentidade;
import br.gov.jfrj.siga.sinc.lib.Desconsiderar;

@SuppressWarnings("serial")
@MappedSuperclass
@NamedQueries({
		@NamedQuery(name = "consultarAcessoDocumento", query = "from ExAcessoDocumento a " 
				+ "where a.cpIdentidade = :identidade "
				+ "and to_char(a.dtAcesso, 'dd/MM/yyyy hh24:mi') = :dtAcesso "
				+ "order by a.dtAcesso desc")
})
public abstract class AbstractExAcessoDocumento implements Serializable {

	@SequenceGenerator(name = "generator", sequenceName = "SIGA.EX_ACESSO_DOCUMENTO_SEQ")
	@Id
	@GeneratedValue(generator = "generator")
	@Column(name = "ID_ACESSO", unique = true, nullable = false)
	@Desconsiderar
	private Long idCpAcesso;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "ID_IDENTIDADE", nullable = false)
	private CpIdentidade cpIdentidade;

	@Column(name = "CD_SIGLA", length = 50, nullable = false)
	private String sigla;
	
	@Column(name = "CD_TIPO", length = 20, nullable = false)
	private String tipoDocumento;

	@Column(name = "NM_MENSAGEM", length = 256, nullable = false)
	private String mensagem;

	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "DT_ACESSO", length = 19)
	private Date dtAcesso;

	@Column(name = "IP_AUDIT", length = 256)
	private String auditIP;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "ID_DOC", nullable = false)
	private ExDocumento exDocumento;

	public Long getIdCpAcesso() {
		return idCpAcesso;
	}

	public void setIdCpAcesso(Long idCpAcesso) {
		this.idCpAcesso = idCpAcesso;
	}

	public CpIdentidade getCpIdentidade() {
		return cpIdentidade;
	}

	public void setCpIdentidade(CpIdentidade cpIdentidade) {
		this.cpIdentidade = cpIdentidade;
	}

	public String getSigla() {
		return sigla;
	}

	public void setSigla(String sigla) {
		this.sigla = sigla;
	}

	public String getTipoDocumento() {
		return tipoDocumento;
	}

	public void setTipoDocumento(String tipoDocumento) {
		this.tipoDocumento = tipoDocumento;
	}

	public String getMensagem() {
		return mensagem;
	}

	public void setMensagem(String mensagem) {
		this.mensagem = mensagem;
	}

	public Date getDtAcesso() {
		return dtAcesso;
	}

	public void setDtAcesso(Date dtAcesso) {
		this.dtAcesso = dtAcesso;
	}

	public String getAuditIP() {
		return auditIP;
	}

	public void setAuditIP(String auditIP) {
		this.auditIP = auditIP;
	}

	public ExDocumento getExDocumento() {
		return exDocumento;
	}

	public void setExDocumento(ExDocumento exDocumento) {
		this.exDocumento = exDocumento;
	}
}
