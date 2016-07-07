package com.epicodus.movieapp.ui;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.epicodus.movieapp.R;
import com.epicodus.movieapp.models.Movie;
import com.squareup.picasso.Picasso;

import org.parceler.Parcels;

import butterknife.Bind;
import butterknife.ButterKnife;

/**
 * A simple {@link Fragment} subclass.
 */
public class MovieDetailFragment extends Fragment {
    @Bind(R.id.movieImageView) ImageView mMovieImageView;
    @Bind(R.id.movieNameTextView) TextView mMovieNameTextView;
//    @Bind(R.id.releaseTextView) TextView mReleaseTextView;
    @Bind(R.id.ratingTextView) TextView mRatingTextView;
    @Bind(R.id.synopsis) TextView mSynopsis;

    private Movie mMovie;


    public static MovieDetailFragment newInstance (Movie movie){
        MovieDetailFragment movieDetailFragment = new MovieDetailFragment();
        Bundle args = new Bundle();
        args.putParcelable("movie", Parcels.wrap(movie));
        movieDetailFragment.setArguments(args);
        return movieDetailFragment;
    }


//    public MovieDetailFragment() {
//        // Required empty public constructor
//    }


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mMovie = Parcels.unwrap(getArguments().getParcelable("movie"));
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_movie_detail, container, false);

        ButterKnife.bind(this, view);

       //Picasso.with(view.getContext()).load(mMovie.getPoster()).into(mMovieImageView);

        mMovieNameTextView.setText(mMovie.getTitle());
        mRatingTextView.setText(Double.toString(mMovie.getRating()) + "/10");
//      mReleaseTextView.setText(mMovie.getRelease());
        mSynopsis.setText(mMovie.getSynopsis());

        return view;

    }

}
