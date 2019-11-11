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
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author David
 */
@Entity
@Table(name = "notificaciones")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Notificacion.findAll", query = "SELECT n FROM Notificacion n")
    , @NamedQuery(name = "Notificacion.findById", query = "SELECT n FROM Notificacion n WHERE n.id = :id")
    , @NamedQuery(name = "Notificacion.findByAsunto", query = "SELECT n FROM Notificacion n WHERE n.asunto = :asunto")
    , @NamedQuery(name = "Notificacion.findByMensaje", query = "SELECT n FROM Notificacion n WHERE n.mensaje = :mensaje")
    , @NamedQuery(name = "Notificacion.findByFechaEnvio", query = "SELECT n FROM Notificacion n WHERE n.fechaEnvio = :fechaEnvio")
    , @NamedQuery(name = "Notificacion.findNotificacionesUsuarioValidado", query = "SELECT n FROM Notificacion n WHERE n.personasIdPersona = :personasIdPersona AND n.isLeido = 0")
    , @NamedQuery(name = "Notificacion.findNotificacionesUsuarioValidadoVista", query = "SELECT n FROM Notificacion n WHERE n.personasIdPersona = :personasIdPersona ORDER BY n.fechaEnvio DESC")
    , @NamedQuery(name = "Notificacion.updateMensajesLeidosPorPersona", query = "UPDATE Notificacion n SET n.isLeido = 1 WHERE n.personasIdPersona = :personasIdPersona")
    , @NamedQuery(name = "Notificacion.findByIsLeido", query = "SELECT n FROM Notificacion n WHERE n.isLeido = :isLeido")})
public class Notificacion implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id")
    private Integer id;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 100)
    @Column(name = "asunto")
    private String asunto;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 250)
    @Column(name = "mensaje")
    private String mensaje;
    @Basic(optional = false)
    @NotNull
    @Column(name = "fecha_envio")
    @Temporal(TemporalType.TIMESTAMP)
    private Date fechaEnvio;
    @Basic(optional = false)
    @NotNull
    @Column(name = "is_leido")
    private int isLeido;
    @JoinColumn(name = "personas_id_persona", referencedColumnName = "id_persona")
    @ManyToOne(optional = false, fetch = FetchType.EAGER)
    private Persona personasIdPersona;

    public Notificacion() {
    }

    public Notificacion(Integer id) {
        this.id = id;
    }

    public Notificacion(Integer id, String asunto, String mensaje, Date fechaEnvio, int isLeido) {
        this.id = id;
        this.asunto = asunto;
        this.mensaje = mensaje;
        this.fechaEnvio = fechaEnvio;
        this.isLeido = isLeido;
    }

    public Notificacion(Integer id, String asunto, String mensaje, Date fechaEnvio, int isLeido, Persona personasIdPersona) {
        this.id = id;
        this.asunto = asunto;
        this.mensaje = mensaje;
        this.fechaEnvio = fechaEnvio;
        this.isLeido = isLeido;
        this.personasIdPersona = personasIdPersona;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getAsunto() {
        return asunto;
    }

    public void setAsunto(String asunto) {
        this.asunto = asunto;
    }

    public String getMensaje() {
        return mensaje;
    }

    public void setMensaje(String mensaje) {
        this.mensaje = mensaje;
    }

    public Date getFechaEnvio() {
        return fechaEnvio;
    }

    public void setFechaEnvio(Date fechaEnvio) {
        this.fechaEnvio = fechaEnvio;
    }

    public int getIsLeido() {
        return isLeido;
    }

    public void setIsLeido(int isLeido) {
        this.isLeido = isLeido;
    }

    public Persona getPersonasIdPersona() {
        return personasIdPersona;
    }

    public void setPersonasIdPersona(Persona personasIdPersona) {
        this.personasIdPersona = personasIdPersona;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (id != null ? id.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Notificacion)) {
            return false;
        }
        Notificacion other = (Notificacion) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "org.entidades.Notificacion[ id=" + id + " ]";
    }
    
}
