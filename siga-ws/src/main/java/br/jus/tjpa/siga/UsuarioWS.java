package br.jus.tjpa.siga;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlType;

/**
 * <p>
 * Java class for usuario complex type.
 * 
 * <p>
 * The following schema fragment specifies the expected content contained within
 * this class.
 * 
 * <pre>
 * &lt;complexType name=&quot;usuario&quot;&gt;
 *   &lt;complexContent&gt;
 *     &lt;restriction base=&quot;{http://www.w3.org/2001/XMLSchema}anyType&quot;&gt;
 *       &lt;sequence&gt;
 *         &lt;element name=&quot;ipCliente&quot; type=&quot;{http://www.w3.org/2001/XMLSchema}string&quot; minOccurs=&quot;0&quot;/&gt;
 *         &lt;element name=&quot;ipServidor&quot; type=&quot;{http://www.w3.org/2001/XMLSchema}string&quot; minOccurs=&quot;0&quot;/&gt;
 *         &lt;element name=&quot;login&quot; type=&quot;{http://www.w3.org/2001/XMLSchema}string&quot; minOccurs=&quot;0&quot;/&gt;
 *         &lt;element name=&quot;senha&quot; type=&quot;{http://www.w3.org/2001/XMLSchema}string&quot; minOccurs=&quot;0&quot;/&gt;
 *         &lt;element name=&quot;sistema&quot; type=&quot;{http://www.w3.org/2001/XMLSchema}string&quot; minOccurs=&quot;0&quot;/&gt;
 *         &lt;element name=&quot;status&quot; type=&quot;{http://www.w3.org/2001/XMLSchema}string&quot; minOccurs=&quot;0&quot;/&gt;
 *       &lt;/sequence&gt;
 *     &lt;/restriction&gt;
 *   &lt;/complexContent&gt;
 * &lt;/complexType&gt;
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "usuario", propOrder = { "ipCliente", "ipServidor", "login",
		"senha", "sistema", "status" })
public class UsuarioWS {

	protected String ipCliente;
	protected String ipServidor;
	protected String login;
	protected String senha;
	protected String sistema;
	protected String status;

	/**
	 * Gets the value of the ipCliente property.
	 * 
	 * @return possible object is {@link String }
	 * 
	 */
	public String getIpCliente() {
		return ipCliente;
	}

	/**
	 * Sets the value of the ipCliente property.
	 * 
	 * @param value
	 *            allowed object is {@link String }
	 * 
	 */
	public void setIpCliente(String value) {
		this.ipCliente = value;
	}

	/**
	 * Gets the value of the ipServidor property.
	 * 
	 * @return possible object is {@link String }
	 * 
	 */
	public String getIpServidor() {
		return ipServidor;
	}

	/**
	 * Sets the value of the ipServidor property.
	 * 
	 * @param value
	 *            allowed object is {@link String }
	 * 
	 */
	public void setIpServidor(String value) {
		this.ipServidor = value;
	}

	/**
	 * Gets the value of the login property.
	 * 
	 * @return possible object is {@link String }
	 * 
	 */
	public String getLogin() {
		return login;
	}

	/**
	 * Sets the value of the login property.
	 * 
	 * @param value
	 *            allowed object is {@link String }
	 * 
	 */
	public void setLogin(String value) {
		this.login = value;
	}

	/**
	 * Gets the value of the senha property.
	 * 
	 * @return possible object is {@link String }
	 * 
	 */
	public String getSenha() {
		return senha;
	}

	/**
	 * Sets the value of the senha property.
	 * 
	 * @param value
	 *            allowed object is {@link String }
	 * 
	 */
	public void setSenha(String value) {
		this.senha = value;
	}

	/**
	 * Gets the value of the sistema property.
	 * 
	 * @return possible object is {@link String }
	 * 
	 */
	public String getSistema() {
		return sistema;
	}

	/**
	 * Sets the value of the sistema property.
	 * 
	 * @param value
	 *            allowed object is {@link String }
	 * 
	 */
	public void setSistema(String value) {
		this.sistema = value;
	}

	/**
	 * Gets the value of the status property.
	 * 
	 * @return possible object is {@link String }
	 * 
	 */
	public String getStatus() {
		return status;
	}

	/**
	 * Sets the value of the status property.
	 * 
	 * @param value
	 *            allowed object is {@link String }
	 * 
	 */
	public void setStatus(String value) {
		this.status = value;
	}

}
