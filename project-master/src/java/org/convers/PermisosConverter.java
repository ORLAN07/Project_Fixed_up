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
import org.dao.PermisoFacadeLocal;
import org.dao.RolFacadeLocal;
import org.entidades.Permiso;

/**
 *
 * @author David
 */
@FacesConverter(value = "permisosConverter")
public class PermisosConverter implements Converter {

    private PermisoFacadeLocal permisoFacadeLocal;

    public PermisosConverter() {
        permisoFacadeLocal = CDI.current().select(PermisoFacadeLocal.class).get();

    }

    @Override
    public Object getAsObject(FacesContext context, UIComponent component, String value) {

        if (value != null && !value.equals("")) {
            System.out.println(value);
            return permisoFacadeLocal.find(Integer.valueOf(value));

        } else {
            return null;

        }

    }

    @Override
    public String getAsString(FacesContext context, UIComponent component, Object value) {
        if (value != null && value instanceof Permiso) {
            Permiso permiso = (Permiso) value;
            return permiso.getIdPermiso().toString();

        } else {
            System.out.println("noooooo");
            return null;
        }

    }

}
