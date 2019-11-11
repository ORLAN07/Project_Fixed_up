/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.modulos.stock;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.inject.Named;
import javax.faces.view.ViewScoped;
import javax.ejb.EJB;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
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
@Named(value = "crearStock")
@ViewScoped
public class CrearStock implements Serializable {

    @EJB
    private MateriaPrimaFacadeLocal materiaPrimaFacadeLocal;
    @EJB
    private StockFacadeLocal stockFacadeLocal;
    @EJB
    private ProveedorFacadeLocal proveedorFacadeLocal;
    private List<MateriaPrima>listaMateriaPrima;
    private List<Proveedor>listaProvedores;
    private MateriaPrima materiaPrima;
    private Stock stockEntidad;
    private Proveedor proveedor;

    @PostConstruct
    public void init() {
        listaMateriaPrima = materiaPrimaFacadeLocal.findAll();
        listaProvedores = proveedorFacadeLocal.findAll();
        stockEntidad = new Stock();
        materiaPrima = new MateriaPrima();
        proveedor = new Proveedor();
        
    }
    
    public List<Proveedor> getListaProvedores() {
        return listaProvedores;
    }

    public void setListaProvedores(List<Proveedor> listaProvedores) {
        this.listaProvedores = listaProvedores;
    }

    public Proveedor getProveedor() {
        return proveedor;
    }

    public void setProveedor(Proveedor proveedor) {
        this.proveedor = proveedor;
    }
    
    public MateriaPrima getMateriaPrima() {
        return materiaPrima;
    }

    public void setMateriaPrima(MateriaPrima materiaPrima) {
        this.materiaPrima = materiaPrima;
    }

    public Stock getStockEntidad() {
        return stockEntidad;
    }

    public void setStockEntidad(Stock stockEntidad) {
        this.stockEntidad = stockEntidad;
    }
    
    public List<MateriaPrima> getListaMateriaPrima() {
        return listaMateriaPrima;
    }

    public String crear() {
        try {
            List<Proveedor>proveedorSeleccionado = new ArrayList<>();
            proveedorSeleccionado.add(proveedor);
            Date date = new Date();
            stockEntidad.setMateriasPrimaIdMateria(materiaPrima);
            stockEntidad.setFechaIngreso(date);
            stockEntidad.getMateriasPrimaIdMateria().setProveedorList(proveedorSeleccionado);
            stockFacadeLocal.create(stockEntidad);            
            return "/admin/stock/listarStock.xhtml?faces-redirect=true";
        } catch (Exception e) {
            e.printStackTrace();
            FacesMessage msj = new FacesMessage(FacesMessage.SEVERITY_ERROR, "error al crear el stock, por favor contacte al admin", "");
            FacesContext.getCurrentInstance().addMessage(null, msj);
            return "";
            
        }
        
    }

    public String cancelar() {
        System.out.println(toString());
        return "/admin/stock/listarStock.xhtml?faces-redirect=true";

    }
    
}