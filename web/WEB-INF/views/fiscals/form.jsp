<%-- 
    Document   : usuarios
    Created on : 11/10/2019, 05:18:26 PM
    Author     : Lalo
--%>


<div class="col-sm-12 col-md-12 col-lg-12" align="center">
    <h1><i class="fa fa-plus"></i>  ${titulo}</h1>              
    <form method="POST" id="formFiscal" class="row" action=${action}>       
        <input name="idFisSource" id="idFisSource" type="hidden" value="${fiscal.idFis}">
        <br><br>
        <div class="col-sm-4 col-md-5 col-lg-5"> 		 
            <div class="form-group"><strong>Nombre de fiscal</strong>
                <input class = "form-control"  type="text" id="nombreFiscal" name="nombreFiscal" value="${fiscal.nombre}"
                       placeholder="Nombre" maxlength="20" required>
            </div>
        </div>                
        <div class="col-sm-4 col-md-5  col-lg-5">
            <div class="form-group"><strong>Dirección</strong>
                <input class="form-control"  type="text" id="direccionFiscal" name="direccionFiscal" value="${fiscal.direccion}" 
                       placeholder="Dirección" maxlength="20" required>
            </div>
        </div>
        <div class="col-sm-4 col-md-4  col-lg-4">
            <div class="form-group"><strong>CFCI</strong>
                <input class="form-control"  type="text" placeholder="CFCI"   id="usoCFCI" name="usoCFCI" value="${fiscal.usoCFCI}" 
                       maxlength="10" required>
            </div>
        </div>
        <div class="col-sm-4 col-md-4  col-lg-4">
            <div class="form-group"><strong>Pago</strong>
                <input class="form-control"  type="number" placeholder="Pago"   id="conPago" name="conPago" value="${fiscal.conPago}" 
                       maxlength="10" required>
            </div>
        </div>
        <div class="col-sm-4 col-md-4  col-lg-4">
            <div class="form-group"><strong>Forma de pago</strong>
                <input class="form-control"  type="text" placeholder="Forma de pago"   id="formaDePago" name="formaDePago" value="${fiscal.formaDePago}" 
                       maxlength="10" required>
            </div>
        </div>



        <div class="col-sm-12">
            <div class="form-group text-center">           
                <a href="http://localhost:30533/Maar/fiscales"
                   class="btn btn-danger">
                    <i class="fa fa-times"> </i>
                    Cancelar
                </a>
                <button  onclick="registrarFiscal()"
                      class="btn btn-primary">
                    <span class="glyphicon glyphicon-ok"></span>
                    Registrar
                </button>
            </div>
        </div>
    </form>                     
</div>


