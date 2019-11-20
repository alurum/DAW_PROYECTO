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
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author Lalo
 */
@Entity
@Table(name = "cuenta")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Cuenta.findAll", query = "SELECT c FROM Cuenta c"),
    @NamedQuery(name = "Cuenta.findByIdCue", query = "SELECT c FROM Cuenta c WHERE c.idCue = :idCue"),
    @NamedQuery(name = "Cuenta.findByCargaE", query = "SELECT c FROM Cuenta c WHERE c.cargaE = :cargaE"),
    @NamedQuery(name = "Cuenta.findByCargaCe", query = "SELECT c FROM Cuenta c WHERE c.cargaCe = :cargaCe"),
    @NamedQuery(name = "Cuenta.findByCargaG", query = "SELECT c FROM Cuenta c WHERE c.cargaG = :cargaG"),
    @NamedQuery(name = "Cuenta.findByCargaCg", query = "SELECT c FROM Cuenta c WHERE c.cargaCg = :cargaCg"),
    @NamedQuery(name = "Cuenta.findByTicketE", query = "SELECT c FROM Cuenta c WHERE c.ticketE = :ticketE"),
    @NamedQuery(name = "Cuenta.findByTicketG", query = "SELECT c FROM Cuenta c WHERE c.ticketG = :ticketG"),
    @NamedQuery(name = "Cuenta.findByDevoDe", query = "SELECT c FROM Cuenta c WHERE c.devoDe = :devoDe"),
    @NamedQuery(name = "Cuenta.findByDevoDg", query = "SELECT c FROM Cuenta c WHERE c.devoDg = :devoDg"),
    @NamedQuery(name = "Cuenta.findByTraE", query = "SELECT c FROM Cuenta c WHERE c.traE = :traE"),
    @NamedQuery(name = "Cuenta.findByTraG", query = "SELECT c FROM Cuenta c WHERE c.traG = :traG"),
    @NamedQuery(name = "Cuenta.findByTotalP", query = "SELECT c FROM Cuenta c WHERE c.totalP = :totalP"),
    @NamedQuery(name = "Cuenta.findByGas", query = "SELECT c FROM Cuenta c WHERE c.gas = :gas"),
    @NamedQuery(name = "Cuenta.findByComida", query = "SELECT c FROM Cuenta c WHERE c.comida = :comida"),
    @NamedQuery(name = "Cuenta.findByOtros", query = "SELECT c FROM Cuenta c WHERE c.otros = :otros"),
    @NamedQuery(name = "Cuenta.findByTotalPp", query = "SELECT c FROM Cuenta c WHERE c.totalPp = :totalPp")})
public class Cuenta implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id_Cue")
    private Integer idCue;
    @Column(name = "carga_e")
    private Integer cargaE;
    @Column(name = "carga_ce")
    private Integer cargaCe;
    @Column(name = "carga_g")
    private Integer cargaG;
    @Column(name = "carga_cg")
    private Integer cargaCg;
    @Column(name = "ticket_e")
    private Integer ticketE;
    @Column(name = "ticket_g")
    private Integer ticketG;
    @Column(name = "devo_de")
    private Integer devoDe;
    @Column(name = "devo_dg")
    private Integer devoDg;
    @Column(name = "tra_e")
    private Integer traE;
    @Column(name = "tra_g")
    private Integer traG;
    @Column(name = "total_p")
    private Integer totalP;
    @Column(name = "gas")
    private Integer gas;
    @Column(name = "comida")
    private Integer comida;
    @Column(name = "otros")
    private Integer otros;
    @Column(name = "total_pp")
    private Integer totalPp;
    @JoinColumn(name = "id_Aso", referencedColumnName = "id_Aso")
    @ManyToOne
    private Asociado idAso;

    public Cuenta() {
    }

    public Cuenta(Integer idCue) {
        this.idCue = idCue;
    }

    public Integer getIdCue() {
        return idCue;
    }

    public void setIdCue(Integer idCue) {
        this.idCue = idCue;
    }

    public Integer getCargaE() {
        return cargaE;
    }

    public void setCargaE(Integer cargaE) {
        this.cargaE = cargaE;
    }

    public Integer getCargaCe() {
        return cargaCe;
    }

    public void setCargaCe(Integer cargaCe) {
        this.cargaCe = cargaCe;
    }

    public Integer getCargaG() {
        return cargaG;
    }

    public void setCargaG(Integer cargaG) {
        this.cargaG = cargaG;
    }

    public Integer getCargaCg() {
        return cargaCg;
    }

    public void setCargaCg(Integer cargaCg) {
        this.cargaCg = cargaCg;
    }

    public Integer getTicketE() {
        return ticketE;
    }

    public void setTicketE(Integer ticketE) {
        this.ticketE = ticketE;
    }

    public Integer getTicketG() {
        return ticketG;
    }

    public void setTicketG(Integer ticketG) {
        this.ticketG = ticketG;
    }

    public Integer getDevoDe() {
        return devoDe;
    }

    public void setDevoDe(Integer devoDe) {
        this.devoDe = devoDe;
    }

    public Integer getDevoDg() {
        return devoDg;
    }

    public void setDevoDg(Integer devoDg) {
        this.devoDg = devoDg;
    }

    public Integer getTraE() {
        return traE;
    }

    public void setTraE(Integer traE) {
        this.traE = traE;
    }

    public Integer getTraG() {
        return traG;
    }

    public void setTraG(Integer traG) {
        this.traG = traG;
    }

    public Integer getTotalP() {
        return totalP;
    }

    public void setTotalP(Integer totalP) {
        this.totalP = totalP;
    }

    public Integer getGas() {
        return gas;
    }

    public void setGas(Integer gas) {
        this.gas = gas;
    }

    public Integer getComida() {
        return comida;
    }

    public void setComida(Integer comida) {
        this.comida = comida;
    }

    public Integer getOtros() {
        return otros;
    }

    public void setOtros(Integer otros) {
        this.otros = otros;
    }

    public Integer getTotalPp() {
        return totalPp;
    }

    public void setTotalPp(Integer totalPp) {
        this.totalPp = totalPp;
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
        hash += (idCue != null ? idCue.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Cuenta)) {
            return false;
        }
        Cuenta other = (Cuenta) object;
        if ((this.idCue == null && other.idCue != null) || (this.idCue != null && !this.idCue.equals(other.idCue))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "model.entities.Cuenta[ idCue=" + idCue + " ]";
    }
    
}
