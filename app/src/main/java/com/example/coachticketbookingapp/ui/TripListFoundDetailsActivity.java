package com.example.coachticketbookingapp.ui;

import android.annotation.SuppressLint;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.example.coachticketbookingapp.Object.TripInfo;
import com.example.coachticketbookingapp.Object.TrippingCart;
import com.example.coachticketbookingapp.Object.User;
import com.example.coachticketbookingapp.R;

import java.text.NumberFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

import DataBase.MyDataBase;

public class TripListFoundDetailsActivity extends AppCompatActivity {
    private TripInfo tripInfoo;
    private TextView txvDiemBatDau, txvDiemDen, txvGioKhoiHanh, txvGioKetThuc, txvDiemDon, txvDiemTra, txvSoChoTrong, txvGiaTien,txvDetailsDeparture,txvDetailsTime;
    private Button btnDatVe;
    private User thisUser;
    private MyDataBase myDataBase;
    private Button btnThemVaoGioHang,btnXemDanhGia;
    private TrippingCart trippingCart;
    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_trip_list_found_details);
        myDataBase = new MyDataBase(this);
        txvDiemBatDau = findViewById(R.id.txvDiemBatDau);
        txvDiemDen = findViewById(R.id.txvDiemDenn);
        txvGioKhoiHanh = findViewById(R.id.txvGioKhoiHanh);
        txvGioKetThuc = findViewById(R.id.txvGioKetThuc);
        txvDiemDon = findViewById(R.id.txvDiemDon);
        txvDiemTra = findViewById(R.id.txvDiemTra);
        txvSoChoTrong = findViewById(R.id.txvSoChoTrong);
        txvGiaTien = findViewById(R.id.txvGiaTien);
        btnDatVe = findViewById(R.id.btnDatVe);
        btnThemVaoGioHang = findViewById(R.id.btnThemGioHang);
        btnXemDanhGia = findViewById(R.id.btnXemDanhGia);
        txvDetailsDeparture = findViewById(R.id.txvDetailsDeparture);
        txvDetailsTime = findViewById(R.id.txvDetailsTime);


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
            txvDetailsTime.setText(tripInfoo.getDepartureTime());
            txvDetailsDeparture.setText(tripInfoo.getDeparture());
            txvSoChoTrong.setText(String.valueOf(tripInfoo.getTicketAvailable()));
            NumberFormat numberFormat = NumberFormat.getCurrencyInstance(new Locale("vi", "VN"));
            txvGiaTien.setText(numberFormat.format(tripInfoo.getPrice()));
        }

        btnDatVe.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(checkActiveTicket()) {
                    Intent intentTypeInfo = new Intent(TripListFoundDetailsActivity.this, BookingUserInfoActivity.class);
                    intentTypeInfo.putExtra("tripinfoo", tripInfoo);
                    intentTypeInfo.putExtra("userr", thisUser);
                    intentTypeInfo.putExtra("ticketQuantity", 0);
                    startActivity(intentTypeInfo);
                }
                else{
                    showAlertDialog("Thông Báo","Có vẻ như chuyến đi này đã hoàn thành, bạn không thể đặt vé");
                }
            }
        });

        btnThemVaoGioHang.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(checkActiveTicket()) {
                        boolean isExist = myDataBase.isTrippingCartExists(tripInfoo.getTripID(), thisUser.getUserID());
                        if (isExist) {
                            // Nếu giỏ hàng đã tồn tại, cập nhật số lượng vé và giá tiền
                            trippingCart = myDataBase.getTrippingCart(tripInfoo.getTripID(), thisUser.getUserID());
                            if (trippingCart != null) {
                                int currentQuantity = trippingCart.getTicketQuantity()+1;
                                if (currentQuantity <= tripInfoo.getTicketAvailable()) {
                                double price = tripInfoo.getPrice() * currentQuantity;
                                myDataBase.updateTrippingCart(trippingCart.getTripID(), thisUser.getUserID(), currentQuantity, price);
                                Toast.makeText(getApplicationContext(), "Đã cập nhật vào giỏ hàng", Toast.LENGTH_SHORT).show();
                                } else {
                                    Toast.makeText(getApplicationContext(), "Không đủ vé", Toast.LENGTH_SHORT).show();
                                }
                            }
                        } else {
                            // Nếu giỏ hàng chưa tồn tại, tạo mới giỏ hàng
                            Date currentDateObj = new Date();  // Lấy thời gian hiện tại
                            SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy", Locale.getDefault());
                            String currentDate = sdf.format(currentDateObj);

                            boolean isAdded = myDataBase.addTrippingCart(thisUser.getUserID(), currentDate, tripInfoo.getTripID(), 1, tripInfoo.getPrice());

                            if (isAdded) {
                                Toast.makeText(getApplicationContext(), "Đã thêm vào giỏ hàng", Toast.LENGTH_SHORT).show();
                            } else {
                                Toast.makeText(getApplicationContext(), "Lỗi khi thêm vào giỏ hàng", Toast.LENGTH_SHORT).show();
                            }
                        }
                }
                else{

                    showAlertDialog("Thông báo", "Có vẻ như chuyến đi này đã hoàn thành, bạn không thể thêm vào giỏ hàng");
                    return;
                }
            }
        });

        btnXemDanhGia.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intenttt = new Intent(TripListFoundDetailsActivity.this, FeedBackListActivity.class);
                intenttt.putExtra("tripinfo",tripInfoo);
                startActivity(intenttt);
            }
        });

    }

    public boolean checkActiveTicket() {
        long currentTimeMillis = System.currentTimeMillis();
        String bookingDateTimeString = tripInfoo.getDepartureDate() + " " + tripInfoo.getDepartureTime();
        long bookingDateTimeInMillis = convertToMillisWithTime(bookingDateTimeString);

        if(currentTimeMillis < bookingDateTimeInMillis){
            return true;
        }
        else
            return false;
    }


    public long convertToMillisWithTime(String dateTimeString) {
        try {
            // Định dạng ngày giờ đầy đủ
            SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy HH:mm", Locale.getDefault());

            // Chuyển chuỗi thành đối tượng Date
            Date date = dateFormat.parse(dateTimeString);

            // Trả về thời gian tính bằng milliseconds
            return date != null ? date.getTime() : 0;
        } catch (ParseException e) {
            e.printStackTrace();
            return 0;  // Nếu có lỗi trong quá trình phân tích, trả về 0
        }
    }

    public void showAlertDialog(String title, String message) {
        AlertDialog.Builder builder = new AlertDialog.Builder(TripListFoundDetailsActivity.this);
        builder.setTitle(title);
        builder.setMessage(message);
        builder.setCancelable(false);
        builder.setPositiveButton("Đã rõ", null);
        builder.create().show();
    }
}