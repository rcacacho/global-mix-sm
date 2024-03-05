package global.mix.sm.web.asesor;

import global.mix.sm.api.ejb.CatalogosBeanLocal;
import global.mix.sm.api.entity.Asesor;
import java.io.Serializable;
import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import org.apache.log4j.Logger;

/**
 *
 * @author rcacacho
 */
@ManagedBean(name = "registroAsesorMB")
@ViewScoped
public class RegistroAsesorMB implements Serializable {

    private static final Logger log = Logger.getLogger(RegistroAsesorMB.class);

    @EJB
    private CatalogosBeanLocal catalogoBean;
  
    private String obra;
    private Asesor asesor;
//    private List<Configuracioncomision> listComision;
//
//    @PostConstruct
//    void cargarDatos() {
//        List<Asesor> list = asesorBean.ListAsesores();
//        if (list != null) {
//            listAsesor = list;
//        } else {
//            listAsesor = new ArrayList<>();
//        }
//
//        List<Configuracioncomision> listCon = configuracionBean.ListComisiones();
//        if (listCon != null) {
//            listComision = listCon;
//        } else {
//            listComision = new ArrayList<>();
//        }
//    }

}
