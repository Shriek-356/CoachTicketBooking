package com.example.coachticketbookingapp.ui;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.widget.Button;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.example.coachticketbookingapp.R;

public class LgTicket extends AppCompatActivity {
    Button buttonTimKiem;
    Button buttonTaiKhoan;
    TextView greetingTextView;
    @SuppressLint("SetTextI18n")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lgticket);

        greetingTextView = findViewById(R.id.greetingTextView);

        SharedPreferences sharedPreferences = getSharedPreferences("UserPrefs", Context.MODE_PRIVATE);
        String userName = getIntent().getStringExtra("USER_NAME");

        if (userName != null && !userName.isEmpty()) {
            sharedPreferences.edit().putString("USER_NAME", userName).apply();
        } else {
            userName = sharedPreferences.getString("USER_NAME", "");
        }

        if (userName != null && !userName.isEmpty()) {
            greetingTextView.setText("Xin chào, " + userName);
        }

        buttonTimKiem = findViewById(R.id.buttonTimKiem);
        buttonTaiKhoan = findViewById(R.id.buttonTaiKhoan);

        buttonTimKiem.setOnClickListener(v -> {
            Intent intent = new Intent(LgTicket.this, Welcomepage.class);
            startActivity(intent);
        });

        buttonTaiKhoan.setOnClickListener(v -> {
            Intent intent = new Intent(LgTicket.this, LgAccount.class);
            startActivity(intent);
        });
        Button button1 = findViewById(R.id.button1);
        Button button2 = findViewById(R.id.button2);
        Button button3 = findViewById(R.id.button3);

        button1.setOnClickListener(v -> {
            button1.setBackgroundResource(R.drawable.button_underline);
            button2.setBackgroundResource(android.R.color.transparent);
            button3.setBackgroundResource(android.R.color.transparent);
        });

        button2.setOnClickListener(v -> {
            button1.setBackgroundResource(android.R.color.transparent);
            button2.setBackgroundResource(R.drawable.button_underline);
            button3.setBackgroundResource(android.R.color.transparent);
        });

        button3.setOnClickListener(v -> {
            button1.setBackgroundResource(android.R.color.transparent);
            button2.setBackgroundResource(android.R.color.transparent);
            button3.setBackgroundResource(R.drawable.button_underline);
        });

    }
}

