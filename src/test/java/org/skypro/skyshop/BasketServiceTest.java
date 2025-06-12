package org.skypro.skyshop;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.skypro.skyshop.exception.NoSuchProductException;
import org.skypro.skyshop.model.basket.ProductBasket;
import org.skypro.skyshop.model.basket.UserBasket;
import org.skypro.skyshop.model.product.Product;
import org.skypro.skyshop.model.product.SimpleProduct;
import org.skypro.skyshop.service.BasketService;
import org.skypro.skyshop.service.StorageService;

import java.util.Collections;
import java.util.Map;
import java.util.Optional;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class BasketServiceTest {
    @Mock
    private StorageService storageService;
    @Mock
    private Map<UUID, Integer> basket;

    @InjectMocks
    private BasketService basketService;

    @Test
    void testAddNonExistentProductThrowsException() {
        UUID nonExistentProductId = UUID.randomUUID();
        when(storageService.getProductById(nonExistentProductId)).thenReturn(Optional.empty());

        assertThrows(NoSuchProductException.class, () -> basketService.addProduct(nonExistentProductId),
                "должно быть выброшено NoSuchProductException");
    }

    @Test
    void testAddExistingProductCallsBasketAddProduct() {
        UUID productId = UUID.randomUUID();
        Product product = new SimpleProduct("Product", productId, 100);
        when(storageService.getProductById(productId)).thenReturn(Optional.of(product));

        basketService.addProduct(productId);

        verify(basket).merge(productId, 1, Integer::sum);
    }

    @Test
    void testGetUserBasketReturnsEmptyBasket() {
        when(basket.entrySet()).thenReturn(Collections.emptySet());

        UserBasket userBasket = basketService.getUserBasket();

        assertTrue(userBasket.getBasket().isEmpty(), "должно быть пусто");
    }

    @Test
    void testGetUserBasketReturnsFilledBasket() {
        UUID productId1 = UUID.randomUUID();
        UUID productId2 = UUID.randomUUID();

        Product product1 = new SimpleProduct("Product1", productId1, 100);
        Product product2 = new SimpleProduct("Product2", productId2, 200);

        when(basket.entrySet()).thenReturn(Map.of(productId1, 2, productId2, 1).entrySet());
        when(storageService.getProductById(productId1)).thenReturn(Optional.of(product1));
        when(storageService.getProductById(productId2)).thenReturn(Optional.of(product2));

        UserBasket userBasket = basketService.getUserBasket();

        assertEquals(2, userBasket.getBasket().size(), "в корзине должно быть 2 товара");
        assertTrue(userBasket.getBasket().stream().anyMatch(item ->
                        item.getProduct().equals(product1) && item.getCount() == 2),
                "корзина должна содержать Product1 с количеством 2");
        assertTrue(userBasket.getBasket().stream().anyMatch(item ->
                        item.getProduct().equals(product2) && item.getCount() == 1),
                "корзина должна содержать Product2 с количеством 2");
    }
}
