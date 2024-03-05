package global.mix.sm.bussiness.ejb.imp;

import global.mix.sm.api.ejb.CatalogosBeanLocal;
import global.mix.sm.api.entity.Asesor;
import global.mix.sm.api.entity.Camion;
import global.mix.sm.api.entity.Cliente;
import global.mix.sm.api.entity.Diesel;
import global.mix.sm.api.entity.Estadodespacho;
import global.mix.sm.api.entity.Estadopedido;
import global.mix.sm.api.entity.Material;
import global.mix.sm.api.entity.Tipopago;
import global.mix.sm.api.entity.Tipousuario;
import global.mix.sm.api.entity.Unidadmedida;
import java.util.List;
import javax.annotation.Resource;
import javax.ejb.EJBContext;
import javax.ejb.Singleton;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.validation.ConstraintViolation;
import javax.validation.ConstraintViolationException;
import org.apache.log4j.Logger;

/**
 *
 * @author rcacacho
 */
@Singleton
public class CatalogosBean implements CatalogosBeanLocal {

    private static final Logger log = Logger.getLogger(CatalogosBean.class);

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
    public List<Unidadmedida> listUnidadMedida() {
        List<Unidadmedida> lst = em.createQuery("SELECT qj FROM Unidadmedida qj where qj.activo = true ", Unidadmedida.class)
                .getResultList();

        if (lst == null || lst.isEmpty()) {
            return null;
        }

        return lst;
    }

    @Override
    public List<Tipousuario> listTipoUsuario() {
        List<Tipousuario> lst = em.createQuery("SELECT qj FROM Tipousuario qj where qj.activo = true ", Tipousuario.class)
                .getResultList();

        if (lst == null || lst.isEmpty()) {
            return null;
        }

        return lst;
    }

    @Override
    public List<Cliente> listCliente() {
        List<Cliente> lst = em.createQuery("SELECT qj FROM Cliente qj where qj.activo = true ", Cliente.class)
                .getResultList();

        if (lst == null || lst.isEmpty()) {
            return null;
        }

        return lst;
    }

    @Override
    public List<Asesor> listAsesor() {
        List<Asesor> lst = em.createQuery("SELECT qj FROM Asesor qj where qj.activo = true ", Asesor.class)
                .getResultList();

        if (lst == null || lst.isEmpty()) {
            return null;
        }

        return lst;
    }

    @Override
    public List<Estadopedido> listEstadoPedido() {
        List<Estadopedido> lst = em.createQuery("SELECT qj FROM Estadopedido qj where qj.activo = true ", Estadopedido.class)
                .getResultList();

        if (lst == null || lst.isEmpty()) {
            return null;
        }

        return lst;
    }

    @Override
    public List<Tipopago> listTipoPago() {
        List<Tipopago> lst = em.createQuery("SELECT qj FROM Tipopago qj where qj.activo = true ", Tipopago.class)
                .getResultList();

        if (lst == null || lst.isEmpty()) {
            return null;
        }

        return lst;
    }

    @Override
    public List<Camion> listCamiones() {
        List<Camion> lst = em.createQuery("SELECT qj FROM Camion qj where qj.activo = true ", Camion.class)
                .getResultList();

        if (lst == null || lst.isEmpty()) {
            return null;
        }

        return lst;
    }

    @Override
    public List<Material> listPiedrin() {
        List<Material> lst = em.createQuery("SELECT qj FROM Material qj where qj.activo = true and qj.material like :piedrin ", Material.class)
                .setParameter("piedrin", "%" + "piedrin" + "%")
                .getResultList();

        if (lst == null || lst.isEmpty()) {
            return null;
        }

        return lst;
    }

    @Override
    public List<Material> listAditivo() {
        List<Material> lst = em.createQuery("SELECT qj FROM Material qj where qj.activo = true and qj.material like :aditivo ", Material.class)
                .setParameter("aditivo", "%" + "aditivo" + "%")
                .getResultList();

        if (lst == null || lst.isEmpty()) {
            return null;
        }

        return lst;
    }

    @Override
    public Estadopedido findEstadoPedido(Integer idestadopedido) {
        List<Estadopedido> lst = em.createQuery("SELECT qj FROM Estadopedido qj where qj.activo = true and qj.idestadopedido =:idestadopedido ", Estadopedido.class)
                .setParameter("idestadopedido", idestadopedido)
                .getResultList();

        if (lst == null || lst.isEmpty()) {
            return null;
        }

        return lst.get(0);
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
    public Diesel findConfiguracionDieselById(Integer iddiesel) {
        List<Diesel> lst = em.createQuery("SELECT qj FROM Diesel qj where qj.activo = true and qj.iddiesel =:iddiesel ", Diesel.class)
                .setParameter("iddiesel", iddiesel)
                .getResultList();

        if (lst == null || lst.isEmpty()) {
            return null;
        }

        return lst.get(0);
    }

    @Override
    public List<Material> listMaterial() {
        List<Material> lst = em.createQuery("SELECT qj FROM Material qj where qj.activo = true  ", Material.class)
                .getResultList();

        if (lst == null || lst.isEmpty()) {
            return null;
        }

        return lst;
    }

    @Override
    public Estadodespacho findEstadoDespacho(Integer idestadodespacho) {
        List<Estadodespacho> lst = em.createQuery("SELECT qj FROM Estadodespacho qj where qj.activo = true and qj.idestadodespacho =:idestadodespacho ", Estadodespacho.class)
                .setParameter("idestadodespacho", idestadodespacho)
                .getResultList();

        if (lst == null || lst.isEmpty()) {
            return null;
        }

        return lst.get(0);
    }

}
