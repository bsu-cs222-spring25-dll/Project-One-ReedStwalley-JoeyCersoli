package edu.bsu.cs;

import java.util.Scanner;

public class Menu {
    public String getInput(){
        Scanner scnr = new Scanner(System.in);
        System.out.println("Enter search request: ");
        String request = scnr.nextLine();
        return request;
    }

}

