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
@Table(name = "asesor", catalog = "global_mix_sm", schema = "")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Asesor.findAll", query = "SELECT a FROM Asesor a"),
    @NamedQuery(name = "Asesor.findByIdasesor", query = "SELECT a FROM Asesor a WHERE a.idasesor = :idasesor"),
    @NamedQuery(name = "Asesor.findByNombres", query = "SELECT a FROM Asesor a WHERE a.nombres = :nombres"),
    @NamedQuery(name = "Asesor.findByApellidos", query = "SELECT a FROM Asesor a WHERE a.apellidos = :apellidos"),
    @NamedQuery(name = "Asesor.findByUsuariocreacion", query = "SELECT a FROM Asesor a WHERE a.usuariocreacion = :usuariocreacion"),
    @NamedQuery(name = "Asesor.findByFechacreacion", query = "SELECT a FROM Asesor a WHERE a.fechacreacion = :fechacreacion"),
    @NamedQuery(name = "Asesor.findByUsuariomodificacion", query = "SELECT a FROM Asesor a WHERE a.usuariomodificacion = :usuariomodificacion"),
    @NamedQuery(name = "Asesor.findByFechamodificacion", query = "SELECT a FROM Asesor a WHERE a.fechamodificacion = :fechamodificacion"),
    @NamedQuery(name = "Asesor.findByUsuarioeliminacion", query = "SELECT a FROM Asesor a WHERE a.usuarioeliminacion = :usuarioeliminacion"),
    @NamedQuery(name = "Asesor.findByFechaeliminacion", query = "SELECT a FROM Asesor a WHERE a.fechaeliminacion = :fechaeliminacion"),
    @NamedQuery(name = "Asesor.findByActivo", query = "SELECT a FROM Asesor a WHERE a.activo = :activo")})
public class Asesor implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "idasesor", nullable = false)
    private Integer idasesor;
    
    @Basic(optional = false)
    @Column(name = "nombres", nullable = false, length = 250)
    private String nombres;
    
    @Basic(optional = false)
    @Column(name = "apellidos", nullable = false, length = 250)
    private String apellidos;
    
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
    
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "idasesor", fetch = FetchType.LAZY)
    private List<Pedidos> pedidosList;
    
    @OneToMany(mappedBy = "idasesor", fetch = FetchType.LAZY)
    private List<Despachos> despachosList;

    public Asesor() {
    }

    public Asesor(Integer idasesor) {
        this.idasesor = idasesor;
    }

    public Asesor(Integer idasesor, String nombres, String apellidos, String usuariocreacion, Date fechacreacion, boolean activo) {
        this.idasesor = idasesor;
        this.nombres = nombres;
        this.apellidos = apellidos;
        this.usuariocreacion = usuariocreacion;
        this.fechacreacion = fechacreacion;
        this.activo = activo;
    }

    public Integer getIdasesor() {
        return idasesor;
    }

    public void setIdasesor(Integer idasesor) {
        this.idasesor = idasesor;
    }

    public String getNombres() {
        return nombres;
    }

    public void setNombres(String nombres) {
        this.nombres = nombres;
    }

    public String getApellidos() {
        return apellidos;
    }

    public void setApellidos(String apellidos) {
        this.apellidos = apellidos;
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
    public List<Pedidos> getPedidosList() {
        return pedidosList;
    }

    public void setPedidosList(List<Pedidos> pedidosList) {
        this.pedidosList = pedidosList;
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
        hash += (idasesor != null ? idasesor.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Asesor)) {
            return false;
        }
        Asesor other = (Asesor) object;
        if ((this.idasesor == null && other.idasesor != null) || (this.idasesor != null && !this.idasesor.equals(other.idasesor))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "global.mix.sm.api.entity.Asesor[ idasesor=" + idasesor + " ]";
    }
    
}
