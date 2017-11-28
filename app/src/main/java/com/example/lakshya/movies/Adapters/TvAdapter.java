package com.example.lakshya.movies.Adapters;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.lakshya.movies.R;
import com.example.lakshya.movies.Tv;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;

public class TvAdapter extends RecyclerView.Adapter<TvAdapter.MovieViewHolder> {
    private Context mContext;
    private ArrayList<Tv> mMovies;
    private MovieClickListener mListener;

    public interface MovieClickListener {
        void onItemClick(View view, int position);
        void onAddClicked(int position);
    }


    public TvAdapter(Context context, ArrayList<Tv> movies, MovieClickListener listener) {
        mContext = context;
        mMovies = movies;
        mListener = listener;
    }

    @Override
    public MovieViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(mContext).inflate(R.layout.movie_list_item_layout, parent, false);
        return new MovieViewHolder(itemView, mListener);
    }

    @Override
    public void onBindViewHolder(MovieViewHolder holder, int position) {
        Tv movie = mMovies.get(position);
        holder.titleTextView.setText(movie.getName());
        Picasso.with(mContext)
                .load("http://image.tmdb.org/t/p/w500/"+movie.getPosterPath())
                .into(holder.imageView);
    }

    @Override
    public int getItemCount() {
        if (mMovies!=null)return mMovies.size();
        else return 0;
    }

    public static class MovieViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

        TextView titleTextView;
        ImageView imageView ;
        Button addButton;
        MovieClickListener mMovieClickListener;

        public MovieViewHolder(View itemView, MovieClickListener listener) {
            super(itemView);
            itemView.setOnClickListener(this);
            mMovieClickListener = listener;
            imageView = itemView.findViewById(R.id.MoviePoster);
            titleTextView = itemView.findViewById(R.id.TitleText);
            addButton = itemView.findViewById(R.id.AddButton);
            addButton.setOnClickListener(this);
        }

        @Override
        public void onClick(View view) {
            int id = view.getId();
            int position = getAdapterPosition();
            if(position != RecyclerView.NO_POSITION){
                if(id == R.id.Movielayout){
                    mMovieClickListener.onItemClick(view,position);
                }
                else if(id == R.id.AddButton){
                    mMovieClickListener.onAddClicked(position);
                }
            }

        }
    }
    }

