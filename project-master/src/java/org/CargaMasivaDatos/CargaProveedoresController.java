/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.CargaMasivaDatos;

import java.io.Serializable;
import javax.ejb.EJB;
import javax.inject.Named;
import javax.faces.context.FacesContext;
import javax.faces.view.ViewScoped;
import javax.inject.Inject;
import org.dao.ProveedorFacade;
import org.dao.ProveedorFacadeLocal;

/**
 *
 * @author David
 */
@Named(value = "cargaProveedoresController")
@ViewScoped
public class CargaProveedoresController implements Serializable{

    @Inject
    private FileUploadController fuc;
    @EJB
    private ProveedorFacadeLocal pfl;
    /**
     * Creates a new instance of CargaUsuarioController
     */
    public CargaProveedoresController() {
    }
    
    public String cargarUsuarios(){
        //System.out.println("hdjsadkasjdjsaljdajkld");
        fuc.setNameFileUpload("datos");
        if(fuc.uploadFiles()){
            try {
                String fileDatos = FacesContext.getCurrentInstance().getExternalContext().getRealPath("")
                        + fuc.getPathUploadFile() + fuc.getNameFileUpload();
                String d = fileDatos.replaceAll("\\\\", "/");
                System.out.println("dhsjakhdjhajdhasjhdj - " + fileDatos);
                System.out.println("dhsjakhdjhajdhasjhdj - " + d);
                pfl.loadProveedores(d);
            } catch (Exception e) {
                e.printStackTrace();
            }
            return "";
        } else{
            
            return "";
        }
    }

}
