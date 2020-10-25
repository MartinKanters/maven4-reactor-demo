package org.ecosearch.query;

import java.util.List;

public class QueryResults {
    private final List<String> websites;
    private final List<byte[]> images;

    public QueryResults(List<String> websites, List<byte[]> images) {
        this.websites = websites;
        this.images = images;
    }

    public List<String> getWebsites() {
        return websites;
    }

    public List<byte[]> getImages() {
        return images;
    }
}