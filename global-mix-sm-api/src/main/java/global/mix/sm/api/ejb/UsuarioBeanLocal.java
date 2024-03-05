package global.mix.sm.api.ejb;

import global.mix.sm.api.entity.Usuario;
import java.util.List;
import javax.ejb.Local;

/**
 *
 * @author rcacacho
 */
@Local
public interface UsuarioBeanLocal {

    List<Usuario> listaUsuarios();

    Usuario saveUsuario(Usuario usuario, String usuarioCreacion);

    Usuario findUsuario(Integer idusuario);

    Usuario findUsuario(String usuario);

    Usuario reinicioPassword(Usuario usuario, String usuarioModificacion);

    List<Usuario> listaUsuarioBuzon(String usuario);

    Usuario deleteUsuario(Usuario usuario, String usuarioEliminacion);

    Usuario updateUsuario(Usuario usuario, String usuarioModificacion);

}
