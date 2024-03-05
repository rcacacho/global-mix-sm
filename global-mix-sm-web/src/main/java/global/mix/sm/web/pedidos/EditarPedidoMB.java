package global.mix.sm.web.pedidos;

import global.mix.sm.api.ejb.CatalogosBeanLocal;
import global.mix.sm.api.ejb.PedidosBeanLocal;
import global.mix.sm.api.entity.Asesor;
import global.mix.sm.api.entity.Cliente;
import global.mix.sm.api.entity.Pedidos;
import global.mix.sm.api.entity.Tipopago;
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

/**
 *
 * @author rcacacho
 */
@ManagedBean(name = "editarPedidoMB")
@ViewScoped
public class EditarPedidoMB implements Serializable {

    private static final Logger log = Logger.getLogger(EditarPedidoMB.class);

    @EJB
    private PedidosBeanLocal pedidoBean;
    @EJB
    private CatalogosBeanLocal catalogoBean;

    private Integer idPedido;
    private Pedidos pedido;
    private List<Asesor> listAsesor;
    private List<Cliente> listClientes;
    private List<Tipopago> listTipoPago;
    private boolean mostrarColocado;

    @PostConstruct
    void init() {
        List<Asesor> list = catalogoBean.listAsesor();
        if (list != null) {
            listAsesor = list;
        } else {
            listAsesor = new ArrayList<>();
        }

        List<Cliente> listCon = catalogoBean.listCliente();
        if (listCon != null) {
            listClientes = listCon;
        } else {
            listClientes = new ArrayList<>();
        }

        List<Tipopago> listTip = catalogoBean.listTipoPago();
        if (listTip != null) {
            listTipoPago = listTip;
        } else {
            listTipoPago = new ArrayList<>();
        }
    }

    public void cargarDatos() {
        Pedidos response = pedidoBean.findPedidoById(idPedido);
        if (response != null) {
            pedido = response;
            cargarColocado();
        } else {
            JsfUtil.addErrorMessage("No se encontro información");
        }
    }

    public void actualizarProgramacion() throws IOException {
        Pedidos actualizacion = pedidoBean.updatePedido(pedido, SesionUsuarioMB.getUserName());
        if (actualizacion != null) {
            JsfUtil.addSuccessMessage("Programación actualizada correctamente");
        }
    }

    public void cargarColocado() {
        if (pedido.getColocado() != null) {
            if (pedido.getColocado().equals("Si")) {
                mostrarColocado = Boolean.TRUE;
            } else {
                mostrarColocado = Boolean.FALSE;
            }
        }
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

    public List<Asesor> getListAsesor() {
        return listAsesor;
    }

    public void setListAsesor(List<Asesor> listAsesor) {
        this.listAsesor = listAsesor;
    }

    public List<Cliente> getListClientes() {
        return listClientes;
    }

    public void setListClientes(List<Cliente> listClientes) {
        this.listClientes = listClientes;
    }

    public List<Tipopago> getListTipoPago() {
        return listTipoPago;
    }

    public void setListTipoPago(List<Tipopago> listTipoPago) {
        this.listTipoPago = listTipoPago;
    }

    public boolean isMostrarColocado() {
        return mostrarColocado;
    }

    public void setMostrarColocado(boolean mostrarColocado) {
        this.mostrarColocado = mostrarColocado;
    }

}
