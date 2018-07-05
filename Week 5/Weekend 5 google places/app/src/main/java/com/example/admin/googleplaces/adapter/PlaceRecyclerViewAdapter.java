package com.example.admin.googleplaces.adapter;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.example.admin.googleplaces.activity.PlacesActivity;
import com.example.admin.googleplaces.R;
import com.example.admin.googleplaces.fragments.PlaceListFragment.OnListFragmentInteractionListener;
import com.example.admin.googleplaces.model.Place;

import java.util.List;

public class PlaceRecyclerViewAdapter extends RecyclerView.Adapter<PlaceRecyclerViewAdapter.ViewHolder> {

    private final List<Place> places;
    private final OnListFragmentInteractionListener mListener;

    public PlaceRecyclerViewAdapter(List<Place> items, OnListFragmentInteractionListener listener) {
        places = items;
        mListener = listener;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.item_place, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(final ViewHolder holder, int position) {
        Place place = places.get(position);
        holder.place = place;
        holder.tvName.setText(place.getName());
        holder.tvRating.setText(place.getRating().toString());
        if (place.getPhotos().size() > 0)
            Glide.with(holder.view.getContext()).load(place.getPhotos().get(0)).into(holder.ivIcon);
//        Glide.with(holder.view).load(place.getIcon()).into(holder.ivIcon);

        holder.view.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (null != mListener) {
                    // Notify the active callbacks interface (the activity, if the
                    // fragment is attached to one) that an item has been selected.
                    mListener.onPlaceSelect(holder.place);
                }
            }
        });
        holder.btnDirections.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ((PlacesActivity)holder.view.getContext()).getDirections(holder.place);
            }
        });
    }

    @Override
    public int getItemCount() {
        return places.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        Place place;
        public final View view;
        public final TextView tvName;
        public final TextView tvRating;
        public final ImageView ivIcon;
        public final Button btnDirections;

        public ViewHolder(View view) {
            super(view);
            this.view = view;
            tvName = (TextView) view.findViewById(R.id.tvName);
            tvRating = (TextView) view.findViewById(R.id.tvRating);
            ivIcon = view.findViewById(R.id.ivIcon);
            btnDirections = view.findViewById(R.id.btnDirections);
        }
    }
}
