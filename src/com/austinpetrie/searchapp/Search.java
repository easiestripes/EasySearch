package com.austinpetrie.searchapp;

import java.awt.*;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.URI;
import java.net.URISyntaxException;
import java.net.URL;
import java.net.URLConnection;
import java.nio.charset.Charset;

import com.sun.deploy.uitoolkit.impl.fx.HostServicesFactory;
import com.sun.javafx.application.HostServicesDelegate;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Orientation;
import javafx.scene.control.ScrollBar;
import javafx.scene.layout.VBox;
import javafx.scene.text.TextFlow;
import org.json.*;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.layout.GridPane;
import javafx.scene.control.ScrollPane;
import javafx.scene.text.Text;

public class Search {

    private static FXMLLoader fxmlLoader;
    final private static String wikiUrl = "https://en.wikipedia.org/w/api.php?format=json&action=query&prop=extracts&exlimit=1&explaintext&exintro&redirects=&titles=";
    final private static String googleSearchUrl = "https://www.google.com/search?&q=";
    final private static String googleLuckyUrl = "https://www.google.com/search?btnI=I%27m+Feeling+Lucky&q=";
    final private static String stackOFUrl = "";
    final private static String youtubeUrl = "";
    final private static String githubUrl = "";
    private static String urlStr;
    private static int websiteID = -1;
    private Scene resultSearchScene;

    /*public static void main(String[] args) throws IOException {
        // text field for url, dropdown menu for websites (put that value into websiteID, and button to Search
        // press button, call parseInput();
        Scanner input = new Scanner(System.in);
        System.out.print("Search: ");
        String url = input.nextLine();
        websiteID = 1;
        parseInput(url, websiteID);
    }*/

    /*
        Called when search button is pressed
        Creates proper input search strings for the chosen website
     */
    public void parseInput(String titleStr, String website) throws IOException {
        if(website.equals("Wikipedia")) {
            titleStr = titleStr.replaceAll("\\s", "_");
            pullWiki(titleStr);
        } else if(website.equals("Google")) {
            titleStr = titleStr.replaceAll("\\s", "+");
            pullGoogle(titleStr);
        } else if(website.equals("StackOverflow")) {
            pullStackOverflow(titleStr);
        } else if(website.equals("YouTube")) {
            pullYoutube(titleStr);
        } else if(website.equals("GitHub")) {
            pullGithub(titleStr);
        } else {
            System.out.println("Incorrect website"); // somehow failed to give a proper website
        }
    }

    // Wikipedia result from their Search bar
    public void pullWiki(String titleStr) throws IOException {

        urlStr = wikiUrl + titleStr;
        URL url = new URL(urlStr);

        URLConnection conn = url.openConnection();
        InputStream is = conn.getInputStream();

        BufferedReader br = new BufferedReader(new InputStreamReader(is, Charset.forName("UTF-8")));

        String line = null;
        StringBuilder sb = new StringBuilder();

        // read json plaintext into sb
        while ((line = br.readLine()) != null) {
            sb.append(line);
        }
        String wikiJson = sb.toString();

        JSONObject jsonObj = new JSONObject(wikiJson);

        // get 'extract' (or summary) text from JSON
        String pageID = (String) jsonObj.getJSONObject("query").getJSONObject("pages").names().get(0);
        String summary = jsonObj.getJSONObject("query").getJSONObject("pages").getJSONObject(pageID).getString("extract");

        // Build scene for wikipedia results
        ScrollPane scrollPane = new ScrollPane();
        GridPane grid1 = new GridPane();
        grid1.setAlignment(Pos.CENTER);
        grid1.setHgap(10);
        grid1.setVgap(10);
        grid1.setPadding(new Insets(25, 25, 25, 25));

        TextFlow summaryWikiText = new TextFlow();
        Text t1 = new Text(summary);
        summaryWikiText.getChildren().add(t1);

        scrollPane.setFitToWidth(true);
        scrollPane.setContent(summaryWikiText);
        Scene wikiResultScene = new Scene(scrollPane, 300, 275);

        setResultSearchScene(wikiResultScene);

        /*
            button to open entire wiki page in browser
			Print Synopsis/Summary and have a link to "keep reading" which reveals it all (maybe open browser)
		 */
    }

    // Google first result in their Search bar, so I'm feeling lucky (or X result links and desciptions)
    public void pullGoogle(String titleStr) throws IOException {

        urlStr = googleLuckyUrl + titleStr;
        //System.out.println(urlStr);
        URL url = new URL(urlStr);

        openWebpage(url);

		/*
			Feeling Lucky opens first page in browser
			Showing X results shows X result links and their descriptions in the app (clicking a link opens it in a browser)
		 */
    }

    // StackOverflow first result in their Search bar, return the question and X answers (or most vote or accepted answer)
    public static void pullStackOverflow(String titleStr) {

    }

    // YouTube first video in their Search bar, auto play the video (or should it open it in your browser; should X results be shown?)
    public static void pullYoutube(String titleStr) {

    }

    // GitHub X results from their Search bar
    public static void pullGithub(String titleStr) {

    }

    // open URL in default browser
    public static void openWebpage(URL url) {
        try {
            openWebpage(url.toURI());
        } catch (URISyntaxException e) {
            e.printStackTrace();
        }
    }

    // open URI in default browser
    public static void openWebpage(URI uri) {
        Desktop desktop = Desktop.isDesktopSupported() ? Desktop.getDesktop() : null;
        if (desktop != null && desktop.isSupported(Desktop.Action.BROWSE)) {
            Thread browser = new Thread(() -> {
                try {
                    Desktop.getDesktop().browse(uri);
                } catch (IOException e) {
                    e.printStackTrace();
                }
            });
            browser.start();
            browser.interrupt();
        }
    }

    public Search () {
        this.fxmlLoader = new FXMLLoader();
    }

    public static FXMLLoader getFXMLLoader() {
        return fxmlLoader;
    }

    public Scene getResultSearchScene() {
        return this.resultSearchScene;
    }

    public void setResultSearchScene(Scene s) {
        this.resultSearchScene = s;
    }
}
