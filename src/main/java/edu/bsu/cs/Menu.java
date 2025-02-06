package edu.bsu.cs;

import java.net.URLConnection;
import java.util.Scanner;

public class Menu {
    public static void main(String[] args) {
        Menu menu = new Menu();
        String searchTerm = menu.getInput();
    }
    public String getInput(){
        Scanner scnr = new Scanner(System.in);
        System.out.println("Enter search request: ");
        String request = scnr.nextLine();
        return request;
    }
    /*public String displayOutput(){
        Wiki wiki = new Wiki();

    }

     */

}

