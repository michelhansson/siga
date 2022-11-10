var meses = [ "Janeiro", "Fevereiro", "Mar�o", "Abril", "Maio", "Junho", "Julho", "Agosto", "Setembro", "Outubro", "Novembro", "Dezembro" ];
var dias = ["D", "S", "T", "Q", "Q", "S", "S"];
var msgComparativoData = "A Data Final dever� ser igual ou posterior � Data Inicial!";

$(document).ready(function(){

	$("#_data_inicial").change(function(){
		if($("#_data_inicial").val != ''){
			if($("#_data_final").val() != ''){
				var data_inicial = $("#_data_inicial").val().split("/");    
				var data_inicial_d = new Date(data_inicial[2], data_inicial[1]-1, data_inicial[0]);
				var data_final = $("#_data_final").val().split("/");    
				var data_final_d = new Date(data_final[2], data_final[1]-1, data_final[0]);
				if(data_inicial_d > data_final_d){
					alert(msgComparativoData);
					$("#_data_inicial").val('');
					$("#_data_inicial").focus();
				}
			}
			if($("#_data_dje").val != ''){
				var data_inicial = $("#_data_inicial").val().split("/");    
				var data_inicial_d = new Date(data_inicial[2], data_inicial[1]-1, data_inicial[0]);
				var data_dje = $("#_data_dje").val().split("/");    
				var data_dje_d = new Date(data_dje[2], data_dje[1]-1, data_dje[0]);
				if(data_inicial_d < data_dje_d){
					alert('A data inicial n�o pode ser inferior a data de publica��o do DJE');
					$("#_data_inicial").val('');
					$("#_data_inicial").focus();
				}
			}
		}
	});
	$("#_data_inicial").blur(function(){
		if($("#_data_inicial").val == ''){
			alert('A Data Inicial deve ser preenchida!');
		}else{
			if($("#_data_dje").val != ''){
				var data_inicial = $("#_data_inicial").val().split("/");    
				var data_inicial_d = new Date(data_inicial[2], data_inicial[1]-1, data_inicial[0]);
				var data_dje = $("#_data_dje").val().split("/");    
				var data_dje_d = new Date(data_dje[2], data_dje[1]-1, data_dje[0]);
				if(data_inicial_d < data_dje_d){
					alert('A data inicial n�o pode ser inferior a data de publica��o do DJE');
					$("#_data_inicial").val('');
					$("#_data_inicial").focus();
				}
			}
		}
		
	});
	$("#_data_final").change(function(){
		console.log($("#_data_final").val);
		if($("#_data_final").val != ''){
			if($("#_data_inicial").val() != ''){
				var data_inicial = $("#_data_inicial").val().split("/");    
				var data_inicial_d = new Date(data_inicial[2], data_inicial[1]-1, data_inicial[0]);
				var data_final = $("#_data_final").val().split("/");    
				var data_final_d = new Date(data_final[2], data_final[1]-1, data_final[0]);
				if(data_inicial_d > data_final_d){
					alert(msgComparativoData);
					$("#_data_final").val('');
					$("#_data_final").focus();
				}
			}else{
				alert('A Data Inicial deve ser preenchida!');
				$("#_data_inicial").focus();
			}
		}
	});

	$("#_data_final").blur(function(){
		if($("#_data_final").val == ''){
			alert('A Data Final deve ser preenchida!');
		}
	});
	
	$("#_data_nascimento").blur(function(){
		if(this.val() == ''){
			alert('A data de nascimento deve ser preenchida!');
			this.val('');
			this.focus();
		}
	});
	
	$("#_data_admissao").blur(function(){
		if(this.val() == ''){
			alert('A data de admissão deve ser preenchida!');
			this.val('');
			this.focus();
		}
	});
	
	$("#_data_ingresso").blur(function(){
		if(this.val() == ''){
			alert('A data de ingresso deve ser preenchida!');
			this.val('');
			this.focus();
		}
	});

	$(function(){
		$("#_data_inicial").datepicker({ dateFormat: "dd/mm/yy", monthNames: meses, dayNamesMin: dias});
		$("#_data_final").datepicker({ dateFormat: "dd/mm/yy", monthNames: meses, dayNamesMin: dias});
		$("#_data_nascimento").datepicker({ dateFormat: "dd/mm/yy", monthNames: meses, dayNamesMin: dias});
		$("#_data_ingresso").datepicker({ dateFormat: "dd/mm/yy", monthNames: meses, dayNamesMin: dias});
		$("#_data_simples").datepicker({ dateFormat: "dd/mm/yy", monthNames: meses, dayNamesMin: dias});
		$("#_data_dje").datepicker({ dateFormat: "dd/mm/yy", monthNames: meses, dayNamesMin: dias});
		$("#_data_portaria").datepicker({ dateFormat: "dd/mm/yy", monthNames: meses, dayNamesMin: dias});
		$("#_data_publicacao").datepicker({ dateFormat: "dd/mm/yy", monthNames: meses, dayNamesMin: dias});
		$("#_data_admissao").datepicker({ dateFormat: "dd/mm/yy", monthNames: meses, dayNamesMin: dias});
	}); 
	
	$("#_cpf").mask('999.999.999-99');
	
	$("#_cep").mask('99999-999');
	
	$("#_cpf").blur(function(){
		if(!$("#_cpf").validateCPF()){
			alert('CPF INVALIDO!');
			$("#_cpf").val('');
			$("#_cpf").focus();
		}
	});
	
/*	$("#_matricula").blur(function() {
		$.get('/sigaex/public/app/pessoa/complemento',{matricula:$("#_matricula").val()},function(data) {
			$("#_nome_completo").val(data.nomePessoa);
			$("#_nome_pai").val(data.nomePai);
			$("#_nome_mae").val(data.nomeMae);
			$("#_data_nascimento").val(data.dataNascimento);
			$("#_naturalidade").val(data.naturalidade);
			$("#_cpf").val(data.cpf);
			$("#_identidade").val(data.identidade);
			$("#_titulo_eleitor").val(data.titulo);
			$("#_data_ingresso").val(data.dataInicio);
			$("#_logradouro").val(data.logradouro);
			$("#_cep").val(data.cep);
			$("#_bairro").val(data.bairro);
			$("#_cidade").val(data.cidade);
			$("#_telefones").val(data.telefones);
			$("#_email_institucional").val(data.email);
			$("#_comarca").val(data.comarca);
			$("#_lotacao").val(data.lotacao);
			$("#_cargo").val(data.cargo);
			$("_tipo_sanguineo").val(data.tipoSanguineo);
		},'json');
	});
*/	
	$("#_matricula").blur(function() {
	    $.ajax({
	        type : "GET",
	        url : '/sigaex/public/app/pessoa/complemento?matricula='+$("#_matricula").val(),
	        contentType : "application/json; charset=utf-8",
	        dataType : "json",
	        success : function(data){
				$("#_nome_completo").val(data.nomePessoa);
				$("#_nome_pai").val(data.nomePai);
				$("#_nome_mae").val(data.nomeMae);
				$("#_data_nascimento").val(data.dataNascimento);
				$("#_naturalidade").val(data.naturalidade);
				$("#_cpf").val(data.cpf);
				$("#_identidade").val(data.identidade);
				$("#_titulo_eleitor").val(data.titulo);
				$("#_data_ingresso").val(data.dataInicio);
				$("#_logradouro").val(data.logradouro);
				$("#_cep").val(data.cep);
				$("#_bairro").val(data.bairro);
				$("#_cidade").val(data.cidade);
				$("#_telefones").val(data.telefones);
				$("#_email_institucional").val(data.email);
				$("#_comarca").val(data.comarca);
				$("#_lotacao").val(data.lotacao);
				$("#_cargo").val(data.cargo);
				$("_tipo_sanguineo").val(data.tipoSanguineo);
	        },
	        error: function(data){
		        var erro = JSON.parse(JSON.stringify(data))
		        console.log(erro);
	        },	    
	    });
	});

	
});
