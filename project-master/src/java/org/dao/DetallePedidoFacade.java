/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.dao;

import java.util.ArrayList;
import java.util.List;
import javax.ejb.Stateless;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import org.entidades.DetallePedido;
import org.entidades.Pedido;

/**
 *
 * @author David
 */
@Stateless
public class DetallePedidoFacade extends AbstractFacade<DetallePedido> implements DetallePedidoFacadeLocal {

    @PersistenceContext(unitName = "fixed_up_prototipo_v2PU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public DetallePedidoFacade() {
        super(DetallePedido.class);
    }

    
}
