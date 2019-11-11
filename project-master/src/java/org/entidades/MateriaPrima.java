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
import javax.persistence.ManyToMany;
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
@Table(name = "materias_prima")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "MateriaPrima.findAll", query = "SELECT m FROM MateriaPrima m")
    , @NamedQuery(name = "MateriaPrima.findByIdMateria", query = "SELECT m FROM MateriaPrima m WHERE m.idMateria = :idMateria")
    , @NamedQuery(name = "MateriaPrima.findByReferencia", query = "SELECT m FROM MateriaPrima m WHERE m.referencia = :referencia")
    , @NamedQuery(name = "MateriaPrima.findByTipoMateriaPrima", query = "SELECT m FROM MateriaPrima m WHERE m.tipoMateriaPrima = :tipoMateriaPrima")
    , @NamedQuery(name = "MateriaPrima.findByCalibre", query = "SELECT m FROM MateriaPrima m WHERE m.calibre = :calibre")
    , @NamedQuery(name = "MateriaPrima.findByDimensionLargo", query = "SELECT m FROM MateriaPrima m WHERE m.dimensionLargo = :dimensionLargo")
    , @NamedQuery(name = "MateriaPrima.findByExistencias", query = "SELECT m FROM MateriaPrima m, IN (m.stockList) s WHERE s.stock > 0")
    , @NamedQuery(name = "MateriaPrima.findByDimensionAlto", query = "SELECT m FROM MateriaPrima m WHERE m.dimensionAlto = :dimensionAlto")})
public class MateriaPrima implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id_materia")
    private Integer idMateria;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 50)
    @Column(name = "referencia")
    private String referencia;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 50)
    @Column(name = "tipo_materia_prima")
    private String tipoMateriaPrima;
    @Basic(optional = false)
    @NotNull
    @Column(name = "calibre")
    private int calibre;
    @Basic(optional = false)
    @NotNull
    @Column(name = "dimension_largo")
    private int dimensionLargo;
    @Basic(optional = false)
    @NotNull
    @Column(name = "dimension_alto")
    private int dimensionAlto;
    @JoinTable(name = "proveedores_materias_prima", joinColumns = {
        @JoinColumn(name = "materias_prima_id_materia", referencedColumnName = "id_materia")}, inverseJoinColumns = {
        @JoinColumn(name = "proveedores_id_proveedor", referencedColumnName = "id_proveedor")})
    @ManyToMany(fetch = FetchType.EAGER)
    private List<Proveedor> proveedorList;
    @OneToMany(mappedBy = "materiasPrimaIdMateria", fetch = FetchType.EAGER)
    private List<Pedido> pedidoList;
    @OneToMany(mappedBy = "materiasPrimaIdMateria", fetch = FetchType.EAGER)
    private List<Stock> stockList;

    public MateriaPrima() {
    }

    public MateriaPrima(Integer idMateria) {
        this.idMateria = idMateria;
    }

    public MateriaPrima(Integer idMateria, String referencia, String tipoMateriaPrima, int calibre, int dimensionLargo, int dimensionAlto) {
        this.idMateria = idMateria;
        this.referencia = referencia;
        this.tipoMateriaPrima = tipoMateriaPrima;
        this.calibre = calibre;
        this.dimensionLargo = dimensionLargo;
        this.dimensionAlto = dimensionAlto;
    }

    public Integer getIdMateria() {
        return idMateria;
    }

    public void setIdMateria(Integer idMateria) {
        this.idMateria = idMateria;
    }

    public String getReferencia() {
        return referencia;
    }

    public void setReferencia(String referencia) {
        this.referencia = referencia;
    }

    public String getTipoMateriaPrima() {
        return tipoMateriaPrima;
    }

    public void setTipoMateriaPrima(String tipoMateriaPrima) {
        this.tipoMateriaPrima = tipoMateriaPrima;
    }

    public int getCalibre() {
        return calibre;
    }

    public void setCalibre(int calibre) {
        this.calibre = calibre;
    }

    public int getDimensionLargo() {
        return dimensionLargo;
    }

    public void setDimensionLargo(int dimensionLargo) {
        this.dimensionLargo = dimensionLargo;
    }

    public int getDimensionAlto() {
        return dimensionAlto;
    }

    public void setDimensionAlto(int dimensionAlto) {
        this.dimensionAlto = dimensionAlto;
    }

    @XmlTransient
    public List<Proveedor> getProveedorList() {
        return proveedorList;
    }

    public void setProveedorList(List<Proveedor> proveedorList) {
        this.proveedorList = proveedorList;
    }

    @XmlTransient
    public List<Pedido> getPedidoList() {
        return pedidoList;
    }

    public void setPedidoList(List<Pedido> pedidoList) {
        this.pedidoList = pedidoList;
    }

    @XmlTransient
    public List<Stock> getStockList() {
        return stockList;
    }

    public void setStockList(List<Stock> stockList) {
        this.stockList = stockList;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idMateria != null ? idMateria.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof MateriaPrima)) {
            return false;
        }
        MateriaPrima other = (MateriaPrima) object;
        if ((this.idMateria == null && other.idMateria != null) || (this.idMateria != null && !this.idMateria.equals(other.idMateria))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "org.entidades.MateriaPrima[ idMateria=" + idMateria + " ]";
    }
    
}
