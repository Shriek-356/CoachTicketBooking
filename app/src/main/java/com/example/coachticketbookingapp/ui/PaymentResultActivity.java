package com.example.coachticketbookingapp.ui;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.example.coachticketbookingapp.Object.TripBookingDetailsPayment;
import com.example.coachticketbookingapp.Object.User;
import com.example.coachticketbookingapp.R;

public class PaymentResultActivity extends AppCompatActivity {

    private Button btnBackToHome;
    private User user;
    private TripBookingDetailsPayment trip;
    private int result;
    private ImageView imgResult;
    private TextView txvAmount;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_payment_result);

        btnBackToHome = findViewById(R.id.btnBackToHome);
        txvAmount = findViewById(R.id.txvAmount);

        Intent intent = getIntent();
        user = (User) intent.getSerializableExtra("user");
        trip = (TripBookingDetailsPayment) intent.getSerializableExtra("trip");
        result = (Integer) intent.getSerializableExtra("result");

        txvAmount.setText(String.format("%0.f",trip.getTotalPrice()));
        // Thiết lập sự kiện cho nút "Quay lại trang chủ"
        btnBackToHome.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Quay lại trang chủ, ví dụ quay về MainActivity
                Intent intent = new Intent(PaymentResultActivity.this, MainUIActivity.class);
                // Xóa các Activity trước đó trong Stack để người dùng không quay lại trang thanh toán
                intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                intent.putExtra("user",user);
                startActivity(intent);
                finish(); // Đảm bảo Activity này không còn trong stack sau khi quay lại trang chủ
            }
        });
    }
}
