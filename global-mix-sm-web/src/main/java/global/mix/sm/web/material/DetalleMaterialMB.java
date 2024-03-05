package global.mix.sm.web.material;

import global.mix.sm.api.ejb.MaterialBeanLocal;
import global.mix.sm.api.entity.Detallematerial;
import global.mix.sm.api.entity.Material;
import global.mix.sm.web.utils.JsfUtil;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import org.apache.log4j.Logger;

/**
 *
 * @author rcacacho
 */
@ManagedBean(name = "detalleMaterialMB")
@ViewScoped
public class DetalleMaterialMB implements Serializable {

    private static final Logger log = Logger.getLogger(DetalleMaterialMB.class);

    @EJB
    private MaterialBeanLocal materialBean;

    private Integer idMaterial;
    private Material material;
    private List<Detallematerial> listDetalle;
    private List<Detallematerial> listDetalleFilter;

    public void cargarDatos() {
        Material response = materialBean.findMaterialById(idMaterial);
        if (response != null) {
            material = response;
        } else {
            JsfUtil.addErrorMessage("No se encontro información");
        }

        List<Detallematerial> responseDetalle = materialBean.listDetalleMaterial(idMaterial);
        if (responseDetalle != null) {
            listDetalle = responseDetalle;
        } else {
            listDetalle = new ArrayList<>();
        }
    }

    public void verLista() {
        JsfUtil.redirectTo("/material/lista.xhtml?faces-redirect=true");
    }

    public int getTotalIngresos() {
        int total = 0;

        if (listDetalle != null) {
            for (Detallematerial det : listDetalle) {
                if (det.getIngreso() != null) {
                    if (det.getActivo() == true) {
                        total += det.getIngreso();
                    }
                }
            }
        }

        return total;
    }
    
    public int getTotalEgresos() {
        int total = 0;

        if (listDetalle != null) {
            for (Detallematerial det : listDetalle) {
                if (det.getEgreso() != null) {
                    if (det.getActivo() == true) {
                        total += det.getEgreso();
                    }
                }
            }
        }

        return total;
    }
    
    public int getTotal() {
        int total = 0;

        if (listDetalle != null) {
            for (Detallematerial det : listDetalle) {
                if (det.getTotal() != null) {
                    if (det.getActivo() == true) {
                        total += det.getTotal();
                    }
                }
            }
        }

        return total;
    }

    /*Metodos getters y setters*/
    public Integer getIdMaterial() {
        return idMaterial;
    }

    public void setIdMaterial(Integer idMaterial) {
        this.idMaterial = idMaterial;
    }

    public Material getMaterial() {
        return material;
    }

    public void setMaterial(Material material) {
        this.material = material;
    }

    public List<Detallematerial> getListDetalle() {
        return listDetalle;
    }

    public void setListDetalle(List<Detallematerial> listDetalle) {
        this.listDetalle = listDetalle;
    }

    public List<Detallematerial> getListDetalleFilter() {
        return listDetalleFilter;
    }

    public void setListDetalleFilter(List<Detallematerial> listDetalleFilter) {
        this.listDetalleFilter = listDetalleFilter;
    }

}
