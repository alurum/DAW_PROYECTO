
 function dropRol(idRol) {
        if (confirm("¿Seguro que deseas realizar esta accion?")) {
            txt = "You pressed OK!";
            $.ajax({
                url: 'borrar-rol',
                type: "POST",
                data: {idRol: idRol},
                success: function (resultado) {
                    location.reload();
                    alert(resultado);
                },
                error: function (resultado) {
                    alert("Algo salio mal, intente nuevamente");
                }
            });

        }

    }





    jQuery(document).ready(function ($) {

        $("#registrarRol").click(function () {
            var action = document.getElementById("action").value;
            var idRol = document.getElementById("idRol").value;
            var nombre = document.getElementById("nombreRol").value;
            $.ajax({
                url: action,
                type: 'POST',
                data: {idRol: idRol, nombre: nombre},
                success: function (resultado) {
                    switch (resultado) {
                        case "Registro correcto":
                            location.href = 'http://localhost:30533/Maar/roles';
                            alert(resultado);
                            break;
                        case "Rol no disponible":
                            location.reload();
                            alert(resultado);
                            break;
                            defaul:
                                    location.reload();
                    }
                },
                error: function (resultado) {
                    location.reload();
                    alert("Algo salio mal, intente nuevamente");
                }

            });

        });





    });
