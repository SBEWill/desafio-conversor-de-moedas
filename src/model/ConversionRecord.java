package model;

import java.math.BigDecimal;

/**
 * Registro imutável simplificado para histórico de conversões.
 * Timestamp é armazenado como String (ISO) para simplicidade com Gson.
 */
public class ConversionRecord {
    private final String timestampIso;
    private final Currency from;
    private final Currency to;
    private final BigDecimal amount;
    private final BigDecimal rate;
    private final BigDecimal result;

    public ConversionRecord(String timestampIso, Currency from, Currency to,
                            BigDecimal amount, BigDecimal rate, BigDecimal result) {
        this.timestampIso = timestampIso;
        this.from = from;
        this.to = to;
        this.amount = amount;
        this.rate = rate;
        this.result = result;
    }

    public String getTimestampIso() { return timestampIso; }
    public Currency getFrom() { return from; }
    public Currency getTo() { return to; }
    public BigDecimal getAmount() { return amount; }
    public BigDecimal getRate() { return rate; }
    public BigDecimal getResult() { return result; }

    @Override
    public String toString() {
        return String.format("[%s] %s %s -> %s %s (rate=%s)",
                timestampIso, amount, from, result, to, rate);
    }
}

