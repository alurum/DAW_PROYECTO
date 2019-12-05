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
import model.entities.Fiscal;

/**
 *
 * @author Lalo
 */
@Stateless
public class FiscalFacade extends AbstractFacade<Fiscal> {

    @PersistenceContext(unitName = "MaarPU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public FiscalFacade() {
        super(Fiscal.class);
    }
    
    
     public Fiscal findDuplicate(String direccion) {
        Fiscal fiscal = new Fiscal();
        Query query = em.createNativeQuery("select * from fiscal where direccion = ?", Fiscal.class);
        query.setParameter(1, direccion);                
        try {
           return (Fiscal) query.getSingleResult();
        } catch (NoResultException nre) {
            fiscal.setDireccion("disponible");
            return fiscal;                                    
        }                         
    }
    
    
    public Fiscal findDuplicateUpdate(String direccionNueva, String direccionActual) {
        Fiscal fiscal = new Fiscal();
        Query query = em.createNativeQuery("select * from fiscal where direccion = ? and direccion <> ?", Fiscal.class);
        query.setParameter(1, direccionNueva);        
        query.setParameter(2, direccionActual);                
        try {
           return (Fiscal) query.getSingleResult();
        } catch (NoResultException nre) {
            fiscal.setDireccion("disponible");
            return fiscal;                                    
        }                         
    }
    
    
    public void Insert(Fiscal fiscal) {
        Fiscal persistance = new Fiscal();
        em.persist(persistance);
        persistance.setIdFis(fiscal.getIdFis());
        persistance.setNombre(fiscal.getNombre());
        persistance.setDireccion(fiscal.getDireccion());
        persistance.setUsoCFCI(fiscal.getUsoCFCI());
        persistance.setConPago(fiscal.getConPago());
        persistance.setFormaDePago(fiscal.getFormaDePago());
    }
    
    
    public void Update(Fiscal fiscal) {                
        em.merge(fiscal);        
    }
    
}
