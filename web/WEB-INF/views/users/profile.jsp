<%-- 
    Document   : usuarios
    Created on : 11/10/2019, 05:18:26 PM
    Author     : Lalo
--%>


        <div class="col-sm-12 col-md-12 col-lg-12" >
            <h1><i class="fa fa-wrench"></i>  ${titulo}</h1>                      
            <form method="POST" id="form" class="row">                       
                <br><br>
                <input type="hidden" type="text" id="SSidAso" name="SSidAso" value="${SSidAso}">
                <h5><strong>Información personal</h5></strong>                
                <div class="col-sm-1 col-md-1 col-lg-8"></div>
                <div class="col-sm-4 col-md-5 col-lg-5"> 		 
                    <div class="form-group"><strong>Nombre de usuario</strong>
                        <input class = "form-control"  type="text" id="SSusuario" name="SSusuario" value="${SSusuario}"
                               placeholder="usuario" maxlength="20" required>
                    </div>
                </div>                
                <div class="col-sm-4 col-md-5  col-lg-4">
                    <div class="form-group"><strong>Contraseña</strong>
                        <input class="form-control"  type="password" id="SSpassword" name="SSpassword" value="${SScontraseña}" 
                               placeholder="Contraseña" maxlength="10" required>
                    </div>
                </div>
                <div class="col-sm-4 col-md-5  col-lg-4">
                    <div class="form-group"><strong>Repita contraseña</strong>
                        <input class="form-control"  type="password" id="SSRpassword" name="SSRpassword" value="${SScontraseña}" 
                               placeholder="Repita contraseña" maxlength="10" required>
                    </div>
                </div>
                <div class="col-sm-12">
                    <div class="form-group text-center">           
                        <a href="http://localhost:30533/Maar/usuarios"
                           class="btn btn-danger">
                            <i class="fa fa-times"> </i>
                            Cancelar
                        </a>
                        <button type="submit" id="profile"
                                class="btn btn-primary">
                            <span class="glyphicon glyphicon-ok"></span>
                            Registrar
                        </button>
                    </div>
                </div>
            </form>                     

        </div>

