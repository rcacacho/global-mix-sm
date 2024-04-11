package global.mix.sm.web.material;

import global.mix.sm.api.ejb.CatalogosBeanLocal;
import global.mix.sm.api.ejb.MaterialBeanLocal;
import global.mix.sm.api.entity.Detallematerial;
import global.mix.sm.api.entity.Material;
import global.mix.sm.api.entity.Unidadmedida;
import global.mix.sm.web.utils.JsfUtil;
import global.mix.sm.web.utils.SesionUsuarioMB;
import java.io.IOException;
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
import org.primefaces.event.RowEditEvent;

/**
 *
 * @author rcacacho
 */
@ManagedBean(name = "listaMaterialMB")
@ViewScoped
public class ListaMaterialMB implements Serializable {

 private static final Logger log = Logger.getLogger(ListaMaterialMB.class);

    @EJB
    private MaterialBeanLocal materialBean;
    @EJB
    private CatalogosBeanLocal catalogoBean;

    private List<Material> listMaterial;
    private String material;
    private Date fechaInicio;
    private Date fechaFin;
    private List<Material> listMaterialFiltrado;
    private Unidadmedida unidadMedida;
    private List<Unidadmedida> listUnidadMedida;
    private Material mat;
    private Material materialAgregar;
    private Double existencia;

    public ListaMaterialMB() {
        unidadMedida = null;
        unidadMedida = new Unidadmedida();
    }

    @PostConstruct
    void cargarDatos() {
        List<Material> list = materialBean.listMaterial();
        if (list != null) {
            listMaterial = list;
        } else {
            listMaterial = new ArrayList<>();
        }
    }

    public void verDetalle(Integer idMaterial) {
        JsfUtil.redirectTo("/material/detalle.xhtml?faces-redirect=true&idMaterial=" + idMaterial);
    }

    public void verRegistro() {
        mat = null;
        mat = new Material();
        unidadMedida = null;
        unidadMedida = new Unidadmedida();

        listUnidadMedida = catalogoBean.listUnidadMedida();

        RequestContext.getCurrentInstance().execute("PF('dlgRegistro').show()");
    }

    public void limpiarCampos() {
        material = null;
        fechaInicio = null;
        fechaFin = null;
        cargarDatos();
    }

    public void buscarFiltro() {
        try {
            if (material != null || fechaInicio != null || fechaFin != null) {
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

                List<Material> response = materialBean.listMaterialBuzon(material, fechaInicio, fechaFin);
                if (response != null) {
                    listMaterial = response;
                } else {
                    listMaterial = new ArrayList<>();
                }
            }

        } catch (Exception ex) {
            log.error(ex.getMessage());
        }
    }

    public void registroMaterial() throws IOException {
        if (mat.getValor() == 0) {
            JsfUtil.addErrorMessage("Debe ingresar un valor de factura");
            return;
        }

        Material responseValid = materialBean.findMaterialByNombre(mat.getMaterial());
        if (responseValid != null) {
            JsfUtil.addErrorMessage("Esta material ya existe debe actualizar su existencia");
            return;
        }

        mat.setValorneto(mat.getValor() / 1.12);

        if (unidadMedida != null) {
            mat.setIdunidadmedida(unidadMedida);
        }

        if (mat.getIdunidadmedida().getIdkilogramo() != null) {
            Double totalUnidad = mat.getIdunidadmedida().getIdkilogramo().getValor() * mat.getExistenciainicial();
            mat.setCosto(mat.getValorneto() / totalUnidad);
            mat.setUnidadmedidaexistencia(mat.getExistenciainicial() * mat.getIdunidadmedida().getIdkilogramo().getValor());
        } else {
            mat.setCosto(mat.getValorneto() / mat.getExistenciainicial());
            mat.setUnidadmedidaexistencia(mat.getExistenciainicial());
        }

        Material responseSave = materialBean.saveMaterial(mat, SesionUsuarioMB.getUserName());
        if (responseSave != null) {
            Detallematerial detalle = new Detallematerial();
            Double cantidadConvertida = mat.getExistenciainicial() * mat.getIdunidadmedida().getIdkilogramo().getValor();
            detalle.setExistenciaActual(mat.getExistenciainicial());
            detalle.setIdmaterial(mat);
            detalle.setIngreso(mat.getExistenciainicial());
            detalle.setIngresounidadmedida(cantidadConvertida);
            detalle.setTotal(mat.getExistenciainicial() * mat.getValorneto());
            Detallematerial responseDetalle = materialBean.saveDetalleMaterial(detalle, SesionUsuarioMB.getUserName());

            JsfUtil.addSuccessMessage("Material creado exitosamente");
            cargarDatos();
            RequestContext.getCurrentInstance().execute("PF('dlgRegistro').hide()");
        } else {
            JsfUtil.addErrorMessage("Ocurrio un error verificar datos");
        }
    }

    public void eliminarMaterial(Material id) throws IOException {
        Material response = materialBean.deleteMaterial(id, SesionUsuarioMB.getUserName());
        if (response != null) {
            cargarDatos();
            JsfUtil.addSuccessMessage("Se elimino el material exitosamente");
            return;
        }

        JsfUtil.addErrorMessage("Sucedio un error al eliminar");
    }

