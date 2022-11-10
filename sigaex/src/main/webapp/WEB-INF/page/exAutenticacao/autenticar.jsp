<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<%@ page language="java" contentType="text/html; charset=UTF-8"
	buffer="64kb"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<%@ taglib uri="http://localhost/customtag" prefix="tags"%>
<%@ taglib uri="http://localhost/jeetags" prefix="siga"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>

<c:set var="texto1">
	<fmt:message key="tela.autenticar.texto1" />
</c:set>
<c:set var="texto2">
	<fmt:message key="tela.autenticar.texto2" />
</c:set>
<c:set var="texto3">
	<fmt:message key="tela.autenticar.texto3" />
</c:set>
<c:set var="texto4">
	<fmt:message key="tela.autenticar.texto4" />
</c:set>

<siga:pagina titulo="Autenticidade de Documentos" desabilitarmenu="sim"
	onLoad="try{var num = document.getElementById('id_number');if (num.value == ''){num.focus();num.select();}else{var cap = document.getElementById('id_captcha');cap.focus();cap.select();}}catch(e){};">
	<script src='https://www.google.com/recaptcha/api.js'></script>
	<div class="container-fluid">
		<div class="row">
			<div class="col col-12 col-sm-4">
				<div class="card bg-light mb-3" >
					<div class="card-header">
						<h5>
							Autenticação de Documentos
						</h5>
					</div>
					<div class="card-body">
		
						<form action="${request.contextPath}/public/app/autenticar"	method="get" class="form">
							<div class="row">
								<div class="col">
									<div class="form-group">
										<label>Número de referência</label> 
										<input type="text" id="id_number" name="n" class="form-control" value="${param.n}" />
									</div>
								</div>
							</div>
							<div class="row">
								<div class="col">
									<div class="form-group">
										<label>Verificação</label> 
										<div class="g-recaptcha" data-sitekey="${recaptchaSiteKey}"></div>
									</div>
								</div>
							</div>
							<div class="row">
								<div class="col">
									<button type="submit" class="btn btn-lg btn-primary btn-block"><i class="fas fa-stamp"></i> Autenticar</button>
								</div>
							</div>
							<div class"row">
								<div class="col" style="padding: 0;">
									<a href="http://www.tjpa.jus.br/PortalExterno/imprensa/noticias/Informes/1212160-documentos-oficiais-devem-conter-logomarca-do-selo-prata-de-qualidade.xhtml" target="_blank" title="Selo CNJ de qualidade - Prata - 2021"><img style="margin-top: 0px; margin-bottom: -13px;float:right;"	src="/siga/imagens/selo-prata.png"></a>		
								</div>
							</div>
						</form>
					</div>	
				</div>
			</div>
			<div class="col">
			
				<div class="card mb-6" >
				  <div class="card-header">
				    <h5>Informações Gerais</h5>
				  </div>
				  <div class="card-body">
				    <c:if test="${!fn:startsWith(texto1,'???')}">
							<p style="font-weight: bold;">${texto1}</p>
						</c:if>
						<c:if test="${!fn:startsWith(texto2,'???')}">
							<p>
								<u> ${texto2} </u>
							</p>
						</c:if>

						<c:if test="${!fn:startsWith(texto3,'???')}">
							<ul>
								<c:if test="${!fn:startsWith(texto3,'???')}">
									<li>${texto3}</li>
								</c:if>
								<c:if test="${!fn:startsWith(texto4,'???')}">
									<li>${texto4}</li>
								</c:if>
							</ul>
						</c:if>
				  </div>
				</div>
			</div>
		</div>
		
	</div>
	</div>
	</div>

</siga:pagina>
