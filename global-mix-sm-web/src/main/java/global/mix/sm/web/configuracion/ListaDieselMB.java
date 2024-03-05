package global.mix.sm.web.configuracion;

import global.mix.sm.api.ejb.ConfiguracionBeanLocal;
import global.mix.sm.api.ejb.DespachosBeanLocal;
import global.mix.sm.api.entity.Despachos;
import global.mix.sm.api.entity.Diesel;
import global.mix.sm.web.utils.JasperUtil;
import global.mix.sm.web.utils.JsfUtil;
import global.mix.sm.web.utils.ReporteJasper;
import global.mix.sm.web.utils.SesionUsuarioMB;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.annotation.Resource;
import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;
import javax.servlet.ServletContext;
import javax.sql.DataSource;
import org.apache.log4j.Logger;
import org.primefaces.context.RequestContext;
import org.primefaces.event.RowEditEvent;
import org.primefaces.model.DefaultStreamedContent;
import org.primefaces.model.StreamedContent;

/**
 *
 * @author rcacacho
 */
@ManagedBean(name = "listaDieselMB")
@ViewScoped
public class ListaDieselMB implements Serializable {

    private static final Logger log = Logger.getLogger(ListaDieselMB.class);

    @EJB
    private ConfiguracionBeanLocal configuracionBean;
    @EJB
    private DespachosBeanLocal despachoBean;

    @Resource(lookup = "jdbc/globamix")
    private DataSource dataSource;

    private List<Diesel> listDiesel;
    private Diesel diesel;
    private String descripcion;
    private List<Diesel> listDieselFilter;

    @PostConstruct
    void cargarDatos() {
        List<Diesel> list = configuracionBean.ListDiesel();
        if (list != null) {
            listDiesel = list;
        } else {
            listDiesel = new ArrayList<>();
        }
    }

    public void verRegistro() {
        diesel = null;
        diesel = new Diesel();

        RequestContext.getCurrentInstance().execute("PF('dlgRegistro').show()");
    }

    public void buscarFiltro() {
        try {
            if (descripcion != null) {
                List<Diesel> response = configuracionBean.listDieselBuzon(descripcion);
                if (response != null) {
                    listDiesel = response;
                }
            }

        } catch (Exception ex) {
            log.error(ex.getMessage());
        }
    }

    public void limpiarCampos() {
        descripcion = null;
        cargarDatos();
    }

    public void verDetalle(Integer idDiesel) {
        JsfUtil.redirectTo("/configuracion/diesel/detalle.xhtml?faces-redirect=true&idDiesel=" + idDiesel);
    }

    public void registroDiesel() throws IOException {
        if (diesel.getFechaconsumo() == null) {
            JsfUtil.addErrorMessage("Debe registrar una fecha");
            return;
        }

        if (diesel.getCantidadconsumida() == null) {
            JsfUtil.addErrorMessage("Debe registrar una cantidad");
            return;
        }

        Date fechaInicio = diesel.getFechaconsumo();
        Date fechaFin = diesel.getFechaconsumo();

        Calendar c = Calendar.getInstance();
        c.setTime(fechaInicio);
        c.set(Calendar.HOUR_OF_DAY, 0);
        c.set(Calendar.MINUTE, 0);
        c.set(Calendar.SECOND, 0);
        fechaInicio = c.getTime();

        Calendar c1 = Calendar.getInstance();
        c1.setTime(fechaFin);
        c1.set(Calendar.HOUR_OF_DAY, 23);
        c1.set(Calendar.MINUTE, 59);
        c1.set(Calendar.SECOND, 59);
        fechaFin = c1.getTime();

        List<Despachos> listDespa = despachoBean.listDespachoBuzonReporteGeneral(fechaInicio, fechaFin);
        Double material = 0.0;

        if (listDespa == null) {
            JsfUtil.addErrorMessage("No existen despachos para esta fecha");
            return;
        }

        for (Despachos despp : listDespa) {
            material += despp.getMetroscubicosvendidos();
        }

        diesel.setCantidadtotalmaterial(diesel.getCantidadconsumida() / material);
        diesel.setCantidadtotaldinero(diesel.getCantidadconsumida() * diesel.getPrecio());

        Diesel responseSave = configuracionBean.saveDiesel(diesel, SesionUsuarioMB.getUserName());
        if (responseSave != null) {
            JsfUtil.addSuccessMessage("Configuración registrada exitosamente");

            for (Despachos des : listDespa) {
                des.setCostototaldiesel(diesel.getCantidadtotalmaterial() * des.getMetroscubicosvendidos());
                des.setCostototaldieseldinero(des.getCostototaldiesel() * diesel.getPrecio());
                if (des.getTotalmateriaprima() != null) {
                    des.setTotalmateriaprima(des.getTotalmateriaprima() + des.getCostototaldieseldinero());
                    des.setBeneficioprimo(des.getPagototalsiniva() - des.getTotalmateriaprima());
                    des.setBeneficiometrocubico(des.getBeneficioprimo() / des.getMetroscubicosvendidos());
                    des.setPrecioventametrocubico(des.getPagototal() / des.getMetroscubicosvendidos());
                    des.setPrecioventametrocubicosiniva(des.getPrecioventametrocubico() / 1.12);
                    des.setCostoobra(des.getTotalmateriaprima());
                    des.setCostometrocubicovendido(des.getCostoobra() / des.getMetroscubicosvendidos());
                }

                Despachos despa = despachoBean.updateDespacho(des, SesionUsuarioMB.getUserName());
            }

        } else {
            JsfUtil.addErrorMessage("Ocurrio un error verificar datos");
        }
        cargarDatos();
        RequestContext.getCurrentInstance().execute("PF('dlgRegistro').hide()");
    }

