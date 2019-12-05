/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model.sessions;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import model.entities.Venta;

/**
 *
 * @author Lalo
 */
@Stateless
public class VentaFacade extends AbstractFacade<Venta> {

    @PersistenceContext(unitName = "MaarPU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public VentaFacade() {
        super(Venta.class);
    }
    
    
    
     public Venta findPedido(Integer idPed) {
    Venta venta = new Venta();
        Query query = em.createNativeQuery("select * from venta where id_Ped = ? LIMIT 1", Venta.class);
        query.setParameter(1, idPed);                
        try {
           return (Venta) query.getSingleResult();
        } catch (NoResultException nre) {
            venta.setModoPago("disponible");
            return venta;                                    
        }                         
    }
    
     
     
     
     
     public void Insert(Venta venta) {
        Venta persistance = new Venta();
        em.persist(persistance);
        persistance.setIdVen(venta.getIdVen());
        persistance.setDevolucione(venta.getDevolucione());
        persistance.setFecha(venta.getFecha());
        persistance.setModoPago(venta.getModoPago());
        persistance.setIdClien(venta.getIdClien());
        persistance.setIdAso(venta.getIdAso());
        persistance.setIdPed(venta.getIdPed());
    }   
    
    public void Update(Venta venta) {                
        em.merge(venta);        
    }
    
    
    public Venta getId() {        
        Query query = em.createNativeQuery("SELECT * FROM venta ORDER by id_Ven DESC LIMIT 1", Venta.class);        
            return (Venta) query.getSingleResult();        
    }
     
     
}
