package global.mix.sm.web.despacho;

import global.mix.sm.api.ejb.CatalogosBeanLocal;
import global.mix.sm.api.ejb.DespachosBeanLocal;
import global.mix.sm.api.entity.Asesor;
import global.mix.sm.api.entity.Cliente;
import global.mix.sm.api.entity.Despachos;
import global.mix.sm.api.entity.Estadodespacho;
import global.mix.sm.api.enums.EstadoDespacho;
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
import org.primefaces.model.DefaultStreamedContent;
import org.primefaces.model.StreamedContent;

/**
 *
 * @author elfo_
 */
@ManagedBean(name = "listaDespachoMB")
@ViewScoped
public class ListaDespachoMB implements Serializable {

    private static final Logger log = Logger.getLogger(ListaDespachoMB.class);

    @EJB
    private DespachosBeanLocal despachoBean;
    @EJB
    private CatalogosBeanLocal catalogoBean;

    @Resource(lookup = "jdbc/globamix")
    private DataSource dataSource;

    private List<Despachos> listDespachos;
    private List<Despachos> listDespachoFiltrado;
    private Integer idsesor;
    private Integer idcliente;
    private String obra;
    private Date fechaInicio;
    private Date fechaFin;
    private List<Asesor> listAsesor;
    private List<Cliente> listCliente;
    private Date fechaInicioReporte;
    private Date fechaFinReporte;
    private Double pago;
    private Double metroVendido;
    private Double pagoComision;
    private Despachos despachoSelected;
    private Asesor asesor;
    private Despachos despachoAsesor;

