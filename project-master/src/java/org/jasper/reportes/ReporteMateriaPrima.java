/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.jasper.reportes;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.inject.Named;
import javax.enterprise.context.RequestScoped;
import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;
import javax.inject.Inject;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletResponse;
import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JasperExportManager;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.data.JRBeanCollectionDataSource;
import org.dao.MateriaPrimaFacadeLocal;
import org.dao.PersonaFacadeLocal;
import org.entidades.MateriaPrima;
import org.entidades.Persona;
import org.login.ControladorSesion;

/**
 *
 * @author David
 */
@Named(value = "reporteMateriaPrima")
@RequestScoped
public class ReporteMateriaPrima {

    @Inject
    private ControladorSesion controladorSesion;
    
    @EJB
    private MateriaPrimaFacadeLocal materiaPrimaFacadeLocal;
    private List<MateriaPrima>listaReporteMateriaPrima;
    private JasperPrint jasperPrint;
    
    public ReporteMateriaPrima() {
    }
    
    @PostConstruct
    public void init(){
        listaReporteMateriaPrima = materiaPrimaFacadeLocal.findAll();
    }

    public List<MateriaPrima> getListaReporteMateriaPrima() {
        return listaReporteMateriaPrima;
    }

    public void setListaReporteMateriaPrima(List<MateriaPrima> listaReporteMateriaPrima) {
        this.listaReporteMateriaPrima = listaReporteMateriaPrima;
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
        params.put("nombre", controladorSesion.getP().getNombre().toString());
        JRBeanCollectionDataSource bcds = new JRBeanCollectionDataSource(listaReporteMateriaPrima);
        String reportPath = FacesContext.getCurrentInstance().getExternalContext().getRealPath("/")+"/WEB-INF/reportes/materia_prima/report2.jasper";
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
        httpServletResponse.setHeader("Content-disposition", "attachment; filename=\"Reporte.pdf\"");
        servletOutputStream = httpServletResponse.getOutputStream();
        JasperExportManager.exportReportToPdfStream(jasperPrint, servletOutputStream);
        facesContext.responseComplete();
        
    }
    
    
    
}
