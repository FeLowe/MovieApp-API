package com.epicodus.movieapp.adapters;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.epicodus.movieapp.R;
import com.epicodus.movieapp.models.Movie;

import java.util.ArrayList;

import butterknife.Bind;
import butterknife.ButterKnife;


public class MovieListAdapter extends RecyclerView.Adapter<MovieListAdapter.MovieViewHolder> {
    private ArrayList<Movie> mMovies = new ArrayList<>();
    private Context mContext;

    public MovieListAdapter(Context context, ArrayList<Movie> movies) {
        mContext = context;
        mMovies = movies;
        Log.d("Movies in adapter", mMovies.size() + "");
    }

    @Override
    public MovieListAdapter.MovieViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.movie_list, parent, false);
        MovieViewHolder viewHolder = new MovieViewHolder(view);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(MovieListAdapter.MovieViewHolder holder, int position) {
        holder.bindMovie(mMovies.get(position));
    }

    @Override
    public int getItemCount() {
        return mMovies.size();
    }


    public class MovieViewHolder extends RecyclerView.ViewHolder {
//        @Bind(R.id.movieImageView) ImageView mMovieImageView;
        @Bind(R.id.movieNameTextView) TextView mMovieNameTextView;
        @Bind(R.id.releaseTextView) TextView mReleaseTextView;
        @Bind(R.id.ratingTextView) TextView mRatingTextView;

        private Context mContext;

        public MovieViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
            mContext = itemView.getContext();
        }
        public void bindMovie(Movie movie) {
//            mMovieImageView.setText(movie.getPoster());
            mMovieNameTextView.setText(movie.getTitle());
            mReleaseTextView.setText(movie.getRelease());
            mRatingTextView.setText("Rating: " + movie.getRating() + "/10");
        }

    }
}