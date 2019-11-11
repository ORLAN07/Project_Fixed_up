/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.modulos.roles;

import java.io.Serializable;
import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.inject.Named;
import javax.faces.view.ViewScoped;
import org.dao.RolFacadeLocal;
import org.entidades.Rol;

/**
 *
 * @author David
 */
@Named(value = "eliminarRol")
@ViewScoped
public class EliminarRol implements Serializable{

    @EJB
    private RolFacadeLocal rolFacadeLocal;
    private Rol rolSeleccionado;
    
    public EliminarRol() {
    }
    
    @PostConstruct
    public void init(){
        rolSeleccionado = new Rol();
        
    }

    public Rol getRolSeleccionado() {
        return rolSeleccionado;
    }

    public void setRolSeleccionado(Rol rolSeleccionado) {
        this.rolSeleccionado = rolSeleccionado;
    }
    
    public void prepararEliminar(Rol r){
        rolSeleccionado = r;
    
    }
    
    public String eliminar(){
        boolean isUntouched = false; //o sea, es root, admin, vendedor o ayudante
        
        if ("administrador".equals(rolSeleccionado.getNombreRol()) || "root".equals(rolSeleccionado.getNombreRol()) || "vendedor".equals(rolSeleccionado.getNombreRol()) || "operario".equals(rolSeleccionado.getNombreRol())) {
            isUntouched=true;
            
        }
        
        if (isUntouched) {
            FacesMessage mjs = new FacesMessage(FacesMessage.SEVERITY_WARN, "No se puede eliminar el rol. ", "nombre rol: " + rolSeleccionado.getNombreRol());
            FacesContext.getCurrentInstance().addMessage(null, mjs);
            return "";

        }else{
            rolFacadeLocal.remove(rolSeleccionado);
            FacesMessage msj = new FacesMessage(FacesMessage.SEVERITY_INFO, "error eliminado correctamente", "nombre rol: " + rolSeleccionado.getNombreRol());
            FacesContext.getCurrentInstance().addMessage(null, msj);
            return "listarRoles.xhtml?faces-redirect=true";
            
        }
                
    }
    
}