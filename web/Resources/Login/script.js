jQuery(document).ready(function ($){
  
  
    var animating = false,
      submitPhase1 = 1100,
      submitPhase2 = 400,
      logoutPhase1 = 800,
      $login = $(".login"),
      $app = $(".app");
  
  function ripple(elem, e) {
    $(".ripple").remove();
    var elTop = elem.offset().top,
        elLeft = elem.offset().left,
        x = e.pageX - elLeft,
        y = e.pageY - elTop;
    var $ripple = $("<div class='ripple'></div>");
    $ripple.css({top: y, left: x});
    elem.append($ripple);
  };
  
  
  
      $("#entrar").click(function (){
    
                var usuario = document.getElementById("usuario").value;
                var contraseña = document.getElementById("contraseña").value;
				
                
             
                        $.ajax({ 
                            url:"entrar",
                            type:'POST',                            
                            data: {usuario: usuario, contraseña: contraseña},
                            success: function (resultado) {                                                           
                                if(resultado==="Datos correctos"){
                               
            
                                alert("Datos correctos");
                                location.href='http://localhost:30533/Maar/usuarios'                            
                                } else {   
                                    alert("Datos erroneos");                                       
                                }
                            },                            
                            error: function (resultado) {
                                alert("Algo salio mal, intente nuevamente" + resultado);                                                        
                            }
                            
                        });
                        
                    });
                    
                    
                     $("#registrar").click(function (){
				var nombre = document.getElementById("nombre").value;
				var celular = document.getElementById("celular").value;
				var direccion = document.getElementById("direccion").value;				
                var Rusuario = document.getElementById("Rusuario").value;
                var Rcontraseña1 = document.getElementById("Rcontraseña1").value;
                var Rcontraseña2 = document.getElementById("Rcontraseña2").value;
                
                        $.ajax({ 
                            url:"registrar",
                            type:'POST',                            
                            data: {nombre: nombre, celular: celular, direccion: direccion, Rusuario: Rusuario, Rcontraseña1: Rcontraseña1, Rcontraseña2: Rcontraseña2},
                            success: function (resultado) {                                                           
                                if(resultado==="Registro correcto"){
                               alert("Registro correcto");
                                 
				if (animating) return;
    $(".ripple").remove();
    animating = true;
    var that = this;
    $(that).addClass("clicked");
    setTimeout(function() {
      $app.removeClass("active");
      $login.show();
      $login.css("top");
      $login.removeClass("inactive");
    }, logoutPhase1 - 120);
    setTimeout(function() {
      $app.hide();
      animating = false;
      $(that).removeClass("clicked");
    }, logoutPhase1);
  
                                } else {   
                                    alert("Datos erroneos");                                       
                                }
                            },                            
                            error: function (resultado) {
                                alert("Algo salio mal, intente nuevamente" + resultado);                                                        
                            }
                            
                        });
                        
                    });
                    
                  
                    
                    
  
  
  
  
  
  
  
  

  $(document).on("click", ".login__signup", function(e) {
    if (animating) return;
    animating = true;
    var that = this;
    ripple($(that), e);
    $(that).addClass("processing");
    setTimeout(function() {
      $(that).addClass("success");
      setTimeout(function() {
        $app.show();
        $app.css("top");
        $app.addClass("active");
      }, submitPhase2 - 70);
      setTimeout(function() {
        $login.hide();
        $login.addClass("inactive");
        animating = false;
        $(that).removeClass("success processing");
      }, submitPhase2);
    }, submitPhase1);
  });
  
 
  
});