package global.mix.sm.bussiness.ejb.imp;

import global.mix.sm.api.ejb.AsesoresBeanLocal;
import global.mix.sm.api.entity.Asesor;
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
public class AsesorBean implements AsesoresBeanLocal {

    private static final Logger log = Logger.getLogger(AsesorBean.class);

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
    public List<Asesor> ListAsesorBuzon(String nombres) {
        CriteriaBuilder cb = em.getCriteriaBuilder();
        CriteriaQuery<Asesor> query = cb.createQuery(Asesor.class);
        Root<Asesor> orden = query.from(Asesor.class);

        List<Predicate> lstPredicates = new ArrayList<>();
        Predicate activo = cb.equal(orden.<Integer>get("activo"), 1);
        lstPredicates.add(activo);

        if (nombres != null) {
            Predicate orClause = cb.or(cb.like(orden.<String>get("nombres"), nombres),
                    cb.like(orden.<String>get("apellidos"), nombres));
            lstPredicates.add(orClause);
        }

        query.orderBy(cb.asc(orden.get("fechacreacion")));

        query.where(cb.and(lstPredicates.toArray(new Predicate[lstPredicates.size()])));
        List<Asesor> lst;
        lst = em.createQuery(query).getResultList();
        if (lst == null || lst.isEmpty()) {
            return null;
        }

        return lst;
    }

    @Override
    public List<Asesor> ListAsesores() {
        List<Asesor> lst = em.createQuery("SELECT qj FROM Asesor qj where qj.activo = true ", Asesor.class)
                .getResultList();

        if (lst == null || lst.isEmpty()) {
            return null;
        }

        return lst;
    }

    @Override
    public Asesor saveAsesor(Asesor asesor, String usuarioCreacion) {
        try {
            asesor.setActivo(true);
            asesor.setFechacreacion(new Date());
            asesor.setUsuariocreacion(usuarioCreacion);
            em.persist(asesor);
            em.flush();
            return (asesor);
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
    public Asesor updateAsesor(Asesor asesor, String usuarioModificacion) {
        try {
            asesor.setFechamodificacion(new Date());
            asesor.setUsuariomodificacion(usuarioModificacion);
            em.merge(asesor);
            em.flush();
            return (asesor);
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
    public Asesor deleteAsesor(Asesor asesor, String usuarioEliminacion) {
        try {
            asesor.setFechaeliminacion(new Date());
            asesor.setUsuarioeliminacion(usuarioEliminacion);
            asesor.setActivo(false);
            em.merge(asesor);
            em.flush();
            return (asesor);
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
    public Asesor findAsesorById(Integer idasesor) {
        List<Asesor> lst = em.createQuery("SELECT cl FROM Asesor cl WHERE cl.idasesor =:idasesor ", Asesor.class)
                .setParameter("idasesor", idasesor)
                .getResultList();

        if (lst == null || lst.isEmpty()) {
            return null;
        }

        return lst.get(0);
    }

}
