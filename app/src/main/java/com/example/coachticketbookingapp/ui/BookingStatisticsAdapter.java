package com.example.coachticketbookingapp.ui;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.example.coachticketbookingapp.R;

import java.util.List;

public class BookingStatisticsAdapter extends BaseAdapter {
    private Context context;
    private List<BookingStatisticsItem> data;

    public BookingStatisticsAdapter(Context context, List<BookingStatisticsItem> data) {
        this.context = context;
        this.data = data;
    }

    @Override
    public int getCount() {
        return data.size();
    }

    @Override
    public Object getItem(int position) {
        return data.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        if (convertView == null) {
            convertView = LayoutInflater.from(context).inflate(R.layout.statistic_item, parent, false);
        }

        BookingStatisticsItem item = data.get(position);

        TextView tvBookingDate = convertView.findViewById(R.id.tvBookingDate);
        TextView tvTotalTickets = convertView.findViewById(R.id.tvTotalTickets);

        tvBookingDate.setText(item.getBookingDate());
        tvTotalTickets.setText(String.valueOf(item.getTotalTickets()));

        return convertView;
    }
}

