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

import com.example.lakshya.movies.Adapters.TvAdapter;
import com.example.lakshya.movies.DetailActivity.DetailsActivityClass;
import com.example.lakshya.movies.network.ApiInterface;
import com.example.lakshya.movies.network.TvResponse;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by LAKSHYA on 7/30/2017.
 */

public class TvFragment extends Fragment {
   public static boolean TvFlag;
    RecyclerView popularRecyclerView;
    RecyclerView topRatedRecyclerView;
    ArrayList<Tv> popularMovies;
    ArrayList<Tv> ratedMovies;
    TvAdapter popularAdapter;
    TvAdapter ratedAdapter;
    MovieFragment.MovieFragmentListItemClick mListener;

    void  setMoviesFragmentListItemClick(MovieFragment.MovieFragmentListItemClick listener){
        this.mListener = listener;
    }
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.tv_list, container, false);
        TvFlag = true;
        MovieFragment.MovieFlag = false;
        popularRecyclerView = v.findViewById(R.id.popular_shows_recycler_view);
        topRatedRecyclerView = v.findViewById(R.id.top_rated_shows_recycler_view);
        popularMovies = new ArrayList<>();
        ratedMovies = new ArrayList<>();
        popularAdapter = new TvAdapter(getContext(), popularMovies, new TvAdapter.MovieClickListener() {
            @Override
            public void onItemClick(View view, int position) {
                Tv tv = popularMovies.get(position);
                Intent intent = new Intent(getContext(),DetailsActivityClass.class);
                intent.putExtra("movie",tv);
                startActivity(intent);
            }

            @Override
            public void onAddClicked(int position) {
                Tv tv = popularMovies.get(position);
                Snackbar.make(topRatedRecyclerView,tv.getName(),Snackbar.LENGTH_LONG).show();

            }
        });
        popularRecyclerView.setAdapter(popularAdapter);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getContext(), GridLayout.HORIZONTAL, false);
        popularRecyclerView.setLayoutManager(linearLayoutManager);
        TvFragment.MyItemDecorator myItemDecorator = new MyItemDecorator(10);
        popularRecyclerView.addItemDecoration(myItemDecorator);
        popularRecyclerView.setItemAnimator(new DefaultItemAnimator());
        //adapter for top rated
        ratedAdapter = new TvAdapter(getContext(), ratedMovies, new TvAdapter.MovieClickListener() {
            @Override
            public void onItemClick(View view, int position) {
                Tv tv = ratedMovies.get(position);
                Intent intent = new Intent(getContext(),DetailsActivityClass.class);
                intent.putExtra("movie",tv);
                startActivity(intent);
            }

            @Override
            public void onAddClicked(int position) {
                Tv tv = ratedMovies.get(position);
                Snackbar.make(topRatedRecyclerView,tv.getName(),Snackbar.LENGTH_LONG).show();

            }
        });
        topRatedRecyclerView.setAdapter(ratedAdapter);
        LinearLayoutManager linearLayoutManagerRated = new LinearLayoutManager(getContext(),LinearLayoutManager.HORIZONTAL, false);
        topRatedRecyclerView.setLayoutManager(linearLayoutManagerRated);
        TvFragment.MyItemDecorator myItemDecoratorRated = new MyItemDecorator(10);
        topRatedRecyclerView.addItemDecoration(myItemDecoratorRated);
        topRatedRecyclerView.setItemAnimator(new DefaultItemAnimator());
        fetchShows();
        return v;
    }

    private void fetchShows() {
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("http://api.themoviedb.org/3/")
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        ApiInterface apiInterface =  retrofit.create(ApiInterface.class);
        Call<TvResponse> call  = apiInterface.getPopularShowResponse(Keys.API_KEY);


        call.enqueue(new Callback<TvResponse>() {
            @Override
            public void onResponse(Call<TvResponse> call, Response<TvResponse> response) {
                TvResponse movieResponse = response.body();
                ArrayList<Tv> movieArrayList = movieResponse.getMovieArrayList();
                onDownloadComplete(movieArrayList);
            }

            @Override
            public void onFailure(Call<TvResponse> call, Throwable t) {

            }
        });
        Call<TvResponse> callTop  = apiInterface.getTopRatedShowResponse(Keys.API_KEY);


        callTop.enqueue(new Callback<TvResponse>() {
            @Override
            public void onResponse(Call<TvResponse> call, Response<TvResponse> response) {
                TvResponse movieResponse = response.body();
                ArrayList<Tv> movieArrayList = movieResponse.getMovieArrayList();
                onDownloadCompleteRated(movieArrayList);
            }

            @Override
            public void onFailure(Call<TvResponse> call, Throwable t) {

            }
        });
    }
    public void onDownloadComplete(ArrayList<Tv> movieArrayList){
        popularMovies.clear();
        popularMovies.addAll(movieArrayList);
        popularAdapter.notifyDataSetChanged();
    }
    public void onDownloadCompleteRated(ArrayList<Tv> movieArrayList){
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
