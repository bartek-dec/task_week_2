package com.example.shop;

import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class Cart {

    private List<Product> userProducts = new ArrayList<>();

    public List<Product> getUserProducts() {
        return userProducts;
    }
}

