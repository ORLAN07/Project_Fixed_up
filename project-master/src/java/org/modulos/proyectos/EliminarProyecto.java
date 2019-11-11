/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.modulos.proyectos;

import java.io.Serializable;
import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.inject.Named;
import javax.faces.view.ViewScoped;
import org.dao.ProyectoFacadeLocal;
import org.entidades.Proyecto;

/**
 *
 * @author David
 */
@Named(value = "eliminarProyecto")
@ViewScoped
public class EliminarProyecto implements Serializable{
    
    @EJB
    private ProyectoFacadeLocal proyectoFacadeLocal;
    private Proyecto proyectoSeleccionado;
    
    public EliminarProyecto() {
    }
    
    @PostConstruct
    public void init(){
        proyectoSeleccionado = new Proyecto();
    
    }

    public Proyecto getProyectoSeleccionado() {
        return proyectoSeleccionado;
    }

    public void setProyectoSeleccionado(Proyecto proyectoSeleccionado) {
        this.proyectoSeleccionado = proyectoSeleccionado;
    }
    
    public void prepararEliminar(Proyecto p){
        proyectoSeleccionado = p;
        
    }
    
    public String eliminar(){
        proyectoFacadeLocal.remove(proyectoSeleccionado);
        return "/admin/proyectos/listarProyectos.xhtml?faces-redirect=true";
    
    }
}
