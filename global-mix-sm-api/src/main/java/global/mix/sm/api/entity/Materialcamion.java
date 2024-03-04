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
@Table(name = "materialcamion", catalog = "global_mix_sm", schema = "")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Materialcamion.findAll", query = "SELECT m FROM Materialcamion m"),
    @NamedQuery(name = "Materialcamion.findByIdmaterialcamion", query = "SELECT m FROM Materialcamion m WHERE m.idmaterialcamion = :idmaterialcamion"),
    @NamedQuery(name = "Materialcamion.findByIdcamion", query = "SELECT m FROM Materialcamion m WHERE m.idcamion = :idcamion"),
    @NamedQuery(name = "Materialcamion.findByIdmaterial", query = "SELECT m FROM Materialcamion m WHERE m.idmaterial = :idmaterial"),
    @NamedQuery(name = "Materialcamion.findByIdpedido", query = "SELECT m FROM Materialcamion m WHERE m.idpedido = :idpedido"),
    @NamedQuery(name = "Materialcamion.findByFechacreacion", query = "SELECT m FROM Materialcamion m WHERE m.fechacreacion = :fechacreacion"),
    @NamedQuery(name = "Materialcamion.findByUsuariocreacion", query = "SELECT m FROM Materialcamion m WHERE m.usuariocreacion = :usuariocreacion"),
    @NamedQuery(name = "Materialcamion.findByActivo", query = "SELECT m FROM Materialcamion m WHERE m.activo = :activo")})
public class Materialcamion implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "idmaterialcamion", nullable = false)
    private Integer idmaterialcamion;
    
    @Basic(optional = false)
    @Column(name = "idcamion", nullable = false)
    private Integer idcamion;
    
    @Basic(optional = false)
    @Column(name = "idmaterial", nullable = false)
    private Integer idmaterial;
    
    @Basic(optional = false)
    @Column(name = "idpedido", nullable = false)
    private Integer idpedido;
    
    @Basic(optional = false)
    @Column(name = "fechacreacion", nullable = false)
    @Temporal(TemporalType.TIMESTAMP)
    private Date fechacreacion;
    
    @Basic(optional = false)
    @Column(name = "usuariocreacion", nullable = false, length = 50)
    private String usuariocreacion;
    
    @Basic(optional = false)
    @Column(name = "activo", nullable = false)
    private boolean activo;

    public Materialcamion() {
    }

    public Materialcamion(Integer idmaterialcamion) {
        this.idmaterialcamion = idmaterialcamion;
    }

    public Materialcamion(Integer idmaterialcamion, int idcamion, int idmaterial, int idpedido, Date fechacreacion, String usuariocreacion, boolean activo) {
        this.idmaterialcamion = idmaterialcamion;
        this.idcamion = idcamion;
        this.idmaterial = idmaterial;
        this.idpedido = idpedido;
        this.fechacreacion = fechacreacion;
        this.usuariocreacion = usuariocreacion;
        this.activo = activo;
    }

    public Integer getIdmaterialcamion() {
        return idmaterialcamion;
    }

    public void setIdmaterialcamion(Integer idmaterialcamion) {
        this.idmaterialcamion = idmaterialcamion;
    }

    public Integer getIdcamion() {
        return idcamion;
    }

    public void setIdcamion(Integer idcamion) {
        this.idcamion = idcamion;
    }

    public Integer getIdmaterial() {
        return idmaterial;
    }

    public void setIdmaterial(Integer idmaterial) {
        this.idmaterial = idmaterial;
    }

    public Integer getIdpedido() {
        return idpedido;
    }

    public void setIdpedido(Integer idpedido) {
        this.idpedido = idpedido;
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

    public boolean getActivo() {
        return activo;
    }

    public void setActivo(boolean activo) {
        this.activo = activo;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idmaterialcamion != null ? idmaterialcamion.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Materialcamion)) {
            return false;
        }
        Materialcamion other = (Materialcamion) object;
        if ((this.idmaterialcamion == null && other.idmaterialcamion != null) || (this.idmaterialcamion != null && !this.idmaterialcamion.equals(other.idmaterialcamion))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "global.mix.sm.api.entity.Materialcamion[ idmaterialcamion=" + idmaterialcamion + " ]";
    }
    
}
