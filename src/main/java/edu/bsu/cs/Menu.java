package edu.bsu.cs;

import java.util.Scanner;

public class Menu {
    public String getInput(){
        Scanner scnr = new Scanner(System.in);
        System.out.print("Enter search request: ");
        return scnr.nextLine();
    }

}

