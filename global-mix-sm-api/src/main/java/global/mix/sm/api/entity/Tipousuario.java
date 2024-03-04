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
@Table(name = "tipousuario", catalog = "global_mix_sm", schema = "")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Tipousuario.findAll", query = "SELECT t FROM Tipousuario t"),
    @NamedQuery(name = "Tipousuario.findByIdtipousuario", query = "SELECT t FROM Tipousuario t WHERE t.idtipousuario = :idtipousuario"),
    @NamedQuery(name = "Tipousuario.findByTipousuario", query = "SELECT t FROM Tipousuario t WHERE t.tipousuario = :tipousuario"),
    @NamedQuery(name = "Tipousuario.findByDescripcion", query = "SELECT t FROM Tipousuario t WHERE t.descripcion = :descripcion"),
    @NamedQuery(name = "Tipousuario.findByUsuariocreacion", query = "SELECT t FROM Tipousuario t WHERE t.usuariocreacion = :usuariocreacion"),
    @NamedQuery(name = "Tipousuario.findByFechacreacion", query = "SELECT t FROM Tipousuario t WHERE t.fechacreacion = :fechacreacion"),
    @NamedQuery(name = "Tipousuario.findByUsuarioeliminacion", query = "SELECT t FROM Tipousuario t WHERE t.usuarioeliminacion = :usuarioeliminacion"),
    @NamedQuery(name = "Tipousuario.findByFechaeliminacion", query = "SELECT t FROM Tipousuario t WHERE t.fechaeliminacion = :fechaeliminacion"),
    @NamedQuery(name = "Tipousuario.findByActivo", query = "SELECT t FROM Tipousuario t WHERE t.activo = :activo")})
public class Tipousuario implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "idtipousuario", nullable = false)
    private Integer idtipousuario;
    
    @Basic(optional = false)
    @Column(name = "tipousuario", nullable = false, length = 250)
    private String tipousuario;
    
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
    
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "idtipousuario", fetch = FetchType.LAZY)
    private List<Usuario> usuarioList;

    public Tipousuario() {
    }

    public Tipousuario(Integer idtipousuario) {
        this.idtipousuario = idtipousuario;
    }

    public Tipousuario(Integer idtipousuario, String tipousuario, String usuariocreacion, Date fechacreacion, boolean activo) {
        this.idtipousuario = idtipousuario;
        this.tipousuario = tipousuario;
        this.usuariocreacion = usuariocreacion;
        this.fechacreacion = fechacreacion;
        this.activo = activo;
    }

    public Integer getIdtipousuario() {
        return idtipousuario;
    }

    public void setIdtipousuario(Integer idtipousuario) {
        this.idtipousuario = idtipousuario;
    }

    public String getTipousuario() {
        return tipousuario;
    }

    public void setTipousuario(String tipousuario) {
        this.tipousuario = tipousuario;
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
    public List<Usuario> getUsuarioList() {
        return usuarioList;
    }

    public void setUsuarioList(List<Usuario> usuarioList) {
        this.usuarioList = usuarioList;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idtipousuario != null ? idtipousuario.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Tipousuario)) {
            return false;
        }
        Tipousuario other = (Tipousuario) object;
        if ((this.idtipousuario == null && other.idtipousuario != null) || (this.idtipousuario != null && !this.idtipousuario.equals(other.idtipousuario))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "global.mix.sm.api.entity.Tipousuario[ idtipousuario=" + idtipousuario + " ]";
    }
    
}
