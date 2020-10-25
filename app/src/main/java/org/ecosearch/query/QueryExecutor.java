package org.ecosearch.query;

import org.ecosearch.SearchImagesService;
import org.ecosearch.SearchWebsitesService;

import java.util.List;

public class QueryExecutor {
    private final SearchWebsitesService searchWebsitesService;
    private final SearchImagesService searchImagesService;

    public QueryExecutor(SearchWebsitesService searchWebsitesService, SearchImagesService searchImagesService) {
        this.searchWebsitesService = searchWebsitesService;
        this.searchImagesService = searchImagesService;
    }

    public QueryResults query(String tag) {
        List<String> websitesByTag = searchWebsitesService.searchWebsitesByTag(tag);
        List<byte[]> imagesByTag = searchImagesService.searchImagesByTag(tag);

        return new QueryResults(websitesByTag, imagesByTag);
    }
}