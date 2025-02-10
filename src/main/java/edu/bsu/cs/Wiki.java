package edu.bsu.cs;

import java.io.IOException;
import java.net.URL;
import java.net.URLConnection;
import java.net.URLEncoder;
import java.nio.charset.Charset;

public class Wiki {
    public static void main(String[] args) throws IOException {
        Menu menu = new Menu();
        Sorting sorting = new Sorting();
        String request = menu.getInput();
        URLConnection connection = connectToWikipedia(request);
        String jsonData = readJsonAsStringFrom(connection);
        Object sortedJSONData = sorting.sortedRevisions(jsonData);
        System.out.println(sortedJSONData);
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