    public void verActualizarExistencia() {
        materialAgregar = null;
        materialAgregar = new Material();
        existencia = null;
        RequestContext.getCurrentInstance().execute("PF('dlgAgregar').show()");
    }

    public void actualizarMaterial() throws IOException {
        if (materialAgregar.getValor() == 0) {
            JsfUtil.addErrorMessage("Debe ingresar un valor de factura");
            return;
        }

        materialAgregar.setExistenciainicial(existencia + materialAgregar.getExistenciainicial());
        materialAgregar.setValorneto(materialAgregar.getValor() / 1.12);

        if (mat.getValor() != materialAgregar.getValor()) {
            if (materialAgregar.getIdunidadmedida().getIdkilogramo() != null) {
                Double totalUnidad = materialAgregar.getIdunidadmedida().getIdkilogramo().getValor() * materialAgregar.getExistenciainicial();
                materialAgregar.setCosto(materialAgregar.getValorneto() / totalUnidad);
                materialAgregar.setUnidadmedidaexistencia(materialAgregar.getExistenciainicial() * materialAgregar.getIdunidadmedida().getIdkilogramo().getValor());
            } else {
                materialAgregar.setCosto(materialAgregar.getValorneto() / materialAgregar.getExistenciainicial());
                materialAgregar.setUnidadmedidaexistencia(materialAgregar.getExistenciainicial());
            }
        } else {
            if (mat.getIdunidadmedida().getIdkilogramo() != null) {
                Double totalUnidad = materialAgregar.getIdunidadmedida().getIdkilogramo().getValor() * materialAgregar.getExistenciainicial();
                materialAgregar.setCosto(materialAgregar.getValorneto() / totalUnidad);
                materialAgregar.setUnidadmedidaexistencia(materialAgregar.getExistenciainicial() * materialAgregar.getIdunidadmedida().getIdkilogramo().getValor());
            } else {
                materialAgregar.setCosto(materialAgregar.getValorneto() / materialAgregar.getExistenciainicial());
                materialAgregar.setUnidadmedidaexistencia(materialAgregar.getExistenciainicial());
            }
        }

        Material responseSave = materialBean.updateMaterial(materialAgregar, SesionUsuarioMB.getUserName());
        if (responseSave != null) {
            Detallematerial detalle = new Detallematerial();
            Double cantidadConvertida = mat.getExistenciainicial() * mat.getIdunidadmedida().getIdkilogramo().getValor();
            detalle.setExistenciaActual(mat.getExistenciainicial());
            detalle.setIngresounidadmedida(cantidadConvertida);
            detalle.setIdmaterial(mat);
            detalle.setIngreso(existencia);
            Detallematerial responseDetalle = materialBean.saveDetalleMaterial(detalle, SesionUsuarioMB.getUserName());

            JsfUtil.addSuccessMessage("Material actualizado exitosamente");
            cargarDatos();
            RequestContext.getCurrentInstance().execute("PF('dlgAgregar').hide()");
        } else {
            JsfUtil.addErrorMessage("Ocurrio un error verificar datos");
        }
    }

    public void seleccionarMaterial() {
        Material materi = catalogoBean.findMaterialById(materialAgregar.getIdmaterial());
        if (materi != null) {
            mat = materi;
        }
    }

    public void onRowEditMaterial(RowEditEvent event) throws IOException {
        Material objeto = ((Material) event.getObject());
        Material response = materialBean.updateMaterial(objeto, SesionUsuarioMB.getUserName());
        if (response == null) {
            JsfUtil.addErrorMessage("Ocurrio un error al actualizar");
        } else {
            JsfUtil.addSuccessMessage("Material actualizado correctamente");
        }
    }

    /*Metodos getters y setters*/
    public List<Material> getListMaterial() {
        return listMaterial;
    }

    public void setListMaterial(List<Material> listMaterial) {
        this.listMaterial = listMaterial;
    }

    public String getMaterial() {
        return material;
    }

    public void setMaterial(String material) {
        this.material = material;
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

    public List<Material> getListMaterialFiltrado() {
        return listMaterialFiltrado;
    }

    public void setListMaterialFiltrado(List<Material> listMaterialFiltrado) {
        this.listMaterialFiltrado = listMaterialFiltrado;
    }

    public List<Unidadmedida> getListUnidadMedida() {
        return listUnidadMedida;
    }

    public void setListUnidadMedida(List<Unidadmedida> listUnidadMedida) {
        this.listUnidadMedida = listUnidadMedida;
    }

    public Material getMat() {
        return mat;
    }

    public void setMat(Material mat) {
        this.mat = mat;
    }

    public Material getMaterialAgregar() {
        return materialAgregar;
    }

    public void setMaterialAgregar(Material materialAgregar) {
        this.materialAgregar = materialAgregar;
    }

    public Double getExistencia() {
        return existencia;
    }

    public void setExistencia(Double existencia) {
        this.existencia = existencia;
    }

    public Unidadmedida getUnidadMedida() {
        return unidadMedida;
    }

    public void setUnidadMedida(Unidadmedida unidadMedida) {
        this.unidadMedida = unidadMedida;
    }
}
