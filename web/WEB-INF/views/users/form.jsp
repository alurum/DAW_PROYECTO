<%-- 
    Document   : usuarios
    Created on : 11/10/2019, 05:18:26 PM
    Author     : Lalo
--%>


        <div class="col-sm-12 col-md-12 col-lg-12" >
            <h1><i class="fa fa-plus"></i>  ${titulo}</h1>          
            <input name="action" id="action" type="hidden" value="${action}">
            <form method="POST" id="form" class="row">       
                <input name="idAso" id="idAso" type="hidden" value="${dato.idAso}">
                <br><br>
                <h5><strong>Informaci�n personal</h5></strong>
                <div class="col-sm-3 col-md-3 col-lg-7">
                    <div class="form-group">
                        <input class = "form-control"  type="text" id="nombreUsuario" name="nombreUsuario" value="${dato.nombre}"
                               placeholder="Nombre completo" maxlength="20" required>					  
                    </div>
                </div>	   
                <div class="col-sm-3 col-md-3 col-lg-7">
                    <div class="form-group">
                        <input class = "form-control"  type="text" id="direccionAsociado" name="direccionAsociado" value="${dato.direccion}"
                               placeholder="Direcci�n" maxlength="30"> 

                    </div>
                </div>
                <div class="col-sm-1 col-md-1 col-lg-8"></div>
                <div class="col-sm-4 col-md-4 col-lg-5">
                    <div class="form-group">
                        <input class = "form-control"  type="number" id="celular" name="celular" value="${dato.celular}"
                               placeholder="Celular" maxlength="10" required>
                    </div>
                </div>           
                <div class="col-sm-1 col-md-1 col-lg-8"></div>
                <div class="col-sm-4 col-md-5 col-lg-5"> 		 
                    <div class="form-group"><strong>Nombre de usuario</strong>
                        <input class = "form-control"  type="password" id="usuario" name="usuario" value="${dato.usuario}"
                               placeholder="usuario" maxlength="20" required>
                    </div>
                </div>
                <div class="col-sm-4 col-md-5 col-lg-5">
                    <div class="form-group"><strong>Tipo de usuario</strong>
                        <select  class="form-control"  id="idRol" name="idRol"
                                maxlength="1" required>                            
                            <c:forEach var="rol" items="${roles}">
                                <option value="${rol.idRol}" >${rol.nombre}</option>  
                            </c:forEach>                
                        </select>
                    </div>
                </div>
                <div class="col-sm-4 col-md-5  col-lg-4">
                    <div class="form-group"><strong>Salario</strong>
                        <input class="form-control"  type="number" placeholder="0.00" min="0" step="1.00"  id="salario" name="salario" value="${dato.salario}" 
                                maxlength="10" required>
                    </div>
                </div>
                               
                <div class="col-sm-4 col-md-5  col-lg-4">
                    <div class="form-group"><strong>Contrase�a</strong>
                        <input class="form-control"  type="password" id="password" name="password" value="${dato.contrase�a}" 
                               placeholder="Contrase�a" maxlength="10" required>
                    </div>
                </div>
                
                               
                               
                <div class="col-sm-4 col-md-5  col-lg-4">
                    <div class="form-group"><strong>Repita contrase�a</strong>
                        <input class="form-control"  type="password" id="Rpassword" name="Rpassword" value="${dato.contrase�a}" 
                               placeholder="Repita contrase�a" maxlength="10" required>
                    </div>
                </div>
                               
                
                
                <div class="col-sm-12">
                    <div class="form-group text-center">           
                        <a href="http://localhost:30533/Maar/usuarios"
                           class="btn btn-danger">
                            <i class="fa fa-times"> </i>
                            Cancelar
                        </a>
                        <button  id="registrar"
                                class="btn btn-primary">
                            <span class="glyphicon glyphicon-ok"></span>
                            Registrar
                        </button>
                    </div>
                </div>
            </form>                     

        </div>

