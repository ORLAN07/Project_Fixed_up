/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.modulos.proyectos;

import java.io.Serializable;
import java.util.Date;
import javax.ejb.EJB;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.inject.Named;
import javax.faces.view.ViewScoped;
import org.dao.DificultadFacadeLocal;
import org.dao.EstadoFacadeLocal;
import org.dao.PedidoFacadeLocal;
import org.dao.PersonaFacadeLocal;
import org.dao.ProyectoFacadeLocal;
import org.entidades.Dificultad;
import org.entidades.Estado;
import org.entidades.Pedido;
import org.entidades.Persona;
import org.entidades.Proyecto;

/**
 *
 * @author David
 */
@Named(value = "crearProyecto")
@ViewScoped
public class CrearProyecto implements Serializable{

    @EJB
    private ProyectoFacadeLocal pfl;
    @EJB
    private PedidoFacadeLocal pedidoFacadeLocal;
    @EJB
    private EstadoFacadeLocal efl;
    @EJB
    private DificultadFacadeLocal dfl;
    @EJB
    private PersonaFacadeLocal personaFacadeLocal;

    private int idProyecto;
    private Date fechaInicio;
    private int tiempoEstimado;

    public CrearProyecto() {
    }

    public int getIdProyecto() {
        return idProyecto;
    }

    public void setIdProyecto(int idProyecto) {
        this.idProyecto = idProyecto;
    }

    public Date getFechaInicio() {
        return fechaInicio;
    }

    public void setFechaInicio(Date fechaInicio) {
        this.fechaInicio = fechaInicio;
    }

    public int getTiempoEstimado() {
        return tiempoEstimado;
    }

    public void setTiempoEstimado(int tiempoEstimado) {
        this.tiempoEstimado = tiempoEstimado;
    }

    public String crear() {
        try {
            Estado estado;
            Dificultad dificultad;
            Persona persona;
            Pedido pedido;
            
            pedido = pedidoFacadeLocal.find(1);
            estado = efl.find(1);
            dificultad = dfl.find(1);
            persona = personaFacadeLocal.find(1);
            
            Proyecto p = new Proyecto(null, null, null);
            
            p.setDificultadesIdDificultad(dificultad);
            p.setEstadosIdEstado(estado);
            p.setOperarioIdPersona(persona);
            p.setPedidosIdPedido(pedido);
            Date date = new Date();
            p.setFechaInicio(date);
            pfl.create(p);
            return "/admin/proyectos/listarProyectos.xhtml?faces-redirect=true";
        } catch (Exception e) {
            e.printStackTrace();
            FacesMessage msj = new FacesMessage(FacesMessage.SEVERITY_ERROR, "error al crear el proyecto, por favor contacte al admin", "");
            FacesContext.getCurrentInstance().addMessage(null, msj);
            return "";
            
        }
        
    }
    
    public String cancelar() {
        return "/admin/proyectos/listarProyectos.xhtml?faces-redirect=true";

    }
    
}