package global.mix.sm.bussiness.ejb.imp;

import global.mix.sm.api.ejb.CamionesBeanLocal;
import global.mix.sm.api.entity.Camion;
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
public class CamionBean implements CamionesBeanLocal {

    private static final Logger log = Logger.getLogger(CamionBean.class);

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
    public List<Camion> ListCamionesBuzon(String descripcion, Integer numero) {
        CriteriaBuilder cb = em.getCriteriaBuilder();
        CriteriaQuery<Camion> query = cb.createQuery(Camion.class);
        Root<Camion> orden = query.from(Camion.class);

        List<Predicate> lstPredicates = new ArrayList<>();
        Predicate activo = cb.equal(orden.<Integer>get("activo"), 1);
        lstPredicates.add(activo);

        if (descripcion != null) {
            Predicate predicate = cb.like(orden.<String>get("descripcion"), "%" + descripcion + "%");
            lstPredicates.add(predicate);
        }

        if (numero != null) {
            Predicate predicate = cb.equal(orden.<Integer>get("numero"), numero);
            lstPredicates.add(predicate);
        }

        query.orderBy(cb.asc(orden.get("fechacreacion")));

        query.where(cb.and(lstPredicates.toArray(new Predicate[lstPredicates.size()])));
        List<Camion> lst;
        lst = em.createQuery(query).getResultList();
        if (lst == null || lst.isEmpty()) {
            return null;
        }

        return lst;
    }

    @Override
    public List<Camion> ListCamiones() {
        List<Camion> lst = em.createQuery("SELECT qj FROM Camion qj where qj.activo = true ", Camion.class)
                .getResultList();

        if (lst == null || lst.isEmpty()) {
            return null;
        }

        return lst;
    }

    @Override
    public Camion saveCamion(Camion camion, String usuarioCreacion) {
        try {
            camion.setActivo(true);
            camion.setFechacreacion(new Date());
            camion.setUsuariocreacion(usuarioCreacion);
            em.persist(camion);
            em.flush();
            return (camion);
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
    public Camion updateCamion(Camion camion, String usuarioModificacion) {
        try {
            camion.setFechamodificacion(new Date());
            camion.setUsuariomodificacion(usuarioModificacion);
            em.merge(camion);
            em.flush();
            return (camion);
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
    public Camion deleteCamion(Camion camion, String usuarioEliminacion) {
        try {
            camion.setFechaeliminacion(new Date());
            camion.setUsuarioeliminacion(usuarioEliminacion);
            camion.setActivo(false);
            em.merge(camion);
            em.flush();
            return (camion);
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
    public Camion findCamionById(Integer idcamion) {
        List<Camion> lst = em.createQuery("SELECT cl FROM Camion cl WHERE cl.idcamion =:idcamion ", Camion.class)
                .setParameter("idcamion", idcamion)
                .getResultList();

        if (lst == null || lst.isEmpty()) {
            return null;
        }

        return lst.get(0);
    }

}
