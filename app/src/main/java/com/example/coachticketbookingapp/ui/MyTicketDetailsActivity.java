package com.example.coachticketbookingapp.ui;

import android.content.Intent;
import android.os.Bundle;
import android.widget.TextView;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.example.coachticketbookingapp.Object.TripBookingDetails;
import com.example.coachticketbookingapp.Object.User;
import com.example.coachticketbookingapp.R;

import java.text.NumberFormat;
import java.util.Locale;

public class MyTicketDetailsActivity extends AppCompatActivity {
    TripBookingDetails tripBookingDetails;
    User myTicketDetailsActivityUser;
    TextView txvTenHanhKhach, txvDienThoai,txvEmail, txvDiemXuatPhat, txvDiemDen, txvNgayDatVe, txvNgayXuatPhat, txvGiaVe, txvSoVe, txvMaVe;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_my_ticket_details);

        Intent intentMyTicketDetailsActivity = getIntent();
        tripBookingDetails = (TripBookingDetails) intentMyTicketDetailsActivity.getSerializableExtra("tripbookingdetails");
        myTicketDetailsActivityUser = (User) intentMyTicketDetailsActivity.getSerializableExtra("tripBookingDetailsUser");

        txvTenHanhKhach = findViewById(R.id.txvTenHanhKhach);
        txvDienThoai = findViewById(R.id.txvDienThoai);
        txvEmail = findViewById(R.id.txvEmail);
        txvDiemXuatPhat=findViewById(R.id.txvDiemXuatPhat);
        txvDiemDen = findViewById(R.id.txvDiemDen);
        txvNgayDatVe = findViewById(R.id.txvNgayDatVe);
        txvNgayXuatPhat = findViewById(R.id.txvNgayXuatPhat);
        txvGiaVe = findViewById(R.id.txvGiaVe);
        txvSoVe = findViewById(R.id.txvSoVe);
        txvMaVe = findViewById(R.id.txvMaVe);

        if(tripBookingDetails!=null){
            txvTenHanhKhach.setText(myTicketDetailsActivityUser.getUserName());
            txvDienThoai.setText(myTicketDetailsActivityUser.getPhone());
            txvEmail.setText(myTicketDetailsActivityUser.getEmail());
            txvDiemXuatPhat.setText(tripBookingDetails.getFirstLocation());
            txvDiemDen.setText(tripBookingDetails.getSecondLocation());
            txvNgayDatVe.setText(tripBookingDetails.getBookingDate());
            txvNgayXuatPhat.setText(tripBookingDetails.getDepartureDate()+"-"+tripBookingDetails.getDepartureTime());
            double tonggia= tripBookingDetails.getPrice()* tripBookingDetails.getTicketQuantity();
            NumberFormat numberFormat = NumberFormat.getCurrencyInstance(new Locale("vi", "VN"));
            txvGiaVe.setText(numberFormat.format(tonggia));
            txvSoVe.setText(String.valueOf(tripBookingDetails.getTicketQuantity()));
            txvMaVe.setText(String.valueOf(tripBookingDetails.getTripBookingDetailsID()));
        }
    }
}