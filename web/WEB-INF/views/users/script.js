


    function drop(usuario, SSusr) {
        if (confirm("¿Seguro que deseas realizar esta accion?")) {
            txt = "You pressed OK!";
            $.ajax({
                url: 'borrar-usuario',
                type: "POST",
                data: {usuario: usuario, SSusr: SSusr},
                success: function (resultado) {
                    switch (resultado) {
                        case "Error, usuario en uso!":
                            alert(resultado);
                            break;
                        case "Registro correcto":
                            alert(resultado);
                            location.reload();
                            break;
                        default :
                            location.reload();
                    }
                },
                error: function (resultado) {
                    alert("Algo salio mal, intente nuevamente" + resultado);
                }
            });

        }

    }










    jQuery(document).ready(function ($) {

$("#registrar").click(function () {
    var action = document.getElementById("action").value;
    var idAso = document.getElementById("idAso").value;
    var nombre = document.getElementById("nombre").value;
    var salario = document.getElementById("salario").value;
    var password = document.getElementById("password").value;
    var Rpassword = document.getElementById("Rpassword").value;
    var celular = document.getElementById("celular").value;
    var direccion = document.getElementById("direccion").value;
    var usuario = document.getElementById("usuario").value;
    var idRol = document.getElementById("idRol").value;
        $.ajax({
            url: action,
            type: 'POST',            
            data: {idAso: idAso, nombre: nombre, salario: salario, password: password, Rpassword: Rpassword, celular: celular, direccion: direccion, usuario: usuario, idRol: idRol},
                success: function (resultado) {
                    switch (resultado) {
                        case "Registro correcto":
                            alert(resultado);
                            location.href = 'http://localhost:30533/Maar/usuarios';
                            break;
                        case "Usuario no disponible":
                            alert(resultado);
                            location.reload();
                            break;
                        case "Contraseñas diferentes":
                            alert(resultado);
                            location.reload();
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
        
        
        
    $("#profile").click(function () { 
    var SSidAso = document.getElementById("SSidAso").value;
    var SSusuario = document.getElementById("SSusuario").value;
    var SSpassword = document.getElementById("SSpassword").value;
    var SSRpassword = document.getElementById("SSRpassword").value;
    $.ajax({
            url: 'profile',
            type: 'POST',
            data: {SSidAso: SSidAso, SSusuario: SSusuario, SSpassword: SSpassword, SSRpassword: SSRpassword},
            success: function (resultado) {
                switch (resultado) {
                    case "Registro correcto":
                        alert(resultado);
                        location.href = 'http://localhost:30533/Maar/usuarios';
                        break;
                    case "Usuario no disponible":
                        alert(resultado);
                        break;
                    case "Contraseñas diferentes":
                        alert(resultado);
                        break;
                        defaul:
                                location.reload();
                }
            },
            error: function (resultado) {
                alert("Algo salio mal, intente nuevamente" + resultado);
            }

        });

    });
        
        
        

    });