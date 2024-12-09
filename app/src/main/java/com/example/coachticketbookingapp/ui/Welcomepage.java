package com.example.coachticketbookingapp.ui;

import android.annotation.SuppressLint;
import android.app.DatePickerDialog;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.Switch;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.coachticketbookingapp.R;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Calendar;
import java.util.List;

import DataBase.MyDataBase;

public class Welcomepage extends AppCompatActivity {
   /* TextView greetingTextView;
    Button buttonVeCuaToi;
    Button buttonTaiKhoan;
    Button buttonTimChuyenXe;
    EditText editTextNgayDi;

    private EditText editTextNoiXuatPhat, editTextNoiDen;
    private RecyclerView recyclerView;
    private LinearLayout locationSelectionLayout;
    private Switch switchKhuhoi;

    MyDataBase myDataBase;

    @SuppressLint({"SetTextI18n", "MissingInflatedId"})
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_welcomepage);

        greetingTextView = findViewById(R.id.greetingTextView);
        buttonVeCuaToi = findViewById(R.id.buttonVeCuaToi);
        buttonTaiKhoan = findViewById(R.id.buttonTaiKhoan);
        buttonTimChuyenXe = findViewById(R.id.buttonTimChuyenXe);

        editTextNoiXuatPhat = findViewById(R.id.editTextNoiXuatPhat);
        editTextNoiDen = findViewById(R.id.editTextNoiDen);
        editTextNgayDi = findViewById(R.id.editTextNgayDi);

        editTextNoiXuatPhat = findViewById(R.id.editTextNoiXuatPhat);
        editTextNoiDen = findViewById(R.id.editTextNoiDen);
        locationSelectionLayout = findViewById(R.id.locationSelectionLayout);
        recyclerView = findViewById(R.id.recyclerView);
        switchKhuhoi = findViewById(R.id.switchKhuhoi);

        switchKhuhoi.setOnCheckedChangeListener((buttonView, isChecked) -> {
            if (isChecked) {
                showDatePickerDialogKhuHoi();
            }
        });

        List<String> locations = Arrays.asList(
                "Hà Nội", "TP Hồ Chí Minh", "Đà Nẵng", "An Giang", "Bà Rịa-Vũng Tàu", "Bắc Giang", "Bắc Kạn",
                "Bắc Ninh", "Bến Tre", "Bình Dương", "Bình Định", "Bình Phước", "Bình Thuận", "Cà Mau",
                "Cao Bằng", "Cần Thơ", "Côn Đảo", "Đắk Lắk", "Đắk Nông", "Điện Biên", "Đồng Nai", "Đồng Tháp",
                "Gia Lai", "Hà Giang", "Hà Nam", "Hải Dương", "Hải Phòng", "Hậu Giang", "Hòa Bình",
                "Hưng Yên", "Khánh Hòa", "Kiên Giang", "Kon Tum", "Lai Châu", "Lâm Đồng", "Lạng Sơn", "Lào Cai",
                "Long An", "Nam Định", "Nghệ An", "Ninh Bình", "Ninh Thuận", "Phú Thọ", "Phú Yên", "Quảng Bình",
                "Quảng Nam", "Quảng Ngãi", "Quảng Ninh", "Quảng Trị", "Sóc Trăng", "Sơn La", "Tây Ninh", "Thái Bình",
                "Thái Nguyên", "Thanh Hóa", "Thừa Thiên-Huế", "Tiền Giang", "TP Cần Thơ", "Trà Vinh", "Tuyên Quang",
                "Vĩnh Long", "Vĩnh Phúc", "Yên Bái"
        );

        LocationAdapter adapter = new LocationAdapter(locations, location -> {
            if (editTextNoiXuatPhat.isFocused()) {
                editTextNoiXuatPhat.setText(location);
            } else if (editTextNoiDen.isFocused()) {
                editTextNoiDen.setText(location);
            }
            locationSelectionLayout.setVisibility(View.GONE);
        });

        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setAdapter(adapter);

        editTextNoiXuatPhat.setOnClickListener(v -> locationSelectionLayout.setVisibility(View.VISIBLE));
        editTextNoiDen.setOnClickListener(v -> locationSelectionLayout.setVisibility(View.VISIBLE));

        editTextNgayDi.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                showDatePickerDialog();
            }
        });

        SharedPreferences sharedPreferences = getSharedPreferences("UserPrefs", Context.MODE_PRIVATE);
        String userName = getIntent().getStringExtra("USER_NAME");

        if (userName != null && !userName.isEmpty()) {
            sharedPreferences.edit().putString("USER_NAME", userName).apply();
        } else {
            userName = sharedPreferences.getString("USER_NAME", "");
        }

        if (userName != null && !userName.isEmpty()) {
            greetingTextView.setText("Xin chào, " + userName);
        }

        myDataBase = new MyDataBase(this);

        buttonTimChuyenXe.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String departure = editTextNoiXuatPhat.getText().toString().trim();
                String destination = editTextNoiDen.getText().toString().trim();
                String departureDate = editTextNgayDi.getText().toString().trim();

                searchTrip(departure, destination, departureDate);
            }
        });

        buttonVeCuaToi.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(Welcomepage.this, LgTicket.class);
                startActivity(intent);
            }
        });

        buttonTaiKhoan.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(Welcomepage.this, LgAccount.class);
                startActivity(intent);
            }
        });
    }

    private void searchTrip(String departure, String destination, String departureDate) {
        SQLiteDatabase db = myDataBase.getReadableDatabase();
        String query = "SELECT * FROM " + MyDataBase.tbTripInfo + " WHERE " +
                MyDataBase.tbTripInfo_Departure + " = ? AND " +
                MyDataBase.tbTripInfo_Destination + " = ? AND " +
                MyDataBase.tbTripInfo_DepartureDate + " = ?";
        Cursor cursor = db.rawQuery(query, new String[]{departure, destination, departureDate});

        if (cursor.moveToFirst()) {
            List<TripInfoo> tripList = new ArrayList<>();

            do {
                @SuppressLint("Range") String departureTime = cursor.getString(cursor.getColumnIndex(MyDataBase.tbTripInfo_DepartureTime));
                @SuppressLint("Range") int ticketsAvailable = cursor.getInt(cursor.getColumnIndex(MyDataBase.tbTripInfo_TicketAvailable));
                @SuppressLint("Range") String coachBrand = cursor.getString(cursor.getColumnIndex(MyDataBase.tbTripInfo_CoachBrand));
                @SuppressLint("Range") String price = cursor.getString(cursor.getColumnIndex(MyDataBase.tbTripInfo_Price));
                @SuppressLint("Range") int distance = cursor.getInt(cursor.getColumnIndex(MyDataBase.tbTripInfo_Distance));

                TripInfoo tripInfoo = new TripInfoo(departureTime, ticketsAvailable, coachBrand, price, distance);
                tripList.add(tripInfoo);
            } while (cursor.moveToNext());

            Intent intent = new Intent(Welcomepage.this, SearchResultsActivity.class);
            intent.putParcelableArrayListExtra("tripList", new ArrayList<>(tripList));
            startActivity(intent);

        } else {
            Toast.makeText(Welcomepage.this, "Không tìm thấy chuyến xe.", Toast.LENGTH_SHORT).show();
        }
        cursor.close();
    }



    private void showDatePickerDialog() {

        final Calendar calendar = Calendar.getInstance();
        int year = calendar.get(Calendar.YEAR);
        int month = calendar.get(Calendar.MONTH);
        int day = calendar.get(Calendar.DAY_OF_MONTH);


        DatePickerDialog datePickerDialog = new DatePickerDialog(Welcomepage.this, new DatePickerDialog.OnDateSetListener() {
            @Override
            public void onDateSet(DatePicker view, int year, int monthOfYear, int dayOfMonth) {

                String selectedDate = String.format("%02d/%02d/%d", dayOfMonth, monthOfYear + 1, year);
                editTextNgayDi.setText(selectedDate);
            }
        }, year, month, day);


        datePickerDialog.show();
    }

    private void showDatePickerDialogKhuHoi() {
        final Calendar calendar = Calendar.getInstance();
        int year = calendar.get(Calendar.YEAR);
        int month = calendar.get(Calendar.MONTH);
        int day = calendar.get(Calendar.DAY_OF_MONTH);

        DatePickerDialog datePickerDialog = new DatePickerDialog(Welcomepage.this, new DatePickerDialog.OnDateSetListener() {
            @Override
            public void onDateSet(DatePicker view, int year, int monthOfYear, int dayOfMonth) {
                String selectedDate = String.format("%02d/%02d/%d", dayOfMonth, monthOfYear + 1, year);
            }
        }, year, month, day);

        datePickerDialog.show();
    }

    */
}