package com.example.coachticketbookingapp.ui;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;

import com.example.coachticketbookingapp.Object.User;
import com.example.coachticketbookingapp.R;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.text.method.HideReturnsTransformationMethod;
import android.text.method.PasswordTransformationMethod;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import java.text.SimpleDateFormat;
import java.util.Locale;
    import java.util.Date;

import DataBase.MyDataBase;

public class LoginActivity extends AppCompatActivity {
    TextView txvLinkToReg;
    EditText edtEmail, edtPassword;
    ImageView imgEyes;
    Button btnLogin;
    private MainUIActivity mainUIActivity;
    MyDataBase myDataBase;

    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_login);
        txvLinkToReg = findViewById(R.id.txvLinkToReg);
        edtEmail = findViewById(R.id.edtEmail);
        edtPassword = findViewById(R.id.edtPassword);
        imgEyes = findViewById(R.id.imgEyes);
        btnLogin = findViewById(R.id.button3);

        myDataBase = new MyDataBase(this);
        myDataBase.addAdminAccount();
        myDataBase.addDefaultCoaches();
        myDataBase.addInitialTripInfo();

        MyDataBase mydb = new MyDataBase(this);

        //mydb.open();//Hieu don gian la tao csdl neu chua ton tai
        //SQLiteDatabase db = mydb.getWritableDatabase();

        Date currentDateObj = new Date();  // Lấy thời gian hiện tại
        // Chuyển đổi thời gian thành chuỗi theo định dạng dd/MM/yyyy
        SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy", Locale.getDefault());
        String currentDate = sdf.format(currentDateObj);  // Chuyển thời gian thành chuỗi

        btnLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String email = edtEmail.getText().toString().trim();
                String password = edtPassword.getText().toString().trim();

                if (email.isEmpty() || password.isEmpty()) {
                    Toast.makeText(LoginActivity.this, "Vui lòng nhập đầy đủ thông tin", Toast.LENGTH_SHORT).show();
                } else {
                    if (mydb.checkLogin(email, password)) {
                        if(mydb.checkAdminLogin(email,password)) {
                            Intent intentManage = new Intent(LoginActivity.this,Manage.class);
                            startActivity(intentManage);
                        }else {
                            User user = mydb.getUser(email);  // Lấy đối tượng User từ DB

                            if (user != null) {
                                Toast.makeText(LoginActivity.this, "Đăng nhập thành công!", Toast.LENGTH_SHORT).show();
                                // Truyền đối tượng User qua Intent
                                Intent intentt = new Intent(LoginActivity.this, MainUIActivity.class);
                                intentt.putExtra("user", user);  // Truyền User vào Intent
                                startActivity(intentt);
                                finish();  // Đóng activity hiện tại
                            } else {
                                Toast.makeText(LoginActivity.this, "Không tìm thấy người dùng!", Toast.LENGTH_SHORT).show();
                            }
                        }
                    }
                    else {
                        Toast.makeText(LoginActivity.this, "Sai email hoặc mật khẩu!!!", Toast.LENGTH_SHORT).show();
                    }
                }
            }
        });
    }

    @Override
    protected void onResume() {
        super.onResume();

        txvLinkToReg.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(), RegisterActivity.class);
                startActivity(intent);
            }
        });

        imgEyes.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (edtPassword.getTransformationMethod().equals(HideReturnsTransformationMethod.getInstance())) {
                    edtPassword.setTransformationMethod(PasswordTransformationMethod.getInstance());
                    imgEyes.setImageResource(R.drawable.baseline_visibility_off_24);
                } else {
                    edtPassword.setTransformationMethod(HideReturnsTransformationMethod.getInstance());
                    imgEyes.setImageResource(R.drawable.baseline_visibility_24);
                }
                edtPassword.setSelection(edtPassword.getText().length());
            }
        });
    }

}

