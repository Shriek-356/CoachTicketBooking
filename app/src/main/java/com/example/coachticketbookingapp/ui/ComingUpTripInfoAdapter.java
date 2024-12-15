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

import com.example.coachticketbookingapp.Object.TripInfo;
import com.example.coachticketbookingapp.R;

import java.util.List;

import DataBase.MyDataBase;

public class ComingUpTripInfoAdapter extends RecyclerView.Adapter<ComingUpTripInfoAdapter.ComingUpTripInfoViewHolder> {
    private Context context;
    private List<TripInfo> tripList;

    public ComingUpTripInfoAdapter(Context context, List<TripInfo> tripList) {
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
        TripInfo trip = tripList.get(position);
        holder.departureText.setText(trip.getDeparture());
        holder.destinationText.setText(trip.getDestination());
        holder.departureDateText.setText(trip.getDepartureDate());

        MyDataBase myDataBase = new MyDataBase(context);

        float totalRate = myDataBase.getAverageRating(trip.getCoachID());

        if(totalRate==0){
            holder.txvCMRating.setText("Chưa có đánh giá");
        }
        else{
            List<Float> dsRate = myDataBase.getListRate(trip.getCoachID());
            int quantityFeedBack = dsRate.size();
            holder.txvCMRating.setText("Đánh giá nhà xe: "+String.valueOf(totalRate)+" (" + String.valueOf(quantityFeedBack)+") ");
        }
    }

    @Override
    public int getItemCount() {
        return tripList.size(); // Số lượng item trong danh sách
    }

    // ViewHolder giữ các view trong CardView
    public static class ComingUpTripInfoViewHolder extends RecyclerView.ViewHolder {

        TextView departureText, destinationText, departureDateText,txvCMRating;

        public ComingUpTripInfoViewHolder(@NonNull View itemView) {
            super(itemView);

            // Tìm các view trong CardView
            departureText = itemView.findViewById(R.id.txvCMDeparture);
            destinationText = itemView.findViewById(R.id.txvCMDestination);
            departureDateText = itemView.findViewById(R.id.txvCMDepartureDate);
            txvCMRating = itemView.findViewById(R.id.txvCMRating);
        }
    }
}
