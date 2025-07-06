package com.robin.renart.casestudy.repository;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.robin.renart.casestudy.model.Product;
import org.springframework.stereotype.Repository;

import java.io.IOException;
import java.io.InputStream;
import java.util.List;

@Repository
public class ProductDataLoader {
    ObjectMapper mapper = new ObjectMapper();

    public List<Product> loadProducts() {
        try (InputStream input = getClass().getClassLoader().getResourceAsStream("products.json")) {
            List<Product> products = mapper.readValue(input, new TypeReference<List<Product>>(){});

            return products;
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
