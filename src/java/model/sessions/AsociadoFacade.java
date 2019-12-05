/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model.sessions;


import java.util.List;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import model.entities.Asociado;

/**
 *
 * @author Lalo
 */
@Stateless
public class AsociadoFacade extends AbstractFacade<Asociado> {

    @PersistenceContext(unitName = "MaarPU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public AsociadoFacade() {
        super(Asociado.class);
    }

    public Asociado find(String usuario) {
        Query query = em.createQuery("select u from Usuario u where u.usuario = :usuario");
        query.setParameter("usuario", usuario);
        return (Asociado) query.getSingleResult();
    }

    public Asociado findByid_Aso(Integer id_Aso) {
        Query query = em.createNativeQuery("select * from asociado where id_Aso = ?", Asociado.class);
        query.setParameter(1, id_Aso);
        return (Asociado) query.getSingleResult();
    }

    public Asociado findByUsuario(String usuario) {
        Query query = em.createNativeQuery("select * from asociado where usuario = ?", Asociado.class);
        query.setParameter(1, usuario);
        return (Asociado) query.getSingleResult();
    }
    
    
      public List<Asociado> findAllExcept(String usuario) {
        Query query = em.createNativeQuery("select * from asociado where usuario <> ?", Asociado.class);
        query.setParameter(1, usuario);
        return (List<Asociado>)  query.getResultList();
    }

    public Asociado findDuplicate(String usuario) {
        Asociado asc = new Asociado();
        Query query = em.createNativeQuery("select * from asociado where usuario = ?", Asociado.class);
        query.setParameter(1, usuario);        
        try {
           return (Asociado) query.getSingleResult();
        } catch (NoResultException nre) {
            asc.setUsuario("disponible");
            return asc;                                    
        }                         
    }
        
    
    public Asociado findDuplicateUpdate(String usuarioNuevo, String usuarioActual ) {
        Asociado asc = new Asociado();
        Query query = em.createNativeQuery("select * from asociado where usuario = ? and usuario <> ?", Asociado.class);
        query.setParameter(1, usuarioNuevo);        
        query.setParameter(2, usuarioActual);        
        try {
           return (Asociado) query.getSingleResult();
        } catch (NoResultException nre) {
            asc.setUsuario("disponible");
            return asc;                                    
        }                         
    }
    
    
    
    public Asociado findRol(Integer idRol ) {
        Asociado asc = new Asociado();
        Query query = em.createNativeQuery("select * from asociado where id_Rol = ? LIMIT 1", Asociado.class);
        query.setParameter(1, idRol);                
        try {
           return (Asociado) query.getSingleResult();
        } catch (NoResultException nre) {
            asc.setUsuario("disponible");
            return asc;                                    
        }                         
    }
        
    public void Insert(Asociado asociado) {
        Asociado persistance = new Asociado();
        em.persist(persistance);
        persistance.setIdAso(asociado.getIdAso());
        persistance.setNombre(asociado.getNombre());
        persistance.setSalario(asociado.getSalario());
        persistance.setCelular(asociado.getCelular());
        persistance.setDireccion(asociado.getDireccion());
        persistance.setUsuario(asociado.getUsuario());
        persistance.setContraseña(asociado.getContraseña());
        persistance.setIdRol(asociado.getIdRol());
    }
       
    public void Update(Asociado asociado) {                
        em.merge(asociado);        
    }

}