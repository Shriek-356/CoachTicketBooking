package com.example.coachticketbookingapp.ui;

import android.content.Intent;
import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.coachticketbookingapp.Object.FeedBack;
import com.example.coachticketbookingapp.Object.TripInfo;
import com.example.coachticketbookingapp.R;

import java.util.ArrayList;
import java.util.List;

import DataBase.MyDataBase;

public class FeedBackListActivity extends AppCompatActivity {
    private TripInfo tripInfo;
    private List<FeedBack> dsFeedBack = new ArrayList<>();
    private RecyclerView recyclerView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_feed_back_list);
        Intent intent = getIntent();
        tripInfo = (TripInfo) intent.getSerializableExtra("tripinfo");
        MyDataBase myDataBase = new MyDataBase(getApplicationContext());
        dsFeedBack= myDataBase.getFeedbacksByCoachId(tripInfo.getCoachID());
        recyclerView = findViewById(R.id.recycleview_feedback);

        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this, RecyclerView.VERTICAL,false);
        recyclerView.setLayoutManager(linearLayoutManager);
        FeedBackListAdapter feedBackListAdapter = new FeedBackListAdapter(this,dsFeedBack);

        recyclerView.setAdapter(feedBackListAdapter);
    }
}