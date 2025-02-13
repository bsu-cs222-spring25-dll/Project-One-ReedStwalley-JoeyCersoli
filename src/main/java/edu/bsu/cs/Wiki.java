package edu.bsu.cs;

import com.jayway.jsonpath.JsonPath;
import net.minidev.json.JSONArray;

import java.io.IOException;
import java.net.URL;
import java.net.URLConnection;
import java.net.URLEncoder;
import java.nio.charset.Charset;


public class Wiki {
    public static void main(String[] args) throws IOException {
        Menu menu = new Menu();
        ErrorHandler errorHandler = new ErrorHandler();
        Sorting sorting = new Sorting();
        String request = menu.getInput();
        errorHandler.checkEmptyRequest(request);
        URLConnection connection = connectToWikipedia(request);
        String jsonData = readJsonAsStringFrom(connection);
        JSONArray jsonArray = extractRevisions(jsonData);
        System.out.println(sorting.sortRevisions(jsonArray));
    }


    public static URLConnection connectToWikipedia(String request) throws IOException {
        ErrorHandler errorHandler = new ErrorHandler();
        String encodedUrlString = "https://en.wikipedia.org/w/api.php?action=query&format=json&prop=revisions&titles=" +
                URLEncoder.encode(request, Charset.defaultCharset()) +
                "&rvprop=timestamp|user&rvlimit=21&redirects";
        URL url = new URL(encodedUrlString);
        errorHandler.checkConnection(url);
        URLConnection connection = url.openConnection();
        connection.setRequestProperty("User-Agent",
                "CS222FirstProject/0.1 (reed.stwalley@bsu.edu)");
        connection.connect();
        return connection;
    }

    public static String readJsonAsStringFrom(URLConnection connection) throws IOException {
        return new String(connection.getInputStream().readAllBytes(), Charset.defaultCharset());

    }

    public static JSONArray extractRevisions(String jsonData) {
        if (jsonData.contains("redirects")) {
            String redirectedArticle = JsonPath.read(jsonData, "$.query.redirects[0].to").toString();
            System.out.println("Redirected to " + redirectedArticle);
        }
        return JsonPath.read(jsonData, "$..revisions[*]");
    }

}
