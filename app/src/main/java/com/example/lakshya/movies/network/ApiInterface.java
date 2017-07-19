package com.example.lakshya.movies.network;

import com.example.lakshya.movies.Movie;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

/**
 * Created by LAKSHYA on 7/19/2017.
 */

public interface ApiInterface {
    @GET("movie/popular")
    Call<MovieResponse> getMovieResponse(@Query("api_key") String apiKey);
}
