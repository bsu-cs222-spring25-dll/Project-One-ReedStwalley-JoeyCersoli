package edu.bsu.cs;

import com.jayway.jsonpath.JsonPath;
import net.minidev.json.JSONArray;

import java.net.URL;

public class ErrorHandler {
    public void checkEmptyRequest(String input){
        if(input.isEmpty()){
            System.err.print("You did not provide an article.\n");
        }
    }
    public void checkConnection(URL url){
        try {
            url.openConnection().connect();
        }
        catch (Exception NetworkError){
            System.err.print("There was a network error; could not connect to the article");
        }
    }
    public void checkIfMissingArticle(String jsonString){
        try {
            JSONArray checkForMissing = JsonPath.read(jsonString, "$..missing");
            if (!checkForMissing.isEmpty()) {
                System.err.println("The article you were looking for doesn't exist.");
            }
        } catch (Exception e) {
            System.err.println("Error while checking for missing articles: " + e.getMessage());
        }
    }

}
