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
import model.entities.Rol;

/**
 *
 * @author Lalo
 */
@Stateless
public class RolFacade extends AbstractFacade<Rol> {

    @PersistenceContext(unitName = "MaarPU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public RolFacade() {
        super(Rol.class);
    }
    
    
    public Rol findDuplicate(String nombre) {
        Rol rol = new Rol();
        Query query = em.createNativeQuery("select * from rol where nombre = ?", Rol.class);
        query.setParameter(1, nombre);        
        try {
           return (Rol) query.getSingleResult();
        } catch (NoResultException nre) {
            rol.setNombre("disponible");
            return rol;                                    
        }                         
    }
    
    
    public Rol findDuplicateUpdate(String nombreNuevo, String nombreActual ) {
        Rol rol = new Rol();
        Query query = em.createNativeQuery("select * from rol where nombre = ? and nombre <> ?", Rol.class);
        query.setParameter(1, nombreNuevo);        
        query.setParameter(2, nombreActual);        
        try {
           return (Rol) query.getSingleResult();
        } catch (NoResultException nre) {
            rol.setNombre("disponible");
            return rol;                                    
        }                         
    }
    
            
    public void Insert(Rol rol) {
        Rol persistance = new Rol();
        em.persist(persistance);
        persistance.setIdRol(rol.getIdRol());
        persistance.setNombre(rol.getNombre());
    }
    
    
    public void Update(Rol rol) {                
        em.merge(rol);        
    }
    
    
    
    
    
}
