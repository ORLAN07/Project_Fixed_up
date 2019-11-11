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
import org.dao.RolFacadeLocal;
import org.entidades.Rol;

/**
 *
 * @author David
 */
@Named(value = "rolController")
@ViewScoped
public class RolController implements Serializable{

    @EJB
    private RolFacadeLocal rfl;
    private List<Rol> roles;


    public RolController() {
    }
    
    @PostConstruct
    public void init(){
        roles = rfl.findAll();
        for(Rol rol:roles){
            System.out.println(rol);
        }
    }

    public List<Rol> getRoles() {
        return roles;
    }
    
}