package com.example.coachticketbookingapp.ui;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;

import com.example.coachticketbookingapp.R;

import java.util.List;

public class TripStatisticAdapter extends ArrayAdapter<TripStatistic> {
    private Context context;
    private List<TripStatistic> statistics;

    public TripStatisticAdapter(@NonNull Context context, List<TripStatistic> statistics) {
        super(context, R.layout.item_trip_statistic, statistics);
        this.context = context;
        this.statistics = statistics;
    }

    @NonNull
    @Override
    public View getView(int position, View convertView, @NonNull ViewGroup parent) {
        if (convertView == null) {
            convertView = LayoutInflater.from(context).inflate(R.layout.item_trip_statistic, parent, false);
        }

        TripStatistic statistic = statistics.get(position);

        TextView tvDeparture = convertView.findViewById(R.id.tvDeparture);
        TextView tvDestination = convertView.findViewById(R.id.tvDestination);
        TextView tvTotalTickets = convertView.findViewById(R.id.tvTotalTickets);

        tvDeparture.setText(statistic.getDeparture());
        tvDestination.setText(statistic.getDestination());
        tvTotalTickets.setText(String.valueOf(statistic.getTotalTickets()));

        // Bắt sự kiện click vào item
        convertView.setOnClickListener(v -> {
            if (context instanceof TicketStatisticTrip) {
                ((TicketStatisticTrip) context).setEditTextFields(statistic.getDeparture(), statistic.getDestination());
            } else {
                Toast.makeText(context, "Không thể cập nhật EditText", Toast.LENGTH_SHORT).show();
            }
        });

        return convertView;
    }
}
