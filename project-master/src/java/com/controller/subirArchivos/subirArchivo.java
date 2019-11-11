/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.controller.subirArchivos;

import javax.inject.Named;
import javax.enterprise.context.SessionScoped;
import java.io.Serializable;
import javax.ejb.EJB;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.inject.Inject;
import org.dao.PersonaFacadeLocal;
import org.entidades.Persona;
import org.login.ControladorSesion;
import org.primefaces.model.UploadedFile;

/**
 *
 * @author Orlando.R.R
 */
@Named(value = "subirArchivo")
@SessionScoped
public class subirArchivo implements Serializable {

    private UploadedFile file;
    
    public subirArchivo() {
    }
    
    @EJB
    private PersonaFacadeLocal pfl;
    private Persona personaPersonaSeleccionada;
            

    @Inject
    private ControladorSesion cs;

    public PersonaFacadeLocal getPfl() {
        return pfl;
    }

    public void setPfl(PersonaFacadeLocal pfl) {
        this.pfl = pfl;
    }

    public ControladorSesion getCs() {
        return cs;
    }

    public void setCs(ControladorSesion cs) {
        this.cs = cs;
    }
    
    
    
    
    public UploadedFile getFile() {
        return file;
    }

    public void setFile(UploadedFile file) {
        this.file = file;
    }
    
    public void upload(){
        
        
        if (file != null) {
            
            try {
                
                //Class.forName("com.mysql.jdbc.Driver");
                //com.mysql.jdbc.Connection c = (com.mysql.jdbc.Connection) DriverManager.getConnection("jdbc:mysql://localhost/proyecto_v3?user=root&password=");
                //PreparedStatement ps = c.prepareStatement("UPDATE personas SET imagen WHERE id_persona=1");
               
               // ps.execute();
                //c.close();
                
                
                /*pfl.edit(cs.ge);*/
                
                FacesMessage message = new FacesMessage("Succesful", file.getFileName() + " is uploaded.");
                FacesContext.getCurrentInstance().addMessage(null, message);
                
            } catch (Exception e) {
                
                e.printStackTrace();
                FacesMessage message = new FacesMessage("Error de conexion");
                FacesContext.getCurrentInstance().addMessage(null, message);
            }
        }
    }
}
