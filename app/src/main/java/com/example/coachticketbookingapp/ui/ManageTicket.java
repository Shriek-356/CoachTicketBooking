package com.example.coachticketbookingapp.ui;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.example.coachticketbookingapp.R;

public class ManageTicket extends AppCompatActivity {
    private Button btnThoat, btnveuser, btnvengaythang, btnvechuyen;
    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_manage_ticket);

        btnThoat = findViewById(R.id.btnThoat);
        btnThoat.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });

        btnveuser = findViewById(R.id.btnveuser);
        btnveuser.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(ManageTicket.this, TicketStatisticUser.class);
                startActivity(intent);
            }
        });

        btnvengaythang = findViewById(R.id.btnvengaythang);
        btnvengaythang.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(ManageTicket.this, TicketStatisticDayMonth.class);
                startActivity(intent);
            }
        });

        btnvechuyen = findViewById(R.id.btnvechuyen);
        btnvechuyen.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(ManageTicket.this, TicketStatisticTrip.class);
                startActivity(intent);
            }
        });

    }
}