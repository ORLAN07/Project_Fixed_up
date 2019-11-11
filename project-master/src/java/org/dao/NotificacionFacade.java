/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.dao;

import java.util.ArrayList;
import java.util.List;
import javax.ejb.EJB;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import org.entidades.Notificacion;
import org.entidades.Persona;

/**
 *
 * @author David
 */
@Stateless
public class NotificacionFacade extends AbstractFacade<Notificacion> implements NotificacionFacadeLocal {

    @PersistenceContext(unitName = "fixed_up_prototipo_v2PU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public NotificacionFacade() {
        super(Notificacion.class);
    }

    @Override
    public List<Notificacion> notificacionesUsuario(Persona persona) {
        List<Notificacion>listaNotificaciones = new ArrayList<>();
        try {
            TypedQuery query = getEntityManager().createNamedQuery("Notificacion.findNotificacionesUsuarioValidado", Notificacion.class);
            query.setParameter("personasIdPersona", persona);
            listaNotificaciones = query.getResultList();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return listaNotificaciones;
    }

    @Override
    public void mensajesLeidos(Persona persona) {
        try {
            TypedQuery query = getEntityManager().createNamedQuery("Notificacion.updateMensajesLeidosPorPersona", Notificacion.class);
            query.setParameter("personasIdPersona", persona);
            query.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public List<Notificacion> notificacionesUsuarioVista(Persona persona) {
        List<Notificacion>listaNotificaciones = new ArrayList<>();
        try {
            TypedQuery query = getEntityManager().createNamedQuery("Notificacion.findNotificacionesUsuarioValidadoVista", Notificacion.class);
            query.setParameter("personasIdPersona", persona);
            listaNotificaciones = query.getResultList();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return listaNotificaciones;    }
    
}
