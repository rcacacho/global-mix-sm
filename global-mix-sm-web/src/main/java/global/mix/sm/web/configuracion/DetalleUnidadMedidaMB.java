package global.mix.sm.web.configuracion;

import global.mix.sm.api.ejb.ConfiguracionBeanLocal;
import global.mix.sm.api.entity.Unidadmedida;
import global.mix.sm.web.utils.JsfUtil;
import java.io.Serializable;
import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import org.apache.log4j.Logger;

/**
 *
 * @author rcacacho
 */
@ManagedBean(name = "detalleUnidadMedidaMB")
@ViewScoped
public class DetalleUnidadMedidaMB implements Serializable {

    private static final Logger log = Logger.getLogger(DetalleUnidadMedidaMB.class);

    @EJB
    private ConfiguracionBeanLocal configuracionBean;

    private Integer idUnidadMedida;
    private Unidadmedida unidadMedida;

    public void cargarDatos() {
        Unidadmedida response = configuracionBean.findUnidadMedidaById(idUnidadMedida);
        if (response != null) {
            unidadMedida = response;
        } else {
            JsfUtil.addErrorMessage("No se encontro información");
        }
    }

    public void verLista() {
        JsfUtil.redirectTo("/configuracion/unidadmedida/lista.xhtml?faces-redirect=true");
    }

    /*Metodos getters y setters*/
    public Integer getIdUnidadMedida() {
        return idUnidadMedida;
    }

    public void setIdUnidadMedida(Integer idUnidadMedida) {
        this.idUnidadMedida = idUnidadMedida;
    }

    public Unidadmedida getUnidadMedida() {
        return unidadMedida;
    }

    public void setUnidadMedida(Unidadmedida unidadMedida) {
        this.unidadMedida = unidadMedida;
    }

}
