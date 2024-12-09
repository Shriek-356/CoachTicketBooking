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

public class CoachTripAdapter extends ArrayAdapter<CoachTripInfo> {
    private Context context;
    private List<CoachTripInfo> coachTripInfoList;

    public CoachTripAdapter(Context context, List<CoachTripInfo> coachTripInfoList) {
        super(context, R.layout.list_item_coach, coachTripInfoList);
        this.context = context;
        this.coachTripInfoList = coachTripInfoList;
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        if (convertView == null) {
            convertView = LayoutInflater.from(context).inflate(R.layout.list_item_coach, parent, false);
        }

        CoachTripInfo coachTripInfo = coachTripInfoList.get(position);

        // Initialize TextViews
        TextView textCoachBrand = convertView.findViewById(R.id.textCoachBrand);
        TextView textTotalSeat = convertView.findViewById(R.id.textTotalSeat);
        TextView textLicensePlate = convertView.findViewById(R.id.textLicensePlate);
        TextView textType = convertView.findViewById(R.id.textType);

        // Set data from CoachTripInfo to TextViews
        textCoachBrand.setText(coachTripInfo.getCoachBrand());
        textTotalSeat.setText(String.valueOf(coachTripInfo.getTotalSeat()));
        textLicensePlate.setText(coachTripInfo.getLicensePlate());
        textType.setText(coachTripInfo.getType());

        return convertView;
    }
}

