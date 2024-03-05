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
@Table(name = "diesel", catalog = "global_mix_sm", schema = "")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Diesel.findAll", query = "SELECT d FROM Diesel d"),
    @NamedQuery(name = "Diesel.findByIddiesel", query = "SELECT d FROM Diesel d WHERE d.iddiesel = :iddiesel"),
    @NamedQuery(name = "Diesel.findByFechaconsumo", query = "SELECT d FROM Diesel d WHERE d.fechaconsumo = :fechaconsumo"),
    @NamedQuery(name = "Diesel.findByCantidadconsumida", query = "SELECT d FROM Diesel d WHERE d.cantidadconsumida = :cantidadconsumida"),
    @NamedQuery(name = "Diesel.findByPrecio", query = "SELECT d FROM Diesel d WHERE d.precio = :precio"),
    @NamedQuery(name = "Diesel.findByCantidadtotalmaterial", query = "SELECT d FROM Diesel d WHERE d.cantidadtotalmaterial = :cantidadtotalmaterial"),
    @NamedQuery(name = "Diesel.findByCantidadtotaldinero", query = "SELECT d FROM Diesel d WHERE d.cantidadtotaldinero = :cantidadtotaldinero"),
    @NamedQuery(name = "Diesel.findByDescripcion", query = "SELECT d FROM Diesel d WHERE d.descripcion = :descripcion"),
    @NamedQuery(name = "Diesel.findByUsuariocreacion", query = "SELECT d FROM Diesel d WHERE d.usuariocreacion = :usuariocreacion"),
    @NamedQuery(name = "Diesel.findByFechacreacion", query = "SELECT d FROM Diesel d WHERE d.fechacreacion = :fechacreacion"),
    @NamedQuery(name = "Diesel.findByUsuariomodificacion", query = "SELECT d FROM Diesel d WHERE d.usuariomodificacion = :usuariomodificacion"),
    @NamedQuery(name = "Diesel.findByFechamodificacion", query = "SELECT d FROM Diesel d WHERE d.fechamodificacion = :fechamodificacion"),
    @NamedQuery(name = "Diesel.findByUsuarioeliminacion", query = "SELECT d FROM Diesel d WHERE d.usuarioeliminacion = :usuarioeliminacion"),
    @NamedQuery(name = "Diesel.findByFechaeliminacion", query = "SELECT d FROM Diesel d WHERE d.fechaeliminacion = :fechaeliminacion"),
    @NamedQuery(name = "Diesel.findByActivo", query = "SELECT d FROM Diesel d WHERE d.activo = :activo")})
public class Diesel implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "iddiesel", nullable = false)
    private Integer iddiesel;
    
    @Column(name = "fechaconsumo")
    @Temporal(TemporalType.TIMESTAMP)
    private Date fechaconsumo;
    
    @Basic(optional = false)
    @Column(name = "cantidadconsumida", nullable = false)
    private Double cantidadconsumida;

    @Column(name = "precio", precision = 22)
    private Double precio;
    
    @Column(name = "cantidadtotalmaterial", precision = 22)
    private Double cantidadtotalmaterial;
    
    @Column(name = "cantidadtotaldinero", precision = 22)
    private Double cantidadtotaldinero;
    
    @Column(name = "descripcion", length = 250)
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

    public Diesel() {
    }

    public Diesel(Integer iddiesel) {
        this.iddiesel = iddiesel;
    }

    public Diesel(Integer iddiesel, double cantidadconsumida, String usuariocreacion, Date fechacreacion, boolean activo) {
        this.iddiesel = iddiesel;
        this.cantidadconsumida = cantidadconsumida;
        this.usuariocreacion = usuariocreacion;
        this.fechacreacion = fechacreacion;
        this.activo = activo;
    }

    public Integer getIddiesel() {
        return iddiesel;
    }

    public void setIddiesel(Integer iddiesel) {
        this.iddiesel = iddiesel;
    }

    public Date getFechaconsumo() {
        return fechaconsumo;
    }

    public void setFechaconsumo(Date fechaconsumo) {
        this.fechaconsumo = fechaconsumo;
    }

    public Double getCantidadconsumida() {
        return cantidadconsumida;
    }

    public void setCantidadconsumida(Double cantidadconsumida) {
        this.cantidadconsumida = cantidadconsumida;
    }

    public Double getPrecio() {
        return precio;
    }

    public void setPrecio(Double precio) {
        this.precio = precio;
    }

    public Double getCantidadtotalmaterial() {
        return cantidadtotalmaterial;
    }

    public void setCantidadtotalmaterial(Double cantidadtotalmaterial) {
        this.cantidadtotalmaterial = cantidadtotalmaterial;
    }

    public Double getCantidadtotaldinero() {
        return cantidadtotaldinero;
    }

    public void setCantidadtotaldinero(Double cantidadtotaldinero) {
        this.cantidadtotaldinero = cantidadtotaldinero;
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

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (iddiesel != null ? iddiesel.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Diesel)) {
            return false;
        }
        Diesel other = (Diesel) object;
        if ((this.iddiesel == null && other.iddiesel != null) || (this.iddiesel != null && !this.iddiesel.equals(other.iddiesel))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "global.mix.sm.api.entity.Diesel[ iddiesel=" + iddiesel + " ]";
    }
    
}
