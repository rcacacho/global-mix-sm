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
@Table(name = "kilogramo", catalog = "global_mix_sm", schema = "")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Kilogramo.findAll", query = "SELECT k FROM Kilogramo k"),
    @NamedQuery(name = "Kilogramo.findByIdkilogramo", query = "SELECT k FROM Kilogramo k WHERE k.idkilogramo = :idkilogramo"),
    @NamedQuery(name = "Kilogramo.findByValor", query = "SELECT k FROM Kilogramo k WHERE k.valor = :valor"),
    @NamedQuery(name = "Kilogramo.findByDescripcion", query = "SELECT k FROM Kilogramo k WHERE k.descripcion = :descripcion"),
    @NamedQuery(name = "Kilogramo.findByUsuariocreacion", query = "SELECT k FROM Kilogramo k WHERE k.usuariocreacion = :usuariocreacion"),
    @NamedQuery(name = "Kilogramo.findByFechacreacion", query = "SELECT k FROM Kilogramo k WHERE k.fechacreacion = :fechacreacion"),
    @NamedQuery(name = "Kilogramo.findByUsuarioeliminacion", query = "SELECT k FROM Kilogramo k WHERE k.usuarioeliminacion = :usuarioeliminacion"),
    @NamedQuery(name = "Kilogramo.findByFechaeliminacion", query = "SELECT k FROM Kilogramo k WHERE k.fechaeliminacion = :fechaeliminacion"),
    @NamedQuery(name = "Kilogramo.findByActivo", query = "SELECT k FROM Kilogramo k WHERE k.activo = :activo")})
public class Kilogramo implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "idkilogramo", nullable = false)
    private Integer idkilogramo;
    
    @Basic(optional = false)
    @Column(name = "valor", nullable = false)
    private double valor;
    
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
    
    @OneToMany(mappedBy = "idkilogramo", fetch = FetchType.LAZY)
    private List<Unidadmedida> unidadmedidaList;

    public Kilogramo() {
    }

    public Kilogramo(Integer idkilogramo) {
        this.idkilogramo = idkilogramo;
    }

    public Kilogramo(Integer idkilogramo, double valor, String usuariocreacion, Date fechacreacion, boolean activo) {
        this.idkilogramo = idkilogramo;
        this.valor = valor;
        this.usuariocreacion = usuariocreacion;
        this.fechacreacion = fechacreacion;
        this.activo = activo;
    }

    public Integer getIdkilogramo() {
        return idkilogramo;
    }

    public void setIdkilogramo(Integer idkilogramo) {
        this.idkilogramo = idkilogramo;
    }

    public double getValor() {
        return valor;
    }

    public void setValor(double valor) {
        this.valor = valor;
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
    public List<Unidadmedida> getUnidadmedidaList() {
        return unidadmedidaList;
    }

    public void setUnidadmedidaList(List<Unidadmedida> unidadmedidaList) {
        this.unidadmedidaList = unidadmedidaList;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idkilogramo != null ? idkilogramo.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Kilogramo)) {
            return false;
        }
        Kilogramo other = (Kilogramo) object;
        if ((this.idkilogramo == null && other.idkilogramo != null) || (this.idkilogramo != null && !this.idkilogramo.equals(other.idkilogramo))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "global.mix.sm.api.entity.Kilogramo[ idkilogramo=" + idkilogramo + " ]";
    }
    
}
