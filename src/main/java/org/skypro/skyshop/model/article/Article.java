package org.skypro.skyshop.model.article;

import org.skypro.skyshop.model.search.Searchable;

import java.util.Objects;
import java.util.UUID;

public class Article implements Searchable {

    private final String tittle;
    private final String text;
    private final UUID id;

    public Article(String tittle, String text, UUID id) {
        this.tittle = tittle;
        this.text = text;
        this.id = id;
    }

    @Override
    public String toString() {
        return tittle + '\n' + text;
    }

    @Override
    public String getSearchTerm() {

        return this + " " + getTypeContent();
    }

    @Override
    public String getTypeContent() {
        return "ARTICLE";
    }

    @Override
    public String getStringRepresentation() {

        return tittle + "\nТип - ARTICLE";
    }

    @Override
    public UUID getId(){

        return id;
    }


    public String getTittle() {
        return tittle;
    }
    public String getText() {
        return text;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Article article = (Article) o;
        return Objects.equals(tittle, article.tittle) && Objects.equals(text, article.text);
    }

    @Override
    public int hashCode() {
        return Objects.hash(tittle, text);
    }
}
