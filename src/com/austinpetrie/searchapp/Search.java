package com.austinpetrie.searchapp;

<<<<<<< HEAD
import com.google.api.client.googleapis.json.GoogleJsonResponseException;
import com.google.api.client.http.HttpRequest;
import com.google.api.client.http.HttpRequestInitializer;
import com.google.api.client.http.javanet.NetHttpTransport;
import com.google.api.client.json.jackson.JacksonFactory;
import com.google.api.services.youtube.*;
import com.google.api.services.youtube.model.ResourceId;
import com.google.api.services.youtube.model.SearchListResponse;
import com.google.api.services.youtube.model.SearchResult;
import com.google.api.services.youtube.model.Thumbnail;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ScrollPane;
import javafx.scene.layout.GridPane;
import javafx.scene.text.Text;
import javafx.scene.text.TextFlow;
import javafx.scene.web.WebView;
import org.json.*;

=======
>>>>>>> 7d7298eaf0b2641ea1d84ab66b7c0df60f3017c7
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
<<<<<<< HEAD
import java.util.Iterator;
import java.util.List;
import java.util.Properties;
=======

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
>>>>>>> 7d7298eaf0b2641ea1d84ab66b7c0df60f3017c7

public class Search {

    private static FXMLLoader fxmlLoader;
    final private static String wikiUrl = "https://en.wikipedia.org/w/api.php?format=json&action=query&prop=extracts&exlimit=1&explaintext&exintro&redirects=&titles=";
    final private static String googleSearchUrl = "https://www.google.com/search?&q=";
    final private static String googleLuckyUrl = "https://www.google.com/search?btnI=I%27m+Feeling+Lucky&q=";
    final private static String stackOFUrl = "";
    final private static String youtubeUrl = "";
    final private static String githubUrl = "";
    private static String urlStr;
<<<<<<< HEAD
    //private Scene resultSearchScene;

    /**
     * Called when search button is pressed
     * Creates proper input search strings for the chosen website
     */
    public void parseInput(String titleStr, String website) throws IOException, JSONException {
=======
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
>>>>>>> 7d7298eaf0b2641ea1d84ab66b7c0df60f3017c7
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
<<<<<<< HEAD
    public void pullWiki(String titleStr) throws IOException, JSONException {
=======
    public void pullWiki(String titleStr) throws IOException {
>>>>>>> 7d7298eaf0b2641ea1d84ab66b7c0df60f3017c7

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

<<<<<<< HEAD
        Button backBtn = new Button("Go Back");

        grid1.add(backBtn, 0, 0);
        grid1.add(summaryWikiText, 0, 1);

        scrollPane.setFitToWidth(true);
        scrollPane.setContent(grid1);
        Scene wikiResultScene = new Scene(scrollPane, 300, 275);

        //setResultSearchScene(wikiResultScene);

        Main.pStage.setScene(wikiResultScene);

        // Return to search scene
        backBtn.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent e) {
                Main.pStage.setScene(Main.searchScene);
            }
        });

        /*
         * button to open entire wiki page in browser
         * Print Synopsis/Summary and have a link to "keep reading" which reveals it all (maybe open browser)
         */
=======
        scrollPane.setFitToWidth(true);
        scrollPane.setContent(summaryWikiText);
        Scene wikiResultScene = new Scene(scrollPane, 300, 275);

        setResultSearchScene(wikiResultScene);

        /*
            button to open entire wiki page in browser
			Print Synopsis/Summary and have a link to "keep reading" which reveals it all (maybe open browser)
		 */
>>>>>>> 7d7298eaf0b2641ea1d84ab66b7c0df60f3017c7
    }

    // Google first result in their Search bar, so I'm feeling lucky (or X result links and desciptions)
    public void pullGoogle(String titleStr) throws IOException {

        urlStr = googleLuckyUrl + titleStr;
<<<<<<< HEAD
=======
        //System.out.println(urlStr);
>>>>>>> 7d7298eaf0b2641ea1d84ab66b7c0df60f3017c7
        URL url = new URL(urlStr);

        openWebpage(url);

<<<<<<< HEAD
        /*
         * Feeling Lucky opens first page in browser
         * Showing X results shows X result links and their descriptions in the app (clicking a link opens it in a browser)
         */
=======
		/*
			Feeling Lucky opens first page in browser
			Showing X results shows X result links and their descriptions in the app (clicking a link opens it in a browser)
		 */
>>>>>>> 7d7298eaf0b2641ea1d84ab66b7c0df60f3017c7
    }

    // StackOverflow first result in their Search bar, return the question and X answers (or most vote or accepted answer)
    public static void pullStackOverflow(String titleStr) {

    }

