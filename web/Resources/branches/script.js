
function dropSucursal(idSuc) {
    if (confirm("\u00bfSeguro que deseas realizar esta acci\u00f3n?")) {
        txt = "You pressed OK!";
        $.ajax({
            url: 'borrar-sucursal',
            type: "POST",
            dataType: 'text',
            data: {idSuc: idSuc},
            success: function (resultado) {
                switch (resultado) {
                    case "Registro correcto":
                        location.reload();
                        alert(resultado);
                        break;
                    case "Error, la sucursal contiene pedidos ligados":
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



function registrarSucursal() {    
    $.ajax({        
        type: 'POST',
        data: $("#formSucursal").serialize(),
        success: function (resultado) {
            switch (resultado) {
                case "Registro correcto":
                    location.href = 'http://localhost:30533/Maar/sucursales';
                    alert(resultado);
                    break;
                case "Sucursal no disponible":
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

