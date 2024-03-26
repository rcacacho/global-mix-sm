package global.mix.sm.web.pedidos;

import global.mix.sm.api.ejb.CatalogosBeanLocal;
import global.mix.sm.api.ejb.PedidosBeanLocal;
import global.mix.sm.api.entity.Asesor;
import global.mix.sm.api.entity.Cliente;
import global.mix.sm.api.entity.Confirmacionpago;
import global.mix.sm.api.entity.Estadopedido;
import global.mix.sm.api.entity.Pedidos;
import global.mix.sm.api.enums.EstadoPedido;
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
import javax.faces.event.AjaxBehaviorEvent;
import javax.servlet.ServletContext;
import javax.sql.DataSource;
import org.apache.log4j.Logger;
import org.primefaces.component.schedule.Schedule;
import org.primefaces.context.RequestContext;
import org.primefaces.event.RowEditEvent;
import org.primefaces.event.SelectEvent;
import org.primefaces.model.DefaultScheduleEvent;
import org.primefaces.model.DefaultScheduleModel;
import org.primefaces.model.DefaultStreamedContent;
import org.primefaces.model.ScheduleEvent;
import org.primefaces.model.ScheduleModel;
import org.primefaces.model.StreamedContent;

/**
 *
 * @author rcacacho
 */
@ManagedBean(name = "listaPedidosMB")
@ViewScoped
public class ListaPedidosMB implements Serializable {

    private static final Logger log = Logger.getLogger(ListaPedidosMB.class);

    @EJB
    private PedidosBeanLocal pedidoBean;
    @EJB
    private CatalogosBeanLocal catalogoBean;

    @Resource(lookup = "jdbc/globamix")
    private DataSource dataSource;

    private List<Pedidos> listPedidos;
    private List<Pedidos> listPedidoFiltrado;
    private Integer idsesor;
    private Integer idcliente;
    private Integer idestadopedido;
    private String obra;
    private Date fechaInicio;
    private Date fechaFin;
    private List<Asesor> listAsesor;
    private List<Cliente> listCliente;
    private List<Estadopedido> listEstado;
    private Date fechaInicioReporte;
    private Date fechaFinReporte;
    private ScheduleModel schedule;
    private ScheduleModel lazyEventModel;
    private ScheduleEvent pedidoCalendario = new DefaultScheduleEvent();
    private Pedidos pedidoSelected;
    private ScheduleModel lazyModel;

    public ListaPedidosMB() {
        schedule = new DefaultScheduleModel();
    }

    @PostConstruct
    void cargarDatos() {
        List<Pedidos> list = pedidoBean.listPedido();
        if (list != null) {
            listPedidos = list;
        } else {
            listPedidos = new ArrayList<>();
        }

        List<Asesor> listAs = catalogoBean.listAsesor();
        if (listAs != null) {
            listAsesor = listAs;
        } else {
            listAsesor = new ArrayList<>();
        }

        List<Cliente> listCl = catalogoBean.listCliente();
        if (listCl != null) {
            listCliente = listCl;
        } else {
            listCliente = new ArrayList<>();
        }

        List<Estadopedido> listEst = catalogoBean.listEstadoPedido();
        if (listEst != null) {
            listEstado = listEst;
        } else {
            listEstado = new ArrayList<>();
        }

        fechaInicio = new Date();
        fechaFin = new Date();
        buscarFiltro();
        loadCalendario();
    }

    public void loadData() {

    }

    public void verDetalle(Integer idPedido) {
        JsfUtil.redirectTo("/pedidos/detalle.xhtml?faces-redirect=true&idPedido=" + idPedido);
    }

    public void limpiarCampos() {
        idcliente = null;
        idestadopedido = null;
        idestadopedido = null;
        obra = null;
        fechaFin = null;
        fechaInicio = null;

        cargarDatos();
    }

    public void buscarFiltro() {
        try {
            if (idcliente != null || idestadopedido != null || idsesor != null || obra != null || fechaInicio != null || fechaFin != null) {
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

                List<Pedidos> response = pedidoBean.listPedidoBuzon(idsesor, idcliente, idestadopedido, obra, fechaInicio, fechaFin);
                if (response != null) {
                    listPedidos = response;
                } else {
                    listPedidos = new ArrayList<>();
                }
            }
        } catch (Exception ex) {
            log.error(ex.getMessage());
        }
    }

    public void dialogReporte() {
        fechaInicioReporte = null;
        fechaFinReporte = null;
        RequestContext.getCurrentInstance().execute("PF('dlgReporte').show()");
    }

