package com.example.coachticketbookingapp.ui;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.example.coachticketbookingapp.R;

public class Manage extends AppCompatActivity {
    Button btnback, btnmanageuser, btnmanagecategory, btnmanagecoach, btnmanagetrip, btnmanageticket;
    Button btnDangXuat;
    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_manage);

        btnmanageticket = findViewById(R.id.btnmanageticket);
        btnmanageticket.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(Manage.this, ManageTicket.class);
                startActivity(intent);
            }
        });

        btnmanagetrip = findViewById(R.id.btnmanagetrip);
        btnmanagetrip.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(Manage.this, ManageTrip.class);
                startActivity(intent);
            }
        });

        btnDangXuat = findViewById(R.id.btnDangXuat2);

        btnDangXuat.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Manage.this, LoginActivity.class);
                intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                startActivity(intent);
                finish();
            }
        });

        btnmanagecoach = findViewById(R.id.btnmanagecoach);
        btnmanagecoach.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(Manage.this, ManageCoach.class);
                startActivity(intent);
            }
        });

        btnmanagecategory = findViewById(R.id.btnmanagecategory);
        btnmanagecategory.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(Manage.this, ManageCategory.class);
                startActivity(intent);
            }
        });

        btnmanageuser = findViewById(R.id.btnmanageuser);
        btnmanageuser.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(Manage.this, ManageUser.class);
                startActivity(intent);
            }
        });

        btnback = findViewById(R.id.btnback);
        btnback.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });
    }
}