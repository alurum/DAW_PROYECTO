
function dropPedido(idPedSource) {
    if (confirm("\u00bfSeguro que deseas realizar esta acci\u00f3n?")) {
        txt = "You pressed OK!";
        $.ajax({
            url: 'borrar-pedido',
            type: "POST",
            dataType: 'text',
            data: {idPedSource: idPedSource},
            success: function (resultado) {
                switch (resultado) {
                    case "Registro correcto":
                        location.reload();
                        alert(resultado);
                        break;
                    case "Error, el pedido contiene ventas ligadas":
                        alert(resultado);
                        break;
                    default :
                        location.reload();
                }
            },
            error: function (resultado) {
                alert("Algo salió mal, intentelo nuevamente");
            }
        });

    }

}



function registrarPedido() {    
    $.ajax({        
        type: 'POST',
        data: $("#formPedido").serialize(),
        success: function (resultado) {
            switch (resultado) {
                case "Registro correcto":
                    location.href = 'http://localhost:30533/Maar/pedidos';
                    alert(resultado);                                    
                    break;
                    defaul:
                            location.reload();
            }
        },
        error: function (resultado) {
            location.reload();
            alert("Algo salió mal, intentelo nuevamente");
        }

    });

}






jQuery(document).ready(function ($) {

});

