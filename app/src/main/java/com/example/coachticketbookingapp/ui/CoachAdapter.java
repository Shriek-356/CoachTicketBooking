package com.example.coachticketbookingapp.ui;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import com.example.coachticketbookingapp.R;

import java.util.List;

public class CoachAdapter extends RecyclerView.Adapter<CoachAdapter.ViewHolder> {

    private List<String> coachList;
    private OnCoachClickListener listener;

    public interface OnCoachClickListener {
        void onCoachClick(String coachID);
    }

    public CoachAdapter(List<String> coachList, OnCoachClickListener listener) {
        this.coachList = coachList;
        this.listener = listener;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_coach, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        String coach = coachList.get(position);
        holder.coachTextView.setText(coach);

        // Khi item được click, gọi phương thức listener
        holder.itemView.setOnClickListener(v -> listener.onCoachClick(coach.split(" - ")[1]));
    }

    @Override
    public int getItemCount() {
        return coachList.size();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {
        TextView coachTextView;

        public ViewHolder(View itemView) {
            super(itemView);
            coachTextView = itemView.findViewById(R.id.coachTextView);
        }
    }
}

