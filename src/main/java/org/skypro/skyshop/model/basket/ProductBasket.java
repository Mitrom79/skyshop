package org.skypro.skyshop.model.basket;

import org.springframework.stereotype.Service;
import org.springframework.web.context.annotation.SessionScope;

import java.util.*;

@SessionScope
@Service
public class ProductBasket {

    private final Map<UUID, Integer> basket;

    public ProductBasket(Map<UUID, Integer> basket) {
        basket= new HashMap<>();
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
