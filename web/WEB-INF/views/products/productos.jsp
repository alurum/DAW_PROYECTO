<%-- 
    Document   : usuarios
    Created on : 11/10/2019, 05:18:26 PM
    Author     : Lalo
--%>
<div class="row">
    <div class="col-sm-11 col-md-11 col-lg-11">
<h1><i class="fa fa-list-alt"></i>  Lista de productos</h1>   
</div>      
<div class="col-sm-1 col-md-1 col-lg-1">
    <br><i class="fa fa-plus  fa-3x"  onclick="location.href = 'agregar-producto'"></i> 
</div>
    
</div>
<br>
<table  table  class="table table-striped table-bordered" style="width:100%">
    <thead>
        <tr>
            <th style="text-align:center">NOMBRE</th>            
            <th style="text-align:center">PRECIO</th>            
            <th style="text-align:center">SABOR</th>            
            <th style="text-align:center">CATEGORIA</th>            
            <th style="text-align:center">OPCIONES</th>
        </tr>
    </thead>
    <tbody>
        <c:forEach var="producto" items="${productos}">            
            <tr>                    
                <td>${producto.nombre}</td>                
                <td>${producto.precio}</td>                
                <td>${producto.sabor}</td>                
                <td>${producto.idCat.nombre}</td>                                
                <td align="center">                                       
                    <button type="button" class="btn btn-warning btn-sm"  name="Editar"  onclick="location.href = 'editar-producto?i=${producto.idPro}'"><i class="fa fa-pencil-square-o"></i> Editar</button> 
                    <button type="button" class="btn btn-danger btn-sm " name="Borrar"  onclick="dropProducto ( '${producto.idPro}' )"><i class="fa fa-remove"></i> Borrar</button>
                </td>                                                                       
            </tr>
            
        </c:forEach>
    </tbody>
</table>













