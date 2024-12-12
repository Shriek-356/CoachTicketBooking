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
import com.example.coachticketbookingapp.Object.TrippingCart;
import com.example.coachticketbookingapp.Object.User;
import com.example.coachticketbookingapp.R;

import java.text.NumberFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

import DataBase.MyDataBase;

public class TripListFoundDetailsActivity extends AppCompatActivity {
    private TripInfo tripInfoo;
    private TextView txvDiemBatDau, txvDiemDen, txvGioKhoiHanh, txvGioKetThuc, txvDiemDon, txvDiemTra, txvSoChoTrong, txvGiaTien;
    private Button btnDatVe;
    private User thisUser;
    private MyDataBase myDataBase;
    private Button btnThemVaoGioHang;
    private TrippingCart trippingCart;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_trip_list_found_details);
        myDataBase = new MyDataBase(this);
        txvDiemBatDau = findViewById(R.id.txvDiemBatDau);
        txvDiemDen = findViewById(R.id.txvDiemDen);
        txvGioKhoiHanh = findViewById(R.id.txvGioKhoiHanh);
        txvGioKetThuc = findViewById(R.id.txvGioKetThuc);
        txvDiemDon = findViewById(R.id.txvDiemDon);
        txvDiemTra = findViewById(R.id.txvDiemTra);
        txvSoChoTrong = findViewById(R.id.txvSoChoTrong);
        txvGiaTien = findViewById(R.id.txvGiaTien);
        btnDatVe = findViewById(R.id.btnDatVe);
        btnThemVaoGioHang = findViewById(R.id.btnThemGioHang);

        Intent intent = getIntent();
        thisUser =(User) intent.getSerializableExtra("thisuser");
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

               Intent intentTypeInfo = new Intent(TripListFoundDetailsActivity.this, BookingUserInfoActivity.class);
               intentTypeInfo.putExtra("tripinfoo",tripInfoo);
               intentTypeInfo.putExtra("userr",thisUser);
               startActivity(intentTypeInfo);
            }
        });

        btnThemVaoGioHang.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                boolean isExist = myDataBase.isTrippingCartExists(tripInfoo.getTripID(), thisUser.getUserID());
                if(isExist){
                    // Nếu giỏ hàng đã tồn tại, cập nhật số lượng vé và giá tiền
                    trippingCart = myDataBase.getTrippingCart(tripInfoo.getTripID(), thisUser.getUserID());
                    if(trippingCart != null){
                        int quantity = trippingCart.getTicketQuantity() + 1;
                        double price = tripInfoo.getPrice() * quantity;
                        myDataBase.updateTrippingCart(trippingCart.getTripID(), thisUser.getUserID(), quantity, price);
                        Toast.makeText(getApplicationContext(), "Đã cập nhật vào giỏ hàng", Toast.LENGTH_SHORT).show();
                    }

                }
                else {
                    // Nếu giỏ hàng chưa tồn tại, tạo mới giỏ hàng
                    Date currentDateObj = new Date();  // Lấy thời gian hiện tại
                    SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy", Locale.getDefault());
                    String currentDate = sdf.format(currentDateObj);

                    boolean isAdded = myDataBase.addTrippingCart(thisUser.getUserID(), currentDate, tripInfoo.getTripID(), 1, tripInfoo.getPrice());

                    if(isAdded) {
                        Toast.makeText(getApplicationContext(), "Đã thêm vào giỏ hàng", Toast.LENGTH_SHORT).show();
                    } else {
                        Toast.makeText(getApplicationContext(), "Lỗi khi thêm vào giỏ hàng", Toast.LENGTH_SHORT).show();
                    }
                }
            }
        });
    }
}