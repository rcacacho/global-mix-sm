package global.mix.sm.web.reportes;

import global.mix.sm.api.ejb.CatalogosBeanLocal;
import global.mix.sm.api.entity.Material;
import global.mix.sm.web.utils.JasperUtil;
import global.mix.sm.web.utils.JsfUtil;
import global.mix.sm.web.utils.ReporteJasper;
import global.mix.sm.web.utils.SesionUsuarioMB;
import java.io.File;
import java.io.FileInputStream;
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
import org.primefaces.model.DefaultStreamedContent;
import org.primefaces.model.StreamedContent;

/**
 *
 * @author elfo_
 */
@ManagedBean(name = "reporteMaterialMB")
@ViewScoped
public class ReporteMaterialMB implements Serializable {

    @EJB
    private CatalogosBeanLocal catalogoBean;

    @Resource(lookup = "jdbc/globamix")
    private DataSource dataSource;

    private Date fechaInicio;
    private Date fechaFin;
    private Material idmaterial;
    private List<Material> listMaterial;

    public ReporteMaterialMB() {
        idmaterial = new Material();
    }

    @PostConstruct
    void cargarDatos() {
        List<Material> list = catalogoBean.listMaterial();
        if (list != null) {
            listMaterial = list;
        } else {
            listMaterial = new ArrayList<>();
        }
    }

    public StreamedContent generReporteFechas() {
        if (fechaInicio == null || fechaFin == null) {
            JsfUtil.addErrorMessage("Debe de indicar las fechas");
            return null;
        }

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
            nombreReporte = "rptMaterialFechas";
            nombreArchivo = "Material";

            parametros.put("IMAGE", "logo.jpeg");
            parametros.put("DIRECTORIO", realPath + File.separator + "resources" + File.separator + "images" + File.separator);
            parametros.put("fechaInicio", fechaInicio);
            parametros.put("fechaFin", fechaFin);
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

    public StreamedContent generReporte() {
        String nombreReporte;
        String nombreArchivo;
        try {

            ServletContext servletContext = (ServletContext) FacesContext.getCurrentInstance().getExternalContext().getContext();
            String realPath = servletContext.getRealPath("/");
            //crearImage();
            HashMap parametros = new HashMap();
            nombreReporte = "rptMaterialF";
            nombreArchivo = "Materiales";

            parametros.put("IMAGE", "logo.jpeg");
            parametros.put("DIRECTORIO", realPath + File.separator + "resources" + File.separator + "images" + File.separator);
            parametros.put("usuario", SesionUsuarioMB.getUserName());
            parametros.put("idmaterial", idmaterial.getIdmaterial());
            parametros.put("unidadmedida", idmaterial.getIdunidadmedida().getUnidadmedida());

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

    public Material getIdmaterial() {
        return idmaterial;
    }

    public void setIdmaterial(Material idmaterial) {
        this.idmaterial = idmaterial;
    }

    public List<Material> getListMaterial() {
        return listMaterial;
    }

    public void setListMaterial(List<Material> listMaterial) {
        this.listMaterial = listMaterial;
    }

}
