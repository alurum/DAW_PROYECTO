/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model.entities;

import java.io.Serializable;
import java.util.Date;
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
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author Lalo
 */
@Entity
@Table(name = "venta")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Venta.findAll", query = "SELECT v FROM Venta v"),
    @NamedQuery(name = "Venta.findByIdVen", query = "SELECT v FROM Venta v WHERE v.idVen = :idVen"),
    @NamedQuery(name = "Venta.findByDevolucione", query = "SELECT v FROM Venta v WHERE v.devolucione = :devolucione"),
    @NamedQuery(name = "Venta.findByFecha", query = "SELECT v FROM Venta v WHERE v.fecha = :fecha"),
    @NamedQuery(name = "Venta.findByModoPago", query = "SELECT v FROM Venta v WHERE v.modoPago = :modoPago")})
public class Venta implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id_Ven")
    private Integer idVen;
    @Column(name = "devolucione")
    private Integer devolucione;
    @Column(name = "fecha")
    @Temporal(TemporalType.DATE)
    private Date fecha;
    @Size(max = 40)
    @Column(name = "modo_pago")
    private String modoPago;
    @JoinColumn(name = "id_Ped", referencedColumnName = "id_Ped")
    @ManyToOne
    private Pedido idPed;
    @JoinColumn(name = "id_Clien", referencedColumnName = "id_Clien")
    @ManyToOne
    private Cliente idClien;
    @JoinColumn(name = "id_Aso", referencedColumnName = "id_Aso")
    @ManyToOne
    private Asociado idAso;

    public Venta() {
    }

    public Venta(Integer idVen) {
        this.idVen = idVen;
    }

    public Integer getIdVen() {
        return idVen;
    }

    public void setIdVen(Integer idVen) {
        this.idVen = idVen;
    }

    public Integer getDevolucione() {
        return devolucione;
    }

    public void setDevolucione(Integer devolucione) {
        this.devolucione = devolucione;
    }

    public Date getFecha() {
        return fecha;
    }

    public void setFecha(Date fecha) {
        this.fecha = fecha;
    }

    public String getModoPago() {
        return modoPago;
    }

    public void setModoPago(String modoPago) {
        this.modoPago = modoPago;
    }

    public Pedido getIdPed() {
        return idPed;
    }

    public void setIdPed(Pedido idPed) {
        this.idPed = idPed;
    }

    public Cliente getIdClien() {
        return idClien;
    }

    public void setIdClien(Cliente idClien) {
        this.idClien = idClien;
    }

    public Asociado getIdAso() {
        return idAso;
    }

    public void setIdAso(Asociado idAso) {
        this.idAso = idAso;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idVen != null ? idVen.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Venta)) {
            return false;
        }
        Venta other = (Venta) object;
        if ((this.idVen == null && other.idVen != null) || (this.idVen != null && !this.idVen.equals(other.idVen))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "model.entities.Venta[ idVen=" + idVen + " ]";
    }
    
}
