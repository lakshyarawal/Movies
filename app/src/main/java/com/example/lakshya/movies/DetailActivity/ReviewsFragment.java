package com.example.lakshya.movies.DetailActivity;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;

import com.example.lakshya.movies.Details.ReviewResult;
import com.example.lakshya.movies.Keys;
import com.example.lakshya.movies.MovieFragment;
import com.example.lakshya.movies.R;
import com.example.lakshya.movies.Adapters.ReviewAdapter;
import com.example.lakshya.movies.TvFragment;
import com.example.lakshya.movies.network.ApiInterface;
import com.example.lakshya.movies.network.DetailsResponse;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by LAKSHYA on 7/31/2017.
 */

public class ReviewsFragment extends android.support.v4.app.Fragment{
    RecyclerView recyclerView;
    ArrayList<ReviewResult> reviewResults;
    ReviewAdapter reviewAdapter;
    int id;
    String baseUrl = "https://api.themoviedb.org/3/";
    String base;
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.reviews_fragment, container, false);
        if(MovieFragment.MovieFlag){
            base = baseUrl +"movie/";
        }
        if(TvFragment.TvFlag){
            base = baseUrl +"tv/";
        }
        recyclerView = v.findViewById(R.id.ReviewList);
        id =  getArguments().getInt("id");
        Log.i("TAG", id+"");
        fetchReviews();
        return v;
    }

    private void fetchReviews() {

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
                if(detailsResponse!=null){
                    ArrayList<ReviewResult> reviewList = detailsResponse.reviews.getReviewResults();
                    Log.i("TAG", "onResponse: "+reviewList.get(0).getAuthor());
                    onDownloadCompleteDetails( reviewList);
                }

            }


            @Override
            public void onFailure(Call<DetailsResponse> call, Throwable t) {
                Log.d("error", t.getMessage());
            }
        });

    }

    private void onDownloadCompleteDetails(ArrayList<ReviewResult> castList) {
        Log.i("Tag", "onResponseDownload: "+castList.get(0).getContent());
        reviewResults = new ArrayList<>();
        reviewAdapter = new ReviewAdapter(getActivity(),castList);
        recyclerView.setAdapter(reviewAdapter);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getContext(), LinearLayout.VERTICAL, false);
        recyclerView.setLayoutManager(linearLayoutManager);
        recyclerView.setItemAnimator(new DefaultItemAnimator());
        DividerItemDecoration dividerItemDecoration = new DividerItemDecoration(recyclerView.getContext(),
                linearLayoutManager.getOrientation());
        recyclerView.addItemDecoration(dividerItemDecoration);
    }
}
