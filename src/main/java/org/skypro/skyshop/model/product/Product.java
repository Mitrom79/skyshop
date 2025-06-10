package org.skypro.skyshop.model.product;


import org.skypro.skyshop.model.search.Searchable;

import java.util.Objects;
import java.util.UUID;

public abstract class Product implements Searchable {
    private String name;
    private final UUID id;


    public Product(String name, UUID id) {
        try{
            if(name.isBlank()) throw new IllegalArgumentException("Имя не может быть пустой строкой");
        } catch (NullPointerException e){
            throw new IllegalArgumentException("Имя не может быть пустой строкой");
        }
        this.name = name;
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public abstract int getPrice();
    public abstract String toString();
    public abstract boolean isSpecial();

    @Override
    public String getTypeContent() {
        return "PRODUCT";
    }

    @Override
    public String getSearchTerm() {
        return getName() + " - " + getTypeContent();
    }

    @Override
    public UUID getId() {
        return id;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Product product = (Product) o;
        return Objects.equals(name, product.name);
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(name);
    }
}
