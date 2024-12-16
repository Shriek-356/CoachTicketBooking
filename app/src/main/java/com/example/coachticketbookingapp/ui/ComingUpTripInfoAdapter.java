package com.example.coachticketbookingapp.ui;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import com.example.coachticketbookingapp.Object.PopularTripInfo;
import com.example.coachticketbookingapp.Object.TripInfo;
import com.example.coachticketbookingapp.R;

import java.util.List;

import DataBase.MyDataBase;

public class ComingUpTripInfoAdapter extends RecyclerView.Adapter<ComingUpTripInfoAdapter.ComingUpTripInfoViewHolder> {
    private Context context;
    private List<PopularTripInfo> tripList;

    public ComingUpTripInfoAdapter(Context context, List<PopularTripInfo> tripList) {
        this.context=context;
        this.tripList = tripList;
    }

    @NonNull
    @Override
    public ComingUpTripInfoViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        // Inflate layout cho CardView
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.coming_up_trip_cardview, parent, false);
        return new ComingUpTripInfoViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ComingUpTripInfoViewHolder holder, int position) {
         PopularTripInfo trip = tripList.get(position);

        holder.departureText.setText(trip.getDeparture());
        holder.destinationText.setText(trip.getDestination());
        holder.txvCMRating.setText("Tổng lượt đánh giá: "+String.valueOf(trip.getRatingCount()));
    }

    @Override
    public int getItemCount() {
        return tripList.size(); // Số lượng item trong danh sách
    }

    public static class ComingUpTripInfoViewHolder extends RecyclerView.ViewHolder {

        TextView departureText, destinationText, departureDateText,txvCMRating,txvNhaXe;

        public ComingUpTripInfoViewHolder(@NonNull View itemView) {
            super(itemView);

            departureText = itemView.findViewById(R.id.txvCMDeparture);
            destinationText = itemView.findViewById(R.id.txvCMDestination);
            txvCMRating = itemView.findViewById(R.id.txvCMRating);
        }
    }
}
