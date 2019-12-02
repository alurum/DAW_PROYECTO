/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model.entities;

import java.io.Serializable;
import java.util.List;
import javax.persistence.Basic;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

/**
 *
 * @author Lalo
 */
@Entity
@Table(name = "fiscal")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Fiscal.findAll", query = "SELECT f FROM Fiscal f"),
    @NamedQuery(name = "Fiscal.findByIdFis", query = "SELECT f FROM Fiscal f WHERE f.idFis = :idFis"),
    @NamedQuery(name = "Fiscal.findByNombre", query = "SELECT f FROM Fiscal f WHERE f.nombre = :nombre"),
    @NamedQuery(name = "Fiscal.findByDireccion", query = "SELECT f FROM Fiscal f WHERE f.direccion = :direccion"),
    @NamedQuery(name = "Fiscal.findByUsoCFCI", query = "SELECT f FROM Fiscal f WHERE f.usoCFCI = :usoCFCI"),
    @NamedQuery(name = "Fiscal.findByConPago", query = "SELECT f FROM Fiscal f WHERE f.conPago = :conPago"),
    @NamedQuery(name = "Fiscal.findByFormaDePago", query = "SELECT f FROM Fiscal f WHERE f.formaDePago = :formaDePago")})
public class Fiscal implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id_Fis")
    private Integer idFis;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 40)
    @Column(name = "nombre")
    private String nombre;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 40)
    @Column(name = "direccion")
    private String direccion;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 40)
    @Column(name = "uso_CFCI")
    private String usoCFCI;
    @Basic(optional = false)
    @NotNull
    @Column(name = "con_pago")
    private int conPago;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 40)
    @Column(name = "forma_de_pago")
    private String formaDePago;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "idFis")
    private List<Cliente> clienteList;

    public Fiscal() {
    }

    public Fiscal(Integer idFis) {
        this.idFis = idFis;
    }

    public Fiscal(Integer idFis, String nombre, String direccion, String usoCFCI, int conPago, String formaDePago) {
        this.idFis = idFis;
        this.nombre = nombre;
        this.direccion = direccion;
        this.usoCFCI = usoCFCI;
        this.conPago = conPago;
        this.formaDePago = formaDePago;
    }

    public Integer getIdFis() {
        return idFis;
    }

    public void setIdFis(Integer idFis) {
        this.idFis = idFis;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getDireccion() {
        return direccion;
    }

    public void setDireccion(String direccion) {
        this.direccion = direccion;
    }

    public String getUsoCFCI() {
        return usoCFCI;
    }

    public void setUsoCFCI(String usoCFCI) {
        this.usoCFCI = usoCFCI;
    }

    public int getConPago() {
        return conPago;
    }

    public void setConPago(int conPago) {
        this.conPago = conPago;
    }

    public String getFormaDePago() {
        return formaDePago;
    }

    public void setFormaDePago(String formaDePago) {
        this.formaDePago = formaDePago;
    }

    @XmlTransient
    public List<Cliente> getClienteList() {
        return clienteList;
    }

    public void setClienteList(List<Cliente> clienteList) {
        this.clienteList = clienteList;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idFis != null ? idFis.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Fiscal)) {
            return false;
        }
        Fiscal other = (Fiscal) object;
        if ((this.idFis == null && other.idFis != null) || (this.idFis != null && !this.idFis.equals(other.idFis))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "model.entities.Fiscal[ idFis=" + idFis + " ]";
    }
    
}
