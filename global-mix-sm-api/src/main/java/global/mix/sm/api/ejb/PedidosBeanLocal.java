package global.mix.sm.api.ejb;

import global.mix.sm.api.entity.Confirmacionpago;
import global.mix.sm.api.entity.Detallepedido;
import global.mix.sm.api.entity.Detallepedidonormal;
import global.mix.sm.api.entity.Pedidos;
import global.mix.sm.api.entity.Secuenciapedido;
import global.mix.sm.api.entity.Tipocemento;
import java.util.Date;
import java.util.List;

/**
 *
 * @author rcacacho
 */
public interface PedidosBeanLocal {

    List<Pedidos> listPedidoBuzon(Integer idasesor, Integer idcliente, Integer idestadopedido, String obra, Date fechaInicio, Date fechaFin);

    List<Pedidos> listPedido();

    Pedidos savePedido(Pedidos pedido, String usuarioCreacion);

    Pedidos updatePedido(Pedidos pedido, String usuarioModificacion);

    Pedidos deletePedido(Pedidos pedido, String usuarioEliminacion);

    Pedidos findPedidoById(Integer idpedido);

    Detallepedido saveDetallePedido(Detallepedido detallePedido, String usuarioCreacion);

    Detallepedido updateDetallePedido(Detallepedido detallePedido, String usuarioModificacion);

    Detallepedidonormal saveDetallePedidoNormal(Detallepedidonormal detallePedidonormal, String usuarioCreacion);

    List<Detallepedidonormal> listDetallePedidoNormalByIdPedido(Integer idpedido);

    Double sumCantidadAgua(Integer idpedido);

    Double sumCantidadCemento(Integer idpedido);

    Double sumCantidadArena(Integer idpedido);

    Double sumCantidadPiedrin(Integer idpedido);

    Double sumCantidadAditivo(Integer idpedido);

    Confirmacionpago saveConfirmarPedido(Confirmacionpago confirmar, String usuarioCreacion);

    Secuenciapedido findUltimaSecuencia();

    Secuenciapedido saveSecuenciaPedido(Secuenciapedido secuencia, String usuarioCreacion);

    Secuenciapedido findSecuenciaPedidoByIdDetalle(Integer iddetallepedidonormal);

    Detallepedidonormal updateDetallePedidoNormal(Detallepedidonormal detallePedidonormal, String usuarioModificacion);

    List<Tipocemento> listTipoCemento();

    Tipocemento findTipoCemento(Integer idTipocemento);

    Tipocemento saveTipoCemento(Tipocemento tipo, String usuarioCreacion);

    Tipocemento updateTipoCemento(Tipocemento tipo, String usuarioModificacion);

    Tipocemento deleteTipoCemento(Tipocemento tipo, String usuarioEliminacion);

    List<Pedidos> listPedidoByMesAnio(Integer mes, Integer anio);

    List<Pedidos> listPedidoByFechaInicio(Date fechaInicio, Date fechaFin);
}
