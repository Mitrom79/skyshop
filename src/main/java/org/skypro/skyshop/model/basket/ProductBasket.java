package org.skypro.skyshop.model.basket;

import java.util.*;

public class ProductBasket {
    private final Map<UUID, Integer> basket;

    public ProductBasket(Map<UUID, Integer> basket) {
        this.basket = basket;
    }

    public void addProduct(UUID productId) {
        if (basket.containsKey(productId)) {
            basket.put(productId, basket.get(productId) + 1);
        } else {
            basket.put(productId, 1);
        }
    }

    public Map<UUID, Integer> getBasket() {
        return Collections.unmodifiableMap(basket);
    }
}
