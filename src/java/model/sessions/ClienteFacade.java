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
import model.entities.Cliente;


/**
 *
 * @author Lalo
 */
@Stateless
public class ClienteFacade extends AbstractFacade<Cliente> {

    @PersistenceContext(unitName = "MaarPU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public ClienteFacade() {
        super(Cliente.class);
    }

    public Cliente findDuplicate(String direccion) {
        Cliente cliente = new Cliente();
        Query query = em.createNativeQuery("select * from cliente where direccion = ?", Cliente.class);
        query.setParameter(1, direccion);
        try {
            return (Cliente) query.getSingleResult();
        } catch (NoResultException nre) {
            cliente.setDireccion("disponible");
            return cliente;
        }
    }

    public Cliente findDuplicateUpdate(String direccionNueva, String direccionActual) {
        Cliente cliente = new Cliente();
        Query query = em.createNativeQuery("select * from cliente where direccion = ? and direccion <> ?", Cliente.class);
        query.setParameter(1, direccionNueva);
        query.setParameter(2, direccionActual);
        try {
            return (Cliente) query.getSingleResult();
        } catch (NoResultException nre) {
            cliente.setDireccion("disponible");
            return cliente;
        }
    }

    
    public Cliente findFiscal(Integer idFis) {
    Cliente cliente = new Cliente();
        Query query = em.createNativeQuery("select * from cliente where id_Fis = ? LIMIT 1", Cliente.class);
        query.setParameter(1, idFis);                
        try {
           return (Cliente) query.getSingleResult();
        } catch (NoResultException nre) {
            cliente.setNombre("disponible");
            return cliente;                                    
        }                         
    }
    
    
    public void Insert(Cliente cliente) {
        Cliente persistance = new Cliente();
        em.persist(persistance);
        persistance.setIdClien(cliente.getIdClien());
        persistance.setNombre(cliente.getNombre());
        persistance.setDireccion(cliente.getDireccion());
        persistance.setTel(cliente.getTel());
        persistance.setIdFis(cliente.getIdFis());
    }
    
    
    public void Update(Cliente cliente) {                
        em.merge(cliente);        
    }
       

}
