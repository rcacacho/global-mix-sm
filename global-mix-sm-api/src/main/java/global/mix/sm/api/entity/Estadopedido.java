/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package global.mix.sm.api.entity;

import java.io.Serializable;
import java.util.Date;
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
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

/**
 *
 * @author rcacacho
 */
@Entity
@Table(name = "estadopedido", catalog = "global_mix_sm", schema = "")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Estadopedido.findAll", query = "SELECT e FROM Estadopedido e"),
    @NamedQuery(name = "Estadopedido.findByIdestadopedido", query = "SELECT e FROM Estadopedido e WHERE e.idestadopedido = :idestadopedido"),
    @NamedQuery(name = "Estadopedido.findByEstado", query = "SELECT e FROM Estadopedido e WHERE e.estado = :estado"),
    @NamedQuery(name = "Estadopedido.findByDescripcion", query = "SELECT e FROM Estadopedido e WHERE e.descripcion = :descripcion"),
    @NamedQuery(name = "Estadopedido.findByUsuariocreacion", query = "SELECT e FROM Estadopedido e WHERE e.usuariocreacion = :usuariocreacion"),
    @NamedQuery(name = "Estadopedido.findByFechacreacion", query = "SELECT e FROM Estadopedido e WHERE e.fechacreacion = :fechacreacion"),
    @NamedQuery(name = "Estadopedido.findByUsuarioeliminacion", query = "SELECT e FROM Estadopedido e WHERE e.usuarioeliminacion = :usuarioeliminacion"),
    @NamedQuery(name = "Estadopedido.findByFechaeliminacion", query = "SELECT e FROM Estadopedido e WHERE e.fechaeliminacion = :fechaeliminacion"),
    @NamedQuery(name = "Estadopedido.findByActivo", query = "SELECT e FROM Estadopedido e WHERE e.activo = :activo")})
public class Estadopedido implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "idestadopedido", nullable = false)
    private Integer idestadopedido;
    @Basic(optional = false)
    @Column(name = "estado", nullable = false, length = 250)
    private String estado;
    @Column(name = "descripcion", length = 250)
    private String descripcion;
    @Basic(optional = false)
    @Column(name = "usuariocreacion", nullable = false, length = 50)
    private String usuariocreacion;
    @Basic(optional = false)
    @Column(name = "fechacreacion", nullable = false)
    @Temporal(TemporalType.TIMESTAMP)
    private Date fechacreacion;
    @Column(name = "usuarioeliminacion", length = 50)
    private String usuarioeliminacion;
    @Column(name = "fechaeliminacion")
    @Temporal(TemporalType.TIMESTAMP)
    private Date fechaeliminacion;
    @Basic(optional = false)
    @Column(name = "activo", nullable = false)
    private int activo;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "idestadopedido", fetch = FetchType.LAZY)
    private List<Pedidos> pedidosList;

    public Estadopedido() {
    }

    public Estadopedido(Integer idestadopedido) {
        this.idestadopedido = idestadopedido;
    }

    public Estadopedido(Integer idestadopedido, String estado, String usuariocreacion, Date fechacreacion, int activo) {
        this.idestadopedido = idestadopedido;
        this.estado = estado;
        this.usuariocreacion = usuariocreacion;
        this.fechacreacion = fechacreacion;
        this.activo = activo;
    }

    public Integer getIdestadopedido() {
        return idestadopedido;
    }

    public void setIdestadopedido(Integer idestadopedido) {
        this.idestadopedido = idestadopedido;
    }

    public String getEstado() {
        return estado;
    }

    public void setEstado(String estado) {
        this.estado = estado;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public String getUsuariocreacion() {
        return usuariocreacion;
    }

    public void setUsuariocreacion(String usuariocreacion) {
        this.usuariocreacion = usuariocreacion;
    }

    public Date getFechacreacion() {
        return fechacreacion;
    }

    public void setFechacreacion(Date fechacreacion) {
        this.fechacreacion = fechacreacion;
    }

    public String getUsuarioeliminacion() {
        return usuarioeliminacion;
    }

    public void setUsuarioeliminacion(String usuarioeliminacion) {
        this.usuarioeliminacion = usuarioeliminacion;
    }

    public Date getFechaeliminacion() {
        return fechaeliminacion;
    }

    public void setFechaeliminacion(Date fechaeliminacion) {
        this.fechaeliminacion = fechaeliminacion;
    }

    public int getActivo() {
        return activo;
    }

    public void setActivo(int activo) {
        this.activo = activo;
    }

    @XmlTransient
    public List<Pedidos> getPedidosList() {
        return pedidosList;
    }

    public void setPedidosList(List<Pedidos> pedidosList) {
        this.pedidosList = pedidosList;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idestadopedido != null ? idestadopedido.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Estadopedido)) {
            return false;
        }
        Estadopedido other = (Estadopedido) object;
        if ((this.idestadopedido == null && other.idestadopedido != null) || (this.idestadopedido != null && !this.idestadopedido.equals(other.idestadopedido))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "global.mix.sm.api.entity.Estadopedido[ idestadopedido=" + idestadopedido + " ]";
    }
    
}
