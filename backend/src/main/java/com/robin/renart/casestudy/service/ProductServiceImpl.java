package com.robin.renart.casestudy.service;

import com.robin.renart.casestudy.dto.ProductView;
import com.robin.renart.casestudy.repository.ProductDataLoader;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProductServiceImpl implements ProductService {
    private final ProductDataLoader productDataLoader;

    @Autowired
    public ProductServiceImpl(ProductDataLoader productDataLoader) {
        this.productDataLoader = productDataLoader;
    }

    @Override
    public List<ProductView> getAllProducts() {
        return List.of();
    }
}
