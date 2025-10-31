package model;

/**
 * Enum para representar moedas suportadas.
 * Adicionar novas moedas aqui torna o restante do código automático (menu, seleção, etc).
 */
public enum Currency {
    USD("USD"),
    BRL("BRL"),
    ARS("ARS"),
    BOB("BOB");

    private final String code;

    Currency(String code) {
        this.code = code;
    }

    public String code() {
        return code;
    }

    @Override
    public String toString() {
        return code;
    }
}

