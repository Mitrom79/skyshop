package org.skypro.skyshop.model.search;

import java.util.UUID;

public interface Searchable {
    String getSearchTerm();

    String getTypeContent();


    default String getStringRepresentation() {
        return getSearchTerm();
    }

    UUID getId();
}
