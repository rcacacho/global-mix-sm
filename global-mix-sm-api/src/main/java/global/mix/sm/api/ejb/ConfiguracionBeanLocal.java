package global.mix.sm.api.ejb;

import global.mix.sm.api.entity.Diesel;
import global.mix.sm.api.entity.Kilogramo;
import global.mix.sm.api.entity.Unidadmedida;
import java.util.List;
import javax.ejb.Local;

/**
 *
 * @author rcacacho
 */
@Local
public interface ConfiguracionBeanLocal {

    List<Kilogramo> ListKilogramos();

    Kilogramo saveKilogramo(Kilogramo kilogramos, String usuarioCreacion);

    Kilogramo updateKilogramo(Kilogramo kilogramo, String usuarioModificacion);

    List<Diesel> ListDiesel();

    Diesel saveDiesel(Diesel diesel, String usuarioCreacion);

    Diesel updateDiesel(Diesel diesel, String usuarioModificacion);

    List<Kilogramo> listKilogramoBuzon(String descripcion);

    List<Diesel> listDieselBuzon(String descripcion);

    Kilogramo deleteKilogramo(Kilogramo kilogramo, String usuarioEliminacion);

    Diesel deleteDiesel(Diesel diesel, String usuarioEliminacion);

    Kilogramo findKilogramoById(Integer idkilogramo);

    Diesel findDieselById(Integer iddiesel);

    List<Unidadmedida> ListUnidadMedida();

    Unidadmedida saveUnidadMedida(Unidadmedida unidadMedida, String usuarioCreacion);

    Unidadmedida updateUnidadMedida(Unidadmedida unidadMedida, String usuarioModificacion);

    Unidadmedida deleteUnidadMedida(Unidadmedida unidadMedida, String usuarioEliminacion);

    Unidadmedida findUnidadMedidaById(Integer idUnidadMedida);

    List<Unidadmedida> listUnidadMedidaBuzon(String descripcion);

}
