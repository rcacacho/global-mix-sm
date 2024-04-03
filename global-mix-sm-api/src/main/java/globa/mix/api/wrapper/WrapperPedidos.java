package globa.mix.api.wrapper;

import java.util.Date;

/**
 *
 * @author rcacacho
 */
public class WrapperPedidos {

    private String cliente;
    private String asesor;
    private String ubicacion;
    private String material;
    private String elemento;
    private String bombeo;
    private String colocado;
    private Double volumen;
    private String estado;
    private Date fecha;

    public String getCliente() {
        return cliente;
    }

    public void setCliente(String cliente) {
        this.cliente = cliente;
    }

    public String getAsesor() {
        return asesor;
    }

    public void setAsesor(String asesor) {
        this.asesor = asesor;
    }

    public String getUbicacion() {
        return ubicacion;
    }

    public void setUbicacion(String ubicacion) {
        this.ubicacion = ubicacion;
    }

    public String getMaterial() {
        return material;
    }

    public void setMaterial(String material) {
        this.material = material;
    }

    public String getElemento() {
        return elemento;
    }

    public void setElemento(String elemento) {
        this.elemento = elemento;
    }

    public String getBombeo() {
        return bombeo;
    }

    public void setBombeo(String bombeo) {
        this.bombeo = bombeo;
    }

    public String getColocado() {
        return colocado;
    }

    public void setColocado(String colocado) {
        this.colocado = colocado;
    }

    public Double getVolumen() {
        return volumen;
    }

    public void setVolumen(Double volumen) {
        this.volumen = volumen;
    }

    public String getEstado() {
        return estado;
    }

    public void setEstado(String estado) {
        this.estado = estado;
    }

    public Date getFecha() {
        return fecha;
    }

    public void setFecha(Date fecha) {
        this.fecha = fecha;
    }
    
    
}
