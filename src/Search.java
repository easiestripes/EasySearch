import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.URL;
import java.net.URLConnection;
import java.nio.charset.Charset;
import java.util.Scanner;

import jdk.internal.util.xml.impl.Input;
import org.json.*;

public class Search {

    final private static String WIKIURL = "https://en.wikipedia.org/w/api.php?format=json&action=query&prop=extracts&exlimit=1&explaintext&exintro&redirects=&titles=";
    final private static String GOOGLEURL = "";
    final private static String STACKOFURL = "";
    final private static String YOUTUBEURL = "";
    final private static String GITHUBURL = "";
    private static int websiteID = -1;

    public static void main(String[] args) throws IOException {
        // text field for url, dropdown menu for websites (put that value into websiteID, and button to Search
        // press button, call parseInput();
        Scanner input = new Scanner(System.in);
        System.out.print("Wiki link: ");
        String url = input.next();
        websiteID = 0;
        parseInput(url, websiteID);
    }

    public static void parseInput(String titleStr, int websiteID) throws IOException {
        switch(websiteID) {
            case 0:
                pullWiki(titleStr);
                break;
            case 1:
                pullGoogle(titleStr);
                break;
            case 2:
                pullStackOverflow(titleStr);
                break;
            case 3:
                pullYoutube(titleStr);
                break;
            case 4:
                pullGithub(titleStr);
                break;
            default:
                System.out.println("Incorrect websiteID"); // somehow failed to give a propr websiteID
        }
    }

    // Wikipedia result from their Search bar
    public static void pullWiki(String titleStr) throws IOException {

        // create URL
        String urlStr = WIKIURL + titleStr; // might need char regex checking/converting
        System.out.println(urlStr);
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

        System.out.println(summary);


		/*
			Print Synopsis/Summary and have a link to "keep reading" which reveals it all (maybe open browser)
		 */
    }

    // Google first result in their Search bar, so I'm feeling lucky (or X result links and desciptions)
    public static void pullGoogle(String titleStr) {

		/*
			Feeling Lucky opens first page in browser
			Showing X results shows X result links and their descriptions in the app (clicking a link opens it in a browser)
		 */
    }

    // StackOverflow first result in their Search bar, return the question and X answers (or most vote or accepted answer)
    public static void pullStackOverflow(String titleStr) {

    }

    // Youtube first video in their Search bar, auto play the video (or should it open it in your browser; should X results be shown?)
    public static void pullYoutube(String titleStr) {

    }

    // Github X results from their Search bar
    public static void pullGithub(String titleStr) {

    }
}
