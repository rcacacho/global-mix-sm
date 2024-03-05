package global.mix.sm.web.usuarios;

import global.mix.sm.api.ejb.CatalogosBeanLocal;
import global.mix.sm.api.ejb.UsuarioBeanLocal;
import global.mix.sm.api.entity.Tipousuario;
import global.mix.sm.api.entity.Usuario;
import global.mix.sm.web.utils.JsfUtil;
import global.mix.sm.web.utils.SesionUsuarioMB;
import java.io.IOException;
import java.io.Serializable;
import java.math.BigInteger;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.ArrayList;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import org.apache.log4j.Logger;
import org.primefaces.context.RequestContext;
import org.primefaces.event.RowEditEvent;

/**
 *
 * @author rcacacho
 */
@ManagedBean(name = "listaUsuariosMB")
@ViewScoped
public class ListaUsuariosMB implements Serializable {

    private static final Logger log = Logger.getLogger(ListaUsuariosMB.class);

    @EJB
    private UsuarioBeanLocal usuarioBean;
    @EJB
    private CatalogosBeanLocal catalogoBean;

    private List<Usuario> listUsuarios;
    private Usuario usuario;
    private String nombreUsuario;
    private List<Usuario> listUsuarioFilter;
    private Usuario selectedUsuario;
    private List<Tipousuario> listTipoUsuario;

    @PostConstruct
    void cargarDatos() {
        List<Usuario> list = usuarioBean.listaUsuarios();
        if (list != null) {
            listUsuarios = list;
        } else {
            listUsuarios = new ArrayList<>();
        }

        List<Tipousuario> listTipo = catalogoBean.listTipoUsuario();
        if (list != null) {
            listTipoUsuario = listTipo;
        } else {
            listTipoUsuario = new ArrayList<>();
        }
    }

    public void verRegistro() {
        usuario = null;
        usuario = new Usuario();

        RequestContext.getCurrentInstance().execute("PF('dlgRegistro').show()");
    }

    public void buscarFiltro() {
        try {
            if (nombreUsuario != null) {
                List<Usuario> response = usuarioBean.listaUsuarioBuzon(nombreUsuario);
                if (response != null) {
                    listUsuarios = response;
                }
            }

        } catch (Exception ex) {
            log.error(ex.getMessage());
        }
    }

    public void limpiarCampos() {
        nombreUsuario = null;
        cargarDatos();
    }

    public void verDetalle(Integer idUsuario) {
        JsfUtil.redirectTo("/usuario/detalle.xhtml?faces-redirect=true&idUsuario=" + idUsuario);
    }

    public void registroUsuario() throws IOException {
        if (usuario.getUsuario() == null) {
            JsfUtil.addErrorMessage("Debe registrar un usuario");
            return;
        }

        Usuario responseVerificacion = usuarioBean.findUsuario(usuario.getUsuario());
        if (responseVerificacion != null) {
            JsfUtil.addErrorMessage("El usuario ya existe verifique");
            return;
        }

        usuario.setPassword(md5(usuario.getPassword()));
        Usuario responseSave = usuarioBean.saveUsuario(usuario, SesionUsuarioMB.getUserName());
        if (responseSave != null) {
            JsfUtil.addSuccessMessage("Usuario registrado exitosamente");
        } else {
            JsfUtil.addErrorMessage("Ocurrio un error verificar datos");
        }
        cargarDatos();
        RequestContext.getCurrentInstance().execute("PF('dlgRegistro').hide()");
    }

    public void eliminarUsuario(Usuario id) throws IOException {
        Usuario response = usuarioBean.deleteUsuario(id, SesionUsuarioMB.getUserName());
        if (response != null) {
            cargarDatos();
            JsfUtil.addSuccessMessage("Se elimino el usuario exitosamente");
            return;
        }

        JsfUtil.addErrorMessage("Sucedio un error al eliminar");
    }

    public void reinicioPassword(Usuario usu) {
        selectedUsuario = usu;
        RequestContext.getCurrentInstance().execute("PF('dlgReinicio').show()");
    }

    public void reinicioUsuario() throws IOException {
        String contra = md5(selectedUsuario.getPassword());
        selectedUsuario.setPassword(contra);
        Usuario responseVerificacion = usuarioBean.reinicioPassword(selectedUsuario, SesionUsuarioMB.getUserName());
        if (responseVerificacion != null) {
            JsfUtil.addSuccessMessage("Se reinicio la contraseña exitosamente");
            selectedUsuario = null;
            return;
        }
    }

    public String md5(String input) {
        try {
            MessageDigest md = MessageDigest.getInstance("MD5");
            byte[] messageDigest = md.digest(input.getBytes());
            BigInteger number = new BigInteger(1, messageDigest);
            String hashtext = number.toString(16);

            while (hashtext.length() < 32) {
                hashtext = "0" + hashtext;
            }
            return hashtext;
        } catch (NoSuchAlgorithmException e) {
            throw new RuntimeException(e);
        }
    }

    public void onRowEditUsuario(RowEditEvent event) throws IOException {
        Usuario objeto = ((Usuario) event.getObject());
        Usuario response = usuarioBean.updateUsuario(objeto, SesionUsuarioMB.getUserName());
        if (response == null) {
            JsfUtil.addErrorMessage("Ocurrio un error al actualizar");
        } else {
            JsfUtil.addSuccessMessage("Usuario actualizado correctamente");
        }
    }

    /*Metodos getters y setters*/
    public List<Usuario> getListUsuarios() {
        return listUsuarios;
    }

    public void setListUsuarios(List<Usuario> listUsuarios) {
        this.listUsuarios = listUsuarios;
    }

    public Usuario getUsuario() {
        return usuario;
    }

    public void setUsuario(Usuario usuario) {
        this.usuario = usuario;
    }

    public String getNombreUsuario() {
        return nombreUsuario;
    }

    public void setNombreUsuario(String nombreUsuario) {
        this.nombreUsuario = nombreUsuario;
    }

    public List<Usuario> getListUsuarioFilter() {
        return listUsuarioFilter;
    }

    public void setListUsuarioFilter(List<Usuario> listUsuarioFilter) {
        this.listUsuarioFilter = listUsuarioFilter;
    }

    public Usuario getSelectedUsuario() {
        return selectedUsuario;
    }

    public void setSelectedUsuario(Usuario selectedUsuario) {
        this.selectedUsuario = selectedUsuario;
    }

    public List<Tipousuario> getListTipoUsuario() {
        return listTipoUsuario;
    }

    public void setListTipoUsuario(List<Tipousuario> listTipoUsuario) {
        this.listTipoUsuario = listTipoUsuario;
    }

}
