package edu.bsu.cs;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

import java.io.IOException;
public class UI extends Application {
    public static void main(String[] args) {
        launch(args);
    }
    private final Button searchButton = new Button("Search");
    private final TextField inputField = new TextField();
    private final TextField outputField = new TextField();

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
        searchButton.setOnAction(event -> {
            try {
                searcherWIKIList();
                ErrorHandler errorHandler = new ErrorHandler();
                errorHandler.checkEmptyRequest(inputField.getText());
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        });
    }

    public void searcherWIKIList() throws IOException {
        String input = inputField.getText();
        ErrorHandler errorHandler = new ErrorHandler();
        Sorting sorting = new Sorting();
        String jsonData = Wiki.readJsonAsStringFrom(Wiki.connectToWikipedia(input));
        boolean noError = errorHandler.checkIfMissingArticle(jsonData);
        if(noError){
            outputField.setText(sorting.sortRevisions(jsonData).toString());
        }
    }
}
