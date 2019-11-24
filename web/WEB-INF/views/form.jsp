<%-- 
    Document   : usuarios
    Created on : 11/10/2019, 05:18:26 PM
    Author     : Lalo
--%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
    </head>
    <body>
        <div class="col-sm-12 col-md-12 col-lg-12" >
            <h1><i class="fa fa-plus"></i>  ${titulo}</h1>          
            <input name="action" id="action" type="hidden" value="${action}">
            <form method="POST" id="form" class="row">       
                <input name="id_Aso" type="hidden" value="${dato.idAso}">
                <br><br>
                <h5><strong>Información personal</h5></strong>
                <div class="col-sm-3 col-md-3 col-lg-7">
                    <div class="form-group">
                        <input class = "form-control"  type="text" id="nombre" name="nombre" value="${dato.nombre}"
                               placeholder="Nombre completo" maxlength="20" required>					  
                    </div>
                </div>	   
                <div class="col-sm-3 col-md-3 col-lg-7">
                    <div class="form-group">
                        <input class = "form-control"  type="text" id="direccion" name="direccion" value="${dato.direccion}"
                               placeholder="Dirección" maxlength="30"> 

                    </div>
                </div>
                <div class="col-sm-1 col-md-1 col-lg-8"></div>

                <div class="col-sm-4 col-md-4 col-lg-5">
                    <div class="form-group">
                        <input class = "form-control"  type="number" id="celular" name="celular" value="${dato.celular}"
                               placeholder="Celular" maxlength="10" required>
                    </div>
                </div>           
                <div class="col-sm-1 col-md-1 col-lg-8"></div>
                <div class="col-sm-4 col-md-5 col-lg-5"> 		 
                    <div class="form-group"><strong>Nombre de usuario</strong>
                        <input class = "form-control"  type="password" id="usuario" name="usuario" value="${dato.usuario}"
                               placeholder="usuario" maxlength="20" required>
                    </div>
                </div>
                <div class="col-sm-4 col-md-5 col-lg-5">
                    <div class="form-group"><strong>Tipo de usuario</strong>
                        <select  class="form-control"  id="idRol" name="idRol"
                                maxlength="1" required>
                            <option value >Tipo de usuario</option>
                            <c:forEach var="rol" items="${roles}">
                                <option value="${rol.idRol}" >${rol.nombre}</option>  
                            </c:forEach>                
                        </select>
                    </div>
                </div>
                <div class="col-sm-4 col-md-5  col-lg-4">
                    <div class="form-group"><strong>Salario</strong>
                        <input class="form-control"  type="number" id="salario" name="salario" value="${dato.salario}" 
                               placeholder="Salario" maxlength="10" required>
                    </div>
                </div>
                <div class="col-sm-4 col-md-5 col-lg-4">
                    <div class="form-group"><strong>Contraseña</strong>
                        <input class = "form-control"  type="password" id="contraseña" name="contraseña" value="${dato.contraseña}"
                               placeholder="Contraseña" maxlength="20" required>

                    </div>
                </div>            
                <div class="col-sm-4 col-md-5 col-lg-4">
                    <div class="form-group"><strong>Repita contraseña</strong>
                        <input class = "form-control"  type="password" id="Rcontraseña" name="Rcontraseña" value="${dato.contraseña}"
                               placeholder="Contraseña" maxlength="20" required>
                    </div>
                </div>            
                <div class="col-sm-12">
                    <div class="form-group text-center">           
                        <a href="http://localhost:30533/Maar/usuarios"
                           class="btn btn-danger">
                            <i class="fa fa-times"> </i>
                            Cancelar
                        </a>
                        <button type="submit" id="registrar"
                                class="btn btn-primary">
                            <span class="glyphicon glyphicon-ok"></span>
                            Registrar
                        </button>
                    </div>
                </div>
            </form>                     

        </div>

    </body>

    <script src="https://code.jquery.com/jquery-3.4.1.min.js" type="text/javascript"></script>

    <script>



        var action = document.getElementById("action").value;
        

        



        jQuery(document).ready(function ($) {
            $("#registrar").click(function () {    
        var nombre = document.getElementById("nombre").value;
        var salario = document.getElementById("salario").value;
        var celular = document.getElementById("celular").value;
        var direccion = document.getElementById("direccion").value;
        var usuario = document.getElementById("usuario").value;
        var contraseña = document.getElementById("contraseña").value;
        var Rcontraseña = document.getElementById("Rcontraseña").value;
        var idRol = document.getElementById("idRol").value;
        
                $.ajax({
                    url: action,
                    type: 'POST',                    
                    data: {nombre: nombre, salario: salario, celular: celular, direccion: direccion, usuario: usuario, contraseña: contraseña, Rcontraseña: Rcontraseña, idRol: idRol},
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
                    error: function (resultado, status, xhr) {
                        alert("Algo salio mal, intente nuevamente" + resultado);
                    }

                });

            });
        });



    </script>


</html>
