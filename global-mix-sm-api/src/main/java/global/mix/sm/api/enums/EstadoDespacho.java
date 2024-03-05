package global.mix.sm.api.enums;

/**
 *
 * @author rcacacho
 */
public enum EstadoDespacho {

    EN_PROCESO(1),
    FINALIZADO(2);

    private Integer id;

    private EstadoDespacho(Integer parametro) {
        this.id = parametro;
    }

    public Integer getValue() {
        return id;
    }

}
