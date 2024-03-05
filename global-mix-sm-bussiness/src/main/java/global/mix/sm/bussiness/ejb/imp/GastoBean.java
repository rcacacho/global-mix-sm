package global.mix.sm.bussiness.ejb.imp;

import global.mix.sm.api.ejb.GastoBeanLocal;
import global.mix.sm.api.entity.Gastos;
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
public class GastoBean implements GastoBeanLocal {

    private static final Logger log = Logger.getLogger(GastoBean.class);

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
    public List<Gastos> ListGastos() {
        List<Gastos> lst = em.createQuery("SELECT qj FROM Gastos qj where qj.activo = true ", Gastos.class)
                .getResultList();

        if (lst == null || lst.isEmpty()) {
            return null;
        }

        return lst;
    }

    @Override
    public Gastos saveGasto(Gastos gastos, String usuarioCreacion) {
        try {
            gastos.setActivo(true);
            gastos.setFechacreacion(new Date());
            gastos.setUsuariocreacion(usuarioCreacion);
            em.persist(gastos);
            em.flush();
            return (gastos);
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
    public Gastos updateGasto(Gastos gasto, String usuarioModificacion) {
        try {
            gasto.setFechamodificacion(new Date());
            gasto.setUsuariomodificacion(usuarioModificacion);
            em.merge(gasto);
            em.flush();
            return (gasto);
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
    public Gastos deleteGasto(Gastos gasto, String usuarioEliminacion) {
        try {
            gasto.setFechaeliminacion(new Date());
            gasto.setUsuarioeliminacion(usuarioEliminacion);
            em.merge(gasto);
            em.flush();
            return (gasto);
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
    public Gastos findGastoById(Integer idgasto) {
        List<Gastos> lst = em.createQuery("SELECT qj FROM Gastos qj where qj.activo = true and qj.idgasto =:idunidadmedida ", Gastos.class)
                .setParameter("idgasto", idgasto)
                .getResultList();

        if (lst == null || lst.isEmpty()) {
            return null;
        }

        return lst.get(0);
    }

    @Override
    public List<Gastos> listGastoBuzon(Date fechaInicio, Date fechaFin) {
        CriteriaBuilder cb = em.getCriteriaBuilder();
        CriteriaQuery<Gastos> query = cb.createQuery(Gastos.class);
        Root<Gastos> orden = query.from(Gastos.class);

        List<Predicate> lstPredicates = new ArrayList<>();
        Predicate activo = cb.equal(orden.<Integer>get("activo"), 1);
        lstPredicates.add(activo);

     if (fechaInicio != null && fechaFin != null) {
            Predicate predicate = cb.between(orden.<Date>get("fechacreacion"), fechaInicio, fechaFin);
            lstPredicates.add(predicate);
        }else if (fechaInicio != null) {
            Predicate predicate = cb.greaterThanOrEqualTo(orden.<Date>get("fechacreacion"), fechaInicio);
            lstPredicates.add(predicate);
        }else if (fechaFin != null) {
            Predicate predicate = cb.lessThanOrEqualTo(orden.<Date>get("fechacreacion"), fechaFin);
            lstPredicates.add(predicate);
        }

        query.orderBy(cb.asc(orden.get("fechacreacion")));

        query.where(cb.and(lstPredicates.toArray(new Predicate[lstPredicates.size()])));
        List<Gastos> lst;
        lst = em.createQuery(query).getResultList();
        if (lst == null || lst.isEmpty()) {
            return null;
        }

        return lst;
    }

}
