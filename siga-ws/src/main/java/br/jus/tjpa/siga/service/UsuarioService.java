package br.jus.tjpa.siga.service;

import java.awt.HeadlessException;
import java.io.Serializable;

import br.jus.tjpa.siga.AutenticacaoWS;
import br.jus.tjpa.siga.AutenticacaoWSService;
import br.jus.tjpa.siga.UsuarioWS;

public class UsuarioService implements Serializable {

	private static final long serialVersionUID = 1L;

	public String validarUsuarioRede(String userName, String senha, String hostCliente, String hostServidor) {
		try {
			UsuarioWS usuarioWS = new UsuarioWS(); 
			AutenticacaoWSService servico = new AutenticacaoWSService();
			AutenticacaoWS autenticacao = servico.getAutenticacaoWSPort();
			usuarioWS.setLogin(userName);
			usuarioWS.setSenha(senha);
			
			usuarioWS.setIpCliente(hostCliente);// Pegar o IP do Usu�rio
			usuarioWS.setIpServidor(hostServidor);// Pegar o IP do Servidor da Aplica��o
			usuarioWS.setSistema("SIGADOC");
			
			return (String) autenticacao.autenticar(usuarioWS);
			
		} catch (HeadlessException e) {
			return "Login de rede ou senha inválida."; 
		}
	}

}
