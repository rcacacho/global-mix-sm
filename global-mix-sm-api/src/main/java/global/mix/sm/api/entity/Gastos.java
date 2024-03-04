package global.mix.sm.api.entity;

import java.io.Serializable;
import java.util.Date;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
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
@Table(name = "gastos", catalog = "global_mix_sm", schema = "")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Gastos.findAll", query = "SELECT g FROM Gastos g"),
    @NamedQuery(name = "Gastos.findByIdgasto", query = "SELECT g FROM Gastos g WHERE g.idgasto = :idgasto"),
    @NamedQuery(name = "Gastos.findByTipogasto", query = "SELECT g FROM Gastos g WHERE g.tipogasto = :tipogasto"),
    @NamedQuery(name = "Gastos.findByTotal", query = "SELECT g FROM Gastos g WHERE g.total = :total"),
    @NamedQuery(name = "Gastos.findByPordia", query = "SELECT g FROM Gastos g WHERE g.pordia = :pordia"),
    @NamedQuery(name = "Gastos.findBySietedias", query = "SELECT g FROM Gastos g WHERE g.sietedias = :sietedias"),
    @NamedQuery(name = "Gastos.findByFechacreacion", query = "SELECT g FROM Gastos g WHERE g.fechacreacion = :fechacreacion"),
    @NamedQuery(name = "Gastos.findByUsuariocreacion", query = "SELECT g FROM Gastos g WHERE g.usuariocreacion = :usuariocreacion"),
    @NamedQuery(name = "Gastos.findByUsuariomodificacion", query = "SELECT g FROM Gastos g WHERE g.usuariomodificacion = :usuariomodificacion"),
    @NamedQuery(name = "Gastos.findByFechamodificacion", query = "SELECT g FROM Gastos g WHERE g.fechamodificacion = :fechamodificacion"),
    @NamedQuery(name = "Gastos.findByUsuarioeliminacion", query = "SELECT g FROM Gastos g WHERE g.usuarioeliminacion = :usuarioeliminacion"),
    @NamedQuery(name = "Gastos.findByFechaeliminacion", query = "SELECT g FROM Gastos g WHERE g.fechaeliminacion = :fechaeliminacion"),
    @NamedQuery(name = "Gastos.findByActivo", query = "SELECT g FROM Gastos g WHERE g.activo = :activo")})
public class Gastos implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "idgasto", nullable = false)
    private Integer idgasto;
    
    @Basic(optional = false)
    @Column(name = "tipogasto", nullable = false, length = 1000)
    private String tipogasto;
    
    @Basic(optional = false)
    @Column(name = "total", nullable = false)
    private double total;
    
    @Basic(optional = false)
    @Column(name = "pordia", nullable = false)
    private double pordia;
    
    @Basic(optional = false)
    @Column(name = "sietedias", nullable = false)
    private double sietedias;
    
    @Basic(optional = false)
    @Column(name = "fechacreacion", nullable = false)
    @Temporal(TemporalType.TIMESTAMP)
    private Date fechacreacion;
    
    @Basic(optional = false)
    @Column(name = "usuariocreacion", nullable = false, length = 50)
    private String usuariocreacion;
    
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

    public Gastos() {
    }

    public Gastos(Integer idgasto) {
        this.idgasto = idgasto;
    }

    public Gastos(Integer idgasto, String tipogasto, double total, double pordia, double sietedias, Date fechacreacion, String usuariocreacion, boolean activo) {
        this.idgasto = idgasto;
        this.tipogasto = tipogasto;
        this.total = total;
        this.pordia = pordia;
        this.sietedias = sietedias;
        this.fechacreacion = fechacreacion;
        this.usuariocreacion = usuariocreacion;
        this.activo = activo;
    }

    public Integer getIdgasto() {
        return idgasto;
    }

    public void setIdgasto(Integer idgasto) {
        this.idgasto = idgasto;
    }

    public String getTipogasto() {
        return tipogasto;
    }

    public void setTipogasto(String tipogasto) {
        this.tipogasto = tipogasto;
    }

    public double getTotal() {
        return total;
    }

    public void setTotal(double total) {
        this.total = total;
    }

    public double getPordia() {
        return pordia;
    }

    public void setPordia(double pordia) {
        this.pordia = pordia;
    }

    public double getSietedias() {
        return sietedias;
    }

    public void setSietedias(double sietedias) {
        this.sietedias = sietedias;
    }

    public Date getFechacreacion() {
        return fechacreacion;
    }

    public void setFechacreacion(Date fechacreacion) {
        this.fechacreacion = fechacreacion;
    }

    public String getUsuariocreacion() {
        return usuariocreacion;
    }

    public void setUsuariocreacion(String usuariocreacion) {
        this.usuariocreacion = usuariocreacion;
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

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idgasto != null ? idgasto.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Gastos)) {
            return false;
        }
        Gastos other = (Gastos) object;
        if ((this.idgasto == null && other.idgasto != null) || (this.idgasto != null && !this.idgasto.equals(other.idgasto))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "global.mix.sm.api.entity.Gastos[ idgasto=" + idgasto + " ]";
    }
    
}
