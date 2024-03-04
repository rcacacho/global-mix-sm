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
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
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
@Table(name = "detallepedidonormal", catalog = "global_mix_sm", schema = "")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Detallepedidonormal.findAll", query = "SELECT d FROM Detallepedidonormal d"),
    @NamedQuery(name = "Detallepedidonormal.findByIddetallepedidonormal", query = "SELECT d FROM Detallepedidonormal d WHERE d.iddetallepedidonormal = :iddetallepedidonormal"),
    @NamedQuery(name = "Detallepedidonormal.findByIdcemento", query = "SELECT d FROM Detallepedidonormal d WHERE d.idcemento = :idcemento"),
    @NamedQuery(name = "Detallepedidonormal.findByCantidadcemento", query = "SELECT d FROM Detallepedidonormal d WHERE d.cantidadcemento = :cantidadcemento"),
    @NamedQuery(name = "Detallepedidonormal.findByIdarena", query = "SELECT d FROM Detallepedidonormal d WHERE d.idarena = :idarena"),
    @NamedQuery(name = "Detallepedidonormal.findByCantidadarena", query = "SELECT d FROM Detallepedidonormal d WHERE d.cantidadarena = :cantidadarena"),
    @NamedQuery(name = "Detallepedidonormal.findByIdpiedrin", query = "SELECT d FROM Detallepedidonormal d WHERE d.idpiedrin = :idpiedrin"),
    @NamedQuery(name = "Detallepedidonormal.findByCantidadpiedrin", query = "SELECT d FROM Detallepedidonormal d WHERE d.cantidadpiedrin = :cantidadpiedrin"),
    @NamedQuery(name = "Detallepedidonormal.findByIdaditivo", query = "SELECT d FROM Detallepedidonormal d WHERE d.idaditivo = :idaditivo"),
    @NamedQuery(name = "Detallepedidonormal.findByCantidadaditivo", query = "SELECT d FROM Detallepedidonormal d WHERE d.cantidadaditivo = :cantidadaditivo"),
    @NamedQuery(name = "Detallepedidonormal.findByIdagua", query = "SELECT d FROM Detallepedidonormal d WHERE d.idagua = :idagua"),
    @NamedQuery(name = "Detallepedidonormal.findByCantidadagua", query = "SELECT d FROM Detallepedidonormal d WHERE d.cantidadagua = :cantidadagua"),
    @NamedQuery(name = "Detallepedidonormal.findByIdotromaterial", query = "SELECT d FROM Detallepedidonormal d WHERE d.idotromaterial = :idotromaterial"),
    @NamedQuery(name = "Detallepedidonormal.findByCantidadotromaterial", query = "SELECT d FROM Detallepedidonormal d WHERE d.cantidadotromaterial = :cantidadotromaterial"),
    @NamedQuery(name = "Detallepedidonormal.findByCantidaddespachada", query = "SELECT d FROM Detallepedidonormal d WHERE d.cantidaddespachada = :cantidaddespachada"),
    @NamedQuery(name = "Detallepedidonormal.findByUsuariocreacion", query = "SELECT d FROM Detallepedidonormal d WHERE d.usuariocreacion = :usuariocreacion"),
    @NamedQuery(name = "Detallepedidonormal.findByFechacreacion", query = "SELECT d FROM Detallepedidonormal d WHERE d.fechacreacion = :fechacreacion"),
    @NamedQuery(name = "Detallepedidonormal.findByUsuariomoficacion", query = "SELECT d FROM Detallepedidonormal d WHERE d.usuariomoficacion = :usuariomoficacion"),
    @NamedQuery(name = "Detallepedidonormal.findByFechamodificacion", query = "SELECT d FROM Detallepedidonormal d WHERE d.fechamodificacion = :fechamodificacion"),
    @NamedQuery(name = "Detallepedidonormal.findByUsuarioeliminacion", query = "SELECT d FROM Detallepedidonormal d WHERE d.usuarioeliminacion = :usuarioeliminacion"),
    @NamedQuery(name = "Detallepedidonormal.findByFechaeliminacion", query = "SELECT d FROM Detallepedidonormal d WHERE d.fechaeliminacion = :fechaeliminacion"),
    @NamedQuery(name = "Detallepedidonormal.findByActivo", query = "SELECT d FROM Detallepedidonormal d WHERE d.activo = :activo")})
