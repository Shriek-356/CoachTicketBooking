package com.example.coachticketbookingapp.ui;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.coachticketbookingapp.R;

import java.util.List;

public class ProvinceAdapter extends RecyclerView.Adapter<ProvinceAdapter.ViewHolder> {

    private final List<String> provinces;
    private final OnItemClickListener listener;

    public interface OnItemClickListener {
        void onItemClick(String province);
    }

    public ProvinceAdapter(List<String> provinces, OnItemClickListener listener) {
        this.provinces = provinces;
        this.listener = listener;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.layout_province_item, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        String province = provinces.get(position);
        holder.tvProvince.setText(province);
        holder.itemView.setOnClickListener(v -> listener.onItemClick(province));
    }

    @Override
    public int getItemCount() {
        return provinces.size();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {
        TextView tvProvince;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            tvProvince = itemView.findViewById(R.id.tvProvince);
        }
    }
}

