package com.example.service;

import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.UUID;

import org.eclipse.jetty.server.RequestLog.Collection;

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

    public Feed getFeedById(UUID id) {
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

    public void deleteFeed(UUID id) {
        feedDAO.deleteFeed(id);
    }

    public void changeFeedCategory(UUID id, String newCategory) {
        Feed feed = feedDAO.getFeed(id);
        feed.setCategory(newCategory);
        feedDAO.updateFeed(feed);
    }

    public List<Article> getArticlesFromFeed(Feed feed) {
        return RssReader.getArticlesFromRssFeed(feed.getUrl());
    }

    public List<Article> getArticlesFromFeed(Feed feed, int pageNumber, int pageSize) {
        List<Article> articles = RssReader.getArticlesFromRssFeed(feed.getUrl());
        return paginateArticles(articles, pageNumber, pageSize);
    }

    public List<Article> paginateArticles(List<Article> articles, int pageNumber, int pageSize) {
        int startIndex = (pageNumber - 1) * pageSize;
        int endIndex = Math.min(startIndex + pageSize, articles.size());
        return articles.subList(startIndex, endIndex);
    }

    public List<Article> orderByPublishedDate(List<Article> articles) {
        Collections.sort(articles,
                new Comparator<Article>() {
                    public int compare(Article a1, Article a2) {
                        return a2.getPublishDate().compareTo(a1.getPublishDate());
                    }
                });
        return articles;
    }
}
