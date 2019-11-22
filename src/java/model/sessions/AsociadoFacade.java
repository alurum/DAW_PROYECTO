/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model.sessions;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.NonUniqueResultException;
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
        Query query = em.createNativeQuery("select * from asociado where id_Aso like ?", Asociado.class);
        query.setParameter(1, id_Aso);
        return (Asociado) query.getSingleResult();
    }

    public Asociado findByUsuario(String usuario) {
        Query query = em.createNativeQuery("select * from asociado where usuario like ?", Asociado.class);
        query.setParameter(1, usuario);
        return (Asociado) query.getSingleResult();
    }

    public Asociado findDuplicate(String usuario) {
        Asociado asc = new Asociado();
        Query query = em.createNativeQuery("select * from asociado where usuario like ?", Asociado.class);
        query.setParameter(1, usuario);        
        try {
           return (Asociado) query.getSingleResult();
        } catch (NoResultException nre) {
            asc.setUsuario("disponible");
            return asc;                                    
        }                         
    }

    public Asociado Insert(Asociado usuario) {
        Query query = em.createNativeQuery("Insert into asociado values (?,?,?,?,?,?,?,?)", Asociado.class);
        query.setParameter(1, usuario.getIdAso());
        query.setParameter(2, usuario.getNombre());
        query.setParameter(3, usuario.getSalario());
        query.setParameter(4, usuario.getCelular());
        query.setParameter(5, usuario.getDireccion());
        query.setParameter(6, usuario.getUsuario());
        query.setParameter(7, usuario.getContraseña());
        query.setParameter(8, usuario.getIdRol());
        System.out.println((Asociado) query.getSingleResult());
        return (Asociado) query.getSingleResult();

    }

}
