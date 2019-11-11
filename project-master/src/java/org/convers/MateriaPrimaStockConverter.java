/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.convers;

import javax.enterprise.inject.spi.CDI;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.FacesConverter;
import org.dao.AreaFacadeLocal;
import org.dao.StockFacadeLocal;
import org.entidades.Area;
import org.entidades.MateriaPrima;

/**
 *
 * @author David
 */
@FacesConverter(value = "materiaPrimaStockConverter")
public class MateriaPrimaStockConverter implements Converter{

    private StockFacadeLocal stockFacadeLocal;
    
    public MateriaPrimaStockConverter() {
        stockFacadeLocal = CDI.current().select(StockFacadeLocal.class).get();
    }
    
    @Override
    public Object getAsObject(FacesContext context, UIComponent component, String value) {
        if(value != null && value.length() > 0){
            return stockFacadeLocal.find(Integer.valueOf(value));
        }
        return  null;
    }

    @Override
    public String getAsString(FacesContext context, UIComponent component, Object value) {
        System.out.println("entro");
        if(value != null && value instanceof MateriaPrima){
            System.out.println("es instancia");
            MateriaPrima materiaPrima = (MateriaPrima) value;
            return materiaPrima.getReferencia();
            
        }else{
                    System.out.println("no es instancia");
        }
        return  null;
    }
    
}
