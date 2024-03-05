package global.mix.sm.api.ejb;

import global.mix.sm.api.entity.Usuario;
import javax.ejb.Local;

/**
 *
 * @author rcacacho
 */
@Local
public interface LoginBeanLocal {

    Usuario verificarUsuario(String usuario, String password);

    String findUsuario(String usuario);

}
