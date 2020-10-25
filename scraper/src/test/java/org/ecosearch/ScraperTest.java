package org.ecosearch;

import org.ecosearch.storage.WebsiteResultsStorage;
import org.ecosearch.storage.WebsiteUrlStorage;
import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

class ScraperTest {

    @Test
    void scrapesAllNextSites() {
        Map<String, List<String>> tagsBySiteStore = new HashMap<>();
        List<String> nextWebsites = Arrays.asList(
                "https://www.google.com",
                "https://www.tweakers.net",
                "https://www.oracle.com"
        );
        Scraper scraper = setUpService(tagsBySiteStore, nextWebsites);

        scraper.scrapeNextSites();

        assertEquals(tagsBySiteStore.keySet(), new HashSet<>(nextWebsites), "Expected all next sites to have results");
    }

    @Test
    void doesNotStoreAnythingWhenNextSiteListIsEmpty() {
        Map<String, List<String>> tagsBySiteStore = new HashMap<>();
        List<String> nextWebsites = Collections.emptyList();
        Scraper scraper = setUpService(tagsBySiteStore, nextWebsites);

        scraper.scrapeNextSites();

        assertTrue(tagsBySiteStore.isEmpty());
    }

    @Test
    void doesNotScrapeMoreThan100SitesAtOnce() {
        Map<String, List<String>> tagsBySiteStore = new HashMap<>();
        List<String> nextWebsites = IntStream.range(0, 120).mapToObj(i -> "https://website-" + i + ".com").collect(Collectors.toList());
        Scraper scraper = setUpService(tagsBySiteStore, nextWebsites);

        scraper.scrapeNextSites();

        assertEquals(100, tagsBySiteStore.size(), "Expected the amount of scrapes to be capped at 100");
    }

    private Scraper setUpService(Map<String, List<String>> tagsBySiteStore, List<String> nextWebsites) {
        WebsiteUrlStorage mockWebsiteUrlStorage = () -> nextWebsites;
        WebsiteResultsStorage mockWebsiteResultsStorage = new WebsiteResultsStorage() {
            public void storeWords(String url, List<String> tags) {
                tagsBySiteStore.put(url, tags);
            }

            public void storeImage(byte[] imageBlob, List<String> tags) {
            }
        };

        return new Scraper(mockWebsiteUrlStorage, mockWebsiteResultsStorage);
    }
}
