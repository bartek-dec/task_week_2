package com.example.shop;

import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class Util {

    public void displayProducts(List<Product> products) {
        if (products.size() == 0) {
            System.out.println("There are no products");
        } else {
            products.stream()
                    .forEach(e -> System.out.printf("%s %.2f \n", e.getName(), e.getPrice()));
        }
    }
}
