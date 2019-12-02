   
    
 function dropSucursal(idSuc) {
        if (confirm("¿Seguro que deseas realizar esta accion?")) {
            txt = "You pressed OK!";
            $.ajax({
                url: 'borrar-sucursal',
                type: "POST",
                data: {idSuc: idSuc},
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

        $("#registrarSucursal").click(function () {
            var action = document.getElementById("action").value;
            var idSuc = document.getElementById("idSuc").value;
            var nombre = document.getElementById("nombreSucursal").value;
            var direccion = document.getElementById("direccionSucursal").value;
            var notienda = document.getElementById("notienda").value;
            var idClien = document.getElementById("idClien").value;
            $.ajax({
                url: action,
                type: 'POST',
                data: {idSuc: idSuc, nombre: nombre, direccion: direccion, notienda: notienda, idClien: idClien},
                success: function (resultado) {
                    switch (resultado) {
                        case "Registro correcto":
                            location.href = 'http://localhost:30533/Maar/sucursales';
                            alert(resultado);
                            break;
                        case "Dirección de sucursal no disponible":
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

