/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.dao;

import java.util.ArrayList;
import java.util.List;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import org.entidades.Persona;
import org.entidades.Estado;
import org.entidades.Proyecto;

/**
 *
 * @author Orlando
 */
@Stateless
public class ProyectoFacade extends AbstractFacade<Proyecto> implements ProyectoFacadeLocal {

    @PersistenceContext(unitName = "fixed_up_prototipo_v2PU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public ProyectoFacade() {
        super(Proyecto.class);
    }

    public List<Proyecto> listarProyectosOperariosAsignadosIdSeis(){
        List<Proyecto> listarProyectosSeis = new ArrayList<>();
        
        Persona persona = new Persona(6);
        
        try {
            TypedQuery<Proyecto> q = getEntityManager().createNamedQuery("Proyecto.findByOperarioAsignado", Proyecto.class);
            q.setParameter("operarioIdPersona", persona);
            listarProyectosSeis = q.getResultList();
        } catch (Exception e) {
            e.printStackTrace();
            
        }
        return listarProyectosSeis;
    }
    
    public List<Proyecto> listarProyectosOperariosAsignados(Persona p) {
        List<Proyecto> listaProyectos = new ArrayList<>();
        Persona persona = new Persona();
        persona.setIdPersona(p.getIdPersona());

        try {
            TypedQuery<Proyecto> q = getEntityManager().createNamedQuery("Proyecto.findByOperarioAsignado", Proyecto.class);
            q.setParameter("operarioIdPersona", persona);
            listaProyectos = q.getResultList();

        } catch (Exception e) {
            e.printStackTrace();

        }

        return listaProyectos;

    }

    public List<Proyecto> listarProyectosOperariosAsignadosIdCinco() {
        List<Proyecto> listaProyectos = new ArrayList<>();
        Persona persona = new Persona(7);
        

        try {
            TypedQuery<Proyecto> q = getEntityManager().createNamedQuery("Proyecto.findByOperarioAsignado", Proyecto.class);
            q.setParameter("operarioIdPersona", persona);
            listaProyectos = q.getResultList();

        } catch (Exception e) {
            e.printStackTrace();

        }

        return listaProyectos;

    }

    public List<Proyecto> buscarProyectosTerminados() {

        List<Proyecto> proyectosTerminados = new ArrayList<>();

        Estado estado = new Estado(3);

        try {
            TypedQuery<Proyecto> q = getEntityManager().createNamedQuery("proyectosTerminados", Proyecto.class);
            q.setParameter("estado", estado);

            proyectosTerminados = q.getResultList();

        } catch (Exception e) {
            e.printStackTrace();
        }
        return proyectosTerminados;
    }

    public List<Proyecto> buscarProyectosIniciados() {

        List<Proyecto> proyectosIniciados = new ArrayList<>();

        Estado estado = new Estado(1);

        try {
            TypedQuery<Proyecto> q = getEntityManager().createNamedQuery("ProyectosIniciados", Proyecto.class);
            q.setParameter("estado", estado);

            proyectosIniciados = q.getResultList();

        } catch (Exception e) {
            e.printStackTrace();
        }
        return proyectosIniciados;
    }

    public List<Proyecto> buscarProyectosPausados() {

        List<Proyecto> proyectosPausados = new ArrayList<>();

        Estado estado = new Estado(2);

        try {
            TypedQuery<Proyecto> q = getEntityManager().createNamedQuery("ProyectosPausados", Proyecto.class);
            q.setParameter("estado", estado);

            proyectosPausados = q.getResultList();

        } catch (Exception e) {
            e.printStackTrace();
        }
        return proyectosPausados;
    }

    @Override
    public List<Proyecto> buscarProyectosSinComenzar() {

        List<Proyecto> proyectosSinComenzar = new ArrayList<>();

        Estado estado = new Estado(5);

        try {
            TypedQuery<Proyecto> q = getEntityManager().createNamedQuery("ProyectosSinComenzar", Proyecto.class);
            q.setParameter("estado", estado);

            proyectosSinComenzar = q.getResultList();

        } catch (Exception e) {
            e.printStackTrace();
        }
        return proyectosSinComenzar;

    }

    @Override
    public List<Proyecto> buscarProyectosConNovedad() {

        List<Proyecto> ProyectosConNovedad = new ArrayList<>();

        Estado estado = new Estado(4);

        try {
            TypedQuery<Proyecto> q = getEntityManager().createNamedQuery("ProyectosConNovedad", Proyecto.class);
            q.setParameter("estado", estado);

            ProyectosConNovedad = q.getResultList();

        } catch (Exception e) {
            e.printStackTrace();
        }
        return ProyectosConNovedad;
    }

    @Override
    public List<Proyecto> findByfechaFinalizado() {
        List<Proyecto> listaProyecto = new ArrayList<>();
        try {
            TypedQuery<Proyecto> q = getEntityManager().createNamedQuery("Proyecto.findVencidos", Proyecto.class);
            listaProyecto = q.getResultList();

        } catch (Exception e) {
            e.printStackTrace();
        }
        return listaProyecto;
    }

    @Override
    public List<Proyecto> cantidaddeproyectosvendidos() {
        List<Proyecto> proyectosvendidos = new ArrayList<>();

        try {
            TypedQuery<Proyecto> q = getEntityManager().createNamedQuery("Proyectosvendidos", Proyecto.class);
            proyectosvendidos = q.getResultList();

        } catch (Exception e) {
        }
        return proyectosvendidos;
    }

    @Override
    public List<Proyecto> cantidaddeproyectosvendidosenaños() {
        List<Proyecto> Proyectosvendidosenelaño = new ArrayList<>();

        try {
            TypedQuery<Proyecto> q = getEntityManager().createNamedQuery("Proyectosvendidosenelaño", Proyecto.class);
            Proyectosvendidosenelaño = q.getResultList();
        } catch (Exception e) {

        }

        return Proyectosvendidosenelaño;
    }

    @Override
    public List<Proyecto> cantidadDeproyectosVendidosEnDosMilDiesiseis() {
        List<Proyecto> proyectosVendidosEnDosMilDiesiseis = new ArrayList<>();

        try {
            TypedQuery<Proyecto> q = getEntityManager().createNamedQuery("ProyectosVendidosEnDosMilDiesiseis", Proyecto.class);
            proyectosVendidosEnDosMilDiesiseis = q.getResultList();

        } catch (Exception e) {
        }

        return proyectosVendidosEnDosMilDiesiseis;
    }

    @Override
    public List<Proyecto> cantidadDeproyectosVendidosEnDosMilDiesisiete() {
        List<Proyecto> cantidadDeproyectosVendidosEnDosMilDiesisiete = new ArrayList<>();

        try {
            TypedQuery<Proyecto> q = getEntityManager().createNamedQuery("cantidadDeproyectosVendidosEnDosMilDiesisiete", Proyecto.class);
            cantidadDeproyectosVendidosEnDosMilDiesisiete = q.getResultList();

        } catch (Exception e) {
        }

        return cantidadDeproyectosVendidosEnDosMilDiesisiete;
    }

}
