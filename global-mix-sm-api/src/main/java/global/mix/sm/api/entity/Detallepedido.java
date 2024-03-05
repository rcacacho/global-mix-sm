package global.mix.sm.api.entity;

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
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author rcacacho
 */
@Entity
@Table(name = "detallepedido", catalog = "global_mix_sm", schema = "")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Detallepedido.findAll", query = "SELECT d FROM Detallepedido d"),
    @NamedQuery(name = "Detallepedido.findByIddetallepedido", query = "SELECT d FROM Detallepedido d WHERE d.iddetallepedido = :iddetallepedido"),
    @NamedQuery(name = "Detallepedido.findByCantidad", query = "SELECT d FROM Detallepedido d WHERE d.cantidad = :cantidad"),
    @NamedQuery(name = "Detallepedido.findByUsuariocreacion", query = "SELECT d FROM Detallepedido d WHERE d.usuariocreacion = :usuariocreacion"),
    @NamedQuery(name = "Detallepedido.findByFechacreacion", query = "SELECT d FROM Detallepedido d WHERE d.fechacreacion = :fechacreacion"),
    @NamedQuery(name = "Detallepedido.findByUsuariomodificacion", query = "SELECT d FROM Detallepedido d WHERE d.usuariomodificacion = :usuariomodificacion"),
    @NamedQuery(name = "Detallepedido.findByFechamodificacion", query = "SELECT d FROM Detallepedido d WHERE d.fechamodificacion = :fechamodificacion"),
    @NamedQuery(name = "Detallepedido.findByUsuarioeliminacion", query = "SELECT d FROM Detallepedido d WHERE d.usuarioeliminacion = :usuarioeliminacion"),
    @NamedQuery(name = "Detallepedido.findByFechaeliminacion", query = "SELECT d FROM Detallepedido d WHERE d.fechaeliminacion = :fechaeliminacion"),
    @NamedQuery(name = "Detallepedido.findByActivo", query = "SELECT d FROM Detallepedido d WHERE d.activo = :activo")})
public class Detallepedido implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "iddetallepedido", nullable = false)
    private Integer iddetallepedido;
    
    @Basic(optional = false)
    @Column(name = "cantidad", nullable = false)
    private double cantidad;
    
    @Basic(optional = false)
    @Column(name = "usuariocreacion", nullable = false, length = 50)
    private String usuariocreacion;
    
    @Basic(optional = false)
    @Column(name = "fechacreacion", nullable = false)
    @Temporal(TemporalType.TIMESTAMP)
    private Date fechacreacion;
    
    @Column(name = "usuariomodificacion", length = 50)
    private String usuariomodificacion;
    
    @Column(name = "fechamodificacion")
    @Temporal(TemporalType.TIMESTAMP)
    private Date fechamodificacion;
    
    @Column(name = "usuarioeliminacion", length = 50)
    private String usuarioeliminacion;
    
    @Column(name = "fechaeliminacion")
    @Temporal(TemporalType.TIMESTAMP)
    private Date fechaeliminacion;
    
    @Basic(optional = false)
    @Column(name = "activo", nullable = false)
    private boolean activo;
    
    @JoinColumn(name = "iddetallepedidonormal", referencedColumnName = "iddetallepedidonormal", nullable = false)
    @ManyToOne(optional = false, fetch = FetchType.LAZY)
    private Detallepedidonormal iddetallepedidonormal;
    
    @JoinColumn(name = "idmaterial", referencedColumnName = "idmaterial", nullable = false)
    @ManyToOne(optional = false, fetch = FetchType.LAZY)
    private Material idmaterial;

    public Detallepedido() {
    }

    public Detallepedido(Integer iddetallepedido) {
        this.iddetallepedido = iddetallepedido;
    }

    public Detallepedido(Integer iddetallepedido, double cantidad, String usuariocreacion, Date fechacreacion, boolean activo) {
        this.iddetallepedido = iddetallepedido;
        this.cantidad = cantidad;
        this.usuariocreacion = usuariocreacion;
        this.fechacreacion = fechacreacion;
        this.activo = activo;
    }

    public Integer getIddetallepedido() {
        return iddetallepedido;
    }

    public void setIddetallepedido(Integer iddetallepedido) {
        this.iddetallepedido = iddetallepedido;
    }

    public Double getCantidad() {
        return cantidad;
    }

    public void setCantidad(Double cantidad) {
        this.cantidad = cantidad;
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

    public String getUsuariomodificacion() {
        return usuariomodificacion;
    }

    public void setUsuariomodificacion(String usuariomodificacion) {
        this.usuariomodificacion = usuariomodificacion;
    }

    public Date getFechamodificacion() {
        return fechamodificacion;
    }

    public void setFechamodificacion(Date fechamodificacion) {
        this.fechamodificacion = fechamodificacion;
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

    public boolean getActivo() {
        return activo;
    }

    public void setActivo(boolean activo) {
        this.activo = activo;
    }

    public Detallepedidonormal getIddetallepedidonormal() {
        return iddetallepedidonormal;
    }

    public void setIddetallepedidonormal(Detallepedidonormal iddetallepedidonormal) {
        this.iddetallepedidonormal = iddetallepedidonormal;
    }

    public Material getIdmaterial() {
        return idmaterial;
    }

    public void setIdmaterial(Material idmaterial) {
        this.idmaterial = idmaterial;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (iddetallepedido != null ? iddetallepedido.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Detallepedido)) {
            return false;
        }
        Detallepedido other = (Detallepedido) object;
        if ((this.iddetallepedido == null && other.iddetallepedido != null) || (this.iddetallepedido != null && !this.iddetallepedido.equals(other.iddetallepedido))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "global.mix.sm.api.entity.Detallepedido[ iddetallepedido=" + iddetallepedido + " ]";
    }
    
}
