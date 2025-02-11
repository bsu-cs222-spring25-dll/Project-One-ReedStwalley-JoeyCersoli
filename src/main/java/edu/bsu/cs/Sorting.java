package edu.bsu.cs;

import com.jayway.jsonpath.JsonPath;
import net.minidev.json.JSONArray;

import java.util.ArrayList;
import java.util.List;

public class Sorting {
    public StringBuilder sortRevisions(JSONArray unsortedList)  {
        StringBuilder sortedList = new StringBuilder();
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
        for(int i = 0; i < userList.size(); i++){
            sortedList.append(i+1).append(". ");
            sortedList.append(timestampList.get(i)).append(" ");
            sortedList.append(userList.get(i)).append("\n");
        }
        return sortedList;
    }
}
