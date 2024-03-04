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
@Table(name = "secuenciapedido", catalog = "global_mix_sm", schema = "")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Secuenciapedido.findAll", query = "SELECT s FROM Secuenciapedido s"),
    @NamedQuery(name = "Secuenciapedido.findByIdsecuenciapedido", query = "SELECT s FROM Secuenciapedido s WHERE s.idsecuenciapedido = :idsecuenciapedido"),
    @NamedQuery(name = "Secuenciapedido.findBySecuencia", query = "SELECT s FROM Secuenciapedido s WHERE s.secuencia = :secuencia"),
    @NamedQuery(name = "Secuenciapedido.findByCorrelativo", query = "SELECT s FROM Secuenciapedido s WHERE s.correlativo = :correlativo"),
    @NamedQuery(name = "Secuenciapedido.findByFechacreacion", query = "SELECT s FROM Secuenciapedido s WHERE s.fechacreacion = :fechacreacion"),
    @NamedQuery(name = "Secuenciapedido.findByUsuariocreacion", query = "SELECT s FROM Secuenciapedido s WHERE s.usuariocreacion = :usuariocreacion"),
    @NamedQuery(name = "Secuenciapedido.findByFechaeliminacion", query = "SELECT s FROM Secuenciapedido s WHERE s.fechaeliminacion = :fechaeliminacion"),
    @NamedQuery(name = "Secuenciapedido.findByUsuarioeliminacion", query = "SELECT s FROM Secuenciapedido s WHERE s.usuarioeliminacion = :usuarioeliminacion"),
    @NamedQuery(name = "Secuenciapedido.findByActivo", query = "SELECT s FROM Secuenciapedido s WHERE s.activo = :activo")})
public class Secuenciapedido implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "idsecuenciapedido", nullable = false)
    private Integer idsecuenciapedido;
    
    @Basic(optional = false)
    @Column(name = "secuencia", nullable = false)
    private Integer secuencia;
    
    @Basic(optional = false)
    @Column(name = "correlativo", nullable = false, length = 250)
    private String correlativo;
    
    @Basic(optional = false)
    @Column(name = "fechacreacion", nullable = false)
    @Temporal(TemporalType.TIMESTAMP)
    private Date fechacreacion;
    
    @Basic(optional = false)
    @Column(name = "usuariocreacion", nullable = false, length = 50)
    private String usuariocreacion;
    
    @Column(name = "fechaeliminacion")
    @Temporal(TemporalType.TIMESTAMP)
    private Date fechaeliminacion;
    
    @Column(name = "usuarioeliminacion", length = 50)
    private String usuarioeliminacion;
    
    @Basic(optional = false)
    @Column(name = "activo", nullable = false)
    private boolean activo;
    
    @JoinColumn(name = "iddetallepedidonormal", referencedColumnName = "iddetallepedidonormal", nullable = false)
    @ManyToOne(optional = false, fetch = FetchType.LAZY)
    private Detallepedidonormal iddetallepedidonormal;

    public Secuenciapedido() {
    }

    public Secuenciapedido(Integer idsecuenciapedido) {
        this.idsecuenciapedido = idsecuenciapedido;
    }

    public Secuenciapedido(Integer idsecuenciapedido, Integer secuencia, String correlativo, Date fechacreacion, String usuariocreacion, boolean activo) {
        this.idsecuenciapedido = idsecuenciapedido;
        this.secuencia = secuencia;
        this.correlativo = correlativo;
        this.fechacreacion = fechacreacion;
        this.usuariocreacion = usuariocreacion;
        this.activo = activo;
    }

    public Integer getIdsecuenciapedido() {
        return idsecuenciapedido;
    }

    public void setIdsecuenciapedido(Integer idsecuenciapedido) {
        this.idsecuenciapedido = idsecuenciapedido;
    }

    public Integer getSecuencia() {
        return secuencia;
    }

    public void setSecuencia(Integer secuencia) {
        this.secuencia = secuencia;
    }

    public String getCorrelativo() {
        return correlativo;
    }

    public void setCorrelativo(String correlativo) {
        this.correlativo = correlativo;
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

    public Detallepedidonormal getIddetallepedidonormal() {
        return iddetallepedidonormal;
    }

    public void setIddetallepedidonormal(Detallepedidonormal iddetallepedidonormal) {
        this.iddetallepedidonormal = iddetallepedidonormal;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idsecuenciapedido != null ? idsecuenciapedido.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Secuenciapedido)) {
            return false;
        }
        Secuenciapedido other = (Secuenciapedido) object;
        if ((this.idsecuenciapedido == null && other.idsecuenciapedido != null) || (this.idsecuenciapedido != null && !this.idsecuenciapedido.equals(other.idsecuenciapedido))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "global.mix.sm.api.entity.Secuenciapedido[ idsecuenciapedido=" + idsecuenciapedido + " ]";
    }
    
}
