package com.example.shop;

import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.event.EventListener;

import java.util.Optional;
import java.util.Scanner;


public abstract class Shop {

    protected Scanner scanner = new Scanner(System.in);

    protected ShopStorage shopStorage;
    protected Cart cart;
    protected Util util;


    public Shop(ShopStorage shopStorage, Cart cart, Util util) {
        this.shopStorage = shopStorage;
        this.cart = cart;
        this.util = util;
    }

    @EventListener(ApplicationReadyEvent.class)
    public void run() {
        int input;
        do {
            displayMenu();
            input = getUserInput();
            switch (input) {
                case 1:
                    addToCart();
                    break;
                case 2:
                    displayCart();
                    break;
                case 0:
                    System.out.println("Good bye");
                    break;
            }

        } while (input != 0);
    }

    private void displayMenu() {
        System.out.println("1) Add product to cart");
        System.out.println("2) Display products in cart");
        System.out.println("0) Exit");
    }

    private int getUserInput() {
        int input;

        try {
            input = Integer.parseInt(scanner.nextLine().trim());

            if (input < 0 || input > 2) {
                System.out.println("\nWrong input\n");
            }
        } catch (NumberFormatException e) {
            System.out.println("\nWrong input\n");
            return -1;
        }

        return input;
    }

    private void addToCart() {
        System.out.println("Available products:");
        util.displayProducts(shopStorage.getProducts());

        System.out.print("\nChoose product name (case sensitive): ");
        String name = scanner.nextLine().trim();

        Optional<Product> optionalProduct = shopStorage.getProducts().stream()
                .filter(e -> e.getName().equals(name))
                .findFirst();

        optionalProduct.ifPresentOrElse((product) -> {
            cart.addProduct(product);
            shopStorage.getProducts().remove(product);
            System.out.println("Product added to the cart\n");
        }, () -> System.out.println("Wrong input\n"));

    }

    protected abstract void displayCart();
}
