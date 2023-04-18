package com.example.model;

import java.util.Date;

// import java.time.LocalDateTime;

public class Article {
    private String title;    
    private String link;     
    private String imgUrl;   
    private String body;     
    private Date publishDate;  
    

    public Article() {
    }

    // construtor com todos os campos obrigatórios
    public Article(String title, String link, String imgUrl, String body,  Date publishDate) {
        this.title = title;
        this.link = link;
        this.imgUrl = imgUrl;
        this.body = body;
        this.publishDate = publishDate;
    }

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

    public Date getPublishDate() {
        return publishDate;
    }

    public void setPublishDate(Date publishDate) {
        this.publishDate = publishDate;
    }

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
