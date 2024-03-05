package global.mix.sm.bussiness.ejb.imp;

import global.mix.sm.api.ejb.LoginBeanLocal;
import global.mix.sm.api.entity.Usuario;
import java.util.List;
import javax.annotation.Resource;
import javax.ejb.EJBContext;
import javax.ejb.Singleton;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import org.apache.log4j.Logger;

/**
 *
 * @author rcacacho
 */
@Singleton
public class LoginBean implements LoginBeanLocal {

    private static final Logger log = Logger.getLogger(LoginBean.class);

    @PersistenceContext(unitName = "GlobalMixSmPU")
    EntityManager em;

    @Resource
    private EJBContext context;

    @Override
    public Usuario verificarUsuario(String usuario, String password) {
        List<Usuario> lst = em.createQuery("SELECT us FROM Usuario us WHERE us.usuario =:usuario and us.password =:password and us.activo = true ", Usuario.class)
                .setParameter("usuario", usuario)
                .setParameter("password", password)
                .getResultList();

        if (lst == null || lst.isEmpty()) {
            return null;
        }

        return lst.get(0);
    }

    @Override
    public String findUsuario(String usuario) {
        List<Usuario> lst = em.createQuery("SELECT us FROM Usuario us WHERE us.usuario =:usuario ", Usuario.class)
                .setParameter("usuario", usuario)
                .getResultList();

        if (lst == null || lst.isEmpty()) {
            return null;
        }
        return lst.get(0).getUsuario();
    }

}
