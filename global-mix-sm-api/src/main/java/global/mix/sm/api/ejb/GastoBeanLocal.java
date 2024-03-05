package global.mix.sm.api.ejb;

import global.mix.sm.api.entity.Gastos;
import java.util.Date;
import java.util.List;

/**
 *
 * @author rcacacho
 */
public interface GastoBeanLocal {

    List<Gastos> ListGastos();

    Gastos saveGasto(Gastos gastos, String usuarioCreacion);

    Gastos updateGasto(Gastos gasto, String usuarioModificacion);

    Gastos deleteGasto(Gastos gasto, String usuarioEliminacion);

    Gastos findGastoById(Integer idgasto);

    List<Gastos> listGastoBuzon(Date fechaInicio, Date fechaFin);

}
