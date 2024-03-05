package global.mix.sm.web.gastos;

import global.mix.sm.api.ejb.GastoBeanLocal;
import global.mix.sm.api.entity.Gastos;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import org.apache.log4j.Logger;
import org.primefaces.context.RequestContext;

/**
 *
 * @author rcacacho
 */
@ManagedBean(name = "listaGastosMB")
@ViewScoped
public class ListaGastosMB implements Serializable {

    private static final Logger log = Logger.getLogger(ListaGastosMB.class);

    @EJB
    private GastoBeanLocal gastoBean;

    private List<Gastos> listGasto;
    private Date fechaInicio;
    private Date fechaFin;
    private List<Gastos> listGastoFiltrado;
    private Gastos gastoRegistro;

    @PostConstruct
    void cargarDatos() {
        List<Gastos> list = gastoBean.ListGastos();
        if (list != null) {
            listGasto = list;
        } else {
            listGasto = new ArrayList<>();
        }
    }

    public void verRegistro() {
        gastoRegistro = null;
        gastoRegistro = new Gastos();

        RequestContext.getCurrentInstance().execute("PF('dlgRegistro').show()");
    }

    public void limpiarCampos() {
        fechaInicio = null;
        fechaFin = null;
        cargarDatos();
    }

    public void buscarFiltro() {
        try {
            if (fechaInicio != null || fechaFin != null) {
                if (fechaInicio != null) {
                    Calendar c = Calendar.getInstance();
                    c.setTime(fechaInicio);
                    c.set(Calendar.HOUR_OF_DAY, 0);
                    c.set(Calendar.MINUTE, 0);
                    c.set(Calendar.SECOND, 0);
                    fechaInicio = c.getTime();
                }

                if (fechaFin != null) {
                    Calendar c1 = Calendar.getInstance();
                    c1.setTime(fechaFin);
                    c1.set(Calendar.HOUR_OF_DAY, 23);
                    c1.set(Calendar.MINUTE, 59);
                    c1.set(Calendar.SECOND, 59);
                    fechaFin = c1.getTime();
                }

                List<Gastos> response = gastoBean.listGastoBuzon(fechaInicio, fechaFin);
                if (response != null) {
                    listGasto = response;
                } else {
                    listGasto = new ArrayList<>();
                }
            }

        } catch (Exception ex) {
            log.error(ex.getMessage());
        }
    }
    
    public void registrarGasto() {
        
    }

    /*Metodos getters y setters*/
    public GastoBeanLocal getGastoBean() {
        return gastoBean;
    }

    public void setGastoBean(GastoBeanLocal gastoBean) {
        this.gastoBean = gastoBean;
    }

    public List<Gastos> getListGasto() {
        return listGasto;
    }

    public void setListGasto(List<Gastos> listGasto) {
        this.listGasto = listGasto;
    }

    public Date getFechaInicio() {
        return fechaInicio;
    }

    public void setFechaInicio(Date fechaInicio) {
        this.fechaInicio = fechaInicio;
    }

    public Date getFechaFin() {
        return fechaFin;
    }

    public void setFechaFin(Date fechaFin) {
        this.fechaFin = fechaFin;
    }

    public List<Gastos> getListGastoFiltrado() {
        return listGastoFiltrado;
    }

    public void setListGastoFiltrado(List<Gastos> listGastoFiltrado) {
        this.listGastoFiltrado = listGastoFiltrado;
    }

    public Gastos getGastoRegistro() {
        return gastoRegistro;
    }

    public void setGastoRegistro(Gastos gastoRegistro) {
        this.gastoRegistro = gastoRegistro;
    }

}
