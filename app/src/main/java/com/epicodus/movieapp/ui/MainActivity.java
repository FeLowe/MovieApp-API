package com.epicodus.movieapp.ui;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.epicodus.movieapp.R;

import butterknife.Bind;
import butterknife.ButterKnife;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {
    @Bind(R.id.findMoviesButton)
    Button mFindMoviesButton;
    @Bind(R.id.searchMovie)
    EditText mSearchMovie;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);
        mFindMoviesButton.setOnClickListener(this);
    }


    @Override
    public void onClick(View v) {
        String movieName = mSearchMovie.getText().toString();
        Intent intent = new Intent(MainActivity.this, MoviesActivity.class);
        intent.putExtra("movie", movieName);
        startActivity(intent);
    }
}

