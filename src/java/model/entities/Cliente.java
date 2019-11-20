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
@Table(name = "cliente")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Cliente.findAll", query = "SELECT c FROM Cliente c"),
    @NamedQuery(name = "Cliente.findByIdClien", query = "SELECT c FROM Cliente c WHERE c.idClien = :idClien"),
    @NamedQuery(name = "Cliente.findByNombre", query = "SELECT c FROM Cliente c WHERE c.nombre = :nombre"),
    @NamedQuery(name = "Cliente.findByDireccion", query = "SELECT c FROM Cliente c WHERE c.direccion = :direccion"),
    @NamedQuery(name = "Cliente.findByTel", query = "SELECT c FROM Cliente c WHERE c.tel = :tel"),
    @NamedQuery(name = "Cliente.findByStatus", query = "SELECT c FROM Cliente c WHERE c.status = :status")})
public class Cliente implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id_Clien")
    private Integer idClien;
    @Size(max = 40)
    @Column(name = "nombre")
    private String nombre;
    @Size(max = 40)
    @Column(name = "direccion")
    private String direccion;
    @Column(name = "tel")
    private Integer tel;
    @Column(name = "status")
    private Boolean status;
    @JoinColumn(name = "id_Fis", referencedColumnName = "id_Fis")
    @ManyToOne
    private Fiscal idFis;
    @OneToMany(mappedBy = "idClien")
    private List<Venta> ventaList;
    @OneToMany(mappedBy = "idClien")
    private List<Sucursal> sucursalList;
    @OneToMany(mappedBy = "idClien")
    private List<Pedido> pedidoList;

    public Cliente() {
    }

    public Cliente(Integer idClien) {
        this.idClien = idClien;
    }

    public Integer getIdClien() {
        return idClien;
    }

    public void setIdClien(Integer idClien) {
        this.idClien = idClien;
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

    public Integer getTel() {
        return tel;
    }

    public void setTel(Integer tel) {
        this.tel = tel;
    }

    public Boolean getStatus() {
        return status;
    }

    public void setStatus(Boolean status) {
        this.status = status;
    }

    public Fiscal getIdFis() {
        return idFis;
    }

    public void setIdFis(Fiscal idFis) {
        this.idFis = idFis;
    }

    @XmlTransient
    public List<Venta> getVentaList() {
        return ventaList;
    }

    public void setVentaList(List<Venta> ventaList) {
        this.ventaList = ventaList;
    }

    @XmlTransient
    public List<Sucursal> getSucursalList() {
        return sucursalList;
    }

    public void setSucursalList(List<Sucursal> sucursalList) {
        this.sucursalList = sucursalList;
    }

    @XmlTransient
    public List<Pedido> getPedidoList() {
        return pedidoList;
    }

    public void setPedidoList(List<Pedido> pedidoList) {
        this.pedidoList = pedidoList;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idClien != null ? idClien.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Cliente)) {
            return false;
        }
        Cliente other = (Cliente) object;
        if ((this.idClien == null && other.idClien != null) || (this.idClien != null && !this.idClien.equals(other.idClien))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "model.entities.Cliente[ idClien=" + idClien + " ]";
    }
    
}
