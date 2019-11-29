/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model.entities;

import java.io.Serializable;
import java.util.List;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

/**
 *
 * @author Lalo
 */
@Entity
@Table(name = "asociado")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Asociado.findAll", query = "SELECT a FROM Asociado a"),
    @NamedQuery(name = "Asociado.findByIdAso", query = "SELECT a FROM Asociado a WHERE a.idAso = :idAso"),
    @NamedQuery(name = "Asociado.findByNombre", query = "SELECT a FROM Asociado a WHERE a.nombre = :nombre"),
    @NamedQuery(name = "Asociado.findBySalario", query = "SELECT a FROM Asociado a WHERE a.salario = :salario"),
    @NamedQuery(name = "Asociado.findByCelular", query = "SELECT a FROM Asociado a WHERE a.celular = :celular"),
    @NamedQuery(name = "Asociado.findByDireccion", query = "SELECT a FROM Asociado a WHERE a.direccion = :direccion"),
    @NamedQuery(name = "Asociado.findByUsuario", query = "SELECT a FROM Asociado a WHERE a.usuario = :usuario")})
public class Asociado implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id_Aso")
    private Integer idAso;
    @Size(max = 40)
    @Column(name = "nombre")
    private String nombre;
    // @Max(value=?)  @Min(value=?)//if you know range of your decimal fields consider using these annotations to enforce field validation
    @Column(name = "salario")
    private Double salario;
    @Column(name = "celular")
    private Integer celular;
    @Size(max = 40)
    @Column(name = "direccion")
    private String direccion;
    @Size(max = 40)
    @Column(name = "usuario")
    private String usuario;
    @Size(max = 40)
    @Column(name = "contrase\u00f1a")
    private String contraseña;
    @OneToMany(mappedBy = "idAso")
    private List<Venta> ventaList;
    @JoinColumn(name = "id_Rol", referencedColumnName = "id_Rol")
    @ManyToOne
    private Rol idRol;
    @OneToMany(mappedBy = "idAso")
    private List<Cuenta> cuentaList;

    public Asociado() {
    }

    public Asociado(Integer idAso) {
        this.idAso = idAso;
    }

    public Integer getIdAso() {
        return idAso;
    }

    public void setIdAso(Integer idAso) {
        this.idAso = idAso;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public Double getSalario() {
        return salario;
    }

    public void setSalario(Double salario) {
        this.salario = salario;
    }

    public Integer getCelular() {
        return celular;
    }

    public void setCelular(Integer celular) {
        this.celular = celular;
    }

    public String getDireccion() {
        return direccion;
    }

    public void setDireccion(String direccion) {
        this.direccion = direccion;
    }

    public String getUsuario() {
        return usuario;
    }

    public void setUsuario(String usuario) {
        this.usuario = usuario;
    }

    public String getContraseña() {
        return contraseña;
    }

    public void setContraseña(String contraseña) {
        this.contraseña = contraseña;
    }

    @XmlTransient
    public List<Venta> getVentaList() {
        return ventaList;
    }

    public void setVentaList(List<Venta> ventaList) {
        this.ventaList = ventaList;
    }

    public Rol getIdRol() {
        return idRol;
    }

    public void setIdRol(Rol idRol) {
        this.idRol = idRol;
    }

    @XmlTransient
    public List<Cuenta> getCuentaList() {
        return cuentaList;
    }

    public void setCuentaList(List<Cuenta> cuentaList) {
        this.cuentaList = cuentaList;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idAso != null ? idAso.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Asociado)) {
            return false;
        }
        Asociado other = (Asociado) object;
        if ((this.idAso == null && other.idAso != null) || (this.idAso != null && !this.idAso.equals(other.idAso))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "model.entities.Asociado[ idAso=" + idAso + " ]";
    }
    
}
