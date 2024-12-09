package com.example.coachticketbookingapp.ui;

import android.annotation.SuppressLint;
import android.app.DatePickerDialog;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.coachticketbookingapp.R;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import DataBase.MyDataBase;

public class TicketStatisticDayMonth extends AppCompatActivity {

    private ListView listView;
    private BookingStatisticsAdapter adapter;
    private List<BookingStatisticsItem> bookingStatisticsList;
    private EditText editTextDayMonth;
    private Button btnTimKiem, btnThoat;
    private MyDataBase dbHelper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ticket_statistic_day_month);

        // Khởi tạo các view
        listView = findViewById(R.id.listView);
        editTextDayMonth = findViewById(R.id.editTextDayMonth);
        btnTimKiem = findViewById(R.id.btnTimKiem);
        btnThoat = findViewById(R.id.btnThoat);

        // Khởi tạo dbHelper
        dbHelper = new MyDataBase(this);

        // Lấy dữ liệu thống kê từ cơ sở dữ liệu
        bookingStatisticsList = getBookingStatistics();

        // Tạo adapter và gắn vào ListView
        adapter = new BookingStatisticsAdapter(this, bookingStatisticsList);
        listView.setAdapter(adapter);

        btnThoat.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });

        editTextDayMonth.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showDatePickerDialog(editTextDayMonth);
            }
        });

        // Xử lý sự kiện tìm kiếm khi nhấn nút "Tìm kiếm"
        btnTimKiem.setOnClickListener(v -> {
            String datePattern = editTextDayMonth.getText().toString().trim();

            // Kiểm tra nếu người dùng đã nhập dữ liệu vào EditText
            if (datePattern.isEmpty()) {
                // Hiển thị thông báo Toast
                Toast.makeText(TicketStatisticDayMonth.this, "Vui lòng nhập ngày cần tìm kiếm!", Toast.LENGTH_SHORT).show();
            } else {
                // Lọc kết quả theo ngày tháng đã nhập
                List<BookingStatisticsItem> filteredList = filterBookingStatisticsByDate(datePattern);

                // Cập nhật lại adapter với dữ liệu đã lọc
                adapter = new BookingStatisticsAdapter(this, filteredList);
                listView.setAdapter(adapter);
            }
        });
    }

    // Phương thức lấy thống kê số lượng vé theo ngày
    public List<BookingStatisticsItem> getBookingStatistics() {
        SQLiteDatabase db = dbHelper.getReadableDatabase();
        List<BookingStatisticsItem> bookingStatistics = new ArrayList<>();

        String query = "SELECT BookingDate, COUNT(TripBookingDetailsId) as TotalTickets " +
                "FROM TripBookingDetails " +
                "GROUP BY BookingDate";

        Cursor cursor = db.rawQuery(query, null);
        if (cursor.moveToFirst()) {
            do {
                @SuppressLint("Range") String bookingDate = cursor.getString(cursor.getColumnIndex("BookingDate"));
                @SuppressLint("Range") int totalTickets = cursor.getInt(cursor.getColumnIndex("TotalTickets"));
                bookingStatistics.add(new BookingStatisticsItem(bookingDate, totalTickets));
            } while (cursor.moveToNext());
        }
        cursor.close();
        db.close();

        return bookingStatistics;
    }

    // Hàm lọc dữ liệu theo ngày tháng nhập vào
    private List<BookingStatisticsItem> filterBookingStatisticsByDate(String datePattern) {
        List<BookingStatisticsItem> filteredList = new ArrayList<>();
        for (BookingStatisticsItem item : bookingStatisticsList) {
            if (item.getBookingDate().contains(datePattern)) {
                filteredList.add(item);
            }
        }
        return filteredList;
    }

    private void showDatePickerDialog(final EditText editText) {
        // Lấy ngày hiện tại
        final Calendar calendar = Calendar.getInstance();
        int year = calendar.get(Calendar.YEAR);
        int month = calendar.get(Calendar.MONTH);
        int day = calendar.get(Calendar.DAY_OF_MONTH);

        DatePickerDialog datePickerDialog = new DatePickerDialog(this,
                new DatePickerDialog.OnDateSetListener() {
                    @Override
                    public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
                        String selectedDate = String.format("%02d/%02d/%d", dayOfMonth, month + 1, year);
                        editText.setText(selectedDate);
                    }
                },
                year, month, day);

        datePickerDialog.show();
    }
}
