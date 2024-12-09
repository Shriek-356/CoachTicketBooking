package com.example.coachticketbookingapp.ui;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.example.coachticketbookingapp.R;

import java.util.List;

public class BusTripAdapter extends ArrayAdapter<BusTripInfo> {
    private Context context;
    private List<BusTripInfo> busTripInfoList;

    public BusTripAdapter(Context context, List<BusTripInfo> busTripInfoList) {
        super(context, R.layout.list_item_coach, busTripInfoList);
        this.context = context;
        this.busTripInfoList = busTripInfoList;
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        if (convertView == null) {
            convertView = LayoutInflater.from(context).inflate(R.layout.list_item_coach, parent, false);
        }

        BusTripInfo busTripInfo = busTripInfoList.get(position);

        // Initialize TextViews
        TextView textBusBrand = convertView.findViewById(R.id.textCoachBrand);
        TextView textTotalSeat = convertView.findViewById(R.id.textTotalSeat);
        TextView textLicensePlate = convertView.findViewById(R.id.textLicensePlate);
        TextView textType = convertView.findViewById(R.id.textType);

        // Set data from BusTripInfo to TextViews
        textBusBrand.setText(busTripInfo.getBusBrand());
        textTotalSeat.setText(String.valueOf(busTripInfo.getTotalSeat()));
        textLicensePlate.setText(busTripInfo.getLicensePlate());
        textType.setText(busTripInfo.getType());

        return convertView;
    }
}
