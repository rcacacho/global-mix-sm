package global.mix.sm.web.camion;

import global.mix.sm.api.ejb.CamionesBeanLocal;
import global.mix.sm.api.entity.Camion;
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
@ManagedBean(name = "detalleCamionMB")
@ViewScoped
public class DetalleCamionMB implements Serializable {
    
      private static final Logger log = Logger.getLogger(DetalleCamionMB.class);

    @EJB
    private CamionesBeanLocal camionBean;

    private Integer idCamion;
    private Camion camion;

    public void cargarDatos() {
        Camion response = camionBean.findCamionById(idCamion);
        if (response != null) {
            camion = response;
        } else {
            JsfUtil.addErrorMessage("No se encontro información");
        }
    }
    
    /*Metodos getters y setters*/
    public void verLista() {
        JsfUtil.redirectTo("/camion/lista.xhtml?faces-redirect=true");
    }

    public Integer getIdCamion() {
        return idCamion;
    }

    public void setIdCamion(Integer idCamion) {
        this.idCamion = idCamion;
    }

    public Camion getCamion() {
        return camion;
    }

    public void setCamion(Camion camion) {
        this.camion = camion;
    }
    
    
    
}
