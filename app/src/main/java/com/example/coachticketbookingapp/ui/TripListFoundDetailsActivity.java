package com.example.coachticketbookingapp.ui;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.example.coachticketbookingapp.Object.TripInfo;
import com.example.coachticketbookingapp.R;

import java.text.NumberFormat;
import java.util.Locale;

public class TripListFoundDetailsActivity extends AppCompatActivity {
    private TripInfo tripInfoo;
    private TextView txvDiemBatDau, txvDiemDen, txvGioKhoiHanh, txvGioKetThuc, txvDiemDon, txvDiemTra, txvSoChoTrong, txvGiaTien;
    private Button btnDatVe;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_trip_list_found_details);

        txvDiemBatDau = findViewById(R.id.txvDiemBatDau);
        txvDiemDen = findViewById(R.id.txvDiemDen);
        txvGioKhoiHanh = findViewById(R.id.txvGioKhoiHanh);
        txvGioKetThuc = findViewById(R.id.txvGioKetThuc);
        txvDiemDon = findViewById(R.id.txvDiemDon);
        txvDiemTra = findViewById(R.id.txvDiemTra);
        txvSoChoTrong = findViewById(R.id.txvSoChoTrong);
        txvGiaTien = findViewById(R.id.txvGiaTien);
        btnDatVe = findViewById(R.id.btnDatVe);

        Intent intent = getIntent();
        tripInfoo = (TripInfo) intent.getSerializableExtra("trip");
         if(tripInfoo != null){
            txvDiemBatDau.setText(tripInfoo.getDeparture());
            txvDiemDen.setText(tripInfoo.getDestination());
            txvGioKhoiHanh.setText(tripInfoo.getDepartureTime());
            txvGioKetThuc.setText(tripInfoo.getDestinationTime());
            txvDiemDon.setText(tripInfoo.getFirstLocation());
            txvDiemTra.setText(tripInfoo.getSecondLocation());
            txvSoChoTrong.setText(String.valueOf(tripInfoo.getTicketAvailable()));
            NumberFormat numberFormat = NumberFormat.getCurrencyInstance(new Locale("vi", "VN"));
            txvGiaTien.setText(numberFormat.format(tripInfoo.getPrice()));
        }

        btnDatVe.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
               Intent intentpayment = new Intent(TripListFoundDetailsActivity.this, PaymentMethodActivity.class);
               startActivity(intentpayment);
            }
        });
    }
}