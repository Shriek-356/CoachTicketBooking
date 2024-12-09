package com.example.coachticketbookingapp.ui;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.coachticketbookingapp.R;

import java.util.List;

public class LocationAdapter extends RecyclerView.Adapter<LocationAdapter.LocationViewHolder> {

    private List<String> locationList;
    private OnLocationClickListener listener;

    public interface OnLocationClickListener {
        void onLocationClick(String location);
    }

    public LocationAdapter(List<String> locationList, OnLocationClickListener listener) {
        this.locationList = locationList;
        this.listener = listener;
    }

    @Override
    public LocationViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_location, parent, false);
        return new LocationViewHolder(view);
    }

    @Override
    public void onBindViewHolder(LocationViewHolder holder, int position) {
        String location = locationList.get(position);
        holder.textViewLocation.setText(location);

        holder.itemView.setOnClickListener(v -> listener.onLocationClick(location));
    }

    @Override
    public int getItemCount() {
        return locationList.size();
    }

    public static class LocationViewHolder extends RecyclerView.ViewHolder {
        public TextView textViewLocation;

        public LocationViewHolder(View itemView) {
            super(itemView);
            textViewLocation = itemView.findViewById(R.id.textViewLocation);
        }
    }
}


