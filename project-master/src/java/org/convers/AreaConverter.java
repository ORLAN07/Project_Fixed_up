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
import org.entidades.Area;

/**
 *
 * @author David
 */
@FacesConverter(value = "areaConverter")
public class AreaConverter implements Converter{

    private AreaFacadeLocal afl;
    
    public AreaConverter() {
        afl = CDI.current().select(AreaFacadeLocal.class).get();
    }
    
    @Override
    public Object getAsObject(FacesContext context, UIComponent component, String value) {
        if(value != null && value.length() > 0){
            return afl.find(Integer.valueOf(value));
        }
        return  null;
    }

    @Override
    public String getAsString(FacesContext context, UIComponent component, Object value) {
        if(value != null && value instanceof Area){
            Area area = (Area) value;
            return area.getNombreArea();
        
        }
        return  null;
    }
    
}
