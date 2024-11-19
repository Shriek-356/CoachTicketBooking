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

public class LgAccount extends AppCompatActivity {
    Button buttonTimKiem;
    Button buttonVeCuaToi;
    TextView greetingTextView;
    @SuppressLint("SetTextI18n")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lgaccount);

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
        buttonVeCuaToi = findViewById(R.id.buttonVeCuaToi);

        buttonTimKiem.setOnClickListener(v -> {
            Intent intent = new Intent(LgAccount.this, Welcomepage.class);
            startActivity(intent);
        });

        // Set OnClickListener for "Vé của tôi" button
        buttonVeCuaToi.setOnClickListener(v -> {
            Intent intent = new Intent(LgAccount.this, LgTicket.class);
            startActivity(intent);
        });
    }
}

