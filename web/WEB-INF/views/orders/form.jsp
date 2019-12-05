<%-- 
    Document   : usuarios
    Created on : 11/10/2019, 05:18:26 PM
    Author     : Lalo
--%>


<div class="col-sm-12 col-md-12 col-lg-12" align="center">
    <h1><i class="fa fa-plus"></i>  ${titulo}</h1>              
    <form method="POST" id="formPedido" class="row" action=${action}>       
        <input name="idPedSource" id="idPedSource" type="hidden" value="${pedido.idPed}">
        <br><br>
        <div class="col-sm-4 col-md-5 col-lg-5">
            <div class="form-group"><strong>Producto</strong>
                <select  class="form-control"  id="idProPed" name="idProPed"
                         maxlength="1" required>                            
                    <c:forEach var="producto" items="${productos}">
                        <option value="${producto.idPro}" >${producto.nombre}</option>  
                    </c:forEach>                
                </select>
            </div>
        </div>
        <div class="col-sm-2 col-md-2  col-lg-2">
            <div class="form-group"><strong>Cantidad</strong>
                <input class="form-control"  type="number" placeholder="0" min="0" step="1"  id="cantidad" name="cantidad" value="${pedido.cantidad}" 
                       maxlength="10" required>
            </div>
        </div>
        <div class="col-sm-4 col-md-5  col-lg-5">
            <div class="form-group"><strong>Dirección</strong>
                <input class="form-control"  type="text" id="direccionPedido" name="direccionPedido" value="${pedido.direccion}" 
                       placeholder="Dirección" maxlength="20" required>
            </div>
        </div>
        <div class="col-sm-4 col-md-4  col-lg-4">
            <div class="form-group"><strong>Comentario</strong>
                <input class="form-control"  type="text" id="comentario" name="comentario" value="${pedido.comentario}" 
                       placeholder="Comentario" maxlength="100" required>
            </div>
        </div>               
        <div class="col-sm-4 col-md-4 col-lg-4">
            <div class="form-group"><strong>Cliente</strong>
                <select  class="form-control"  id="idClienPed" name="idClienPed"
                         maxlength="1" required>                            
                    <c:forEach var="cliente" items="${clientes}">
                        <option value="${cliente.idClien}" >${cliente.nombre}</option>  
                    </c:forEach>                
                </select>
            </div>
        </div>
        <div class="col-sm-4 col-md-4 col-lg-4">
            <div class="form-group"><strong>Sucursal</strong>
                <select  class="form-control"  id="idSucPed" name="idSucPed"
                         maxlength="1" required>                            
                    <c:forEach var="sucursal" items="${sucursales}">
                        <option value="${sucursal.idSuc}" >${sucursal.nombre}</option>  
                    </c:forEach>                
                </select>
            </div>
        </div>               
        
                       

        <div class="col-sm-12">
            <div class="form-group text-center">           
                <a href="http://localhost:30533/Maar/pedidos"
                   class="btn btn-danger">
                    <i class="fa fa-times"> </i>
                    Cancelar
                </a>
                <button  onclick="registrarPedido()"
                      class="btn btn-primary">
                    <span class="glyphicon glyphicon-ok"></span>
                    Registrar
                </button>
            </div>
        </div>
    </form>                     
</div>


