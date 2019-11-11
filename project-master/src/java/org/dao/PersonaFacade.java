/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.dao;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import javax.ejb.Stateless;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import javax.persistence.TypedQuery;
import org.entidades.Persona;
import org.entidades.Rol;

/**
 *
 * @author David
 */
@Stateless
public class PersonaFacade extends AbstractFacade<Persona> implements PersonaFacadeLocal {

    @PersistenceContext(unitName = "fixed_up_prototipo_v2PU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public PersonaFacade() {
        super(Persona.class);
    }

    @Override
    public Persona iniciarSesion(int documento, String password) {
        Persona u = null;
        FacesMessage fm;
        FacesContext fc;

        try {
            TypedQuery<Persona> q = getEntityManager().createNamedQuery("Persona.login", Persona.class);
            q.setParameter("documento", documento);
            q.setParameter("password", password);
            u = q.getSingleResult();
        } catch (Exception e) {
            e.printStackTrace();
        }

        return u;
    }

    @Override
    public Persona recuperacontrasena(String email) {
        Persona p = null;
        try {
            TypedQuery<Persona> q = getEntityManager().createNamedQuery("enviar.email", Persona.class);
            q.setParameter("email", email);
            p = q.getSingleResult();
        } catch (Exception e) {
            e.printStackTrace();
        }

        return p;
    }

    @Override
    public List<Persona> filtroMultiCriterio(Map<String, Object> usuariosFiltro) {
        List<Persona> Persona = new ArrayList<>();
        String sql = "SELECT u FROM Persona u WHERE ";
        try {
            Iterator<String> it = usuariosFiltro.keySet().iterator();
            while (it.hasNext()) {
                String key = it.next();
                sql += key + " =: " + key + (it.hasNext() ? " AND " : "");
            }
            TypedQuery<Persona> q = (TypedQuery<Persona>) getEntityManager().createQuery(sql);
            it = usuariosFiltro.keySet().iterator();

            while (it.hasNext()) {
                String key = it.next();
                sql += key + " =: " + key + (it.hasNext() ? " AND " : "");

            }

            Persona = q.getResultList();
        } catch (Exception e) {
            e.printStackTrace();

        }
        return Persona;

    }
    
   @Override
    public Boolean loadUsuarios(String pathFile) {
         try {
            String sql = "LOAD DATA LOCAL INFILE '" + pathFile + "' "
                    + "INTO TABLE personas "
                    + "FIELDS TERMINATED BY ';' "
                    + "LINES TERMINATED BY '\n' "
                    + "(documento, nombre, apellido, password, telefono, direccion, email, areas_id_area, estado, fecha_nacimiento, fecha_ingreso   );";
            Query nq = getEntityManager().createNativeQuery(sql);
            nq.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
        return true;
    }

    @Override
    public List<Persona> listarVendedorAdminRoot() {
        List<Persona> listaPersonas = new ArrayList<>();
        Rol rolRoot = new Rol(1);
        Rol rolAdmin = new Rol(2);
        Rol rolVendedor = new Rol(3);
        try {
            TypedQuery<Persona>query = getEntityManager().createNamedQuery("Persona.findVendedoresAdminRoot", Persona.class);
            query.setParameter("rol1", rolRoot);
            query.setParameter("rol2", rolAdmin);
            query.setParameter("rol3", rolVendedor);
            listaPersonas = query.getResultList();
            
        } catch (Exception e) {
            e.printStackTrace();
            
        }
        
        return listaPersonas;
    }

    @Override
    public List<Persona> listarRootAdmin() {
        List<Persona> listaPersonas = new ArrayList<>();
        Rol rolRoot = new Rol(1);
        Rol rolAdmin = new Rol(2);
        try {
            TypedQuery<Persona>query = getEntityManager().createNamedQuery("Persona.findRootAdmin", Persona.class);
            query.setParameter("rol1", rolRoot);
            query.setParameter("rol2", rolAdmin);
            listaPersonas = query.getResultList();
            
        } catch (Exception e) {
            e.printStackTrace();
            
        }
        return listaPersonas;
    }

}
