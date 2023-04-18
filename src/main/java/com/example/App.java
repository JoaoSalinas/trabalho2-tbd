package com.example;

import com.example.controller.FeedController;
import com.example.model.Feed;
import com.google.gson.Gson;

import jodd.net.HttpStatus;

import static spark.Spark.*;

import java.util.UUID;

import javax.persistence.Id;

public class App {

    public static void main(String[] args) {     
        Gson gson = new Gson();        

        FeedController feedController = new FeedController();



        path("/api", () -> {
            get("/feeds", (request, reponse) -> {
                return gson.toJson(feedController.getAllFeeds());
            });
            get("/feed/:id", (request, reponse) -> {
                UUID id = UUID.fromString(request.params("id"));
                return gson.toJson(feedController.getFeed(id));
            });

            post("/feed", (request, reponse) -> {
                Feed feed = gson.fromJson(request.body(), Feed.class);
                return gson.toJson(feedController.createFeed(feed));            
            });
            put("/feed/:id", (request, reponse) -> {
                UUID id = UUID.fromString(request.params("id"));
                Feed feed = gson.fromJson(request.body(), Feed.class);
                feed.setId(id);
                return gson.toJson(feedController.updateFeed(id, feed));            
            });
            put("/feed/change-category/:id", (request, reponse) -> {
                UUID id = UUID.fromString(request.params("id"));
                Feed feed = gson.fromJson(request.body(), Feed.class);
                feed.setId(id);
                return gson.toJson(feedController.updateFeed(id, feed));            
            });
            delete("/feed/:id", (request, response) -> {
                feedController.deleteFeed(UUID.fromString(request.params("id")));
                return response.status();
            });


            get("/articles", (request, response) -> {
                int pageNumber = Integer.parseInt(request.queryParams("pageNumber"));
                int pageSize = Integer.parseInt(request.queryParams("pageSize"));

                return gson.toJson(feedController.getAllArticles(pageNumber, pageSize));
            });


            get("/articles/:id", (request, response) -> {
                int pageNumber = Integer.parseInt(request.queryParams("pageNumber"));
                int pageSize = Integer.parseInt(request.queryParams("pageSize"));
                
                return gson.toJson(feedController.getArticles(UUID.fromString(request.params("id")), pageNumber, pageSize));
            });


        });


    }

}
