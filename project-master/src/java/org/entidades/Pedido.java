/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.entidades;

import java.io.Serializable;
import java.util.List;
import javax.persistence.Basic;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

/**
 *
 * @author David
 */
@Entity
@Table(name = "pedidos")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Pedido.findAll", query = "SELECT p FROM Pedido p ORDER BY  p.idPedido ")
    , @NamedQuery(name = "Pedido.findByIdPedido", query = "SELECT p FROM Pedido p WHERE p.idPedido = :idPedido")
    , @NamedQuery(name = "Pedido.findByNombreProyecto", query = "SELECT p FROM Pedido p WHERE p.nombreProyecto = :nombreProyecto")
    , @NamedQuery(name = "Pedido.findByVendedorAsignado", query = "SELECT p FROM Pedido p WHERE p.vendedorIdPersona = :vendedorIdPersona ORDER BY p.idPedido DESC")
    , @NamedQuery(name = "Pedido.findByDescripcion", query = "SELECT p FROM Pedido p WHERE p.descripcion = :descripcion")
    , @NamedQuery(name = "Pedido.findByRealizoPago", query = "SELECT p FROM Pedido p WHERE p.realizoPago = :realizoPago")
    , @NamedQuery(name = "Pedido.findByCantidad", query = "SELECT p FROM Pedido p WHERE p.cantidad = :cantidad")})
public class Pedido implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id_pedido")
    private Integer idPedido;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 30)
    @Column(name = "nombre_proyecto")
    private String nombreProyecto;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 50)
    @Column(name = "descripcion")
    private String descripcion;
    @Basic(optional = false)
    @NotNull
    @Column(name = "realizo_pago")
    private short realizoPago;
    @Basic(optional = false)
    @NotNull
    @Column(name = "esta_asignado")
    private int estaAsignado;
    @Basic(optional = false)
    @NotNull
    @Column(name = "cantidad")
    private int cantidad;
    @Basic(optional = false)
    @NotNull
    @Column(name = "nombre_cliente")
    private String nombreCliente;
    @Basic(optional = false)
    @NotNull
    @Column(name = "telefono_cliente")
    private String telefonoCliente;
    @Basic(optional = false)
    @NotNull
    @Column(name = "correo_cliente")
    private String correoCliente;
    @Basic(optional = false)
    @Column(name = "movimiento_proyecto")
    private String movimientoProyecto;
    @JoinColumn(name = "materias_prima_id_materia", referencedColumnName = "id_materia")
    @ManyToOne(optional = false, fetch = FetchType.EAGER)
    private MateriaPrima materiasPrimaIdMateria;
    @JoinColumn(name = "vendedor_id_persona", referencedColumnName = "id_persona")
    @ManyToOne(optional = false, fetch = FetchType.EAGER)
    private Persona vendedorIdPersona;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "pedidosIdPedido", fetch = FetchType.EAGER)
    private List<Proyecto> proyectoList;

    public Pedido() {
    }

    public Pedido(Integer idPedido) {
        this.idPedido = idPedido;
    }

    public Pedido(Integer idPedido, String nombreProyecto, String descripcion, short realizoPago, int cantidad) {
        this.idPedido = idPedido;
        this.nombreProyecto = nombreProyecto;
        this.descripcion = descripcion;
        this.realizoPago = realizoPago;
        this.cantidad = cantidad;
    }
    
    public Integer getIdPedido() {
        return idPedido;
    }

    public void setIdPedido(Integer idPedido) {
        this.idPedido = idPedido;
    }

    public String getNombreProyecto() {
        return nombreProyecto;
    }

    public void setNombreProyecto(String nombreProyecto) {
        this.nombreProyecto = nombreProyecto;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public short getRealizoPago() {
        return realizoPago;
    }

    public void setRealizoPago(short realizoPago) {
        this.realizoPago = realizoPago;
    }

    public int getEstaAsignado() {
        return estaAsignado;
    }

    public void setEstaAsignado(int estaAsignado) {
        this.estaAsignado = estaAsignado;
    }

    public int getCantidad() {
        return cantidad;
    }

    public String getNombreCliente() {
        return nombreCliente;
    }

    public void setNombreCliente(String nombreCliente) {
        this.nombreCliente = nombreCliente;
    }

    public String getTelefonoCliente() {
        return telefonoCliente;
    }

    public void setTelefonoCliente(String telefonoCliente) {
        this.telefonoCliente = telefonoCliente;
    }

    public String getCorreoCliente() {
        return correoCliente;
    }

    public void setCorreoCliente(String correoCliente) {
        this.correoCliente = correoCliente;
    }
    
    public void setCantidad(int cantidad) {
        this.cantidad = cantidad;
    }

    public String getMovimientoProyecto() {
        return movimientoProyecto;
    }

    public void setMovimientoProyecto(String movimientoProyecto) {
        this.movimientoProyecto = movimientoProyecto;
    }

    public MateriaPrima getMateriasPrimaIdMateria() {
        return materiasPrimaIdMateria;
    }

    public void setMateriasPrimaIdMateria(MateriaPrima materiasPrimaIdMateria) {
        this.materiasPrimaIdMateria = materiasPrimaIdMateria;
    }

    public Persona getVendedorIdPersona() {
        return vendedorIdPersona;
    }

    public void setVendedorIdPersona(Persona vendedorIdPersona) {
        this.vendedorIdPersona = vendedorIdPersona;
    }

    @XmlTransient
    public List<Proyecto> getProyectoList() {
        return proyectoList;
    }

    public void setProyectoList(List<Proyecto> proyectoList) {
        this.proyectoList = proyectoList;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idPedido != null ? idPedido.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Pedido)) {
            return false;
        }
        Pedido other = (Pedido) object;
        if ((this.idPedido == null && other.idPedido != null) || (this.idPedido != null && !this.idPedido.equals(other.idPedido))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "org.entidades.Pedido[ idPedido=" + idPedido + " ]";
    }

    
}
