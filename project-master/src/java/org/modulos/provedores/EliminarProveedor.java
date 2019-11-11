/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.modulos.provedores;

import java.io.Serializable;
import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.inject.Named;
import javax.faces.view.ViewScoped;
import org.dao.ProveedorFacadeLocal;
import org.entidades.Proveedor;

/**
 *
 * @author David
 */
@Named(value = "eliminarProveedor")
@ViewScoped
public class EliminarProveedor implements Serializable{
    
    @EJB
    private ProveedorFacadeLocal proveedorFacadeLocal;
    private Proveedor proveedorSeleccionado; 
    
    public EliminarProveedor() {
        
    }
    
    @PostConstruct
    public void init(){
        proveedorSeleccionado = new Proveedor();
        
    }

    public Proveedor getProveedorSeleccionado() {
        return proveedorSeleccionado;
    }

    public void setProveedorSeleccionado(Proveedor proveedorSeleccionado) {
        this.proveedorSeleccionado = proveedorSeleccionado;
    }
    
    public void prepararEliminar(Proveedor p){
        proveedorSeleccionado = p;
    
    }
    
    public String eliminar(){
        proveedorFacadeLocal.remove(proveedorSeleccionado);
        return "/admin/provedores/listarProveedores.xhtml?faces-redirect=true";
    
    }
    
}