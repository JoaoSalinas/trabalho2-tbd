package com.example.controller;

import com.example.persistence.SubscriptionDAO;
import com.example.rssreader.subscription.Subscription;
import com.example.service.SubscriptionService;
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

public class SubscriptionController {
    SubscriptionService subscriptionService = new SubscriptionService(null);

    @GetMapping("/subscriptions")
    public List<Subscription> getAllSubscriptions() {
        return subscriptionService.getAllSubscriptions();
    }

    @GetMapping("/subscriptions/{id}")
    public Subscription getSubscriptionById(@PathVariable int id) {
        return subscriptionService.getSubscriptionById(id);
    }

    @PostMapping("/subscriptions")
    public Subscription createSubscription(@RequestBody Subscription subscription) {        
        return subscriptionService.createSubscription(subscription);
    }

    @PutMapping("/subscriptions/{id}")
    public Subscription updateSubscription(@PathVariable int id, @RequestBody Subscription subscription) {
        return subscriptionService.updateSubscription(subscription);
    }

    @DeleteMapping("/subscriptions/{id}")
    public void deleteSubscription(@PathVariable int id) {
        subscriptionService.deleteSubscription(id);
    }

}
