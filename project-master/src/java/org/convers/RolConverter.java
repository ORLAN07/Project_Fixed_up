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
import org.dao.PersonaFacadeLocal;
import org.dao.RolFacadeLocal;
import org.entidades.Rol;

/**
 *
 * @author David
 */
@FacesConverter(value = "rolConverter")
public class RolConverter implements Converter {

    
    private RolFacadeLocal rfl;

    public RolConverter() {
        rfl = CDI.current().select(RolFacadeLocal.class).get();

    }

    @Override
    public Object getAsObject(FacesContext context, UIComponent component, String value) {
        if (value != null && !value.equals("")) {
            try {
                int idRol = Integer.valueOf(value);
                return rfl.find(idRol);

            } catch (NumberFormatException numberFormatException) {
                return "";

            }
        }
        return "";

    }

    @Override
    public String getAsString(FacesContext context, UIComponent component, Object value) {

        if (value != null && value instanceof Rol) {
            return ((Rol) value).getIdRol().toString();

        }
        return "";

    }

}