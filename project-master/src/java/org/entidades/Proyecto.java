/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.entidades;

import java.io.Serializable;
import java.util.Date;
import javax.persistence.Basic;
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
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.NotNull;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author Orlando
 */
@Entity
@Table(name = "proyectos")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Proyecto.findAll", query = "SELECT p FROM Proyecto p")
    , @NamedQuery(name = "Proyecto.findByIdProyecto", query = "SELECT p FROM Proyecto p WHERE p.idProyecto = :idProyecto")
    , @NamedQuery(name = "Proyecto.findByFechaInicio", query = "SELECT p FROM Proyecto p WHERE p.fechaInicio = :fechaInicio")
    , @NamedQuery(name = "Proyecto.findByfechaFinalizado", query = "SELECT p FROM Proyecto p WHERE p.fechaFinalizado = :fechaFinalizado")
    , @NamedQuery(name = "Proyecto.findByOperarioAsignado", query = "SELECT p FROM Proyecto p WHERE p.operarioIdPersona = :operarioIdPersona")
    , @NamedQuery(name = "Proyecto.findVencidos", query = "SELECT p FROM Proyecto p WHERE p.estadosIdEstado.idEstado != 5 AND p.tiempoEstimado <= CURRENT_DATE AND p.correoNotificacionEnviado = 0")
    , @NamedQuery(name = "proyectosTerminados", query = "SELECT p FROM Proyecto p WHERE p.estadosIdEstado = :estado")
    , @NamedQuery(name = "ProyectosIniciados", query = "SELECT p FROM Proyecto p WHERE p.estadosIdEstado = :estado")
    , @NamedQuery(name = "ProyectosPausados", query = "SELECT p FROM Proyecto p WHERE p.estadosIdEstado = :estado")
    , @NamedQuery(name = "ProyectosSinComenzar", query = "SELECT p FROM Proyecto p WHERE p.estadosIdEstado = :estado")
    , @NamedQuery(name = "ProyectosConNovedad", query = "SELECT p FROM Proyecto p WHERE p.estadosIdEstado = :estado")
    , @NamedQuery(name = "Proyectosvendidos", query = "SELECT p FROM Proyecto p WHERE p.fechaInicio BETWEEN '2014-01-01' AND '2014-12-01'")
    , @NamedQuery(name = "Proyectosvendidosenelaño", query = "SELECT p FROM Proyecto p WHERE p.fechaInicio BETWEEN '2015-01-01' AND '2015-12-01'")
    , @NamedQuery(name = "ProyectosVendidosEnDosMilDiesiseis", query = "SELECT p FROM Proyecto p WHERE p.fechaInicio BETWEEN '2016-01-01' AND '2016-12-01'")
    , @NamedQuery(name = "cantidadDeproyectosVendidosEnDosMilDiesisiete", query = "SELECT p FROM Proyecto p WHERE p.fechaInicio BETWEEN '2017-01-01' AND '2017-12-01'")
    , @NamedQuery(name = "Proyecto.findByTiempoEstimado", query = "SELECT p FROM Proyecto p WHERE p.tiempoEstimado = :tiempoEstimado")})
public class Proyecto implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id_proyecto")
    private Integer idProyecto;
    @Basic(optional = false)
    @Column(name = "fecha_inicio")
    @Temporal(TemporalType.TIMESTAMP)
    private Date fechaInicio;
    @Basic(optional = false)
    @Column(name = "fecha_finalizado")
    @Temporal(TemporalType.TIMESTAMP)
    private Date fechaFinalizado;
    @Basic(optional = false)
    @Column(name = "tiempo_estimado")
    @Temporal(TemporalType.TIMESTAMP)
    private Date tiempoEstimado;
    @NotNull
    @Basic(optional = false)
    @Column(name = "correo_notificacion_enviado")
    private Integer correoNotificacionEnviado;
    @JoinColumn(name = "dificultades_id_dificultad", referencedColumnName = "id_dificultad")
    @ManyToOne(optional = false, fetch = FetchType.EAGER)
    private Dificultad dificultadesIdDificultad;
    @JoinColumn(name = "estados_id_estado", referencedColumnName = "id_estado")
    @ManyToOne(optional = false, fetch = FetchType.EAGER)
    private Estado estadosIdEstado;
    @JoinColumn(name = "pedidos_id_pedido", referencedColumnName = "id_pedido")
    @ManyToOne(optional = false, fetch = FetchType.EAGER)
    private Pedido pedidosIdPedido;
    @JoinColumn(name = "operario_id_persona", referencedColumnName = "id_persona")
    @ManyToOne(optional = false, fetch = FetchType.EAGER)
    private Persona operarioIdPersona;

    public Proyecto() {
    }

    public Proyecto(Integer idProyecto) {
        this.idProyecto = idProyecto;
    }

    public Proyecto(Integer idProyecto, Date fechaInicio, Date tiempoEstimado) {
        this.idProyecto = idProyecto;
        this.fechaInicio = fechaInicio;
        this.tiempoEstimado = tiempoEstimado;
    }

    public Integer getIdProyecto() {
        return idProyecto;
    }

    public void setIdProyecto(Integer idProyecto) {
        this.idProyecto = idProyecto;
    }

    public Date getFechaInicio() {
        return fechaInicio;
    }

    public void setFechaInicio(Date fechaInicio) {
        this.fechaInicio = fechaInicio;
    }

    public Date getFechaFinalizado() {
        return fechaFinalizado;
    }

    public void setFechaFinalizado(Date fechaFinalizado) {
        this.fechaFinalizado = fechaFinalizado;
    }
    
    public Date getTiempoEstimado() {
        return tiempoEstimado;
    }

    public void setTiempoEstimado(Date tiempoEstimado) {
        this.tiempoEstimado = tiempoEstimado;
    }

    public Integer getCorreoNotificacionEnviado() {
        return correoNotificacionEnviado;
    }

    public void setCorreoNotificacionEnviado(Integer correoNotificacionEnviado) {
        this.correoNotificacionEnviado = correoNotificacionEnviado;
    }

    public Dificultad getDificultadesIdDificultad() {
        return dificultadesIdDificultad;
    }

    public void setDificultadesIdDificultad(Dificultad dificultadesIdDificultad) {
        this.dificultadesIdDificultad = dificultadesIdDificultad;
    }

    public Estado getEstadosIdEstado() {
        return estadosIdEstado;
    }

    public void setEstadosIdEstado(Estado estadosIdEstado) {
        this.estadosIdEstado = estadosIdEstado;
    }

    public Pedido getPedidosIdPedido() {
        return pedidosIdPedido;
    }

    public void setPedidosIdPedido(Pedido pedidosIdPedido) {
        this.pedidosIdPedido = pedidosIdPedido;
    }

    public Persona getOperarioIdPersona() {
        return operarioIdPersona;
    }

    public void setOperarioIdPersona(Persona operarioIdPersona) {
        this.operarioIdPersona = operarioIdPersona;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idProyecto != null ? idProyecto.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Proyecto)) {
            return false;
        }
        Proyecto other = (Proyecto) object;
        if ((this.idProyecto == null && other.idProyecto != null) || (this.idProyecto != null && !this.idProyecto.equals(other.idProyecto))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "org.entidades.Proyecto[ idProyecto=" + idProyecto + " ]";
    }
    
}
