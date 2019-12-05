
 function dropRol(idRol) {
        if (confirm("\u00bfSeguro que deseas realizar esta acci\u00f3n?")) {
            txt = "You pressed OK!";
            $.ajax({
                url: 'borrar-rol',
                type: "POST",
                dataType: 'text',
                data: {idRol: idRol},
                success: function (resultado) {
                    switch (resultado) {
                    case "Registro correcto":
                        location.reload();
                        alert(resultado);
                        break;
                    case "Error, el rol contiene usuarios ligados":                      
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





    function registrarRol() {             
        $.ajax({                                
                type: 'POST',                           
                data : $("#formRol").serialize(),
                success: function (resultado) {
                    switch (resultado) {
                        case "Registro correcto":
                            location.href = 'http://localhost:30533/Maar/roles';
                            alert(resultado);
                            break;
                        case "Rol no disponible":                                                                                   
                            alert(resultado);                            
                            location.reload();
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

    

    jQuery(document).ready(function ($){

    });
