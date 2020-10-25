package org.ecosearch.query;

import org.ecosearch.SearchImagesService;
import org.ecosearch.SearchWebsitesService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

class QueryExecutorTest {

    private QueryExecutor queryExecutor;

    @BeforeEach
    void before() {
        SearchWebsitesService mockWebsitesService = new SearchWebsitesService() {
            @Override
            public List<String> searchWebsitesByTag(String tag) {
                if (tag.contains("cat")) {
                    return Arrays.asList("https://http.cat", "https://en.wikipedia.org/wiki/Cat");
                } else {
                    return Collections.emptyList();
                }
            }
        };

        SearchImagesService mockImagesService = new SearchImagesService() {
            @Override
            public List<byte[]> searchImagesByTag(String tag) {
                if (tag.contains("cat")) {
                    return Collections.singletonList(new byte[]{});
                } else {
                    return Collections.emptyList();
                }
            }
        };

        queryExecutor = new QueryExecutor(mockWebsitesService, mockImagesService);
    }

    @Test
    void catReturnsResults() {
        QueryResults results = queryExecutor.query("cat");

        assertEquals(2, results.getWebsites().size());
        assertEquals(1, results.getImages().size());
    }

    @Test
    void somethingElseReturnsNothing() {
        QueryResults results = queryExecutor.query("something else");

        assertTrue(results.getWebsites().isEmpty());
        assertTrue(results.getImages().isEmpty());
    }
}