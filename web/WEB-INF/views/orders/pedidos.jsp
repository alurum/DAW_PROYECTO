<%-- 
    Document   : usuarios
    Created on : 11/10/2019, 05:18:26 PM
    Author     : Lalo
--%>
<div class="row">
    <div class="col-sm-11 col-md-11 col-lg-11">
<h1><i class="fa fa-list-alt"></i>  Lista de pedidos</h1>   
</div>      
<div class="col-sm-1 col-md-1 col-lg-1">
    <br><i class="fa fa-plus  fa-3x"  onclick="location.href = 'agregar-pedido'"></i> 
</div>
    
</div>
<br>
<table  table class="table table-striped table-bordered" style="width:100%">
    <thead>
        <tr>
            <th style="text-align:center">PRODUCTO</th>            
            <th style="text-align:center">CANTIDAD</th>            
            <th style="text-align:center">DIRECCIÓN</th>            
            <th style="text-align:center">COMENTARIO</th>            
            <th style="text-align:center">CLIENTE</th>            
            <th style="text-align:center">SUCURSAL</th>            
            <th style="text-align:center">OPCIONES</th>
        </tr>
    </thead>
    <tbody>
        <c:forEach var="pedido" items="${pedidos}">            
            <tr>                    
                <td>${pedido.idPro.nombre}</td>                
                <td>${pedido.cantidad}</td>                
                <td>${pedido.direccion}</td>                
                <td>${pedido.comentario}</td>                
                <td>${pedido.idClien.nombre}</td>                                
                <td>${pedido.idSuc.nombre}</td>                                
                <td align="center">                                       
                    <button type="button" class="btn btn-warning btn-sm"  name="Editar"  onclick="location.href = 'editar-pedido?i=${pedido.idPed}'"><i class="fa fa-pencil-square-o"></i> Editar</button> 
                    <button type="button" class="btn btn-danger btn-sm " name="Borrar"  onclick="dropPedido ( '${pedido.idPed}' )"><i class="fa fa-remove"></i> Borrar</button>
                </td>                                                                       
            </tr>
            
        </c:forEach>
    </tbody>
</table>













