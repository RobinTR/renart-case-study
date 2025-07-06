package com.robin.renart.casestudy.service;

import com.robin.renart.casestudy.client.GoldApiClient;
import com.robin.renart.casestudy.dto.GoldRateResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class GoldRateServiceImpl implements GoldRateService {
    private final GoldApiClient goldApiClient;

    @Autowired
    public GoldRateServiceImpl(GoldApiClient goldApiClient) {
        this.goldApiClient = goldApiClient;
    }

    @Override
    public GoldRateResponse getCurrentGoldRateInUsd() {
        return goldApiClient.getGoldPrice().block();
    }
}
