package com.example.lakshya.movies.DetailActivity;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.lakshya.movies.Details.Genre;
import com.example.lakshya.movies.Keys;
import com.example.lakshya.movies.Movie;
import com.example.lakshya.movies.MovieFragment;
import com.example.lakshya.movies.R;


import com.example.lakshya.movies.TvFragment;
import com.example.lakshya.movies.network.ApiInterface;
import com.example.lakshya.movies.network.DetailsResponse;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class InfoFragment extends Fragment {
    TextView overView,genreTextView;
    ImageView moviePoster;
    Movie movie;
    String key;
    int id;
    String baseUrl = "https://api.themoviedb.org/3/";
    String base;

    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View v= inflater.inflate(R.layout.activity_movie_details, container, false);
        id =  getArguments().getInt("id");
        moviePoster = (ImageView) v.findViewById(R.id.MoviePosterView);
        overView =(TextView) v.findViewById(R.id.MovieOverview);
        if(MovieFragment.MovieFlag){
            base = baseUrl +"movie/";

        }
        if(TvFragment.TvFlag){
            base = baseUrl +"tv/";
        }
        genreTextView = (TextView)v.findViewById(R.id.MovieGenre);
        fetchDetails();
        return v;
    }
    private void fetchDetails() {
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(base)
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        ApiInterface apiInterface = retrofit.create(ApiInterface.class);
        Call<DetailsResponse> call = apiInterface.getDetailMovieResponse(id, Keys.API_KEY);

        call.enqueue(new Callback<DetailsResponse>() {
            @Override
            public void onResponse(Call<DetailsResponse> call, Response<DetailsResponse> response) {
                DetailsResponse detailsResponse = response.body();
                ArrayList<Genre> genreArrayList = detailsResponse.getGenres();
                String posterPath = detailsResponse.getPoster_path();
                String overview = detailsResponse.getOverview();
                onDownloadCompleteDetails( genreArrayList,posterPath,overview);

            }


            @Override
            public void onFailure(Call<DetailsResponse> call, Throwable t) {
                Log.d("error", t.getMessage());
            }
        });

    }

    private void onDownloadCompleteDetails(ArrayList<Genre> genreArrayList, String posterpath,String overview) {

        getGenres(genreArrayList);
        Picasso.with(getActivity())
                .load("http://image.tmdb.org/t/p/w500/"+posterpath)
                .centerCrop()
                .fit()
                .into(moviePoster);
        overView.setText(overview);
    }
    private void getGenres(ArrayList<Genre> genres) {
        String genreList="";
        for(Genre genre:genres){
            genreList = genreList+ genre.getName()+", ";
        }
        String genreList2 = genreList.substring(0,genreList.length()-2);
        genreTextView.setText(genreList2);
    }

}
