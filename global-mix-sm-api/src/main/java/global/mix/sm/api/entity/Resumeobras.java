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
@Table(name = "resumeobras", catalog = "global_mix_sm", schema = "")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Resumeobras.findAll", query = "SELECT r FROM Resumeobras r"),
    @NamedQuery(name = "Resumeobras.findByIdresumen", query = "SELECT r FROM Resumeobras r WHERE r.idresumen = :idresumen"),
    @NamedQuery(name = "Resumeobras.findByCliente", query = "SELECT r FROM Resumeobras r WHERE r.cliente = :cliente"),
    @NamedQuery(name = "Resumeobras.findByObra", query = "SELECT r FROM Resumeobras r WHERE r.obra = :obra"),
    @NamedQuery(name = "Resumeobras.findByMetrosencargados", query = "SELECT r FROM Resumeobras r WHERE r.metrosencargados = :metrosencargados"),
    @NamedQuery(name = "Resumeobras.findByMetrosvendidos", query = "SELECT r FROM Resumeobras r WHERE r.metrosvendidos = :metrosvendidos"),
    @NamedQuery(name = "Resumeobras.findByTotalmateriaprima", query = "SELECT r FROM Resumeobras r WHERE r.totalmateriaprima = :totalmateriaprima"),
    @NamedQuery(name = "Resumeobras.findByTotaldiesel", query = "SELECT r FROM Resumeobras r WHERE r.totaldiesel = :totaldiesel"),
    @NamedQuery(name = "Resumeobras.findByTotalbombeo", query = "SELECT r FROM Resumeobras r WHERE r.totalbombeo = :totalbombeo"),
    @NamedQuery(name = "Resumeobras.findByTotalcolocado", query = "SELECT r FROM Resumeobras r WHERE r.totalcolocado = :totalcolocado"),
    @NamedQuery(name = "Resumeobras.findByTotalcomision", query = "SELECT r FROM Resumeobras r WHERE r.totalcomision = :totalcomision"),
    @NamedQuery(name = "Resumeobras.findByTotalingresoneto", query = "SELECT r FROM Resumeobras r WHERE r.totalingresoneto = :totalingresoneto"),
    @NamedQuery(name = "Resumeobras.findByBeneficioprimo", query = "SELECT r FROM Resumeobras r WHERE r.beneficioprimo = :beneficioprimo"),
    @NamedQuery(name = "Resumeobras.findByCompras", query = "SELECT r FROM Resumeobras r WHERE r.compras = :compras"),
    @NamedQuery(name = "Resumeobras.findByBeneficio", query = "SELECT r FROM Resumeobras r WHERE r.beneficio = :beneficio"),
    @NamedQuery(name = "Resumeobras.findByUsuariocreacion", query = "SELECT r FROM Resumeobras r WHERE r.usuariocreacion = :usuariocreacion"),
    @NamedQuery(name = "Resumeobras.findByFechacreacion", query = "SELECT r FROM Resumeobras r WHERE r.fechacreacion = :fechacreacion"),
    @NamedQuery(name = "Resumeobras.findByUsuariomodificacion", query = "SELECT r FROM Resumeobras r WHERE r.usuariomodificacion = :usuariomodificacion"),
    @NamedQuery(name = "Resumeobras.findByFechamodificacion", query = "SELECT r FROM Resumeobras r WHERE r.fechamodificacion = :fechamodificacion"),
    @NamedQuery(name = "Resumeobras.findByUsuarioeliminacion", query = "SELECT r FROM Resumeobras r WHERE r.usuarioeliminacion = :usuarioeliminacion"),
    @NamedQuery(name = "Resumeobras.findByFechaeliminacion", query = "SELECT r FROM Resumeobras r WHERE r.fechaeliminacion = :fechaeliminacion"),
    @NamedQuery(name = "Resumeobras.findByActivo", query = "SELECT r FROM Resumeobras r WHERE r.activo = :activo")})
