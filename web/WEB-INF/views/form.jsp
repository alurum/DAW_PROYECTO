<%-- 
    Document   : usuarios
    Created on : 11/10/2019, 05:18:26 PM
    Author     : Lalo
--%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
    </head>
    <body>

        <h1><i class="fa fa-plus"></i>  ${titulo}</h1>          
        <input name="action" id="action" type="hidden" value="${action}">
        <form method="POST" id="frm">
            <br>        
            <input name="id_Aso" type="hidden" value="${dato.idAso}">
            <label>Nombre</label>
            <input type="text" id="usuario" name="usuario" value="${dato.nombre}" />
            <br><br>
            <label>Salario</label>
            <input type="text" id="password" name="password" value="${dato.salario}" />
            <br><br>  
            <label>Celular</label>
            <input type="text" id="repassword" name="repassword" value="${dato.celular}" />
            <br><br>
            <label>Dirección</label>
            <input type="text" id="password" name="password" value="${dato.direccion}" />
            <br><br>  
            <label>Usuario</label>
            <input type="text" id="password" name="password" value="${dato.usuario}" />
            <br><br>  
            <label>Contraseña</label>
            <input type="password" id="password" name="password" value="${dato.contraseña}" />
            <br><br>  
            <label>Rol</label>
            <input type="text" id="password" name="password" value="${dato.idRol.nombre}" />
            <br><br>  

            

                        <input id="realizar" type="button" value="realizar">
                        <input  name="button" type="button" value="cancelar" onclick="">      
                        </form>                     



                        </body>

                        <script src="https://code.jquery.com/jquery-3.4.1.min.js" type="text/javascript"></script>

                        <script>



                            var action = document.getElementById("action").value;


                            jQuery(document).ready(function ($) {


                                $("#realizar").click(function () {

                                    $.ajax({
                                        url: action,
                                        type: 'POST',
                                        dataType: 'text',
                                        data: $('#frm').serialize(),
                                        success: function (resultado) {
                                            alert(resultado);
                                            //location.href='http://localhost:30533/iti44__02_2/usuarios'
                                            //  $("#r").html(Bienvenido);
                                        },
                                        error: function (resultado, status, xhr) {
                                            alert('err:' + status);
                                        }

                                    });

                                });
                            });



                        </script>


                        </html>