<<<<<<< HEAD
    /*
     * Define a global instance of a Youtube object, which will be used
     * to make YouTube Data API requests.
     */
    private static YouTube youtube;

    /*
     * Define a global variable that identifies the name of a file that
     * contains the developer's API key.
     */
    private static final String PROPERTIES_FILENAME = "youtube.properties";
    private static final long NUMBER_OF_VIDEOS_RETURNED = 1;

    /**
     * YouTube first video in their Search bar, auto play the video
     * (or should it open it in your browser; should X results be shown?)
     */
    public static void pullYoutube(String queryTerm) {

        // Read the developer key from the properties file.
        Properties properties = new Properties();
        try {
            InputStream in = Search.class.getResourceAsStream(PROPERTIES_FILENAME);
            properties.load(in);

        } catch (IOException|NullPointerException e) {
            System.err.println("There was an error reading " + PROPERTIES_FILENAME + ": " + e.getCause()
                    + " : " + e.getMessage());
            System.exit(1);
        }

        try {
            /*
             * This object is used to make YouTube Data API requests. The last
             * argument is required, but since we don't need anything
             * initialized when the HttpRequest is initialized, we override
             * the interface and provide a no-op function.
             */
            youtube = new YouTube.Builder(new NetHttpTransport(), new JacksonFactory(), new HttpRequestInitializer() {
                public void initialize(HttpRequest request) throws IOException {
                }
            }).setApplicationName("youtube-cmdline-search-sample").build();

            // Define the API request for retrieving search results.
            YouTube.Search.List search = youtube.search().list("id,snippet");

            // Set the developer key
            String apiKey = properties.getProperty("youtube.apikey");
            search.setKey(apiKey);
            search.setQ(queryTerm);

            // Restrict the search results to only include videos
            search.setType("video");

            // To increase efficiency, only retrieve the fields that the application uses.
            // common fields: id/kind,id/videoId,snippet/title,snippet/thumbnails/default/url
            search.setFields("items(id/kind,id/videoId)");
            search.setMaxResults(NUMBER_OF_VIDEOS_RETURNED);

            // Call the API and print results.
            SearchListResponse searchResponse = search.execute();
            List<SearchResult> searchResultList = searchResponse.getItems();
            if (searchResultList != null) {

                SearchResult singleVideo = searchResultList.iterator().next();
                ResourceId rId = singleVideo.getId();

                /*
                 * Confirm that the result represents a video. Otherwise, the
                 * item will not contain a video ID.
                 */
                if (rId.getKind().equals("youtube#video")) {
                    String videoId = rId.getVideoId();
                    WebView youtubePlayer = new WebView();
                    youtubePlayer.getEngine().load(
                            "https://www.youtube.com/embed/"+videoId+"?autoplay=1"
                    );
                    youtubePlayer.setPrefSize(640, 390);

                    // Build scene for youtube results
                    GridPane grid1 = new GridPane();
                    grid1.setAlignment(Pos.CENTER);
                    grid1.setHgap(10);
                    grid1.setVgap(10);
                    grid1.setPadding(new Insets(25, 25, 25, 25));

                    Button backBtn = new Button("Go Back");

                    grid1.add(backBtn, 0, 1);
                    grid1.add(youtubePlayer, 0, 0);

                    ScrollPane scrollPane = new ScrollPane();
                    scrollPane.setFitToWidth(true);
                    scrollPane.setContent(grid1);
                    Scene youtubeResultScene = new Scene(scrollPane, 700, 390);

                    Main.pStage.setScene(youtubeResultScene);

                    // Return to search scene
                    backBtn.setOnAction(new EventHandler<ActionEvent>() {
                        @Override
                        public void handle(ActionEvent e) {
                            //kill the youtube video by loading a null url,
                            //otherwise it continues to play in background
                            youtubePlayer.getEngine().load(null);
                            Main.pStage.setScene(Main.searchScene);
                        }
                    });
                }
            }
        } catch (GoogleJsonResponseException e) {
            System.err.println("There was a service error: " + e.getDetails().getCode() + " : "
                    + e.getDetails().getMessage());
        } catch (IOException e) {
            System.err.println("There was an IO error: " + e.getCause() + " : " + e.getMessage());
        } catch (Throwable t) {
            t.printStackTrace();
        }
    }

    /**
     * Prints out all results in the Iterator. For each result, print the
     * title, video ID, and thumbnail.
     *
     * @param iteratorSearchResults Iterator of SearchResults to print
     *
     * @param query Search query (String)
     */
    private static void prettyPrint(Iterator<SearchResult> iteratorSearchResults, String query) {

        System.out.println("\n=============================================================");
        System.out.println(
                "   First " + NUMBER_OF_VIDEOS_RETURNED + " videos for search on \"" + query + "\".");
        System.out.println("=============================================================\n");

        if (!iteratorSearchResults.hasNext()) {
            System.out.println(" There aren't any results for your query.");
        }

        while (iteratorSearchResults.hasNext()) {

            SearchResult singleVideo = iteratorSearchResults.next();
            ResourceId rId = singleVideo.getId();

            // Confirm that the result represents a video. Otherwise, the
            // item will not contain a video ID.
            if (rId.getKind().equals("youtube#video")) {
                Thumbnail thumbnail = singleVideo.getSnippet().getThumbnails().getDefault();

                System.out.println(" Video Id" + rId.getVideoId());
                System.out.println(" Title: " + singleVideo.getSnippet().getTitle());
                System.out.println(" Thumbnail: " + thumbnail.getUrl());
                System.out.println("\n-------------------------------------------------------------\n");
            }
        }
=======
    // YouTube first video in their Search bar, auto play the video (or should it open it in your browser; should X results be shown?)
    public static void pullYoutube(String titleStr) {

>>>>>>> 7d7298eaf0b2641ea1d84ab66b7c0df60f3017c7
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

<<<<<<< HEAD
    /*public Scene getResultSearchScene() {
=======
    public Scene getResultSearchScene() {
>>>>>>> 7d7298eaf0b2641ea1d84ab66b7c0df60f3017c7
        return this.resultSearchScene;
    }

    public void setResultSearchScene(Scene s) {
        this.resultSearchScene = s;
<<<<<<< HEAD
    }*/
}
=======
    }
}
>>>>>>> 7d7298eaf0b2641ea1d84ab66b7c0df60f3017c7
