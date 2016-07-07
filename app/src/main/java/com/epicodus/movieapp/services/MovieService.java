package com.epicodus.movieapp.services;



import android.util.Log;

import com.epicodus.movieapp.Constants;
import com.epicodus.movieapp.models.Movie;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.HttpUrl;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;


public class MovieService {
    public static final String TAG = MovieService.class.getSimpleName();


    public static void findMovies(String movieSearch, Callback callback) {

        OkHttpClient client = new OkHttpClient.Builder().build();
//        OkHttpClient client = new OkHttpClient();

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

    public ArrayList<Movie> processResults(String jsonData) {
        ArrayList<Movie> movies = new ArrayList<>();

        Log.d("response service", jsonData + "");

        try {
            Log.d("jsonData service", jsonData + " ?");

            JSONObject movieJSON = new JSONObject(jsonData);

            Log.d("movieJson", movieJSON + "");

              JSONArray resultsJSON = movieJSON.getJSONArray("results");

                for (int i = 0; i < resultsJSON.length(); i++) {

                    JSONObject moviJSON = resultsJSON.getJSONObject(i);

                    String poster = moviJSON.getString("poster_path");
                    String title = moviJSON.getString("title");
                    String synopsis = moviJSON.getString("overview");
                    double rating = moviJSON.getDouble("vote_average");
                    String release = moviJSON.getString("release_date");

//                String title = restaurantJSON.optString("display_phone", "Phone not available");

                    Movie movie = new Movie(poster, title, synopsis, rating, release);
                    movies.add(movie);

                }

                Log.d(TAG, movieJSON.toString());
                Log.d("JSON", jsonData);
        }
//        catch (IOException e) {
//            e.printStackTrace();
//            Log.d("error", "io " + e);
//        }
        catch (JSONException e) {
            e.printStackTrace();
            Log.d("error", "json " + e);
        }
        return movies;
    }
}