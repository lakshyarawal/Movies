package com.example.lakshya.movies.network;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;
import retrofit2.http.Query;

public interface ApiInterface {
    @GET("movie/popular")
    Call<MovieResponse> getPopularMovieResponse(@Query("api_key") String apiKey);
    @GET("movie/top_rated")
    Call<MovieResponse> getTopRatedMovieResponse(@Query("api_key") String apiKey);
    @GET("tv/popular")
    Call<TvResponse> getPopularShowResponse(@Query("api_key") String apiKey);
    @GET("tv/top_rated")
    Call<TvResponse> getTopRatedShowResponse(@Query("api_key") String apiKey);
    @GET("{id}?append_to_response=videos,credits,reviews")
    Call<DetailsResponse> getDetailMovieResponse(@Path("id") int id, @Query("api_key") String apiKey);
}
