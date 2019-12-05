
function dropProducto(idPro) {
    if (confirm("\u00bfSeguro que deseas realizar esta acci\u00f3n?")) {
        txt = "You pressed OK!";
        $.ajax({
            url: 'borrar-producto',
            type: "POST",
            dataType: 'text',
            data: {idPro: idPro},
            success: function (resultado) {
                switch (resultado) {
                    case "Registro correcto":
                        location.reload();
                        alert(resultado);
                        break;
                    case "Error, el producto contiene pedidos ligados":
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



function registrarProducto() {    
    $.ajax({        
        type: 'POST',
        data: $("#formProducto").serialize(),
        success: function (resultado) {
            switch (resultado) {
                case "Registro correcto":
                    location.href = 'http://localhost:30533/Maar/productos';
                    alert(resultado);
                    break;
                case "Nombre no disponible":
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

