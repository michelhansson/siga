<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	buffer="64kb"%>
<%@ taglib tagdir="/WEB-INF/tags/mod" prefix="mod"%>
<%@ taglib uri="http://localhost/functiontag" prefix="f"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<mod:modelo>
	<mod:entrevista>
		
		<mod:grupo titulo="Texto a ser inserido no corpo do despacho">
			<mod:grupo>
				<mod:editor titulo="" var="texto" />
			</mod:grupo>
		</mod:grupo>
		<mod:grupo>
			<mod:selecao titulo="Fecho" var="fecho"	opcoes="[Nenhum];Atenciosamente;Cordialmente;Respeitosamente;" />
		</mod:grupo>
		<mod:selecao titulo="Tamanho da letra" var="tamanhoLetra"
			opcoes="Normal;Pequeno;Grande" />
	</mod:entrevista>
	<mod:documento>
		<c:if test="${tamanhoLetra=='Normal'}">
			<c:set var="tl" value="11pt" />
		</c:if>
		<c:if test="${tamanhoLetra=='Pequeno'}">
			<c:set var="tl" value="9pt" />
		</c:if>
		<c:if test="${tamanhoLetra=='Grande'}">
			<c:set var="tl" value="13pt" />
		</c:if>
		<html xmlns="http://www.w3.org/1999/xhtml" xml:lang="en" lang="en">
		<head>
		<style type="text/css">
@page {
	margin-left: 3cm;
	margin-right: 2cm;
	margin-top: 1cm;
	margin-bottom: 2cm;
}
</style>
		</head>
		<body>
		<!-- INICIO PRIMEIRO CABECALHO
		<table width="100%" border="0" bgcolor="#FFFFFF"><tr><td>
		<c:import url="/paginas/expediente/modelos/inc_cabecalhoCentralizadoPrimeiraPagina.jsp" />
		</td></tr>
			<tr bgcolor="#FFFFFF">
				<td width="100%">
				</td>
			</tr>
		</table>
		FIM PRIMEIRO CABECALHO -->

		<!-- INICIO CABECALHO
		<c:import url="/paginas/expediente/modelos/inc_cabecalhoCentralizado.jsp" />
		FIM CABECALHO -->
		<mod:letra tamanho="${tl}">
			<p><br>
			<br>
			<br>
			</p>
			<c:if test="${not empty orgao_dest}">
				<c:if test="${genero=='Feminino'}">
					<p>À ${orgao_dest},</p>
				</c:if>
				<c:if test="${genero=='Masculino'}">
					<p>Ao ${orgao_dest},</p>
				</c:if>
			</c:if>

			<c:if test="${ empty texto }">
				<p style="text-align: justify;">
					<span style="font-size:${tl};"> ${doc.tipoDespachoDescricao} </span>
				</p>
			</c:if>

			<span style="font-size:${tl};"> ${texto} </span>
			
			<c:if test="${fecho == '[Nenhum]'}">
				 <c:set var="fecho" value="" />
			</c:if>
			
			<p>
				<span style="font-size:${tl};"> ${fecho} </span>
			</p>
			
			<center>${doc.dtExtenso}</center>
			<p>&nbsp;</p>
			<c:import
				url="/paginas/expediente/modelos/inc_assinatura.jsp?textoFinal=${portDelegacao}" />

			<!-- INICIO PRIMEIRO RODAPE
		<c:import url="/paginas/expediente/modelos/inc_rodapeClassificacaoDocumental.jsp" />
		FIM PRIMEIRO RODAPE -->

			<!-- INICIO RODAPE
		<c:import url="/paginas/expediente/modelos/inc_rodapeNumeracaoADireita.jsp" />
		FIM RODAPE -->
		</mod:letra>
		</body>
		</html>
	</mod:documento>
</mod:modelo>