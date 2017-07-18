package com.example.lakshya.movies;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.Snackbar;
import android.support.v4.app.Fragment;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;

import com.example.lakshya.movies.network.ApiInterface;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by LAKSHYA on 7/18/2017.
 */

public class MovieListFragment extends Fragment {
    RecyclerView mRecyclerView;
    ArrayList<Movie> mMovies;
    ArrayList<String> movieNames;
    RecyclerAdapter mAdapter;
    MovieFragmentListItemClick mListener;

    void  setMoviesFragmentListItemClick(MovieFragmentListItemClick listener){
        this.mListener = listener;
    }
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.movie_list, container, false);
        mRecyclerView = v.findViewById(R.id.recycler_view);
        mAdapter = new RecyclerAdapter(getContext(), mMovies, new RecyclerAdapter.MovieClickListener() {
            @Override
            public void onItemClick(View view, int position) {
                Movie movie = mMovies.get(position);
                Snackbar.make(mRecyclerView,movie.getTitle(),Snackbar.LENGTH_LONG).show();
            }

            @Override
            public void onAddClicked(int position) {

            }
        });
        mRecyclerView.setAdapter(mAdapter);
        mRecyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        mRecyclerView.addItemDecoration(new DividerItemDecoration(getContext(),DividerItemDecoration.VERTICAL));
        mRecyclerView.setItemAnimator(new DefaultItemAnimator());
        fetchMovies();
        return v;
    }

    private void fetchMovies() {
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("http://api.themoviedb.org/3/")
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        ApiInterface apiInterface =  retrofit.create(ApiInterface.class);
        Call<ArrayList<Movie>> call  =  apiInterface.getPopularMovies(MainActivity.API_KEY);


        call.enqueue(new Callback<ArrayList<Movie>>() {
            @Override
            public void onResponse(Call<ArrayList<Movie>> call, Response<ArrayList<Movie>> response) {
                ArrayList<Movie> movieArrayList = response.body();
                onDownloadComplete(movieArrayList);
            }

            @Override
            public void onFailure(Call<ArrayList<Movie>> call, Throwable t) {

            }
        });


    }
    public void onDownloadComplete(ArrayList<Movie> movieArrayList){
        mMovies.clear();
        mMovies.addAll(movieArrayList);
        for(int i = 0; i < mMovies.size(); i++){
            movieNames.add(mMovies.get(i).getTitle());
        }

        mAdapter.notifyDataSetChanged();

//        for(Course c : courses){
//
//        }

    }
    interface MovieFragmentListItemClick{
        void onListItemClicked(Movie movie);
    }
}
