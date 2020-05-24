function abreAlteraStatusModal(id) {
	$.ajax({

		url : "/alteraStatus/" + id,
		success : function(data) {
			$("#alteraStatusModalHolder").html(data);
			$('#alteraStatusModal').modal({
				backdrop : 'static'
			});
		}
	});
}

function abreAlteraProdutoModal(id) {
	$.ajax({
		url : "/alteraProduto/" + id,
		success : function(data) {
			$("#alteraProdutoModalHolder").html(data);
			$('#alteraProdutoModal').modal({
				backdrop : 'static'
			});
		}
	});
}

function abreVisalizaProdutoModal(id) {
	$.ajax({
		url : "/visualizaProduto/" + id,
		success : function(data) {
			$("#visualizaProdutoModalHolder").html(data);
			$('#visualizaProdutoModal').modal({
				backdrop : 'static'
			});
		}
	});
}

function abreAlteraOfertaModal(id) {
	$.ajax({
		url : "/alteraOferta/" + id,
		success : function(data) {
			$("#alteraOfertaModalHolder").html(data);
			$('#alteraOfertaModal').modal({
				backdrop : 'static'
			});
		}
	});
}

function abreVisualizaOfertaModal(id) {
	$.ajax({
		url : "/visualizaOferta/" + id,
		success : function(data) {
			$("#visualizaOfertaModalHolder").html(data);
			$('#visualizaOfertaModal').modal({
				backdrop : 'static'
			});
		}
	});
}

function abreAlteraNivelModal(id) {
	$.ajax({

		url : "/alteraNivel/" + id,
		success : function(data) {
			$("#alteraNivelModalHolder").html(data);
			$('#alteraNivelModal').modal({
				backdrop : 'static'
			});
		}
	});
}

function abreAlteraClienteModal(id) {

	$.ajax({

		url : "/alteraCliente/" + id,
		success : function(data) {
			$("#alteraClienteModalHolder").html(data);
			$('#alteraClienteModal').modal({
				backdrop : 'static'
			});
		}
	});
}

function abreVisualizaClienteModal(id) {
	$.ajax({

		url : "/visualizaCliente/" + id,
		success : function(data) {
			$("#visualizaClienteModalHolder").html(data);
			$('#visualizaClienteModal').modal({
				backdrop : 'static'
			});
		}
	});
}

function abreVisualizaTimelineClienteModal(id) {
	$.ajax({

		url : "/visualizaTimelineCliente/" + id,
		success : function(data) {
			$("#visualizaTimelineClienteModalHolder").html(data);
			$('#visualizaTimelineClienteModal').modal({
				backdrop : 'static'
			});
		}
	});
}

function abreAlteraAcaoModal(id) {
	$.ajax({

		url : "/alteraAcao/" + id,
		success : function(data) {
			$("#alteraAcaoModalHolder").html(data);
			$('#alteraAcaoModal').modal({
				backdrop : 'static'
			});
		}
	});
}

function abreFormNivelModal() {
	$.ajax({
		url : "/cadastraNivel/",
		success : function(data) {
			$("#formNivelModalHolder").html(data);
			$('#formNivelModal').modal({
				backdrop : 'static'
			});
		}
	});
}

function abreFormAcaoModal() {
	$.ajax({
		url : "/cadastraAcao/",
		success : function(data) {
			$("#formAcaoModalHolder").html(data);
			$('#formAcaoModal').modal({
				backdrop : 'static'
			});
		}
	});
}

function abreFormCategoriaModal() {
	$.ajax({
		url : "/cadastraCategoria/",
		success : function(data) {
			$("#formCategoriaModalHolder").html(data);
			$('#formCategoriaModal').modal({
				backdrop : 'static'
			});
		}
	});
}

function abreFormUsuarioModal() {
	$.ajax({
		url : "/cadastraUsuario/",
		success : function(data) {
			$("#formUsuarioModalHolder").html(data);
			$('#formUsuarioModal').modal({
				backdrop : 'static'
			});
		}
	});
}

function abreFormPerfilModal() {
	$.ajax({
		url : "/cadastraPerfil/",
		success : function(data) {
			$("#formPerfilModalHolder").html(data);
			$('#formPerfilModal').modal({
				backdrop : 'static'
			});
		}
	});
}

function abreFormStatusModal() {
	$.ajax({
		url : "/cadastraStatus/",
		success : function(data) {
			$("#formStatusModalHolder").html(data);
			$('#formStatusModal').modal({
				backdrop : 'static'
			});
		}
	});
}

function abreFormAcaoUsuarioClienteModal(id) {
	$.ajax({
		url : "/cadastraAcaoUsuarioCliente/" + id,
		success : function(data) {
			$("#formAcaoUsuarioClienteModalHolder").html(data);
			$('#formAcaoUsuarioClienteModal').modal({
				backdrop : 'static'
			});
		}
	});
}

function abreFormAcaoUsuarioClienteOfertaModal(id) {
	$.ajax({
		url : "/cadastraAcaoUsuarioClienteOferta/" + id,
		success : function(data) {
			$("#formAcaoUsuarioClienteOfertaModalHolder").html(data);
			$('#formAcaoUsuarioClienteOfertaModal').modal({
				backdrop : 'static'
			});
		}
	});
}

function abreAlteraClienteOfertaModal(id) {
	$.ajax({
		url : "/alteraClienteOferta/" + id,
		success : function(data) {
			$("#formAlteraClienteOfertaModalHolder").html(data);
			$('#formAlteraClienteOfertaModal').modal({
				backdrop : 'static'
			});
		}
	});
}

function abreVisualizaClienteOfertaModal(id) {
	$.ajax({
		url : "/visualizaClienteOferta/" + id,
		success : function(data) {
			$("#visualizaClienteOfertaModalHolder").html(data);
			$('#visualizaClienteOfertaModal').modal({
				backdrop : 'static'
			});
		}
	});
}

$(document).ready(function() {
	$('h5.accordion').click(function() {
		$(this).parent().find('div.accordion1').slideToggle("slow");
	});
});