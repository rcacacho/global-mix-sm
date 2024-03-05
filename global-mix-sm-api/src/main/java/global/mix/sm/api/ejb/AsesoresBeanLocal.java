package global.mix.sm.api.ejb;

import global.mix.sm.api.entity.Asesor;
import java.util.List;

/**
 *
 * @author rcacacho
 */
public interface AsesoresBeanLocal {

    List<Asesor> ListAsesorBuzon(String nombres);

    List<Asesor> ListAsesores();

    Asesor saveAsesor(Asesor asesor, String usuarioCreacion);

    Asesor updateAsesor(Asesor asesor, String usuarioModificacion);

    Asesor deleteAsesor(Asesor asesor, String usuarioEliminacion);

    Asesor findAsesorById(Integer idasesor);

}
