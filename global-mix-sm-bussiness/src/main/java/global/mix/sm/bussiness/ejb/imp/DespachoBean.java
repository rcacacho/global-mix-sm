package global.mix.sm.bussiness.ejb.imp;

import global.mix.sm.api.ejb.DespachosBeanLocal;
import global.mix.sm.api.entity.Asesor;
import global.mix.sm.api.entity.Cliente;
import global.mix.sm.api.entity.Despachos;
import global.mix.sm.api.entity.Pedidos;
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
import javax.persistence.criteria.Path;
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
public class DespachoBean implements DespachosBeanLocal {

    private static final Logger log = Logger.getLogger(DespachosBeanLocal.class);

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
    public List<Despachos> listDespachoBuzon(Integer idasesor, Integer idcliente, String obra, Date fechaInicio, Date fechaFin) {
        CriteriaBuilder cb = em.getCriteriaBuilder();
        CriteriaQuery<Despachos> query = cb.createQuery(Despachos.class);
        Root<Despachos> orden = query.from(Despachos.class);

        List<Predicate> lstPredicates = new ArrayList<>();
        Predicate activo = cb.equal(orden.<Integer>get("activo"), 1);
        lstPredicates.add(activo);

        if (idasesor != null) {
            Path<Pedidos> pedidos = orden.get("idpedido");
            Path<Asesor> asesor = pedidos.get("idasesor");
            Predicate predicate = cb.equal(asesor.<Integer>get("idasesor"), idasesor);
            lstPredicates.add(predicate);
        }

        if (idcliente != null) {
            Path<Pedidos> pedidos = orden.get("idpedido");
            Path<Cliente> cliente = pedidos.get("idcliente");
            Predicate predicate = cb.equal(cliente.<Integer>get("idcliente"), idcliente);
            lstPredicates.add(predicate);
        }

        if (fechaInicio != null) {
            Predicate predicate = cb.greaterThanOrEqualTo(orden.<Date>get("fechacreacion"), fechaInicio);
            lstPredicates.add(predicate);
        }

        if (obra != null) {
            Path<Pedidos> pedidos = orden.get("idpedido");
            Predicate predicate = cb.like(pedidos.<String>get("obra"), '%' + obra + '%');
            lstPredicates.add(predicate);
        }

        if (fechaFin != null) {
            Predicate predicate = cb.lessThanOrEqualTo(orden.<Date>get("fechacreacion"), fechaFin);
            lstPredicates.add(predicate);
        }

        if (fechaInicio != null && fechaFin != null) {
            Predicate predicate = cb.between(orden.<Date>get("fechacreacion"), fechaInicio, fechaFin);
            lstPredicates.add(predicate);
        }

        query.orderBy(cb.asc(orden.get("fechacreacion")));

        query.where(cb.and(lstPredicates.toArray(new Predicate[lstPredicates.size()])));
        List<Despachos> lst;
        lst = em.createQuery(query).getResultList();
        if (lst == null || lst.isEmpty()) {
            return null;
        }

        return lst;
    }

    @Override
    public List<Despachos> listDespachos() {
        List<Despachos> lst = em.createQuery("SELECT qj FROM Despachos qj where qj.activo = true ", Despachos.class)
                .getResultList();

        if (lst == null || lst.isEmpty()) {
            return null;
        }

        return lst;
    }

    @Override
    public Despachos saveDespacho(Despachos despacho, String usuarioCreacion) {
        try {
            despacho.setActivo(true);
            despacho.setFechacreacion(new Date());
            despacho.setUsuariocreacion(usuarioCreacion);
            em.persist(despacho);
            em.flush();
            return (despacho);
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
    public Despachos findDespachoById(Integer iddespacho) {
        List<Despachos> lst = em.createQuery("SELECT cl FROM Despachos cl WHERE cl.iddespacho =:iddespacho ", Despachos.class)
                .setParameter("iddespacho", iddespacho)
                .getResultList();

        if (lst == null || lst.isEmpty()) {
            return null;
        }

        return lst.get(0);
    }

    @Override
    public Despachos findDespachoByIdPedido(Integer idpedido) {
        List<Despachos> lst = em.createQuery("SELECT cl FROM Despachos cl WHERE cl.idpedido.idpedido =:idpedido ", Despachos.class)
                .setParameter("idpedido", idpedido)
                .getResultList();

        if (lst == null || lst.isEmpty()) {
            return null;
        }

        return lst.get(0);
    }

    @Override
    public Despachos finalizarDespacho(Despachos despacho, String usuarioModificacion) {
        try {
            despacho.setFechamodificacion(new Date());
            despacho.setUsuariomodificacion(usuarioModificacion);
            em.merge(despacho);
            em.flush();
            return (despacho);
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
    public List<Despachos> listDespachoBuzonReporteGeneral(Date fechaInicio, Date fechaFin) {
        CriteriaBuilder cb = em.getCriteriaBuilder();
        CriteriaQuery<Despachos> query = cb.createQuery(Despachos.class);
        Root<Despachos> orden = query.from(Despachos.class);

        List<Predicate> lstPredicates = new ArrayList<>();
        Predicate activo = cb.equal(orden.<Integer>get("activo"), 1);
        lstPredicates.add(activo);

        if (fechaInicio != null) {
            Predicate predicate = cb.greaterThanOrEqualTo(orden.<Date>get("fechacreacion"), fechaInicio);
            lstPredicates.add(predicate);
        }

        if (fechaFin != null) {
            Predicate predicate = cb.lessThanOrEqualTo(orden.<Date>get("fechacreacion"), fechaFin);
            lstPredicates.add(predicate);
        }

        if (fechaInicio != null && fechaFin != null) {
            Predicate predicate = cb.between(orden.<Date>get("fechacreacion"), fechaInicio, fechaFin);
            lstPredicates.add(predicate);
        }

        query.orderBy(cb.asc(orden.get("fechacreacion")));

        query.where(cb.and(lstPredicates.toArray(new Predicate[lstPredicates.size()])));
        List<Despachos> lst;
        lst = em.createQuery(query).getResultList();
        if (lst == null || lst.isEmpty()) {
            return null;
        }

        return lst;
    }

    @Override
    public Double sumCantidadDespachos(String fecha) {
        Double resultado;

        try {
            resultado = (Double) em.createNativeQuery("SELECT sum(metroscubicosvendidos) FROM despacho d\n"
                    + "WHERE d.activo = 1 \n"
                    + "AND d.fechacreacion = " + fecha)
                    .getResultList().get(0);

            return resultado;

        } catch (Exception ex) {
            log.error(ex);
            return null;
        }
    }

    @Override
    public List<Despachos> listDespachoByFecha(Date fechacreacion) {
       List<Despachos> lst = em.createQuery("SELECT cl FROM Despachos cl WHERE cl.fechacreacion =:fechacreacion and cl.idestadodespacho.idestadodespacho = 2", Despachos.class)
                .setParameter("fechacreacion", fechacreacion)
                .getResultList();

        if (lst == null || lst.isEmpty()) {
            return null;
        }

        return lst;
    }

    @Override
    public Despachos updateDespacho(Despachos despacho, String usuarioModificacion) {
         try {
            despacho.setFechamodificacion(new Date());
            despacho.setUsuariomodificacion(usuarioModificacion);
            em.merge(despacho);
            em.flush();
            return (despacho);
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

}