    @PostConstruct
    void cargarDatos() {
        List<Despachos> list = despachoBean.listDespachos();
        if (list != null) {
            listDespachos = list;
        } else {
            listDespachos = new ArrayList<>();
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
    }

    public void limpiarCampos() {
        idcliente = null;
        obra = null;
        idsesor = null;

        cargarDatos();
    }

    public void buscarFiltro() {
        try {
            if (idcliente != null || idsesor != null || obra != null || fechaInicio != null || fechaFin != null) {
                List<Despachos> response = despachoBean.listDespachoBuzon(idsesor, idcliente, obra, fechaInicio, fechaFin);
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

    public void dialogReporte() {
        fechaInicioReporte = null;
        fechaFinReporte = null;
        RequestContext.getCurrentInstance().execute("PF('dlgReporte').show()");
    }

    public void verDetalle(Integer idDespacho) {
        JsfUtil.redirectTo("/despacho/detalle.xhtml?faces-redirect=true&idDespacho=" + idDespacho);
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
            nombreReporte = "rptDespachos";
            nombreArchivo = "Despachos";

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

    public void finalizarPedido() throws IOException {
        if (pago != null) {
            despachoSelected.setSegundopago(pago);
            despachoSelected.setPagototal(pago + despachoSelected.getIdpedido().getCantidadpagada());
            despachoSelected.setPagototalsiniva(despachoSelected.getPagototal() / 1.12);
        } else {
            despachoSelected.setPagototal(despachoSelected.getIdpedido().getCantidadpagada());
            despachoSelected.setPagototalsiniva(despachoSelected.getPagototal() / 1.12);
        }

        if (metroVendido != null) {
            despachoSelected.setMetroscubicosvendidos(metroVendido);
        } else {
            despachoSelected.setMetroscubicosvendidos((double) despachoSelected.getIdpedido().getVolumen());
        }

        //falta de calculo de bombeo de diesel
        despachoSelected.setTotalmateriaprima(despachoSelected.getCostototaladitivodinero() + despachoSelected.getCostototalaguadinero()
                + despachoSelected.getCostototaldinerocemento() + despachoSelected.getCostototalpiedrindinero());

        if (despachoSelected.getCostototalotrodinero() != null) {
            despachoSelected.setTotalmateriaprima(despachoSelected.getTotalmateriaprima() + despachoSelected.getCostototalotrodinero());
        }

        if (despachoSelected.getIdpedido().getBombeo().equals("Si")) {
            despachoSelected.setCostototalbombeo(90 * despachoSelected.getMetroscubicosvendidos());
            despachoSelected.setTotalmateriaprima(despachoSelected.getTotalmateriaprima() + despachoSelected.getCostototalbombeo());
        }
        if (despachoSelected.getIdpedido().getColocado().equals("Si")) {
            despachoSelected.setCostototalcolocado(despachoSelected.getIdpedido().getCantidadcobradacolocado() * despachoSelected.getMetroscubicosvendidos());
            despachoSelected.setTotalmateriaprima(despachoSelected.getTotalmateriaprima() + despachoSelected.getCostototalcolocado());
        }
        
        if (despachoSelected.getCostototaldieseldinero() != null){
            despachoSelected.setTotalmateriaprima(despachoSelected.getTotalmateriaprima() + despachoSelected.getCostototaldieseldinero());
        }
        
        if (despachoSelected.getComision() != null){
            despachoSelected.setTotalmateriaprima(despachoSelected.getTotalmateriaprima() + despachoSelected.getComision());
        }

        despachoSelected.setBeneficioprimo(despachoSelected.getPagototalsiniva() - despachoSelected.getTotalmateriaprima());
        despachoSelected.setBeneficiometrocubico(despachoSelected.getBeneficioprimo() / despachoSelected.getMetroscubicosvendidos());
        despachoSelected.setPrecioventametrocubico(despachoSelected.getPagototal() / despachoSelected.getMetroscubicosvendidos());
        despachoSelected.setPrecioventametrocubicosiniva(despachoSelected.getPrecioventametrocubico() / 1.12);
        despachoSelected.setCostoobra(despachoSelected.getTotalmateriaprima());
        despachoSelected.setCostometrocubicovendido(despachoSelected.getCostoobra() / despachoSelected.getMetroscubicosvendidos());

        Estadodespacho responseEstado = catalogoBean.findEstadoDespacho(EstadoDespacho.FINALIZADO.getValue());
        despachoSelected.setIdestadodespacho(responseEstado);

        Despachos response = despachoBean.finalizarDespacho(despachoSelected, SesionUsuarioMB.getUserName());
        if (response != null) {
            JsfUtil.addSuccessMessage("Despacho finalizado correctamente");
        }
    }

    public void dialogFinalizar(Despachos des) {
        despachoSelected = des;
        RequestContext.getCurrentInstance().execute("PF('dlgFinalizar').show()");
    }

    public void dialogComision(Despachos des) {
        asesor = des.getIdasesor();
        despachoAsesor = des;
        RequestContext.getCurrentInstance().execute("PF('dlgComision').show()");
    }

    public void registrarComision() throws IOException {
        if (pagoComision == null) {
            JsfUtil.addErrorMessage("Debe ingresar un valor");
            return;
        }

        despachoAsesor.setComision(pagoComision);
        if (despachoAsesor.getTotalmateriaprima() != null) {
            despachoAsesor.setTotalmateriaprima(despachoAsesor.getTotalmateriaprima() + despachoAsesor.getComision());
            despachoAsesor.setBeneficioprimo(despachoAsesor.getPagototalsiniva() - despachoAsesor.getTotalmateriaprima());
            despachoAsesor.setBeneficiometrocubico(despachoAsesor.getBeneficioprimo() / despachoAsesor.getMetroscubicosvendidos());
            despachoAsesor.setPrecioventametrocubico(despachoAsesor.getPagototal() / despachoAsesor.getMetroscubicosvendidos());
            despachoAsesor.setPrecioventametrocubicosiniva(despachoAsesor.getPrecioventametrocubico() / 1.12);
            despachoAsesor.setCostoobra(despachoAsesor.getTotalmateriaprima());
            despachoAsesor.setCostometrocubicovendido(despachoAsesor.getCostoobra() / despachoAsesor.getMetroscubicosvendidos());
        }
         Despachos despa = despachoBean.updateDespacho(despachoAsesor, SesionUsuarioMB.getUserName());
    }

    /*Metodos getters y setters*/
    public List<Despachos> getListDespachos() {
        return listDespachos;
    }

    public void setListDespachos(List<Despachos> listDespachos) {
        this.listDespachos = listDespachos;
    }

    public List<Despachos> getListDespachoFiltrado() {
        return listDespachoFiltrado;
    }

    public void setListDespachoFiltrado(List<Despachos> listDespachoFiltrado) {
        this.listDespachoFiltrado = listDespachoFiltrado;
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

    public Double getPago() {
        return pago;
    }

    public void setPago(Double pago) {
        this.pago = pago;
    }

    public Double getMetroVendido() {
        return metroVendido;
    }

    public void setMetroVendido(Double metroVendido) {
        this.metroVendido = metroVendido;
    }

    public Double getPagoComision() {
        return pagoComision;
    }

    public void setPagoComision(Double pagoComision) {
        this.pagoComision = pagoComision;
    }

    public Despachos getDespachoSelected() {
        return despachoSelected;
    }

    public void setDespachoSelected(Despachos despachoSelected) {
        this.despachoSelected = despachoSelected;
    }

    public Asesor getAsesor() {
        return asesor;
    }

    public void setAsesor(Asesor asesor) {
        this.asesor = asesor;
    }

    public Despachos getDespachoAsesor() {
        return despachoAsesor;
    }

    public void setDespachoAsesor(Despachos despachoAsesor) {
        this.despachoAsesor = despachoAsesor;
    }

}
