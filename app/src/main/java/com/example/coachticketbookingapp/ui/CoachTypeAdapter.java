package com.example.coachticketbookingapp.ui;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import com.example.coachticketbookingapp.R;

import java.util.List;

public class CoachTypeAdapter extends RecyclerView.Adapter<CoachTypeAdapter.ViewHolder> {
    private List<String> coachTypes;
    private OnItemClickListener listener;

    public CoachTypeAdapter(List<String> coachTypes, OnItemClickListener listener) {
        this.coachTypes = coachTypes;
        this.listener = listener;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_coach_type, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        String coachType = coachTypes.get(position);
        holder.bind(coachType, listener);
    }

    @Override
    public int getItemCount() {
        return coachTypes.size();
    }

    public interface OnItemClickListener {
        void onItemClick(String coachType);
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        private TextView textViewCoachType;

        public ViewHolder(View itemView) {
            super(itemView);
            textViewCoachType = itemView.findViewById(R.id.textViewCoachType);
        }

        public void bind(final String coachType, final OnItemClickListener listener) {
            textViewCoachType.setText(coachType);
            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    listener.onItemClick(coachType);
                }
            });
        }
    }
}

