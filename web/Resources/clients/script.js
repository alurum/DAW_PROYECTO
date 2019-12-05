
function dropCliente(idClienSource) {
    if (confirm("\u00bfSeguro que deseas realizar esta acci\u00f3n?")) {
        txt = "You pressed OK!";
        $.ajax({
            url: 'borrar-cliente',
            type: "POST",
            dataType: 'text',
            data: {idClienSource: idClienSource},
            success: function (resultado) {
                switch (resultado) {
                    case "Registro correcto":
                        location.reload();
                        alert(resultado);
                        break;
                    case "Error, el cliente contiene sucursales ligadas":
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



function registrarCliente() {    
    $.ajax({        
        type: 'POST',
        data: $("#formCliente").serialize(),
        success: function (resultado) {
            switch (resultado) {
                case "Registro correcto":
                    location.href = 'http://localhost:30533/Maar/clientes';
                    alert(resultado);
                    break;
                case "Domicilio no disponible":
                    location.reload();
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

