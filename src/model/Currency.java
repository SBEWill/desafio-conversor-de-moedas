package model;


public enum Currency {
    USD("USD"),
    BRL("BRL"),
    ARS("ARS"),
    EUR("EUR"),
    CNY("CNY"),
    KES("KES"),
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

