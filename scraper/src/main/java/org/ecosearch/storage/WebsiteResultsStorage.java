package org.ecosearch.storage;

import java.net.URL;
import java.util.List;

public interface WebsiteResultsStorage {
    void storeWords(String url, List<String> tags);
    void storeImage(byte[] imageBlob, List<String> tags);
}
