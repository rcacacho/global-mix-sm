package global.mix.sm.web.despacho;

import global.mix.sm.api.ejb.DespachosBeanLocal;
import global.mix.sm.api.entity.Despachos;
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
@ManagedBean(name = "detalleDespachoMB")
@ViewScoped
public class DetalleDespachoMB implements Serializable {

    private static final Logger log = Logger.getLogger(DetalleDespachoMB.class);

    @EJB
    private DespachosBeanLocal despachoBean;

    private Integer idDespacho;
    private Despachos despacho;

    public void cargarDatos() {
        Despachos response = despachoBean.findDespachoById(idDespacho);
        if (response != null) {
            despacho = response;
        } else {
            JsfUtil.addErrorMessage("No se encontro información");
        }
    }

    /*Metodos getters y setters*/
    public Integer getIdDespacho() {
        return idDespacho;
    }

    public void setIdDespacho(Integer idDespacho) {
        this.idDespacho = idDespacho;
    }

    public Despachos getDespacho() {
        return despacho;
    }

    public void setDespacho(Despachos despacho) {
        this.despacho = despacho;
    }

}
