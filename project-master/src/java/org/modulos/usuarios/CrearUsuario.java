/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.modulos.usuarios;

import com.controllerEmail.EnviarCorreosMasivos.controller;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.inject.Named;
import javax.faces.view.ViewScoped;
import org.dao.PersonaFacadeLocal;
import org.entidades.Persona;
import javax.ejb.EJB;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.inject.Inject;
import org.dao.AreaFacadeLocal;
import org.dao.RolFacadeLocal;
import org.entidades.Area;
import org.entidades.Rol;
import org.login.ControladorSesion;
/**
 *
 * @author David
 */
@Named(value = "crearUsuario")
@ViewScoped
public class CrearUsuario implements Serializable {

    @EJB
    private PersonaFacadeLocal pfl;
    @EJB
    private RolFacadeLocal rfl;
    @EJB
    private AreaFacadeLocal afl;
    @Inject
    ControladorSesion controladorSesion;
    private Persona persona;

    private List<Rol> listaRoles;
    private Rol rol;
    private List<Rol> rolesAsignados;
    private List<Area> listaAreas;
    private controller c;


    @PostConstruct
    public void init() {
        listaAreas = afl.findAll();
        for(Rol rol:controladorSesion.getP().getRoles()){
            listaRoles = rfl.findRolByUsuarioEnSesion(rol.getIdRol());
            System.out.println(rol.getIdRol());
        }
        persona = new Persona();
        c = new controller();

    }

    public Persona getPersona() {
        return persona;
    }

    public void setPersona(Persona persona) {
        this.persona = persona;
    }

    public List<Rol> getListaRoles() {
        return listaRoles;
    }

    public List<Area> getListaAreas() {
        return listaAreas;
    }

    public List<Rol> getRolesAsignados() {
        return rolesAsignados;
    }

    public void setRolesAsignados(List<Rol> rolesAsignados) {
        this.rolesAsignados = rolesAsignados;
    }

    public Rol getRol() {
        return rol;
    }

    public void setRol(Rol rol) {
        this.rol = rol;
    }

    public String crearUsuario() {
        int year = Calendar.getInstance().get(Calendar.YEAR);
        try {
            if (persona != null) {
                String mensaje = "<h2>Estimado " + persona.getNombre() + " " + persona.getApellido() + ".<h2/><br/>"
                        + "<h3>Nos permitimos informarle que su usuario ha sido creado exitosamente.<br/>"
                        + "Datos de inicio de sesion de su cuenta:<h3/><br/>"
                        + "<h4>Usuario: " + persona.getDocumento() + "<br/>"
                        + "Contraseña = " + persona.getPassword() + "<h4/><br/>"
                        + "<h5>Este correo es de carácter informativo, por favor no responder<br/>"
                        + "Fixedup " + year + "<h5/>";
                Calendar cal = Calendar.getInstance();
                persona.setIdPersona(null);
                persona.setFechaIngreso(cal.getTime());
                rolesAsignados = new ArrayList<>();
                rolesAsignados.add(rfl.find(rol.getIdRol()));
                persona.setRoles(rolesAsignados);
                pfl.create(persona);
                c.enviarEmailCliente(persona.getEmail(), "Notificacion creacion usuario", mensaje);
                return "/admin/usuarios/listarUsuarios.xhtml?faces-redirect=true";

            } else {
                FacesMessage msj = new FacesMessage(FacesMessage.SEVERITY_ERROR, "Por favor, Termine de llenar el formulario", "");
                FacesContext.getCurrentInstance().addMessage(null, msj);
                return "";

            }
        } catch (Exception e) {
            e.printStackTrace();
            FacesMessage msj = new FacesMessage(FacesMessage.SEVERITY_ERROR, "error al crear el usuario, por favor contacte al admin", "");
            FacesContext.getCurrentInstance().addMessage(null, msj);
            return "";

        }

    }

    public String cancelar() {
        return "/admin/usuarios/listarUsuarios.xhtml?faces-redirect=true";

    }

}
