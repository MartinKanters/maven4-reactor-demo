package org.ecosearch;

import org.ecosearch.query.QueryExecutor;
import org.ecosearch.query.QueryResults;

import java.util.Scanner;

public class CommandLineApp {
    public static void main(String... args) {
        SearchWebsitesService searchWebsitesService = new SearchWebsitesService();
        SearchImagesService searchImagesService = new SearchImagesService();
        QueryExecutor queryExecutor = new QueryExecutor(searchWebsitesService, searchImagesService);

        System.out.println("Query websites and images by tags: ");

        try (Scanner cli = new Scanner(System.in)) {
            String tag = cli.nextLine();
            QueryResults results = queryExecutor.query(tag);

            System.out.println("Websites query: " + results.getWebsites().size() + " results.");
            System.out.println("Image query: " + results.getImages().size() + " results.");
        }
    }
}
