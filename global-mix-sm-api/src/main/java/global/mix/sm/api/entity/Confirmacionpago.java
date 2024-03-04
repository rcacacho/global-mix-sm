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
@Table(name = "confirmacionpago", catalog = "global_mix_sm", schema = "")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Confirmacionpago.findAll", query = "SELECT c FROM Confirmacionpago c"),
    @NamedQuery(name = "Confirmacionpago.findByIdconfirmacionpago", query = "SELECT c FROM Confirmacionpago c WHERE c.idconfirmacionpago = :idconfirmacionpago"),
    @NamedQuery(name = "Confirmacionpago.findByNombreconfirmo", query = "SELECT c FROM Confirmacionpago c WHERE c.nombreconfirmo = :nombreconfirmo"),
    @NamedQuery(name = "Confirmacionpago.findByUsuariocreacion", query = "SELECT c FROM Confirmacionpago c WHERE c.usuariocreacion = :usuariocreacion"),
    @NamedQuery(name = "Confirmacionpago.findByFechacreacion", query = "SELECT c FROM Confirmacionpago c WHERE c.fechacreacion = :fechacreacion"),
    @NamedQuery(name = "Confirmacionpago.findByActivo", query = "SELECT c FROM Confirmacionpago c WHERE c.activo = :activo")})
public class Confirmacionpago implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "idconfirmacionpago", nullable = false)
    private Integer idconfirmacionpago;
    
    @Basic(optional = false)
    @Column(name = "nombreconfirmo", nullable = false, length = 250)
    private String nombreconfirmo;
    
    @Basic(optional = false)
    @Column(name = "usuariocreacion", nullable = false, length = 50)
    private String usuariocreacion;
    
    @Basic(optional = false)
    @Column(name = "fechacreacion", nullable = false)
    @Temporal(TemporalType.TIMESTAMP)
    private Date fechacreacion;
    
    @Basic(optional = false)
    @Column(name = "activo", nullable = false)
    private boolean activo;
    
    @JoinColumn(name = "idpedido", referencedColumnName = "idpedido", nullable = false)
    @ManyToOne(optional = false, fetch = FetchType.LAZY)
    private Pedidos idpedido;

    public Confirmacionpago() {
    }

    public Confirmacionpago(Integer idconfirmacionpago) {
        this.idconfirmacionpago = idconfirmacionpago;
    }

    public Confirmacionpago(Integer idconfirmacionpago, String nombreconfirmo, String usuariocreacion, Date fechacreacion, boolean activo) {
        this.idconfirmacionpago = idconfirmacionpago;
        this.nombreconfirmo = nombreconfirmo;
        this.usuariocreacion = usuariocreacion;
        this.fechacreacion = fechacreacion;
        this.activo = activo;
    }

    public Integer getIdconfirmacionpago() {
        return idconfirmacionpago;
    }

    public void setIdconfirmacionpago(Integer idconfirmacionpago) {
        this.idconfirmacionpago = idconfirmacionpago;
    }

    public String getNombreconfirmo() {
        return nombreconfirmo;
    }

    public void setNombreconfirmo(String nombreconfirmo) {
        this.nombreconfirmo = nombreconfirmo;
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

    public boolean getActivo() {
        return activo;
    }

    public void setActivo(boolean activo) {
        this.activo = activo;
    }

    public Pedidos getIdpedido() {
        return idpedido;
    }

    public void setIdpedido(Pedidos idpedido) {
        this.idpedido = idpedido;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idconfirmacionpago != null ? idconfirmacionpago.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Confirmacionpago)) {
            return false;
        }
        Confirmacionpago other = (Confirmacionpago) object;
        if ((this.idconfirmacionpago == null && other.idconfirmacionpago != null) || (this.idconfirmacionpago != null && !this.idconfirmacionpago.equals(other.idconfirmacionpago))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "global.mix.sm.api.entity.Confirmacionpago[ idconfirmacionpago=" + idconfirmacionpago + " ]";
    }
    
}
