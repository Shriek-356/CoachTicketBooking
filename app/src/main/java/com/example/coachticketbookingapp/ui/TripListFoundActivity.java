package com.example.coachticketbookingapp.ui;

import android.os.Bundle;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.coachticketbookingapp.Object.TripInfo;
import com.example.coachticketbookingapp.R;

import java.util.ArrayList;

public class TripListFoundActivity extends AppCompatActivity implements TripListFoundAdapter.ButtonClickListener {
    ArrayList<TripInfo> dsTrip = new ArrayList<>();
    RecyclerView recycleview_trip_list_found;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_trip_list_found);
        recycleview_trip_list_found = findViewById(R.id.recycleview_trip_list_found);
        dsTrip.add(new TripInfo(123,120000,40,"20/10/2024","10:10","30/10/2024","7:00","Quan 7 TPHCM","Phuong Thang Nhi","TP HCM","Vung Tau",5));
        dsTrip.add(new TripInfo(456,120000,40,"20/10/2024","10:10","30/10/2024","7:00","Quan 7 TPHCM","Phuong Thang Nhi","Vung Tau","TP HCM ",10));
        LinearLayoutManager layoutManager = new LinearLayoutManager(getApplicationContext(), LinearLayoutManager.VERTICAL, false);//Dieu chinh cach hien thi
        recycleview_trip_list_found.setLayoutManager(layoutManager);
        TripListFoundAdapter tripListAdapter = new TripListFoundAdapter(getApplicationContext(),dsTrip,this);
        recycleview_trip_list_found.setAdapter(tripListAdapter);
    }

    public void onButtonClick(int position) {

        TripInfo tripInfo = dsTrip.get(position);//Lay duoc thong tin cua chuyen di duoc click
        Toast.makeText(getApplicationContext(), "Clicked trip: " + tripInfo.getDeparture() + " to " + tripInfo.getDestination(), Toast.LENGTH_SHORT).show();
    }
}