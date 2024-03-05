package global.mix.sm.web.reportes;

import global.mix.sm.api.ejb.DespachosBeanLocal;
import global.mix.sm.api.entity.Despachos;
import global.mix.sm.web.utils.JsfUtil;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import org.apache.log4j.Logger;
import org.primefaces.context.RequestContext;
import org.primefaces.model.StreamedContent;

/**
 *
 * @author rcacacho
 */
@ManagedBean(name = "resumenDiarioMB")
@ViewScoped
public class ResumenDiarioMB implements Serializable {
     private static final Logger log = Logger.getLogger(ResumenDiarioMB.class);

    @EJB
    private DespachosBeanLocal despachoBean;
    
    
    private Date fechaInicio;
    private Date fechaFin;
    private Date fechaInicioReporte;
    private Date fechaFinReporte;
    private List<Despachos> listDespachos;
    private List<Despachos> listDespachosFilter;

    @PostConstruct
    void cargarDatos() {
        List<Despachos> response = despachoBean.listDespachoBuzonReporteGeneral(fechaInicio, fechaFin);
        if (response != null) {
            listDespachos = response;
        }
    }

    public void dialogReporte() {
        fechaInicioReporte = null;
        fechaFinReporte = null;
        RequestContext.getCurrentInstance().execute("PF('dlgReporte').show()");
    }

    public void buscarFiltro() {
        try {
            if (fechaInicio != null || fechaFin != null) {
                List<Despachos> response = despachoBean.listDespachoBuzonReporteGeneral(fechaInicio, fechaFin);
                if (response != null) {
                    listDespachos = response;
                } else {
                    listDespachos = new ArrayList<>();
                }
            }
        } catch (Exception ex) {
            log.error(ex.getMessage());
        }
    }

    public void limpiarCampos() {
        fechaInicio = null;
        fechaFin = null;

        cargarDatos();
    }

    public StreamedContent generReporte() {
        return null;
    }

    public void verDetalle(Integer idPedido) {
        JsfUtil.redirectTo("/pedidos/detalle.xhtml?faces-redirect=true&idPedido=" + idPedido);
    }

    /*Metodos getters y setters */
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

    public List<Despachos> getListDespachos() {
        return listDespachos;
    }

    public void setListDespachos(List<Despachos> listDespachos) {
        this.listDespachos = listDespachos;
    }

    public Date getFechaInicioReporte() {
        return fechaInicioReporte;
    }

    public void setFechaInicioReporte(Date fechaInicioReporte) {
        this.fechaInicioReporte = fechaInicioReporte;
    }

    public Date getFechaFinReporte() {
        return fechaFinReporte;
    }

    public void setFechaFinReporte(Date fechaFinReporte) {
        this.fechaFinReporte = fechaFinReporte;
    }

    public List<Despachos> getListDespachosFilter() {
        return listDespachosFilter;
    }

    public void setListDespachosFilter(List<Despachos> listDespachosFilter) {
        this.listDespachosFilter = listDespachosFilter;
    }
    
}
