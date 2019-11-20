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
@Table(name = "pedido")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Pedido.findAll", query = "SELECT p FROM Pedido p"),
    @NamedQuery(name = "Pedido.findByIdPed", query = "SELECT p FROM Pedido p WHERE p.idPed = :idPed"),
    @NamedQuery(name = "Pedido.findByCantidad", query = "SELECT p FROM Pedido p WHERE p.cantidad = :cantidad"),
    @NamedQuery(name = "Pedido.findByDireccion", query = "SELECT p FROM Pedido p WHERE p.direccion = :direccion"),
    @NamedQuery(name = "Pedido.findByComentario", query = "SELECT p FROM Pedido p WHERE p.comentario = :comentario"),
    @NamedQuery(name = "Pedido.findByStatus", query = "SELECT p FROM Pedido p WHERE p.status = :status")})
public class Pedido implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id_Ped")
    private Integer idPed;
    @Column(name = "cantidad")
    private Integer cantidad;
    @Size(max = 40)
    @Column(name = "direccion")
    private String direccion;
    @Size(max = 40)
    @Column(name = "comentario")
    private String comentario;
    @Column(name = "status")
    private Boolean status;
    @JoinColumn(name = "id_Clien", referencedColumnName = "id_Clien")
    @ManyToOne
    private Cliente idClien;
    @JoinColumn(name = "id_Pro", referencedColumnName = "id_Pro")
    @ManyToOne
    private Producto idPro;

    public Pedido() {
    }

    public Pedido(Integer idPed) {
        this.idPed = idPed;
    }

    public Integer getIdPed() {
        return idPed;
    }

    public void setIdPed(Integer idPed) {
        this.idPed = idPed;
    }

    public Integer getCantidad() {
        return cantidad;
    }

    public void setCantidad(Integer cantidad) {
        this.cantidad = cantidad;
    }

    public String getDireccion() {
        return direccion;
    }

    public void setDireccion(String direccion) {
        this.direccion = direccion;
    }

    public String getComentario() {
        return comentario;
    }

    public void setComentario(String comentario) {
        this.comentario = comentario;
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

    public Producto getIdPro() {
        return idPro;
    }

    public void setIdPro(Producto idPro) {
        this.idPro = idPro;
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
        if (!(object instanceof Pedido)) {
            return false;
        }
        Pedido other = (Pedido) object;
        if ((this.idPed == null && other.idPed != null) || (this.idPed != null && !this.idPed.equals(other.idPed))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "model.entities.Pedido[ idPed=" + idPed + " ]";
    }
    
}
