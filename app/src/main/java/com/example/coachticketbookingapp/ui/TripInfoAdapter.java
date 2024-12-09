package com.example.coachticketbookingapp.ui;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import com.example.coachticketbookingapp.Object.TripInfo;
import com.example.coachticketbookingapp.R;

import java.util.List;

public class TripInfoAdapter extends ArrayAdapter<Trip> {

    public TripInfoAdapter(Context context, List<Trip> trips) {
        super(context, 0, trips);
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        if (convertView == null) {
            convertView = LayoutInflater.from(getContext()).inflate(R.layout.item_trip, parent, false);
        }

        Trip tripInfo = getItem(position);

        TextView tvDeparture = convertView.findViewById(R.id.tvDeparture);
        TextView tvDestination = convertView.findViewById(R.id.tvDestination);
        TextView tvDepartureTime = convertView.findViewById(R.id.tvDepartureTime);
        TextView tvDepartureDate = convertView.findViewById(R.id.tvDepartureDate);

        tvDeparture.setText(tripInfo.getDeparture());
        tvDestination.setText(tripInfo.getDestination());
        tvDepartureTime.setText(tripInfo.getDepartureTime());
        tvDepartureDate.setText(tripInfo.getDepartureDate());

        return convertView;
    }
}

