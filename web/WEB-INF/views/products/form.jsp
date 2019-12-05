<%-- 
    Document   : usuarios
    Created on : 11/10/2019, 05:18:26 PM
    Author     : Lalo
--%>


<div class="col-sm-12 col-md-12 col-lg-12" align="center">
    <h1><i class="fa fa-plus"></i>  ${titulo}</h1>              
    <form method="POST" id="formProducto" class="row" action=${action}>       
        <input name="idPro" id="idPro" type="hidden" value="${producto.idPro}">
        <br><br>
           <div class="col-sm-4 col-md-5 col-lg-5"> 		 
                    <div class="form-group"><strong>Nombre del producto</strong>
                        <input class = "form-control"  type="text" id="nombreProducto" name="nombreProducto" value="${producto.nombre}"
                               placeholder="Nombre" maxlength="20" required>
                    </div>
                </div>
                <div class="col-sm-4 col-md-5 col-lg-5">
                    <div class="form-group"><strong>Categoria</strong>
                        <select  class="form-control"  id="idCat" name="idCat"
                                maxlength="1" required>                            
                            <c:forEach var="categoria" items="${categorias}">
                                <option value="${categoria.idCat}" >${categoria.nombre}</option>  
                            </c:forEach>                
                        </select>
                    </div>
                </div>
                <div class="col-sm-4 col-md-5  col-lg-4">
                    <div class="form-group"><strong>Precio</strong>
                        <input class="form-control"  type="number" placeholder="0.00" min="0" step="1.00"  id="precio" name="precio" value="${producto.precio}" 
                                maxlength="10" required>
                    </div>
                </div>
                               
                <div class="col-sm-4 col-md-5  col-lg-4">
                    <div class="form-group"><strong>Sabor</strong>
                        <input class="form-control"  type="text" id="sabor" name="sabor" value="${producto.sabor}" 
                               placeholder="Sabor" maxlength="10" required>
                    </div>
                </div>
                
        <div class="col-sm-12">
            <div class="form-group text-center">           
                <a href="http://localhost:30533/Maar/productos"
                   class="btn btn-danger">
                    <i class="fa fa-times"> </i>
                    Cancelar
                </a>
                <button  onclick="registrarProducto()"
                      class="btn btn-primary">
                    <span class="glyphicon glyphicon-ok"></span>
                    Registrar
                </button>
            </div>
        </div>
    </form>                     
</div>


