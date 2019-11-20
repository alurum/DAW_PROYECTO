/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model.entities;

import java.io.Serializable;
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
import javax.persistence.Table;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author Lalo
 */
@Entity
@Table(name = "sucursal")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Sucursal.findAll", query = "SELECT s FROM Sucursal s"),
    @NamedQuery(name = "Sucursal.findByIdPed", query = "SELECT s FROM Sucursal s WHERE s.idPed = :idPed"),
    @NamedQuery(name = "Sucursal.findByNombre", query = "SELECT s FROM Sucursal s WHERE s.nombre = :nombre"),
    @NamedQuery(name = "Sucursal.findByDireccion", query = "SELECT s FROM Sucursal s WHERE s.direccion = :direccion"),
    @NamedQuery(name = "Sucursal.findByNotienda", query = "SELECT s FROM Sucursal s WHERE s.notienda = :notienda"),
    @NamedQuery(name = "Sucursal.findByStatus", query = "SELECT s FROM Sucursal s WHERE s.status = :status")})
public class Sucursal implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id_Ped")
    private Integer idPed;
    @Size(max = 40)
    @Column(name = "nombre")
    private String nombre;
    @Size(max = 40)
    @Column(name = "direccion")
    private String direccion;
    @Size(max = 40)
    @Column(name = "No_tienda")
    private String notienda;
    @Column(name = "status")
    private Boolean status;
    @JoinColumn(name = "id_Clien", referencedColumnName = "id_Clien")
    @ManyToOne
    private Cliente idClien;

    public Sucursal() {
    }

    public Sucursal(Integer idPed) {
        this.idPed = idPed;
    }

    public Integer getIdPed() {
        return idPed;
    }

    public void setIdPed(Integer idPed) {
        this.idPed = idPed;
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

    public Boolean getStatus() {
        return status;
    }

    public void setStatus(Boolean status) {
        this.status = status;
    }

    public Cliente getIdClien() {
        return idClien;
    }

    public void setIdClien(Cliente idClien) {
        this.idClien = idClien;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idPed != null ? idPed.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Sucursal)) {
            return false;
        }
        Sucursal other = (Sucursal) object;
        if ((this.idPed == null && other.idPed != null) || (this.idPed != null && !this.idPed.equals(other.idPed))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "model.entities.Sucursal[ idPed=" + idPed + " ]";
    }
    
}
