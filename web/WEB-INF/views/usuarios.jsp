<%-- 
    Document   : usuarios
    Created on : 11/10/2019, 05:18:26 PM
    Author     : Lalo
--%>
<h1><i class="fa fa-users"></i>  Lista de usuarios</h1>                        
<br>        
<table  table id="users" class="table table-striped table-bordered" style="width:100%">
    <thead>
        <tr>
            <th>NOMBRE</th>
            <th>SALARIO</th>
            <th>CELULAR</th>
            <th>DIRECCIÓN</th>
            <th>USUARIO</th>
            <th>ROL</th>
            <th>OPCIONES</th>
        </tr>
    </thead>
    <tbody>
        <c:forEach var="dato" items="${datos}">
            <tr>                    
                <td>${dato.nombre}</td>
                <td>${dato.salario}</td>
                <td>${dato.celular}</td>
                <td>${dato.direccion}</td>
                <td>${dato.usuario}</td>
                <td>${dato.idRol.nombre}</td>
                
                <td align="center">
                    
                    <button type="button" class="btn btn-primary btn-sm" onclick="location.href = 'agregar-usuario'">Agregar</button>
                    <button type="button" class="btn btn-warning btn-sm"  name="Editar"  onclick="location.href = 'editar-usuario?i=${dato.usuario}'">Editar</button> 
                    <button type="button" class="btn btn-danger btn-sm " name="Borrar"  onclick="drop ( '${dato.usuario}' )">Borrar</button>
                </td>                                                                       
            </tr>
        </c:forEach>
    </tbody>
</table>













