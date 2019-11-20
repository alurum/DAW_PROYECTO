/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model.business;

import java.util.List;

import model.dao.DaoUsuario;
import model.dto.DtoUsuario;

/**
 *
 * @author Lalo
 */
public class BnsMain {
    
private DaoUsuario daoUsuario;


    public BnsMain() {
        daoUsuario = new DaoUsuario();
    }

        
    public DtoUsuario buscarUsuario(String usuario) throws Exception {    
                    
        DtoUsuario dtoUsuario = null;
        if (usuario == null || usuario.isEmpty()) {
            throw new Exception("Dato no valido");
        } else {
            dtoUsuario = daoUsuario.find(usuario);                    
            if (dtoUsuario == null) {
                throw new Exception("Usuario no encontrado");
            }
        }
        return dtoUsuario;
    }
    
    

    
}




