package edu.bsu.cs;

import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.net.URLConnection;
import java.net.URLEncoder;
import java.nio.charset.Charset;
import java.util.Objects;

import org.junit.jupiter.api.Assertions;

import javax.swing.*;

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
        URLConnection connection = connectToWikipedia("Soup");
        String jsonData = readJsonAsStringFrom(connection);
        printRawJson(jsonData);
        Assertions.assertNotNull(jsonData);
    }
    @org.junit.jupiter.api.Test
    public void testEmptyError(){
        ErrorHandler errorHandler = new ErrorHandler();
        String request = "";
        errorHandler.checkEmptyRequest(request);
        Assertions.assertTrue(request == "");
    }
    @org.junit.jupiter.api.Test
    public void testNoConnection() {
        ErrorHandler errorHandler = new ErrorHandler();
        URL url = null;
        errorHandler.checkConnection(url);
    }
    public static URLConnection connectToWikipedia(String request) throws IOException {
        String encodedUrlString = "https://en.wikipedia.org/w/api.php?action=query&format=json&prop=revisions&titles=" +
                URLEncoder.encode(request, Charset.defaultCharset()) +
                "&rvprop=timestamp|user&rvlimit=4&redirects";
        URL url = new URL(encodedUrlString);
        URLConnection connection = url.openConnection();
        connection.setRequestProperty("User-Agent",
                "CS222FirstProject/0.1 (reed.stwalley@bsu.edu)");
        connection.connect();
        return connection;
    }
    public static String readJsonAsStringFrom(URLConnection connection) throws IOException {
        return new String(connection.getInputStream().readAllBytes(), Charset.defaultCharset());
    }

    public static void printRawJson(String jsonData) {
        System.out.println(jsonData);
    }
}