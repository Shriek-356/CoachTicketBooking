package com.example.coachticketbookingapp.ui;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RatingBar;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.coachticketbookingapp.Object.FeedBack;
import com.example.coachticketbookingapp.R;


import java.util.List;

public class FeedBackListAdapter extends RecyclerView.Adapter<FeedBackListAdapter.FeedBackViewHolder> {

    private Context context;
    private List<FeedBack> feedbackList;

    public FeedBackListAdapter(Context context, List<FeedBack> feedbackList) {
        this.context = context;
        this.feedbackList = feedbackList;
    }

    @NonNull
    @Override
    public FeedBackViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.feed_back_cardview,parent, false);
        return new FeedBackViewHolder(view);
    }

    // Gắn dữ liệu vào từng item
    @Override
    public void onBindViewHolder(@NonNull FeedBackViewHolder holder, int position) {
        FeedBack feedback = feedbackList.get(position);

        holder.textViewUserName.setText("User #" + feedback.getUserId());

        holder.textViewContent.setText(feedback.getContent());
        holder.ratingBar.setRating(feedback.getRate());

    }
    @Override
    public int getItemCount() {
        return feedbackList.size();
    }

    public static class FeedBackViewHolder extends RecyclerView.ViewHolder {

        TextView textViewUserName, textViewContent;
        RatingBar ratingBar;

        public FeedBackViewHolder(@NonNull View itemView) {
            super(itemView);
            textViewUserName = itemView.findViewById(R.id.feedback_user_name);
            textViewContent = itemView.findViewById(R.id.feedback_content);
            ratingBar = itemView.findViewById(R.id.feedback_rating_bar);
        }
    }
}
