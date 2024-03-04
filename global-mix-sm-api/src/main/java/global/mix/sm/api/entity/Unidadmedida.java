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
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
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
@Table(name = "unidadmedida", catalog = "global_mix_sm", schema = "")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Unidadmedida.findAll", query = "SELECT u FROM Unidadmedida u"),
    @NamedQuery(name = "Unidadmedida.findByIdunidadmedida", query = "SELECT u FROM Unidadmedida u WHERE u.idunidadmedida = :idunidadmedida"),
    @NamedQuery(name = "Unidadmedida.findByUnidadmedida", query = "SELECT u FROM Unidadmedida u WHERE u.unidadmedida = :unidadmedida"),
    @NamedQuery(name = "Unidadmedida.findByDescripcion", query = "SELECT u FROM Unidadmedida u WHERE u.descripcion = :descripcion"),
    @NamedQuery(name = "Unidadmedida.findByUsuariocreacion", query = "SELECT u FROM Unidadmedida u WHERE u.usuariocreacion = :usuariocreacion"),
    @NamedQuery(name = "Unidadmedida.findByFechacreacion", query = "SELECT u FROM Unidadmedida u WHERE u.fechacreacion = :fechacreacion"),
    @NamedQuery(name = "Unidadmedida.findByUsuarioeliminacion", query = "SELECT u FROM Unidadmedida u WHERE u.usuarioeliminacion = :usuarioeliminacion"),
    @NamedQuery(name = "Unidadmedida.findByFechaeliminacion", query = "SELECT u FROM Unidadmedida u WHERE u.fechaeliminacion = :fechaeliminacion"),
    @NamedQuery(name = "Unidadmedida.findByActivo", query = "SELECT u FROM Unidadmedida u WHERE u.activo = :activo")})
public class Unidadmedida implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "idunidadmedida", nullable = false)
    private Integer idunidadmedida;
    
    @Basic(optional = false)
    @Column(name = "unidadmedida", nullable = false, length = 250)
    private String unidadmedida;
    
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
    private boolean activo;
    
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "idunidadmedida", fetch = FetchType.LAZY)
    private List<Material> materialList;
    
    @JoinColumn(name = "idkilogramo", referencedColumnName = "idkilogramo")
    @ManyToOne(fetch = FetchType.LAZY)
    private Kilogramo idkilogramo;

    public Unidadmedida() {
    }

    public Unidadmedida(Integer idunidadmedida) {
        this.idunidadmedida = idunidadmedida;
    }

    public Unidadmedida(Integer idunidadmedida, String unidadmedida, String usuariocreacion, Date fechacreacion, boolean activo) {
        this.idunidadmedida = idunidadmedida;
        this.unidadmedida = unidadmedida;
        this.usuariocreacion = usuariocreacion;
        this.fechacreacion = fechacreacion;
        this.activo = activo;
    }

    public Integer getIdunidadmedida() {
        return idunidadmedida;
    }

    public void setIdunidadmedida(Integer idunidadmedida) {
        this.idunidadmedida = idunidadmedida;
    }

    public String getUnidadmedida() {
        return unidadmedida;
    }

    public void setUnidadmedida(String unidadmedida) {
        this.unidadmedida = unidadmedida;
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

    public boolean getActivo() {
        return activo;
    }

    public void setActivo(boolean activo) {
        this.activo = activo;
    }

    @XmlTransient
    public List<Material> getMaterialList() {
        return materialList;
    }

    public void setMaterialList(List<Material> materialList) {
        this.materialList = materialList;
    }

    public Kilogramo getIdkilogramo() {
        return idkilogramo;
    }

    public void setIdkilogramo(Kilogramo idkilogramo) {
        this.idkilogramo = idkilogramo;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idunidadmedida != null ? idunidadmedida.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Unidadmedida)) {
            return false;
        }
        Unidadmedida other = (Unidadmedida) object;
        if ((this.idunidadmedida == null && other.idunidadmedida != null) || (this.idunidadmedida != null && !this.idunidadmedida.equals(other.idunidadmedida))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "global.mix.sm.api.entity.Unidadmedida[ idunidadmedida=" + idunidadmedida + " ]";
    }
    
}
