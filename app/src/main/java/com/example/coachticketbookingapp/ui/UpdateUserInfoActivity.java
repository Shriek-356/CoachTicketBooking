package com.example.coachticketbookingapp.ui;

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

import com.example.coachticketbookingapp.R;

public class UpdateUserInfoActivity extends AppCompatActivity {

    private EditText editTextUsername, editTextEmail, editTextPhone, editTextPassword;
    private Spinner spinnerGender;
    private TextView textViewError;
    private Button btnUpdate;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_update_user_info);

        // Khởi tạo các view
        editTextUsername = findViewById(R.id.editTextUsername);
        editTextEmail = findViewById(R.id.editTextEmail);
        editTextPhone = findViewById(R.id.editTextPhone);
        editTextPassword = findViewById(R.id.editTextPassword);
        spinnerGender = findViewById(R.id.spinnerGender);
        textViewError = findViewById(R.id.textViewError);
        btnUpdate = findViewById(R.id.btnCapNhat);

        // Gắn mảng giới tính vào Spinner
        ArrayAdapter<CharSequence> genderAdapter = ArrayAdapter.createFromResource(
                this,
                R.array.gender_options,
                android.R.layout.simple_spinner_item
        );
        genderAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinnerGender.setAdapter(genderAdapter);

        // Xử lý sự kiện nút Update
        btnUpdate.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    // Kiểm tra thông tin nhập
                    String username = editTextUsername.getText().toString().trim();
                    String email = editTextEmail.getText().toString().trim();
                    String phone = editTextPhone.getText().toString().trim();
                    String password = editTextPassword.getText().toString().trim();
                    String gender = spinnerGender.getSelectedItem().toString();

                    // Kiểm tra các giá trị hợp lệ
                    if (username.length() < 5) {
                        textViewError.setVisibility(View.VISIBLE);
                        textViewError.setText("Username phải có ít nhất 5 ký tự.");
                    } else if (!isValidEmail(email)) {
                        textViewError.setVisibility(View.VISIBLE);
                        textViewError.setText("Email không hợp lệ.");
                    } else if (!isValidPhone(phone)) {
                        textViewError.setVisibility(View.VISIBLE);
                        textViewError.setText("Số điện thoại không hợp lệ.");
                    } else {
                        textViewError.setVisibility(View.GONE);
                        // Thực hiện cập nhật thông tin user ở đây
                        Toast.makeText(getApplicationContext(), "Cập nhật thành công!", Toast.LENGTH_SHORT).show();
                    }
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