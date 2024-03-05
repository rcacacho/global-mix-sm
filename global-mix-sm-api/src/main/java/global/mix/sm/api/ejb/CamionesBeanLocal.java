package global.mix.sm.api.ejb;

import global.mix.sm.api.entity.Camion;
import java.util.List;

/**
 *
 * @author rcacacho
 */
public interface CamionesBeanLocal {

    List<Camion> ListCamionesBuzon(String descripcion, Integer numero);

    List<Camion> ListCamiones();

    Camion saveCamion(Camion camion, String usuarioCreacion);

    Camion updateCamion(Camion camion, String usuarioModificacion);

    Camion deleteCamion(Camion camion, String usuarioEliminacion);

    Camion findCamionById(Integer idcamion);

}
