// Call the dataTables jQuery plugin
$(document).ready(function() {
    $('#dataTable').DataTable( {
        "language": {
        	"processing": "Processando...",
            "lengthMenu": "Mostrar _MENU_ registros por página",
            "zeroRecords": "Nenhum há registros - desculpe",
            "info": "Mostrando página _PAGE_ até _PAGES_",
            "infoEmpty": "Não foram encontrados registros para sua pesquisa",
            "search": "Pesquisar:",
            "infoFiltered": "(total de registros: _MAX_ )",
            "paginate": {
            	"first":    "Primeiro",
                "previous": "Anterior",
                "next":     "Seguinte",
                "last":     "Último"	
            }
        }
    });
});