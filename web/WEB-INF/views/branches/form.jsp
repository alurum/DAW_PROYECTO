<%-- 
    Document   : usuarios
    Created on : 11/10/2019, 05:18:26 PM
    Author     : Lalo
--%>


<div class="col-sm-12 col-md-12 col-lg-12" align="center">
    <h1><i class="fa fa-plus"></i>  ${titulo}</h1>              
    <form method="POST" id="formSucursal" class="row" action=${action}>       
        <input name="idSuc" id="idSuc" type="hidden" value="${sucursal.idSuc}">
        <br><br>
           <div class="col-sm-4 col-md-5 col-lg-5"> 		 
                    <div class="form-group"><strong>Nombre de sucursal</strong>
                        <input class = "form-control"  type="text" id="nombreSucursal" name="nombreSucursal" value="${sucursal.nombre}"
                               placeholder="Nombre" maxlength="20" required>
                    </div>
                </div>
                <div class="col-sm-4 col-md-5 col-lg-5">
                    <div class="form-group"><strong>Cliente</strong>
                        <select  class="form-control"  id="idClien" name="idClien"
                                maxlength="1" required>                            
                            <c:forEach var="cliente" items="${clientes}">
                                <option value="${cliente.idClien}" >${cliente.nombre}</option>  
                            </c:forEach>                
                        </select>
                    </div>
                </div>
                <div class="col-sm-4 col-md-5  col-lg-4">
                    <div class="form-group"><strong>Número de tienda</strong>
                        <input class="form-control"  type="number" placeholder="0.00" min="0" step="1.00"  id="notienda" name="notienda" value="${sucursal.notienda}" 
                                maxlength="10" required>
                    </div>
                </div>
                               
                <div class="col-sm-4 col-md-5  col-lg-4">
                    <div class="form-group"><strong>Dirección</strong>
                        <input class="form-control"  type="text" id="direccionSucursal" name="direccionSucursal" value="${sucursal.direccion}" 
                               placeholder="Dirección" maxlength="10" required>
                    </div>
                </div>
                
        <div class="col-sm-12">
            <div class="form-group text-center">           
                <a href="http://localhost:30533/Maar/sucursales"
                   class="btn btn-danger">
                    <i class="fa fa-times"> </i>
                    Cancelar
                </a>
                <button  onclick="registrarSucursal()"
                      class="btn btn-primary">
                    <span class="glyphicon glyphicon-ok"></span>
                    Registrar
                </button>
            </div>
        </div>
    </form>                     
</div>


