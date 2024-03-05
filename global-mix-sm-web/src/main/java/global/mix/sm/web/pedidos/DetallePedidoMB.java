package global.mix.sm.web.pedidos;

import global.mix.sm.api.ejb.PedidosBeanLocal;
import global.mix.sm.api.entity.Detallepedidonormal;
import global.mix.sm.api.entity.Pedidos;
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
@ManagedBean(name = "detallePedidoMB")
@ViewScoped
public class DetallePedidoMB implements Serializable {

    private static final Logger log = Logger.getLogger(RegistroPedidoMB.class);

    @EJB
    private PedidosBeanLocal pedidoBean;

    private Integer idPedido;
    private Pedidos pedido;
    private List<Detallepedidonormal> listDetallePedido;
    private List<Detallepedidonormal> listDetallePedidoFilter;

    public void cargarDatos() {
        Pedidos response = pedidoBean.findPedidoById(idPedido);
        if (response != null) {
            pedido = response;
        } else {
            JsfUtil.addErrorMessage("No se encontro información");
        }

        List<Detallepedidonormal> responseDet = pedidoBean.listDetallePedidoNormalByIdPedido(idPedido);
        if (responseDet != null) {
            listDetallePedido = responseDet;
        } else {
            listDetallePedido = new ArrayList<>();
        }
    }

    public int getTotalCemento() {
        int total = 0;

        if (listDetallePedido != null) {
            for (Detallepedidonormal det : listDetallePedido) {
                if (det.getCantidadcemento() != null) {
                    if (det.getActivo() == true) {
                        total += det.getCantidadcemento();
                    }
                }
            }
        }
        return total;
    }

    public int getTotalArena() {
        int total = 0;

        if (listDetallePedido != null) {
            for (Detallepedidonormal det : listDetallePedido) {
                if (det.getCantidadarena() != null) {
                    if (det.getActivo() == true) {
                        total += det.getCantidadarena();
                    }
                }
            }
        }
        return total;
    }

    public int getTotalPiedrin() {
        int total = 0;

        if (listDetallePedido != null) {
            for (Detallepedidonormal det : listDetallePedido) {
                if (det.getCantidadpiedrin() != null) {
                    if (det.getActivo() == true) {
                        total += det.getCantidadpiedrin();
                    }
                }
            }
        }
        return total;
    }

    public int getTotalAditivo() {
        int total = 0;

        if (listDetallePedido != null) {
            for (Detallepedidonormal det : listDetallePedido) {
                if (det.getCantidadaditivo() != null) {
                    if (det.getActivo() == true) {
                        total += det.getCantidadaditivo();
                    }
                }
            }
        }
        return total;
    }

    public int getTotalAgua() {
        int total = 0;

        if (listDetallePedido != null) {
            for (Detallepedidonormal det : listDetallePedido) {
                if (det.getCantidadagua() != null) {
                    if (det.getActivo() == true) {
                        total += det.getCantidadagua();
                    }
                }
            }
        }
        return total;
    }

    public int getTotalOtroMaterial() {
        int total = 0;

        if (listDetallePedido != null) {
            for (Detallepedidonormal det : listDetallePedido) {
                if (det.getCantidadotromaterial() != null) {
                    if (det.getActivo() == true) {
                        total += det.getCantidadotromaterial();
                    }
                }
            }
        }
        return total;
    }

    /*Metodos getters y setters*/
    public Integer getIdPedido() {
        return idPedido;
    }

    public void setIdPedido(Integer idPedido) {
        this.idPedido = idPedido;
    }

    public Pedidos getPedido() {
        return pedido;
    }

    public void setPedido(Pedidos pedido) {
        this.pedido = pedido;
    }

    public List<Detallepedidonormal> getListDetallePedido() {
        return listDetallePedido;
    }

    public void setListDetallePedido(List<Detallepedidonormal> listDetallePedido) {
        this.listDetallePedido = listDetallePedido;
    }

    public List<Detallepedidonormal> getListDetallePedidoFilter() {
        return listDetallePedidoFilter;
    }

    public void setListDetallePedidoFilter(List<Detallepedidonormal> listDetallePedidoFilter) {
        this.listDetallePedidoFilter = listDetallePedidoFilter;
    }

}
