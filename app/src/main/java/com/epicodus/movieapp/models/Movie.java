package com.epicodus.movieapp.models;

import java.util.ArrayList;

/**
 * Created by Guest on 7/6/16.
 */
public class Movie {
    private String mPoster;
    private String mTitle;
    private String mSynopsis;
//    private String mDirector;
//    private ArrayList<String> mActors = new ArrayList<>();
    private String mRelease;
    private double mRating;

    public Movie(String poster, String title, String synopsis, double rating, String release) {
        this.mPoster = "http://image.tmdb.org/t/p/w500" + poster;
        this.mTitle = title;
        this.mSynopsis = synopsis;
        this.mRating = rating;
        this.mRelease = release;

    }
    public String getPoster() {
        return mPoster;
    }

    public String getTitle() {
        return mTitle;
    }

    public String getSynopsis() {
        return  mSynopsis;
    }

    public double getRating() {
        return mRating;
    }

    public String getRelease(){
        return mRelease;
    }

}


