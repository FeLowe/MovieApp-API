package com.epicodus.movieapp.services;



import android.util.Log;

import com.epicodus.movieapp.Constants;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.HttpUrl;
import okhttp3.OkHttpClient;
import okhttp3.Request;


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

}
