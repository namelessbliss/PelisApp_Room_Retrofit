package com.nb.pelisapp.ui;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.nb.pelisapp.R;
import com.nb.pelisapp.data.local.db.entity.MovieEntity;
import com.nb.pelisapp.data.remote.ApiConstants;

import java.util.List;

public class MyMovieRecyclerViewAdapter extends RecyclerView.Adapter<MyMovieRecyclerViewAdapter.ViewHolder> {

    private final List<MovieEntity> mValues;

    Context context;

    public MyMovieRecyclerViewAdapter(Context context, List<MovieEntity> items) {
        this.context = context;
        mValues = items;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.fragment_movie, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(final ViewHolder holder, int position) {
        holder.mItem = mValues.get(position);

        Glide.with(context).load(ApiConstants.IMAGE_API_PREFIX + holder.mItem.getPosterPath())
                .into(holder.ivCover);
    }

    @Override
    public int getItemCount() {
        return mValues.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        public final View mView;
        public final ImageView ivCover;
        public MovieEntity mItem;

        public ViewHolder(View view) {
            super(view);
            mView = view;
            ivCover = view.findViewById(R.id.ivCover);
        }

        @Override
        public String toString() {
            return super.toString();
        }
    }
}
