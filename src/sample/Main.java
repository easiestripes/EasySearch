package sample;

import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Text;
import javafx.scene.web.WebView;
import javafx.stage.Stage;

import java.io.IOException;

public class Main extends Application {

    @Override
    public void start(Stage primaryStage) throws Exception{

        // Initialize Search class
        Search search = new Search();

        Parent root = FXMLLoader.load(getClass().getResource("sample.fxml"));
        primaryStage.setTitle("EasySearch");
        primaryStage.setAlwaysOnTop(true);

        // Search Bar Scene
        GridPane grid = new GridPane();
        grid.setAlignment(Pos.CENTER);
        grid.setHgap(10);
        grid.setVgap(10);
        grid.setPadding(new Insets(25, 25, 25, 25));
        Scene searchScene = new Scene(grid, 300, 275);

        TextField searchInputField = new TextField();
        grid.add(searchInputField, 0, 1);

        Button searchButton = new Button("Search");
        grid.add(searchButton, 1, 1);

        // Wikipedia Result Scene
        GridPane grid1 = new GridPane();
        grid1.setAlignment(Pos.CENTER);
        grid1.setHgap(10);
        grid1.setVgap(10);
        grid1.setPadding(new Insets(25, 25, 25, 25));
        Scene wikiResultScene = new Scene(grid1, 300, 275);

        Text summaryWikiText = new Text();

        searchButton.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent e) {
                try {
                    search.parseInput(searchInputField.getText(), 0);
                    //summaryWikiText.setText();
                    System.out.println("1");
                    primaryStage.setScene(search.getResultSearchScene());
                    System.out.println("2");
                } catch(IOException exception) {
                    System.out.println("Invalid input");
                }
            }
        });


        //search.parseInput("stackoverflow null strings", 1);

        /*WebView webview = new WebView();
        webview.getEngine().load(
                "https://www.youtube.com/embed/xXpPweAooeE?autoplay=1"
        );
        webview.setPrefSize(640, 390);

        primaryStage.setScene(new Scene(webview));*/
        primaryStage.setScene(searchScene);
        primaryStage.show();
    }


    public static void main(String[] args) {
        launch(args);
    }

}
