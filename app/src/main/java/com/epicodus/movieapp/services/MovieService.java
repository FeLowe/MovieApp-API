package com.epicodus.movieapp.services;



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
        String url = urlBuilder.build().toString();

        Request request = new Request.Builder().url(url).build();

        Call call = client.newCall(request);
        call.enqueue(callback);
    }

}
