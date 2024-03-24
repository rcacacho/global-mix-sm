package global.mix.sm.api.ejb;

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
import javax.ejb.Local;

/**
 *
 * @author rcacacho
 */
@Local
public interface CatalogosBeanLocal {

    List<Unidadmedida> listUnidadMedida();

    List<Tipousuario> listTipoUsuario();

    List<Cliente> listCliente();

    List<Asesor> listAsesor();

    List<Estadopedido> listEstadoPedido();

    List<Tipopago> listTipoPago();

    List<Camion> listCamiones();

    List<Material> listPiedrin();

    List<Material> listAditivo();

    Estadopedido findEstadoPedido(Integer idestadopedido);

    Material findMaterialById(Integer idmaterial);

    Diesel findConfiguracionDieselById(Integer iddiesel);

    List<Material> listMaterial();

    Estadodespacho findEstadoDespacho(Integer idestadodespacho);
    
    Material findMaterialExistenciaMayorCeroById(Integer idmaterial);

}
