<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<%@ page language="java" contentType="text/html; charset=UTF-8"
	buffer="64kb"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%@ taglib uri="http://localhost/customtag" prefix="tags"%>
<%@ taglib uri="http://localhost/jeetags" prefix="siga"%>
<%@ taglib uri="http://jsptags.com/tags/navigation/pager" prefix="pg"%>
<%@ taglib uri="http://localhost/functiontag" prefix="f"%>

<siga:pagina titulo="Atualizar Marcas em Lote">

	<script type="text/javascript" language="Javascript1.1">	
	function sbmt(offset) {
		frm.action = '${pageContext.request.contextPath}/app/expediente/mov/atualizar_marcas_lote';
		frm.submit();
	}
	
	
</script>

	<!-- main content bootstrap -->
	<div class="container-fluid">
		<div class="card bg-light mb-3">
			<div class="card-header">
				<h5>Atualizar Marcas em Lote</h5>
			</div>
			<div class="card-body">
				<form name="frm" action="atualizar_marcas_lote_gravar" method="post">
					<input type="hidden" name="postback" value="1" />
					<div class="row">
						<div class="col-sm-3">
							<div class="form-group">
								<label for="descrMov">Informe o(s) Documento(s):</label>
								<textarea class="form-control" name="descrMov" value="${descrMov}" cols="20" rows="5" placeholder="Informe no formato: TJPA-PRO-AAAA/NNNNN" ></textarea>
							</div>
						</div>
					</div>

					<div class="row">
						<div class="col-sm">
							<input type="submit" value="Ok" class="btn btn-primary" />
							<input type="button" value="Cancela" onclick="javascript:history.back();" class="btn btn-cancel ml-2" />
						</div>
					</div>
				</form>
		    </div>
	    </div>
	</div>
	    	    
</siga:pagina>