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
@Table(name = "material", catalog = "global_mix_sm", schema = "")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Material.findAll", query = "SELECT m FROM Material m"),
    @NamedQuery(name = "Material.findByIdmaterial", query = "SELECT m FROM Material m WHERE m.idmaterial = :idmaterial"),
    @NamedQuery(name = "Material.findByMaterial", query = "SELECT m FROM Material m WHERE m.material = :material"),
    @NamedQuery(name = "Material.findByExistenciainicial", query = "SELECT m FROM Material m WHERE m.existenciainicial = :existenciainicial"),
    @NamedQuery(name = "Material.findByValor", query = "SELECT m FROM Material m WHERE m.valor = :valor"),
    @NamedQuery(name = "Material.findByValorneto", query = "SELECT m FROM Material m WHERE m.valorneto = :valorneto"),
    @NamedQuery(name = "Material.findByCosto", query = "SELECT m FROM Material m WHERE m.costo = :costo"),
    @NamedQuery(name = "Material.findByFechacreacion", query = "SELECT m FROM Material m WHERE m.fechacreacion = :fechacreacion"),
    @NamedQuery(name = "Material.findByUsuariocreacion", query = "SELECT m FROM Material m WHERE m.usuariocreacion = :usuariocreacion"),
    @NamedQuery(name = "Material.findByFechamodificacion", query = "SELECT m FROM Material m WHERE m.fechamodificacion = :fechamodificacion"),
    @NamedQuery(name = "Material.findByUsuariomodificacion", query = "SELECT m FROM Material m WHERE m.usuariomodificacion = :usuariomodificacion"),
    @NamedQuery(name = "Material.findByFechaeliminacion", query = "SELECT m FROM Material m WHERE m.fechaeliminacion = :fechaeliminacion"),
    @NamedQuery(name = "Material.findByUsuarioeliminacion", query = "SELECT m FROM Material m WHERE m.usuarioeliminacion = :usuarioeliminacion"),
    @NamedQuery(name = "Material.findByActivo", query = "SELECT m FROM Material m WHERE m.activo = :activo")})
public class Material implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "idmaterial", nullable = false)
    private Integer idmaterial;

    @Basic(optional = false)
    @Column(name = "material", nullable = false, length = 1000)
    private String material;

    @Column(name = "existenciainicial", precision = 22)
    private Double existenciainicial;

    @Basic(optional = false)
    @Column(name = "valor", nullable = false)
    private Double valor;

    @Basic(optional = false)
    @Column(name = "valorneto", nullable = false)
    private Double valorneto;

    @Column(name = "costo", precision = 22)
    private Double costo;

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

    @OneToMany(cascade = CascadeType.ALL, mappedBy = "idmaterial", fetch = FetchType.LAZY)
    private List<Detallematerial> detallematerialList;

    @JoinColumn(name = "idunidadmedida", referencedColumnName = "idunidadmedida", nullable = false)
    @ManyToOne(optional = false, fetch = FetchType.LAZY)
    private Unidadmedida idunidadmedida;

    @OneToMany(cascade = CascadeType.ALL, mappedBy = "idmaterial", fetch = FetchType.LAZY)
    private List<Detallepedido> detallepedidoList;

    public Material() {
    }

    public Material(Integer idmaterial) {
        this.idmaterial = idmaterial;
    }

    public Material(Integer idmaterial, String material, Double valor, Double valorneto, Date fechacreacion, String usuariocreacion, boolean activo) {
        this.idmaterial = idmaterial;
        this.material = material;
        this.valor = valor;
        this.valorneto = valorneto;
        this.fechacreacion = fechacreacion;
        this.usuariocreacion = usuariocreacion;
        this.activo = activo;
    }

    public Integer getIdmaterial() {
        return idmaterial;
    }

    public void setIdmaterial(Integer idmaterial) {
        this.idmaterial = idmaterial;
    }

    public String getMaterial() {
        return material;
    }

    public void setMaterial(String material) {
        this.material = material;
    }

    public Double getExistenciainicial() {
        return existenciainicial;
    }

    public void setExistenciainicial(Double existenciainicial) {
        this.existenciainicial = existenciainicial;
    }

    public Double getValor() {
        return valor;
    }

    public void setValor(Double valor) {
        this.valor = valor;
    }

    public Double getValorneto() {
        return valorneto;
    }

    public void setValorneto(Double valorneto) {
        this.valorneto = valorneto;
    }

    public Double getCosto() {
        return costo;
    }

    public void setCosto(Double costo) {
        this.costo = costo;
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

    @XmlTransient
    public List<Detallematerial> getDetallematerialList() {
        return detallematerialList;
    }

    public void setDetallematerialList(List<Detallematerial> detallematerialList) {
        this.detallematerialList = detallematerialList;
    }

    public Unidadmedida getIdunidadmedida() {
        return idunidadmedida;
    }

    public void setIdunidadmedida(Unidadmedida idunidadmedida) {
        this.idunidadmedida = idunidadmedida;
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
        hash += (idmaterial != null ? idmaterial.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Material)) {
            return false;
        }
        Material other = (Material) object;
        if ((this.idmaterial == null && other.idmaterial != null) || (this.idmaterial != null && !this.idmaterial.equals(other.idmaterial))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "global.mix.sm.api.entity.Material[ idmaterial=" + idmaterial + " ]";
    }

}
