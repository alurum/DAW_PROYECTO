package model.interfaz;

import java.util.List;
import model.dto.DtoUsuario;

/**
 *
 * @author Jorge Coronel González
 */
public interface IUsuario {
    
    static final String USER = "root";
    static final String PASSWORD = "capi12345";
    static final String URL = "jdbc:mysql://localhost/iti44_03";
    static final String DRIVER = "com.mysql.jdbc.Driver";
    

    
    public DtoUsuario find(String usuario) throws Exception;    
    
    
}