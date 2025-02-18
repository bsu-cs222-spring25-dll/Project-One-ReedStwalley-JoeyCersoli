package edu.bsu.cs;

import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.net.URLConnection;
import java.nio.charset.Charset;
import java.util.Objects;

import org.junit.jupiter.api.Assertions;


import static edu.bsu.cs.Wiki.connectToWikipedia;
import static edu.bsu.cs.Wiki.readJsonAsStringFrom;

public class Test {

    @org.junit.jupiter.api.Test
    public void testAccessToJsonFile() throws IOException {
        String jsonData = readSampleFileAsString();
        Assertions.assertNotNull(jsonData);
    }

    private String readSampleFileAsString() throws NullPointerException, IOException {
        try (InputStream sampleFile = Thread.currentThread().getContextClassLoader()
                .getResourceAsStream("sample.json")) {
            return new String(Objects.requireNonNull(sampleFile).readAllBytes(), Charset.defaultCharset());
        }
    }
    @org.junit.jupiter.api.Test
    public void testSoup() throws IOException{
        Sorting sorting = new Sorting();
        URLConnection connection = connectToWikipedia("Soup");
        String jsonData = readJsonAsStringFrom(connection);
        sorting.sortRevisions(jsonData);
        Assertions.assertNotNull(jsonData);
    }
    @org.junit.jupiter.api.Test
    public void testNoInputError(){
        ErrorHandler errorHandler = new ErrorHandler();
        String request = "";
        errorHandler.checkEmptyRequest(request);
        Assertions.assertSame("", request);
    }
    @org.junit.jupiter.api.Test
    public void testNoConnectionError() {
        ErrorHandler errorHandler = new ErrorHandler();
        URL url = null;
        errorHandler.checkConnection(url);
        Assertions.assertNull(url);
    }
    @org.junit.jupiter.api.Test
    public void testMissingArticleError(){
        ErrorHandler errorHandler = new ErrorHandler();
        String request = "SJDFJIVKnkelispal";
        errorHandler.checkIfMissingArticle(request);
    }
    @org.junit.jupiter.api.Test
    public void testSorting() throws IOException {
        Sorting sorting = new Sorting();
        String request = "Soup";
        connectToWikipedia(request);
        sorting.sortRevisions(String.valueOf(connectToWikipedia(request)));
    }
}