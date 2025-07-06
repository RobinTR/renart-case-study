package com.robin.renart.casestudy.model;

import lombok.*;

import java.util.Map;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
@Builder
public class Product {
    private String name;
    private double popularityScore;
    private double weight;
    private Map<RingColor, String> images;
}
