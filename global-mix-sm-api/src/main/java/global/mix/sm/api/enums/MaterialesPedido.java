package global.mix.sm.api.enums;

/**
 *
 * @author rcacacho
 */
public enum MaterialesPedido {

    CEMENTO(1),
    ARENA(12),
    AGUA(11),
    DIESEL(14);

    private Integer id;

    private MaterialesPedido(Integer parametro) {
        this.id = parametro;
    }

    public Integer getValue() {
        return id;
    }

}
