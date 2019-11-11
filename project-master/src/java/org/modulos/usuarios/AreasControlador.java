/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.modulos.usuarios;

import java.util.List;
import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.inject.Named;
import javax.enterprise.context.RequestScoped;
import org.dao.AreaFacadeLocal;
import org.entidades.Area;

/**
 *
 * @author David
 */
@Named(value = "areasControlador")
@RequestScoped
public class AreasControlador {

    @EJB
    private AreaFacadeLocal afl;
    private List<Area> areaLista;

    public AreasControlador() {
    }

    @PostConstruct
    public void init() {
        areaLista = afl.findAll();
    }

    public List<Area> getAreaLista() {
        return areaLista;
    }

}
