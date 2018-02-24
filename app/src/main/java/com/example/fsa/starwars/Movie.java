package com.example.fsa.starwars;

import android.content.Context;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

public class Movie {

    // instance variables or fields
    public String title;
    public int episode_number;
    public String main_characters;
    public List<String> mcs;
    public String description;
    public String poster;
    public String url;
    public String hasSeen;

    // constructor
    // default

    // method
    // static methods that read the json file in and load into Recipe

    // static method that loads our recipes.json using the helper method
    // this method will return an array list of recipes constructed from the JSON
    // file
    public static ArrayList<Movie> getMoviesFromFile(String filename, Context context){
        ArrayList<Movie> movieList = new ArrayList<Movie>();


        // try to read from JSON file
        // get information by using the tags
        // construct a Recipe Object for each recipe in JSON
        // add the object to arraylist
        // return arraylist
        try{
            String jsonString = loadJsonFromAsset("movies.json", context);
            JSONObject json = new JSONObject(jsonString);
            JSONArray movies = json.getJSONArray("movies");

            // for loop to go through each recipe in your recipes array

            for (int i = 0; i < movies.length(); i++){
                Movie movie = new Movie();
                movie.title = movies.getJSONObject(i).getString("title");
                movie.episode_number = movies.getJSONObject(i).getInt("episode_number");
                movie.main_characters = movies.getJSONObject(i).getString("main_characters");
                movie.description = movies.getJSONObject(i).getString("description");
                movie.poster = movies.getJSONObject(i).getString("poster");
                movie.url = movies.getJSONObject(i).getString("url");
                movie.hasSeen = "Has Seen?";

                String jsonArr = movies.getJSONObject(i).getString("main__characters");
                movie.mcs = new ArrayList<String>();
                JSONArray jsonArray = new JSONArray(jsonArr);
                for(int j = 0; j<jsonArray.length(); j++){
                    movie.mcs.add(jsonArray.get(j).toString());
                }

                // add to arraylist
                movieList.add(movie);
            }

        } catch (JSONException e) {
            e.printStackTrace();
        }
        return movieList;
    }


    // helper method that loads from any Json file
    private static String loadJsonFromAsset(String filename, Context context) {
        String json = null;

        try {
            InputStream is = context.getAssets().open(filename);
            int size = is.available();
            byte[] buffer = new byte[size];
            is.read(buffer);
            is.close();
            json = new String(buffer, "UTF-8");
        }
        catch (java.io.IOException ex) {
            ex.printStackTrace();
            return null;
        }

        return json;
    }

}

