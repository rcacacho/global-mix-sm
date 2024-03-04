package global.mix.sm.api.entity;

import java.io.Serializable;
import java.util.Date;
import java.util.List;
import javax.persistence.Basic;
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
@Table(name = "estadodespacho", catalog = "global_mix_sm", schema = "")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Estadodespacho.findAll", query = "SELECT e FROM Estadodespacho e"),
    @NamedQuery(name = "Estadodespacho.findByIdestadodespacho", query = "SELECT e FROM Estadodespacho e WHERE e.idestadodespacho = :idestadodespacho"),
    @NamedQuery(name = "Estadodespacho.findByEstado", query = "SELECT e FROM Estadodespacho e WHERE e.estado = :estado"),
    @NamedQuery(name = "Estadodespacho.findByDescripcion", query = "SELECT e FROM Estadodespacho e WHERE e.descripcion = :descripcion"),
    @NamedQuery(name = "Estadodespacho.findByUsuariocreacion", query = "SELECT e FROM Estadodespacho e WHERE e.usuariocreacion = :usuariocreacion"),
    @NamedQuery(name = "Estadodespacho.findByFechacreacion", query = "SELECT e FROM Estadodespacho e WHERE e.fechacreacion = :fechacreacion"),
    @NamedQuery(name = "Estadodespacho.findByUsuarioeliminacion", query = "SELECT e FROM Estadodespacho e WHERE e.usuarioeliminacion = :usuarioeliminacion"),
    @NamedQuery(name = "Estadodespacho.findByFechaeliminacion", query = "SELECT e FROM Estadodespacho e WHERE e.fechaeliminacion = :fechaeliminacion"),
    @NamedQuery(name = "Estadodespacho.findByActivo", query = "SELECT e FROM Estadodespacho e WHERE e.activo = :activo")})
public class Estadodespacho implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "idestadodespacho", nullable = false)
    private Integer idestadodespacho;
    
    @Basic(optional = false)
    @Column(name = "estado", nullable = false, length = 250)
    private String estado;
    
    @Column(name = "descripcion", length = 500)
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
    
    @OneToMany(mappedBy = "idestadodespacho", fetch = FetchType.LAZY)
    private List<Despachos> despachosList;

    public Estadodespacho() {
    }

    public Estadodespacho(Integer idestadodespacho) {
        this.idestadodespacho = idestadodespacho;
    }

    public Estadodespacho(Integer idestadodespacho, String estado, String usuariocreacion, Date fechacreacion, boolean activo) {
        this.idestadodespacho = idestadodespacho;
        this.estado = estado;
        this.usuariocreacion = usuariocreacion;
        this.fechacreacion = fechacreacion;
        this.activo = activo;
    }

    public Integer getIdestadodespacho() {
        return idestadodespacho;
    }

    public void setIdestadodespacho(Integer idestadodespacho) {
        this.idestadodespacho = idestadodespacho;
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

    public boolean getActivo() {
        return activo;
    }

    public void setActivo(boolean activo) {
        this.activo = activo;
    }

    @XmlTransient
    public List<Despachos> getDespachosList() {
        return despachosList;
    }

    public void setDespachosList(List<Despachos> despachosList) {
        this.despachosList = despachosList;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idestadodespacho != null ? idestadodespacho.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Estadodespacho)) {
            return false;
        }
        Estadodespacho other = (Estadodespacho) object;
        if ((this.idestadodespacho == null && other.idestadodespacho != null) || (this.idestadodespacho != null && !this.idestadodespacho.equals(other.idestadodespacho))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "global.mix.sm.api.entity.Estadodespacho[ idestadodespacho=" + idestadodespacho + " ]";
    }
    
}
