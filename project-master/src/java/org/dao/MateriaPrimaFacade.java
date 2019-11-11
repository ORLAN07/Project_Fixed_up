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
import org.entidades.MateriaPrima;
import org.entidades.Proyecto;

/**
 *
 * @author David
 */
@Stateless
public class MateriaPrimaFacade extends AbstractFacade<MateriaPrima> implements MateriaPrimaFacadeLocal {

    @PersistenceContext(unitName = "fixed_up_prototipo_v2PU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public MateriaPrimaFacade() {
        super(MateriaPrima.class);
    }

    @Override
    public List<MateriaPrima> listarPorStockMayor0() {
        List<MateriaPrima> listaMateriaPrima = new ArrayList<>();
        try {
            TypedQuery<MateriaPrima> q = getEntityManager().createNamedQuery("MateriaPrima.findByExistencias", MateriaPrima.class);
            listaMateriaPrima = q.getResultList();
            
        } catch (Exception e) {
            e.printStackTrace();
            
        }
        
        return listaMateriaPrima;

    }
    
}
