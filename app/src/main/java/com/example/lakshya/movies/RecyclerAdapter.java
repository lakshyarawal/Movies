package com.example.lakshya.movies;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import java.util.ArrayList;

/**
 * Created by LAKSHYA on 7/18/2017.
 */

public class RecyclerAdapter extends RecyclerView.Adapter<RecyclerAdapter.MovieViewHolder> {
    private Context mContext;
    private ArrayList<Movie> mMovies;
    private MovieClickListener mListener;

    public interface MovieClickListener {
        void onItemClick(View view, int position);
        void onAddClicked(int position);
    }


    public RecyclerAdapter(Context context, ArrayList<Movie> notes, MovieClickListener listener) {
        mContext = context;
        mMovies = notes;
        mListener = listener;
    }

    @Override
    public MovieViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(mContext).inflate(R.layout.movie_list_item_layout, parent, false);
        return new MovieViewHolder(itemView, mListener);
    }

    @Override
    public void onBindViewHolder(MovieViewHolder holder, int position) {
        Movie movie = mMovies.get(position);
        holder.titleTextView.setText(movie.getTitle());
    }

    @Override
    public int getItemCount() {
        if (mMovies!=null)return mMovies.size();
        else return 0;
    }

    public static class MovieViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

        TextView titleTextView;
        Button addButton;
        MovieClickListener mMovieClickListener;

        public MovieViewHolder(View itemView, MovieClickListener listener) {
            super(itemView);
            itemView.setOnClickListener(this);
            mMovieClickListener = listener;
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

