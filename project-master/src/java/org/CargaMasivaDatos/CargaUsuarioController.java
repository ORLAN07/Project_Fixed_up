/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.CargaMasivaDatos;

//import edu.crud.util.MessageUtil;
import java.io.Serializable;
import javax.ejb.EJB;
import javax.faces.context.FacesContext;
import javax.inject.Named;
import javax.faces.view.ViewScoped;
import javax.inject.Inject;
import org.dao.PersonaFacadeLocal;

/**
 *
 * @author David
 */
@Named(value = "cargaUsuarioController")
@ViewScoped
public class CargaUsuarioController implements Serializable{

    @Inject
    private FileUploadController fuc;
    @EJB
    private PersonaFacadeLocal pfl;
    /**
     * Creates a new instance of CargaUsuarioController
     */
    public CargaUsuarioController() {
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
                pfl.loadUsuarios(d);
            } catch (Exception e) {
                e.printStackTrace();;
            }
            return "";
        } else{
            
            return "";
        }
    }
}
