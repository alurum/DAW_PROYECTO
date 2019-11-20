/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;
import model.dto.DtoUsuario;
import model.interfaz.IUsuario;
import static model.interfaz.IUsuario.PASSWORD;
import static model.interfaz.IUsuario.URL;
import static model.interfaz.IUsuario.USER;

/**
 *
 * @author Lalo
 */
public class DaoUsuario implements IUsuario{

    

    @Override
    public DtoUsuario find(String usuario) throws Exception {
        DtoUsuario dto = null;        
        Class.forName(DRIVER);               
        Connection conn = DriverManager.getConnection(URL, USER, PASSWORD  );                     
        String consulta = "select * from usuario where usuario like ?";                
        PreparedStatement pst = conn.prepareStatement(consulta);
        pst.setString(1, usuario);                  
        ResultSet rs = pst.executeQuery();        
        if (rs.next()) {
            dto = new DtoUsuario(
                    rs.getInt("id_usuario"),
                    rs.getString("usuario"),
                    rs.getString("password"),
                    rs.getBoolean("estatus"));            
        }        
        return dto;
        
    }
    
}