    public void eliminarPedido(Pedidos id) throws IOException {
        Pedidos response = pedidoBean.deletePedido(id, SesionUsuarioMB.getUserName());
        if (response != null) {
            cargarDatos();
            JsfUtil.addSuccessMessage("Se elimino el pedido exitosamente");
            return;
        }
        JsfUtil.addErrorMessage("Sucedio un error al eliminar");
    }

    public void onRowEditPedido(RowEditEvent event) throws IOException {
        Pedidos objeto = ((Pedidos) event.getObject());
        Pedidos response = pedidoBean.updatePedido(objeto, SesionUsuarioMB.getUserName());
        if (response == null) {
            JsfUtil.addErrorMessage("Ocurrio un error al actualizar");
        } else {
            JsfUtil.addSuccessMessage("Pedido actualizado correctamente");
        }
    }

    public String abrirRegistro(Integer idPedido) {
        return "/pedidos/registro.xhtml?faces-redirect=true&idPedido=" + idPedido;
    }

    public StreamedContent generReporte() {
        if (fechaInicioReporte == null || fechaFinReporte == null) {
            JsfUtil.addErrorMessage("Debe de indicar las fechas");
            return null;
        }

        Calendar c = Calendar.getInstance();
        c.setTime(fechaInicioReporte);
        c.set(Calendar.HOUR_OF_DAY, 0);
        c.set(Calendar.MINUTE, 0);
        c.set(Calendar.SECOND, 0);
        fechaInicioReporte = c.getTime();

        Calendar c1 = Calendar.getInstance();
        c1.setTime(fechaFinReporte);
        c1.set(Calendar.HOUR_OF_DAY, 23);
        c1.set(Calendar.MINUTE, 59);
        c1.set(Calendar.SECOND, 59);
        fechaFinReporte = c1.getTime();

//        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
//        sdf.format(fechaIncioUsuario);
//        sdf.format(fechaFinUsuario);
        String nombreReporte;
        String nombreArchivo;
        try {
            ServletContext servletContext = (ServletContext) FacesContext.getCurrentInstance().getExternalContext().getContext();
            String realPath = servletContext.getRealPath("/");
            //crearImage();
            HashMap parametros = new HashMap();
            nombreReporte = "rptPedidos";
            nombreArchivo = "Pedidos";

            parametros.put("IMAGE", "logo.jpeg");
            parametros.put("DIRECTORIO", realPath + File.separator + "resources" + File.separator + "images" + File.separator);
            parametros.put("fechaInicio", fechaInicioReporte);
            parametros.put("fechaFin", fechaFinReporte);
            parametros.put("usuario", SesionUsuarioMB.getUserName());

            ReporteJasper reporteJasper = JasperUtil.jasperReportPDF(nombreReporte, nombreArchivo, parametros, dataSource);
            StreamedContent streamedContent;
            FileInputStream stream = new FileInputStream(realPath + "resources/reports/" + reporteJasper.getFileName());
            streamedContent = new DefaultStreamedContent(stream, "application/pdf", reporteJasper.getFileName());
            return streamedContent;

        } catch (Exception ex) {
            JsfUtil.addErrorMessage("Ocurrio un error al generar el pdf del reporte");
        }
        RequestContext.getCurrentInstance().execute("PF('dlgGenerarEmpleado').hide()");
        return null;
    }

    public void verEditar(Integer idPedido) {
        JsfUtil.redirectTo("/pedidos/editar.xhtml?faces-redirect=true&idPedido=" + idPedido);
    }

    public void confirmarPedido(Pedidos idPedido) throws IOException {
        Confirmacionpago confir = new Confirmacionpago();
        confir.setIdpedido(idPedido);
        confir.setNombreconfirmo(SesionUsuarioMB.getUserName());

        Confirmacionpago response = pedidoBean.saveConfirmarPedido(confir, SesionUsuarioMB.getRolUsuario());
        if (response != null) {
            idPedido.setConfirmado(Boolean.TRUE);
            Pedidos responsePedido = pedidoBean.updatePedido(idPedido, SesionUsuarioMB.getRolUsuario());

            JsfUtil.addSuccessMessage("Se registro la confirmación exisitosamente");
        }
    }

