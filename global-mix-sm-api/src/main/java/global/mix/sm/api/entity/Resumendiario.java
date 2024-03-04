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
@Table(name = "resumendiario", catalog = "global_mix_sm", schema = "")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Resumendiario.findAll", query = "SELECT r FROM Resumendiario r"),
    @NamedQuery(name = "Resumendiario.findByIdresumendiario", query = "SELECT r FROM Resumendiario r WHERE r.idresumendiario = :idresumendiario"),
    @NamedQuery(name = "Resumendiario.findByMateriaprima", query = "SELECT r FROM Resumendiario r WHERE r.materiaprima = :materiaprima"),
    @NamedQuery(name = "Resumendiario.findByDiesel", query = "SELECT r FROM Resumendiario r WHERE r.diesel = :diesel"),
    @NamedQuery(name = "Resumendiario.findByBombeo", query = "SELECT r FROM Resumendiario r WHERE r.bombeo = :bombeo"),
    @NamedQuery(name = "Resumendiario.findByColocado", query = "SELECT r FROM Resumendiario r WHERE r.colocado = :colocado"),
    @NamedQuery(name = "Resumendiario.findByComision", query = "SELECT r FROM Resumendiario r WHERE r.comision = :comision"),
    @NamedQuery(name = "Resumendiario.findByTotalcosto", query = "SELECT r FROM Resumendiario r WHERE r.totalcosto = :totalcosto"),
    @NamedQuery(name = "Resumendiario.findByIngresoneto", query = "SELECT r FROM Resumendiario r WHERE r.ingresoneto = :ingresoneto"),
    @NamedQuery(name = "Resumendiario.findByUtilidadbruta", query = "SELECT r FROM Resumendiario r WHERE r.utilidadbruta = :utilidadbruta"),
    @NamedQuery(name = "Resumendiario.findByUsuariocreacion", query = "SELECT r FROM Resumendiario r WHERE r.usuariocreacion = :usuariocreacion"),
    @NamedQuery(name = "Resumendiario.findByFechacreacion", query = "SELECT r FROM Resumendiario r WHERE r.fechacreacion = :fechacreacion"),
    @NamedQuery(name = "Resumendiario.findByUsuariomodificacion", query = "SELECT r FROM Resumendiario r WHERE r.usuariomodificacion = :usuariomodificacion"),
    @NamedQuery(name = "Resumendiario.findByFechamodificacion", query = "SELECT r FROM Resumendiario r WHERE r.fechamodificacion = :fechamodificacion"),
    @NamedQuery(name = "Resumendiario.findByUsuarioeliminacion", query = "SELECT r FROM Resumendiario r WHERE r.usuarioeliminacion = :usuarioeliminacion"),
    @NamedQuery(name = "Resumendiario.findByFechaeliminacion", query = "SELECT r FROM Resumendiario r WHERE r.fechaeliminacion = :fechaeliminacion"),
    @NamedQuery(name = "Resumendiario.findByActivo", query = "SELECT r FROM Resumendiario r WHERE r.activo = :activo")})
public class Resumendiario implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "idresumendiario", nullable = false)
    private Integer idresumendiario;

    @Column(name = "materiaprima", precision = 22)
    private Double materiaprima;
    
    @Column(name = "diesel", precision = 22)
    private Double diesel;
    
    @Column(name = "bombeo", precision = 22)
    private Double bombeo;
    
    @Column(name = "colocado", precision = 22)
    private Double colocado;
    
    @Column(name = "comision", precision = 22)
    private Double comision;
    
    @Column(name = "totalcosto", precision = 22)
    private Double totalcosto;
    
    @Column(name = "ingresoneto")
    private Integer ingresoneto;
    
    @Column(name = "utilidadbruta")
    private Integer utilidadbruta;
    
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

    public Resumendiario() {
    }

    public Resumendiario(Integer idresumendiario) {
        this.idresumendiario = idresumendiario;
    }

    public Resumendiario(Integer idresumendiario, String usuariocreacion, Date fechacreacion, boolean activo) {
        this.idresumendiario = idresumendiario;
        this.usuariocreacion = usuariocreacion;
        this.fechacreacion = fechacreacion;
        this.activo = activo;
    }

    public Integer getIdresumendiario() {
        return idresumendiario;
    }

    public void setIdresumendiario(Integer idresumendiario) {
        this.idresumendiario = idresumendiario;
    }

    public Double getMateriaprima() {
        return materiaprima;
    }

    public void setMateriaprima(Double materiaprima) {
        this.materiaprima = materiaprima;
    }

    public Double getDiesel() {
        return diesel;
    }

    public void setDiesel(Double diesel) {
        this.diesel = diesel;
    }

    public Double getBombeo() {
        return bombeo;
    }

    public void setBombeo(Double bombeo) {
        this.bombeo = bombeo;
    }

    public Double getColocado() {
        return colocado;
    }

    public void setColocado(Double colocado) {
        this.colocado = colocado;
    }

    public Double getComision() {
        return comision;
    }

    public void setComision(Double comision) {
        this.comision = comision;
    }

    public Double getTotalcosto() {
        return totalcosto;
    }

    public void setTotalcosto(Double totalcosto) {
        this.totalcosto = totalcosto;
    }

    public Integer getIngresoneto() {
        return ingresoneto;
    }

    public void setIngresoneto(Integer ingresoneto) {
        this.ingresoneto = ingresoneto;
    }

    public Integer getUtilidadbruta() {
        return utilidadbruta;
    }

    public void setUtilidadbruta(Integer utilidadbruta) {
        this.utilidadbruta = utilidadbruta;
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
        hash += (idresumendiario != null ? idresumendiario.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Resumendiario)) {
            return false;
        }
        Resumendiario other = (Resumendiario) object;
        if ((this.idresumendiario == null && other.idresumendiario != null) || (this.idresumendiario != null && !this.idresumendiario.equals(other.idresumendiario))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "global.mix.sm.api.entity.Resumendiario[ idresumendiario=" + idresumendiario + " ]";
    }
    
}
