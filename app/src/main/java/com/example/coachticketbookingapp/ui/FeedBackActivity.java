package com.example.coachticketbookingapp.ui;

import android.content.ContentValues;
import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RatingBar;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.example.coachticketbookingapp.Object.TripBookingDetails;
import com.example.coachticketbookingapp.Object.TripInfo;
import com.example.coachticketbookingapp.Object.User;
import com.example.coachticketbookingapp.R;

import DataBase.MyDataBase;

public class FeedBackActivity extends AppCompatActivity {
    private RatingBar ratingBar;
    private EditText etFeedbackContent;
    private Button btnSubmitFeedback;
    private TripBookingDetails tripBookingDetails;
    private User user;
    private TripInfo tripInfo;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_feed_back);

        Intent intent = getIntent();
        tripBookingDetails = (TripBookingDetails) intent.getSerializableExtra("tripbookingdetails");
        user = (User) intent.getSerializableExtra("user");
        ratingBar=findViewById(R.id.ratingBar);
        etFeedbackContent = findViewById(R.id.etFeedbackContent);
        btnSubmitFeedback = findViewById(R.id.btnSubmitFeedback);

        btnSubmitFeedback.setOnClickListener(view -> submitFeedback());
    }

    private void submitFeedback() {
        float rating = ratingBar.getRating();
        String feedbackContent = etFeedbackContent.getText().toString();

        if (rating == 0 || feedbackContent.isEmpty()) {
            Toast.makeText(this, "Vui lòng chọn đánh giá và nhập phản hồi!", Toast.LENGTH_SHORT).show();
            return;
        }

        MyDataBase myDataBase = new MyDataBase(getApplicationContext());
        tripInfo=myDataBase.getTripInfo(tripBookingDetails.getTripID());
        myDataBase.addFeedback(feedbackContent,user.getUserID(),tripInfo.getCoachID(),tripInfo.getTripID(),rating);
        myDataBase.updateIsFeedBack(tripBookingDetails.getTripBookingDetailsID());
        Toast.makeText(this, "Cảm ơn bạn đã đánh giá!", Toast.LENGTH_SHORT).show();
    }
}