    public void eliminarDiesel(Diesel id) throws IOException {
        Diesel response = configuracionBean.deleteDiesel(id, SesionUsuarioMB.getUserName());
        if (response != null) {
            cargarDatos();
            JsfUtil.addSuccessMessage("Se elimino el diésel exitosamente");
            return;
        }

        JsfUtil.addErrorMessage("Sucedio un error al eliminar");
    }

    public void onRowEditDiesel(RowEditEvent event) throws IOException {
        Diesel objeto = ((Diesel) event.getObject());
        Diesel response = configuracionBean.updateDiesel(objeto, SesionUsuarioMB.getUserName());
        if (response == null) {
            JsfUtil.addErrorMessage("Ocurrio un error al actualizar");
        } else {
            JsfUtil.addSuccessMessage("Diésel actualizado correctamente");
        }
    }

    public StreamedContent generReporte() {
        String nombreReporte;
        String nombreArchivo;
        try {
            ServletContext servletContext = (ServletContext) FacesContext.getCurrentInstance().getExternalContext().getContext();
            String realPath = servletContext.getRealPath("/");
            //crearImage();
            HashMap parametros = new HashMap();
            nombreReporte = "rptDiesel";
            nombreArchivo = "Diesel";

            parametros.put("IMAGE", "logo.jpeg");
            parametros.put("DIRECTORIO", realPath + File.separator + "resources" + File.separator + "images" + File.separator);
            parametros.put("usuario", SesionUsuarioMB.getUserName());

            ReporteJasper reporteJasper = JasperUtil.jasperReportPDF(nombreReporte, nombreArchivo, parametros, dataSource);
            StreamedContent streamedContent;
            FileInputStream stream = new FileInputStream(realPath + "resources/reports/" + reporteJasper.getFileName());
            streamedContent = new DefaultStreamedContent(stream, "application/pdf", reporteJasper.getFileName());
            return streamedContent;

        } catch (Exception ex) {
            JsfUtil.addErrorMessage("Ocurrio un error al generar el pdf del reporte");
        }
        return null;
    }

    /*Metodos getters y setters*/
    public List<Diesel> getListDiesel() {
        return listDiesel;
    }

    public void setListDiesel(List<Diesel> listDiesel) {
        this.listDiesel = listDiesel;
    }

    public Diesel getDiesel() {
        return diesel;
    }

    public void setDiesel(Diesel diesel) {
        this.diesel = diesel;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public List<Diesel> getListDieselFilter() {
        return listDieselFilter;
    }

    public void setListDieselFilter(List<Diesel> listDieselFilter) {
        this.listDieselFilter = listDieselFilter;
    }

}
