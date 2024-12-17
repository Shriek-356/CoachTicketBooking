package com.example.coachticketbookingapp.ui;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.example.coachticketbookingapp.Object.User;
import com.example.coachticketbookingapp.R;

import DataBase.MyDataBase;

public class UpdateUserInfoActivity extends AppCompatActivity {

    private EditText editTextUsername, editTextEmail, editTextPhone;
    private TextView textViewError;
    private Button btnUpdate;
    private User user;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_update_user_info);

        // Khởi tạo các view
        editTextUsername = findViewById(R.id.editTextUsername);
        editTextEmail = findViewById(R.id.editTextEmail);
        editTextPhone = findViewById(R.id.editTextPhone);
        textViewError = findViewById(R.id.textViewError);
        btnUpdate = findViewById(R.id.btnCapNhat);

        Intent intent = getIntent();
        user = (User) intent.getSerializableExtra("user");
        editTextUsername.setText(user.getUserName());
        editTextEmail.setText(user.getEmail());
        editTextPhone.setText(user.getPhone());


        // Xử lý sự kiện nút Update
        btnUpdate.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    if(user!=null) {
                        // Kiểm tra thông tin nhập
                        String username = editTextUsername.getText().toString().trim();
                        String email = editTextEmail.getText().toString().trim();
                        String phone = editTextPhone.getText().toString().trim();

                        // Kiểm tra các giá trị hợp lệ
                        if (username.length() < 5) {
                            textViewError.setVisibility(View.VISIBLE);
                            textViewError.setText("Username phải có ít nhất 5 ký tự.");
                            return;
                        } else if (!isValidEmail(email)) {
                            textViewError.setVisibility(View.VISIBLE);
                            textViewError.setText("Email không hợp lệ.");
                            return;
                        } else if (!isValidPhone(phone)) {
                            textViewError.setVisibility(View.VISIBLE);
                            textViewError.setText("Số điện thoại không hợp lệ.");
                            return;
                        } else {
                            MyDataBase myDataBase = new MyDataBase(UpdateUserInfoActivity.this);
                            if(myDataBase.isUpdateLoginExist(email,phone)){
                                textViewError.setVisibility(View.GONE);
                                // Thực hiện cập nhật thông tin user ở đây

                                if (myDataBase.updateUser(user.getUserID(),username,email,phone)) {
                                    user.setUserName(username);
                                    user.setEmail(email);
                                    user.setPhone(phone);
                                    Toast.makeText(getApplicationContext(), "Cập nhật thành công!", Toast.LENGTH_SHORT).show();

                                    Intent intent = new Intent(UpdateUserInfoActivity.this, MainUIActivity.class);
                                    intent.putExtra("user",user);
                                    intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_NEW_TASK);
                                    startActivity(intent);
                                    finish(); // Đóng Activity sau khi cập nhật
                                }
                                else{
                                    Toast.makeText(getApplicationContext(), "Cập nhật thất bại!", Toast.LENGTH_SHORT).show();
                                }
                            }
                            else{
                                textViewError.setVisibility(View.VISIBLE);
                                textViewError.setText("Số điện thoại hoặc email đã tồn tại");
                                return;
                            }

                        }
                    }
                    else
                        return;
                }
        });

    }

    // Kiểm tra định dạng email
    private boolean isValidEmail(String email) {
        return email != null && email.contains("@") && email.contains(".");
    }

    // Kiểm tra định dạng số điện thoại (VD: 10 chữ số)
    private boolean isValidPhone(String phone) {
        return phone != null && phone.matches("\\d{10}");
    }
}