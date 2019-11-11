/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.modulos.provedores;

import java.io.Serializable;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.inject.Named;
import javax.faces.view.ViewScoped;
import org.dao.CiudadFacadeLocal;
import org.dao.ProveedorFacadeLocal;
import org.entidades.Ciudad;
import org.entidades.Proveedor;

/**
 *
 * @author David
 */
@Named(value = "crearProveedor")
@ViewScoped
public class CrearProveedor implements Serializable{
    
    @EJB
    private ProveedorFacadeLocal proveedorFacadeLocal;
    @EJB
    private CiudadFacadeLocal ciudadFacadeLocal;
    private List<Ciudad>listaCiudades;
    private Proveedor proveedor;
    private Ciudad ciudad;
    
    public CrearProveedor() {
        
    }
    
    @PostConstruct
    public void init(){
        listaCiudades = ciudadFacadeLocal.findAll();
        ciudad = new Ciudad();
        proveedor = new Proveedor();
        
    }

    public ProveedorFacadeLocal getProveedorFacadeLocal() {
        return proveedorFacadeLocal;
    }

    public void setProveedorFacadeLocal(ProveedorFacadeLocal proveedorFacadeLocal) {
        this.proveedorFacadeLocal = proveedorFacadeLocal;
    }

    public CiudadFacadeLocal getCiudadFacadeLocal() {
        return ciudadFacadeLocal;
    }

    public void setCiudadFacadeLocal(CiudadFacadeLocal ciudadFacadeLocal) {
        this.ciudadFacadeLocal = ciudadFacadeLocal;
    }

    public List<Ciudad> getListaCiudades() {
        return listaCiudades;
    }

    public void setListaCiudades(List<Ciudad> listaCiudades) {
        this.listaCiudades = listaCiudades;
    }

    public Proveedor getProveedor() {
        return proveedor;
    }

    public void setProveedor(Proveedor proveedor) {
        this.proveedor = proveedor;
    }

    public Ciudad getCiudad() {
        return ciudad;
    }

    public void setCiudad(Ciudad ciudad) {
        this.ciudad = ciudad;
    }
    
    public String crear(){
        try {
            proveedorFacadeLocal.create(proveedor);
            return "/admin/provedores/listarProveedores.xhtml?faces-redirect=true";
        } catch (Exception e) {
            e.printStackTrace();
            FacesMessage msj = new FacesMessage(FacesMessage.SEVERITY_ERROR, "error al crear el proveedor, por favor contacte al admin", "");
            FacesContext.getCurrentInstance().addMessage(null, msj);
            return "";
            
        }
        
    }
    
    public String cancelar() {
        return "/admin/provedores/listarProveedores.xhtml?faces-redirect=true";
        
    }
       
}