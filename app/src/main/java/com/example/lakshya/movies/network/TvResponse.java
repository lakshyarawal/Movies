package com.example.lakshya.movies.network;

import com.example.lakshya.movies.Movie;
import com.example.lakshya.movies.Tv;
import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;

/**
 * Created by LAKSHYA on 7/19/2017.
 */

public class TvResponse {
    public int page;

@SerializedName("results")
    private ArrayList<Tv> movieArrayList;


    public ArrayList<Tv> getMovieArrayList() {
        return movieArrayList;
    }
    public void setMovieArrayList(ArrayList<Tv> movieArrayList) {
        this.movieArrayList = movieArrayList;
    }
}
