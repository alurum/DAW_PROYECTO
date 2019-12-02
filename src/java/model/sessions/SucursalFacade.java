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
import model.entities.Asociado;
import model.entities.Sucursal;

/**
 *
 * @author Lalo
 */
@Stateless
public class SucursalFacade extends AbstractFacade<Sucursal> {

    @PersistenceContext(unitName = "MaarPU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public SucursalFacade() {
        super(Sucursal.class);
    }
 
    
    public Sucursal findDuplicate(String direccion) {
        Sucursal sucursal = new Sucursal();
        Query query = em.createNativeQuery("select * from sucursal where direccion = ?", Sucursal.class);
        query.setParameter(1, direccion);                
        try {
           return (Sucursal) query.getSingleResult();
        } catch (NoResultException nre) {
            sucursal.setNombre("disponible");
            return sucursal;                                    
        }                         
    }
    
    
    public Sucursal findDuplicateUpdate(String direccionNueva, String direccionActual) {
        Sucursal sucursal = new Sucursal();
        Query query = em.createNativeQuery("select * from sucursal where direccion = ? and direccion <> ?", Sucursal.class);
        query.setParameter(1, direccionNueva);        
        query.setParameter(2, direccionActual);                
        try {
           return (Sucursal) query.getSingleResult();
        } catch (NoResultException nre) {
            sucursal.setDireccion("disponible");
            return sucursal;                                    
        }                         
    }
    
    
}
