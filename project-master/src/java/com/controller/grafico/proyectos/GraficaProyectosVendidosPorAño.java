/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.controller.grafico.proyectos;

import java.io.Serializable;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.inject.Named;
import javax.faces.view.ViewScoped;
import org.dao.ProyectoFacadeLocal;
import org.entidades.Proyecto;
import org.primefaces.model.chart.BarChartModel;
import org.primefaces.model.chart.PieChartModel;

/**
 *
 * @author Orlando.R.R
 */
@Named(value = "graficaProyectosVendidosPorAño")
@ViewScoped
public class GraficaProyectosVendidosPorAño implements Serializable{

    @EJB
    private ProyectoFacadeLocal pfl;
    List<Proyecto> listaProyectosVendidos;
    List<Proyecto> listaProyectosdosmilquince;
    private Proyecto proyecto;
    private PieChartModel piemodel;

    
    
    public GraficaProyectosVendidosPorAño() {
    }

    public ProyectoFacadeLocal getPfl() {
        return pfl;
    }

    public void setPfl(ProyectoFacadeLocal pfl) {
        this.pfl = pfl;
    }

    public List<Proyecto> getListaProyectosVendidos() {
        return listaProyectosVendidos;
    }

    public void setListaProyectosVendidos(List<Proyecto> listaProyectosVendidos) {
        this.listaProyectosVendidos = listaProyectosVendidos;
    }

    public Proyecto getProyecto() {
        return proyecto;
    }

    public void setProyecto(Proyecto proyecto) {
        this.proyecto = proyecto;
    }

    public PieChartModel getPiemodel() {
        return piemodel;
    }

    public void setPiemodel(PieChartModel piemodel) {
        this.piemodel = piemodel;
    }
    
    
    @PostConstruct
    public void init(){
        listaProyectosVendidos = pfl.cantidaddeproyectosvendidos();
        listaProyectosdosmilquince = pfl.cantidaddeproyectosvendidosenaños();
        System.out.println(listaProyectosVendidos);
        
        Reporte(listaProyectosVendidos, listaProyectosdosmilquince);
    }
    
    public void Reporte(List<Proyecto> listaProyectosVendidos, List<Proyecto> listaProyectosdosmilquince){
        
        piemodel = new PieChartModel();
        
        piemodel.set("Año 2014", listaProyectosVendidos.size());
        piemodel.set("Año 2015", listaProyectosdosmilquince.size());
        
        
         piemodel.setTitle("Proyectos vendidos");
        piemodel.setLegendPosition("e");
        piemodel.setFill(false);
        piemodel.setShowDataLabels(true);
        piemodel.setDiameter(150);
         
    }
    
}
