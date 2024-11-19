package com.example.coachticketbookingapp.ui;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;

import com.example.coachticketbookingapp.R;

public class NolgTicket extends AppCompatActivity {

    Button buttonDangNhap, buttonTimKiem, buttonTaiKhoan;

    @SuppressLint("SetTextI18n")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ticket);

        buttonDangNhap = findViewById(R.id.buttonDangNhap);
        buttonDangNhap.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(NolgTicket.this, LoginActivity.class);
                startActivity(intent);
                finish();
            }
        });

        buttonTimKiem = findViewById(R.id.buttonTimKiem);
        buttonTimKiem.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(NolgTicket.this, MainUIActivity.class);
                startActivity(intent);
                finish();
            }
        });

        buttonTaiKhoan = findViewById(R.id.buttonTaiKhoan);
        buttonTaiKhoan.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(NolgTicket.this, NolgAccount.class);
                startActivity(intent);
                finish();
            }
        });
    }
}

