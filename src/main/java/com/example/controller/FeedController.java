package com.example.controller;

import com.example.model.Article;
import com.example.model.Feed;
import com.example.persistence.FeedDAO;
import com.example.service.FeedService;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

public class FeedController {
    
    FeedService feedService = new FeedService(new FeedDAO());

    public List<Feed> getAllFeeds() {
        return feedService.getAllFeeds();
    }

    public Feed getFeed(UUID id) {
        return feedService.getFeedById(id);
    }

    public Feed createFeed(Feed feed) {        
        return feedService.createFeed(feed);
    }

    public Feed updateFeed(UUID id, Feed feed) {
        return feedService.updateFeed(feed);
    }

    public void deleteFeed(UUID id) {
        feedService.deleteFeed(id);
    }
    
    public List<Article> getAllArticles(int pageNumber, int pageSize) {
        List<Article> articles = new ArrayList<>();

        feedService.getAllFeeds().forEach(feed -> {
            articles.addAll(feedService.getArticlesFromFeed(feed));
        });
        return feedService.paginateArticles(feedService.orderByPublishedDate(articles), pageNumber, pageSize);
    }

    public Feed getArticles(UUID id, int pageNumber, int pageSize) {
        Feed feed = feedService.getFeedById(id);        
        feed.setArticles(feedService.getArticlesFromFeed(feed, pageNumber, pageSize));
        return feed;        
    }

    

}
