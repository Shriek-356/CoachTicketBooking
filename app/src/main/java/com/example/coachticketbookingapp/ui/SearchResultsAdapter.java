package com.example.coachticketbookingapp.ui;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import com.example.coachticketbookingapp.R;

import java.util.List;

/*public class SearchResultsAdapter extends RecyclerView.Adapter<SearchResultsAdapter.ViewHolder> {

   /* private List<TripInfoo> tripList;

    public SearchResultsAdapter(List<TripInfoo> tripList) {
        this.tripList = tripList;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_trip_info, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        TripInfoo tripInfoo = tripList.get(position);
        holder.departureTime.setText("Giờ đi: " + tripInfoo.getDepartureTime());
        holder.ticketsAvailable.setText("Số vé có sẵn: " + tripInfoo.getTicketsAvailable());
        holder.coachBrand.setText("Thương hiệu xe: " + tripInfoo.getCoachBrand());
        holder.price.setText("Giá: " + tripInfoo.getPrice());
        holder.distance.setText("Khoảng cách: " + tripInfoo.getDistance() + " km");
    }

    @Override
    public int getItemCount() {
        return tripList.size();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {

        TextView departureTime, ticketsAvailable, coachBrand, price, distance;

        public ViewHolder(View itemView) {
            super(itemView);
            departureTime = itemView.findViewById(R.id.departureTime);
            ticketsAvailable = itemView.findViewById(R.id.ticketsAvailable);
            coachBrand = itemView.findViewById(R.id.coachBrand);
            price = itemView.findViewById(R.id.price);
            distance = itemView.findViewById(R.id.distance);
        }
    }

    */
//}


