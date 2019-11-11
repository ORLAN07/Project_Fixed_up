/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.modulos.materia_prima;

import javax.inject.Named;
import javax.enterprise.context.ConversationScoped;
import java.io.Serializable;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.enterprise.context.Conversation;
import javax.inject.Inject;
import org.dao.MateriaPrimaFacadeLocal;
import org.dao.StockFacadeLocal;
import org.entidades.MateriaPrima;
import org.entidades.Stock;

/**
 *
 * @author David
 */
@Named(value = "listarMateriaPrima")
@ConversationScoped
public class ListarMateriaPrima implements Serializable {

    @Inject
    private Conversation conversacion;
    @EJB
    private StockFacadeLocal stockFacadeLocal;
    @EJB
    private MateriaPrimaFacadeLocal materiaPrimaFacadeLocal;
    private List<MateriaPrima> listaMateriaPrima;
    private List<Stock> listaStock;
    private MateriaPrima materiaPrimaSeleccionada;
    
    public ListarMateriaPrima() {
        
    }
    
    @PostConstruct
    public void init(){
        listaMateriaPrima = materiaPrimaFacadeLocal.findAll();
        
    }

    public List<MateriaPrima> getListaMateriaPrima() {
        return listaMateriaPrima;
    }

    public MateriaPrima getMateriaPrimaSeleccionada() {
        return materiaPrimaSeleccionada;
    }

    public void setMateriaPrimaSeleccionada(MateriaPrima materiaPrimaSeleccionada) {
        this.materiaPrimaSeleccionada = materiaPrimaSeleccionada;
    }

    public List<Stock> getListaStock() {
        return listaStock;
    }

    public void setListaStock(List<Stock> listaStock) {
        this.listaStock = listaStock;
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
        return "/admin/materiaPrima/listarMateriaPrima.xhtml?faces-redirect=true";

    }
    
    public String prepararEditar(MateriaPrima mp){
        iniciarConversacion();
        materiaPrimaSeleccionada = mp;
        return "/admin/materiaPrima/editarMateriaPrima.xhtml?faces-redirect=true";
        
    }

    public String actializarMateriaPrima(){
        materiaPrimaFacadeLocal.edit(materiaPrimaSeleccionada);
        return cancelar();
    }
    
}