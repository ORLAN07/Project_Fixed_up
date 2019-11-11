/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.modulos.stock;

import java.io.Serializable;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.enterprise.context.Conversation;
import javax.enterprise.context.ConversationScoped;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.inject.Inject;
import javax.inject.Named;
import org.dao.MateriaPrimaFacadeLocal;
import org.dao.ProveedorFacadeLocal;
import org.dao.StockFacadeLocal;
import org.entidades.MateriaPrima;
import org.entidades.Proveedor;
import org.entidades.Stock;

/**
 *
 * @author David
 */
@Named(value = "listarStock")
@ConversationScoped
public class ListarStock implements Serializable {

    @EJB
    private StockFacadeLocal stockFacadeLocal;
    @EJB
    private ProveedorFacadeLocal proveedorFacadeLocal;
    private Stock stockSeleccionado;
    private List<Stock> listaStock;
    private List<Proveedor>listaProveedores;
    @Inject
    private Conversation conversacion;

    public ListarStock() {
    }

    @PostConstruct
    public void init() {
        listaStock = stockFacadeLocal.findAll();
        listaProveedores = proveedorFacadeLocal.findAll();

    }

    public Stock getStockSeleccionado() {
        return stockSeleccionado;
    }

    public void setStockSeleccionado(Stock stockSeleccionado) {
        this.stockSeleccionado = stockSeleccionado;
    }

    public List<Stock> getListaStock() {
        return listaStock;
    }

    public void setListaStock(List<Stock> listaStock) {
        this.listaStock = listaStock;
    }

    public List<Proveedor> getListaProveedores() {
        return listaProveedores;
    }
    
    public Conversation getConversacion() {
        return conversacion;
    }

    public void setConversacion(Conversation conversacion) {
        this.conversacion = conversacion;
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
        return "/admin/stock/listarStock.xhtml?faces-redirect=true";

    }

    public String preparacionEditar(Stock s) {
        stockSeleccionado = s;
        iniciarConversacion();
        return "/admin/stock/editarStock.xhtml?faces-redirect=true";

    }

    public String actualizarStock() {
        stockFacadeLocal.edit(stockSeleccionado);
        return cancelar();

    }

    public void preparacionEliminar(Stock s) {
        iniciarConversacion();

    }

    public void eliminarStock() {
        try {
            stockFacadeLocal.remove(stockSeleccionado);
            FacesMessage msj = new FacesMessage(FacesMessage.SEVERITY_INFO, "stock eliminado correctamente", "");
            FacesContext.getCurrentInstance().addMessage(null, msj);

        } catch (Exception e) {
            e.printStackTrace();
            FacesMessage msj = new FacesMessage(FacesMessage.SEVERITY_ERROR, "error al emiminar el stock", "intentelo de nuevo");
            FacesContext.getCurrentInstance().addMessage(null, msj);

        }

    }

}