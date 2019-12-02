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
import javax.validation.constraints.NotNull;
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
    @Basic(optional = false)
    @NotNull
    @Column(name = "carga_e")
    private int cargaE;
    @Basic(optional = false)
    @NotNull
    @Column(name = "carga_ce")
    private int cargaCe;
    @Basic(optional = false)
    @NotNull
    @Column(name = "carga_g")
    private int cargaG;
    @Basic(optional = false)
    @NotNull
    @Column(name = "carga_cg")
    private int cargaCg;
    @Basic(optional = false)
    @NotNull
    @Column(name = "ticket_e")
    private int ticketE;
    @Basic(optional = false)
    @NotNull
    @Column(name = "ticket_g")
    private int ticketG;
    @Basic(optional = false)
    @NotNull
    @Column(name = "devo_de")
    private int devoDe;
    @Basic(optional = false)
    @NotNull
    @Column(name = "devo_dg")
    private int devoDg;
    @Basic(optional = false)
    @NotNull
    @Column(name = "tra_e")
    private int traE;
    @Basic(optional = false)
    @NotNull
    @Column(name = "tra_g")
    private int traG;
    @Basic(optional = false)
    @NotNull
    @Column(name = "total_p")
    private int totalP;
    @Basic(optional = false)
    @NotNull
    @Column(name = "gas")
    private int gas;
    @Basic(optional = false)
    @NotNull
    @Column(name = "comida")
    private int comida;
    @Basic(optional = false)
    @NotNull
    @Column(name = "otros")
    private int otros;
    @Basic(optional = false)
    @NotNull
    @Column(name = "total_pp")
    private int totalPp;
    @JoinColumn(name = "id_Aso", referencedColumnName = "id_Aso")
    @ManyToOne(optional = false)
    private Asociado idAso;

    public Cuenta() {
    }

    public Cuenta(Integer idCue) {
        this.idCue = idCue;
    }

    public Cuenta(Integer idCue, int cargaE, int cargaCe, int cargaG, int cargaCg, int ticketE, int ticketG, int devoDe, int devoDg, int traE, int traG, int totalP, int gas, int comida, int otros, int totalPp) {
        this.idCue = idCue;
        this.cargaE = cargaE;
        this.cargaCe = cargaCe;
        this.cargaG = cargaG;
        this.cargaCg = cargaCg;
        this.ticketE = ticketE;
        this.ticketG = ticketG;
        this.devoDe = devoDe;
        this.devoDg = devoDg;
        this.traE = traE;
        this.traG = traG;
        this.totalP = totalP;
        this.gas = gas;
        this.comida = comida;
        this.otros = otros;
        this.totalPp = totalPp;
    }

    public Integer getIdCue() {
        return idCue;
    }

    public void setIdCue(Integer idCue) {
        this.idCue = idCue;
    }

    public int getCargaE() {
        return cargaE;
    }

    public void setCargaE(int cargaE) {
        this.cargaE = cargaE;
    }

    public int getCargaCe() {
        return cargaCe;
    }

    public void setCargaCe(int cargaCe) {
        this.cargaCe = cargaCe;
    }

    public int getCargaG() {
        return cargaG;
    }

    public void setCargaG(int cargaG) {
        this.cargaG = cargaG;
    }

    public int getCargaCg() {
        return cargaCg;
    }

    public void setCargaCg(int cargaCg) {
        this.cargaCg = cargaCg;
    }

    public int getTicketE() {
        return ticketE;
    }

    public void setTicketE(int ticketE) {
        this.ticketE = ticketE;
    }

    public int getTicketG() {
        return ticketG;
    }

    public void setTicketG(int ticketG) {
        this.ticketG = ticketG;
    }

    public int getDevoDe() {
        return devoDe;
    }

    public void setDevoDe(int devoDe) {
        this.devoDe = devoDe;
    }

    public int getDevoDg() {
        return devoDg;
    }

    public void setDevoDg(int devoDg) {
        this.devoDg = devoDg;
    }

    public int getTraE() {
        return traE;
    }

    public void setTraE(int traE) {
        this.traE = traE;
    }

    public int getTraG() {
        return traG;
    }

    public void setTraG(int traG) {
        this.traG = traG;
    }

    public int getTotalP() {
        return totalP;
    }

    public void setTotalP(int totalP) {
        this.totalP = totalP;
    }

    public int getGas() {
        return gas;
    }

    public void setGas(int gas) {
        this.gas = gas;
    }

    public int getComida() {
        return comida;
    }

    public void setComida(int comida) {
        this.comida = comida;
    }

    public int getOtros() {
        return otros;
    }

    public void setOtros(int otros) {
        this.otros = otros;
    }

    public int getTotalPp() {
        return totalPp;
    }

    public void setTotalPp(int totalPp) {
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
