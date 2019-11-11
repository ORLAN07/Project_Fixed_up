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
@Table(name = "dificultades")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Dificultad.findAll", query = "SELECT d FROM Dificultad d")
    , @NamedQuery(name = "Dificultad.findByIdDificultad", query = "SELECT d FROM Dificultad d WHERE d.idDificultad = :idDificultad")
    , @NamedQuery(name = "Dificultad.findByDescripcionDificultad", query = "SELECT d FROM Dificultad d WHERE d.descripcionDificultad = :descripcionDificultad")})
public class Dificultad implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id_dificultad")
    private Integer idDificultad;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 25)
    @Column(name = "descripcion_dificultad")
    private String descripcionDificultad;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "dificultadesIdDificultad", fetch = FetchType.EAGER)
    private List<Proyecto> proyectoList;

    public Dificultad() {
    }

    public Dificultad(Integer idDificultad) {
        this.idDificultad = idDificultad;
    }

    public Dificultad(Integer idDificultad, String descripcionDificultad) {
        this.idDificultad = idDificultad;
        this.descripcionDificultad = descripcionDificultad;
    }

    public Integer getIdDificultad() {
        return idDificultad;
    }

    public void setIdDificultad(Integer idDificultad) {
        this.idDificultad = idDificultad;
    }

    public String getDescripcionDificultad() {
        return descripcionDificultad;
    }

    public void setDescripcionDificultad(String descripcionDificultad) {
        this.descripcionDificultad = descripcionDificultad;
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
        hash += (idDificultad != null ? idDificultad.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Dificultad)) {
            return false;
        }
        Dificultad other = (Dificultad) object;
        if ((this.idDificultad == null && other.idDificultad != null) || (this.idDificultad != null && !this.idDificultad.equals(other.idDificultad))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "org.entidades.Dificultad[ idDificultad=" + idDificultad + " ]";
    }
    
}
