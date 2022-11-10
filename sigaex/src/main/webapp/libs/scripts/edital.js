
$(document).ready( function() {
	
	$("#lista_edital").change(function(){
		var idEdital = $("#lista_edital").val();
		$.ajaxSetup({cache:false});
		$.ajax({
			url: "/sigaex/edital/edital.action",
			data: {id_edital : idEdital},
			async: false,
			dataType: 'json',
		    success: function(data) {
		    	$("#_tipo_concurso").val(data.tipo_concurso);
				$("#_entrancia").val(data.entrancia);
				$("#_vara").val(data.vara);
				$("#_comarca").val(data.comarca);
				$("#_diario").val(data.diario);
				$("#_data_dje").val(data.data_dje);
				$("#_criterio").val(data.criterio);
				$("#_edital_text").val($("#_edital").find("option:selected").text());
		    }
		});
	});
	
	$("#lista_ano").change(function () {	
		var options_edital = '';
		var str = "";	
		$("#lista_ano option:selected").each(function () {
			str += $(this).text();
		});
		
		if ("Ano" != str) {
			$.ajax({
		    	url: "/sigaex/edital/listaEditaisPorAno.action",
		    	async: false,
		    	dataType: 'json',
		    	success: function(data) {
		    		$.each(data.listaEditais, function () {
						options_edital += '<option value="' + this + '">' + this + '</option>';
					});
		    	}
		    });
		}
		
		$("#lista_edital").html(options_edital);
	})
	
	$.ajax({
    	url: "/sigaex/edital/listaAnosEdital.action",
    	async: false,
    	dataType: 'json',
    	success: function(data) {
    		var options = '<option value="">Ano</option>';
    		
    		$.each(data.listaAnos, function () {
    			options += '<option value="' + this + '">' + this + '</option>';
    		});	
    		$("#lista_ano").html(options);
    		
    		$("#lista_ano").change();
    	}
    });
	
});