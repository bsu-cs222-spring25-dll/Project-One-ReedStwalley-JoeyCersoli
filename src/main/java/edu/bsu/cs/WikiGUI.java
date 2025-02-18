package edu.bsu.cs;

import java.awt.*;
import java.io.IOException;
import java.net.URLConnection;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;


public class WikiGUI {
    public static void main(String[] args) {
        launch(args);
    }
    private final Button searchButton = new Button("Search");
    private final TextField inputField = new TextField();
    private final TextField outputField = new TextField();

    @Override
    public void start(Stage primaryStage) {
        outputField.setEditable(false);
        configure(primaryStage);
        configureSearchButton();
    }

    private void configure(Stage stage){
        stage.setTitle("Wikipedia Search Box");
        stage.setScene(new Scene(createRoot()));
        stage.sizeToScene();
        stage.show();
    }
    private Pane createRoot() {
        VBox root = new VBox();
        root.getChildren().addAll( //
                new Label("Search:"), //
                inputField, //
                searchButton, //
                new Label("Results:"),//
                outputField);
        return root;
    }

    private void configureSearchButton() {
        searchButton.setOnAction(event -> searcherWIKIList());
    }

    public void searcherWIKIList() throws IOException {
        String input = inputField.getText();
        Wiki wiki = new Wiki();
        ErrorHandler errorHandler = new ErrorHandler();
        Sorting sorting = new Sorting();
        URLConnection connection = wiki.connectToWikipedia(input);
        String jsonData = wiki.readJsonAsStringFrom(connection);
        boolean noError = errorHandler.checkIfMissingArticle(jsonData);
        if(noError){
            outputField.setText(sorting.sortRevisions(jsonData).toString());
        }
    }
}
