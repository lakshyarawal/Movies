package com.example.lakshya.movies.network;

import com.example.lakshya.movies.Movie;
import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;

/**
 * Created by LAKSHYA on 7/19/2017.
 */

public class MovieResponse {
    public int page;

    public ArrayList<Movie> movieArrayList;
    public ArrayList<Movie> getMovieArrayList() {
        return movieArrayList;
    }
    public void setMovieArrayList(ArrayList<Movie> movieArrayList) {
        this.movieArrayList = movieArrayList;
    }
}
