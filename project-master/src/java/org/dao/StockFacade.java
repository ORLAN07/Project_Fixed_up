/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.dao;

import java.util.ArrayList;
import java.util.List;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import org.entidades.Stock;

/**
 *
 * @author David
 */
@Stateless
public class StockFacade extends AbstractFacade<Stock> implements StockFacadeLocal {

    @PersistenceContext(unitName = "fixed_up_prototipo_v2PU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public StockFacade() {
        super(Stock.class);
    }

    @Override
    public List<Stock> findAllWithMateriaPrima() {
        
        List<Stock>listaStock = new ArrayList<>();
        try {
            TypedQuery<Stock> q = getEntityManager().createNamedQuery("Stock.findAllWithMateriaPrima", Stock.class);
            listaStock = q.getResultList();
            
        } catch (Exception e) {
            e.printStackTrace();
            
        }
        
        return listaStock;        
    }
    
}
