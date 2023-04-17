package com.example.service;

import java.util.List;

import com.example.model.Article;
import com.example.model.Feed;
import com.example.persistence.FeedDAO;



public class FeedService {

    private FeedDAO feedDAO;

    public FeedService(FeedDAO feedDAO) {
        this.feedDAO = feedDAO;
    }

    public List<Feed> getAllFeeds() {
        return feedDAO.getAllFeeds();
    }

    public Feed getFeedById(int id) {
        return feedDAO.getFeed(id);
    }

    public Feed createFeed(Feed feed) {
        feedDAO.addFeed(feed);
        return feed;
    }

    public Feed updateFeed(Feed feed) {
        feedDAO.updateFeed(feed);
        return feed;
    }

    public void deleteFeed(int id) {
        feedDAO.deleteFeed(id);
    }

    public void changeFeedCategory(int id, String newCategory) {
        Feed feed = feedDAO.getFeed(id);
        feed.setCategory(newCategory);
        feedDAO.updateFeed(feed);
    }

    public List<Article> getArticlesFromFeed(int id, int pageNumber, int pageSize) {
        Feed feed = feedDAO.getFeed(id);
        List<Article> articles = feed.getArticles();
        return paginateArticles(articles, pageNumber, pageSize);
    }

    private List<Article> paginateArticles(List<Article> articles, int pageNumber, int pageSize) {
        int startIndex = (pageNumber - 1) * pageSize;
        int endIndex = Math.min(startIndex + pageSize, articles.size());
        return articles.subList(startIndex, endIndex);
    }
}
