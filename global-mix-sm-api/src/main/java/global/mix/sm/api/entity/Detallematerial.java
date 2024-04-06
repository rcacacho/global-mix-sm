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
@Table(name = "detallematerial", catalog = "global_mix_sm", schema = "")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Detallematerial.findAll", query = "SELECT d FROM Detallematerial d"),
    @NamedQuery(name = "Detallematerial.findByIddetallematerial", query = "SELECT d FROM Detallematerial d WHERE d.iddetallematerial = :iddetallematerial"),
    @NamedQuery(name = "Detallematerial.findByExistenciaActual", query = "SELECT d FROM Detallematerial d WHERE d.existenciaActual = :existenciaActual"),
    @NamedQuery(name = "Detallematerial.findByIngreso", query = "SELECT d FROM Detallematerial d WHERE d.ingreso = :ingreso"),
    @NamedQuery(name = "Detallematerial.findByEgreso", query = "SELECT d FROM Detallematerial d WHERE d.egreso = :egreso"),
    @NamedQuery(name = "Detallematerial.findByTotal", query = "SELECT d FROM Detallematerial d WHERE d.total = :total"),
    @NamedQuery(name = "Detallematerial.findByFechacreacion", query = "SELECT d FROM Detallematerial d WHERE d.fechacreacion = :fechacreacion"),
    @NamedQuery(name = "Detallematerial.findByUsuariocreacion", query = "SELECT d FROM Detallematerial d WHERE d.usuariocreacion = :usuariocreacion"),
    @NamedQuery(name = "Detallematerial.findByFechaeliminacion", query = "SELECT d FROM Detallematerial d WHERE d.fechaeliminacion = :fechaeliminacion"),
    @NamedQuery(name = "Detallematerial.findByUsuarioeliminacion", query = "SELECT d FROM Detallematerial d WHERE d.usuarioeliminacion = :usuarioeliminacion"),
    @NamedQuery(name = "Detallematerial.findByActivo", query = "SELECT d FROM Detallematerial d WHERE d.activo = :activo")})
public class Detallematerial implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "iddetallematerial", nullable = false)
    private Integer iddetallematerial;

    @Basic(optional = false)
    @Column(name = "existencia_actual", nullable = false)
    private double existenciaActual;

    @Column(name = "ingreso", precision = 22)
    private Double ingreso;

    @Column(name = "egreso", precision = 22)
    private Double egreso;

    @Column(name = "egresounidadmedida", precision = 22, scale = 0)
    private Double egresounidadmedida;

    @Column(name = "total", precision = 22, scale = 0)
    private Double total;

    @Column(name = "totalunidadmedida", precision = 22, scale = 0)
    private Double totalunidadmedida;

    @Basic(optional = false)
    @Column(name = "fechacreacion", nullable = false)
    @Temporal(TemporalType.TIMESTAMP)
    private Date fechacreacion;

    @Basic(optional = false)
    @Column(name = "usuariocreacion", nullable = false, length = 50)
    private String usuariocreacion;

    @Column(name = "fechaeliminacion")
    @Temporal(TemporalType.TIMESTAMP)
    private Date fechaeliminacion;

    @Column(name = "usuarioeliminacion", length = 50)
    private String usuarioeliminacion;

    @Basic(optional = false)
    @Column(name = "activo", nullable = false)
    private boolean activo;

    @JoinColumn(name = "idmaterial", referencedColumnName = "idmaterial", nullable = false)
    @ManyToOne(optional = false, fetch = FetchType.LAZY)
    private Material idmaterial;

    public Detallematerial() {
    }

    public Detallematerial(Integer iddetallematerial) {
        this.iddetallematerial = iddetallematerial;
    }

    public Detallematerial(Integer iddetallematerial, double existenciaActual, Date fechacreacion, String usuariocreacion, boolean activo) {
        this.iddetallematerial = iddetallematerial;
        this.existenciaActual = existenciaActual;
        this.fechacreacion = fechacreacion;
        this.usuariocreacion = usuariocreacion;
        this.activo = activo;
    }

    public Integer getIddetallematerial() {
        return iddetallematerial;
    }

    public void setIddetallematerial(Integer iddetallematerial) {
        this.iddetallematerial = iddetallematerial;
    }

    public double getExistenciaActual() {
        return existenciaActual;
    }

    public void setExistenciaActual(double existenciaActual) {
        this.existenciaActual = existenciaActual;
    }

    public Double getIngreso() {
        return ingreso;
    }

    public void setIngreso(Double ingreso) {
        this.ingreso = ingreso;
    }

    public Double getEgreso() {
        return egreso;
    }

    public void setEgreso(Double egreso) {
        this.egreso = egreso;
    }

    public Double getTotal() {
        return total;
    }

    public void setTotal(Double total) {
        this.total = total;
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

    public Date getFechaeliminacion() {
        return fechaeliminacion;
    }

    public void setFechaeliminacion(Date fechaeliminacion) {
        this.fechaeliminacion = fechaeliminacion;
    }

    public String getUsuarioeliminacion() {
        return usuarioeliminacion;
    }

    public void setUsuarioeliminacion(String usuarioeliminacion) {
        this.usuarioeliminacion = usuarioeliminacion;
    }

    public boolean getActivo() {
        return activo;
    }

    public void setActivo(boolean activo) {
        this.activo = activo;
    }

    public Material getIdmaterial() {
        return idmaterial;
    }

    public void setIdmaterial(Material idmaterial) {
        this.idmaterial = idmaterial;
    }

    public Double getEgresounidadmedida() {
        return egresounidadmedida;
    }

    public void setEgresounidadmedida(Double egresounidadmedida) {
        this.egresounidadmedida = egresounidadmedida;
    }

    public Double getTotalunidadmedida() {
        return totalunidadmedida;
    }

    public void setTotalunidadmedida(Double totalunidadmedida) {
        this.totalunidadmedida = totalunidadmedida;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (iddetallematerial != null ? iddetallematerial.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Detallematerial)) {
            return false;
        }
        Detallematerial other = (Detallematerial) object;
        if ((this.iddetallematerial == null && other.iddetallematerial != null) || (this.iddetallematerial != null && !this.iddetallematerial.equals(other.iddetallematerial))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "global.mix.sm.api.entity.Detallematerial[ iddetallematerial=" + iddetallematerial + " ]";
    }

}
