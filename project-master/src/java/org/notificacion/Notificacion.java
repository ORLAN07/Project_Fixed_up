/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.notificacion;

import com.controllerEmail.EnviarCorreosMasivos.controller;
import java.io.Serializable;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.enterprise.context.SessionScoped;
import javax.inject.Named;
import org.dao.ProyectoFacadeLocal;
import org.entidades.Proyecto;

/**
 *
 * @author David
 */
@Named(value = "notificaciones")
@SessionScoped
public class Notificacion implements Serializable {

    @EJB
    private ProyectoFacadeLocal proyectoFacadeLocal;
    private List<Proyecto> listaProyecto;
    private controller c;

    @PostConstruct
    public void init() {
        listaProyecto = proyectoFacadeLocal.findAll();
        c = new controller();
    }

    public void enviarNotificacion() {
        System.out.println("essmass");
        controller c = new controller();
        for (Proyecto proyecto : listaProyecto) {
            if (proyecto.getCorreoNotificacionEnviado() == 0) {
                System.out.println("entroooo");
                String mensaje = "Notificacion: el proyecto " + proyecto.getPedidosIdPedido().getNombreProyecto() + " ha finalizado.";
                //c.enviarEmailCliente("edzamora1@misena.edu.co", "Notificacion retraso de proyecto " + "Notificacion retraso de proyecto: ", mensaje);
                //c.enviarEmailCliente(proyecto.getPedidosIdPedido().getVendedorIdPersona().getEmail(), "Notificacion retraso de proyecto: ", mensaje);

            }

        }

    }

    public void prueba() {

        c.enviarEmailCliente("edzamora1@misena.edu.co", "Notificacion retraso de proyecto ", "sldjsaldshl");

    }

}
