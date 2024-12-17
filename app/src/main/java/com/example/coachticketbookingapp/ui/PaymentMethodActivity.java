package com.example.coachticketbookingapp.ui;

import android.annotation.SuppressLint;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.os.StrictMode;
import android.view.View;
import android.widget.Button;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import com.example.coachticketbookingapp.Api.CreateOrder;
import com.example.coachticketbookingapp.Object.TripBookingDetailsPayment;
import com.example.coachticketbookingapp.Object.User;
import com.example.coachticketbookingapp.R;
import java.util.Date;

import java.text.SimpleDateFormat;
import java.util.Locale;

import DataBase.MyDataBase;
import vn.zalopay.sdk.Environment;
import vn.zalopay.sdk.ZaloPayError;
import vn.zalopay.sdk.ZaloPaySDK;
import vn.zalopay.sdk.listeners.PayOrderListener;


import org.json.JSONObject;

public class PaymentMethodActivity extends AppCompatActivity {

    private Button btnThanhToanZl;
    private Button btnThanhToanTrucTiep;
    private double total=100000;
    private int ticketQuantity;
    private User user;
    TripBookingDetailsPayment trip;
    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_payment_method);
        btnThanhToanTrucTiep=findViewById(R.id.btnThanhToanTrucTiep);
        Intent intent = getIntent();
        trip =(TripBookingDetailsPayment) intent.getSerializableExtra("tripinfo");
        ticketQuantity=(Integer)intent.getSerializableExtra("ticketquantity");
        user=(User)intent.getSerializableExtra("user");

        String totalPrice = String.format("%.0f",trip.getTotalPrice());

        btnThanhToanTrucTiep.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                showConfirmationDialog();
            }
        });
        btnThanhToanZl=findViewById(R.id.btnThanhToanZL);

        StrictMode.ThreadPolicy policy = new
                StrictMode.ThreadPolicy.Builder().permitAll().build();
        StrictMode.setThreadPolicy(policy);

        // ZaloPay SDK Init
        ZaloPaySDK.init(2553, Environment.SANDBOX);


         btnThanhToanZl.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                CreateOrder orderApi = new CreateOrder();
                try {
                    JSONObject data = orderApi.createOrder(totalPrice);
                    String code = data.getString("return_code");
                    if (code.equals("1")) {
                        String token = data.getString("zp_trans_token");
                        ZaloPaySDK.getInstance().payOrder(PaymentMethodActivity.this, token, "demozpdk://app", new PayOrderListener() {
                            @Override
                            public void onPaymentSucceeded(String s, String s1, String s2) {
                                // Xử lý thanh toán trực tiếp
                                MyDataBase myDataBase = new MyDataBase(getApplicationContext());
                                SimpleDateFormat sdf = new SimpleDateFormat("HH:mm", Locale.getDefault());
                                String currentTime = sdf.format(new Date());

                                myDataBase.addTripBookingDetails(trip.getUserId(),trip.getTripId(), trip.getBookingDate(), trip.getTicketQuantity(),trip.getTotalPrice(),trip.getFullName(), trip.getPhoneNumber(), trip.getEmail(),currentTime);

                                myDataBase.updateTicketAvailability(trip.getTripId(),ticketQuantity);

                                myDataBase.deleteTrippingCart(user.getUserID(), trip.getTripId());

                                int id = myDataBase.getTripBookingDetailsID(trip.getTripId(),user.getUserID(),trip.getBookingDate(),currentTime);

                                myDataBase.addPayment("Thanh Toán Tiền Mặt",trip.getBookingDate(),id);
                                Intent intent = new Intent(PaymentMethodActivity.this,PaymentResultActivity.class);
                                intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK); // Xóa tất cả các Activity trước đó
                                intent.putExtra("result",1);
                                intent.putExtra("user",user);
                                intent.putExtra("trip",trip);
                                startActivity(intent);
                                finish();
                            }

                            @Override
                            public void onPaymentCanceled(String s, String s1) {
                                Intent intent = new Intent(PaymentMethodActivity.this,PaymentResultActivity.class);
                                intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK); // Xóa tất cả các Activity trước đó
                                intent.putExtra("result",2);
                                intent.putExtra("user",user);
                                intent.putExtra("trip",trip);
                                startActivity(intent);
                                finish();
                            }

                            @Override
                            public void onPaymentError(ZaloPayError zaloPayError, String s, String s1) {
                                Intent intent = new Intent(PaymentMethodActivity.this,PaymentResultActivity.class);
                                intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK); // Xóa tất cả các Activity trước đó
                                intent.putExtra("result",3);
                                intent.putExtra("user",user);
                                intent.putExtra("trip",trip);
                                startActivity(intent);
                                finish();
                            }
                        });

                    }
                } catch (Exception e) {
                    e.printStackTrace();
                }

            }

        });

    }
    @Override
    protected void onNewIntent(Intent intent) {
        super.onNewIntent(intent);
        ZaloPaySDK.getInstance().onResult(intent);
    }

    private void showConfirmationDialog() {
        // Tạo một AlertDialog Builder
        AlertDialog.Builder builder = new AlertDialog.Builder(PaymentMethodActivity.this);
        builder.setTitle("Xác nhận thanh toán")
                .setMessage("Bạn có chắc chắn muốn thanh toán trực tiếp không?")
                .setCancelable(false) // Người dùng không thể tắt hộp thoại ngoài việc chọn nút
                .setPositiveButton("Có", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        // Xử lý thanh toán trực tiếp
                        MyDataBase myDataBase = new MyDataBase(getApplicationContext());
                        SimpleDateFormat sdf = new SimpleDateFormat("HH:mm", Locale.getDefault());
                        String currentTime = sdf.format(new Date());

                        myDataBase.addTripBookingDetails(trip.getUserId(),trip.getTripId(), trip.getBookingDate(), trip.getTicketQuantity(),trip.getTotalPrice(),trip.getFullName(), trip.getPhoneNumber(), trip.getEmail(),currentTime);

                        myDataBase.updateTicketAvailability(trip.getTripId(),ticketQuantity);

                        myDataBase.deleteTrippingCart(user.getUserID(), trip.getTripId());

                        int id = myDataBase.getTripBookingDetailsID(trip.getTripId(),user.getUserID(),trip.getBookingDate(),currentTime);


                        myDataBase.addPayment("Thanh Toán Tiền Mặt",trip.getBookingDate(),id);

                        Intent intent = new Intent(PaymentMethodActivity.this,PaymentResultActivity.class);
                        intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK); // Xóa tất cả các Activity trước đó
                        intent.putExtra("result",0);
                        intent.putExtra("trip",trip);
                        intent.putExtra("user",user);
                        startActivity(intent);
                        finish();
                    }
                })
                .setNegativeButton("Không", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        // Đóng hộp thoại nếu người dùng không muốn thanh toán
                        dialog.dismiss();
                    }
                });
        // Hiển thị hộp thoại
        builder.create().show();
    }

}