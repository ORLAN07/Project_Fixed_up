/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.controller.grafico.proyectos;

import java.io.Serializable;
import java.util.List;
import java.util.Locale;
import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.enterprise.context.RequestScoped;
import javax.inject.Named;
import javax.faces.view.ViewScoped;
import javax.inject.Inject;
import org.dao.ProyectoFacadeLocal;
import org.entidades.Proyecto;
import org.lang.Idioma;
import org.primefaces.model.chart.PieChartModel;

/**
 *
 * @author Orlando.R.R
 */
@Named(value = "graficaProyectos")
@RequestScoped
public class GraficaProyectos implements Serializable {

    @Inject
    private Idioma idioma;

    public GraficaProyectos() {
    }

    @EJB
    private ProyectoFacadeLocal pfl;
    List<Proyecto> ProyectosTerminados;
    List<Proyecto> ProyectosIniciados;
    List<Proyecto> ProyectosPausados;
    List<Proyecto> ProyectosSinComenzar;
    List<Proyecto> ProyectosConNovedad;
    private PieChartModel piemodel;
    private Proyecto proyecto;

    public ProyectoFacadeLocal getPfl() {
        return pfl;
    }

    public void setPfl(ProyectoFacadeLocal pfl) {
        this.pfl = pfl;
    }

    public List<Proyecto> getProyectosTerminados() {
        return ProyectosTerminados;
    }

    public void setProyectosTerminados(List<Proyecto> ProyectosTerminados) {
        this.ProyectosTerminados = ProyectosTerminados;
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

    @PostConstruct
    public void init() {
        ProyectosTerminados = pfl.buscarProyectosTerminados();
        ProyectosIniciados = pfl.buscarProyectosIniciados();
        ProyectosPausados = pfl.buscarProyectosPausados();
        ProyectosSinComenzar = pfl.buscarProyectosSinComenzar();
        ProyectosConNovedad = pfl.buscarProyectosConNovedad();
        reporte(ProyectosTerminados, ProyectosIniciados, ProyectosPausados, ProyectosSinComenzar, ProyectosConNovedad);

    }

    public void reporte(List<Proyecto> ProyectosTerminados, List<Proyecto> ProyectosIniciados, List<Proyecto> ProyectosPausados, List<Proyecto> ProyectosSinComenzar, List<Proyecto> ProyectosConNovedad) {

        piemodel = new PieChartModel();
        String proyectoTerminado,
                proyectoIniciado,
                proyectoPausado,
                protectoSinComenzar,
                proyectoNovedad,
                proyectoEstado;
        
        if (idioma.getLanguageSelect().getLanguage().startsWith("es")) {
            proyectoTerminado = "Proyectos terminados";
            proyectoIniciado = "Proyectos Iniciados";
            proyectoPausado = "Proyectos Pausados";
            protectoSinComenzar = "Proyectos sin comenzar";
            proyectoNovedad = "Proyectos con novedad";
            proyectoEstado = "Estados de proyectos";
        } else {
            proyectoTerminado = "Projects finished";
            proyectoIniciado = "Projects started";
            proyectoPausado = "Projects paused";
            protectoSinComenzar = "Projects not begin";
            proyectoNovedad = "Projects with news";
            proyectoEstado = "States of proyects";
        }

        System.out.println(idioma.getLanguageSelect());
        piemodel.set(proyectoTerminado, ProyectosTerminados.size());
        piemodel.set(proyectoIniciado, ProyectosIniciados.size());
        piemodel.set(proyectoPausado, ProyectosPausados.size());
        piemodel.set(protectoSinComenzar, ProyectosSinComenzar.size());
        piemodel.set(proyectoNovedad, ProyectosConNovedad.size());

        piemodel.setTitle(proyectoEstado);
        piemodel.setLegendPosition("e");
        piemodel.setFill(false);
        piemodel.setShowDataLabels(true);
        piemodel.setDiameter(150);
    }
}
