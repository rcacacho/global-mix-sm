package global.mix.sm.bussiness.ejb.imp;

import global.mix.sm.api.ejb.MaterialBeanLocal;
import global.mix.sm.api.entity.Detallematerial;
import global.mix.sm.api.entity.Material;
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
public class MaterialBean implements MaterialBeanLocal {

    private static final Logger log = Logger.getLogger(MaterialBean.class);

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
    public List<Material> listMaterialBuzon(String material, Date fechaInicio, Date fechaFin) {
        CriteriaBuilder cb = em.getCriteriaBuilder();
        CriteriaQuery<Material> query = cb.createQuery(Material.class);
        Root<Material> orden = query.from(Material.class);

        List<Predicate> lstPredicates = new ArrayList<>();
        Predicate activo = cb.equal(orden.<Integer>get("activo"), 1);
        lstPredicates.add(activo);

        if (material != null || !material.isEmpty()) {
            Predicate predicate = cb.like(orden.<String>get("material"), '%' + material + '%');
            lstPredicates.add(predicate);
        }

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
        List<Material> lst;
        lst = em.createQuery(query).getResultList();
        if (lst == null || lst.isEmpty()) {
            return null;
        }

        return lst;
    }

    @Override
    public List<Material> listMaterial() {
        List<Material> lst = em.createQuery("SELECT qj FROM Material qj where qj.activo = true ", Material.class)
                .getResultList();

        if (lst == null || lst.isEmpty()) {
            return null;
        }

        return lst;
    }

    @Override
    public Material saveMaterial(Material material, String usuarioCreacion) {
        try {
            material.setActivo(true);
            material.setFechacreacion(new Date());
            material.setUsuariocreacion(usuarioCreacion);
            em.persist(material);
            em.flush();
            return (material);
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
    public Material updateMaterial(Material material, String usuarioModificacion) {
        try {
            material.setFechamodificacion(new Date());
            material.setUsuariomodificacion(usuarioModificacion);
            em.merge(material);
            em.flush();
            return (material);
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
    public Material deleteMaterial(Material material, String usuarioEliminacion) {
        try {
            material.setFechaeliminacion(new Date());
            material.setUsuarioeliminacion(usuarioEliminacion);
            material.setActivo(false);
            em.merge(material);
            em.flush();
            return (material);
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
    public Material findMaterialById(Integer idmaterial) {
        List<Material> lst = em.createQuery("SELECT cl FROM Material cl WHERE cl.idmaterial =:idmaterial ", Material.class)
                .setParameter("idmaterial", idmaterial)
                .getResultList();

        if (lst == null || lst.isEmpty()) {
            return null;
        }

        return lst.get(0);
    }

    @Override
    public Material findMaterialByNombre(String material) {
        List<Material> lst = em.createQuery("SELECT mat FROM Material mat WHERE mat.material =:material ", Material.class)
                .setParameter("material", material)
                .getResultList();

        if (lst == null || lst.isEmpty()) {
            return null;
        }

        return lst.get(0);
    }

    @Override
    public Detallematerial saveDetalleMaterial(Detallematerial detalleMaterial, String usuarioCreacion) {
        try {
            detalleMaterial.setActivo(true);
            detalleMaterial.setFechacreacion(new Date());
            detalleMaterial.setUsuariocreacion(usuarioCreacion);
            em.persist(detalleMaterial);
            em.flush();
            return (detalleMaterial);
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
    public List<Detallematerial> listDetalleMaterial(Integer idmaterial) {
        List<Detallematerial> lst = em.createQuery("SELECT det FROM Detallematerial det WHERE det.idmaterial.idmaterial =:idmaterial ", Detallematerial.class)
                .setParameter("idmaterial", idmaterial)
                .getResultList();

        if (lst == null || lst.isEmpty()) {
            return null;
        }
        return lst;
    }

}
