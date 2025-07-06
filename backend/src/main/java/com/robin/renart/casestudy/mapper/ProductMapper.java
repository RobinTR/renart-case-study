package com.robin.renart.casestudy.mapper;

import com.robin.renart.casestudy.dto.ProductView;
import com.robin.renart.casestudy.model.Product;
import com.robin.renart.casestudy.model.RingColor;

import java.math.BigDecimal;
import java.util.Map;
import java.util.stream.Collectors;

public class ProductMapper {
    public static ProductView mapToView(Product product, BigDecimal goldPrice) {
        return ProductView.builder()
                .name(product.getName())
                .priceText(goldPrice)
                .ratingOutOfFive(product.getPopularityScore() + 1)
                .imageByColor(convertImageMap(product.getImages()))
                .build();
    }

    private static Map<String, String> convertImageMap(Map<RingColor, String> images) {
        return images.entrySet().stream()
                .collect(Collectors.toMap(e -> e.getKey().name(), Map.Entry::getValue));
    }
}
