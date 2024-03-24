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
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author elfo_
 */
@Entity
@Table(name = "tipocemento", catalog = "globa_mix", schema = "")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Tipocemento.findAll", query = "SELECT t FROM Tipocemento t"),
    @NamedQuery(name = "Tipocemento.findByIdtipocemento", query = "SELECT t FROM Tipocemento t WHERE t.idtipocemento = :idtipocemento"),
    @NamedQuery(name = "Tipocemento.findByDescripcion", query = "SELECT t FROM Tipocemento t WHERE t.descripcion = :descripcion"),
    @NamedQuery(name = "Tipocemento.findByFechacreacion", query = "SELECT t FROM Tipocemento t WHERE t.fechacreacion = :fechacreacion"),
    @NamedQuery(name = "Tipocemento.findByUsuariocreacion", query = "SELECT t FROM Tipocemento t WHERE t.usuariocreacion = :usuariocreacion"),
    @NamedQuery(name = "Tipocemento.findByFechamodificacion", query = "SELECT t FROM Tipocemento t WHERE t.fechamodificacion = :fechamodificacion"),
    @NamedQuery(name = "Tipocemento.findByUsuariomodificacion", query = "SELECT t FROM Tipocemento t WHERE t.usuariomodificacion = :usuariomodificacion"),
    @NamedQuery(name = "Tipocemento.findByFechaeliminacion", query = "SELECT t FROM Tipocemento t WHERE t.fechaeliminacion = :fechaeliminacion"),
    @NamedQuery(name = "Tipocemento.findByUsuarioeliminacion", query = "SELECT t FROM Tipocemento t WHERE t.usuarioeliminacion = :usuarioeliminacion"),
    @NamedQuery(name = "Tipocemento.findByActivo", query = "SELECT t FROM Tipocemento t WHERE t.activo = :activo")})
public class Tipocemento implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "idtipocemento", nullable = false)
    private Integer idtipocemento;
    
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 1000)
    @Column(name = "descripcion", nullable = false, length = 1000)
    private String descripcion;
    
    @Basic(optional = false)
    @NotNull
    @Column(name = "fechacreacion", nullable = false)
    @Temporal(TemporalType.TIMESTAMP)
    private Date fechacreacion;
    
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 50)
    @Column(name = "usuariocreacion", nullable = false, length = 50)
    private String usuariocreacion;
    
    @Column(name = "fechamodificacion")
    @Temporal(TemporalType.TIMESTAMP)
    private Date fechamodificacion;
    
    @Size(max = 50)
    @Column(name = "usuariomodificacion", length = 50)
    private String usuariomodificacion;
    
    @Column(name = "fechaeliminacion")
    @Temporal(TemporalType.TIMESTAMP)
    private Date fechaeliminacion;
    
    @Size(max = 50)
    @Column(name = "usuarioeliminacion", length = 50)
    private String usuarioeliminacion;
    
    @Basic(optional = false)
    @NotNull
    @Column(name = "activo", nullable = false)
    private boolean activo;

    public Tipocemento() {
    }

    public Tipocemento(Integer idtipocemento) {
        this.idtipocemento = idtipocemento;
    }

    public Tipocemento(Integer idtipocemento, String descripcion, Date fechacreacion, String usuariocreacion, boolean activo) {
        this.idtipocemento = idtipocemento;
        this.descripcion = descripcion;
        this.fechacreacion = fechacreacion;
        this.usuariocreacion = usuariocreacion;
        this.activo = activo;
    }

    public Integer getIdtipocemento() {
        return idtipocemento;
    }

    public void setIdtipocemento(Integer idtipocemento) {
        this.idtipocemento = idtipocemento;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
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

    public Date getFechamodificacion() {
        return fechamodificacion;
    }

    public void setFechamodificacion(Date fechamodificacion) {
        this.fechamodificacion = fechamodificacion;
    }

    public String getUsuariomodificacion() {
        return usuariomodificacion;
    }

    public void setUsuariomodificacion(String usuariomodificacion) {
        this.usuariomodificacion = usuariomodificacion;
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

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idtipocemento != null ? idtipocemento.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Tipocemento)) {
            return false;
        }
        Tipocemento other = (Tipocemento) object;
        if ((this.idtipocemento == null && other.idtipocemento != null) || (this.idtipocemento != null && !this.idtipocemento.equals(other.idtipocemento))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "globa.mix.api.entity.Tipocemento[ idtipocemento=" + idtipocemento + " ]";
    }
    
}