public class Detallepedidonormal implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "iddetallepedidonormal", nullable = false)
    private Integer iddetallepedidonormal;
    
    @Column(name = "idcemento")
    private Integer idcemento;

    @Column(name = "cantidadcemento", precision = 22)
    private Double cantidadcemento;
    
    @Column(name = "idarena")
    private Integer idarena;
    
    @Column(name = "cantidadarena", precision = 22)
    private Double cantidadarena;
    
    @Column(name = "idpiedrin")
    private Integer idpiedrin;
    
    @Column(name = "cantidadpiedrin", precision = 22)
    private Double cantidadpiedrin;
    
    @Column(name = "idaditivo")
    private Integer idaditivo;
    
    @Column(name = "cantidadaditivo", precision = 22)
    private Double cantidadaditivo;
    
    @Column(name = "idagua")
    private Integer idagua;
    
    @Column(name = "cantidadagua", precision = 22)
    private Double cantidadagua;
    
    @Column(name = "idotromaterial")
    private Integer idotromaterial;
    
    @Column(name = "cantidadotromaterial", precision = 22)
    private Double cantidadotromaterial;
    
    @Column(name = "cantidaddespachada", precision = 22)
    private Double cantidaddespachada;
    
    @Basic(optional = false)
    @Column(name = "usuariocreacion", nullable = false, length = 50)
    private String usuariocreacion;
    
    @Basic(optional = false)
    @Column(name = "fechacreacion", nullable = false)
    @Temporal(TemporalType.TIMESTAMP)
    private Date fechacreacion;
    
    @Column(name = "usuariomoficacion", length = 50)
    private String usuariomoficacion;
    
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
    
    @JoinColumn(name = "idcamion", referencedColumnName = "idcamion", nullable = false)
    @ManyToOne(optional = false, fetch = FetchType.LAZY)
    private Camion idcamion;
    
    @JoinColumn(name = "idcamion", referencedColumnName = "idmaterial", nullable = false)
    @ManyToOne(optional = false, fetch = FetchType.LAZY)
    private Material idcamion1;
    
    @JoinColumn(name = "idpedido", referencedColumnName = "idpedido", nullable = false)
    @ManyToOne(optional = false, fetch = FetchType.LAZY)
    private Pedidos idpedido;
    
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "iddetallepedidonormal", fetch = FetchType.LAZY)
    private List<Secuenciapedido> secuenciapedidoList;
    
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "iddetallepedidonormal", fetch = FetchType.LAZY)
    private List<Detallepedido> detallepedidoList;

    public Detallepedidonormal() {
    }

    public Detallepedidonormal(Integer iddetallepedidonormal) {
        this.iddetallepedidonormal = iddetallepedidonormal;
    }

    public Detallepedidonormal(Integer iddetallepedidonormal, String usuariocreacion, Date fechacreacion, boolean activo) {
        this.iddetallepedidonormal = iddetallepedidonormal;
        this.usuariocreacion = usuariocreacion;
        this.fechacreacion = fechacreacion;
        this.activo = activo;
    }

    public Integer getIddetallepedidonormal() {
        return iddetallepedidonormal;
    }

    public void setIddetallepedidonormal(Integer iddetallepedidonormal) {
        this.iddetallepedidonormal = iddetallepedidonormal;
    }

    public Integer getIdcemento() {
        return idcemento;
    }

    public void setIdcemento(Integer idcemento) {
        this.idcemento = idcemento;
    }

    public Double getCantidadcemento() {
        return cantidadcemento;
    }

    public void setCantidadcemento(Double cantidadcemento) {
        this.cantidadcemento = cantidadcemento;
    }

    public Integer getIdarena() {
        return idarena;
    }

    public void setIdarena(Integer idarena) {
        this.idarena = idarena;
    }

    public Double getCantidadarena() {
        return cantidadarena;
    }

    public void setCantidadarena(Double cantidadarena) {
        this.cantidadarena = cantidadarena;
    }

    public Integer getIdpiedrin() {
        return idpiedrin;
    }

    public void setIdpiedrin(Integer idpiedrin) {
        this.idpiedrin = idpiedrin;
    }

    public Double getCantidadpiedrin() {
        return cantidadpiedrin;
    }

    public void setCantidadpiedrin(Double cantidadpiedrin) {
        this.cantidadpiedrin = cantidadpiedrin;
    }

    public Integer getIdaditivo() {
        return idaditivo;
    }

    public void setIdaditivo(Integer idaditivo) {
        this.idaditivo = idaditivo;
    }

    public Double getCantidadaditivo() {
        return cantidadaditivo;
    }

    public void setCantidadaditivo(Double cantidadaditivo) {
        this.cantidadaditivo = cantidadaditivo;
    }

    public Integer getIdagua() {
        return idagua;
    }

    public void setIdagua(Integer idagua) {
        this.idagua = idagua;
    }

    public Double getCantidadagua() {
        return cantidadagua;
    }

    public void setCantidadagua(Double cantidadagua) {
        this.cantidadagua = cantidadagua;
    }

    public Integer getIdotromaterial() {
        return idotromaterial;
    }

    public void setIdotromaterial(Integer idotromaterial) {
        this.idotromaterial = idotromaterial;
    }

    public Double getCantidadotromaterial() {
        return cantidadotromaterial;
    }

    public void setCantidadotromaterial(Double cantidadotromaterial) {
        this.cantidadotromaterial = cantidadotromaterial;
    }

    public Double getCantidaddespachada() {
        return cantidaddespachada;
    }

    public void setCantidaddespachada(Double cantidaddespachada) {
        this.cantidaddespachada = cantidaddespachada;
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

    public String getUsuariomoficacion() {
        return usuariomoficacion;
    }

    public void setUsuariomoficacion(String usuariomoficacion) {
        this.usuariomoficacion = usuariomoficacion;
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

    public Camion getIdcamion() {
        return idcamion;
    }

    public void setIdcamion(Camion idcamion) {
        this.idcamion = idcamion;
    }

    public Material getIdcamion1() {
        return idcamion1;
    }

    public void setIdcamion1(Material idcamion1) {
        this.idcamion1 = idcamion1;
    }

    public Pedidos getIdpedido() {
        return idpedido;
    }

    public void setIdpedido(Pedidos idpedido) {
        this.idpedido = idpedido;
    }

    @XmlTransient
    public List<Secuenciapedido> getSecuenciapedidoList() {
        return secuenciapedidoList;
    }

    public void setSecuenciapedidoList(List<Secuenciapedido> secuenciapedidoList) {
        this.secuenciapedidoList = secuenciapedidoList;
    }

    @XmlTransient
    public List<Detallepedido> getDetallepedidoList() {
        return detallepedidoList;
    }

    public void setDetallepedidoList(List<Detallepedido> detallepedidoList) {
        this.detallepedidoList = detallepedidoList;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (iddetallepedidonormal != null ? iddetallepedidonormal.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Detallepedidonormal)) {
            return false;
        }
        Detallepedidonormal other = (Detallepedidonormal) object;
        if ((this.iddetallepedidonormal == null && other.iddetallepedidonormal != null) || (this.iddetallepedidonormal != null && !this.iddetallepedidonormal.equals(other.iddetallepedidonormal))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "global.mix.sm.api.entity.Detallepedidonormal[ iddetallepedidonormal=" + iddetallepedidonormal + " ]";
    }
    
}
