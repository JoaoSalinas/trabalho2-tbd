package com.example.service;

import java.util.List;

import com.example.model.Article;
import com.example.persistence.SubscriptionDAO;
import com.example.rssreader.subscription.Subscription;

public class SubscriptionService {

    private SubscriptionDAO subscriptionDAO;

    public SubscriptionService(SubscriptionDAO subscriptionDAO) {
        this.subscriptionDAO = subscriptionDAO;
    }

    public List<Subscription> getAllSubscriptions() {
        return subscriptionDAO.getAllSubscriptions();
    }

    public Subscription getSubscriptionById(int id) {
        return subscriptionDAO.getSubscription(id);
    }

    public Subscription createSubscription(Subscription subscription) {
        subscriptionDAO.addSubscription(subscription);
        return subscription;
    }

    public Subscription updateSubscription(Subscription subscription) {
        subscriptionDAO.updateSubscription(subscription);
        return subscription;
    }

    public void deleteSubscription(int id) {
        subscriptionDAO.deleteSubscription(id);
    }

    public void changeSubscriptionCategory(int id, String newCategory) {
        Subscription subscription = subscriptionDAO.getSubscription(id);
        subscription.setCategory(newCategory);
        subscriptionDAO.updateSubscription(subscription);
    }

    public List<Article> getArticlesFromSubscription(int id, int pageNumber, int pageSize) {
        Subscription subscription = subscriptionDAO.getSubscription(id);
        List<Article> articles = subscription.getArticles();
        return paginateArticles(articles, pageNumber, pageSize);
    }

    private List<Article> paginateArticles(List<Article> articles, int pageNumber, int pageSize) {
        int startIndex = (pageNumber - 1) * pageSize;
        int endIndex = Math.min(startIndex + pageSize, articles.size());
        return articles.subList(startIndex, endIndex);
    }
}
