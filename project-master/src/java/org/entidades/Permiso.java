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
import javax.persistence.JoinTable;
import javax.persistence.Lob;
import javax.persistence.ManyToMany;
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
@Table(name = "permisos")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Permiso.findAll", query = "SELECT p FROM Permiso p")
    , @NamedQuery(name = "Permiso.findByIdPermiso", query = "SELECT p FROM Permiso p WHERE p.idPermiso = :idPermiso")
    , @NamedQuery(name = "Permiso.findByNombrePermiso", query = "SELECT p FROM Permiso p WHERE p.nombrePermiso = :nombrePermiso")
    , @NamedQuery(name = "Permiso.findByNombrePermisoEn", query = "SELECT p FROM Permiso p WHERE p.nombrePermisoEn = :nombrePermisoEn")
    , @NamedQuery(name = "Permiso.findByEstado", query = "SELECT p FROM Permiso p WHERE p.estado = :estado")
    , @NamedQuery(name = "Permiso.findByIcon", query = "SELECT p FROM Permiso p WHERE p.icon = :icon")})
public class Permiso implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id_permiso")
    private Integer idPermiso;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 45)
    @Column(name = "nombre_permiso")
    private String nombrePermiso;
    @Size(max = 45)
    @Column(name = "nombre_permiso_en")
    private String nombrePermisoEn;
    @Basic(optional = false)
    @NotNull
    @Column(name = "estado")
    private int estado;
    @Lob
    @Size(max = 65535)
    @Column(name = "url")
    private String url;
    @Size(max = 45)
    @Column(name = "icon")
    private String icon;
   //nuevo
    @ManyToMany(mappedBy = "permisos", fetch = FetchType.EAGER)
    private List<Rol> roles; //indica "ese" permiso, en cuantos roles esta
    @OneToMany(mappedBy = "permisoPadre", fetch = FetchType.EAGER)
    private List<Permiso> subPermisos;//muestra los subpermisos
    @JoinColumn(name = "permiso_padre", referencedColumnName = "id_permiso")
    @ManyToOne(fetch = FetchType.EAGER)
    private Permiso permisoPadre;

    public Permiso() {
    }

    public Permiso(Integer idPermiso) {
        this.idPermiso = idPermiso;
    }

    public Permiso(Integer idPermiso, String nombrePermiso, int estado) {
        this.idPermiso = idPermiso;
        this.nombrePermiso = nombrePermiso;
        this.estado = estado;
    }

    public Integer getIdPermiso() {
        return idPermiso;
    }

    public void setIdPermiso(Integer idPermiso) {
        this.idPermiso = idPermiso;
    }

    public String getNombrePermiso() {
        return nombrePermiso;
    }

    public void setNombrePermiso(String nombrePermiso) {
        this.nombrePermiso = nombrePermiso;
    }

    public String getNombrePermisoEn() {
        return nombrePermisoEn;
    }

    public void setNombrePermisoEn(String nombrePermisoEn) {
        this.nombrePermisoEn = nombrePermisoEn;
    }

    public int getEstado() {
        return estado;
    }

    public void setEstado(int estado) {
        this.estado = estado;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getIcon() {
        return icon;
    }

    public void setIcon(String icon) {
        this.icon = icon;
    }

    @XmlTransient
    public List<Rol> getRoles() {
        return roles;
    }

    public void setRoles(List<Rol> roles) {
        this.roles = roles;
    }

    public Permiso getPermisoPadre() {
        return permisoPadre;
    }

    public void setPermisoPadre(Permiso permisoPadre) {
        this.permisoPadre = permisoPadre;
    }
    
    public List<Permiso> getSubPermisos() {
        return subPermisos;
    }

    public void setSubPermisos(List<Permiso> subPermisos) {
        this.subPermisos = subPermisos;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idPermiso != null ? idPermiso.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Permiso)) {
            return false;
        }
        Permiso other = (Permiso) object;
        if ((this.idPermiso == null && other.idPermiso != null) || (this.idPermiso != null && !this.idPermiso.equals(other.idPermiso))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "org.entidades.Permiso[ idPermiso=" + idPermiso + " ]";
    }
    
}
