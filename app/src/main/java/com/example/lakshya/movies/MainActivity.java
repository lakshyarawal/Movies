package com.example.lakshya.movies;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

public class MainActivity extends AppCompatActivity implements MovieListFragment.MovieFragmentListItemClick {
    public static String API_KEY = "1a3eb51df4da80b3982b77445c360672";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        MovieListFragment movieListFragment = (MovieListFragment) getSupportFragmentManager().findFragmentById(R.id.movieListFragment);
        movieListFragment.setMoviesFragmentListItemClick(this);
    }

    @Override
    public void onListItemClicked(Movie movie) {

    }
}
