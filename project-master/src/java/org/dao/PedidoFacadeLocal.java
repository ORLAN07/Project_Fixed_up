/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.dao;

import java.util.List;
import javax.ejb.Local;
import org.entidades.Pedido;
import org.entidades.Persona;
import org.entidades.Proyecto;

/**
 *
 * @author David
 */
@Local
public interface PedidoFacadeLocal {

    void create(Pedido pedido);

    void edit(Pedido pedido);

    void remove(Pedido pedido);

    Pedido find(Object id);

    List<Pedido> findAll();

    List<Pedido> findRange(int[] range);

    int count();
    
    List<Persona> buscarOperarios();
    
    List<Pedido> pedidosSinPagar(int realizo_pago);
    
    List<Pedido>listarPedidosVendedorAsignados(Persona p);
    
}
