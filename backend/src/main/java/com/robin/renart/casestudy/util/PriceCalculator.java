package com.robin.renart.casestudy.util;

import java.math.BigDecimal;

public class PriceCalculator {
    private PriceCalculator() {}

    //(popularityScore + 1) * weight * goldPrice
    public static BigDecimal calculatePrice(double popularityScore, double weight, BigDecimal goldPrice) {
        return BigDecimal.valueOf(popularityScore + 1)
                .multiply(BigDecimal.valueOf(weight))
                .multiply(goldPrice);
    }
}
