package com.example.coachticketbookingapp.ui;

import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.coachticketbookingapp.R;

import DataBase.MyDataBase;

public class RegisterActivity extends AppCompatActivity {

    EditText editTextName, editTextEmail, editTextPhone, editTextPassword, editTextConfirmPassword;
    CheckBox checkBoxMale, checkBoxFemale;
    Button buttonRegister;
    TextView txvLoginNow;
    MyDataBase myDataBase;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);
        editTextName = findViewById(R.id.editTextText2);
        editTextEmail = findViewById(R.id.editTextText);
        editTextPhone = findViewById(R.id.editTextText5);
        editTextPassword = findViewById(R.id.editTextText3);
        editTextConfirmPassword = findViewById(R.id.editTextText4);
        checkBoxMale = findViewById(R.id.checkBox3);
        checkBoxFemale = findViewById(R.id.checkBox4);
        buttonRegister = findViewById(R.id.button);
        txvLoginNow = findViewById(R.id.txtLinkToLogin);

        myDataBase = new MyDataBase(this);

        buttonRegister.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                registerUser();
            }
        });

        txvLoginNow.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                navigateToLogin();
            }
        });
    }

    private void registerUser() {
        String name = editTextName.getText().toString().trim();
        String email = editTextEmail.getText().toString().trim();
        String phone = editTextPhone.getText().toString().trim();
        String password = editTextPassword.getText().toString().trim();
        String confirmPassword = editTextConfirmPassword.getText().toString().trim();
        String sex = checkBoxMale.isChecked() ? "Nam" : checkBoxFemale.isChecked() ? "Nữ" : "";

        if (name.isEmpty() || email.isEmpty() || phone.isEmpty() || password.isEmpty() || sex.isEmpty()) {
            Toast.makeText(this, "Vui lòng điền đầy đủ thông tin", Toast.LENGTH_SHORT).show();
            return;
        }

        if (!password.equals(confirmPassword)) {
            Toast.makeText(this, "Mật khẩu không khớp", Toast.LENGTH_SHORT).show();
            return;
        }

        SQLiteDatabase db = myDataBase.open();

        String checkQuery = "SELECT * FROM " + MyDataBase.tbUser + " WHERE " + MyDataBase.tbUser_Email + " = ? OR " + MyDataBase.tbUser_Phone + " = ?";
        Cursor cursor = db.rawQuery(checkQuery, new String[]{email, phone});

        if (cursor.moveToFirst()) {
            Toast.makeText(this, "Email hoặc số điện thoại đã tồn tại!", Toast.LENGTH_SHORT).show();
            cursor.close();
            db.close();
            return;
        }

        /*String insertUserQuery = "INSERT INTO " + MyDataBase.tbUser + " ("
                + MyDataBase.tbUser_UserName + ", "
                + MyDataBase.tbUser_Password + ", "
                + MyDataBase.tbUser_Email + ", "
                + MyDataBase.tbUser_Phone + ", "
                + MyDataBase.tbUser_Sex + ", "
                + MyDataBase.tbUser_Role + ") VALUES (?, ?, ?, ?, ?, 'User')";

        db.execSQL(insertUserQuery, new Object[]{name, password, email, phone, sex});


         */
        
        myDataBase.addUser(sex,phone,email,name,password,"User");
        Toast.makeText(this, "Đăng ký thành công!", Toast.LENGTH_SHORT).show();

       // db.close();
        navigateToLogin();
    }

    private void navigateToLogin() {
        Intent intent = new Intent(RegisterActivity.this, LoginActivity.class);
        startActivity(intent);
        finish();
    }
}