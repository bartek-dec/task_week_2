package com.example.shop;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Service;

@Service
@Profile("pro")
public class ShopPRO extends Shop {

    @Value("${vat}")
    private int vat;

    @Value("${discount}")
    private int discount;

    @Autowired
    public ShopPRO(ShopStorage shopStorage, Cart cart, Util util) {
        super(shopStorage, cart, util);
    }

    protected void displayCart() {
        System.out.println("Your products:");
        util.displayProducts(cart.getUserProducts());

        double totalValue = cart.getUserProducts().stream()
                .map(Product::getPrice)
                .reduce(0.0, Double::sum);

        System.out.printf("Total value of products is: %.2f\n\n", totalValue);

        double tax = (totalValue * vat) / 100;
        System.out.printf("Value of the VAT tax is: %.2f\n\n", tax);

        double discount = ((totalValue + tax) * this.discount) / 100;
        System.out.printf("Value of the discount tax is: %.2f\n\n", discount);
    }
}
