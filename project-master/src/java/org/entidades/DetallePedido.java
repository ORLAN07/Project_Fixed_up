/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.entidades;

import java.io.Serializable;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author David
 */
@Entity
@Table(name = "detalle_pedidos")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "DetallePedido.findAll", query = "SELECT d FROM DetallePedido d")
    , @NamedQuery(name = "DetallePedido.findByIdDetalle", query = "SELECT d FROM DetallePedido d WHERE d.idDetalle = :idDetalle")
    , @NamedQuery(name = "DetallePedido.findByPedido", query = "SELECT d FROM DetallePedido d WHERE d.pedidosIdPedido = :idPedido")
    , @NamedQuery(name = "DetallePedido.findByCantidad", query = "SELECT d FROM DetallePedido d WHERE d.cantidad = :cantidad")
    , @NamedQuery(name = "DetallePedido.findByDimensionAlto", query = "SELECT d FROM DetallePedido d WHERE d.dimensionAlto = :dimensionAlto")
    , @NamedQuery(name = "DetallePedido.findByDimensionAncho", query = "SELECT d FROM DetallePedido d WHERE d.dimensionAncho = :dimensionAncho")})
public class DetallePedido implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id_detalle")
    private Integer idDetalle;
    @Basic(optional = false)
    @NotNull
    @Column(name = "cantidad")
    private int cantidad;
    @Basic(optional = false)
    @NotNull
    @Column(name = "dimension_alto")
    private double dimensionAlto;
    @Basic(optional = false)
    @NotNull
    @Column(name = "dimension_ancho")
    private double dimensionAncho;
    @JoinColumn(name = "pedidos_id_pedido", referencedColumnName = "id_pedido")
    @ManyToOne(optional = false)
    private Pedido pedidosIdPedido;

    public DetallePedido() {
    }

    public DetallePedido(Integer idDetalle) {
        this.idDetalle = idDetalle;
    }

    public DetallePedido(Integer idDetalle, int cantidad, double dimensionAlto, double dimensionAncho) {
        this.idDetalle = idDetalle;
        this.cantidad = cantidad;
        this.dimensionAlto = dimensionAlto;
        this.dimensionAncho = dimensionAncho;
    }

    public Integer getIdDetalle() {
        return idDetalle;
    }

    public void setIdDetalle(Integer idDetalle) {
        this.idDetalle = idDetalle;
    }

    public int getCantidad() {
        return cantidad;
    }

    public void setCantidad(int cantidad) {
        this.cantidad = cantidad;
    }

    public double getDimensionAlto() {
        return dimensionAlto;
    }

    public void setDimensionAlto(double dimensionAlto) {
        this.dimensionAlto = dimensionAlto;
    }

    public double getDimensionAncho() {
        return dimensionAncho;
    }

    public void setDimensionAncho(double dimensionAncho) {
        this.dimensionAncho = dimensionAncho;
    }

    public Pedido getPedidosIdPedido() {
        return pedidosIdPedido;
    }

    public void setPedidosIdPedido(Pedido pedidosIdPedido) {
        this.pedidosIdPedido = pedidosIdPedido;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idDetalle != null ? idDetalle.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof DetallePedido)) {
            return false;
        }
        DetallePedido other = (DetallePedido) object;
        if ((this.idDetalle == null && other.idDetalle != null) || (this.idDetalle != null && !this.idDetalle.equals(other.idDetalle))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "org.entidades.DetallePedido[ idDetalle=" + idDetalle + " ]";
    }
    
}
