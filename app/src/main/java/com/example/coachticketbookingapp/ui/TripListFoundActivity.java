package com.example.coachticketbookingapp.ui;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.coachticketbookingapp.Object.TripInfo;
import com.example.coachticketbookingapp.R;

import java.util.ArrayList;
import java.util.List;

public class TripListFoundActivity extends AppCompatActivity {
    List<TripInfo> dsTrip = new ArrayList<>();
    RecyclerView recycleview_trip_list_found;
    TripListFoundAdapter tripListFoundAdapter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_trip_list_found);
        recycleview_trip_list_found=findViewById(R.id.recycleview_trip_list_found);

        Intent intent = getIntent();
        dsTrip = (List<TripInfo>) intent.getSerializableExtra("dsTrip");

        tripListFoundAdapter = new TripListFoundAdapter(this, new TripListFoundAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(TripInfo tripInfo) {
                // Xử lý sự kiện click vào item
                Intent intent = new Intent(TripListFoundActivity.this, TripListFoundDetailsActivity.class);
                intent.putExtra("trip", tripInfo);
                startActivity(intent);
            }
        });

        if(dsTrip!=null && !dsTrip.isEmpty()){
            LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this, RecyclerView.VERTICAL,false);
            recycleview_trip_list_found.setLayoutManager(linearLayoutManager);
            tripListFoundAdapter.setData(dsTrip);
            recycleview_trip_list_found.setAdapter(tripListFoundAdapter);
        }
    }


    /*public void onButtonClick(int position) {

        TripInfo tripInfo = dsTrip.get(position);//Lay duoc thong tin cua chuyen di duoc click
        Toast.makeText(getApplicationContext(), "Clicked trip: " + tripInfo.getDeparture() + " to " + tripInfo.getDestination(), Toast.LENGTH_SHORT).show();
    }*/
}