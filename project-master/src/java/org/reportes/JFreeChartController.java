/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.reportes;

import org.reportes.GraficoRule;

import java.io.File;
import java.io.IOException;
import java.util.Locale;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.faces.context.FacesContext;
import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartUtilities;
import org.jfree.chart.JFreeChart;
import org.jfree.data.general.DefaultPieDataset;

import javax.inject.Named;
import javax.enterprise.context.RequestScoped;
import javax.ejb.EJB;
import javax.annotation.PostConstruct;
/**
 *
 * @author Ismael
 */
@Named(value = "jFreeChartController")
@RequestScoped
public class JFreeChartController {

    @EJB
    private GraficoRule gr;
    /**
     * Creates a new instance of JFreeChartController
     */
    public JFreeChartController() {
    }
    

    @PostConstruct
    public void init(){
        generarGrafico();
    }
    public String getGrafico() {
        return gr.getGraficoPruebaDato();
    }
    public void generarGrafico() {
        try {
            FacesContext fc = FacesContext.getCurrentInstance();
            DefaultPieDataset dataset = new DefaultPieDataset();
            dataset.setValue("Acrilicos rojos", 10);
            dataset.setValue("Acrilicos azules", 15);
            dataset.setValue("Acrilicos verde", 8);
            dataset.setValue("Acrilicos amarillo", 12.5);
            dataset.setValue("Acrilicos fucsia", 30);
           
            JFreeChart chart = ChartFactory.createPieChart("Acrilicos", dataset, true, true, Locale.ENGLISH);
            File f = new File(fc.getExternalContext().getRealPath("/") + "img.png");
            ChartUtilities.saveChartAsPNG(f, chart, 300, 300);
        } catch (IOException ex) {
            Logger.getLogger(JFreeChartController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }


}
