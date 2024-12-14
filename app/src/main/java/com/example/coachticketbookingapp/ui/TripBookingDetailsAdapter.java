package com.example.coachticketbookingapp.ui;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.coachticketbookingapp.Object.TripBookingDetails;
import com.example.coachticketbookingapp.R;

import java.text.NumberFormat;
import java.util.List;
import java.util.Locale;

public class TripBookingDetailsAdapter extends RecyclerView.Adapter<TripBookingDetailsAdapter.TripBookingDetailsViewHolder> {
    private Context context;
    private List<TripBookingDetails> dsTrip;
    private OnItemClickListener mListener;

    public TripBookingDetailsAdapter(Context context, List<TripBookingDetails> dsTrip, OnItemClickListener listener) {
        this.context = context;
        this.dsTrip = dsTrip;
        mListener=listener;
    }

    public interface OnItemClickListener {
        void onItemClick(TripBookingDetails tripInfo);
    }

    @NonNull
    @Override
    public TripBookingDetailsViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.ticket_cardview,parent,false);

        return new TripBookingDetailsViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull TripBookingDetailsViewHolder holder, int position) {
        TripBookingDetails tripBookingDetails = dsTrip.get(position);
        if(tripBookingDetails==null){
            return;
        }
        String departure = tripBookingDetails.getDeparture();
        String destination = tripBookingDetails.getDestination();

        holder.txvLocation.setText("Vé xe từ " + departure + " đến " + destination);
        holder.txvFromTo.setText(departure +" -> " + destination);
        NumberFormat numberFormat = NumberFormat.getCurrencyInstance(new Locale("vi", "VN"));
        holder.txvPrice.setText(numberFormat.format(tripBookingDetails.getPrice()));

        holder.itemView.setOnClickListener(view -> {
            if(mListener!=null){
                mListener.onItemClick(tripBookingDetails);
            }
        });

    }
    @Override
    public int getItemCount() {
        return dsTrip.size();
    }

    public class TripBookingDetailsViewHolder extends RecyclerView.ViewHolder{
        TextView txvLocation;
        TextView txvFromTo;
        TextView txvPrice;

        public TripBookingDetailsViewHolder(@NonNull View itemView) {
            super(itemView);
            txvLocation = itemView.findViewById(R.id.txvTicketLocationn);
            txvFromTo = itemView.findViewById(R.id.txvTicketFromTo);
            txvPrice = itemView.findViewById(R.id.txvTicketPricee);
        }
    }
}
