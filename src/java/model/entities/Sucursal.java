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
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
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
@Table(name = "sucursal")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Sucursal.findAll", query = "SELECT s FROM Sucursal s"),
    @NamedQuery(name = "Sucursal.findByIdSuc", query = "SELECT s FROM Sucursal s WHERE s.idSuc = :idSuc"),
    @NamedQuery(name = "Sucursal.findByNombre", query = "SELECT s FROM Sucursal s WHERE s.nombre = :nombre"),
    @NamedQuery(name = "Sucursal.findByDireccion", query = "SELECT s FROM Sucursal s WHERE s.direccion = :direccion"),
    @NamedQuery(name = "Sucursal.findByNotienda", query = "SELECT s FROM Sucursal s WHERE s.notienda = :notienda")})
public class Sucursal implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id_suc")
    private Integer idSuc;
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
    @Column(name = "No_tienda")
    private String notienda;
    @JoinColumn(name = "id_Clien", referencedColumnName = "id_Clien")
    @ManyToOne(optional = false)
    private Cliente idClien;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "idSuc")
    private List<Pedido> pedidoList;

    public Sucursal() {
    }

    public Sucursal(Integer idSuc) {
        this.idSuc = idSuc;
    }

    public Sucursal(Integer idSuc, String nombre, String direccion, String notienda) {
        this.idSuc = idSuc;
        this.nombre = nombre;
        this.direccion = direccion;
        this.notienda = notienda;
    }

    public Integer getIdSuc() {
        return idSuc;
    }

    public void setIdSuc(Integer idSuc) {
        this.idSuc = idSuc;
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

    public String getNotienda() {
        return notienda;
    }

    public void setNotienda(String notienda) {
        this.notienda = notienda;
    }

    public Cliente getIdClien() {
        return idClien;
    }

    public void setIdClien(Cliente idClien) {
        this.idClien = idClien;
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
        hash += (idSuc != null ? idSuc.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Sucursal)) {
            return false;
        }
        Sucursal other = (Sucursal) object;
        if ((this.idSuc == null && other.idSuc != null) || (this.idSuc != null && !this.idSuc.equals(other.idSuc))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "model.entities.Sucursal[ idSuc=" + idSuc + " ]";
    }
    
}
