package com.example.lakshya.movies.Adapters;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.lakshya.movies.Details.Cast;
import com.example.lakshya.movies.R;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;

/**
 * Created by LAKSHYA on 7/31/2017.
 */

public class CastListAdapter extends RecyclerView.Adapter<CastListAdapter.CastViewHolder> {
    private Context mContext;
     private ArrayList<Cast> castArrayList;

    public CastListAdapter(Context context, ArrayList<Cast> mCastArrayList) {
        mContext = context;
        castArrayList = mCastArrayList;
    }
    public CastListAdapter.CastViewHolder onCreateViewHolder(ViewGroup parent,int viewType ) {
        View itemView = LayoutInflater.from(mContext).inflate(R.layout.cast_list_item, parent, false);
        return new CastListAdapter.CastViewHolder(itemView);
    }
    @Override
    public void onBindViewHolder(CastListAdapter.CastViewHolder holder, int position) {
        Cast cast = castArrayList.get(position);
        holder.nameTextView.setText(cast.getName());
        holder.characterTextView.setText("(as "+cast.getCharacter()+")");
        Picasso.with(mContext)
                .load("http://image.tmdb.org/t/p/w500/"+cast.getProfile_path())
                .into(holder.imageView);
    }

    @Override
    public int getItemCount() {
        if (castArrayList!=null)return castArrayList.size();
        return 0;
    }

    public static class CastViewHolder extends RecyclerView.ViewHolder {

        TextView nameTextView,characterTextView;
        ImageView imageView ;


        public CastViewHolder(View itemView) {
            super(itemView);
            imageView = itemView.findViewById(R.id.castPhoto);
            nameTextView = itemView.findViewById(R.id.CastName);
            characterTextView = itemView.findViewById(R.id.CastCharacter);

        }
    }

}
