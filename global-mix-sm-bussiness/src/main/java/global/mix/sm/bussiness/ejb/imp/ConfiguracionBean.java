package global.mix.sm.bussiness.ejb.imp;

import global.mix.sm.api.ejb.ConfiguracionBeanLocal;
import global.mix.sm.api.entity.Diesel;
import global.mix.sm.api.entity.Kilogramo;
import global.mix.sm.api.entity.Unidadmedida;
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
public class ConfiguracionBean implements ConfiguracionBeanLocal {

    private static final Logger log = Logger.getLogger(ConfiguracionBean.class);

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
    public List<Kilogramo> ListKilogramos() {
        List<Kilogramo> lst = em.createQuery("SELECT qj FROM Kilogramo qj where qj.activo = true ", Kilogramo.class)
                .getResultList();

        if (lst == null || lst.isEmpty()) {
            return null;
        }

        return lst;
    }

    @Override
    public Kilogramo saveKilogramo(Kilogramo kilogramos, String usuarioCreacion) {
        try {
            kilogramos.setActivo(true);
            kilogramos.setFechacreacion(new Date());
            kilogramos.setUsuariocreacion(usuarioCreacion);
            em.persist(kilogramos);
            em.flush();
            return (kilogramos);
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
    public Kilogramo updateKilogramo(Kilogramo kilogramo, String usuarioModificacion) {
        try {
            kilogramo.setFechaeliminacion(new Date());
            kilogramo.setUsuarioeliminacion(usuarioModificacion);
            em.merge(kilogramo);
            em.flush();
            return (kilogramo);
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
    public List<Diesel> ListDiesel() {
        List<Diesel> lst = em.createQuery("SELECT qj FROM Diesel qj where qj.activo = true ", Diesel.class)
                .getResultList();

        if (lst == null || lst.isEmpty()) {
            return null;
        }

        return lst;
    }

    @Override
    public Diesel saveDiesel(Diesel diesel, String usuarioCreacion) {
        try {
            diesel.setActivo(true);
            diesel.setFechacreacion(new Date());
            diesel.setUsuariocreacion(usuarioCreacion);
            em.persist(diesel);
            em.flush();
            return (diesel);
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
    public Diesel updateDiesel(Diesel diesel, String usuarioModificacion) {
        try {
            diesel.setFechaeliminacion(new Date());
            diesel.setUsuarioeliminacion(usuarioModificacion);
            em.merge(diesel);
            em.flush();
            return (diesel);
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
    public List<Kilogramo> listKilogramoBuzon(String descripcion) {
        CriteriaBuilder cb = em.getCriteriaBuilder();
        CriteriaQuery<Kilogramo> query = cb.createQuery(Kilogramo.class);
        Root<Kilogramo> orden = query.from(Kilogramo.class);

        List<Predicate> lstPredicates = new ArrayList<>();
        Predicate activo = cb.equal(orden.<Integer>get("activo"), 1);
        lstPredicates.add(activo);

        if (descripcion != null) {
            Predicate predicate = cb.like(orden.<String>get("descripcion"), "%" + descripcion + "%");
            lstPredicates.add(predicate);
        }

        query.orderBy(cb.asc(orden.get("fechacreacion")));

        query.where(cb.and(lstPredicates.toArray(new Predicate[lstPredicates.size()])));
        List<Kilogramo> lst;
        lst = em.createQuery(query).getResultList();
        if (lst == null || lst.isEmpty()) {
            return null;
        }

        return lst;
    }

    @Override
    public List<Diesel> listDieselBuzon(String descripcion) {
        CriteriaBuilder cb = em.getCriteriaBuilder();
        CriteriaQuery<Diesel> query = cb.createQuery(Diesel.class);
        Root<Diesel> orden = query.from(Diesel.class);

        List<Predicate> lstPredicates = new ArrayList<>();
        Predicate activo = cb.equal(orden.<Integer>get("activo"), 1);
        lstPredicates.add(activo);

        if (descripcion != null) {
            Predicate predicate = cb.like(orden.<String>get("descripcion"), "%" + descripcion + "%");
            lstPredicates.add(predicate);
        }

        query.orderBy(cb.asc(orden.get("fechacreacion")));

        query.where(cb.and(lstPredicates.toArray(new Predicate[lstPredicates.size()])));
        List<Diesel> lst;
        lst = em.createQuery(query).getResultList();
        if (lst == null || lst.isEmpty()) {
            return null;
        }

        return lst;
    }

    @Override
    public Kilogramo deleteKilogramo(Kilogramo kilogramo, String usuarioEliminacion) {
        try {
            kilogramo.setFechaeliminacion(new Date());
            kilogramo.setUsuarioeliminacion(usuarioEliminacion);
            kilogramo.setActivo(false);
            em.merge(kilogramo);
            em.flush();
            return (kilogramo);
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
    public Diesel deleteDiesel(Diesel diesel, String usuarioEliminacion) {
        try {
            diesel.setFechaeliminacion(new Date());
            diesel.setUsuarioeliminacion(usuarioEliminacion);
            diesel.setActivo(false);
            em.merge(diesel);
            em.flush();
            return (diesel);
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
    public Kilogramo findKilogramoById(Integer idkilogramo) {
        List<Kilogramo> lst = em.createQuery("SELECT qj FROM Kilogramo qj where qj.activo = true and qj.idkilogramo =:idkilogramo ", Kilogramo.class)
                .setParameter("idkilogramo", idkilogramo)
                .getResultList();

        if (lst == null || lst.isEmpty()) {
            return null;
        }

        return lst.get(0);
    }

    @Override
    public Diesel findDieselById(Integer iddiesel) {
        List<Diesel> lst = em.createQuery("SELECT qj FROM Diesel qj where qj.activo = true and qj.iddiesel =:iddiesel ", Diesel.class)
                .setParameter("iddiesel", iddiesel)
                .getResultList();

        if (lst == null || lst.isEmpty()) {
            return null;
        }

        return lst.get(0);
    }

    @Override
    public List<Unidadmedida> ListUnidadMedida() {
        List<Unidadmedida> lst = em.createQuery("SELECT qj FROM Unidadmedida qj where qj.activo = true ", Unidadmedida.class)
                .getResultList();

        if (lst == null || lst.isEmpty()) {
            return null;
        }

        return lst;
    }

    @Override
    public Unidadmedida saveUnidadMedida(Unidadmedida unidadMedida, String usuarioCreacion) {
        try {
            unidadMedida.setActivo(true);
            unidadMedida.setFechacreacion(new Date());
            unidadMedida.setUsuariocreacion(usuarioCreacion);
            em.persist(unidadMedida);
            em.flush();
            return (unidadMedida);
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
    public Unidadmedida updateUnidadMedida(Unidadmedida unidadMedida, String usuarioModificacion) {
        try {
            unidadMedida.setFechaeliminacion(new Date());
            unidadMedida.setUsuarioeliminacion(usuarioModificacion);
            em.merge(unidadMedida);
            em.flush();
            return (unidadMedida);
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
    public Unidadmedida deleteUnidadMedida(Unidadmedida unidadMedida, String usuarioEliminacion) {
        try {
            unidadMedida.setFechaeliminacion(new Date());
            unidadMedida.setUsuarioeliminacion(usuarioEliminacion);
            unidadMedida.setActivo(false);
            em.merge(unidadMedida);
            em.flush();
            return (unidadMedida);
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
    public Unidadmedida findUnidadMedidaById(Integer idunidadmedida) {
        List<Unidadmedida> lst = em.createQuery("SELECT qj FROM Unidadmedida qj where qj.activo = true and qj.idunidadmedida =:idunidadmedida ", Unidadmedida.class)
                .setParameter("idunidadmedida", idunidadmedida)
                .getResultList();

        if (lst == null || lst.isEmpty()) {
            return null;
        }

        return lst.get(0);
    }

    @Override
    public List<Unidadmedida> listUnidadMedidaBuzon(String descripcion) {
        CriteriaBuilder cb = em.getCriteriaBuilder();
        CriteriaQuery<Unidadmedida> query = cb.createQuery(Unidadmedida.class);
        Root<Unidadmedida> orden = query.from(Unidadmedida.class);

        List<Predicate> lstPredicates = new ArrayList<>();
        Predicate activo = cb.equal(orden.<Integer>get("activo"), 1);
        lstPredicates.add(activo);

        if (descripcion != null) {
            Predicate predicate = cb.like(orden.<String>get("descripcion"), "%" + descripcion + "%");
            lstPredicates.add(predicate);
        }

        query.orderBy(cb.asc(orden.get("fechacreacion")));

        query.where(cb.and(lstPredicates.toArray(new Predicate[lstPredicates.size()])));
        List<Unidadmedida> lst;
        lst = em.createQuery(query).getResultList();
        if (lst == null || lst.isEmpty()) {
            return null;
        }

        return lst;
    }

}
