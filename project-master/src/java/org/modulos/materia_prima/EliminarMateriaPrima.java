/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.modulos.materia_prima;

import java.io.Serializable;
import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.inject.Named;
import javax.faces.view.ViewScoped;
import org.dao.MateriaPrimaFacadeLocal;
import org.entidades.MateriaPrima;

/**
 *
 * @author David
 */
@Named(value = "eliminarMateriaPrima")
@ViewScoped
public class EliminarMateriaPrima implements Serializable{

    @EJB
    private MateriaPrimaFacadeLocal materiaPrimaFacadeLocal;
    private MateriaPrima materiaPrimaSeleccionada;

    public EliminarMateriaPrima() {

    }

    @PostConstruct
    public void init() {
        materiaPrimaSeleccionada = new MateriaPrima();
        
    }

    public MateriaPrima getMateriaPrimaSeleccionada() {
        return materiaPrimaSeleccionada;
        
    }

    public void setMateriaPrimaSeleccionada(MateriaPrima materiaPrimaSeleccionada) {
        this.materiaPrimaSeleccionada = materiaPrimaSeleccionada;
        
    }

    public void prepararEliminar(MateriaPrima mp) {
        materiaPrimaSeleccionada = mp;

    }

    public String eliminar() {
        try {
            materiaPrimaFacadeLocal.remove(materiaPrimaSeleccionada);
            FacesMessage msj = new FacesMessage(FacesMessage.SEVERITY_INFO, "Materia prima eliminada correctamente", "");
            FacesContext.getCurrentInstance().addMessage(null, msj);
            return "/admin/materiaPrima/ListarMateriaPrima.xhtml?faces-redirect=true";
        } catch (Exception e) {
            materiaPrimaFacadeLocal.remove(materiaPrimaSeleccionada);
            FacesMessage msj = new FacesMessage(FacesMessage.SEVERITY_INFO, "error al eliminar", "");
            FacesContext.getCurrentInstance().addMessage(null, msj);
            return "/admin/materiaPrima/ListarMateriaPrima.xhtml?faces-redirect=true";
        }

    }

}
