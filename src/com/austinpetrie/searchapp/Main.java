package com.austinpetrie.searchapp;

import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.ScrollBar;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;

import java.io.IOException;

public class Main extends Application {

    @Override
    public void start(Stage primaryStage) throws Exception {

        // Initialize Search class
        Search search = new Search();

        Parent root = FXMLLoader.load(getClass().getResource("mainapp.fxml"));
        primaryStage.setTitle("EasySearch");
        primaryStage.setAlwaysOnTop(true);

        // Search Bar Scene
        GridPane grid = new GridPane();
        grid.setAlignment(Pos.CENTER);
        grid.setHgap(10);
        grid.setVgap(10);
        grid.setPadding(new Insets(25, 25, 25, 25));
        Scene searchScene = new Scene(grid, 300, 275);

        // Website list dropdown menu
        ChoiceBox<String> dropdownMenu = new ChoiceBox<String>();
        dropdownMenu.getItems().addAll("Wikipedia", "Google", "StackOverflow", "YouTube", "GitHub");
        // set default dropdown item
        dropdownMenu.setValue("Wikipedia");

        // Add all the components to the grid
        TextField searchInputField = new TextField();
        grid.add(searchInputField, 0, 1);

        Button searchButton = new Button("Search");
        grid.add(searchButton, 0, 2);

        grid.add(dropdownMenu, 1, 1);

        primaryStage.setScene(searchScene);
        primaryStage.show();

        // Search for results and set new scene
        searchButton.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent e) {
                try {
                    String input = searchInputField.getText();
                    if(!input.isEmpty()) {
                        String website = dropdownMenu.getValue();
                        search.parseInput(input, website);
                        if(website.equals("Wikipedia"))
                            primaryStage.setScene(search.getResultSearchScene());
                    }
                } catch(IOException exception) {
                    System.out.println("Invalid input");
                }
            }
        });

        /*
        WebView webview = new WebView();
        webview.getEngine().load(
                "https://www.youtube.com/embed/xXpPweAooeE?autoplay=1"
        );
        webview.setPrefSize(640, 390);
        */

    }

    public static void main(String[] args) {
        launch(args);
    }

}
