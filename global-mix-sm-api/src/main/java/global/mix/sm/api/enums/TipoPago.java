package global.mix.sm.api.enums;

/**
 *
 * @author rcacacho
 */
public enum TipoPago {

    CONTADO(1),
    CREDITO(2),
    MIXTO(3);

    private Integer id;

    private TipoPago(Integer parametro) {
        this.id = parametro;
    }

    public Integer getValue() {
        return id;
    }

}
