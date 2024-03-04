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
@Table(name = "despachos", catalog = "global_mix_sm", schema = "")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Despachos.findAll", query = "SELECT d FROM Despachos d"),
    @NamedQuery(name = "Despachos.findByIddespacho", query = "SELECT d FROM Despachos d WHERE d.iddespacho = :iddespacho"),
    @NamedQuery(name = "Despachos.findByMetroscubicossolicitados", query = "SELECT d FROM Despachos d WHERE d.metroscubicossolicitados = :metroscubicossolicitados"),
    @NamedQuery(name = "Despachos.findByMetroscubicosvendidos", query = "SELECT d FROM Despachos d WHERE d.metroscubicosvendidos = :metroscubicosvendidos"),
    @NamedQuery(name = "Despachos.findByCostototalcemento", query = "SELECT d FROM Despachos d WHERE d.costototalcemento = :costototalcemento"),
    @NamedQuery(name = "Despachos.findByCostototaldinerocemento", query = "SELECT d FROM Despachos d WHERE d.costototaldinerocemento = :costototaldinerocemento"),
    @NamedQuery(name = "Despachos.findByCostototalarena", query = "SELECT d FROM Despachos d WHERE d.costototalarena = :costototalarena"),
    @NamedQuery(name = "Despachos.findByCostototalarenadinero", query = "SELECT d FROM Despachos d WHERE d.costototalarenadinero = :costototalarenadinero"),
    @NamedQuery(name = "Despachos.findByCostototalpiedrin", query = "SELECT d FROM Despachos d WHERE d.costototalpiedrin = :costototalpiedrin"),
    @NamedQuery(name = "Despachos.findByCostototalpiedrindinero", query = "SELECT d FROM Despachos d WHERE d.costototalpiedrindinero = :costototalpiedrindinero"),
    @NamedQuery(name = "Despachos.findByCostototaladitivo", query = "SELECT d FROM Despachos d WHERE d.costototaladitivo = :costototaladitivo"),
    @NamedQuery(name = "Despachos.findByCostototaladitivodinero", query = "SELECT d FROM Despachos d WHERE d.costototaladitivodinero = :costototaladitivodinero"),
    @NamedQuery(name = "Despachos.findByCostotalagua", query = "SELECT d FROM Despachos d WHERE d.costotalagua = :costotalagua"),
    @NamedQuery(name = "Despachos.findByCostototalaguadinero", query = "SELECT d FROM Despachos d WHERE d.costototalaguadinero = :costototalaguadinero"),
    @NamedQuery(name = "Despachos.findByCostototaldiesel", query = "SELECT d FROM Despachos d WHERE d.costototaldiesel = :costototaldiesel"),
    @NamedQuery(name = "Despachos.findByCostototaldieseldinero", query = "SELECT d FROM Despachos d WHERE d.costototaldieseldinero = :costototaldieseldinero"),
    @NamedQuery(name = "Despachos.findByCostototalotro", query = "SELECT d FROM Despachos d WHERE d.costototalotro = :costototalotro"),
    @NamedQuery(name = "Despachos.findByCostototalotrodinero", query = "SELECT d FROM Despachos d WHERE d.costototalotrodinero = :costototalotrodinero"),
    @NamedQuery(name = "Despachos.findByCostototalbombeo", query = "SELECT d FROM Despachos d WHERE d.costototalbombeo = :costototalbombeo"),
    @NamedQuery(name = "Despachos.findByCostototalcolocado", query = "SELECT d FROM Despachos d WHERE d.costototalcolocado = :costototalcolocado"),
    @NamedQuery(name = "Despachos.findBySegundopago", query = "SELECT d FROM Despachos d WHERE d.segundopago = :segundopago"),
    @NamedQuery(name = "Despachos.findByComision", query = "SELECT d FROM Despachos d WHERE d.comision = :comision"),
    @NamedQuery(name = "Despachos.findByBeneficioprimo", query = "SELECT d FROM Despachos d WHERE d.beneficioprimo = :beneficioprimo"),
    @NamedQuery(name = "Despachos.findByBeneficiometrocubico", query = "SELECT d FROM Despachos d WHERE d.beneficiometrocubico = :beneficiometrocubico"),
    @NamedQuery(name = "Despachos.findByPagototal", query = "SELECT d FROM Despachos d WHERE d.pagototal = :pagototal"),
    @NamedQuery(name = "Despachos.findByPagototalsiniva", query = "SELECT d FROM Despachos d WHERE d.pagototalsiniva = :pagototalsiniva"),
    @NamedQuery(name = "Despachos.findByPrecioventametrocubico", query = "SELECT d FROM Despachos d WHERE d.precioventametrocubico = :precioventametrocubico"),
    @NamedQuery(name = "Despachos.findByPrecioventametrocubicosiniva", query = "SELECT d FROM Despachos d WHERE d.precioventametrocubicosiniva = :precioventametrocubicosiniva"),
    @NamedQuery(name = "Despachos.findByCostoobra", query = "SELECT d FROM Despachos d WHERE d.costoobra = :costoobra"),
    @NamedQuery(name = "Despachos.findByCostometrocubicovendido", query = "SELECT d FROM Despachos d WHERE d.costometrocubicovendido = :costometrocubicovendido"),
    @NamedQuery(name = "Despachos.findByTotalmateriaprima", query = "SELECT d FROM Despachos d WHERE d.totalmateriaprima = :totalmateriaprima"),
    @NamedQuery(name = "Despachos.findByUsuariocreacion", query = "SELECT d FROM Despachos d WHERE d.usuariocreacion = :usuariocreacion"),
    @NamedQuery(name = "Despachos.findByFechacreacion", query = "SELECT d FROM Despachos d WHERE d.fechacreacion = :fechacreacion"),
    @NamedQuery(name = "Despachos.findByUsuariomodificacion", query = "SELECT d FROM Despachos d WHERE d.usuariomodificacion = :usuariomodificacion"),
    @NamedQuery(name = "Despachos.findByFechamodificacion", query = "SELECT d FROM Despachos d WHERE d.fechamodificacion = :fechamodificacion"),
    @NamedQuery(name = "Despachos.findByUsuarioelimiancion", query = "SELECT d FROM Despachos d WHERE d.usuarioelimiancion = :usuarioelimiancion"),
    @NamedQuery(name = "Despachos.findByFechaeliminacion", query = "SELECT d FROM Despachos d WHERE d.fechaeliminacion = :fechaeliminacion"),
    @NamedQuery(name = "Despachos.findByActivo", query = "SELECT d FROM Despachos d WHERE d.activo = :activo")})
