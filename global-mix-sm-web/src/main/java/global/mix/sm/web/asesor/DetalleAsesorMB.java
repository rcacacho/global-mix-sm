package global.mix.sm.web.asesor;

import global.mix.sm.api.ejb.AsesoresBeanLocal;
import global.mix.sm.api.entity.Asesor;
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
@ManagedBean(name = "detalleAsesorMB")
@ViewScoped
public class DetalleAsesorMB implements Serializable {

    private static final Logger log = Logger.getLogger(DetalleAsesorMB.class);

    @EJB
    private AsesoresBeanLocal asesorBean;

    private Integer idAsesor;
    private Asesor asesor;

    public void cargarDatos() {
        Asesor response = asesorBean.findAsesorById(idAsesor);
        if (response != null) {
            asesor = response;
        } else {
            JsfUtil.addErrorMessage("No se encontro información");
        }
    }

    public void verLista() {
        JsfUtil.redirectTo("/asesor/lista.xhtml?faces-redirect=true");
    }

    /*Metodos getters y setters*/
    public Integer getIdAsesor() {
        return idAsesor;
    }

    public void setIdAsesor(Integer idAsesor) {
        this.idAsesor = idAsesor;
    }

    public Asesor getAsesor() {
        return asesor;
    }

    public void setAsesor(Asesor asesor) {
        this.asesor = asesor;
    }
    
    

}
