package com.example.shop;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Service;

@Service
@Profile("start")
public class ShopSTART extends Shop {

    @Autowired
    public ShopSTART(ShopStorage shopStorage, Cart cart, Util util) {
        super(shopStorage, cart, util);
    }

    public void displayCart() {
        System.out.println("Your products:");
        util.displayProducts(cart.getUserProducts());

        double totalValue = cart.getTotalValue();

        System.out.printf("Total value of products is: %.2f\n\n", totalValue);
    }
}