public class Despachos implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "iddespacho", nullable = false)
    private Integer iddespacho;
    
    @Basic(optional = false)
    @Column(name = "metroscubicossolicitados", nullable = false)
    private double metroscubicossolicitados;

    @Column(name = "metroscubicosvendidos", precision = 22)
    private Double metroscubicosvendidos;
    
    @Column(name = "costototalcemento", precision = 22)
    private Double costototalcemento;
    
    @Column(name = "costototaldinerocemento", precision = 22)
    private Double costototaldinerocemento;
    
    @Column(name = "costototalarena", precision = 22)
    private Double costototalarena;
    
    @Column(name = "costototalarenadinero", precision = 22)
    private Double costototalarenadinero;
    
    @Column(name = "costototalpiedrin", precision = 22)
    private Double costototalpiedrin;
    
    @Column(name = "costototalpiedrindinero", precision = 22)
    private Double costototalpiedrindinero;
    
    @Column(name = "costototaladitivo", precision = 22)
    private Double costototaladitivo;
    
    @Column(name = "costototaladitivodinero", precision = 22)
    private Double costototaladitivodinero;
    
    @Column(name = "costotalagua", precision = 22)
    private Double costotalagua;
    
    @Column(name = "costototalaguadinero", precision = 22)
    private Double costototalaguadinero;
    
    @Column(name = "costototaldiesel", precision = 22)
    private Double costototaldiesel;
    
    @Column(name = "costototaldieseldinero", precision = 22)
    private Double costototaldieseldinero;
    
    @Column(name = "costototalotro", precision = 22)
    private Double costototalotro;
    
    @Column(name = "costototalotrodinero", precision = 22)
    private Double costototalotrodinero;
    
    @Column(name = "costototalbombeo", precision = 22)
    private Double costototalbombeo;
    
    @Column(name = "costototalcolocado", precision = 22)
    private Double costototalcolocado;
    
    @Column(name = "segundopago", precision = 22)
    private Double segundopago;
    
    @Column(name = "comision", precision = 22)
    private Double comision;
    
    @Column(name = "beneficioprimo", precision = 22)
    private Double beneficioprimo;
    
    @Column(name = "beneficiometrocubico", precision = 22)
    private Double beneficiometrocubico;
    
    @Column(name = "pagototal", precision = 22)
    private Double pagototal;
    
    @Column(name = "pagototalsiniva", precision = 22)
    private Double pagototalsiniva;
    
    @Column(name = "precioventametrocubico", precision = 22)
    private Double precioventametrocubico;
    
    @Column(name = "precioventametrocubicosiniva", precision = 22)
    private Double precioventametrocubicosiniva;
    
    @Column(name = "costoobra", precision = 22)
    private Double costoobra;
    
    @Column(name = "costometrocubicovendido", precision = 22)
    private Double costometrocubicovendido;
    
    @Column(name = "totalmateriaprima", precision = 22)
    private Double totalmateriaprima;
    
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
    
    @Column(name = "usuarioelimiancion", length = 50)
    private String usuarioelimiancion;
    
    @Column(name = "fechaeliminacion")
    @Temporal(TemporalType.TIMESTAMP)
    private Date fechaeliminacion;
    
    @Basic(optional = false)
    @Column(name = "activo", nullable = false)
    private boolean activo;
    
    @JoinColumn(name = "idasesor", referencedColumnName = "idasesor")
    @ManyToOne(fetch = FetchType.LAZY)
    private Asesor idasesor;
    
    @JoinColumn(name = "idestadodespacho", referencedColumnName = "idestadodespacho")
    @ManyToOne(fetch = FetchType.LAZY)
    private Estadodespacho idestadodespacho;
    
    @JoinColumn(name = "idpedido", referencedColumnName = "idpedido", nullable = false)
    @ManyToOne(optional = false, fetch = FetchType.LAZY)
    private Pedidos idpedido;

    public Despachos() {
    }

    public Despachos(Integer iddespacho) {
        this.iddespacho = iddespacho;
    }

    public Despachos(Integer iddespacho, double metroscubicossolicitados, String usuariocreacion, Date fechacreacion, boolean activo) {
        this.iddespacho = iddespacho;
        this.metroscubicossolicitados = metroscubicossolicitados;
        this.usuariocreacion = usuariocreacion;
        this.fechacreacion = fechacreacion;
        this.activo = activo;
    }

    public Integer getIddespacho() {
        return iddespacho;
    }

    public void setIddespacho(Integer iddespacho) {
        this.iddespacho = iddespacho;
    }

    public double getMetroscubicossolicitados() {
        return metroscubicossolicitados;
    }

    public void setMetroscubicossolicitados(double metroscubicossolicitados) {
        this.metroscubicossolicitados = metroscubicossolicitados;
    }

    public Double getMetroscubicosvendidos() {
        return metroscubicosvendidos;
    }

    public void setMetroscubicosvendidos(Double metroscubicosvendidos) {
        this.metroscubicosvendidos = metroscubicosvendidos;
    }

    public Double getCostototalcemento() {
        return costototalcemento;
    }

    public void setCostototalcemento(Double costototalcemento) {
        this.costototalcemento = costototalcemento;
    }

    public Double getCostototaldinerocemento() {
        return costototaldinerocemento;
    }

    public void setCostototaldinerocemento(Double costototaldinerocemento) {
        this.costototaldinerocemento = costototaldinerocemento;
    }

    public Double getCostototalarena() {
        return costototalarena;
    }

    public void setCostototalarena(Double costototalarena) {
        this.costototalarena = costototalarena;
    }

    public Double getCostototalarenadinero() {
        return costototalarenadinero;
    }

    public void setCostototalarenadinero(Double costototalarenadinero) {
        this.costototalarenadinero = costototalarenadinero;
    }

    public Double getCostototalpiedrin() {
        return costototalpiedrin;
    }

    public void setCostototalpiedrin(Double costototalpiedrin) {
        this.costototalpiedrin = costototalpiedrin;
    }

    public Double getCostototalpiedrindinero() {
        return costototalpiedrindinero;
    }

    public void setCostototalpiedrindinero(Double costototalpiedrindinero) {
        this.costototalpiedrindinero = costototalpiedrindinero;
    }

    public Double getCostototaladitivo() {
        return costototaladitivo;
    }

    public void setCostototaladitivo(Double costototaladitivo) {
        this.costototaladitivo = costototaladitivo;
    }

    public Double getCostototaladitivodinero() {
        return costototaladitivodinero;
    }

    public void setCostototaladitivodinero(Double costototaladitivodinero) {
        this.costototaladitivodinero = costototaladitivodinero;
    }

    public Double getCostotalagua() {
        return costotalagua;
    }

    public void setCostotalagua(Double costotalagua) {
        this.costotalagua = costotalagua;
    }

    public Double getCostototalaguadinero() {
        return costototalaguadinero;
    }

    public void setCostototalaguadinero(Double costototalaguadinero) {
        this.costototalaguadinero = costototalaguadinero;
    }

    public Double getCostototaldiesel() {
        return costototaldiesel;
    }

    public void setCostototaldiesel(Double costototaldiesel) {
        this.costototaldiesel = costototaldiesel;
    }

    public Double getCostototaldieseldinero() {
        return costototaldieseldinero;
    }

    public void setCostototaldieseldinero(Double costototaldieseldinero) {
        this.costototaldieseldinero = costototaldieseldinero;
    }

    public Double getCostototalotro() {
        return costototalotro;
    }

    public void setCostototalotro(Double costototalotro) {
        this.costototalotro = costototalotro;
    }

    public Double getCostototalotrodinero() {
        return costototalotrodinero;
    }

    public void setCostototalotrodinero(Double costototalotrodinero) {
        this.costototalotrodinero = costototalotrodinero;
    }

    public Double getCostototalbombeo() {
        return costototalbombeo;
    }

    public void setCostototalbombeo(Double costototalbombeo) {
        this.costototalbombeo = costototalbombeo;
    }

    public Double getCostototalcolocado() {
        return costototalcolocado;
    }

    public void setCostototalcolocado(Double costototalcolocado) {
        this.costototalcolocado = costototalcolocado;
    }

    public Double getSegundopago() {
        return segundopago;
    }

    public void setSegundopago(Double segundopago) {
        this.segundopago = segundopago;
    }

    public Double getComision() {
        return comision;
    }

    public void setComision(Double comision) {
        this.comision = comision;
    }

    public Double getBeneficioprimo() {
        return beneficioprimo;
    }

    public void setBeneficioprimo(Double beneficioprimo) {
        this.beneficioprimo = beneficioprimo;
    }

    public Double getBeneficiometrocubico() {
        return beneficiometrocubico;
    }

    public void setBeneficiometrocubico(Double beneficiometrocubico) {
        this.beneficiometrocubico = beneficiometrocubico;
    }

    public Double getPagototal() {
        return pagototal;
    }

    public void setPagototal(Double pagototal) {
        this.pagototal = pagototal;
    }

    public Double getPagototalsiniva() {
        return pagototalsiniva;
    }

    public void setPagototalsiniva(Double pagototalsiniva) {
        this.pagototalsiniva = pagototalsiniva;
    }

    public Double getPrecioventametrocubico() {
        return precioventametrocubico;
    }

    public void setPrecioventametrocubico(Double precioventametrocubico) {
        this.precioventametrocubico = precioventametrocubico;
    }

    public Double getPrecioventametrocubicosiniva() {
        return precioventametrocubicosiniva;
    }

    public void setPrecioventametrocubicosiniva(Double precioventametrocubicosiniva) {
        this.precioventametrocubicosiniva = precioventametrocubicosiniva;
    }

    public Double getCostoobra() {
        return costoobra;
    }

    public void setCostoobra(Double costoobra) {
        this.costoobra = costoobra;
    }

    public Double getCostometrocubicovendido() {
        return costometrocubicovendido;
    }

    public void setCostometrocubicovendido(Double costometrocubicovendido) {
        this.costometrocubicovendido = costometrocubicovendido;
    }

    public Double getTotalmateriaprima() {
        return totalmateriaprima;
    }

    public void setTotalmateriaprima(Double totalmateriaprima) {
        this.totalmateriaprima = totalmateriaprima;
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

    public String getUsuarioelimiancion() {
        return usuarioelimiancion;
    }

    public void setUsuarioelimiancion(String usuarioelimiancion) {
        this.usuarioelimiancion = usuarioelimiancion;
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

    public Estadodespacho getIdestadodespacho() {
        return idestadodespacho;
    }

    public void setIdestadodespacho(Estadodespacho idestadodespacho) {
        this.idestadodespacho = idestadodespacho;
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
        hash += (iddespacho != null ? iddespacho.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Despachos)) {
            return false;
        }
        Despachos other = (Despachos) object;
        if ((this.iddespacho == null && other.iddespacho != null) || (this.iddespacho != null && !this.iddespacho.equals(other.iddespacho))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "global.mix.sm.api.entity.Despachos[ iddespacho=" + iddespacho + " ]";
    }
    
}
