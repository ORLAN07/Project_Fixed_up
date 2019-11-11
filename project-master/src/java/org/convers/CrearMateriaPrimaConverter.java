/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.convers;

import javax.ejb.EJB;
import javax.enterprise.inject.spi.CDI;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.FacesConverter;
import org.dao.MateriaPrimaFacadeLocal;
import org.dao.RolFacadeLocal;
import org.entidades.MateriaPrima;

/**
 *
 * @author David
 */
@FacesConverter(value = "crearMateriaPrimaConverter")
public class CrearMateriaPrimaConverter implements Converter {

    @EJB
    private MateriaPrimaFacadeLocal materiaPrimaFacadeLocal;

    public CrearMateriaPrimaConverter() {
        materiaPrimaFacadeLocal = CDI.current().select(MateriaPrimaFacadeLocal.class).get();

    }

    @Override
    public Object getAsObject(FacesContext context, UIComponent component, String value) {
        if (value != null && !value.equals("")) {
            try {
                int idMateriaPrima = Integer.valueOf(value);
                return materiaPrimaFacadeLocal.find(idMateriaPrima);

            } catch (NumberFormatException numberFormatException) {
                return "";

            }
        }
        return "";

    }

    @Override
    public String getAsString(FacesContext context, UIComponent component, Object value) {
        System.out.println(value);
        
        if (value != null && value instanceof MateriaPrima) {
            System.out.println("si es");
            return ((MateriaPrima) value).getReferencia();
        }else{
            System.out.println("no es");
            
        }
        return "";

    }

}