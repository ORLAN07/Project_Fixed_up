/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.modulos.usuarios;

import java.io.Serializable;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.inject.Named;
import javax.faces.view.ViewScoped;
import org.dao.PermisoFacadeLocal;
import org.entidades.Permiso;

/**
 *
 * @author David
 */
@Named(value = "permisoController")
@ViewScoped
public class PermisoController implements Serializable{

    @EJB
    private PermisoFacadeLocal permisoFacadeLocal;
    private List<Permiso> listaPermiso;
    
    public PermisoController() {
    }
    
    @PostConstruct
    public void init(){
        listaPermiso = permisoFacadeLocal.findAll();
    
    }

    public List<Permiso> getListaPermiso() {
        return listaPermiso;
    }
    
}
