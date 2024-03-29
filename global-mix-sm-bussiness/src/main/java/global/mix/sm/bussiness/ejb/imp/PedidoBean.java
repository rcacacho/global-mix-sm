package global.mix.sm.bussiness.ejb.imp;

import global.mix.sm.api.ejb.PedidosBeanLocal;
import global.mix.sm.api.entity.Asesor;
import global.mix.sm.api.entity.Cliente;
import global.mix.sm.api.entity.Confirmacionpago;
import global.mix.sm.api.entity.Detallepedido;
import global.mix.sm.api.entity.Detallepedidonormal;
import global.mix.sm.api.entity.Estadopedido;
import global.mix.sm.api.entity.Pedidos;
import global.mix.sm.api.entity.Secuenciapedido;
import global.mix.sm.api.entity.Tipocemento;
import java.util.ArrayList;
import java.util.Calendar;
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
public class PedidoBean implements PedidosBeanLocal {

    private static final Logger log = Logger.getLogger(PedidoBean.class);

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

    private Date getDate(Integer dia, Integer mes, Integer anio) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(new Date());
        calendar.set(Calendar.DAY_OF_MONTH, dia);
        calendar.set(Calendar.MONTH, mes);
        calendar.set(Calendar.YEAR, anio);
        return calendar.getTime();
    }

    private Date obtenerFechaInicioDeMesYAnio(Integer mes, Integer anio) {
        return getDate(1, mes - 1, anio);
    }

    private Date obtenerFechaFinDeMesYAnio(Integer mes, Integer anio) {
        if (mes == 1 || mes == 3 || mes == 5 || mes == 7 || mes == 8 || mes == 10 || mes == 12) {
            return getDate(31, mes - 1, anio);
        } else if (mes == 2) {
            return getDate((anio % 4) == 0 ? 29 : 28, mes - 1, anio);
        } else {
            return getDate(30, mes - 1, anio);
        }
    }

    @Override
    public List<Pedidos> listPedidoBuzon(Integer idasesor, Integer idcliente, Integer idestadopedido, String obra, Date fechaInicio, Date fechaFin) {
        CriteriaBuilder cb = em.getCriteriaBuilder();
        CriteriaQuery<Pedidos> query = cb.createQuery(Pedidos.class);
        Root<Pedidos> orden = query.from(Pedidos.class);

        List<Predicate> lstPredicates = new ArrayList<>();
        Predicate activo = cb.equal(orden.<Integer>get("activo"), 1);
        lstPredicates.add(activo);

        if (idasesor != null) {
            Path<Asesor> asesor = orden.get("idasesor");
            Predicate predicate = cb.equal(asesor.<Integer>get("idasesor"), idasesor);
            lstPredicates.add(predicate);
        }

        if (idcliente != null) {
            Path<Cliente> cliente = orden.get("idcliente");
            Predicate predicate = cb.equal(cliente.<Integer>get("idcliente"), idcliente);
            lstPredicates.add(predicate);
        }

        if (idestadopedido != null) {
            Path<Estadopedido> estado = orden.get("idestadopedido");
            Predicate predicate = cb.equal(estado.<Integer>get("idestadopedido"), idestadopedido);
            lstPredicates.add(predicate);
        }

        if (fechaInicio != null) {
            Predicate predicate = cb.greaterThanOrEqualTo(orden.<Date>get("fechacreacion"), fechaInicio);
            lstPredicates.add(predicate);
        }

        if (obra != null) {
            Predicate predicate = cb.like(orden.<String>get("obra"), '%' + obra + '%');
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
        List<Pedidos> lst;
        lst = em.createQuery(query).getResultList();
        if (lst == null || lst.isEmpty()) {
            return null;
        }

        return lst;
    }

    @Override
    public List<Pedidos> listPedido() {
        List<Pedidos> lst = em.createQuery("SELECT qj FROM Pedidos qj where qj.activo = true ", Pedidos.class)
                .getResultList();

        if (lst == null || lst.isEmpty()) {
            return null;
        }

        return lst;
    }

    @Override
    public Pedidos savePedido(Pedidos pedido, String usuarioCreacion) {
        try {
            pedido.setActivo(true);
            pedido.setFechacreacion(new Date());
            pedido.setUsuariocreacion(usuarioCreacion);
            em.persist(pedido);
            em.flush();
            return (pedido);
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
    public Pedidos updatePedido(Pedidos pedido, String usuarioModificacion) {
        try {
            pedido.setFechamodificacion(new Date());
            pedido.setUsuariomodificacion(usuarioModificacion);
            em.merge(pedido);
            em.flush();
            return (pedido);
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
    public Pedidos deletePedido(Pedidos pedido, String usuarioEliminacion) {
        try {
            pedido.setFechaeliminacion(new Date());
            pedido.setUsuarioeliminacion(usuarioEliminacion);
            pedido.setActivo(false);
            em.merge(pedido);
            em.flush();
            return (pedido);
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
    public Pedidos findPedidoById(Integer idpedido) {
        List<Pedidos> lst = em.createQuery("SELECT cl FROM Pedidos cl WHERE cl.idpedido =:idpedido ", Pedidos.class)
                .setParameter("idpedido", idpedido)
                .getResultList();

        if (lst == null || lst.isEmpty()) {
            return null;
        }

        return lst.get(0);
    }

    @Override
    public Detallepedido saveDetallePedido(Detallepedido detallePedido, String usuarioCreacion) {
        try {
            detallePedido.setActivo(true);
            detallePedido.setFechacreacion(new Date());
            detallePedido.setUsuariocreacion(usuarioCreacion);
            em.persist(detallePedido);
            em.flush();
            return (detallePedido);
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
    public Detallepedido updateDetallePedido(Detallepedido detallePedido, String usuarioModificacion) {
        try {
            //detallePedido.setFechamodificacion(new Date());
            //detallePedido.setUsuariomodificacion(usuarioModificacion);
            em.merge(detallePedido);
            em.flush();
            return (detallePedido);
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
    public Detallepedidonormal saveDetallePedidoNormal(Detallepedidonormal detallePedidonormal, String usuarioCreacion) {
        try {
            detallePedidonormal.setActivo(true);
            detallePedidonormal.setFechacreacion(new Date());
            detallePedidonormal.setUsuariocreacion(usuarioCreacion);
            em.persist(detallePedidonormal);
            em.flush();
            return (detallePedidonormal);
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
    public List<Detallepedidonormal> listDetallePedidoNormalByIdPedido(Integer idpedido) {
        List<Detallepedidonormal> lst = em.createQuery("SELECT cl FROM Detallepedidonormal cl WHERE cl.idpedido.idpedido =:idpedido ", Detallepedidonormal.class)
                .setParameter("idpedido", idpedido)
                .getResultList();

        if (lst == null || lst.isEmpty()) {
            return null;
        }

        return lst;
    }

    @Override
    public Double sumCantidadAgua(Integer idpedido) {
        Double resultado;

        try {
            resultado = (Double) em.createNativeQuery("SELECT sum(cantidadagua) FROM detallepedidonormal d\n"
                    + "WHERE d.activo = 1 \n"
                    + "AND d.idpedido = " + idpedido)
                    .getResultList().get(0);

            return resultado;

        } catch (Exception ex) {
            log.error(ex);
            return null;
        }
    }

    @Override
    public Double sumCantidadCemento(Integer idpedido) {
        Double resultado;

        try {
            resultado = (Double) em.createNativeQuery("SELECT sum(cantidadcemento) FROM detallepedidonormal d\n"
                    + "WHERE d.activo = 1 \n"
                    + "AND d.idpedido = " + idpedido)
                    .getResultList().get(0);

            return resultado;

        } catch (Exception ex) {
            log.error(ex);
            return null;
        }
    }

    @Override
    public Double sumCantidadArena(Integer idpedido) {
        Double resultado;

        try {
            resultado = (Double) em.createNativeQuery("SELECT sum(cantidadarena) FROM detallepedidonormal d\n"
                    + "WHERE d.activo = 1 \n"
                    + "AND d.idpedido = " + idpedido)
                    .getResultList().get(0);

            return resultado;

        } catch (Exception ex) {
            log.error(ex);
            return null;
        }
    }

    @Override
    public Double sumCantidadPiedrin(Integer idpedido) {
        Double resultado;

        try {
            resultado = (Double) em.createNativeQuery("SELECT sum(cantidadpiedrin) FROM detallepedidonormal d\n"
                    + "WHERE d.activo = 1 \n"
                    + "AND d.idpedido = " + idpedido)
                    .getResultList().get(0);

            return resultado;

        } catch (Exception ex) {
            log.error(ex);
            return null;
        }
    }

    @Override
    public Double sumCantidadAditivo(Integer idpedido) {
        Double resultado;

        try {
            resultado = (Double) em.createNativeQuery("SELECT sum(cantidadaditivo) FROM detallepedidonormal d\n"
                    + "WHERE d.activo = 1 \n"
                    + "AND d.idpedido = " + idpedido)
                    .getResultList().get(0);

            return resultado;

        } catch (Exception ex) {
            log.error(ex);
            return null;
        }
    }

    @Override
    public Confirmacionpago saveConfirmarPedido(Confirmacionpago confirmar, String usuarioCreacion) {
        try {
            confirmar.setActivo(true);
            confirmar.setFechacreacion(new Date());
            confirmar.setUsuariocreacion(usuarioCreacion);
            em.persist(confirmar);
            em.flush();
            return (confirmar);
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
    public Secuenciapedido saveSecuenciaPedido(Secuenciapedido secuencia, String usuarioCreacion) {
        try {
            secuencia.setActivo(true);
            secuencia.setFechacreacion(new Date());
            secuencia.setUsuariocreacion(usuarioCreacion);
            em.persist(secuencia);
            em.flush();
            return (secuencia);
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
    public Secuenciapedido findUltimaSecuencia() {
        List<Secuenciapedido> lst = em.createQuery("SELECT cl FROM Secuenciapedido cl WHERE cl.activo = true order by cl.fechacreacion desc ", Secuenciapedido.class)
                .getResultList();

        if (lst == null || lst.isEmpty()) {
            return null;
        }

        return lst.get(0);
    }

    @Override
    public Secuenciapedido findSecuenciaPedidoByIdDetalle(Integer iddetallepedidonormal) {
        List<Secuenciapedido> lst = em.createQuery("SELECT cl FROM Secuenciapedido cl WHERE cl.iddetallepedidonormal.iddetallepedidonormal =:iddetallepedidonormal and cl.activo = true ", Secuenciapedido.class)
                .setParameter("iddetallepedidonormal", iddetallepedidonormal)
                .getResultList();

        if (lst == null || lst.isEmpty()) {
            return null;
        }

        return lst.get(0);
    }

    @Override
    public Detallepedidonormal updateDetallePedidoNormal(Detallepedidonormal detallePedidonormal, String usuarioModificacion) {
        try {
            detallePedidonormal.setFechamodificacion(new Date());
            detallePedidonormal.setUsuariomoficacion(usuarioModificacion);
            em.merge(detallePedidonormal);
            em.flush();
            return (detallePedidonormal);
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
    public List<Tipocemento> listTipoCemento() {
        List<Tipocemento> lst = em.createQuery("SELECT qj FROM Tipocemento qj where qj.activo = true ", Tipocemento.class)
                .getResultList();

        if (lst == null || lst.isEmpty()) {
            return null;
        }

        return lst;
    }

    @Override
    public Tipocemento findTipoCemento(Integer idTipocemento) {
        List<Tipocemento> lst = em.createQuery("SELECT cl FROM Tipocemento cl WHERE cl.idtipocemento =:idTipocemento ", Tipocemento.class)
                .setParameter("idTipocemento", idTipocemento)
                .getResultList();

        if (lst == null || lst.isEmpty()) {
            return null;
        }

        return lst.get(0);
    }

    @Override
    public Tipocemento saveTipoCemento(Tipocemento tipo, String usuarioCreacion) {
        try {
            tipo.setActivo(true);
            tipo.setFechacreacion(new Date());
            tipo.setUsuariocreacion(usuarioCreacion);
            em.persist(tipo);
            em.flush();
            return (tipo);
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
    public Tipocemento updateTipoCemento(Tipocemento tipo, String usuarioModificacion) {
        try {
            tipo.setFechamodificacion(new Date());
            tipo.setUsuariomodificacion(usuarioModificacion);
            em.merge(tipo);
            em.flush();
            return (tipo);
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
    public Tipocemento deleteTipoCemento(Tipocemento tipo, String usuarioEliminacion) {
        try {
            tipo.setFechaeliminacion(new Date());
            tipo.setUsuarioeliminacion(usuarioEliminacion);
            tipo.setActivo(false);
            em.merge(tipo);
            em.flush();
            return (tipo);
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
    public List<Pedidos> listPedidoByMesAnio(Integer mes, Integer anio) {
        try {
            return listPedidoByFechaInicio(obtenerFechaInicioDeMesYAnio(mes, anio), obtenerFechaFinDeMesYAnio(mes, anio));
        } catch (Exception ex) {
            processException(ex);
            return null;
        }
    }

    @Override
    public List<Pedidos> listPedidoByFechaInicio(Date fechaInicio, Date fechaFin) {
        if (fechaInicio != null) {
            Calendar c = Calendar.getInstance();
            c.setTime(fechaInicio);
            c.set(Calendar.HOUR_OF_DAY, 0);
            c.set(Calendar.MINUTE, 0);
            c.set(Calendar.SECOND, 0);
            fechaInicio = c.getTime();
        }

        if (fechaFin != null) {
            Calendar c1 = Calendar.getInstance();
            c1.setTime(fechaFin);
            c1.set(Calendar.HOUR_OF_DAY, 23);
            c1.set(Calendar.MINUTE, 59);
            c1.set(Calendar.SECOND, 59);
            fechaFin = c1.getTime();
        }

        List<Pedidos> lst = em.createQuery("SELECT qj FROM Pedidos qj where qj.activo = true and qj.fechapedido >=:fechaInicio and qj.fechapedido <=:fechaFin ", Pedidos.class)
                .setParameter("fechaInicio", fechaInicio)
                .setParameter("fechaFin", fechaFin)
                .getResultList();

        if (lst == null || lst.isEmpty()) {
            return null;
        }

        return lst;
    }

}
