package com.robin.renart.casestudy.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class GoldRateResponse {
    private String metal;
    private String currency;
    private double price;
    private String timestamp;
}
