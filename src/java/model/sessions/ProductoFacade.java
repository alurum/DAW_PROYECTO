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
import model.entities.Producto;

/**
 *
 * @author Lalo
 */
@Stateless
public class ProductoFacade extends AbstractFacade<Producto> {

    @PersistenceContext(unitName = "MaarPU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public ProductoFacade() {
        super(Producto.class);
    }
    
    
    
    public Producto findDuplicate(String nombre) {
        Producto producto = new Producto();
        Query query = em.createNativeQuery("select * from producto where nombre = ?", Producto.class);
        query.setParameter(1, nombre);                
        try {
           return (Producto) query.getSingleResult();
        } catch (NoResultException nre) {
            producto.setNombre("disponible");
            return producto;                                    
        }                         
    }
    
    
    public Producto findDuplicateUpdate(String nombreNuevo, String nombreActual) {
        Producto producto = new Producto();
        Query query = em.createNativeQuery("select * from producto where nombre = ? and nombre <> ?", Producto.class);
        query.setParameter(1, nombreNuevo);        
        query.setParameter(2, nombreActual);                
        try {
           return (Producto) query.getSingleResult();
        } catch (NoResultException nre) {
            producto.setNombre("disponible");
            return producto;                                    
        }                         
    }
    
         
    
    public void Insert(Producto producto) {
        Producto persistance = new Producto();
        em.persist(persistance);
        persistance.setIdPro(producto.getIdPro());
        persistance.setNombre(producto.getNombre());
        persistance.setPrecio(producto.getPrecio());
        persistance.setSabor(producto.getSabor());
        persistance.setIdCat(producto.getIdCat());
    }   
    
    public void Update(Producto producto) {                
        em.merge(producto);        
    }
    
    
    
    public Producto findCategoria(Integer idCatSource) {
    Producto producto = new Producto();
        Query query = em.createNativeQuery("select * from producto where id_Cat = ? LIMIT 1", Producto.class);
        query.setParameter(1, idCatSource);                
        try {
           return (Producto) query.getSingleResult();
        } catch (NoResultException nre) {
            producto.setNombre("disponible");
            return producto;                                    
        }                         
    }
    
    
    
}
