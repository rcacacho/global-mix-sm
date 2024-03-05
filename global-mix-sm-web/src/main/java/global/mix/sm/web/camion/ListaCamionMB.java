package global.mix.sm.web.camion;

import global.mix.sm.api.ejb.CamionesBeanLocal;
import global.mix.sm.api.entity.Camion;
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
@ManagedBean(name = "listaCamionMB")
@ViewScoped
public class ListaCamionMB implements Serializable {

    private static final Logger log = Logger.getLogger(ListaCamionMB.class);

    @EJB
    private CamionesBeanLocal camionBean;

    private List<Camion> listCamion;
    private Integer numero;
    private String descripcion;
    private List<Camion> listCamionFiltrado;
    private Camion camion;

    @PostConstruct
    void cargarDatos() {
        List<Camion> list = camionBean.ListCamiones();
        if (list != null) {
            listCamion = list;
        } else {
            listCamion = new ArrayList<>();
        }
    }

    public void verDetalle(Integer idCamion) {
        JsfUtil.redirectTo("/camnion/detalle.xhtml?faces-redirect=true&idCamion=" + idCamion);
    }

    public void verRegistro() {
        camion = null;
        camion = new Camion();

        RequestContext.getCurrentInstance().execute("PF('dlgRegistro').show()");
    }

    public void limpiarCampos() {
        numero = null;
        descripcion = null;

        cargarDatos();
    }

    public void buscarFiltro() {
        try {
            if (numero != null || descripcion != null) {
                List<Camion> response = camionBean.ListCamionesBuzon(descripcion, numero);
                if (response != null) {
                    listCamion = response;
                }
            }

        } catch (Exception ex) {
            log.error(ex.getMessage());
        }
    }

    public void registroCamion() throws IOException {
        Camion responseSave = camionBean.saveCamion(camion, SesionUsuarioMB.getUserName());
        if (responseSave != null) {
            JsfUtil.addSuccessMessage("Camión creado exitosamente");
            cargarDatos();
            RequestContext.getCurrentInstance().execute("PF('dlgRegistro').hide()");
        } else {
            JsfUtil.addErrorMessage("Ocurrio un error verificar datos");
        }
    }

    public void eliminarCamion(Camion id) throws IOException {
        Camion response = camionBean.deleteCamion(id, SesionUsuarioMB.getUserName());
        if (response != null) {
            cargarDatos();
            JsfUtil.addSuccessMessage("Se elimino el camión exitosamente");
            return;
        }

        JsfUtil.addErrorMessage("Sucedio un error al eliminar");
    }

    public void onRowEditCamion(RowEditEvent event) throws IOException {
        Camion objeto = ((Camion) event.getObject());
        Camion response = camionBean.updateCamion(objeto, SesionUsuarioMB.getUserName());
        if (response == null) {
            JsfUtil.addErrorMessage("Ocurrio un error al actualizar");
        } else {
            JsfUtil.addSuccessMessage("Camión actualizado correctamente");
        }
    }

    /*Metodos getters y setters*/
    public List<Camion> getListCamion() {
        return listCamion;
    }

    public void setListCamion(List<Camion> listCamion) {
        this.listCamion = listCamion;
    }

    public Integer getNumero() {
        return numero;
    }

    public void setNumero(Integer numero) {
        this.numero = numero;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public List<Camion> getListCamionFiltrado() {
        return listCamionFiltrado;
    }

    public void setListCamionFiltrado(List<Camion> listCamionFiltrado) {
        this.listCamionFiltrado = listCamionFiltrado;
    }

    public Camion getCamion() {
        return camion;
    }

    public void setCamion(Camion camion) {
        this.camion = camion;
    }

}
