package global.mix.sm.web.cliente;

import global.mix.sm.api.ejb.ClienteBeanLocal;
import global.mix.sm.api.entity.Cliente;
import global.mix.sm.web.utils.JasperUtil;
import global.mix.sm.web.utils.JsfUtil;
import global.mix.sm.web.utils.ReporteJasper;
import global.mix.sm.web.utils.SesionUsuarioMB;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.Serializable;
import java.util.ArrayList;
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
@ManagedBean(name = "listaClientesMB")
@ViewScoped
public class ListaClientesMB implements Serializable {

    private static final Logger log = Logger.getLogger(ListaClientesMB.class);

    @EJB
    private ClienteBeanLocal clienteBean;

    @Resource(lookup = "jdbc/globamix")
    private DataSource dataSource;

    private List<Cliente> listCliente;
    private String nit;
    private String nombres;
    private List<Cliente> listClienteFiltrado;
    private Cliente cliente;

    @PostConstruct
    void cargarDatos() {
        List<Cliente> list = clienteBean.ListClientes();
        if (list != null) {
            listCliente = list;
        } else {
            listCliente = new ArrayList<>();
        }
    }

    public void verDetalle(Integer idCliente) {
        JsfUtil.redirectTo("/cliente/detalle.xhtml?faces-redirect=true&idCliente=" + idCliente);
    }

    public void verRegistro() {
        cliente = null;
        cliente = new Cliente();

        RequestContext.getCurrentInstance().execute("PF('dlgRegistro').show()");
    }

    public void limpiarCampos() {
        nombres = null;
        nit = null;

        cargarDatos();
    }

    public void buscarFiltro() {
        try {
            if (nombres != null || nit != null) {
                List<Cliente> response = clienteBean.ListClientesBuzon(nombres, nit);
                if (response != null) {
                    listCliente = response;
                }
            }

        } catch (Exception ex) {
            log.error(ex.getMessage());
        }
    }

    public void registroCliente() throws IOException {
        Cliente responseSave = clienteBean.saveCliente(cliente, SesionUsuarioMB.getUserName());
        if (responseSave != null) {
            JsfUtil.addSuccessMessage("Cliente creado exitosamente");
            cargarDatos();
            RequestContext.getCurrentInstance().execute("PF('dlgRegistro').hide()");
        } else {
            JsfUtil.addErrorMessage("Ocurrio un error verificar datos");
        }
    }

    public void eliminarCliente(Cliente id) throws IOException {
        Cliente response = clienteBean.deleteCliente(id, SesionUsuarioMB.getUserName());
        if (response != null) {
            cargarDatos();
            JsfUtil.addSuccessMessage("Se elimino el cliente exitosamente");
            return;
        }

        JsfUtil.addErrorMessage("Sucedio un error al eliminar");
    }

    public void onRowEditCliente(RowEditEvent event) throws IOException {
        Cliente objeto = ((Cliente) event.getObject());
        Cliente response = clienteBean.updateCliente(objeto, SesionUsuarioMB.getUserName());
        if (response == null) {
            JsfUtil.addErrorMessage("Ocurrio un error al actualizar");
        } else {
            JsfUtil.addSuccessMessage("Cliente actualizado correctamente");
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
            nombreReporte = "rptClientes";
            nombreArchivo = "Clientes";

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
    public List<Cliente> getListCliente() {
        return listCliente;
    }

    public void setListCliente(List<Cliente> listCliente) {
        this.listCliente = listCliente;
    }

    public String getNit() {
        return nit;
    }

    public void setNit(String nit) {
        this.nit = nit;
    }

    public String getNombres() {
        return nombres;
    }

    public void setNombres(String nombres) {
        this.nombres = nombres;
    }

    public List<Cliente> getListClienteFiltrado() {
        return listClienteFiltrado;
    }

    public void setListClienteFiltrado(List<Cliente> listClienteFiltrado) {
        this.listClienteFiltrado = listClienteFiltrado;
    }

    public Cliente getCliente() {
        return cliente;
    }

    public void setCliente(Cliente cliente) {
        this.cliente = cliente;
    }

}
