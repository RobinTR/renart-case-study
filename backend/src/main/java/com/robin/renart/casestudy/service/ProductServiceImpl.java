package com.robin.renart.casestudy.service;

import com.robin.renart.casestudy.client.GoldApiClient;
import com.robin.renart.casestudy.dto.GoldRateResponse;
import com.robin.renart.casestudy.dto.ProductView;
import com.robin.renart.casestudy.exception.GoldPriceUnavailableException;
import com.robin.renart.casestudy.mapper.ProductMapper;
import com.robin.renart.casestudy.model.Product;
import com.robin.renart.casestudy.repository.ProductDataLoader;
import com.robin.renart.casestudy.util.PriceCalculator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;

@Service
public class ProductServiceImpl implements ProductService {
    private final ProductDataLoader productDataLoader;
    private final GoldApiClient goldApiClient;

    @Autowired
    public ProductServiceImpl(ProductDataLoader productDataLoader, GoldApiClient goldApiClient) {
        this.productDataLoader = productDataLoader;
        this.goldApiClient = goldApiClient;
    }

    @Override
    public List<ProductView> getAllProducts() {
        checkGoldApiHealth();
        List<Product> products = productDataLoader.loadProducts();
        GoldRateResponse response = goldApiClient.getGoldPrice().block();
        BigDecimal goldPrice = Optional.ofNullable(response)
                .map(GoldRateResponse::getPrice)
                .orElse(BigDecimal.ZERO);

        if (BigDecimal.ZERO.compareTo(goldPrice) == 0) {
            throw new GoldPriceUnavailableException("An error occurred while fetching the gold price.");
        }

        System.out.println("Gold price from API: " + goldPrice);

        //(popularityScore + 1) * weight * goldPrice(from api)
        return products.stream()
                .map(product -> {
                    BigDecimal price = PriceCalculator.calculatePrice(
                            product.getPopularityScore(),
                            product.getWeight(),
                            goldPrice
                    );
                    return ProductMapper.mapToView(product, price);
                }).toList();
    }

    private void checkGoldApiHealth() {
        Boolean isHealthy = goldApiClient.checkStatus().block();

        if (Boolean.FALSE.equals(isHealthy)) {
            throw new GoldPriceUnavailableException("Gold API is currently unavailable.");
        }
    }
}
