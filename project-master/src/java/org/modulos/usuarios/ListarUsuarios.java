/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.modulos.usuarios;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.enterprise.context.Conversation;
import javax.enterprise.context.ConversationScoped;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.inject.Inject;
import javax.inject.Named;
import org.dao.AreaFacadeLocal;
import org.dao.PersonaFacadeLocal;
import org.dao.RolFacadeLocal;
import org.entidades.Area;
import org.entidades.Persona;
import org.entidades.Rol;
import org.login.ControladorSesion;

/**
 *
 * @author David
 */
@Named(value = "listarUsuarios")
@ConversationScoped
public class ListarUsuarios implements Serializable {

    @EJB
    private PersonaFacadeLocal pfl;
    @EJB
    private RolFacadeLocal rfl;
    @EJB
    private AreaFacadeLocal afl;
    @Inject
    private Conversation conversacion;
    @Inject
    private ControladorSesion controladorSesion;
    private Persona personaSeleccionado;

    private Rol rol;
    private List<Persona> persona;
    private List<Area> listaAreas;
    private List<Rol> listaRoles;
    private List<Rol> rolesAsignados;
    private List<Rol> listaRolesListarUsuario;

    public ListarUsuarios() {
    }

    @PostConstruct
    public void init() {
        persona = pfl.findAll();
        listaAreas = afl.findAll();
        listaRolesListarUsuario = rfl.findAll();
        for(Rol rol:controladorSesion.getP().getRoles()){
            listaRoles = rfl.findRolByUsuarioEnSesion(rol.getIdRol());

        }
        
    }

    public Persona getPersonaSeleccionado() {
        return personaSeleccionado;
    }

    public void setPersonaSeleccionado(Persona personaSeleccionado) {
        this.personaSeleccionado = personaSeleccionado;
    }

    public Rol getRol() {
        return rol;
    }

    public void setRol(Rol rol) {
        this.rol = rol;
    }

    public List<Persona> getPersona() {
        return persona;
    }

    public void setPersona(List<Persona> persona) {
        this.persona = persona;
    }

    public List<Area> getListaAreas() {
        return listaAreas;
    }

    public void setListaAreas(List<Area> listaAreas) {
        this.listaAreas = listaAreas;
    }

    public List<Rol> getListaRoles() {
        return listaRoles;
    }

    public void setListaRoles(List<Rol> listaRoles) {
        this.listaRoles = listaRoles;
    }

    public List<Rol> getRolesAsignados() {
        return rolesAsignados;
    }

    public void setRolesAsignados(List<Rol> rolesAsignados) {
        this.rolesAsignados = rolesAsignados;
    }

    public List<Rol> getListaRolesListarUsuario() {
        return listaRolesListarUsuario;
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
        return "/admin/usuarios/listarUsuarios.xhtml?faces-redirect=true";

    }

    public String preparacionEditar(Persona p) {
        iniciarConversacion();
        personaSeleccionado = p;

        return "/admin/usuarios/editar.xhtml?faces-redirect=true";
    }

    public String actualizarUsuario() {
        rolesAsignados = new ArrayList<>();
        rolesAsignados.add(rfl.find(rol.getIdRol()));
        personaSeleccionado.setRoles(rolesAsignados);
        pfl.edit(personaSeleccionado);
        return cancelar();

    }

    public void preparacionEliminar(Persona p) {
        iniciarConversacion();
        personaSeleccionado = p;
        System.out.printf("se eliminar: %s", personaSeleccionado.getNombre());

    }

    public void eliminarUsuario() {
        try {
            System.out.println("entro a eliminar");
            pfl.remove(personaSeleccionado);
            FacesMessage msj = new FacesMessage(FacesMessage.SEVERITY_INFO, "Usuario eliminado correctamente", "");
            FacesContext.getCurrentInstance().addMessage(null, msj);

        } catch (Exception e) {
            e.printStackTrace();
            FacesMessage msj = new FacesMessage(FacesMessage.SEVERITY_ERROR, "error al emiminar el usuario", "intentelo de nuevo");
            FacesContext.getCurrentInstance().addMessage(null, msj);

        }

    }

}
