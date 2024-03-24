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
@Table(name = "pedidos", catalog = "global_mix_sm", schema = "")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Pedidos.findAll", query = "SELECT p FROM Pedidos p"),
    @NamedQuery(name = "Pedidos.findByIdpedido", query = "SELECT p FROM Pedidos p WHERE p.idpedido = :idpedido"),
    @NamedQuery(name = "Pedidos.findByTipopedido", query = "SELECT p FROM Pedidos p WHERE p.tipopedido = :tipopedido"),
    @NamedQuery(name = "Pedidos.findByObra", query = "SELECT p FROM Pedidos p WHERE p.obra = :obra"),
    @NamedQuery(name = "Pedidos.findByElemento", query = "SELECT p FROM Pedidos p WHERE p.elemento = :elemento"),
    @NamedQuery(name = "Pedidos.findByVolumen", query = "SELECT p FROM Pedidos p WHERE p.volumen = :volumen"),
    @NamedQuery(name = "Pedidos.findByFraguado", query = "SELECT p FROM Pedidos p WHERE p.fraguado = :fraguado"),
    @NamedQuery(name = "Pedidos.findByCantidadpagada", query = "SELECT p FROM Pedidos p WHERE p.cantidadpagada = :cantidadpagada"),
    @NamedQuery(name = "Pedidos.findByKgcm3", query = "SELECT p FROM Pedidos p WHERE p.kgcm3 = :kgcm3"),
    @NamedQuery(name = "Pedidos.findByAgregado", query = "SELECT p FROM Pedidos p WHERE p.agregado = :agregado"),
    @NamedQuery(name = "Pedidos.findByRevpulg", query = "SELECT p FROM Pedidos p WHERE p.revpulg = :revpulg"),
    @NamedQuery(name = "Pedidos.findByFrec", query = "SELECT p FROM Pedidos p WHERE p.frec = :frec"),
    @NamedQuery(name = "Pedidos.findByBombeo", query = "SELECT p FROM Pedidos p WHERE p.bombeo = :bombeo"),
    @NamedQuery(name = "Pedidos.findByDirbom", query = "SELECT p FROM Pedidos p WHERE p.dirbom = :dirbom"),
    @NamedQuery(name = "Pedidos.findByColocado", query = "SELECT p FROM Pedidos p WHERE p.colocado = :colocado"),
    @NamedQuery(name = "Pedidos.findByTipocolocado", query = "SELECT p FROM Pedidos p WHERE p.tipocolocado = :tipocolocado"),
    @NamedQuery(name = "Pedidos.findByCantidadcobradacolocado", query = "SELECT p FROM Pedidos p WHERE p.cantidadcobradacolocado = :cantidadcobradacolocado"),
    @NamedQuery(name = "Pedidos.findByColocador", query = "SELECT p FROM Pedidos p WHERE p.colocador = :colocador"),
    @NamedQuery(name = "Pedidos.findByTuberia", query = "SELECT p FROM Pedidos p WHERE p.tuberia = :tuberia"),
    @NamedQuery(name = "Pedidos.findByLaboratorio", query = "SELECT p FROM Pedidos p WHERE p.laboratorio = :laboratorio"),
    @NamedQuery(name = "Pedidos.findByConfirmado", query = "SELECT p FROM Pedidos p WHERE p.confirmado = :confirmado"),
    @NamedQuery(name = "Pedidos.findByFechapedido", query = "SELECT p FROM Pedidos p WHERE p.fechapedido = :fechapedido"),
    @NamedQuery(name = "Pedidos.findByUsuariocreacion", query = "SELECT p FROM Pedidos p WHERE p.usuariocreacion = :usuariocreacion"),
    @NamedQuery(name = "Pedidos.findByFechacreacion", query = "SELECT p FROM Pedidos p WHERE p.fechacreacion = :fechacreacion"),
    @NamedQuery(name = "Pedidos.findByUsuariomodificacion", query = "SELECT p FROM Pedidos p WHERE p.usuariomodificacion = :usuariomodificacion"),
    @NamedQuery(name = "Pedidos.findByFechamodificacion", query = "SELECT p FROM Pedidos p WHERE p.fechamodificacion = :fechamodificacion"),
    @NamedQuery(name = "Pedidos.findByUsuarioeliminacion", query = "SELECT p FROM Pedidos p WHERE p.usuarioeliminacion = :usuarioeliminacion"),
    @NamedQuery(name = "Pedidos.findByFechaeliminacion", query = "SELECT p FROM Pedidos p WHERE p.fechaeliminacion = :fechaeliminacion"),
    @NamedQuery(name = "Pedidos.findByActivo", query = "SELECT p FROM Pedidos p WHERE p.activo = :activo")})
