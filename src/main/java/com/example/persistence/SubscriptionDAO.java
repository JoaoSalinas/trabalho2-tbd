package com.example.persistence;

import com.example.rssreader.subscription.Subscription;
import com.google.gson.Gson;
import redis.clients.jedis.Jedis;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class SubscriptionDAO {
    private static final String SUBSCRIPTIONS_KEY = "subscriptions";

    private final Jedis jedis;
    private final Gson gson;

    public SubscriptionDAO() {
        this.jedis = new Jedis("localhost");
        this.gson = new Gson();
    }

    public void addSubscription(Subscription subscription) {
        String subscriptionJson = gson.toJson(subscription);
        jedis.hset(SUBSCRIPTIONS_KEY, String.valueOf(subscription.getId()), subscriptionJson);
    }

    public Subscription getSubscription(int id) {
        String subscriptionJson = jedis.hget(SUBSCRIPTIONS_KEY, String.valueOf(id));
        return gson.fromJson(subscriptionJson, Subscription.class);
    }

    public List<Subscription> getAllSubscriptions() {
        List<String> subscriptionJsonsList = jedis.hvals(SUBSCRIPTIONS_KEY);
        Set<String> subscriptionJsons = new HashSet<>(subscriptionJsonsList);
        List<Subscription> subscriptions = new ArrayList<>();
        for (String subscriptionJson : subscriptionJsons) {
            Subscription subscription = gson.fromJson(subscriptionJson, Subscription.class);
            subscriptions.add(subscription);
        }
        return subscriptions;
    }

    public void updateSubscription(Subscription subscription) {
        addSubscription(subscription);
    }

    public void deleteSubscription(int id) {
        jedis.hdel(SUBSCRIPTIONS_KEY, String.valueOf(id));
    }
}
