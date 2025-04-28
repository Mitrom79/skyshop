package org.skypro.skyshop.service;

import org.skypro.skyshop.model.article.Article;
import org.skypro.skyshop.model.product.DiscountedProduct;
import org.skypro.skyshop.model.product.FixPriceProduct;
import org.skypro.skyshop.model.product.Product;
import org.skypro.skyshop.model.product.SimpleProduct;
import org.skypro.skyshop.model.search.Searchable;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

@Service
public class StorageService {
    private final Map<UUID, Product> productStorage;
    private final Map<UUID, Article> articleStorage;

    public StorageService(Map<UUID, Product> productStorage, Map<UUID, Article> articleStorage) {
        this.productStorage = productStorage;
        this.articleStorage = articleStorage;

        fillInTheTestData();
        System.out.println(productStorage.values());
    }

    public Map<UUID, Product> getProductStorage() {
        return productStorage;
    }

    public Map<UUID, Article> getArticleStorage() {
        return articleStorage;
    }

    private void fillInTheTestData(){
        SimpleProduct product1 = new SimpleProduct("Чипсы", UUID.randomUUID(), 100);
        DiscountedProduct product2 = new DiscountedProduct("Молоко", UUID.randomUUID(), 50, 10);
        DiscountedProduct product3 = new DiscountedProduct("Хлеб", UUID.randomUUID(), 90, 5);
        SimpleProduct product3_2 = new SimpleProduct("Хлеб", UUID.randomUUID(), 90);
        FixPriceProduct product4 = new FixPriceProduct("Печенье", UUID.randomUUID());
        FixPriceProduct product5 = new FixPriceProduct("Кола", UUID.randomUUID());
        SimpleProduct product6 = new SimpleProduct("Кола", UUID.randomUUID(), 200);

        productStorage.put(product1.getId(), product1);
        productStorage.put(product2.getId(), product2);
        productStorage.put(product3.getId(), product3);
        productStorage.put(product3_2.getId(), product3_2);
        productStorage.put(product4.getId(), product4);
        productStorage.put(product5.getId(), product5);
        productStorage.put(product6.getId(), product6);

        Article article1 = new Article("Название статьи 1", "Текст статьи 1",  UUID.randomUUID());
        Article article2 = new Article("Статья", "Текст статьи 2",  UUID.randomUUID());
        Article article3 = new Article("Название статьи 33", "Текст статьи 3 текст текст",  UUID.randomUUID());
        Article article4 = new Article("Название статьи", "Текст статьи 4",  UUID.randomUUID());
        Article article5 = new Article("Название оо", "Текст статьи 4",  UUID.randomUUID());
        Article article6 = new Article("Название ии", "Текст статьи 4",  UUID.randomUUID());

        articleStorage.put(article1.getId(), article1);
        articleStorage.put(article2.getId(), article2);
        articleStorage.put(article3.getId(), article3);
        articleStorage.put(article4.getId(), article4);
        articleStorage.put(article5.getId(), article5);
        articleStorage.put(article6.getId(), article6);
    }

    public Map<UUID, Searchable> getSearchables() {
        Map<UUID, Searchable> searchables = new HashMap<>();
        int i = 0;
        for (Product product : productStorage.values()) {
            searchables.put(product.getId(), product);
            i++;
        }
        for (Article article : articleStorage.values()) {
            searchables.put(article.getId(), article);
            i++;
        }
        return searchables;
    }
}
