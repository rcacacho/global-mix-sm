package global.mix.sm.web.configuracion;

import global.mix.sm.api.ejb.ConfiguracionBeanLocal;
import global.mix.sm.api.entity.Kilogramo;
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
@ManagedBean(name = "detalleKilogramosMB")
@ViewScoped
public class DetalleKilogramosMB implements Serializable {

    private static final Logger log = Logger.getLogger(DetalleKilogramosMB.class);

    @EJB
    private ConfiguracionBeanLocal configuracionBean;

    private Integer idKilogramo;
    private Kilogramo kilogramos;

    public void cargarDatos() {
        Kilogramo response = configuracionBean.findKilogramoById(idKilogramo);
        if (response != null) {
            kilogramos = response;
        } else {
            JsfUtil.addErrorMessage("No se encontro información");
        }
    }

    public void verLista() {
        JsfUtil.redirectTo("/configuracion/kilogramo/lista.xhtml?faces-redirect=true");
    }

    /*Metodos getters y setters*/
    public Kilogramo getKilogramos() {
        return kilogramos;
    }

    public void setKilogramos(Kilogramo kilogramos) {
        this.kilogramos = kilogramos;
    }

    public Integer getIdKilogramo() {
        return idKilogramo;
    }

    public void setIdKilogramo(Integer idKilogramo) {
        this.idKilogramo = idKilogramo;
    }

}
