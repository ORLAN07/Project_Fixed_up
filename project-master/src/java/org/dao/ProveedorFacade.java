/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.dao;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import org.entidades.Proveedor;

/**
 *
 * @author David
 */
@Stateless
public class ProveedorFacade extends AbstractFacade<Proveedor> implements ProveedorFacadeLocal {

    @PersistenceContext(unitName = "fixed_up_prototipo_v2PU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public ProveedorFacade() {
        super(Proveedor.class);
    }

    @Override
    public Boolean loadProveedores(String pathFile) {
         try {
            String sql = "LOAD DATA LOCAL INFILE '" + pathFile + "' "
                    + "INTO TABLE ciudades "
                    + "FIELDS TERMINATED BY ';' "
                    + "LINES TERMINATED BY '\n' "
                    + "(id_ciudad, nombre_ciudad);";
            Query nq = getEntityManager().createNativeQuery(sql);
            nq.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
        return true;    }
    
}

