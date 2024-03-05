package global.mix.sm.web.configuracion;

import global.mix.sm.api.ejb.ConfiguracionBeanLocal;
import global.mix.sm.api.entity.Diesel;
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
@ManagedBean(name = "detalleDieselMB")
@ViewScoped
public class DetalleDieselMB implements Serializable {

    private static final Logger log = Logger.getLogger(DetalleDieselMB.class);

    @EJB
    private ConfiguracionBeanLocal configuracionBean;

    private Integer idDiesel;
    private Diesel diesel;

    public void cargarDatos() {
        Diesel response = configuracionBean.findDieselById(idDiesel);
        if (response != null) {
            diesel = response;
        } else {
            JsfUtil.addErrorMessage("No se encontro información");
        }
    }

    public void verLista() {
        JsfUtil.redirectTo("/configuracion/diesel/lista.xhtml?faces-redirect=true");
    }

    /*Metodos getters y setters*/
    public Integer getIdDiesel() {
        return idDiesel;
    }

    public void setIdDiesel(Integer idDiesel) {
        this.idDiesel = idDiesel;
    }

    public Diesel getDiesel() {
        return diesel;
    }

    public void setDiesel(Diesel diesel) {
        this.diesel = diesel;
    }

}
