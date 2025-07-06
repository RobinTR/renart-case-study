package com.robin.renart.casestudy.client;

import com.robin.renart.casestudy.dto.GoldApiStatusResponse;
import com.robin.renart.casestudy.dto.GoldRateResponse;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;

@Component
public class GoldApiClient {
    private final WebClient webClient;
    private final String currency = "USD";
    private final String metal = "XAU";

    public GoldApiClient(
            WebClient.Builder builder,
            @Value("${goldapi.base-url}") final String BASE_URL,
            @Value("${goldapi.token}") final String API_KEY
    ) {
        this.webClient = builder
                .baseUrl(BASE_URL)
                .defaultHeader("x-access-token", API_KEY)
                .defaultHeader("Content-Type", "application/json")
                .build();
    }

    public Mono<Boolean> checkStatus() {
        return webClient.get()
                .uri("/status")
                .retrieve()
                .bodyToMono(GoldApiStatusResponse.class)
                .map(GoldApiStatusResponse::getResult)
                .onErrorReturn(false);
    }

    public Mono<GoldRateResponse> getGoldPrice() {
        return webClient.get()
                .uri("/{metal}/{currency}", metal, currency)
                .retrieve()
                .bodyToMono(GoldRateResponse.class);
    }
}
