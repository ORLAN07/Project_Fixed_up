/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.modulos.usuarios;

import java.io.Serializable;
import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.faces.application.FacesMessage;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;
import javax.faces.view.ViewScoped;
import javax.inject.Named;
import org.dao.PersonaFacadeLocal;
import org.entidades.Persona;
import org.entidades.Rol;

/**
 *
 * @author David
 */
@Named(value = "eliminarUsuario")
@ViewScoped
public class EliminarUsuario implements Serializable {

    @EJB
    private PersonaFacadeLocal pfl;
    private Persona personaSeleccionado;

    public EliminarUsuario() {

    }

    @PostConstruct
    public void init() {
        personaSeleccionado = new Persona();
    }

    public Persona getPersonaSeleccionado() {
        return personaSeleccionado;
    }

    public void setPersonaSeleccionado(Persona personaSeleccionado) {
        this.personaSeleccionado = personaSeleccionado;
    }

    public void preparacionEliminar(Persona p) {

        personaSeleccionado = p;
        System.out.printf("se va a eliminar: %s", personaSeleccionado.getNombre());

        /**/
    }

    public String eliminarUsuario() {
        boolean isAdmin=false;
        for (Rol rol : personaSeleccionado.getRoles()) {
            if ("administrador".equals(rol.getNombreRol()) || "root".equals(rol.getNombreRol())) {
                isAdmin=true;
            }
        }

        System.out.println("entro");
        if (isAdmin) {
            FacesMessage msj = new FacesMessage(FacesMessage.SEVERITY_INFO, "no se pueden borrar usuarios administradores o root", "");
            FacesContext.getCurrentInstance().addMessage(null, msj);
            return "";

        } else {

            pfl.remove(this.personaSeleccionado);
            FacesMessage msj = new FacesMessage(FacesMessage.SEVERITY_INFO, "Usuario eliminado correctamente", "");
            FacesContext.getCurrentInstance().addMessage(null, msj);
            return "listarUsuarios.xhtml?faces-redirect=true";
            
        }
        
    }

}
