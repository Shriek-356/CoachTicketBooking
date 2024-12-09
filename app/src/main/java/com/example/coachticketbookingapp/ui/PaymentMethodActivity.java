package com.example.coachticketbookingapp.ui;

import android.annotation.SuppressLint;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.os.StrictMode;
import android.util.Log;
import android.view.View;
import android.widget.Button;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.example.coachticketbookingapp.Api.CreateOrder;
import com.example.coachticketbookingapp.R;

import org.json.JSONObject;

import vn.zalopay.sdk.Environment;
import vn.zalopay.sdk.ZaloPayError;
import vn.zalopay.sdk.ZaloPaySDK;
import vn.zalopay.sdk.listeners.PayOrderListener;

public class PaymentMethodActivity extends AppCompatActivity {

    private Button btnThanhToanZl;
    private Button btnThanhToanTrucTiep;
    private double total=100000;
    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_payment_method);
        btnThanhToanTrucTiep=findViewById(R.id.btnThanhToanTrucTiep);

        btnThanhToanTrucTiep.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                showConfirmationDialog();
            }
        });

        /*btnThanhToanZl=findViewById(R.id.btnThanhToanZL);
        String totalString=String.format("%.0f",total);

        StrictMode.ThreadPolicy policy = new
                StrictMode.ThreadPolicy.Builder().permitAll().build();
        StrictMode.setThreadPolicy(policy);

        // ZaloPay SDK Init
        ZaloPaySDK.init(2553, Environment.SANDBOX);


        /*btnThanhToanZl.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                CreateOrder orderApi = new CreateOrder();
                try {
                    JSONObject data = orderApi.createOrder(totalString);
                    String code = data.getString("return_code");
                    if (code.equals("1")) {

                        String token = data.getString("zp_trans_token");
                        ZaloPaySDK.getInstance().payOrder(PaymentMethodActivity.this, token, "demozpdk://app", new PayOrderListener() {
                            @Override
                            public void onPaymentSucceeded(String s, String s1, String s2) {

                            }

                            @Override
                            public void onPaymentCanceled(String s, String s1) {

                            }

                            @Override
                            public void onPaymentError(ZaloPayError zaloPayError, String s, String s1) {

                            }
                        });
                    }

                } catch (Exception e) {
                    e.printStackTrace();
                }

            }

        });
        */

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
                        Intent intent = new Intent(PaymentMethodActivity.this,PaymentResultActivity.class);
                        startActivity(intent);
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