public class Pedidos implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "idpedido", nullable = false)
    private Integer idpedido;

    @Column(name = "tipopedido")
    private Integer tipopedido;

    @Basic(optional = false)
    @Column(name = "obra", nullable = false, length = 500)
    private String obra;

    @Basic(optional = false)
    @Column(name = "elemento", nullable = false, length = 100)
    private String elemento;

    @Basic(optional = false)
    @Column(name = "volumen", nullable = false)
    private double volumen;

    @Column(name = "fraguado")
    private Integer fraguado;

    @Column(name = "cantidadpagada", precision = 22)
    private Double cantidadpagada;

    @Column(name = "kgcm3")
    private Integer kgcm3;

    @Column(name = "agregado", length = 100)
    private String agregado;

    @Column(name = "revpulg")
    private Integer revpulg;

    @Column(name = "frec")
    private Integer frec;

    @Basic(optional = false)
    @Column(name = "bombeo", nullable = false, length = 25)
    private String bombeo;

    @Column(name = "dirbom", length = 50)
    private String dirbom;

    @Column(name = "colocado", length = 25)
    private String colocado;

    @Column(name = "tipocolocado", length = 75)
    private String tipocolocado;

    @Column(name = "cantidadcobradacolocado", precision = 22)
    private Double cantidadcobradacolocado;

    @Column(name = "colocador", length = 50)
    private String colocador;

    @Column(name = "tuberia")
    private Integer tuberia;

    @Column(name = "laboratorio", length = 50)
    private String laboratorio;

    @Column(name = "confirmado")
    private Boolean confirmado;

    @Column(name = "fechapedido")
    @Temporal(TemporalType.TIMESTAMP)
    private Date fechapedido;

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

    @JoinColumn(name = "idasesor", referencedColumnName = "idasesor", nullable = false)
    @ManyToOne(optional = false, fetch = FetchType.LAZY)
    private Asesor idasesor;

    @JoinColumn(name = "idcliente", referencedColumnName = "idcliente", nullable = false)
    @ManyToOne(optional = false, fetch = FetchType.LAZY)
    private Cliente idcliente;

    @JoinColumn(name = "idestadopedido", referencedColumnName = "idestadopedido", nullable = false)
    @ManyToOne(optional = false, fetch = FetchType.LAZY)
    private Estadopedido idestadopedido;

    @JoinColumn(name = "idtipopago", referencedColumnName = "idtipopago", nullable = false)
    @ManyToOne(optional = false, fetch = FetchType.LAZY)
    private Tipopago idtipopago;

    @JoinColumn(name = "idtipocemento", referencedColumnName = "idtipocemento", nullable = false)
    @ManyToOne(optional = false, fetch = FetchType.LAZY)
    private Tipocemento idtipocemento;

    @OneToMany(cascade = CascadeType.ALL, mappedBy = "idpedido", fetch = FetchType.LAZY)
    private List<Despachos> despachosList;

    @OneToMany(cascade = CascadeType.ALL, mappedBy = "idpedido", fetch = FetchType.LAZY)
    private List<Detallepedidonormal> detallepedidonormalList;

    @OneToMany(cascade = CascadeType.ALL, mappedBy = "idpedido", fetch = FetchType.LAZY)
    private List<Confirmacionpago> confirmacionpagoList;

    public Pedidos() {
    }

    public Pedidos(Integer idpedido) {
        this.idpedido = idpedido;
    }

    public Pedidos(Integer idpedido, String obra, String elemento, double volumen, String bombeo, String usuariocreacion, Date fechacreacion, boolean activo) {
        this.idpedido = idpedido;
        this.obra = obra;
        this.elemento = elemento;
        this.volumen = volumen;
        this.bombeo = bombeo;
        this.usuariocreacion = usuariocreacion;
        this.fechacreacion = fechacreacion;
        this.activo = activo;
    }

    public Integer getIdpedido() {
        return idpedido;
    }

    public void setIdpedido(Integer idpedido) {
        this.idpedido = idpedido;
    }

    public Integer getTipopedido() {
        return tipopedido;
    }

    public void setTipopedido(Integer tipopedido) {
        this.tipopedido = tipopedido;
    }

    public String getObra() {
        return obra;
    }

    public void setObra(String obra) {
        this.obra = obra;
    }

    public String getElemento() {
        return elemento;
    }

    public void setElemento(String elemento) {
        this.elemento = elemento;
    }

    public double getVolumen() {
        return volumen;
    }

    public void setVolumen(double volumen) {
        this.volumen = volumen;
    }

    public Integer getFraguado() {
        return fraguado;
    }

    public void setFraguado(Integer fraguado) {
        this.fraguado = fraguado;
    }

    public Double getCantidadpagada() {
        return cantidadpagada;
    }

    public void setCantidadpagada(Double cantidadpagada) {
        this.cantidadpagada = cantidadpagada;
    }

    public Integer getKgcm3() {
        return kgcm3;
    }

    public void setKgcm3(Integer kgcm3) {
        this.kgcm3 = kgcm3;
    }

    public String getAgregado() {
        return agregado;
    }

    public void setAgregado(String agregado) {
        this.agregado = agregado;
    }

    public Integer getRevpulg() {
        return revpulg;
    }

    public void setRevpulg(Integer revpulg) {
        this.revpulg = revpulg;
    }

    public Integer getFrec() {
        return frec;
    }

    public void setFrec(Integer frec) {
        this.frec = frec;
    }

    public String getBombeo() {
        return bombeo;
    }

    public void setBombeo(String bombeo) {
        this.bombeo = bombeo;
    }

    public String getDirbom() {
        return dirbom;
    }

    public void setDirbom(String dirbom) {
        this.dirbom = dirbom;
    }

    public String getColocado() {
        return colocado;
    }

    public void setColocado(String colocado) {
        this.colocado = colocado;
    }

    public String getTipocolocado() {
        return tipocolocado;
    }

    public void setTipocolocado(String tipocolocado) {
        this.tipocolocado = tipocolocado;
    }

    public Double getCantidadcobradacolocado() {
        return cantidadcobradacolocado;
    }

    public void setCantidadcobradacolocado(Double cantidadcobradacolocado) {
        this.cantidadcobradacolocado = cantidadcobradacolocado;
    }

    public String getColocador() {
        return colocador;
    }

    public void setColocador(String colocador) {
        this.colocador = colocador;
    }

    public Integer getTuberia() {
        return tuberia;
    }

    public void setTuberia(Integer tuberia) {
        this.tuberia = tuberia;
    }

    public String getLaboratorio() {
        return laboratorio;
    }

    public void setLaboratorio(String laboratorio) {
        this.laboratorio = laboratorio;
    }

    public Boolean getConfirmado() {
        return confirmado;
    }

    public void setConfirmado(Boolean confirmado) {
        this.confirmado = confirmado;
    }

    public Date getFechapedido() {
        return fechapedido;
    }

    public void setFechapedido(Date fechapedido) {
        this.fechapedido = fechapedido;
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

    public Asesor getIdasesor() {
        return idasesor;
    }

    public void setIdasesor(Asesor idasesor) {
        this.idasesor = idasesor;
    }

    public Cliente getIdcliente() {
        return idcliente;
    }

    public void setIdcliente(Cliente idcliente) {
        this.idcliente = idcliente;
    }

    public Estadopedido getIdestadopedido() {
        return idestadopedido;
    }

    public void setIdestadopedido(Estadopedido idestadopedido) {
        this.idestadopedido = idestadopedido;
    }

    public Tipopago getIdtipopago() {
        return idtipopago;
    }

    public void setIdtipopago(Tipopago idtipopago) {
        this.idtipopago = idtipopago;
    }

    @XmlTransient
    public List<Despachos> getDespachosList() {
        return despachosList;
    }

    public void setDespachosList(List<Despachos> despachosList) {
        this.despachosList = despachosList;
    }

    @XmlTransient
    public List<Detallepedidonormal> getDetallepedidonormalList() {
        return detallepedidonormalList;
    }

    public void setDetallepedidonormalList(List<Detallepedidonormal> detallepedidonormalList) {
        this.detallepedidonormalList = detallepedidonormalList;
    }

    @XmlTransient
    public List<Confirmacionpago> getConfirmacionpagoList() {
        return confirmacionpagoList;
    }

    public void setConfirmacionpagoList(List<Confirmacionpago> confirmacionpagoList) {
        this.confirmacionpagoList = confirmacionpagoList;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idpedido != null ? idpedido.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Pedidos)) {
            return false;
        }
        Pedidos other = (Pedidos) object;
        if ((this.idpedido == null && other.idpedido != null) || (this.idpedido != null && !this.idpedido.equals(other.idpedido))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "global.mix.sm.api.entity.Pedidos[ idpedido=" + idpedido + " ]";
    }

    public Tipocemento getIdtipocemento() {
        return idtipocemento;
    }

    public void setIdtipocemento(Tipocemento idtipocemento) {
        this.idtipocemento = idtipocemento;
    }

}
