package com.example.coachticketbookingapp.ui;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;

import com.example.coachticketbookingapp.R;

public class NolgAccount extends AppCompatActivity {

    Button buttonDangNhap, buttonTimKiem, buttonVeCuaToi;

    @SuppressLint("SetTextI18n")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_account);

        buttonDangNhap = findViewById(R.id.buttonDangNhap);
        buttonDangNhap.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(NolgAccount.this, LoginActivity.class);
                startActivity(intent);
                finish();
            }
        });

        buttonTimKiem = findViewById(R.id.buttonTimKiem);
        buttonTimKiem.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(NolgAccount.this, MainUIActivity.class);
                startActivity(intent);
                finish();
            }
        });

        buttonVeCuaToi = findViewById(R.id.buttonVeCuaToi);
        buttonVeCuaToi.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(NolgAccount.this, NolgTicket.class);
                startActivity(intent);
                finish();
            }
        });
    }
}

