package edu.bsu.cs;

import java.io.IOException;
import java.net.URL;
import java.net.URLConnection;
import java.net.URLEncoder;

public class Sorting {
    public String reverseChronological(URLConnection connection) throws IOException {
        Wiki wiki = new Wiki();
        String data = wiki.readJsonAsStringFrom(connection);
        System.out.println(data);
        return "";
    }
    public String listChanges(){
        return "";
    }
    public String sortChanges(){
        return "";
    }
}
