package com.epicodus.movieapp.services;



import android.util.Log;

import com.epicodus.movieapp.Constants;
import com.epicodus.movieapp.models.Movie;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.util.ArrayList;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.HttpUrl;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;


public class MovieService {

    public static void findMovies(String movieSearch, Callback callback) {

        OkHttpClient client = new OkHttpClient.Builder().build();

        //BUILD YOUR HTTP STRING HERE
        HttpUrl.Builder urlBuilder = HttpUrl.parse(Constants.API_BASE_URL).newBuilder();
        urlBuilder.addQueryParameter(Constants.MOVIE_QUERY_PARAMETER, movieSearch);
        urlBuilder.addQueryParameter(Constants.API_KEY_QUERY_PARAMETER, Constants.MOVIE_API_KEY);
//        urlBuilder.addQueryParameter(Constants.MOVIE_API_KEY);


        String url = urlBuilder.build().toString();
        Log.d("URL", url);

        Request request = new Request.Builder().url(url).build();

        Call call = client.newCall(request);
        call.enqueue(callback);
    }

    public ArrayList<Movie> processResults(Response response) {
        ArrayList<Movie> movies = new ArrayList<>();

        try {
            String jsonData = response.body().string();
            if (response.isSuccessful()) {
                JSONObject movieJSON = new JSONObject(jsonData);

                JSONArray resultsJSON = movieJSON.getJSONArray("results");

                for (int i = 0; i < resultsJSON.length(); i++) {

                    JSONObject restaurantJSON = resultsJSON.getJSONObject(i);
                    String poster = restaurantJSON.getString("poster_path");
                    String title = restaurantJSON.getString("title");
                    String synopsis = restaurantJSON.getString("overview");
                    double rating = restaurantJSON.getDouble("vote_average");
                    String release = restaurantJSON.getString("release_date");

//                String title = restaurantJSON.optString("display_phone", "Phone not available");

                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return movies;
    }
}