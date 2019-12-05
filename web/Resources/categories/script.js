
 function dropCategoria(idCatSource) {
        if (confirm("\u00bfSeguro que deseas realizar esta acci\u00f3n?")) {
            txt = "You pressed OK!";
            $.ajax({
                url: 'borrar-categoria',
                type: "POST",
                dataType: 'text',
                data: {idCatSource: idCatSource},
                success: function (resultado) {
                    switch (resultado) {
                    case "Registro correcto":
                        location.reload();
                        alert(resultado);
                        break;
                    case "Error, productos ligados":                      
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




    function registrarCategoria() {             
        $.ajax({                                
                type: 'POST',                           
                data : $("#formCategoria").serialize(),
                success: function (resultado) {
                    switch (resultado) {
                        case "Registro correcto":
                            location.href = 'http://localhost:30533/Maar/categorias';
                            alert(resultado);
                            break;
                        case "Nombre no disponible":                                                                                   
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
