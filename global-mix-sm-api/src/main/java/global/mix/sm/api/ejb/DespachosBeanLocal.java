package global.mix.sm.api.ejb;

import global.mix.sm.api.entity.Despachos;
import java.util.Date;
import java.util.List;

/**
 *
 * @author rcacacho
 */
public interface DespachosBeanLocal {

    List<Despachos> listDespachoBuzon(Integer idasesor, Integer idcliente, String obra, Date fechaInicio, Date fechaFin);

    List<Despachos> listDespachos();

    Despachos saveDespacho(Despachos despacho, String usuarioCreacion);

    Despachos findDespachoById(Integer iddespacho);

    Despachos findDespachoByIdPedido(Integer idpedido);

    Despachos finalizarDespacho(Despachos despacho, String usuarioModificacion);

    List<Despachos> listDespachoBuzonReporteGeneral(Date fechaInicio, Date fechaFin);
    
    Double sumCantidadDespachos(String fecha);
    
    List<Despachos> listDespachoByFecha(Date fechacreacion);
    
    Despachos updateDespacho(Despachos despacho, String usuarioModificacion);

}
