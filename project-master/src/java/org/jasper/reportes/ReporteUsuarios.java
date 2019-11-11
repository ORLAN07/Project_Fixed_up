/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.jasper.reportes;

import java.io.IOException;
import java.io.Serializable;
import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.inject.Named;
import javax.enterprise.context.RequestScoped;
import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;
import javax.faces.view.ViewScoped;
import javax.inject.Inject;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletResponse;
import org.dao.PersonaFacadeLocal;
import org.entidades.Persona;
import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JasperCompileManager;
import net.sf.jasperreports.engine.JasperExportManager;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.JasperReport;
import net.sf.jasperreports.engine.data.JRBeanCollectionDataSource;
import net.sf.jasperreports.engine.export.JRXlsExporter;
import net.sf.jasperreports.engine.export.JRXlsExporterParameter;
import org.login.ControladorSesion;

/**
 *
 * @author David
 */
@Named(value = "reporteUsuarios")
@ViewScoped
public class ReporteUsuarios implements Serializable{

    @Inject
    private ControladorSesion controladorSesion;
    
    @EJB
    private PersonaFacadeLocal personaFacadeLocal;
    private List<Persona>listaReportePersona;
    private JasperPrint jasperPrint;
    
    public ReporteUsuarios() {
    }
    
    @PostConstruct
    public void init(){
        listaReportePersona = personaFacadeLocal.findAll();
    }

    public List<Persona> getListaReportePersona() {
        return listaReportePersona;
    }

    public void setListaReportePersona(List<Persona> listaReportePersona) {
        this.listaReportePersona = listaReportePersona;
    }

    public JasperPrint getJasperPrint() {
        return jasperPrint;
    }

    public void setJasperPrint(JasperPrint jasperPrint) {
        this.jasperPrint = jasperPrint;
    }

    public ControladorSesion getControladorSesion() {
        return controladorSesion;
    }

    public void setControladorSesion(ControladorSesion controladorSesion) {
        this.controladorSesion = controladorSesion;
    }
    
    private void prepararExportar() throws JRException{
        
        Map<String, Object>params = new HashMap<>();
        Persona persona = controladorSesion.getP();
        String nombre = persona.getNombre();
        System.out.println(controladorSesion.getP().getNombre());
        params.put("nombre", controladorSesion.getP().getNombre() + " " + controladorSesion.getP().getApellido());
        JRBeanCollectionDataSource bcds = new JRBeanCollectionDataSource(listaReportePersona);
        String reportPath = FacesContext.getCurrentInstance().getExternalContext().getRealPath("/")+"/WEB-INF/reportes/usuarios/report1.jasper";
        jasperPrint = JasperFillManager.fillReport(reportPath, params, bcds);
    
    }
    
    public void exportarPDF() throws JRException, IOException{
        prepararExportar();
        ServletOutputStream servletOutputStream = null;
        String contentType = "aplication/PDF";
        FacesContext facesContext = FacesContext.getCurrentInstance();
        ExternalContext externalContext = facesContext.getExternalContext();
        HttpServletResponse httpServletResponse = (HttpServletResponse) externalContext.getResponse();
        httpServletResponse.setContentType(contentType);
        httpServletResponse.setHeader("Content-disposition", "attachment; filename=\"ReporteUsuarios.pdf\"");
        servletOutputStream = httpServletResponse.getOutputStream();
        JasperExportManager.exportReportToPdfStream(jasperPrint, servletOutputStream);
        facesContext.responseComplete();
        
    }
    
}