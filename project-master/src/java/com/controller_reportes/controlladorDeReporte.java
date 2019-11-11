/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.controller_reportes;


import java.io.IOException;
import java.io.Serializable;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.inject.Named;
import javax.enterprise.context.RequestScoped;
import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletResponse;
import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JasperExportManager;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.data.JRBeanCollectionDataSource;
import net.sf.jasperreports.engine.util.JRStyledTextParser;
import org.dao.PersonaFacadeLocal;
import org.entidades.Persona;
import org.login.ControladorSesion;

/**
 *
 * @author Orlando.R.R
 */
@Named(value = "controlladorDeReporte")
@RequestScoped
public class controlladorDeReporte implements Serializable{

    /**
     * Creates a new instance of controlladorDeReporte
     */
    public controlladorDeReporte() {
    }
    
   
    @EJB
    private PersonaFacadeLocal pfl; 
    private List<Persona> listapersona;
    private JasperPrint jp;
    

    public PersonaFacadeLocal getPfl() {
        return pfl;
    }

    public void setPfl(PersonaFacadeLocal pfl) {
        this.pfl = pfl;
    }

    public List<Persona> getListapersona() {
        return listapersona;
    }

    public void setListapersona(List<Persona> listapersona) {
        this.listapersona = listapersona;
    }

    public JasperPrint getJp() {
        return jp;
    }

    public void setJp(JasperPrint jp) {
        this.jp = jp;
    }
    
    @PostConstruct
    public void init(){
        listapersona = pfl.findAll();
    }
    
    
    private void prepararExport() throws JRException{
        
        Map<String, Object> params = new HashMap();
        
        
        JRBeanCollectionDataSource bcds = new JRBeanCollectionDataSource(listapersona);
        String reporPath = FacesContext.getCurrentInstance().getExternalContext().getRealPath("/") + "/WEB-INF/reportes/pruebaReporte.jasper"  ;
        jp = JasperFillManager.fillReport(reporPath, params, bcds);
    }
    
    static {
    System.setProperty("java.awt.headless", "true");
    }
    
    
     public void exportarPDF() throws IOException, JRException {
        prepararExport();
        ServletOutputStream out = null;
        String contentType = "application/pdf";
        FacesContext fc = FacesContext.getCurrentInstance();
        ExternalContext ec = fc.getExternalContext();
        HttpServletResponse res = (HttpServletResponse) ec.getResponse();
        res.setContentType(contentType);
        res.addHeader("Content-disposition", "attachment; filename=\"ReporteUsuarios.pdf\"");
        out = res.getOutputStream();
        JasperExportManager.exportReportToPdfStream(jp, out);
        fc.responseComplete();
    }
    
    
    
}
