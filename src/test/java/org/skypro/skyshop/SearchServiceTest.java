package org.skypro.skyshop;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.skypro.skyshop.model.article.Article;
import org.skypro.skyshop.model.product.SimpleProduct;
import org.skypro.skyshop.model.search.SearchResult;
import org.skypro.skyshop.model.search.Searchable;
import org.skypro.skyshop.service.SearchService;
import org.skypro.skyshop.service.StorageService;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.Collections;
import java.util.Map;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class SearchServiceTest {
    @Mock
    private StorageService storageService;

    @InjectMocks
    private SearchService searchService;

    @Test
    public void testSearchWhenStorageIsEmpty() {
        when(storageService.getSearchables()).thenReturn(Collections.emptyMap());
        Map<UUID, SearchResult> results = searchService.search("PRODUCT");

        assertTrue(results.isEmpty(), "Должно быть пусто");
    }

    @Test
    public void testSearchWhenPatternNotFound() {
        Map<UUID, Searchable> searchables = Map.of(
                UUID.randomUUID(), new SimpleProduct("Test1", UUID.randomUUID(), 100),
                UUID.randomUUID(), new Article("Test2", "Content", UUID.randomUUID())
        );
        when(storageService.getSearchables()).thenReturn(searchables);

        Map<UUID, SearchResult> results = searchService.search("NoMatch");

        assertTrue(results.isEmpty(), "Search results should be empty when no objects match");
    }

    @Test
    void testSearchWhenMatchingObjectsExist() {
        UUID matchingId = UUID.randomUUID();
        Map<UUID, Searchable> searchables = Map.of(
                matchingId, new SimpleProduct("Match", matchingId, 100),
                UUID.randomUUID(), new Article("Other", "Content", UUID.randomUUID())
        );
        when(storageService.getSearchables()).thenReturn(searchables);

        Map<UUID, SearchResult> results = searchService.search("Match");

        assertEquals(1, results.size(), "должен содержать 1 объект");
        assertTrue(results.containsKey(matchingId), "совпадение должно быть в результате");
    }
}
