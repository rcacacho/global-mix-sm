package global.mix.sm.web.configuracion;

import global.mix.sm.api.ejb.ConfiguracionBeanLocal;
import global.mix.sm.api.entity.Kilogramo;
import global.mix.sm.api.entity.Unidadmedida;
import global.mix.sm.web.utils.JsfUtil;
import global.mix.sm.web.utils.SesionUsuarioMB;
import java.io.IOException;
import java.io.Serializable;
import java.util.ArrayList;
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
@ManagedBean(name = "listaUnidadMedidaMB")
@ViewScoped
public class ListaUnidadMedidaMB implements Serializable {

    private static final Logger log = Logger.getLogger(ListaUnidadMedidaMB.class);

    @EJB
    private ConfiguracionBeanLocal configuracionBean;

    private List<Unidadmedida> listUnidadMedida;
    private Unidadmedida unidadMedida;
    private String descripcion;
    private List<Unidadmedida> listUnidadMedidaFilter;
    private Kilogramo kilogramo;
    private List<Kilogramo> listKilogramo;

    @PostConstruct
    void cargarDatos() {
        List<Unidadmedida> list = configuracionBean.ListUnidadMedida();
        if (list != null) {
            listUnidadMedida = list;
        } else {
            listUnidadMedida = new ArrayList<>();
        }

        List<Kilogramo> listKilo = configuracionBean.ListKilogramos();
        if (listKilo != null) {
            listKilogramo = listKilo;
        }
    }

    public void verRegistro() {
        unidadMedida = null;
        unidadMedida = new Unidadmedida();
        kilogramo = null;
        kilogramo = new Kilogramo();
        List<Kilogramo> list = configuracionBean.ListKilogramos();
        if (list != null) {
            listKilogramo = list;
        }

        RequestContext.getCurrentInstance().execute("PF('dlgRegistro').show()");
    }

    public void buscarFiltro() {
        try {
            if (descripcion != null) {
                List<Unidadmedida> response = configuracionBean.listUnidadMedidaBuzon(descripcion);
                if (response != null) {
                    listUnidadMedida = response;
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

    public void verDetalle(Integer idUnidadMedida) {
        JsfUtil.redirectTo("/configuracion/unidadmedida/detalle.xhtml?faces-redirect=true&idUnidadMedida=" + idUnidadMedida);
    }

    public void registroUnidad() throws IOException {
        if (unidadMedida.getDescripcion() == null) {
            JsfUtil.addErrorMessage("Debe registrar una descripción");
            return;
        }

        if (kilogramo != null) {
            unidadMedida.setIdkilogramo(kilogramo);
        }

        Unidadmedida responseSave = configuracionBean.saveUnidadMedida(unidadMedida, SesionUsuarioMB.getUserName());
        if (responseSave != null) {
            JsfUtil.addSuccessMessage("Configuración registrada exitosamente");
        } else {
            JsfUtil.addErrorMessage("Ocurrio un error verificar datos");
        }
        cargarDatos();
        RequestContext.getCurrentInstance().execute("PF('dlgRegistro').hide()");
    }

    public void eliminarUnidad(Unidadmedida id) throws IOException {
        Unidadmedida response = configuracionBean.deleteUnidadMedida(id, SesionUsuarioMB.getUserName());
        if (response != null) {
            cargarDatos();
            JsfUtil.addSuccessMessage("Se elimino la unidad de medida exitosamente");
            return;
        }

        JsfUtil.addErrorMessage("Sucedio un error al eliminar");
    }

    public void onRowEditUnidad(RowEditEvent event) throws IOException {
        Unidadmedida objeto = ((Unidadmedida) event.getObject());
        Unidadmedida response = configuracionBean.updateUnidadMedida(objeto, SesionUsuarioMB.getUserName());
        if (response == null) {
            JsfUtil.addErrorMessage("Ocurrio un error al actualizar");
        } else {
            JsfUtil.addSuccessMessage("Unidad medida actualizada correctamente");
        }
    }

    /*Metodos getters y setters*/
    public List<Unidadmedida> getListUnidadMedida() {
        return listUnidadMedida;
    }

    public void setListUnidadMedida(List<Unidadmedida> listUnidadMedida) {
        this.listUnidadMedida = listUnidadMedida;
    }

    public Unidadmedida getUnidadMedida() {
        return unidadMedida;
    }

    public void setUnidadMedida(Unidadmedida unidadMedida) {
        this.unidadMedida = unidadMedida;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public List<Unidadmedida> getListUnidadMedidaFilter() {
        return listUnidadMedidaFilter;
    }

    public void setListUnidadMedidaFilter(List<Unidadmedida> listUnidadMedidaFilter) {
        this.listUnidadMedidaFilter = listUnidadMedidaFilter;
    }

    public Kilogramo getKilogramo() {
        return kilogramo;
    }

    public void setKilogramo(Kilogramo kilogramo) {
        this.kilogramo = kilogramo;
    }

    public List<Kilogramo> getListKilogramo() {
        return listKilogramo;
    }

    public void setListKilogramo(List<Kilogramo> listKilogramo) {
        this.listKilogramo = listKilogramo;
    }

}
