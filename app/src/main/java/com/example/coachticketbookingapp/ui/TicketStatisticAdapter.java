package com.example.coachticketbookingapp.ui;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.example.coachticketbookingapp.R;

import java.util.List;

public class TicketStatisticAdapter extends BaseAdapter {
    private Context context;
    private List<TicketStatisticItem> data;

    public TicketStatisticAdapter(Context context, List<TicketStatisticItem> data) {
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
            convertView = LayoutInflater.from(context).inflate(R.layout.list_statistic_item, parent, false);
        }

        TicketStatisticItem item = data.get(position);

        TextView tvUserName = convertView.findViewById(R.id.tvUserName);
        TextView tvTripDetails = convertView.findViewById(R.id.tvTripDetails);
        TextView tvBookingDate = convertView.findViewById(R.id.tvBookingDate);

        tvUserName.setText(item.getUserName());
        tvTripDetails.setText(item.getTripDetails());
        tvBookingDate.setText(item.getBookingDate());

        // Hiển thị thông tin email và số điện thoại (nếu cần thiết)
        // Nếu muốn hiển thị thêm email và phone trong item, bạn có thể thêm TextView tương ứng vào layout và set giá trị như sau:
        // TextView tvEmail = convertView.findViewById(R.id.tvEmail);
        // TextView tvPhone = convertView.findViewById(R.id.tvPhone);
        // tvEmail.setText(item.getEmail());
        // tvPhone.setText(item.getPhone());

        return convertView;
    }
}
