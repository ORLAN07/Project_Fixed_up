/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.dao;

import java.util.List;
import javax.ejb.Local;
import org.entidades.Notificacion;
import org.entidades.Persona;

/**
 *
 * @author David
 */
@Local
public interface NotificacionFacadeLocal {

    void create(Notificacion notificacion);

    void edit(Notificacion notificacion);

    void remove(Notificacion notificacion);

    Notificacion find(Object id);

    List<Notificacion> findAll();

    List<Notificacion> findRange(int[] range);

    int count();
    
    List<Notificacion>notificacionesUsuario(Persona persona);
    
    List<Notificacion>notificacionesUsuarioVista(Persona persona);
    
    void mensajesLeidos(Persona persona);
    
}
