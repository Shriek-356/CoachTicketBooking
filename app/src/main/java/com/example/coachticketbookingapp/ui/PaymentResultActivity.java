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

import java.text.NumberFormat;
import java.util.Locale;

public class PaymentResultActivity extends AppCompatActivity {

    private Button btnBackToHome;
    private User user;
    private TripBookingDetailsPayment trip;
    private int result;
    private ImageView imgResult;
    private TextView txvAmount,txvSuccessTitle;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_payment_result);

        btnBackToHome = findViewById(R.id.btnBackToHome);
        txvAmount = findViewById(R.id.txvAmount);
        txvSuccessTitle=findViewById(R.id.txvSuccessTitle);
        imgResult = findViewById(R.id.imgSuccess);

        Intent intent = getIntent();
        trip = (TripBookingDetailsPayment) intent.getSerializableExtra("trip");
        user = (User) intent.getSerializableExtra("user");

        result = (Integer) intent.getSerializableExtra("result");

        double totalPrice = trip.getTotalPrice();
        NumberFormat currencyFormat = NumberFormat.getCurrencyInstance(new Locale("vi", "VN"));
        String formattedPrice = currencyFormat.format(totalPrice);
        txvAmount.setText(formattedPrice);

        if(result==4){
            txvSuccessTitle.setText("Đặt Vé Thành Công!!!");
        }
        else if(result==1){
            txvSuccessTitle.setText("Đặt Vé và Thanh Toán Thành Công!!!");
        }
        else{
            imgResult.setImageResource(R.drawable.errorimage);
            txvSuccessTitle.setText("Có lỗi xảy ra, vui lòng thử lại");
        }

        // Thiết lập sự kiện cho nút "Quay lại trang chủ"
        btnBackToHome.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Quay lại trang chủ, ví dụ quay về MainActivity
                Intent intent1 = new Intent(PaymentResultActivity.this, MainUIActivity.class);
                // Xóa các Activity trước đó trong Stack để người dùng không quay lại trang thanh toán
                intent1.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                intent1.putExtra("user",user);
                startActivity(intent1);
                finish(); // Đảm bảo Activity này không còn trong stack sau khi quay lại trang chủ
            }
        });
    }
}
