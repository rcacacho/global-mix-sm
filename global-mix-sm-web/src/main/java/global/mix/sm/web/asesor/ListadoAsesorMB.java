package global.mix.sm.web.asesor;

import global.mix.sm.api.ejb.AsesoresBeanLocal;
import global.mix.sm.api.entity.Asesor;
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
@ManagedBean(name = "listadoAsesorMB")
@ViewScoped
public class ListadoAsesorMB implements Serializable {

    private static final Logger log = Logger.getLogger(ListadoAsesorMB.class);

    @EJB
    private AsesoresBeanLocal asesorBean;

    @Resource(lookup = "jdbc/globamix")
    private DataSource dataSource;

    private List<Asesor> listAsesor;
    private String nombres;
    private List<Asesor> listAsesorFiltrado;
    private Asesor asesor;

    @PostConstruct
    void cargarDatos() {
        List<Asesor> list = asesorBean.ListAsesores();
        if (list != null) {
            listAsesor = list;
        } else {
            listAsesor = new ArrayList<>();
        }
    }

    public void verDetalle(Integer idAsesor) {
        JsfUtil.redirectTo("/asesor/detalle.xhtml?faces-redirect=true&idAsesor=" + idAsesor);
    }

    public void verRegistro() {
        asesor = null;
        asesor = new Asesor();

        RequestContext.getCurrentInstance().execute("PF('dlgRegistro').show()");
    }

    public void limpiarCampos() {
        nombres = null;

        cargarDatos();
    }

    public void buscarFiltro() {
        try {
            if (nombres != null) {
                List<Asesor> response = asesorBean.ListAsesorBuzon(nombres);
                if (response != null) {
                    listAsesor = response;
                }
            }

        } catch (Exception ex) {
            log.error(ex.getMessage());
        }
    }

    public void registroAsesor() throws IOException {
        Asesor responseSave = asesorBean.saveAsesor(asesor, SesionUsuarioMB.getUserName());
        if (responseSave != null) {
            JsfUtil.addSuccessMessage("Asesor creado exitosamente");
            cargarDatos();
            RequestContext.getCurrentInstance().execute("PF('dlgRegistro').hide()");
        } else {
            JsfUtil.addErrorMessage("Ocurrio un error verificar datos");
        }
    }

    public void eliminarAsesor(Asesor id) throws IOException {
        Asesor response = asesorBean.deleteAsesor(id, SesionUsuarioMB.getUserName());
        if (response != null) {
            cargarDatos();
            JsfUtil.addSuccessMessage("Se elimino el asesor exitosamente");
            return;
        }

        JsfUtil.addErrorMessage("Sucedio un error al eliminar");
    }

    public void onRowEditAsesor(RowEditEvent event) throws IOException {
        Asesor objeto = ((Asesor) event.getObject());
        Asesor response = asesorBean.updateAsesor(objeto, SesionUsuarioMB.getUserName());
        if (response == null) {
            JsfUtil.addErrorMessage("Ocurrio un error al actualizar");
        } else {
            JsfUtil.addSuccessMessage("Asesor actualizado correctamente");
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
            nombreReporte = "rptAsesor";
            nombreArchivo = "Asesor";

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
    public List<Asesor> getListAsesor() {
        return listAsesor;
    }

    public void setListAsesor(List<Asesor> listAsesor) {
        this.listAsesor = listAsesor;
    }

    public String getNombres() {
        return nombres;
    }

    public void setNombres(String nombres) {
        this.nombres = nombres;
    }

    public List<Asesor> getListAsesorFiltrado() {
        return listAsesorFiltrado;
    }

    public void setListAsesorFiltrado(List<Asesor> listAsesorFiltrado) {
        this.listAsesorFiltrado = listAsesorFiltrado;
    }

    public Asesor getAsesor() {
        return asesor;
    }

    public void setAsesor(Asesor asesor) {
        this.asesor = asesor;
    }

}
