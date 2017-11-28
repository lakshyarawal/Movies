package com.example.lakshya.movies.Adapters;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.lakshya.movies.Details.ReviewResult;
import com.example.lakshya.movies.R;

import java.util.ArrayList;


public class ReviewAdapter extends RecyclerView.Adapter<ReviewAdapter.ReviewViewHolder> {
    private Context mContext;
    private ArrayList<ReviewResult> reviewResults;

    public ReviewAdapter(Context mContext, ArrayList<ReviewResult> reviewResults) {
        this.mContext = mContext;
        this.reviewResults = reviewResults;
    }

    @Override
    public ReviewViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(mContext).inflate(R.layout.review_list_item, parent, false);
        return new ReviewAdapter.ReviewViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(ReviewViewHolder holder, int position) {
ReviewResult reviewResult = reviewResults.get(position);
        holder.authorTextView.setText(reviewResult.getAuthor());
        holder.contentTextView.setText(reviewResult.getContent());
    }

    @Override
    public int getItemCount() {
        if (reviewResults!=null)return reviewResults.size();
        return 0;
    }

    public static class ReviewViewHolder extends RecyclerView.ViewHolder {

        TextView authorTextView,contentTextView;


        public ReviewViewHolder(View itemView) {
            super(itemView);
            authorTextView = itemView.findViewById(R.id.Author);
            contentTextView = itemView.findViewById(R.id.Content);

        }
    }
}
