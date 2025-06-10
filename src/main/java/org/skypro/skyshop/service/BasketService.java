package org.skypro.skyshop.service;

import org.skypro.skyshop.model.basket.BasketItem;
import org.skypro.skyshop.model.basket.ProductBasket;
import org.skypro.skyshop.model.basket.UserBasket;
import org.skypro.skyshop.model.product.Product;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;
@Service
public class BasketService {
    private final ProductBasket productBasket;
    private final StorageService storageService;
    @Autowired
    BasketService(ProductBasket productBasket, StorageService storageService) {
        this.productBasket = productBasket;
        this.storageService = storageService;
    }

    public void addProduct(UUID id) {
        if (!storageService.getProductById(id).isPresent()) {
            throw new IllegalArgumentException();
        } else {
            productBasket.addProduct(id);
        }
    }

    public UserBasket getUserBasket() {
        ArrayList<BasketItem> basketItems = new ArrayList<>();

        for (Map.Entry<UUID, Integer> entry : productBasket.getBasket().entrySet()) {
            UUID productId = entry.getKey();
            int count = entry.getValue();

            Product product = storageService.getProductById(productId)
                    .orElseThrow(() -> new IllegalArgumentException("Продукт не найден: " + productId));

            basketItems.add(new BasketItem(product, count));
        }

        return new UserBasket(basketItems);
    }
}
