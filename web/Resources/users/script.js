    
function dropUsuario(usuario) {
    if (confirm('\u00bfSeguro que deseas realizar esta acci\u00f3n?')) {
        txt = "You pressed OK!";
        $.ajax({
            url: 'borrar-asociado',
            type: "POST",
            data: {usuario: usuario},
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



 function registrarUsuario() {               
        $.ajax({          
            type: 'POST',
            data: $("#formUsuario").serialize(),
            success: function (resultado) {                      
                switch (resultado) {
                    case "Registro correcto":
                        location.href = 'http://localhost:30533/Maar/asociados';
                        alert(resultado);                        
                        break;
                    case "Usuario no disponible":
                        location.reload();
                        alert(resultado);                        
                        break;
                    case "Passwords diferentes":
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
    
    
    
    
     function profileUsuario() {        
        $.ajax({
            url: 'profile',
            type: 'POST',
            data: $("#formProfile").serialize(),
            success: function (resultado) {
                switch (resultado) {
                    case "Registro correcto":
                        alert(resultado);                        
                        location.href = 'http://localhost:30533/Maar/logout';
                        alert("Por motivos de seguridad debes iniciar sesión nuevamente");                        
                        break;
                    case "Usuario no disponible":                                                
                        alert(resultado);
                        location.reload();
                        break;
                    case "Passwords diferentes":                                                                      
                        alert(resultado);     
                        location.reload();
                        break;
                        defaul:
                                location.reload();
                }
            },
            error: function (resultado) {
                alert("Algo salió mal, intentelo nuevamente");
            }

        });

    }

    



jQuery(document).ready(function ($) {

});