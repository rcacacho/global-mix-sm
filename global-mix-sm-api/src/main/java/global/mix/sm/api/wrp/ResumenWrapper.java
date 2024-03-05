package global.mix.sm.api.wrp;

import java.io.Serializable;
import java.util.Date;
import org.apache.log4j.Logger;

/**
 *
 * @author rcacacho
 */
public class ResumenWrapper implements Serializable {

    private static final Logger log = Logger.getLogger(ResumenWrapper.class);
    
    private Date fecha;
    private String cliente;
    private String obra;
    private Double encargados;
    private Double vendidos;
    private Double totalMateriaPrima;
    private Double diesel;
    private Double totalBombeo;
    private Double colocado;
    private Double comision;
    private Double ingresoNeto;   
    private Double beneficioPrimo;
    private Double compras;

    public ResumenWrapper() {
    }

    /*Metodos getters y setters*/
    public Date getFecha() {
        return fecha;
    }

    public void setFecha(Date fecha) {
        this.fecha = fecha;
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

    public Double getEncargados() {
        return encargados;
    }

    public void setEncargados(Double encargados) {
        this.encargados = encargados;
    }

    public Double getVendidos() {
        return vendidos;
    }

    public void setVendidos(Double vendidos) {
        this.vendidos = vendidos;
    }

    public Double getTotalMateriaPrima() {
        return totalMateriaPrima;
    }

    public void setTotalMateriaPrima(Double totalMateriaPrima) {
        this.totalMateriaPrima = totalMateriaPrima;
    }

    public Double getDiesel() {
        return diesel;
    }

    public void setDiesel(Double diesel) {
        this.diesel = diesel;
    }

    public Double getTotalBombeo() {
        return totalBombeo;
    }

    public void setTotalBombeo(Double totalBombeo) {
        this.totalBombeo = totalBombeo;
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

    public Double getIngresoNeto() {
        return ingresoNeto;
    }

    public void setIngresoNeto(Double ingresoNeto) {
        this.ingresoNeto = ingresoNeto;
    }

    public Double getBeneficioPrimo() {
        return beneficioPrimo;
    }

    public void setBeneficioPrimo(Double beneficioPrimo) {
        this.beneficioPrimo = beneficioPrimo;
    }

    public Double getCompras() {
        return compras;
    }

    public void setCompras(Double compras) {
        this.compras = compras;
    }
    
    

}
