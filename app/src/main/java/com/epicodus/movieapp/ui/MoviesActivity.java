package com.epicodus.movieapp.ui;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ListView;

import com.epicodus.movieapp.R;
import com.epicodus.movieapp.models.Movie;
import com.epicodus.movieapp.services.MovieService;

import java.io.IOException;
import java.util.ArrayList;

import butterknife.Bind;
import butterknife.ButterKnife;
import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.Response;

public class MoviesActivity extends AppCompatActivity {
    public static final String TAG = MoviesActivity.class.getSimpleName();

    @Bind(R.id.recyclerView) RecyclerView mRecyclerView;
//    private MovieListAdapter mAdapter;
    public ArrayList<Movie> mMovies = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_movies);

        ButterKnife.bind(this);

        Intent intent = getIntent();
        String movie = intent.getStringExtra("movie");

        getMovies(movie);

    }

    private void getMovies(String movie){
        final MovieService movieService = new MovieService();
        movieService.findMovies(movie, new Callback() {

            @Override
            public void onFailure(Call call, IOException e) {
                e.printStackTrace();
            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {
                Log.d("success", "onresponse");
                Log.d("response", response + "!");

                try {
                    String jsonData = response.body().string();

                    if (response.isSuccessful()) {
                        Log.v(TAG, jsonData);

                        mMovies = movieService.processResults(jsonData);
                        Log.d("first movie", mMovies.get(0).getTitle());
                    }
                }catch(IOException e){
                    e.printStackTrace();
                }
            }
        });
    }
}
