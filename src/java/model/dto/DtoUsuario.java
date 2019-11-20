package model.dto;

/**
 *
 * @author Jorge Coronel González
 */
public class DtoUsuario {
    
    private int id;
    private String usuario;
    private String password;
    private boolean estatus;
    

    public DtoUsuario() {
    }

    public DtoUsuario(int id, String usuario, String password, boolean estatus) {
        this.id = id;
        this.usuario = usuario;
        this.password = password;
        this.estatus = estatus;
        
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getUsuario() {
        return usuario;
    }

    public void setUsuario(String usuario) {
        this.usuario = usuario;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public boolean isEstatus() {
        return estatus;
    }

    public void setEstatus(boolean estatus) {
        this.estatus = estatus;
    }

    
}