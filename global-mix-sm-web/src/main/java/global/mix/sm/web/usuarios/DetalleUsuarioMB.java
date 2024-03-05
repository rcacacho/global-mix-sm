package global.mix.sm.web.usuarios;

import global.mix.sm.api.ejb.UsuarioBeanLocal;
import global.mix.sm.api.entity.Usuario;
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
@ManagedBean(name = "detalleUsuarioMB")
@ViewScoped
public class DetalleUsuarioMB implements Serializable {

    private static final Logger log = Logger.getLogger(DetalleUsuarioMB.class);

    @EJB
    private UsuarioBeanLocal usuarioBean;

    private Integer idUsuario;
    private Usuario usuario;

    public void cargarDatos() {
        Usuario response = usuarioBean.findUsuario(idUsuario);
        if (response != null) {
            usuario = response;
        } else {
            JsfUtil.addErrorMessage("No se encontro información");
        }
    }

    public void verLista() {
        JsfUtil.redirectTo("/usuario/lista.xhtml?faces-redirect=true");
    }

    /*Metodos getters y setters*/
    public Integer getIdUsuario() {
        return idUsuario;
    }

    public void setIdUsuario(Integer idUsuario) {
        this.idUsuario = idUsuario;
    }

    public Usuario getUsuario() {
        return usuario;
    }

    public void setUsuario(Usuario usuario) {
        this.usuario = usuario;
    }

}
