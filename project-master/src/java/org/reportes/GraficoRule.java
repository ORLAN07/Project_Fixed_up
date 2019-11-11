/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.reportes;

import javax.ejb.Stateless;
import org.jfree.data.general.DefaultPieDataset;

import org.reportes.JFreeChartController;
import org.reportes.Dato;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Base64;
import java.util.List;
import java.util.Locale;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartUtilities;
import org.jfree.chart.JFreeChart;
import org.jfree.data.general.DefaultPieDataset;

/**
 *
 * @author Ismael
 */
@Stateless
public class GraficoRule {

    public GraficoRule() {
    }
    
    public String getGraficoPruebaDato(){
        try {
            DefaultPieDataset dataset = getDatasetDatos(getDatosGraficoPrueba());
            JFreeChart chart = ChartFactory.createPieChart("Acrilicos", dataset, true, true, Locale.ENGLISH);
            BufferedImage bufferedImage = chart.createBufferedImage(300, 300);
            //return Base64.getEncoder().encodeToString(ChartUtilities.encodeAsPNG(bufferedImage));
            return "data:image/png;base64," + new String(Base64.getEncoder().encode(ChartUtilities.encodeAsPNG(bufferedImage)));
        } catch (IOException ex) {
            Logger.getLogger(JFreeChartController.class.getName()).log(Level.SEVERE, null, ex);
            return "";
        }
    }
    
    private List<Dato> getDatosGraficoPrueba(){
        List<Dato> datos = new ArrayList<>();
        //Consulta BD y Organización
        datos.add(new Dato("Acrilicos rojos", 10.0));
        datos.add(new Dato("Acrilicos azules", 15.0));
        datos.add(new Dato("Acrilicos verde", 20.0));
        datos.add(new Dato("Acrilicos amarillo", 5.0));
        datos.add(new Dato("Acrilicos fusia", 35.0));
        return datos;
    }
    
    private DefaultPieDataset getDatasetDatos(List<Dato> datos){
        DefaultPieDataset dataset = new DefaultPieDataset();
        for (Dato dato : datos) {
            dataset.setValue(dato.getNombre(), dato.getValor());
        }
        return dataset;
    }
    
}
