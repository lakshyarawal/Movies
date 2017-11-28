package com.example.lakshya.movies;

import android.content.Intent;
import android.graphics.Rect;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.Snackbar;
import android.support.v4.app.Fragment;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.GridLayout;

import com.example.lakshya.movies.Adapters.MovieAdapter;
import com.example.lakshya.movies.DetailActivity.DetailsActivityClass;
import com.example.lakshya.movies.network.ApiInterface;
import com.example.lakshya.movies.network.MovieResponse;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class MovieFragment extends Fragment {
    public static boolean MovieFlag;
    RecyclerView popularRecyclerView;
    RecyclerView topRatedRecyclerView;
    ArrayList<Movie> popularMovies;
    ArrayList<Movie> ratedMovies;
    MovieAdapter popularAdapter;
    MovieAdapter ratedAdapter;
    MovieFragmentListItemClick mListener;

    void  setMoviesFragmentListItemClick(MovieFragmentListItemClick listener){
        this.mListener = listener;
    }
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.movie_list, container, false);
        MovieFlag = true;
        TvFragment.TvFlag = false;
        popularRecyclerView = v.findViewById(R.id.popular_recycler_view);
        topRatedRecyclerView = v.findViewById(R.id.top_rated_recycler_view);
        popularMovies = new ArrayList<>();
        ratedMovies = new ArrayList<>();
        popularAdapter = new MovieAdapter(getContext(), popularMovies, new MovieAdapter.MovieClickListener() {
            @Override
            public void onItemClick(View view, int position) {
                Movie movie = popularMovies.get(position);
                Intent intent = new Intent(getContext(),DetailsActivityClass.class);
                intent.putExtra("movie",movie);
                startActivity(intent);
            }

            @Override
            public void onAddClicked(int position) {
                Movie movie = popularMovies.get(position);
                Snackbar.make(popularRecyclerView,movie.getTitle(),Snackbar.LENGTH_LONG).show();

            }
        });
        popularRecyclerView.setAdapter(popularAdapter);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getContext(), GridLayout.HORIZONTAL, false);
        popularRecyclerView.setLayoutManager(linearLayoutManager);
        MyItemDecorator myItemDecorator = new MyItemDecorator(10);
        popularRecyclerView.addItemDecoration(myItemDecorator);
        popularRecyclerView.setItemAnimator(new DefaultItemAnimator());
        //adapter for top rated
        ratedAdapter = new MovieAdapter(getContext(), ratedMovies, new MovieAdapter.MovieClickListener() {
            @Override
            public void onItemClick(View view, int position) {
                Movie movie = ratedMovies.get(position);
                Intent intent = new Intent(getContext(),DetailsActivityClass.class);
                intent.putExtra("movie",movie);
                startActivity(intent);
            }

            @Override
            public void onAddClicked(int position) {
                Movie movie = ratedMovies.get(position);
                Snackbar.make(topRatedRecyclerView,movie.getTitle(),Snackbar.LENGTH_LONG).show();

            }
        });
        topRatedRecyclerView.setAdapter(ratedAdapter);
        LinearLayoutManager linearLayoutManagerRated = new LinearLayoutManager(getContext(),LinearLayoutManager.HORIZONTAL, false);
        topRatedRecyclerView.setLayoutManager(linearLayoutManagerRated);
        MyItemDecorator myItemDecoratorRated = new MyItemDecorator(10);
        topRatedRecyclerView.addItemDecoration(myItemDecoratorRated);
        topRatedRecyclerView.setItemAnimator(new DefaultItemAnimator());
        fetchMovies();
        return v;
    }

    private void fetchMovies() {
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("http://api.themoviedb.org/3/")
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        ApiInterface apiInterface =  retrofit.create(ApiInterface.class);
        Call<MovieResponse> call  = apiInterface.getPopularMovieResponse(Keys.API_KEY);


        call.enqueue(new Callback<MovieResponse>() {
            @Override
            public void onResponse(Call<MovieResponse> call, Response<MovieResponse> response) {
                MovieResponse movieResponse = response.body();
                ArrayList<Movie> movieArrayList = movieResponse.getMovieArrayList();
                onDownloadComplete(movieArrayList);
            }

            @Override
            public void onFailure(Call<MovieResponse> call, Throwable t) {

            }
        });
        Call<MovieResponse> callTop  = apiInterface.getTopRatedMovieResponse(Keys.API_KEY);


        callTop.enqueue(new Callback<MovieResponse>() {
            @Override
            public void onResponse(Call<MovieResponse> call, Response<MovieResponse> response) {
                MovieResponse movieResponse = response.body();
                ArrayList<Movie> movieArrayList = movieResponse.getMovieArrayList();
                onDownloadCompleteRated(movieArrayList);
            }

            @Override
            public void onFailure(Call<MovieResponse> call, Throwable t) {

            }
        });


    }
    public void onDownloadComplete(ArrayList<Movie> movieArrayList){
        popularMovies.clear();
        popularMovies.addAll(movieArrayList);
       popularAdapter.notifyDataSetChanged();
    }
    public void onDownloadCompleteRated(ArrayList<Movie> movieArrayList){
        ratedMovies.clear();
        ratedMovies.addAll(movieArrayList);
        ratedAdapter.notifyDataSetChanged();
    }
    interface MovieFragmentListItemClick{
        void onListItemClicked(Movie movie);
    }

    private class MyItemDecorator extends RecyclerView.ItemDecoration{
        int mSpace;
        MyItemDecorator(int space){
            mSpace = space;
        }

        @Override
        public void getItemOffsets(Rect outRect, View view, RecyclerView parent, RecyclerView.State state) {
            outRect.right = mSpace;
            outRect.left = mSpace;
        }
    }
}
