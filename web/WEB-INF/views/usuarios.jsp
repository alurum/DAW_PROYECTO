<%-- 
    Document   : usuarios
    Created on : 11/10/2019, 05:18:26 PM
    Author     : Lalo
--%>
<div class="row">
    <div class="col-sm-11 col-md-11 col-lg-11">
<h1><i class="fa fa-users"></i>  Lista de usuarios</h1>   
</div>      
<div class="col-sm-1 col-md-1 col-lg-1">
<br><i class="fa fa-plus  fa-3x"  onclick="location.href = 'agregar-usuario'"></i> 
</div>
</div>
<br>
<table  table id="users" class="table table-striped table-bordered" style="width:100%">
    <thead>
        <tr>
            <th align="center">NOMBRE</th>
            <th align="center">SALARIO</th>
            <th align="center">CELULAR</th>
            <th align="center">DIRECCIÓN</th>
            <th align="center">USUARIO</th>
            <th align="center">ROL</th>
            <th align="center">OPCIONES</th>
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
                    <button type="button" class="btn btn-warning btn-sm"  name="Editar"  onclick="location.href = 'editar-usuario?i=${dato.usuario}'"><i class="fa fa-pencil-square-o"></i> Editar</button> 
                    <button type="button" class="btn btn-danger btn-sm " name="Borrar"  onclick="drop ( '${dato.usuario}' )"><i class="fa fa-remove"></i> Borrar</button>
                </td>                                                                       
            </tr>
        </c:forEach>
    </tbody>
</table>













