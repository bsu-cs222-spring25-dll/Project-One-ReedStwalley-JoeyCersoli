package edu.bsu.cs;

import com.jayway.jsonpath.JsonPath;
import net.minidev.json.JSONArray;

import java.util.ArrayList;
import java.util.List;

public class Sorting {
    public List<String> sortedRevisions(JSONArray unsortedList)  {
        List<String> sortedRevisions = new ArrayList<>();
        ErrorHandler errorhandler = new ErrorHandler();
        JSONArray users = JsonPath.read(unsortedList, "$..revisions[*].user");
        JSONArray timestamps = JsonPath.read(unsortedList, "$..revisions[*].timestamp");
        List<String> timestampList = new ArrayList<>();
        List<String> userList = new ArrayList<>();
        for (Object timestamp : timestamps) {
            timestampList.add((String) timestamp);
        }
        for (Object user : users) {
            userList.add((String) user);
        }
        for(int i = 1; i <= unsortedList.size(); i++){
            sortedRevisions.add(i + ". ");
            
        }
        return sortedRevisions;
    }
}
