/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.controller.grafico.proyectos;

import java.util.List;
import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.inject.Named;
import javax.enterprise.context.RequestScoped;
import org.dao.ProyectoFacadeLocal;
import org.entidades.Proyecto;
import org.primefaces.model.chart.Axis;
import org.primefaces.model.chart.AxisType;
import org.primefaces.model.chart.BarChartModel;
import org.primefaces.model.chart.ChartSeries;
import org.primefaces.model.chart.PieChartModel;

/**
 *
 * @author orlan
 */
@Named(value = "graficoProyectoOperario")
@RequestScoped
public class GraficoProyectoOperario {

    
    public GraficoProyectoOperario() {
    }
    @EJB
    private ProyectoFacadeLocal pfl;
    
    private PieChartModel piemodel;
    
    private BarChartModel barModel;
    
    private Proyecto proyecto;
    
    
    List<Proyecto> proyectosIdCinco;
    List<Proyecto> proyectosIdSeis;

    public BarChartModel getBarModel() {
        return barModel;
    }

    public void setBarModel(BarChartModel barModel) {
        this.barModel = barModel;
    }

    
    
    public ProyectoFacadeLocal getPfl() {
        return pfl;
    }

    public void setPfl(ProyectoFacadeLocal pfl) {
        this.pfl = pfl;
    }

    public PieChartModel getPiemodel() {
        return piemodel;
    }

    public void setPiemodel(PieChartModel piemodel) {
        this.piemodel = piemodel;
    }

    public Proyecto getProyecto() {
        return proyecto;
    }

    public void setProyecto(Proyecto proyecto) {
        this.proyecto = proyecto;
    }

    public List<Proyecto> getProyectosIdCinco() {
        return proyectosIdCinco;
    }

    public void setProyectosIdCinco(List<Proyecto> proyectosIdCinco) {
        this.proyectosIdCinco = proyectosIdCinco;
    }

    public List<Proyecto> getProyectosIdSeis() {
        return proyectosIdSeis;
    }

    public void setProyectosIdSeis(List<Proyecto> proyectosIdSeis) {
        this.proyectosIdSeis = proyectosIdSeis;
    }
    
    @PostConstruct
    public void init(){
        proyectosIdCinco = pfl.listarProyectosOperariosAsignadosIdCinco();
        proyectosIdSeis = pfl.listarProyectosOperariosAsignadosIdSeis();
        
        createBarModels();
        /*Reporte(proyectosIdCinco, proyectosIdSeis);*/
    }
    
   /* public void Reporte(List<Proyecto> proyectosIdCinco, List<Proyecto> proyectosIdSeis){
        
        piemodel = new PieChartModel();
        
        piemodel.set("Operario", proyectosIdCinco.size());
        piemodel.set("Operario", proyectosIdSeis.size());

        piemodel.setTitle("Tus proyectos asignados");
        piemodel.setLegendPosition("e");
        piemodel.setFill(false);
        piemodel.setShowDataLabels(true);
        piemodel.setDiameter(150);
    }*/
    
    private BarChartModel initBarModel() {
        BarChartModel model = new BarChartModel();

        

        ChartSeries idseis = new ChartSeries();
        idseis.setLabel("Acrilico");
        idseis.set("2017", proyectosIdSeis.size());

      

      
        model.addSeries(idseis);
        

        return model;
    }

    private void createBarModels() {
        createBarModel();
    }

    private void createBarModel() {
        barModel = initBarModel();

       

        barModel.setTitle("Proyectos asignados a Sofia henao");
        barModel.setLegendPosition("ne");

        Axis xAxis = barModel.getAxis(AxisType.X);
        xAxis.setLabel("Acrilico");

        Axis yAxis = barModel.getAxis(AxisType.Y);
        yAxis.setLabel("cantidad");
        yAxis.setMin(0);
        yAxis.setMax(10);
    }
    
    
    
}
