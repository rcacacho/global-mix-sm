package global.mix.sm.web.cliente;

import global.mix.sm.api.ejb.ClienteBeanLocal;
import global.mix.sm.api.entity.Cliente;
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
@ManagedBean(name = "detalleClientesMB")
@ViewScoped
public class DetalleClientesMB implements Serializable {

    private static final Logger log = Logger.getLogger(DetalleClientesMB.class);

    @EJB
    private ClienteBeanLocal clienteBean;

    private Integer idCliente;
    private Cliente cliente;

    public void cargarDatos() {
        Cliente response = clienteBean.findCliente(idCliente);
        if (response != null) {
            cliente = response;
        } else {
            JsfUtil.addErrorMessage("No se encontro información");
        }
    }

    public void verLista() {
        JsfUtil.redirectTo("/cliente/lista.xhtml?faces-redirect=true");
    }

    /*Metodos getters y setters*/
    public Integer getIdCliente() {
        return idCliente;
    }

    public void setIdCliente(Integer idCliente) {
        this.idCliente = idCliente;
    }

    public Cliente getCliente() {
        return cliente;
    }

    public void setCliente(Cliente cliente) {
        this.cliente = cliente;
    }

}
