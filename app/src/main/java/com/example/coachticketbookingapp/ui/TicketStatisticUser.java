package com.example.coachticketbookingapp.ui;

import android.annotation.SuppressLint;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.coachticketbookingapp.R;

import java.util.ArrayList;
import java.util.List;

import DataBase.MyDataBase;

public class TicketStatisticUser extends AppCompatActivity {

    private ListView listView;
    private TicketStatisticAdapter adapter;
    private List<TicketStatisticItem> itemList;
    private MyDataBase dbHelper;
    private Button btnTimKiem, btnThoat;
    private EditText editTextEmail, editTextSDT;

    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ticket_statistic_user);

        // Khởi tạo các view
        editTextEmail = findViewById(R.id.editTextEmail);
        editTextSDT = findViewById(R.id.editTextSDT);
        btnTimKiem = findViewById(R.id.btnTimKiem);
        btnThoat = findViewById(R.id.btnThoat);
        listView = findViewById(R.id.listView);

        // Khởi tạo database helper
        dbHelper = new MyDataBase(this);

        // Lấy và hiển thị danh sách ban đầu (tất cả dữ liệu)
        itemList = fetchTicketStatistics("", "", dbHelper);
        adapter = new TicketStatisticAdapter(this, itemList);
        listView.setAdapter(adapter);

        btnThoat.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });

        // Xử lý sự kiện nút tìm kiếm
        btnTimKiem.setOnClickListener(v -> {
            String email = editTextEmail.getText().toString().trim();
            String phone = editTextSDT.getText().toString().trim();

            // Kiểm tra nếu chưa nhập đủ thông tin
            if (email.isEmpty() || phone.isEmpty()) {
                // Hiển thị thông báo Toast
                Toast.makeText(TicketStatisticUser.this, "Vui lòng nhập đầy đủ thông tin!", Toast.LENGTH_SHORT).show();
                return; // Thoát không thực hiện tìm kiếm
            }

            // Lấy dữ liệu theo điều kiện tìm kiếm
            List<TicketStatisticItem> filteredList = fetchTicketStatistics(email, phone, dbHelper);
            adapter = new TicketStatisticAdapter(this, filteredList);
            listView.setAdapter(adapter);
        });


        // Xử lý sự kiện khi nhấn vào item trong ListView
        listView.setOnItemClickListener((parent, view, position, id) -> {
            // Lấy item được chọn
            TicketStatisticItem selectedItem = itemList.get(position);

            // Hiển thị email và số điện thoại lên EditText
            editTextEmail.setText(selectedItem.getEmail());
            editTextSDT.setText(selectedItem.getPhone());
        });
    }

    /**
     * Hàm lấy danh sách thống kê vé từ database
     *
     * @param email    Email của user (tìm kiếm mờ)
     * @param phone    Số điện thoại của user (tìm kiếm mờ)
     * @param dbHelper Đối tượng MyDataBase để thao tác
     * @return Danh sách các mục thống kê vé
     */
    private List<TicketStatisticItem> fetchTicketStatistics(String email, String phone, MyDataBase dbHelper) {
        List<TicketStatisticItem> data = new ArrayList<>();
        SQLiteDatabase db = dbHelper.open();

        // Câu truy vấn với điều kiện tìm kiếm
        String query = "SELECT u.UserName, ti.Departure, ti.Destination, tc.BookingDate, u.Email, u.Phone " +
                "FROM User u " +
                "JOIN TripBookingDetails tc ON u.UserID = tc.UserID " +
                "JOIN TripInfo ti ON tc.TripID = ti.TripID " +
                "WHERE (u.Email LIKE ? OR ? = '') AND (u.Phone LIKE ? OR ? = '')";

        // Gán tham số tìm kiếm
        String emailPattern = "%" + email + "%";
        String phonePattern = "%" + phone + "%";

        Cursor cursor = db.rawQuery(query, new String[]{emailPattern, email, phonePattern, phone});
        if (cursor != null) {
            while (cursor.moveToNext()) {
                String userName = cursor.getString(0);
                String tripDetails = cursor.getString(1) + " - " + cursor.getString(2);
                String bookingDate = cursor.getString(3);
                String userEmail = cursor.getString(4);  // Lấy email
                String userPhone = cursor.getString(5);  // Lấy số điện thoại

                // Thêm thông tin vào danh sách
                data.add(new TicketStatisticItem(userName, tripDetails, bookingDate, userEmail, userPhone));
            }
            cursor.close();
        }

        db.close();
        return data;
    }
}
