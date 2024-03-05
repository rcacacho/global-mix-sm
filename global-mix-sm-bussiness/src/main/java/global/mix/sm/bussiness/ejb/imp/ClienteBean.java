package global.mix.sm.bussiness.ejb.imp;

import global.mix.sm.api.ejb.ClienteBeanLocal;
import global.mix.sm.api.entity.Cliente;
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
public class ClienteBean implements ClienteBeanLocal {

    private static final Logger log = Logger.getLogger(ClienteBean.class);

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
    public List<Cliente> ListClientesBuzon(String nombres, String nit) {
        CriteriaBuilder cb = em.getCriteriaBuilder();
        CriteriaQuery<Cliente> query = cb.createQuery(Cliente.class);
        Root<Cliente> orden = query.from(Cliente.class);

        List<Predicate> lstPredicates = new ArrayList<>();
        Predicate activo = cb.equal(orden.<Integer>get("activo"), 1);
        lstPredicates.add(activo);

        if (nit != null) {
            Predicate predicate = cb.equal(orden.<String>get("nit"),  nit);
            lstPredicates.add(predicate);
        }
        
        if (nombres != null){
            Predicate orClause = cb.or(cb.like(orden.<String>get("nombres"), '%'+nombres +'%'), 
                    cb.like(orden.<String>get("apellidos"), '%'+nombres + '%'));
            lstPredicates.add(orClause);
        }

        query.orderBy(cb.asc(orden.get("fechacreacion")));

        query.where(cb.and(lstPredicates.toArray(new Predicate[lstPredicates.size()])));
        List<Cliente> lst;
        lst = em.createQuery(query).getResultList();
        if (lst == null || lst.isEmpty()) {
            return null;
        }

        return lst;
    }

    @Override
    public List<Cliente> ListClientes() {
        List<Cliente> lst = em.createQuery("SELECT qj FROM Cliente qj where qj.activo = true ", Cliente.class)
                .getResultList();

        if (lst == null || lst.isEmpty()) {
            return null;
        }

        return lst;
    }

    @Override
    public Cliente saveCliente(Cliente cliente, String usuarioCreacion) {
        try {
            cliente.setActivo(true);
            cliente.setFechacreacion(new Date());
            cliente.setUsuariocreacion(usuarioCreacion);
            em.persist(cliente);
            em.flush();
            return (cliente);
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
    public Cliente updateCliente(Cliente cliente, String usuarioModificacion) {
        try {
            cliente.setFechamodificacion(new Date());
            cliente.setUsuariomodificacion(usuarioModificacion);
            em.merge(cliente);
            em.flush();
            return (cliente);
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
    public Cliente deleteCliente(Cliente cliente, String usuarioEliminacion) {
        try {
            cliente.setFechaeliminacion(new Date());
            cliente.setUsuarioeliminacion(usuarioEliminacion);
            cliente.setActivo(false);
            em.merge(cliente);
            em.flush();
            return (cliente);
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
    public Cliente findCliente(Integer idcliente) {
        List<Cliente> lst = em.createQuery("SELECT cl FROM Cliente cl WHERE cl.idcliente =:idcliente ", Cliente.class)
                .setParameter("idcliente", idcliente)
                .getResultList();

        if (lst == null || lst.isEmpty()) {
            return null;
        }

        return lst.get(0);
    }

}
