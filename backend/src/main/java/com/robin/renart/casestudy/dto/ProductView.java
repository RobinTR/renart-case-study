package com.robin.renart.casestudy.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.util.Map;

@Getter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ProductView {
    private String name;
    private BigDecimal priceText;
    private double ratingOutOfFive;
    private Map<String, String> imageByColor;
}
