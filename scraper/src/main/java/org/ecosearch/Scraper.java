package org.ecosearch;

import org.ecosearch.storage.WebsiteResultsStorage;
import org.ecosearch.storage.WebsiteUrlStorage;

import java.util.Arrays;
import java.util.List;

public class Scraper {

    private final WebsiteUrlStorage websiteUrlStorage;
    private final WebsiteResultsStorage websiteResultsStorage;

    public Scraper(WebsiteUrlStorage websiteUrlStorage, WebsiteResultsStorage websiteResultsStorage) {
        this.websiteUrlStorage = websiteUrlStorage;
        this.websiteResultsStorage = websiteResultsStorage;
    }

    public void scrapeNextSites() {
        websiteUrlStorage.getNextURLs().stream()
                .limit(100)
                .forEach(url -> websiteResultsStorage.storeWords(url, scrapeUrl(url)));
    }

    private List<String> scrapeUrl(String url) {
        System.out.println("Scraping website: " + url);
        try {
            Thread.sleep(100L);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
        return Arrays.asList("Most", "common", "words", "on", "website");
    }
}
