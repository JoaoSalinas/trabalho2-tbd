package com.example.model;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

public class Feed {
    private UUID id;
    private String name;
    private String url;
    private String category;
    private List<Article> articles;

    public Feed(String name, String url, String category) {
        this.id = UUID.randomUUID();
        this.name = name;
        this.url = url;
        this.category = category;
        this.articles = new ArrayList<>();
    }

    public Feed() {
        this.id = UUID.randomUUID();
    }

    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public String getUrl() {
        return url;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public List<Article> getArticles() {
        return articles;
    }

    public void setArticles(List<Article> articles) {
        this.articles = articles;
    }

    public void addArticle(Article article) {
        articles.add(article);
    }

    public void removeArticle(Article article) {
        articles.remove(article);
    }
}
