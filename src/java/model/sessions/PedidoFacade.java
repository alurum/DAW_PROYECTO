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
import model.entities.Pedido;

/**
 *
 * @author Lalo
 */
@Stateless
public class PedidoFacade extends AbstractFacade<Pedido> {

    @PersistenceContext(unitName = "MaarPU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public PedidoFacade() {
        super(Pedido.class);
    }

    public Pedido findSucursal(Integer idSuc) {
    Pedido pedido = new Pedido();
        Query query = em.createNativeQuery("select * from pedido where id_suc = ? LIMIT 1;", Pedido.class);
        query.setParameter(1, idSuc);                
        try {
           return (Pedido) query.getSingleResult();
        } catch (NoResultException nre) {
            pedido.setComentario("disponible");
            return pedido;                                    
        }                         
    }
    
        
}