public class Resumeobras implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "idresumen", nullable = false)
    private Integer idresumen;
    
    @Column(name = "cliente", length = 250)
    private String cliente;
    
    @Column(name = "obra", length = 1000)
    private String obra;

    @Column(name = "metrosencargados", precision = 22)
    private Double metrosencargados;
    
    @Column(name = "metrosvendidos", precision = 22)
    private Double metrosvendidos;
    
    @Column(name = "totalmateriaprima", precision = 22)
    private Double totalmateriaprima;
    
    @Column(name = "totaldiesel", precision = 22)
    private Double totaldiesel;
    
    @Column(name = "totalbombeo", precision = 22)
    private Double totalbombeo;
    
    @Column(name = "totalcolocado", precision = 22)
    private Double totalcolocado;
    
    @Column(name = "totalcomision", precision = 22)
    private Double totalcomision;
    
    @Column(name = "totalingresoneto", precision = 22)
    private Double totalingresoneto;
    
    @Column(name = "beneficioprimo", precision = 22)
    private Double beneficioprimo;
    
    @Column(name = "compras", precision = 22)
    private Double compras;
    
    @Column(name = "beneficio")
    private Double beneficio;
    
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

    public Resumeobras() {
    }

    public Resumeobras(Integer idresumen) {
        this.idresumen = idresumen;
    }

    public Resumeobras(Integer idresumen, String usuariocreacion, Date fechacreacion, boolean activo) {
        this.idresumen = idresumen;
        this.usuariocreacion = usuariocreacion;
        this.fechacreacion = fechacreacion;
        this.activo = activo;
    }

    public Integer getIdresumen() {
        return idresumen;
    }

    public void setIdresumen(Integer idresumen) {
        this.idresumen = idresumen;
    }

    public String getCliente() {
        return cliente;
    }

    public void setCliente(String cliente) {
        this.cliente = cliente;
    }

    public String getObra() {
        return obra;
    }

    public void setObra(String obra) {
        this.obra = obra;
    }

    public Double getMetrosencargados() {
        return metrosencargados;
    }

    public void setMetrosencargados(Double metrosencargados) {
        this.metrosencargados = metrosencargados;
    }

    public Double getMetrosvendidos() {
        return metrosvendidos;
    }

    public void setMetrosvendidos(Double metrosvendidos) {
        this.metrosvendidos = metrosvendidos;
    }

    public Double getTotalmateriaprima() {
        return totalmateriaprima;
    }

    public void setTotalmateriaprima(Double totalmateriaprima) {
        this.totalmateriaprima = totalmateriaprima;
    }

    public Double getTotaldiesel() {
        return totaldiesel;
    }

    public void setTotaldiesel(Double totaldiesel) {
        this.totaldiesel = totaldiesel;
    }

    public Double getTotalbombeo() {
        return totalbombeo;
    }

    public void setTotalbombeo(Double totalbombeo) {
        this.totalbombeo = totalbombeo;
    }

    public Double getTotalcolocado() {
        return totalcolocado;
    }

    public void setTotalcolocado(Double totalcolocado) {
        this.totalcolocado = totalcolocado;
    }

    public Double getTotalcomision() {
        return totalcomision;
    }

    public void setTotalcomision(Double totalcomision) {
        this.totalcomision = totalcomision;
    }

    public Double getTotalingresoneto() {
        return totalingresoneto;
    }

    public void setTotalingresoneto(Double totalingresoneto) {
        this.totalingresoneto = totalingresoneto;
    }

    public Double getBeneficioprimo() {
        return beneficioprimo;
    }

    public void setBeneficioprimo(Double beneficioprimo) {
        this.beneficioprimo = beneficioprimo;
    }

    public Double getCompras() {
        return compras;
    }

    public void setCompras(Double compras) {
        this.compras = compras;
    }

    public Double getBeneficio() {
        return beneficio;
    }

    public void setBeneficio(Double beneficio) {
        this.beneficio = beneficio;
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

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idresumen != null ? idresumen.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Resumeobras)) {
            return false;
        }
        Resumeobras other = (Resumeobras) object;
        if ((this.idresumen == null && other.idresumen != null) || (this.idresumen != null && !this.idresumen.equals(other.idresumen))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "global.mix.sm.api.entity.Resumeobras[ idresumen=" + idresumen + " ]";
    }
    
}
