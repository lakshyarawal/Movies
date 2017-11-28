package com.example.lakshya.movies.DetailActivity;

import android.content.res.Resources;
import android.graphics.Rect;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.util.TypedValue;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;

import com.example.lakshya.movies.Adapters.CastListAdapter;
import com.example.lakshya.movies.Details.Cast;
import com.example.lakshya.movies.Keys;
import com.example.lakshya.movies.MovieFragment;
import com.example.lakshya.movies.R;
import com.example.lakshya.movies.TvFragment;
import com.example.lakshya.movies.network.ApiInterface;
import com.example.lakshya.movies.network.DetailsResponse;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;


public class CastFragment extends android.support.v4.app.Fragment {
    RecyclerView recyclerView;
    ArrayList<Cast> castArrayList;
    CastListAdapter castListAdapter;
    int id;
    String baseUrl = "https://api.themoviedb.org/3/";
   String base;

    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        if(MovieFragment.MovieFlag){
          base = baseUrl +"movie/";
        }
        if(TvFragment.TvFlag){
            base = baseUrl +"tv/";
        }
    id =  getArguments().getInt("id");
        Log.i("Tag1", id+"");
        View v= inflater.inflate(R.layout.cast_list, container, false);
        castArrayList = new ArrayList<>();
        recyclerView = v.findViewById(R.id.CastList);
        fetchCast();
        return v;
    }

    private void fetchCast() {
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
                ArrayList<Cast> castList = detailsResponse.credits.getCast();
                Log.i("Tag", "onResponse: "+castList.get(0).getName());
                onDownloadCompleteDetails( castList);

            }


            @Override
            public void onFailure(Call<DetailsResponse> call, Throwable t) {
                Log.d("error", t.getMessage());
            }
        });

    }

    private void onDownloadCompleteDetails(ArrayList<Cast> castList) {
        Log.i("Tag", "onResponseDownload: "+castList.get(0).getName());
        castListAdapter = new CastListAdapter(getActivity(),castList);
        recyclerView.setAdapter(castListAdapter);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getContext(), LinearLayout.VERTICAL, false);
        recyclerView.setLayoutManager(linearLayoutManager);
        recyclerView.setItemAnimator(new DefaultItemAnimator());
//        RecyclerView.LayoutManager mLayoutManager = new GridLayoutManager(getActivity(), 2);
//        recyclerView.setLayoutManager(mLayoutManager);
//        recyclerView.addItemDecoration(new GridSpacingItemDecoration(2, dpToPx(10), true));
//        recyclerView.setItemAnimator(new DefaultItemAnimator());

    }
    public class GridSpacingItemDecoration extends RecyclerView.ItemDecoration {

        private int spanCount;
        private int spacing;
        private boolean includeEdge;

        public GridSpacingItemDecoration(int spanCount, int spacing, boolean includeEdge) {
            this.spanCount = spanCount;
            this.spacing = spacing;
            this.includeEdge = includeEdge;
        }

        @Override
        public void getItemOffsets(Rect outRect, View view, RecyclerView parent, RecyclerView.State state) {
            int position = parent.getChildAdapterPosition(view); // item position
            int column = position % spanCount; // item column

            if (includeEdge) {
                outRect.left = spacing - column * spacing / spanCount; // spacing - column * ((1f / spanCount) * spacing)
                outRect.right = (column + 1) * spacing / spanCount; // (column + 1) * ((1f / spanCount) * spacing)

                if (position < spanCount) { // top edge
                    outRect.top = spacing;
                }
                outRect.bottom = spacing; // item bottom
            } else {
                outRect.left = column * spacing / spanCount; // column * ((1f / spanCount) * spacing)
                outRect.right = spacing - (column + 1) * spacing / spanCount; // spacing - (column + 1) * ((1f /    spanCount) * spacing)
                if (position >= spanCount) {
                    outRect.top = spacing; // item top
                }
            }
        }
    }

    /**
     * Converting dp to pixel
     */
    private int dpToPx(int dp) {
        Resources r = getResources();
        return Math.round(TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, dp, r.getDisplayMetrics()));
    }

}
