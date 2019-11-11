/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.dao;

import java.util.List;
import javax.ejb.Local;
import org.entidades.Persona;
import org.entidades.Proyecto;

/**
 *
 * @author Orlando
 */
@Local
public interface ProyectoFacadeLocal {

    void create(Proyecto proyecto);

    void edit(Proyecto proyecto);

    void remove(Proyecto proyecto);

    Proyecto find(Object id);

    List<Proyecto> findAll();

    List<Proyecto> findRange(int[] range);

    int count();
    
    List<Proyecto>listarProyectosOperariosAsignadosIdCinco();
    
    List<Proyecto>listarProyectosOperariosAsignados(Persona p);
    
    List<Proyecto>listarProyectosOperariosAsignadosIdSeis();
    
    List<Proyecto> buscarProyectosTerminados();
    
    List<Proyecto> buscarProyectosIniciados();
    
    List<Proyecto> buscarProyectosPausados();
    
    List<Proyecto> buscarProyectosSinComenzar();
    
    List<Proyecto> buscarProyectosConNovedad();
    
    List<Proyecto> cantidaddeproyectosvendidos();
    
    List<Proyecto> cantidaddeproyectosvendidosenaños();
    
    List<Proyecto> cantidadDeproyectosVendidosEnDosMilDiesiseis();
    
    List<Proyecto> cantidadDeproyectosVendidosEnDosMilDiesisiete();
    
   


    public List<Proyecto> findByfechaFinalizado();
    
   

}
