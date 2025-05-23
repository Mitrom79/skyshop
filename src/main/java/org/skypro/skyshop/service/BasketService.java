package org.skypro.skyshop.service;

import org.skypro.skyshop.model.basket.BasketItem;
import org.skypro.skyshop.model.basket.ProductBasket;
import org.skypro.skyshop.model.basket.UserBasket;
import org.skypro.skyshop.model.product.Product;
import org.springframework.stereotype.Service;
//import org.springframework.web.context.annotation.SessionScope;

import java.util.*;

@Service

public class BasketService {
    private final ProductBasket;
    private final StorageService storageService;

    public BasketService(Map<UUID, Integer> basket, StorageService storageService) {
        this.basket = basket;
        this.storageService = storageService;
    }

    public void addProduct(UUID id) {
        Optional<Product> product = storageService.getProductById(id);
        if(product.isEmpty()) throw new IllegalArgumentException();
        basket.merge(id, 1, Integer::sum);
    }

    public UserBasket getUserBasket() {
        ArrayList<BasketItem> basketItems = new ArrayList<>();

        for (Map.Entry<UUID, Integer> entry : basket.entrySet()) {
            UUID productId = entry.getKey();
            int count = entry.getValue();

            Product product = storageService.getProductById(productId)
                    .orElseThrow(() -> new IllegalArgumentException("Продукт не найден: " + productId));

            basketItems.add(new BasketItem(product, count));
        }

        return new UserBasket(basketItems);
    }
}
