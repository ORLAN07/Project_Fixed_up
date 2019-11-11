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
import org.dao.EstadoFacadeLocal;
import org.dao.PersonaFacadeLocal;
import org.dao.RolFacadeLocal;
import org.entidades.Estado;
import org.entidades.Rol;

/**
 *
 * @author David
 */
@FacesConverter(value = "estadoConverter")
public class EstadoConverter implements Converter {

    
    private EstadoFacadeLocal estadoFacadeLocal;

    public EstadoConverter() {
        estadoFacadeLocal = CDI.current().select(EstadoFacadeLocal.class).get();

    }

    @Override
    public Object getAsObject(FacesContext context, UIComponent component, String value) {
        if (value != null && !value.equals("")) {
            try {
                int id = Integer.valueOf(value);
                return estadoFacadeLocal.find(id);

            } catch (NumberFormatException numberFormatException) {
                return null;

            }
        }
        return null;

    }

    @Override
    public String getAsString(FacesContext context, UIComponent component, Object value) {

        if (value != null && value instanceof Estado) {
            Estado estado = (Estado) value;
            return estado.getDescripcionEstado();

        }
        return null;

    }

}