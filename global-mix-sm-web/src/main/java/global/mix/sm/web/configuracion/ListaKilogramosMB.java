package global.mix.sm.web.configuracion;

import global.mix.sm.api.ejb.ConfiguracionBeanLocal;
import global.mix.sm.api.entity.Kilogramo;
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
@ManagedBean(name = "listaKilogramosMB")
@ViewScoped
public class ListaKilogramosMB implements Serializable {

    private static final Logger log = Logger.getLogger(ListaKilogramosMB.class);

    @EJB
    private ConfiguracionBeanLocal configuracionBean;

    private List<Kilogramo> listKilogramos;
    private Kilogramo kilogramos;
    private String descripcion;
    private List<Kilogramo> listKilogramosFilter;

    @PostConstruct
    void cargarDatos() {
        List<Kilogramo> list = configuracionBean.ListKilogramos();
        if (list != null) {
            listKilogramos = list;
        } else {
            listKilogramos = new ArrayList<>();
        }
    }

    public void verRegistro() {
        kilogramos = null;
        kilogramos = new Kilogramo();

        RequestContext.getCurrentInstance().execute("PF('dlgRegistro').show()");
    }

    public void buscarFiltro() {
        try {
            if (descripcion != null) {
                List<Kilogramo> response = configuracionBean.listKilogramoBuzon(descripcion);
                if (response != null) {
                    listKilogramos = response;
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

    public void verDetalle(Integer idKilogramo) {
        JsfUtil.redirectTo("/configuracion/kilogramo/detalle.xhtml?faces-redirect=true&idKilogramo=" + idKilogramo);
    }

    public void registroKilogramo() throws IOException {
        if (kilogramos.getDescripcion() == null) {
            JsfUtil.addErrorMessage("Debe registrar una descripción");
            return;
        }

        Kilogramo responseSave = configuracionBean.saveKilogramo(kilogramos, SesionUsuarioMB.getUserName());
        if (responseSave != null) {
            JsfUtil.addSuccessMessage("Kilogramo registrado exitosamente");
        } else {
            JsfUtil.addErrorMessage("Ocurrio un error verificar datos");
        }
        cargarDatos();
        RequestContext.getCurrentInstance().execute("PF('dlgRegistro').hide()");
    }

    public void eliminarKilo(Kilogramo id) throws IOException {
        Kilogramo response = configuracionBean.deleteKilogramo(id, SesionUsuarioMB.getUserName());
        if (response != null) {
            cargarDatos();
            JsfUtil.addSuccessMessage("Se elimino el kilogramo exitosamente");
            return;
        }

        JsfUtil.addErrorMessage("Sucedio un error al eliminar");
    }
    
    public void onRowEditKilogramo(RowEditEvent event) throws IOException {
        Kilogramo objeto = ((Kilogramo) event.getObject());
        Kilogramo response = configuracionBean.updateKilogramo(objeto, SesionUsuarioMB.getUserName());
        if (response == null) {
            JsfUtil.addErrorMessage("Ocurrio un error al actualizar");
        } else {
            JsfUtil.addSuccessMessage("Kilogramo actualizado correctamente");
        }
    }
    
    /*Metodos getters y setters*/
    public List<Kilogramo> getListKilogramos() {
        return listKilogramos;
    }

    public void setListKilogramos(List<Kilogramo> listKilogramos) {
        this.listKilogramos = listKilogramos;
    }

    public Kilogramo getKilogramos() {
        return kilogramos;
    }

    public void setKilogramos(Kilogramo kilogramos) {
        this.kilogramos = kilogramos;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public List<Kilogramo> getListKilogramosFilter() {
        return listKilogramosFilter;
    }

    public void setListKilogramosFilter(List<Kilogramo> listKilogramosFilter) {
        this.listKilogramosFilter = listKilogramosFilter;
    }

}
