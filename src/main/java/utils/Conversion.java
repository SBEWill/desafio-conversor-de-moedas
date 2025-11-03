package utils;

import java.math.BigDecimal;
import java.math.RoundingMode;

public class Conversion {

    public BigDecimal convertendoMoedas(BigDecimal taxa, BigDecimal volume){

        BigDecimal conversao = taxa.multiply(volume);
        return  conversao.setScale(2, RoundingMode.HALF_UP);

    }
}
