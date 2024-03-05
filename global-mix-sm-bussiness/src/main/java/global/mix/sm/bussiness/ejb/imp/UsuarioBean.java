package global.mix.sm.bussiness.ejb.imp;

import global.mix.sm.api.ejb.UsuarioBeanLocal;
import global.mix.sm.api.entity.Usuario;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import javax.annotation.Resource;
import javax.ejb.EJBContext;
import javax.ejb.Singleton;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import javax.validation.ConstraintViolation;
import javax.validation.ConstraintViolationException;
import org.apache.log4j.Logger;

/**
 *
 * @author rcacacho
 */
@Singleton
public class UsuarioBean implements UsuarioBeanLocal {

    private static final Logger log = Logger.getLogger(UsuarioBean.class);

    @PersistenceContext(unitName = "GlobalMixSmPU")
    EntityManager em;

    @Resource
    private EJBContext context;

    private void processException(Exception ex) {
        log.error(ex.getMessage(), ex);
    }

    private String getConstraintViolationExceptionAsString(ConstraintViolationException ex) {
        StringBuilder sb = new StringBuilder();
        sb.append("Error de validación:\n");
        for (ConstraintViolation c : ex.getConstraintViolations()) {
            sb.append(String.format("[bean: %s; field: %s; message: %s; value: %s]",
                    c.getRootBeanClass().getName(),
                    c.getPropertyPath().toString(),
                    c.getMessage(), c.getInvalidValue()));
        }
        return sb.toString();
    }

    @Override
    public List<Usuario> listaUsuarios() {
        List<Usuario> lst = em.createQuery("SELECT qj FROM Usuario qj where qj.activo = true ", Usuario.class)
                .getResultList();

        if (lst == null || lst.isEmpty()) {
            return null;
        }
        return lst;
    }

    @Override
    public Usuario saveUsuario(Usuario usuario, String usuarioCreacion) {
        try {
            usuario.setUsuariocreacion(usuarioCreacion);
            usuario.setFechacreacion(new Date());
            usuario.setActivo(true);
            em.persist(usuario);
            em.flush();
            return (usuario);
        } catch (ConstraintViolationException ex) {
            String validationError = getConstraintViolationExceptionAsString(ex);
            log.error(validationError);
            context.setRollbackOnly();
            return null;
        } catch (Exception ex) {
            processException(ex);
            context.setRollbackOnly();
            return null;
        }
    }

    @Override
    public Usuario findUsuario(Integer idusuario) {
        List<Usuario> lst = em.createQuery("SELECT usu FROM Usuario usu WHERE usu.idusuario =:idusuario ", Usuario.class)
                .setParameter("idusuario", idusuario)
                .getResultList();

        if (lst == null || lst.isEmpty()) {
            return null;
        }

        return lst.get(0);
    }

    @Override
    public Usuario findUsuario(String usuario) {
        List<Usuario> lst = em.createQuery("SELECT usu FROM Usuario usu WHERE usu.usuario =:usuario ", Usuario.class)
                .setParameter("usuario", usuario)
                .getResultList();

        if (lst == null || lst.isEmpty()) {
            return null;
        }

        return lst.get(0);
    }

    @Override
    public Usuario reinicioPassword(Usuario usuario, String usuarioModificacion) {
        if (usuario == null) {
            context.setRollbackOnly();
            return null;
        }

        try {
            Usuario toUpdate = em.find(Usuario.class, usuario.getIdusuario());

            toUpdate.setPassword(usuario.getPassword());
            toUpdate.setUsuariomodificacion(usuario.getUsuariomodificacion());
            toUpdate.setFechamodificacion(new Date());
            toUpdate.setUsuariomodificacion(usuarioModificacion);
            em.merge(toUpdate);

            return toUpdate;
        } catch (ConstraintViolationException ex) {
            String validationError = getConstraintViolationExceptionAsString(ex);
            log.error(validationError);
            context.setRollbackOnly();
            return null;
        } catch (Exception ex) {
            processException(ex);
            return null;
        }
    }

    @Override
    public List<Usuario> listaUsuarioBuzon(String usuario) {
        CriteriaBuilder cb = em.getCriteriaBuilder();
        CriteriaQuery<Usuario> query = cb.createQuery(Usuario.class);
        Root<Usuario> orden = query.from(Usuario.class);

        List<Predicate> lstPredicates = new ArrayList<>();
        Predicate activo = cb.equal(orden.<Integer>get("activo"), 1);
        lstPredicates.add(activo);

        if (usuario != null) {
            Predicate predicate = cb.like(orden.<String>get("usuario"), "%" + usuario + "%");
            lstPredicates.add(predicate);
        }

        query.orderBy(cb.asc(orden.get("fechacreacion")));

        query.where(cb.and(lstPredicates.toArray(new Predicate[lstPredicates.size()])));
        List<Usuario> lst;
        lst = em.createQuery(query).getResultList();
        if (lst == null || lst.isEmpty()) {
            return null;
        }

        return lst;
    }

    @Override
    public Usuario deleteUsuario(Usuario usuario, String usuarioEliminacion) {
        try {
            usuario.setFechaeliminacion(new Date());
            usuario.setUsuarioeliminacion(usuarioEliminacion);
            usuario.setActivo(false);
            em.merge(usuario);
            em.flush();
            return (usuario);
        } catch (ConstraintViolationException ex) {
            String validationError = getConstraintViolationExceptionAsString(ex);
            log.error(validationError);
            context.setRollbackOnly();
            return null;
        } catch (Exception ex) {
            processException(ex);
            context.setRollbackOnly();
            return null;
        }
    }

    @Override
    public Usuario updateUsuario(Usuario usuario, String usuarioModificacion) {
            if (usuario == null) {
            context.setRollbackOnly();
            return null;
        }

        try {
            Usuario toUpdate = em.find(Usuario.class, usuario.getIdusuario());

            toUpdate.setNombres(usuario.getNombres());
            toUpdate.setApellidos(usuario.getApellidos());
            toUpdate.setUsuariomodificacion(usuario.getUsuariomodificacion());
            toUpdate.setFechamodificacion(new Date());
            toUpdate.setUsuariomodificacion(usuarioModificacion);
            em.merge(toUpdate);

            return toUpdate;
        } catch (ConstraintViolationException ex) {
            String validationError = getConstraintViolationExceptionAsString(ex);
            log.error(validationError);
            context.setRollbackOnly();
            return null;
        } catch (Exception ex) {
            processException(ex);
            return null;
        }
    }

}