    private DefaultScheduleEvent buildEvent(Pedidos pedido) {
        DefaultScheduleEvent e = new DefaultScheduleEvent();
        if (pedido.getIdtipocemento() != null) {
            e.setDescription(pedido.getIdtipocemento().getDescripcion());
        }
        e.setEndDate(pedido.getFechapedido());
        e.setStartDate(pedido.getFechapedido());
        e.setTitle(pedido.getObra());
        e.setData(pedido);
        if (pedido.getIdestadopedido().getIdestadopedido().equals(EstadoPedido.EN_PROCESO.getValue())) {
            e.setStyleClass("rowColorAmarillo");
        }
        if (pedido.getIdestadopedido().getIdestadopedido().equals(EstadoPedido.FINALIZADO.getValue())) {
            e.setStyleClass("rowColorCeleste");
        }
        //e.setStyleClass(dto.getStyleClass());
        return e;
    }

    public void loadCalendario() {
        Calendar fecha = Calendar.getInstance();
        int anio = fecha.get(Calendar.YEAR);
        int mes = fecha.get(Calendar.MONTH) + 1;

        schedule = new DefaultScheduleModel();
        List<Pedidos> response = pedidoBean.listPedidoByMesAnio(mes, anio);
        if (response != null) {
            for (Pedidos ped : response) {
                schedule.addEvent(buildEvent(ped));
            }
        }
    }

    public void onEventSelect(SelectEvent selectEvent) {
        pedidoCalendario = (ScheduleEvent) selectEvent.getObject();
        pedidoCalendario.getDescription();
        pedidoSelected = (Pedidos) pedidoCalendario.getData();
        log.debug("Evento seleccionado: " + pedidoSelected.toString());
        Pedidos response = pedidoBean.findPedidoById(pedidoSelected.getIdpedido());
        if (response != null) {
            pedidoSelected = response;
        }
    }

    public void onViewChange(SelectEvent selectEvent) {
        String view = selectEvent.getObject().toString();

        Schedule schedule = (Schedule) FacesContext.getCurrentInstance().getViewRoot().findComponent(":form:schedule");
        if (schedule == null) {
            System.out.println("------> es null es schedule..........");
        }

        AjaxBehaviorEvent behaviorEvent = (AjaxBehaviorEvent) selectEvent;

        //ZoneId zoneId = CalendarUtils.calculateZoneId(schedule.getTimeZone());
        //System.out.println("zoneId " + zoneId.toString());
    }


    /*Metodos getters y setters*/
    public List<Pedidos> getListPedidos() {
        return listPedidos;
    }

    public void setListPedidos(List<Pedidos> listPedidos) {
        this.listPedidos = listPedidos;
    }

    public List<Pedidos> getListPedidoFiltrado() {
        return listPedidoFiltrado;
    }

    public void setListPedidoFiltrado(List<Pedidos> listPedidoFiltrado) {
        this.listPedidoFiltrado = listPedidoFiltrado;
    }

    public Integer getIdsesor() {
        return idsesor;
    }

    public void setIdsesor(Integer idsesor) {
        this.idsesor = idsesor;
    }

    public Integer getIdcliente() {
        return idcliente;
    }

    public void setIdcliente(Integer idcliente) {
        this.idcliente = idcliente;
    }

    public Integer getIdestadopedido() {
        return idestadopedido;
    }

    public void setIdestadopedido(Integer idestadopedido) {
        this.idestadopedido = idestadopedido;
    }

    public String getObra() {
        return obra;
    }

    public void setObra(String obra) {
        this.obra = obra;
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

    public List<Asesor> getListAsesor() {
        return listAsesor;
    }

    public void setListAsesor(List<Asesor> listAsesor) {
        this.listAsesor = listAsesor;
    }

    public List<Cliente> getListCliente() {
        return listCliente;
    }

    public void setListCliente(List<Cliente> listCliente) {
        this.listCliente = listCliente;
    }

    public List<Estadopedido> getListEstado() {
        return listEstado;
    }

    public void setListEstado(List<Estadopedido> listEstado) {
        this.listEstado = listEstado;
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

    public PedidosBeanLocal getPedidoBean() {
        return pedidoBean;
    }

    public void setPedidoBean(PedidosBeanLocal pedidoBean) {
        this.pedidoBean = pedidoBean;
    }

    public ScheduleModel getSchedule() {
        return schedule;
    }

    public void setSchedule(ScheduleModel schedule) {
        this.schedule = schedule;
    }

    public ScheduleModel getLazyEventModel() {
        return lazyEventModel;
    }

    public void setLazyEventModel(ScheduleModel lazyEventModel) {
        this.lazyEventModel = lazyEventModel;
    }

    public Pedidos getPedidoSelected() {
        return pedidoSelected;
    }

    public void setPedidoSelected(Pedidos pedidoSelected) {
        this.pedidoSelected = pedidoSelected;
    }

}
