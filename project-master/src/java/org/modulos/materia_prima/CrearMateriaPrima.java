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
@Named(value = "crearMateriaPrima")
@ViewScoped
public class CrearMateriaPrima implements Serializable {

    @EJB
    private MateriaPrimaFacadeLocal materiaPrimaFacadeLocal;
    private MateriaPrima materiaPrima;

    public CrearMateriaPrima() {

    }

    @PostConstruct
    public void init() {
        materiaPrima = new MateriaPrima();
    }

    public MateriaPrima getMateriaPrima() {
        return materiaPrima;
    }

    public void setMateriaPrima(MateriaPrima materiaPrima) {
        this.materiaPrima = materiaPrima;
    }

    public String crear() {
        try {
            materiaPrimaFacadeLocal.create(materiaPrima);
            return "/admin/materiaPrima/listarMateriaPrima.xhtml?faces-redirect=true";

        } catch (Exception e) {
            e.printStackTrace();
            FacesMessage msj = new FacesMessage(FacesMessage.SEVERITY_ERROR, "error al crear la materia prima, por favor contacte al admin", "");
            FacesContext.getCurrentInstance().addMessage(null, msj);
            return "";

        }

    }

    public String cancelar() {
        return "/admin/materiaPrima/listarMateriaPrima.xhtml?faces-redirect=true";

    }

}
