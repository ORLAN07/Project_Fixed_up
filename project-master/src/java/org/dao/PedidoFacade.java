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
import org.entidades.Pedido;
import org.entidades.Persona;
import org.entidades.Proyecto;
import org.entidades.Rol;

/**
 *
 * @author David
 */
@Stateless
public class PedidoFacade extends AbstractFacade<Pedido> implements PedidoFacadeLocal {

    @PersistenceContext(unitName = "fixed_up_prototipo_v2PU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public PedidoFacade() {
        super(Pedido.class);
    }

    @Override
    public List<Persona> buscarOperarios() {
        List<Persona> operariosDisponibles = new ArrayList<>();
        Rol rol = new Rol(4);
        try {
            TypedQuery<Persona> q = getEntityManager().createNamedQuery("Persona.findOperarios", Persona.class);
            q.setParameter("rol", rol);
            operariosDisponibles = q.getResultList();

        } catch (Exception e) {
            e.printStackTrace();

        }
        return operariosDisponibles;

    }

    @Override
    public List<Pedido> listarPedidosVendedorAsignados(Persona p) {
        List<Pedido> listaPedidos = new ArrayList<>();
        Persona persona = new Persona();
        persona.setIdPersona(p.getIdPersona());
        
        try {
            TypedQuery<Pedido> q = getEntityManager().createNamedQuery("Pedido.findByVendedorAsignado", Pedido.class);
            q.setParameter("vendedorIdPersona", persona);
            listaPedidos = q.getResultList();
        
        } catch (Exception e) {
            e.printStackTrace();
            
        }
        
        return listaPedidos;

    }
    
    
    
    @Override
    public List<Pedido> pedidosSinPagar(int realizo_pago) {
        List<Pedido> listaproyectossinpagar = null;
        
        try {
            TypedQuery<Pedido> q = getEntityManager().createNamedQuery("Pedido.findByRealizoPago", Pedido.class);
            q.setParameter("realizoPago", realizo_pago);
            listaproyectossinpagar= q.getResultList();
            
        } catch (Exception e) {
            e.printStackTrace();
        }
        
        return listaproyectossinpagar;
    }

    

}
