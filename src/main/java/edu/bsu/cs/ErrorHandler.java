package edu.bsu.cs;

import com.jayway.jsonpath.JsonPath;
import net.minidev.json.JSONArray;

import java.net.URL;

public class ErrorHandler {
    public void checkEmptyRequest(String request){
        if(request.isEmpty()){
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
        JSONArray checkForMissing = JsonPath.read(jsonString, "$..missing");
        if(!checkForMissing.isEmpty()){
            System.err.print("The article you were looking for doesn't exist.");
        }
    }
}
