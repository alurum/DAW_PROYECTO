<%-- 
    Document   : usuarios
    Created on : 11/10/2019, 05:18:26 PM
    Author     : Lalo
--%>


<div class="col-sm-12 col-md-12 col-lg-12" align="center">
    <h1><i class="fa fa-plus"></i>  ${titulo}</h1>              
    <form method="POST" id="formCliente" class="row" action=${action}>       
        <input name="idClienSource" id="idClienSource" type="hidden" value="${cliente.idClien}">
        <br><br>
           <div class="col-sm-4 col-md-5 col-lg-5"> 		 
                    <div class="form-group"><strong>Nombre de cliente</strong>
                        <input class = "form-control"  type="text" id="nombreCliente" name="nombreCliente" value="${cliente.nombre}"
                               placeholder="Nombre" maxlength="20" required>
                    </div>
                </div>
                <div class="col-sm-4 col-md-5 col-lg-5">
                    <div class="form-group"><strong>Fiscal</strong>
                        <select  class="form-control"  id="idFis" name="idFis"
                                maxlength="1" required>                            
                            <c:forEach var="fiscal" items="${fiscales}">
                                <option value="${fiscal.idFis}" >${fiscal.direccion}</option>  
                            </c:forEach>                
                        </select>
                    </div>
                </div>
                <div class="col-sm-4 col-md-5  col-lg-4">
                    <div class="form-group"><strong>Télefono</strong>
                        <input class="form-control"  type="number" placeholder="télefono"   id="telefono" name="telefono" value="${cliente.tel}" 
                                maxlength="10" required>
                    </div>
                </div>
                               
                <div class="col-sm-4 col-md-5  col-lg-4">
                    <div class="form-group"><strong>Dirección</strong>
                        <input class="form-control"  type="text" id="direccionCliente" name="direccionCliente" value="${cliente.direccion}" 
                               placeholder="Dirección" maxlength="10" required>
                    </div>
                </div>
                
        <div class="col-sm-12">
            <div class="form-group text-center">           
                <a href="http://localhost:30533/Maar/clientes"
                   class="btn btn-danger">
                    <i class="fa fa-times"> </i>
                    Cancelar
                </a>
                <button  onclick="registrarCliente()"
                      class="btn btn-primary">
                    <span class="glyphicon glyphicon-ok"></span>
                    Registrar
                </button>
            </div>
        </div>
    </form>                     
</div>


