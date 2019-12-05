
function dropFiscal(idFisSource) {
    if (confirm("\u00bfSeguro que deseas realizar esta acci\u00f3n?")) {
        txt = "You pressed OK!";
        $.ajax({
            url: 'borrar-fiscal',
            type: "POST",
            dataType: 'text',
            data: {idFisSource: idFisSource},
            success: function (resultado) {
                switch (resultado) {
                    case "Registro correcto":
                        location.reload();
                        alert(resultado);
                        break;
                    case "Error, la fiscal contiene clientes ligados":                        
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



function registrarFiscal() {    
    $.ajax({        
        type: 'POST',
        data: $("#formFiscal").serialize(),
        success: function (resultado) {
            switch (resultado) {
                case "Registro correcto":
                    location.href = 'http://localhost:30533/Maar/fiscales';
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

