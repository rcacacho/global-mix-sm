package global.mix.sm.api.ejb;

import global.mix.sm.api.entity.Cliente;
import java.util.List;
import javax.ejb.Local;

/**
 *
 * @author rcacacho
 */
@Local
public interface ClienteBeanLocal {

    List<Cliente> ListClientesBuzon(String nombres, String nit);

    List<Cliente> ListClientes();

    Cliente saveCliente(Cliente cliente, String usuarioCreacion);

    Cliente updateCliente(Cliente cliente, String usuarioModificacion);

    Cliente deleteCliente(Cliente cliente, String usuarioEliminacion);

    Cliente findCliente(Integer idcliente);

}
