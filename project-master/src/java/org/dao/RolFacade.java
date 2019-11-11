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
import org.entidades.Rol;

/**
 *
 * @author David
 */
@Stateless
public class RolFacade extends AbstractFacade<Rol> implements RolFacadeLocal {

    @PersistenceContext(unitName = "fixed_up_prototipo_v2PU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public RolFacade() {
        super(Rol.class);
    }

    @Override
    public List<Rol> findRolByUsuarioEnSesion(int idRol) {
        List<Rol>listaRoles = new ArrayList<>();
        try {
            TypedQuery<Rol> q = getEntityManager().createNamedQuery("Rol.findRolByUsuarioEnSesionCrearUsuario", Rol.class);
            q.setParameter("idRol", idRol);
            listaRoles = q.getResultList();
            
        } catch (Exception e) {
            e.printStackTrace();
            
        }
        return listaRoles;
        
    }

    @Override
    public List<Rol> findRolByUsuarioEnSesionListarRoles(int idRol) {
        List<Rol>listaRoles = new ArrayList<>();
        try {
            TypedQuery<Rol> q = getEntityManager().createNamedQuery("Rol.findRolByUsuarioEnSesionListarRoles", Rol.class);
            q.setParameter("idRol", idRol);
            listaRoles = q.getResultList();
            
        } catch (Exception e) {
            e.printStackTrace();
            
        }
        return listaRoles;
        
    }
    
}