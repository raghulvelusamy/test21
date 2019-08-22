package com.demo.network.config;

import android.util.Log;

import com.demo.network.response.Movies;
import com.google.gson.JsonArray;
import com.google.gson.JsonDeserializationContext;
import com.google.gson.JsonDeserializer;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParseException;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;

public class ResultTypeConverter implements JsonDeserializer<List<Movies>> {
    private static final String TAG = ResultTypeConverter.class.getSimpleName();

    @Override
    public List<Movies> deserialize(JsonElement json, Type typeOfT, JsonDeserializationContext context) throws JsonParseException {

        ArrayList<Movies> moviesList = new ArrayList<>();
        try {
            JsonObject jsonObject = json.getAsJsonObject();
            JsonArray resultsArray = jsonObject.getAsJsonArray("results");
            moviesList = new ArrayList<>(resultsArray.size());
            int totalPages = jsonObject.get("total_pages").getAsInt();
            for (int i = 0; i < resultsArray.size(); i++) {
                Movies movie = RetrofitConfig.getGson().fromJson(resultsArray.get(i), Movies.class);
                movie.setTotalPages(totalPages);
                moviesList.add(movie);
            }
        } catch (JsonParseException e) {
            Log.e(TAG, String.format("Could not deserialize Movies element: %s", json.toString()));
        }
        return moviesList;
    }
}
