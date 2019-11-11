/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.entidades;

import java.io.Serializable;
import java.util.Date;
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
@Table(name = "stocks")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Stock.findAll", query = "SELECT s FROM Stock s")
    //, @NamedQuery(name = "Stock.findAllWithMateriaPrima", query = "SELECT s FROM Stock s, IN (s.materiasPrimaIdMateria) m")
    , @NamedQuery(name = "Stock.findByIdStock", query = "SELECT s FROM Stock s WHERE s.idStock = :idStock")
    , @NamedQuery(name = "Stock.findByStock", query = "SELECT s FROM Stock s WHERE s.stock = :stock")
    , @NamedQuery(name = "Stock.findByFechaIngreso", query = "SELECT s FROM Stock s WHERE s.fechaIngreso = :fechaIngreso")
    , @NamedQuery(name = "Stock.findByIdMateriaPrima", query = "SELECT s FROM Stock s WHERE s.fechaIngreso = :fechaIngreso")
    , @NamedQuery(name = "Stock.findByfechaActualizacion", query = "SELECT s FROM Stock s WHERE s.fechaActualizacion = :fechaActualizacion")})
public class Stock implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id_stock")
    private Integer idStock;
    @Basic(optional = false)
    @NotNull
    @Column(name = "stock")
    private int stock;
    @Basic(optional = false)
    @NotNull
    @Column(name = "fecha_ingreso")
    @Temporal(TemporalType.DATE)
    private Date fechaIngreso;
    @Temporal(TemporalType.DATE)
    @Column(name = "fecha_actualizacion")
    private Date fechaActualizacion;
    @JoinColumn(name = "materias_prima_id_materia", referencedColumnName = "id_materia")
    @ManyToOne(optional = false, fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    private MateriaPrima materiasPrimaIdMateria;

    public Stock() {
    }

    public Stock(Integer idStock) {
        this.idStock = idStock;
    }

    public Stock(Integer idStock, int stock, Date fechaIngreso, Date fechaActualizacion, MateriaPrima materiasPrimaIdMateria) {
        this.idStock = idStock;
        this.stock = stock;
        this.fechaIngreso = fechaIngreso;
        this.fechaActualizacion = fechaActualizacion;
        this.materiasPrimaIdMateria = materiasPrimaIdMateria;
    }
    
    public Stock(Integer idStock, int stock, Date fechaIngreso, Date fechaActualizacion) {
        this.idStock = idStock;
        this.stock = stock;
        this.fechaIngreso = fechaIngreso;
        this.fechaActualizacion = fechaActualizacion;
    }

    public Integer getIdStock() {
        return idStock;
    }

    public void setIdStock(Integer idStock) {
        this.idStock = idStock;
    }

    public int getStock() {
        return stock;
    }

    public void setStock(int stock) {
        this.stock = stock;
    }

    public Date getFechaIngreso() {
        return fechaIngreso;
    }

    public void setFechaIngreso(Date fechaIngreso) {
        this.fechaIngreso = fechaIngreso;
    }

    public Date getFechaActualizacion() {
        return fechaActualizacion;
    }

    public void setFechaActualizacion(Date fechaActualizacion) {
        this.fechaActualizacion = fechaActualizacion;
    }

    public MateriaPrima getMateriasPrimaIdMateria() {
        return materiasPrimaIdMateria;
    }

    public void setMateriasPrimaIdMateria(MateriaPrima materiasPrimaIdMateria) {
        this.materiasPrimaIdMateria = materiasPrimaIdMateria;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idStock != null ? idStock.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Stock)) {
            return false;
        }
        Stock other = (Stock) object;
        if ((this.idStock == null && other.idStock != null) || (this.idStock != null && !this.idStock.equals(other.idStock))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "org.entidades.Stock[ idStock=" + idStock + " ]";
    }
    
}
