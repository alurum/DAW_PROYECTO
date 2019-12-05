
function dropVenta(idVen) {
    if (confirm("\u00bfSeguro que deseas realizar esta acci\u00f3n?")) {
        txt = "You pressed OK!";
        $.ajax({
            url: 'borrar-venta',
            type: "POST",
            dataType: 'text',
            data: {idVen: idVen},
            success: function (resultado) {
                switch (resultado) {
                    case "Registro correcto":
                        location.reload();
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



function registrarVenta() {    
    $.ajax({        
        type: 'POST',
        data: $("#formVenta").serialize(),
        success: function (resultado) {
            switch (resultado) {
                case "Registro correcto":
                    location.href = 'http://localhost:30533/Maar/ventas';
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

