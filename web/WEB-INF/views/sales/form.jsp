<%-- 
    Document   : usuarios
    Created on : 11/10/2019, 05:18:26 PM
    Author     : Lalo
--%>


<div class="col-sm-12 col-md-12 col-lg-12" align="center">
    <h1><i class="fa fa-plus"></i>  ${titulo}</h1>              
    <form method="POST" id="formPedido" class="row" action=${action}>       
        <input name="idVen" id="idVen" type="hidden" value="${venta.idVen}">
        <br><br>
        <div class="col-sm-4 col-md-4  col-lg-4">
            <div class="form-group"><strong>Devoluciones</strong>
                <input class="form-control"  type="number" placeholder="0" min="0" step="1"  id="devolucione" name="devolucione" value="${venta.devolucione}" 
                       maxlength="10" required>
            </div>
        </div>
                       <div class="col-sm-4 col-md-4  col-lg-4">
            <div class="form-group"><strong>Fecha</strong>
                <input class="form-control"  type="date"  id="fecha" name="fecha" value="${venta.fecha}" 
                       maxlength="10" required>
            </div>
        </div>
                       <div class="col-sm-4 col-md-4  col-lg-4">
            <div class="form-group"><strong>Modo de pago</strong>
                <input class="form-control"  type="text" id="modoPago" name="modoPago" value="${venta.modoPago}" 
                       placeholder="Modo de pago" maxlength="20" required>
            </div>
        </div>                                       
        <div class="col-sm-4 col-md-4 col-lg-4">
            <div class="form-group"><strong>Cliente</strong>
                <select  class="form-control"  id="idClienVen" name="idClienVen"
                         maxlength="1" required>                            
                    <c:forEach var="cliente" items="${clientes}">
                        <option value="${cliente.idClien}" >${cliente.nombre}</option>  
                    </c:forEach>                
                </select>
            </div>
        </div>
                       <div class="col-sm-4 col-md-4 col-lg-4">
            <div class="form-group"><strong>Asociado</strong>
                <select  class="form-control"  id="idAsoVen" name="idAsoVen"
                         maxlength="1" required>                            
                    <c:forEach var="asociado" items="${asociados}">
                        <option value="${asociado.idAso}" >${asociado.nombre}</option>  
                    </c:forEach>                
                </select>
            </div>
        </div>
        <div class="col-sm-4 col-md-4 col-lg-4">
            <div class="form-group"><strong>Pedido</strong>
                <select  class="form-control"  id="idPedVen" name="idPedVen"
                         maxlength="1" required>                            
                    <c:forEach var="pedido" items="${pedidos}">
                        <option value="${pedido.idPed}" >${pedido.idPed}</option>  
                    </c:forEach>                
                </select>
            </div>
        </div>               
        
                       

        <div class="col-sm-12">
            <div class="form-group text-center">           
                <a href="http://localhost:30533/Maar/ventas"
                   class="btn btn-danger">
                    <i class="fa fa-times"> </i>
                    Cancelar
                </a>
                <button  onclick="registrarVenta()"
                      class="btn btn-primary">
                    <span class="glyphicon glyphicon-ok"></span>
                    Registrar
                </button>
            </div>
        </div>
    </form>                     
</div>


