<%-- 
    Document   : usuarios
    Created on : 11/10/2019, 05:18:26 PM
    Author     : Lalo
--%>


<div class="col-sm-12 col-md-12 col-lg-12" align="center">
    <h1><i class="fa fa-plus"></i>  ${titulo}</h1>              
    <form method="POST" id="formCategoria" class="row" action=${action}>       
        <input name="idCatSource" id="idCatSource" type="hidden" value="${categoria.idCat}">
        <br><br>
        <h5><strong>Nombre de la categoría</h5></strong>
        <div class="col-sm-4 col-md-4 col-lg-4"></div>
        <div class="col-sm-4 col-md-4 col-lg-4" align="center">
            <div class="form-group">
                <input class = "form-control"  type="text" id="nombreCategoria" name="nombreCategoria" value="${categoria.nombre}"
                        placeholder="Nombre" maxlength="20" required>					  
            </div>
        </div>	   
        <div class="col-sm-4 col-md-4 col-lg-4"></div>
        <div class="col-sm-12">
            <div class="form-group text-center">           
                <a href="http://localhost:30533/Maar/categorias"
                   class="btn btn-danger">
                    <i class="fa fa-times"> </i>
                    Cancelar
                </a>
                <button  onclick="registrarCategoria()"
                    class="btn btn-primary">
                    <span class="glyphicon glyphicon-ok"></span>
                    Registrar
                </button>
            </div>
        </div>
    </form>                     
</div>


