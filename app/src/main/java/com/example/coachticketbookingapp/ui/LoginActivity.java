package com.example.coachticketbookingapp.ui;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;

import com.example.coachticketbookingapp.Object.User;
import com.example.coachticketbookingapp.R;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;

import android.text.method.HideReturnsTransformationMethod;
import android.text.method.PasswordTransformationMethod;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import DataBase.MyDataBase;

public class LoginActivity extends AppCompatActivity {
    TextView txvLinkToReg;
    EditText edtEmail, edtPassword;
    ImageView imgEyes;
    Button btnLogin;
    private MainUIActivity mainUIActivity;

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

        MyDataBase mydb = new MyDataBase(this);
        mydb.open();//Hieu don gian la tao csdl neu chua ton tai

        btnLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String email = edtEmail.getText().toString().trim();
                String password = edtPassword.getText().toString().trim();

                if (email.isEmpty() || password.isEmpty()) {
                    Toast.makeText(LoginActivity.this, "Vui lòng nhập đầy đủ thông tin", Toast.LENGTH_SHORT).show();
                } else {
                    if (mydb.checkLogin(email, password)) {
                        String userName = mydb.getUserName(email);

                        User user = mydb.getUser(email);

                        Toast.makeText(LoginActivity.this, "Đăng nhập thành công!", Toast.LENGTH_SHORT).show();
                        Intent intentt = new Intent(LoginActivity.this,MainUIActivity.class);
                        //Bat dau truyen object User sang MainUIActivity
                        intentt.putExtra("thisUser",user);
                        startActivity(intentt);

                        finish();//Dong activity
                    } else {
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

