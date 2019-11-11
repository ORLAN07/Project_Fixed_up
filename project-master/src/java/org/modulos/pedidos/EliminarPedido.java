/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.modulos.pedidos;

import java.io.Serializable;
import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.inject.Named;
import javax.faces.view.ViewScoped;
import org.dao.PedidoFacadeLocal;
import org.entidades.Pedido;

/**
 *
 * @author David
 */
@Named(value = "eliminarPedido")
@ViewScoped
public class EliminarPedido implements Serializable{

    @EJB
    private PedidoFacadeLocal pedidoFacadeLocal;
    private Pedido pedidoSeleccionado;

    @PostConstruct
    public void init() {
        pedidoSeleccionado = new Pedido();

    }

    public Pedido getPedidoSeleccionado() {
        return pedidoSeleccionado;
    }

    public void setPedidoSeleccionado(Pedido pedidoSeleccionado) {
        this.pedidoSeleccionado = pedidoSeleccionado;
    }

    public void preparacionEliminar(Pedido p) {
        pedidoSeleccionado = p;
        System.out.printf("se va a eliminar: %s", pedidoSeleccionado.getNombreProyecto());

    }

    public String eliminarPedido() {
        pedidoFacadeLocal.remove(pedidoSeleccionado);
        return "/admin/pedidos/listarPedidos.xhtml?faces-redirect=true";
    }

}
