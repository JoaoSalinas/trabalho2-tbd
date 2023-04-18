package com.example.service;

import java.net.URL;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import com.example.model.Article;
import com.rometools.rome.feed.synd.SyndContent;
import com.rometools.rome.feed.synd.SyndEnclosure;
import com.rometools.rome.feed.synd.SyndEntry;
import com.rometools.rome.feed.synd.SyndFeed;
import com.rometools.rome.io.SyndFeedInput;
import com.rometools.rome.io.XmlReader;

public class RssReader {

    public static List<Article> getArticlesFromRssFeed(String rssUrl) {

        List<Article> articles = new ArrayList<Article>();
        try {
            URL feedUrl = new URL(rssUrl);
            SyndFeedInput input = new SyndFeedInput();
            SyndFeed feed = input.build(new XmlReader(feedUrl));
            List<SyndEntry> entries = feed.getEntries();
            for (SyndEntry entry : entries) {
                String title = entry.getTitle();
                String link = entry.getLink();
                String imageUrl = "";
                String body = "";
                Date date = entry.getPublishedDate();

                if (entry.getEnclosures() != null && !entry.getEnclosures().isEmpty()) {
                    for (SyndEnclosure enclosure : entry.getEnclosures()) {
                        if (enclosure.getType().startsWith("image/")) {
                            imageUrl = enclosure.getUrl();
                        }
                    }
                }

                if (entry.getContents() != null && !entry.getContents().isEmpty()) {
                    for (SyndContent getBody : entry.getContents()) {
                        body += getBody.getValue();
                    }
                }

                if (body.isBlank() || body.isEmpty()) {
                    body = entry.getDescription().getValue();
                }

                articles.add(new Article(title, link, imageUrl, body, date));
            }
        } catch (Exception ex) {
            ex.printStackTrace();
        }

        return articles;
    }

}
