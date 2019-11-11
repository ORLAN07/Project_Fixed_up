/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.modulos.perfil;

import javax.inject.Named;
import javax.enterprise.context.ConversationScoped;
import javax.enterprise.context.Conversation;
import java.io.Serializable;
import javax.ejb.EJB;
import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;
import javax.inject.Inject;
import org.dao.PersonaFacadeLocal;
import org.entidades.Persona;

/**
 *
 * @author David
 */
@Named(value = "listarPerfil")
@ConversationScoped
public class ListarPerfil implements Serializable {

    @EJB
    private PersonaFacadeLocal personaFacadeLocal;
    @Inject
    private Conversation conversacion;
    private Persona personaSeleccionada;

    public ListarPerfil() {
    }

    public Persona getPersonaSeleccionada() {
        return personaSeleccionada;
    }

    public void setPersonaSeleccionada(Persona personaSeleccionada) {
        this.personaSeleccionada = personaSeleccionada;
    }

    private void iniciarConversacion() {
        if (conversacion.isTransient()) {
            conversacion.begin();

        }

    }

    private void terminarConversacion() {
        if (!conversacion.isTransient()) {
            conversacion.end();

        }

    }

    public String cancelar() {
        terminarConversacion();
        return "/admin/dashboard/miCuenta.xhtml?faces-redirect=true";
    }

    public String PrepararEditarPerfil(Persona p) {
        iniciarConversacion();
        personaSeleccionada = p;
        return "/admin/dashboard/editarPerfil.xhtml?faces-redirect=true";

    }

    public String actualizarPerfil() {
        personaFacadeLocal.edit(personaSeleccionada);
        return cancelar();
    }
}
