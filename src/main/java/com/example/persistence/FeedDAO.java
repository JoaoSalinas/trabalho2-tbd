package com.example.persistence;

import com.example.model.Feed;
import com.example.model.Article;
import com.google.gson.Gson;
import redis.clients.jedis.Jedis;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class FeedDAO {
    private static final String FEEDS_KEY = "feeds";

    private final Jedis jedis;
    private final Gson gson;

    public FeedDAO() {
        this.jedis = new Jedis("localhost");
        this.gson = new Gson();
    }

    public void addFeed(Feed feed) {
        String feedJson = gson.toJson(feed);
        jedis.hset(FEEDS_KEY, String.valueOf(feed.getId()), feedJson);
    }

    public Feed getFeed(int id) {
        String feedJson = jedis.hget(FEEDS_KEY, String.valueOf(id));
        return gson.fromJson(feedJson, Feed.class);
    }

    public List<Feed> getAllFeeds() {
        List<String> feedJsonsList = jedis.hvals(FEEDS_KEY);
        Set<String> feedJsons = new HashSet<>(feedJsonsList);
        List<Feed> feeds = new ArrayList<>();
        for (String feedJson : feedJsons) {
            Feed feed = gson.fromJson(feedJson, Feed.class);
            feeds.add(feed);
        }
        return feeds;
    }

    public void updateFeed(Feed feed) {
        addFeed(feed);
    }

    public void deleteFeed(int id) {
        jedis.hdel(FEEDS_KEY, String.valueOf(id));
    }
}
