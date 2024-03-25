package global.mix.sm.web.pedidos;

import global.mix.sm.api.ejb.CatalogosBeanLocal;
import global.mix.sm.api.ejb.PedidosBeanLocal;
import global.mix.sm.api.entity.Asesor;
import global.mix.sm.api.entity.Cliente;
import global.mix.sm.api.entity.Estadopedido;
import global.mix.sm.api.entity.Pedidos;
import global.mix.sm.api.entity.Tipocemento;
import global.mix.sm.api.entity.Tipopago;
import global.mix.sm.api.enums.EstadoPedido;
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
import org.primefaces.context.RequestContext;

/**
 *
 * @author rcacacho
 */
@ManagedBean(name = "programacionPedidoMB")
@ViewScoped
public class ProgramacionPedidoMB implements Serializable {

 private static final Logger log = Logger.getLogger(ProgramacionPedidoMB.class);

    @EJB
    private PedidosBeanLocal pedidoBean;
    @EJB
    private CatalogosBeanLocal catalogoBean;

    private List<Asesor> listAsesor;
    private List<Cliente> listClientes;
    private List<Tipopago> listTipoPago;
    private Pedidos pedido;
    private boolean mostrarColocado;
    private List<Tipocemento> listTipoCemento;
    private Tipocemento tipoCemento;

    public ProgramacionPedidoMB() {
        pedido = new Pedidos();
    }

    @PostConstruct
    void cargarDatos() {
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

        List<Tipocemento> listCem = pedidoBean.listTipoCemento();
        if (listCem != null) {
            listTipoCemento = listCem;
        } else {
            listTipoCemento = new ArrayList<>();
        }
    }

    public void registroPedido() throws IOException {
        Estadopedido responseEstado = catalogoBean.findEstadoPedido(EstadoPedido.EN_PROCESO.getValue());
        if (responseEstado != null) {
            pedido.setIdestadopedido(responseEstado);
        }

        if (pedido.getIdtipopago().getIdtipopago().equals(2) || pedido.getIdtipopago().getIdtipopago().equals(3)) {
            pedido.setConfirmado(Boolean.FALSE);
        }

        if (mostrarColocado) {
            if (pedido.getCantidadcobradacolocado() == null) {
                JsfUtil.addErrorMessage("Debe registrar una cantidad cobrada en colocado");
                return;
            }
        }

        Pedidos responseSave = pedidoBean.savePedido(pedido, SesionUsuarioMB.getUserName());
        if (responseSave != null) {
            JsfUtil.addSuccessMessage("Programación de pedido registrado exitosamente");
        } else {
            JsfUtil.addErrorMessage("Ocurrio un error verificar datos");
        }
        pedido = null;
    }

    public void limpiarCampos() {
        JsfUtil.redirectTo("/pedidos/programacion.xhtml");
    }

    public void cargarColocado() {
        if (pedido.getColocado().equals("Si")) {
            mostrarColocado = Boolean.TRUE;
        } else {
            mostrarColocado = Boolean.FALSE;
        }
    }

    public void abrirTipoCemento() {
        tipoCemento = null;
        tipoCemento = new Tipocemento();
        RequestContext.getCurrentInstance().execute("PF('dlgRegistro').show()");
    }

    public void registrarTipoCemento() throws IOException {
        if (tipoCemento.getDescripcion() == null) {
            JsfUtil.addErrorMessage("Debe registrar una descripción");
            return;
        }

        Tipocemento response = pedidoBean.saveTipoCemento(tipoCemento, SesionUsuarioMB.getUserName());
        if (response != null) {
            JsfUtil.addSuccessMessage("Tipo de cemento creado exitosamente!");
            cargarDatos();
            RequestContext.getCurrentInstance().execute("PF('dlgRegistro').hide()");
        }
    }

    /*Metodos getters y setters*/
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

    public Pedidos getPedido() {
        return pedido;
    }

    public void setPedido(Pedidos pedido) {
        this.pedido = pedido;
    }

    public boolean isMostrarColocado() {
        return mostrarColocado;
    }

    public void setMostrarColocado(boolean mostrarColocado) {
        this.mostrarColocado = mostrarColocado;
    }

    public List<Tipocemento> getListTipoCemento() {
        return listTipoCemento;
    }

    public void setListTipoCemento(List<Tipocemento> listTipoCemento) {
        this.listTipoCemento = listTipoCemento;
    }

    public Tipocemento getTipoCemento() {
        return tipoCemento;
    }

    public void setTipoCemento(Tipocemento tipoCemento) {
        this.tipoCemento = tipoCemento;
    }

}
