package com.example.coachticketbookingapp.ui;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.coachticketbookingapp.Object.TripInfo;
import com.example.coachticketbookingapp.R;

import java.util.ArrayList;
import com.example.coachticketbookingapp.Object.Ticket;

public class TripListFoundAdapter extends RecyclerView.Adapter<TripListFoundAdapter.TripListFoundViewHolder>  {
    Context context;
    private ArrayList<TripInfo> dsTrip = new ArrayList<>();
    private ButtonClickListener buttonClickListener;

    public interface ButtonClickListener {
        void onButtonClick(int position);
    }
    public TripListFoundAdapter(Context context, ArrayList<TripInfo> dsTrip, ButtonClickListener buttonClickListener) {
        this.context = context;
        this.dsTrip = dsTrip;
        this.buttonClickListener=buttonClickListener;
    }
    @NonNull
    @Override
    public TripListFoundViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.trip_list__found_cardview,parent,false);
        return new TripListFoundViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull TripListFoundViewHolder holder, int position) {
        TripInfo trip= dsTrip.get(position);
        holder.imvIcon.setImageResource(R.drawable.bus);
        holder.txvStartTime.setText(trip.getDepatureTime());
        holder.txvEndTime.setText(trip.getDestinationTime());
        holder.txvDeparture.setText(trip.getDeparture());
        holder.txvDestination.setText(trip.getDestination());
        //holder.txvPrice.setText(String.format("%,d", trip.getPrice())); bug
        String location = "Tu " +trip.getFirstLocation() +" den " +trip.getSecondLocation();
        holder.txvLocation.setText(location);

        holder.btnGetChoice.setOnClickListener(view -> {
            if (buttonClickListener != null) {
                buttonClickListener.onButtonClick(position);
            }
        });

    }
    @Override
    public int getItemCount() {
        if(dsTrip != null){
            return dsTrip.size();
        }
        return 0;
    }
    public class TripListFoundViewHolder extends RecyclerView.ViewHolder{
        ImageView imvIcon;
        TextView txvStartTime;
        TextView txvEndTime;
        TextView txvDeparture;
        TextView txvDestination;
        TextView txvPrice;
        TextView txvLocation;
        Button btnGetChoice;

        public TripListFoundViewHolder (@NonNull View itemview){
            super(itemview);
           imvIcon = itemview.findViewById(R.id.imvIcon);
           txvStartTime = itemview.findViewById(R.id.txvStartTime);
           txvEndTime = itemview.findViewById(R.id.txvEndTime);
           txvDeparture = itemview.findViewById(R.id.txvDeparture);
           txvDestination = itemview.findViewById(R.id.txvDestination);
           txvPrice = itemview.findViewById(R.id.txvPrice);
           txvLocation = itemview.findViewById(R.id.txvLocation);
           btnGetChoice = itemview.findViewById(R.id.btnGetChoice);

        }

    }
}
