/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.dao;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import org.entidades.Dificultad;

/**
 *
 * @author David
 */
@Stateless
public class DificultadFacade extends AbstractFacade<Dificultad> implements DificultadFacadeLocal {

    @PersistenceContext(unitName = "fixed_up_prototipo_v2PU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public DificultadFacade() {
        super(Dificultad.class);
    }
    
}
