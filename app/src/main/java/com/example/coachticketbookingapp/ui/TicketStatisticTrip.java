package com.example.coachticketbookingapp.ui;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.PopupWindow;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.coachticketbookingapp.R;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import DataBase.MyDataBase;

public class TicketStatisticTrip extends AppCompatActivity {

    private EditText editTextDeparture;
    private EditText editTextDestination;
    private ListView listView;
    private TripStatisticAdapter adapter;
    private List<TripStatistic> statisticList;
    private MyDataBase databaseHelper;
    private Button btnTimKIem, btnThoat;
    private PopupWindow popupWindow;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ticket_statistic_trip);

        // Ánh xạ các View
        editTextDeparture = findViewById(R.id.editTextDeparture);
        editTextDestination = findViewById(R.id.editTextDestination);
        listView = findViewById(R.id.listView);

        statisticList = new ArrayList<>();
        adapter = new TripStatisticAdapter(this, statisticList);
        listView.setAdapter(adapter);

        databaseHelper = new MyDataBase(this);

        // Nút Thoát
        btnThoat = findViewById(R.id.btnThoat);
        btnThoat.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });

        // Nút Tìm Kiếm
        btnTimKIem = findViewById(R.id.btnTimKiem);
        btnTimKIem.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onSearchClick(v);
            }
        });

        // Hiển thị danh sách tỉnh khi click vào EditText
        editTextDeparture.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showProvinceListPopup(editTextDeparture);
            }
        });

        editTextDestination.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showProvinceListPopup(editTextDestination);
            }
        });

        // Tải thống kê mặc định khi mở Activity
        loadStatistics();
    }

    // Hàm tải dữ liệu mặc định
    private void loadStatistics() {
        SQLiteDatabase db = databaseHelper.getReadableDatabase();

        String query = "SELECT t." + MyDataBase.tbTripInfo_Departure + ", " +
                "t." + MyDataBase.tbTripInfo_Destination + ", " +
                "COUNT(c." + MyDataBase.tbTripBookingDetails_TripBookingDetailsId + ") AS TotalTickets " +
                "FROM " + MyDataBase.tbTripInfo + " t " +
                "LEFT JOIN " + MyDataBase.tbTripBookingDetails + " c " +
                "ON t." + MyDataBase.tbTripInfo_TripId + " = c." + MyDataBase.tbTripBookingDetails_TripId + " " +
                "GROUP BY t." + MyDataBase.tbTripInfo_TripId;

        Cursor cursor = db.rawQuery(query, null);
        statisticList.clear();
        if (cursor.moveToFirst()) {
            do {
                String departure = cursor.getString(0);
                String destination = cursor.getString(1);
                int totalTickets = cursor.getInt(2);
                statisticList.add(new TripStatistic(departure, destination, totalTickets));
            } while (cursor.moveToNext());
        }
        cursor.close();
        adapter.notifyDataSetChanged();
    }

    // Hàm xử lý nút tìm kiếm
    public void onSearchClick(View view) {
        String departure = editTextDeparture.getText().toString().trim();
        String destination = editTextDestination.getText().toString().trim();

        // Kiểm tra nếu thông tin không đầy đủ
        if (departure.isEmpty() || destination.isEmpty()) {
            Toast.makeText(this, "Vui lòng nhập đầy đủ thông tin điểm đi và điểm đến!", Toast.LENGTH_SHORT).show();
            return; // Dừng thực hiện nếu thiếu thông tin
        }

        SQLiteDatabase db = databaseHelper.getReadableDatabase();

        String query = "SELECT t." + MyDataBase.tbTripInfo_Departure + ", " +
                "t." + MyDataBase.tbTripInfo_Destination + ", " +
                "COUNT(c." + MyDataBase.tbTripBookingDetails_TripBookingDetailsId + ") AS TotalTickets " +
                "FROM " + MyDataBase.tbTripInfo + " t " +
                "LEFT JOIN " + MyDataBase.tbTripBookingDetails + " c " +
                "ON t." + MyDataBase.tbTripInfo_TripId + " = c." + MyDataBase.tbTripBookingDetails_TripId + " " +
                "WHERE t." + MyDataBase.tbTripInfo_Departure + " LIKE ? AND " +
                "t." + MyDataBase.tbTripInfo_Destination + " LIKE ? " +
                "GROUP BY t." + MyDataBase.tbTripInfo_TripId;

        Cursor cursor = db.rawQuery(query, new String[]{
                "%" + departure + "%", "%" + destination + "%"
        });

        statisticList.clear();
        if (cursor.moveToFirst()) {
            do {
                String dep = cursor.getString(0);
                String dest = cursor.getString(1);
                int totalTickets = cursor.getInt(2);
                statisticList.add(new TripStatistic(dep, dest, totalTickets));
            } while (cursor.moveToNext());
        }
        cursor.close();
        adapter.notifyDataSetChanged();
    }

    public void setEditTextFields(String departure, String destination) {
        editTextDeparture.setText(departure);
        editTextDestination.setText(destination);
    }

    public void showProvinceListPopup(EditText targetEditText) {
        List<String> provinces = getProvincesList();

        View popupView = LayoutInflater.from(TicketStatisticTrip.this).inflate(R.layout.layout_recyclerview_popup, null);

        RecyclerView recyclerView = popupView.findViewById(R.id.recyclerViewPopup);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        ProvinceAdapter adapter = new ProvinceAdapter(provinces, province -> {
            targetEditText.setText(province);
            if (popupWindow != null && popupWindow.isShowing()) {
                popupWindow.dismiss();
            }
        });
        recyclerView.setAdapter(adapter);

        popupWindow = new PopupWindow(
                popupView,
                ViewGroup.LayoutParams.MATCH_PARENT,
                ViewGroup.LayoutParams.WRAP_CONTENT
        );

        popupWindow.setFocusable(true);
        popupWindow.setOutsideTouchable(true);
        popupWindow.showAsDropDown(targetEditText, 0, 10);
    }

    private List<String> getProvincesList() {
        return Arrays.asList(
                "Hà Nội", "Hồ Chí Minh", "Đà Nẵng", "Hải Phòng", "Cần Thơ",
                "Vũng Tàu", "Quảng Ninh", "Lào Cai", "Lâm Đồng", "Khánh Hòa",
                "Bình Dương", "Đồng Nai", "Nghệ An", "Thanh Hóa", "Thừa Thiên - Huế",
                "Quảng Nam", "Quảng Ngãi", "Bình Thuận", "Kiên Giang", "Hà Giang",
                "Điện Biên", "Lai Châu", "Sơn La", "Yên Bái", "Tuyên Quang",
                "Phú Thọ", "Thái Nguyên", "Lạng Sơn", "Bắc Kạn", "Cao Bằng",
                "Hà Nam", "Nam Định", "Ninh Bình", "Thái Bình", "Quảng Bình",
                "Quảng Trị", "Gia Lai", "Kon Tum", "Đắk Lắk", "Đắk Nông",
                "Hậu Giang", "Sóc Trăng", "Bạc Liêu", "Cà Mau", "An Giang",
                "Bình Phước", "Bình Định", "Phú Yên", "Hòa Bình", "Bắc Ninh",
                "Bắc Giang", "Hải Dương", "Hưng Yên", "Vĩnh Phúc", "Hà Tĩnh",
                "Trà Vinh", "Vĩnh Long", "Long An", "Tiền Giang", "Bến Tre",
                "Tây Ninh"
        );
    }
}
