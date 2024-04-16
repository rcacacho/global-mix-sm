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
@Table(name = "camion", catalog = "global_mix_sm", schema = "")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Camion.findAll", query = "SELECT c FROM Camion c"),
    @NamedQuery(name = "Camion.findByIdcamion", query = "SELECT c FROM Camion c WHERE c.idcamion = :idcamion"),
    @NamedQuery(name = "Camion.findByNumero", query = "SELECT c FROM Camion c WHERE c.numero = :numero"),
    @NamedQuery(name = "Camion.findByEncargado", query = "SELECT c FROM Camion c WHERE c.encargado = :encargado"),
    @NamedQuery(name = "Camion.findByDescripcion", query = "SELECT c FROM Camion c WHERE c.descripcion = :descripcion"),
    @NamedQuery(name = "Camion.findByUsuariocreacion", query = "SELECT c FROM Camion c WHERE c.usuariocreacion = :usuariocreacion"),
    @NamedQuery(name = "Camion.findByFechacreacion", query = "SELECT c FROM Camion c WHERE c.fechacreacion = :fechacreacion"),
    @NamedQuery(name = "Camion.findByUsuariomodificacion", query = "SELECT c FROM Camion c WHERE c.usuariomodificacion = :usuariomodificacion"),
    @NamedQuery(name = "Camion.findByFechamodificacion", query = "SELECT c FROM Camion c WHERE c.fechamodificacion = :fechamodificacion"),
    @NamedQuery(name = "Camion.findByUsuarioeliminacion", query = "SELECT c FROM Camion c WHERE c.usuarioeliminacion = :usuarioeliminacion"),
    @NamedQuery(name = "Camion.findByFechaeliminacion", query = "SELECT c FROM Camion c WHERE c.fechaeliminacion = :fechaeliminacion"),
    @NamedQuery(name = "Camion.findByActivo", query = "SELECT c FROM Camion c WHERE c.activo = :activo")})
public class Camion implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "idcamion", nullable = false)
    private Integer idcamion;
    
    @Basic(optional = false)
    @Column(name = "numero", nullable = false)
    private Integer numero;
    
    @Column(name = "encargado", length = 200)
    private String encargado;
    
    @Column(name = "descripcion", length = 200)
    private String descripcion;
    
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
    
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "idcamion", fetch = FetchType.LAZY)
    private List<Detallepedidonormal> detallepedidonormalList;

    public Camion() {
    }

    public Camion(Integer idcamion) {
        this.idcamion = idcamion;
    }

    public Camion(Integer idcamion, int numero, String usuariocreacion, Date fechacreacion, boolean activo) {
        this.idcamion = idcamion;
        this.numero = numero;
        this.usuariocreacion = usuariocreacion;
        this.fechacreacion = fechacreacion;
        this.activo = activo;
    }

    public Integer getIdcamion() {
        return idcamion;
    }

    public void setIdcamion(Integer idcamion) {
        this.idcamion = idcamion;
    }

    public Integer getNumero() {
        return numero;
    }

    public void setNumero(Integer numero) {
        this.numero = numero;
    }

    public String getEncargado() {
        return encargado;
    }

    public void setEncargado(String encargado) {
        this.encargado = encargado;
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

    @XmlTransient
    public List<Detallepedidonormal> getDetallepedidonormalList() {
        return detallepedidonormalList;
    }

    public void setDetallepedidonormalList(List<Detallepedidonormal> detallepedidonormalList) {
        this.detallepedidonormalList = detallepedidonormalList;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idcamion != null ? idcamion.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Camion)) {
            return false;
        }
        Camion other = (Camion) object;
        if ((this.idcamion == null && other.idcamion != null) || (this.idcamion != null && !this.idcamion.equals(other.idcamion))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "global.mix.sm.api.entity.Camion[ idcamion=" + idcamion + " ]";
    }
    
}
