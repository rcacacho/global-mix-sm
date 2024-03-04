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
@Table(name = "usuario", catalog = "global_mix_sm", schema = "")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Usuario.findAll", query = "SELECT u FROM Usuario u"),
    @NamedQuery(name = "Usuario.findByIdusuario", query = "SELECT u FROM Usuario u WHERE u.idusuario = :idusuario"),
    @NamedQuery(name = "Usuario.findByNombres", query = "SELECT u FROM Usuario u WHERE u.nombres = :nombres"),
    @NamedQuery(name = "Usuario.findByApellidos", query = "SELECT u FROM Usuario u WHERE u.apellidos = :apellidos"),
    @NamedQuery(name = "Usuario.findByUsuario", query = "SELECT u FROM Usuario u WHERE u.usuario = :usuario"),
    @NamedQuery(name = "Usuario.findByPassword", query = "SELECT u FROM Usuario u WHERE u.password = :password"),
    @NamedQuery(name = "Usuario.findByFechacreacion", query = "SELECT u FROM Usuario u WHERE u.fechacreacion = :fechacreacion"),
    @NamedQuery(name = "Usuario.findByUsuariocreacion", query = "SELECT u FROM Usuario u WHERE u.usuariocreacion = :usuariocreacion"),
    @NamedQuery(name = "Usuario.findByFechamodificacion", query = "SELECT u FROM Usuario u WHERE u.fechamodificacion = :fechamodificacion"),
    @NamedQuery(name = "Usuario.findByUsuariomodificacion", query = "SELECT u FROM Usuario u WHERE u.usuariomodificacion = :usuariomodificacion"),
    @NamedQuery(name = "Usuario.findByFechaeliminacion", query = "SELECT u FROM Usuario u WHERE u.fechaeliminacion = :fechaeliminacion"),
    @NamedQuery(name = "Usuario.findByUsuarioeliminacion", query = "SELECT u FROM Usuario u WHERE u.usuarioeliminacion = :usuarioeliminacion"),
    @NamedQuery(name = "Usuario.findByActivo", query = "SELECT u FROM Usuario u WHERE u.activo = :activo")})
public class Usuario implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "idusuario", nullable = false)
    private Integer idusuario;
    
    @Basic(optional = false)
    @Column(name = "nombres", nullable = false, length = 500)
    private String nombres;
    
    @Basic(optional = false)
    @Column(name = "apellidos", nullable = false, length = 500)
    private String apellidos;
    
    @Basic(optional = false)
    @Column(name = "usuario", nullable = false, length = 50)
    private String usuario;
    
    @Basic(optional = false)
    @Column(name = "password", nullable = false, length = 50)
    private String password;
    
    @Basic(optional = false)
    @Column(name = "fechacreacion", nullable = false)
    @Temporal(TemporalType.TIMESTAMP)
    private Date fechacreacion;
    
    @Basic(optional = false)
    @Column(name = "usuariocreacion", nullable = false, length = 50)
    private String usuariocreacion;
    
    @Column(name = "fechamodificacion")
    @Temporal(TemporalType.TIMESTAMP)
    private Date fechamodificacion;
    
    @Column(name = "usuariomodificacion", length = 50)
    private String usuariomodificacion;
    
    @Column(name = "fechaeliminacion")
    @Temporal(TemporalType.TIMESTAMP)
    private Date fechaeliminacion;
    
    @Column(name = "usuarioeliminacion", length = 50)
    private String usuarioeliminacion;
    
    @Basic(optional = false)
    @Column(name = "activo", nullable = false)
    private boolean activo;
    
    @JoinColumn(name = "idtipousuario", referencedColumnName = "idtipousuario", nullable = false)
    @ManyToOne(optional = false, fetch = FetchType.LAZY)
    private Tipousuario idtipousuario;

    public Usuario() {
    }

    public Usuario(Integer idusuario) {
        this.idusuario = idusuario;
    }

    public Usuario(Integer idusuario, String nombres, String apellidos, String usuario, String password, Date fechacreacion, String usuariocreacion, boolean activo) {
        this.idusuario = idusuario;
        this.nombres = nombres;
        this.apellidos = apellidos;
        this.usuario = usuario;
        this.password = password;
        this.fechacreacion = fechacreacion;
        this.usuariocreacion = usuariocreacion;
        this.activo = activo;
    }

    public Integer getIdusuario() {
        return idusuario;
    }

    public void setIdusuario(Integer idusuario) {
        this.idusuario = idusuario;
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

    public String getUsuario() {
        return usuario;
    }

    public void setUsuario(String usuario) {
        this.usuario = usuario;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
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

    public Tipousuario getIdtipousuario() {
        return idtipousuario;
    }

    public void setIdtipousuario(Tipousuario idtipousuario) {
        this.idtipousuario = idtipousuario;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idusuario != null ? idusuario.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Usuario)) {
            return false;
        }
        Usuario other = (Usuario) object;
        if ((this.idusuario == null && other.idusuario != null) || (this.idusuario != null && !this.idusuario.equals(other.idusuario))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "global.mix.sm.api.entity.Usuario[ idusuario=" + idusuario + " ]";
    }
    
}
