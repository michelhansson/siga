<%@ tag body-content="empty" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://localhost/libstag" prefix="f"%>
<%@ attribute name="podeAssinarPor" required="false"%>
<script>
	var uriLogoSiga = '${uri_logo_siga_pequeno}';
</script>
<script src="/sigaex/public/javascript/assinatura-digital.js?v=1626317334"></script>
<c:choose>
	<c:when test="${autenticacaoSenha == false}">
		<input type="hidden" id="siglaUsuarioCadastrante" value="${cadastrante.sigla}"/>
	    <input type="hidden" id="siglaUsuSubscritor" value="${doc.subscritor.sigla}"/>
		<input type="hidden" id="siglaUsuTitular" value="${titular.sigla}"/>
	</c:when>
	<c:otherwise>
		<input type="hidden" id="siglaUsuarioCadastrante" value="${cadastrante.loginRede}"/>
		<input type="hidden" id="siglaUsuSubscritor" value="${doc.subscritor.loginRede}"/>
		<input type="hidden" id="siglaUsuTitular" value="${titular.loginRede}"/>
	</c:otherwise>
</c:choose>												

<input type="hidden" id="nomeUsuSubscritor" value="${doc.subscritor.nomePessoa}"/>
<input type="hidden" id="podeAssinarPor" value="${podeAssinarPor}"/>
<input type="hidden" id="cpfUsuarioCadastrante" value="${cadastrante.cpfFormatado}"/>

<c:forEach items="${doc.getMobilGeral().getMovimentacoesPorTipo(ExTipoDeMovimentacao.INCLUSAO_DE_COSIGNATARIO, true)}" var="currentItem" varStatus="stat">
  <c:set var="cossignatarios" value="${stat.first ? '' : cossignatarios} ${currentItem.subscritor.sigla}" />
</c:forEach>

<input type="hidden" id="siglaUsuCossignatarios" value="${cossignatarios}"/>

<c:if test="${not empty f:resource('assinador.externo.popup.url')}">
	<input type="hidden" id="iframePopupUrl" value="${f:resource('assinador.externo.popup.url')}/popup.html"/>
	<script src="${f:resource('assinador.externo.popup.url')}/popup-api.js"></script>
</c:if>
