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
import model.entities.Categoria;


/**
 *
 * @author Lalo
 */
@Stateless
public class CategoriaFacade extends AbstractFacade<Categoria> {

    @PersistenceContext(unitName = "MaarPU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public CategoriaFacade() {
        super(Categoria.class);
    }
    
    
    
    public Categoria findDuplicate(String nombre) {
        Categoria categoria = new Categoria();
        Query query = em.createNativeQuery("select * from categoria where nombre = ?", Categoria.class);
        query.setParameter(1, nombre);        
        try {
           return (Categoria) query.getSingleResult();
        } catch (NoResultException nre) {
            categoria.setNombre("disponible");
            return categoria;                                    
        }                         
    }
    
    
    
       public Categoria findDuplicateUpdate(String nombreNuevo, String nombreActual) {
        Categoria categoria = new Categoria();
        Query query = em.createNativeQuery("select * from categoria where nombre = ? and nombre <> ?", Categoria.class);
        query.setParameter(1, nombreNuevo);        
        query.setParameter(2, nombreActual);        
        try {
           return (Categoria) query.getSingleResult();
        } catch (NoResultException nre) {
            categoria.setNombre("disponible");
            return categoria;                                    
        }                         
    }
            
            
    public void Insert(Categoria categoria) {
        Categoria persistance = new Categoria();
        em.persist(persistance);
        persistance.setIdCat(categoria.getIdCat());
        persistance.setNombre(categoria.getNombre());
    }
    
    
    public void Update(Categoria categoria) {                
        em.merge(categoria);        
    }
    
    
}
