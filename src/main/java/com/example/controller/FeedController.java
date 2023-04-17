package com.example.controller;

import com.example.persistence.FeedDAO;
import com.example.model.Feed;
import com.example.service.FeedService;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import redis.clients.jedis.Jedis;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;

public class FeedController {
    FeedService feedService = new FeedService(null);

    @GetMapping("/feeds")
    public List<Feed> getAllFeeds() {
        return feedService.getAllFeeds();
    }

    @GetMapping("/feeds/{id}")
    public Feed getFeed(@PathVariable int id) {
        return feedService.getFeedById(id);
    }

    @PostMapping("/feeds")
    public Feed createFeed(@RequestBody Feed feed) {        
        return feedService.createFeed(feed);
    }

    @PutMapping("/feeds/{id}")
    public Feed updateFeed(@PathVariable int id, @RequestBody Feed feed) {
        return feedService.updateFeed(feed);
    }

    @DeleteMapping("/feeds/{id}")
    public void deleteFeed(@PathVariable int id) {
        feedService.deleteFeed(id);
    }

}
