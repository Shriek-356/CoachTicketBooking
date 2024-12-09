package com.example.coachticketbookingapp.ui;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.coachticketbookingapp.R;

import java.util.List;

public class SearchResultsActivity extends AppCompatActivity {

    /*private RecyclerView recyclerView;
    private SearchResultsAdapter adapter;
   /* private List<TripInfoo> tripList;

    Button btnthaydoi;

    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search_results);

        btnthaydoi = findViewById(R.id.btnthaydoi);
        btnthaydoi.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });

        recyclerView = findViewById(R.id.recyclerViewResults);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        tripList = getIntent().getParcelableArrayListExtra("tripList");

        if (tripList != null && !tripList.isEmpty()) {
            adapter = new SearchResultsAdapter(tripList);
            recyclerView.setAdapter(adapter);
        } else {
            Toast.makeText(this, "Không có chuyến xe nào", Toast.LENGTH_SHORT).show();
        }
    }

    */
}
