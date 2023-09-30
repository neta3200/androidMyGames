package com.example.mygames.Services;

import android.os.StrictMode;

import com.example.mygames.Models.DataModel;
import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;

import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;

public class DataService {
    public static ArrayList<DataModel> getArrState(String sURL) {
        ArrayList<DataModel> arrState = new ArrayList<>();
        //String sURL = "https://www.freetogame.com/api/games";
        URL url = null;
        StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder().permitAll().build();
        StrictMode.setThreadPolicy(policy);

        try{
            url = new URL (sURL);

        }catch (MalformedURLException e){
            throw  new RuntimeException(e);
        }

        HttpURLConnection request = null;
        try {
            request = (HttpURLConnection) url.openConnection();
            request.connect();

            int responseCode = request.getResponseCode();
            if (responseCode != HttpURLConnection.HTTP_OK) {
                if (responseCode == HttpURLConnection.HTTP_NOT_FOUND) {
                    // Handle the 404 error gracefully
                    return arrState; // Or you can throw a custom exception
                } else {
                    throw new IOException("HTTP error: " + responseCode);
                }
            }




            JsonParser jsonParser = new JsonParser();
            JsonElement root = jsonParser.parse(new InputStreamReader((InputStream) request.getContent()));
            JsonArray rootObj = root.getAsJsonArray();

            for(JsonElement je : rootObj){
                JsonObject obj = je.getAsJsonObject();
                JsonElement entrieTitle = obj.get("title");
                JsonElement entriePublisher = obj.get("publisher");
                JsonElement entrieDescription = obj.get("short_description");
                JsonElement entrieGameUrl = obj.get("game_url");
                JsonElement entrieGenre = obj.get("genre");
                JsonElement entriePlatform = obj.get("platform");
                JsonElement entrieDeveloper = obj.get("developer");
                JsonElement entrieThumbnail = obj.get("thumbnail");
                JsonElement entrieReleaseDate = obj.get("release_date");


                String title = entrieTitle.toString().replace("\"", "");
                String publisher = entriePublisher.toString().replace("\"", "");
                String description = entrieDescription.toString().replace("\"", "");
                String gameUrl = entrieGameUrl.toString().replace("\"", "");
                String genre = "(" + entrieGenre.toString().replace("\"", "") + ")";
                String platform = entriePlatform.toString().replace("\"", "");
                String developer = entrieDeveloper.toString().replace("\"", "");
                String thumbnail = entrieThumbnail.toString().replace("\"", "");
                String releaseDate = entrieReleaseDate.toString().replace("\"", "");

                arrState.add(new DataModel(title, publisher, description, gameUrl, genre, platform, developer, thumbnail, releaseDate));

            }

        } catch (IOException e) {
            throw new RuntimeException(e);
        } finally {
            if (request != null) {
                request.disconnect();
            }
        }

        return arrState;
    }
}
