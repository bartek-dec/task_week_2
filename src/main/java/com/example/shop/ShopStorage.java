package com.example.shop;

import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class ShopStorage {

    private final int minPrice = 50;

    private final int maxPrice = 300;

    private List<Product> products;

    public ShopStorage() {
        products = new ArrayList<>();
        products.add(createProduct("Fridge"));
        products.add(createProduct("Oven"));
        products.add(createProduct("Kettle"));
        products.add(createProduct("Microwave"));
        products.add(createProduct("Cook"));
    }

    public List<Product> getProducts() {
        return products;
    }

    private double generatePrice() {
        return Math.random() * (maxPrice - minPrice) + minPrice;
    }

    private Product createProduct(String name) {
        return new Product(name, generatePrice());
    }
}
