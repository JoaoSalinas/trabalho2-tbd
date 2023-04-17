package com.example.model;

import java.time.LocalDateTime;

public class Article {
    private String title;    // título do artigo
    private String link;     // link para o artigo
    private String imgUrl;   // URL da imagem do artigo (opcional)
    private String body;     // corpo do artigo (opcional)
    private LocalDateTime publishDate;  // data de publicação do artigo
    // outros campos que podem ser úteis para a sua aplicação, como autor, categoria, etc.

    // construtor vazio
    public Article() {
    }

    // construtor com todos os campos obrigatórios
    public Article(String title, String link, LocalDateTime publishDate) {
        this.title = title;
        this.link = link;
        this.publishDate = publishDate;
    }

    // getters e setters para todos os campos relevantes
    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getLink() {
        return link;
    }

    public void setLink(String link) {
        this.link = link;
    }

    public String getImgUrl() {
        return imgUrl;
    }

    public void setImgUrl(String imgUrl) {
        this.imgUrl = imgUrl;
    }

    public String getBody() {
        return body;
    }

    public void setBody(String body) {
        this.body = body;
    }

    public LocalDateTime getPublishDate() {
        return publishDate;
    }

    public void setPublishDate(LocalDateTime publishDate) {
        this.publishDate = publishDate;
    }

    // toString para imprimir o artigo como uma string
    @Override
    public String toString() {
        return "Article{" +
                "title='" + title + '\'' +
                ", link='" + link + '\'' +
                ", imgUrl='" + imgUrl + '\'' +
                ", body='" + body + '\'' +
                ", publishDate=" + publishDate +
                '}';
    }
}
