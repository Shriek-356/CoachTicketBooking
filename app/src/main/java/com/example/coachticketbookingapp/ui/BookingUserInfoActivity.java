package com.example.coachticketbookingapp.ui;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.coachticketbookingapp.Object.TripBookingDetailsPayment;
import com.example.coachticketbookingapp.Object.TripInfo;
import com.example.coachticketbookingapp.Object.User;
import com.example.coachticketbookingapp.R;

import java.text.NumberFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Locale;

public class BookingUserInfoActivity extends AppCompatActivity {
    private User user;
    private TripInfo tripInfo;
    EditText edtFullName, edtPhone, edtEmail;
    TextView txvTamTinh;
    Spinner spinnerSoLuongVe;
    Button btnXacNhanDatt;
    TripBookingDetailsPayment thistripinfo;
    private TextView textView56, textView55;
    private double totalPrice;
    private int ticketQuantity;

    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_booking_user_info);
        //Lay userid va tripid
        Intent intent = getIntent();
        user = (User) intent.getSerializableExtra("userr");
        tripInfo = (TripInfo) intent.getSerializableExtra("tripinfoo");
        ticketQuantity = (Integer)intent.getSerializableExtra("ticketQuantity");


        spinnerSoLuongVe=findViewById(R.id.spinnerSoLuongVe);
        setupTicketQuantitySpinner();
        if(ticketQuantity>0){
            int position = ticketQuantity -1;

            if (position >= 0 && position < spinnerSoLuongVe.getCount()) {
                spinnerSoLuongVe.setSelection(position);  // Chọn phần tử tại vị trí này
            }
        }
        else {
            spinnerSoLuongVe.setSelection(0);  // Nếu ticketQuantity <= 0, chọn giá trị mặc định là 1
        }

        edtFullName=findViewById(R.id.edtFullName);
        edtPhone=findViewById(R.id.edtPhone);
        edtEmail=findViewById(R.id.edtEmaill);
        txvTamTinh=findViewById(R.id.txvTamTinh);
        btnXacNhanDatt=findViewById(R.id.btnXacNhanDatt);
        textView56 = findViewById(R.id.textView56);  // Email error message
        textView55 = findViewById(R.id.textView55);// Phone error message

        edtFullName.setText(user.getUserName());
        edtPhone.setText(user.getPhone());
        edtEmail.setText(user.getEmail());

        spinnerSoLuongVe.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parentView, View selectedItemView, int position, long id) {
                // Lấy số lượng vé được chọn
                String selectedTicketQuantityString = parentView.getItemAtPosition(position).toString();
                Integer selectedTicketQuantity = Integer.valueOf(selectedTicketQuantityString);
                NumberFormat numberFormat = NumberFormat.getCurrencyInstance(new Locale("vi", "VN"));
                txvTamTinh.setText(numberFormat.format(tripInfo.getPrice()*selectedTicketQuantity));
            }
            @Override
            public void onNothingSelected(AdapterView<?> parentView) {

            }
        });

        btnXacNhanDatt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String fullName = edtFullName.getText().toString().trim();
                String email = edtEmail.getText().toString().trim();
                String phone = edtPhone.getText().toString().trim();

                boolean isValid = true;
                // Kiểm tra họ tên
                if (fullName.isEmpty()||fullName=="") {
                    textView56.setVisibility(View.VISIBLE);  // Hiển thị thông báo lỗi
                    isValid = false;
                } else {
                    textView56.setVisibility(View.GONE);  // Ẩn thông báo lỗi
                }

                // Kiểm tra email
                if (email.isEmpty() || !isValidEmail(email) || email=="") {
                    textView55.setVisibility(View.VISIBLE);  // Hiển thị thông báo lỗi
                    isValid = false;
                } else {
                    textView55.setVisibility(View.GONE);  // Ẩn thông báo lỗi
                }

                // Kiểm tra số điện thoại
                if (phone.isEmpty() || !isValidPhone(phone)) {
                    textView55.setVisibility(View.VISIBLE);  // Hiển thị thông báo lỗi
                    isValid = false;
                } else {
                    textView55.setVisibility(View.GONE);  // Ẩn thông báo lỗi
                }

                // Kiểm tra xem có chọn số lượng vé không
                int selectedTicketQuantity = spinnerSoLuongVe.getSelectedItemPosition();
                if (selectedTicketQuantity == -1) {
                    Toast.makeText(BookingUserInfoActivity.this, "Vui lòng chọn số lượng vé!", Toast.LENGTH_SHORT).show();
                    return;
                }
                selectedTicketQuantity += 1; // Sửa lại số lượng vé chính xác, vì getSelectedItemPosition() bắt đầu từ 0
                if (!isValid) {
                    return;  // Nếu có lỗi, không tiếp tục xử lý
                }
                Date currentDateObj = new Date();  // Lấy thời gian hiện tại
                // Chuyển đổi thời gian thành chuỗi theo định dạng dd/MM/yyyy
                SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy", Locale.getDefault());
                String currentDate = sdf.format(currentDateObj);  // Chuyển thời gian thành chuỗi
                totalPrice = selectedTicketQuantity * tripInfo.getPrice();
                thistripinfo = new TripBookingDetailsPayment(user.getUserID(), tripInfo.getTripID(), currentDate, selectedTicketQuantity, totalPrice, fullName, phone, email);
                Intent intentt = new Intent(BookingUserInfoActivity.this, PaymentMethodActivity.class);
                intentt.putExtra("tripinfo", thistripinfo);
                intentt.putExtra("ticketquantity",selectedTicketQuantity);
                intentt.putExtra("user",user);
                startActivity(intentt);
            }
        });
    }

    public void setupTicketQuantitySpinner() {
        // Lấy số vé còn lại của chuyến từ cơ sở dữ liệu
        int availableTickets = tripInfo.getTicketAvailable();

        // Tạo mảng tùy chọn cho Spinner từ 1 đến số vé còn lại
        List<Integer> ticketOptions = new ArrayList<>();
        for (int i = 1; i <= availableTickets; i++) {
            ticketOptions.add(i);
        }
        // Chuyển List<Integer> thành mảng String[] để sử dụng trong Spinner
        String[] ticketOptionsArray = new String[ticketOptions.size()];
        for (int i = 0; i < ticketOptions.size(); i++) {
            ticketOptionsArray[i] = String.valueOf(ticketOptions.get(i));  // Chuyển Integer thành String
        }
        // Cập nhật Spinner với mảng mới
        ArrayAdapter<String> adapter = new ArrayAdapter<>(this,
                android.R.layout.simple_spinner_item, ticketOptionsArray);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinnerSoLuongVe.setAdapter(adapter);
    }

    // Kiểm tra định dạng email
    private boolean isValidEmail(String email) {
        return android.util.Patterns.EMAIL_ADDRESS.matcher(email).matches();
    }

    // Kiểm tra định dạng số điện thoại
    private boolean isValidPhone(String phone) {
        return phone.length() >= 10;  // Có thể tùy chỉnh điều kiện kiểm tra số điện thoại
    }

}