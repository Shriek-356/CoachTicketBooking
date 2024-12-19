package com.example.coachticketbookingapp.ui;

import android.annotation.SuppressLint;
import android.app.DatePickerDialog;
import android.app.TimePickerDialog;
import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.PopupWindow;
import android.widget.TimePicker;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.coachticketbookingapp.R;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Calendar;
import java.util.List;
import java.util.Random;

import DataBase.MyDataBase;

public class ManageTrip extends AppCompatActivity {

    private ListView listViewTrips;
    private TripInfoAdapter adapter;
    private List<Trip> tripInfoList;

    // Khai báo các EditText
    private EditText editTextFirstLocation, editTextSecondLocation, editTextDeparture, editTextDestination;
    private EditText editTextDepartureTime, editTextDepartureDate, editTextDestinationTime, editTextDestinationDate;
    private EditText editTextTicketAvailable, editTextPrice, editTextCoachID;

    private PopupWindow popupWindow;

    // Khai báo nút Thêm, Sửa, Xóa
    private Button btnThem, btnSua, btnXoa, btnThoat;

    private Trip selectedTrip = null; // Lưu chuyến xe đã chọn

    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_manage_trip);

        listViewTrips = findViewById(R.id.listViewTrips);
        tripInfoList = new ArrayList<>();

        // Lấy dữ liệu từ SQLite và thêm vào danh sách
        loadTripData();

        // Tạo adapter và thiết lập cho ListView
        adapter = new TripInfoAdapter(this, tripInfoList);
        listViewTrips.setAdapter(adapter);

        // Khởi tạo các EditText
        editTextCoachID = findViewById(R.id.editTextCoachID);
        editTextFirstLocation = findViewById(R.id.editTextFirstLocation);
        editTextSecondLocation = findViewById(R.id.editTextSecondLocation);
        editTextDeparture = findViewById(R.id.editTextDeparture);
        editTextDestination = findViewById(R.id.editTextDestination);
        editTextDepartureTime = findViewById(R.id.editTextDepartureTime);
        editTextDepartureDate = findViewById(R.id.editTextDepartureDate);
        editTextDestinationTime = findViewById(R.id.editTextDestinationTime);
        editTextDestinationDate = findViewById(R.id.editTextDestinationDate);
        editTextTicketAvailable = findViewById(R.id.editTextTicketAvailable);
        editTextPrice = findViewById(R.id.editTextPrice);

        // Khởi tạo các nút
        btnThem = findViewById(R.id.btnThem);
        btnSua = findViewById(R.id.btnSua);
        btnXoa = findViewById(R.id.btnXoa);
        btnThoat = findViewById(R.id.btnThoat);

        btnThoat.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });

        editTextDepartureDate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showDatePickerDialog(editTextDepartureDate);
            }
        });

        editTextDestinationDate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showDatePickerDialog(editTextDestinationDate);
            }
        });

        editTextDeparture.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Call the method directly from the Activity
                showProvinceListPopup(editTextDeparture);
            }
        });

// Set onClickListener for editTextDestination to show province list
        editTextDestination.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Call the method directly from the Activity
                showProvinceListPopup(editTextDestination);
            }
        });

        editTextDepartureTime.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showTimePickerDialog(editTextDepartureTime);
            }
        });

        editTextDestinationTime.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showTimePickerDialog(editTextDestinationTime);
            }
        });

        editTextCoachID.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Gọi phương thức để hiển thị danh sách các CoachID và Type
                showCoachListPopup(editTextCoachID);
            }
        });

        editTextFirstLocation.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Hiển thị PopupWindow cho FirstLocation
                showLocationListPopup(editTextFirstLocation);
            }
        });

        editTextSecondLocation.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Hiển thị PopupWindow cho SecondLocation
                showLocationListPopup(editTextSecondLocation);
            }
        });


        // Cài đặt sự kiện click item trong ListView
        listViewTrips.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @SuppressLint("Range")
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                // Lấy chuyến đi đã chọn từ danh sách
                selectedTrip = tripInfoList.get(position);

                // Lấy thông tin chi tiết của chuyến đi từ SQLite
                MyDataBase dbHelper = new MyDataBase(ManageTrip.this);
                SQLiteDatabase db = dbHelper.getReadableDatabase();

                // Truy vấn thông tin chuyến đi theo Departure và Destination
                Cursor cursor = db.rawQuery("SELECT * FROM TripInfo WHERE Departure = ? AND Destination = ?",
                        new String[]{selectedTrip.getDeparture(), selectedTrip.getDestination()});

                if (cursor.moveToFirst()) {
                    // Cập nhật các EditText với thông tin chuyến đi
                    editTextCoachID.setText(cursor.getString(cursor.getColumnIndex("CoachID")));
                    editTextFirstLocation.setText(cursor.getString(cursor.getColumnIndex("FirstLocation")));
                    editTextSecondLocation.setText(cursor.getString(cursor.getColumnIndex("SecondLocation")));
                    editTextDeparture.setText(cursor.getString(cursor.getColumnIndex("Departure")));
                    editTextDestination.setText(cursor.getString(cursor.getColumnIndex("Destination")));
                    editTextDepartureTime.setText(cursor.getString(cursor.getColumnIndex("DepartureTime")));
                    editTextDepartureDate.setText(cursor.getString(cursor.getColumnIndex("DepartureDate")));
                    editTextDestinationTime.setText(cursor.getString(cursor.getColumnIndex("DestinationTime")));
                    editTextDestinationDate.setText(cursor.getString(cursor.getColumnIndex("DestinationDate")));
                    editTextTicketAvailable.setText(cursor.getString(cursor.getColumnIndex("TicketAvailable")));
                    editTextPrice.setText(cursor.getString(cursor.getColumnIndex("Price")));
                } else {
                    Toast.makeText(ManageTrip.this, "Không tìm thấy chuyến đi!", Toast.LENGTH_SHORT).show();
                }

                cursor.close();
                db.close();
            }
        });


        // Sự kiện nút Thêm
        btnThem.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                new android.app.AlertDialog.Builder(ManageTrip.this)
                        .setMessage("Bạn có chắc chắn muốn thêm chuyến xe này không?")
                        .setCancelable(false)
                        .setPositiveButton("Đồng ý", (dialog, id) -> {
                            addNewTrip();
                        })
                        .setNegativeButton("Thoát", (dialog, id) -> {
                            dialog.dismiss();
                        })
                        .show();
            }
        });

        // Sự kiện nút Sửa
        btnSua.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Cập nhật chuyến đi đã chọn
                if (selectedTrip != null) {
                    new android.app.AlertDialog.Builder(ManageTrip.this)
                            .setMessage("Bạn có chắc chắn thay đổi chuyến xe này không?")
                            .setCancelable(false)
                            .setPositiveButton("Đồng ý", (dialog, id) -> {
                                updateTrip(selectedTrip);
                            })
                            .setNegativeButton("Thoát", (dialog, id) -> {
                                dialog.dismiss();
                            })
                            .show();
                } else {
                    Toast.makeText(ManageTrip.this, "Chọn một chuyến đi để sửa!", Toast.LENGTH_SHORT).show();
                }
            }
        });

        // Sự kiện nút Xóa
        btnXoa.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Xóa chuyến đi đã chọn
                if (selectedTrip != null) {
                    new android.app.AlertDialog.Builder(ManageTrip.this)
                            .setMessage("Bạn có chắc chắn muốn xóa chuyến xe này không?")
                            .setCancelable(false)
                            .setPositiveButton("Đồng ý", (dialog, id) -> {
                                deleteTrip(selectedTrip);
                                deleteTripBookingDetails(selectedTrip);
                                deleteTrippingCart(selectedTrip);
                            })
                            .setNegativeButton("Thoát", (dialog, id) -> {
                                dialog.dismiss();
                            })
                            .show();
                } else {
                    Toast.makeText(ManageTrip.this, "Chọn một chuyến đi để xóa!", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }

    public void showCoachListPopup(final EditText targetEditText) {
        // Lấy danh sách các CoachID và Type từ cơ sở dữ liệu
        List<String> coachList = getCoachList();

        // Inflate layout cho RecyclerView
        View popupView = LayoutInflater.from(ManageTrip.this).inflate(R.layout.layout_recyclerview_popup, null);

        // Khởi tạo RecyclerView và Adapter
        RecyclerView recyclerView = popupView.findViewById(R.id.recyclerViewPopup);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        // Khởi tạo Adapter cho RecyclerView
        CoachAdapter coachAdapter = new CoachAdapter(coachList, new CoachAdapter.OnCoachClickListener() {
            @Override
            public void onCoachClick(String coachID) {
                // Khi chọn item, cập nhật EditText với CoachID
                targetEditText.setText(coachID);
                if (popupWindow != null && popupWindow.isShowing()) {
                    popupWindow.dismiss(); // Đóng PopupWindow sau khi chọn item
                }
            }
        });
        // Đặt adapter cho RecyclerView
        recyclerView.setAdapter(coachAdapter);

        // Khởi tạo PopupWindow
        popupWindow = new PopupWindow(
                popupView,
                ViewGroup.LayoutParams.MATCH_PARENT,
                ViewGroup.LayoutParams.WRAP_CONTENT
        );

        // Đặt thuộc tính cho PopupWindow
        popupWindow.setFocusable(true);
        popupWindow.setOutsideTouchable(true);

        // Hiển thị PopupWindow bên dưới EditText
        popupWindow.showAsDropDown(targetEditText, 0, 10);
    }

    private List<String> getCoachList() {
        List<String> coachList = new ArrayList<>();
        MyDataBase dbHelper = new MyDataBase(this);
        SQLiteDatabase db = dbHelper.getReadableDatabase();

        // Truy vấn tất cả CoachID và Type từ bảng tbCoach
        Cursor cursor = db.rawQuery("SELECT CoachBrand, CoachID FROM " + MyDataBase.tbCoach, null);

        if (cursor.moveToFirst()) {
            do {
                @SuppressLint("Range") String coachBrand = cursor.getString(cursor.getColumnIndex("CoachBrand"));
                @SuppressLint("Range") String coachID = cursor.getString(cursor.getColumnIndex("CoachID"));

                // Thêm chuỗi "Type - CoachID" vào danh sách
                coachList.add(coachBrand + " - " + coachID);
            } while (cursor.moveToNext());
        }

        cursor.close();
        db.close();
        return coachList;
    }

    public void showLocationListPopup(final EditText targetEditText) {
        // Lấy danh sách các FirstLocation và SecondLocation từ cơ sở dữ liệu
        List<String> locationList = getLocationList();

        // Inflate layout cho RecyclerView
        View popupView = LayoutInflater.from(ManageTrip.this).inflate(R.layout.layout_recyclerview_popup, null);

        // Khởi tạo RecyclerView và Adapter
        RecyclerView recyclerView = popupView.findViewById(R.id.recyclerViewPopup);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        // Khởi tạo Adapter cho RecyclerView
        LocationAdapter locationAdapter = new LocationAdapter(locationList, new LocationAdapter.OnLocationClickListener() {
            @Override
            public void onLocationClick(String location) {
                // Khi chọn item, cập nhật EditText với giá trị chọn
                targetEditText.setText(location); // Đặt giá trị FirstLocation hoặc SecondLocation vào EditText
                if (popupWindow != null && popupWindow.isShowing()) {
                    popupWindow.dismiss(); // Đóng PopupWindow sau khi chọn item
                }
            }
        });

        // Đặt adapter cho RecyclerView
        recyclerView.setAdapter(locationAdapter);

        // Khởi tạo PopupWindow
        popupWindow = new PopupWindow(
                popupView,
                ViewGroup.LayoutParams.MATCH_PARENT,
                ViewGroup.LayoutParams.WRAP_CONTENT
        );

        // Đặt thuộc tính cho PopupWindow
        popupWindow.setFocusable(true);
        popupWindow.setOutsideTouchable(true);

        // Hiển thị PopupWindow bên dưới EditText
        popupWindow.showAsDropDown(targetEditText, 0, 10);
    }


    private List<String> getLocationList() {
        List<String> locationList = new ArrayList<>();
        MyDataBase dbHelper = new MyDataBase(this);
        SQLiteDatabase db = dbHelper.getReadableDatabase();

        // Truy vấn tất cả FirstLocation và SecondLocation từ bảng TripInfo
        Cursor cursor = db.rawQuery("SELECT FirstLocation, SecondLocation FROM " + MyDataBase.tbTripInfo, null);

        if (cursor.moveToFirst()) {
            do {
                @SuppressLint("Range") String firstLocation = cursor.getString(cursor.getColumnIndex("FirstLocation"));
                @SuppressLint("Range") String secondLocation = cursor.getString(cursor.getColumnIndex("SecondLocation"));

                // Thêm giá trị FirstLocation và SecondLocation vào danh sách
                locationList.add(firstLocation);
                locationList.add(secondLocation);
            } while (cursor.moveToNext());
        }

        cursor.close();
        db.close();
        return locationList;
    }


    private void loadTripData() {
        MyDataBase dbHelper = new MyDataBase(this);
        SQLiteDatabase db = dbHelper.getReadableDatabase();

        // Lấy dữ liệu từ bảng TripInfo
        Cursor cursor = db.rawQuery("SELECT Departure, Destination, DepartureTime, DepartureDate, TripID FROM TripInfo", null);

        if (cursor.moveToFirst()) {
            do {
                @SuppressLint("Range") int tripID = cursor.getInt(cursor.getColumnIndex("TripID"));
                @SuppressLint("Range") String departure = cursor.getString(cursor.getColumnIndex("Departure"));
                @SuppressLint("Range") String destination = cursor.getString(cursor.getColumnIndex("Destination"));
                @SuppressLint("Range") String departureTime = cursor.getString(cursor.getColumnIndex("DepartureTime"));
                @SuppressLint("Range") String departureDate = cursor.getString(cursor.getColumnIndex("DepartureDate"));
                Trip trip = new Trip(departure, destination, departureTime, departureDate);
                trip.setTripID(tripID);
                tripInfoList.add(trip);
            } while (cursor.moveToNext());
        }

        cursor.close();
        db.close();
    }

    @SuppressLint("Range")
    private void addNewTrip() {
        // Lấy dữ liệu từ EditText
        String coachID = editTextCoachID.getText().toString().trim();
        String firstLocation = editTextFirstLocation.getText().toString().trim();
        String secondLocation = editTextSecondLocation.getText().toString().trim();
        String departure = editTextDeparture.getText().toString().trim();
        String destination = editTextDestination.getText().toString().trim();
        String departureTime = editTextDepartureTime.getText().toString().trim();
        String departureDate = editTextDepartureDate.getText().toString().trim();
        String destinationTime = editTextDestinationTime.getText().toString().trim();
        String destinationDate = editTextDestinationDate.getText().toString().trim();
        String ticketAvailable = editTextTicketAvailable.getText().toString().trim();
        String price = editTextPrice.getText().toString().trim();

        // Kiểm tra các trường dữ liệu
        if (coachID.isEmpty() || firstLocation.isEmpty() || secondLocation.isEmpty() || departure.isEmpty() || destination.isEmpty()
                || departureTime.isEmpty() || departureDate.isEmpty() || destinationTime.isEmpty() || destinationDate.isEmpty()
                || ticketAvailable.isEmpty() || price.isEmpty()) {

            Toast.makeText(this, "Vui lòng điền đầy đủ thông tin!", Toast.LENGTH_SHORT).show();
            return;
        }

        // Kiểm tra nếu CoachID tồn tại trong bảng tbCoach
        MyDataBase dbHelper = new MyDataBase(this);
        SQLiteDatabase db = dbHelper.getReadableDatabase();
        Cursor coachCursor = db.rawQuery("SELECT * FROM " + MyDataBase.tbCoach + " WHERE " + MyDataBase.tbCoach_CoachID + " = ?", new String[]{coachID});

        if (coachCursor.getCount() == 0) {
            // Không tìm thấy CoachID trong bảng tbCoach
            Toast.makeText(this, "Không có mã loại xe tương ứng!", Toast.LENGTH_SHORT).show();
            coachCursor.close();
            db.close();
            return;
        }

        coachCursor.close();

        // Kiểm tra nếu chuyến đi đã tồn tại
        Cursor cursor = db.rawQuery("SELECT * FROM TripInfo WHERE FirstLocation = ? AND SecondLocation = ? AND Departure = ? AND Destination = ? AND DepartureTime = ? AND DepartureDate = ? AND DestinationTime = ? AND DestinationDate = ? AND TicketAvailable = ? AND Price = ? AND CoachID = ?",
                new String[]{firstLocation, secondLocation, departure, destination, departureTime, departureDate, destinationTime, destinationDate, ticketAvailable, price, coachID});

        if (cursor.getCount() > 0) {
            Toast.makeText(this, "Chuyến đi này đã tồn tại!", Toast.LENGTH_SHORT).show();
            cursor.close();
            db.close();
            return;
        }

        cursor.close();


        // Random giá trị cho Distance từ 10 đến 1000 km
        int distance = (int) (Math.random() * (1000 - 10 + 1)) + 10;

        // Chèn dữ liệu vào cơ sở dữ liệu
        db = dbHelper.getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put("CoachID", coachID);
        values.put("FirstLocation", firstLocation);
        values.put("SecondLocation", secondLocation);
        values.put("Departure", departure);
        values.put("Destination", destination);
        values.put("DepartureTime", departureTime);
        values.put("DepartureDate", departureDate);
        values.put("DestinationTime", destinationTime);
        values.put("DestinationDate", destinationDate);
        values.put("TicketAvailable", ticketAvailable);
        values.put("Price", price);
        values.put("Distance", distance);

        long result = db.insert("TripInfo", null, values);
        if (result != -1) {
            Toast.makeText(this, "Chuyến đi đã được thêm!", Toast.LENGTH_SHORT).show();

            // Thêm chuyến đi vào danh sách và cập nhật ListView
            tripInfoList.add(new Trip(departure, destination, departureTime, departureDate));
            adapter.notifyDataSetChanged(); // Cập nhật ListView
        } else {
            Toast.makeText(this, "Thêm chuyến đi thất bại!", Toast.LENGTH_SHORT).show();
        }

        db.close();
    }


    private void updateTrip(Trip trip) {
        String coachID = editTextCoachID.getText().toString();
        String firstLocation = editTextFirstLocation.getText().toString();
        String secondLocation = editTextSecondLocation.getText().toString();
        String departure = editTextDeparture.getText().toString();
        String destination = editTextDestination.getText().toString();
        String departureTime = editTextDepartureTime.getText().toString();
        String departureDate = editTextDepartureDate.getText().toString();
        String destinationTime = editTextDestinationTime.getText().toString();
        String destinationDate = editTextDestinationDate.getText().toString();
        String ticketAvailable = editTextTicketAvailable.getText().toString();
        String price = editTextPrice.getText().toString();

        if (firstLocation.isEmpty() || secondLocation.isEmpty() || departure.isEmpty() || destination.isEmpty()) {
            Toast.makeText(this, "Vui lòng điền đầy đủ thông tin", Toast.LENGTH_SHORT).show();
            return;
        }

        // ghi vao csdl

        MyDataBase dbHelper = new MyDataBase(this);
        SQLiteDatabase db = dbHelper.getReadableDatabase();
        Cursor coachCursor = db.rawQuery("SELECT * FROM " + MyDataBase.tbCoach + " WHERE " + MyDataBase.tbCoach_CoachID + " = ?", new String[]{coachID});

        if (coachCursor.getCount() == 0) {
            // Không tìm thấy CoachID trong bảng tbCoach
            Toast.makeText(this, "Không có mã loại xe tương ứng!", Toast.LENGTH_SHORT).show();
            coachCursor.close();
            db.close();
            return;
        }

        coachCursor.close();

        ContentValues values = new ContentValues();
        values.put("CoachID", coachID);
        values.put("FirstLocation", firstLocation);
        values.put("SecondLocation", secondLocation);
        values.put("Departure", departure);
        values.put("Destination", destination);
        values.put("DepartureTime", departureTime);
        values.put("DepartureDate", departureDate);
        values.put("DestinationTime", destinationTime);
        values.put("DestinationDate", destinationDate);
        values.put("TicketAvailable", ticketAvailable);
        values.put("Price", price);

        String whereClause = "Departure = ? AND Destination = ?";
        String[] whereArgs = new String[]{trip.getDeparture(), trip.getDestination()};

        int result = db.update("TripInfo", values, whereClause, whereArgs);
        if (result > 0) {
            Toast.makeText(this, "Chuyến đi đã được cập nhật!", Toast.LENGTH_SHORT).show();
            loadTripData(); // Tải lại danh sách sau khi cập nhật
        } else {
            Toast.makeText(this, "Cập nhật chuyến đi thất bại!", Toast.LENGTH_SHORT).show();
        }

        db.close();
    }

    private void deleteTrip(Trip trip) {
        MyDataBase dbHelper = new MyDataBase(this);
        SQLiteDatabase db = dbHelper.getWritableDatabase();

        String whereClause = " TripID = ? ";
        String[] whereArgs = new String[]{String.valueOf(trip.getTripID())};

        int result = db.delete("TripInfo", whereClause, whereArgs);
        if (result > 0) {
            Toast.makeText(this, "Chuyến đi đã được xóa!", Toast.LENGTH_SHORT).show();

            tripInfoList.remove(trip);

            // Cập nhật laij
            adapter.notifyDataSetChanged(); // Cập nhật ListView mà không tải lại trang
        } else {
            Toast.makeText(this, "Xóa chuyến đi thất bại!", Toast.LENGTH_SHORT).show();
        }
        db.close();
    }

    private void deleteTripBookingDetails(Trip trip) {
        MyDataBase dbHelper = new MyDataBase(this);
        SQLiteDatabase db = dbHelper.getWritableDatabase();

        String whereClause = " TripID = ? ";
        String[] whereArgs = new String[]{String.valueOf(trip.getTripID())};
        db.delete("TripBookingDetails", whereClause, whereArgs);
        db.close();
    }

    private void deleteTrippingCart(Trip trip) {
        MyDataBase dbHelper = new MyDataBase(this);
        SQLiteDatabase db = dbHelper.getWritableDatabase();
        String whereClause = " TripID = ? ";
        String[] whereArgs = new String[]{String.valueOf(trip.getTripID())};
        db.delete("TrippingCart", whereClause, whereArgs);
        db.close();
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

    private void showTimePickerDialog(final EditText editText) {
        // Lấy giờ hiện tại
        final Calendar calendar = Calendar.getInstance();
        int hour = calendar.get(Calendar.HOUR_OF_DAY);
        int minute = calendar.get(Calendar.MINUTE);

        // Tạo TimePickerDialog
        TimePickerDialog timePickerDialog = new TimePickerDialog(this,
                new TimePickerDialog.OnTimeSetListener() {
                    @Override
                    public void onTimeSet(TimePicker view, int hourOfDay, int minute) {
                        String selectedTime = String.format("%02d:%02d", hourOfDay, minute);
                        editText.setText(selectedTime);
                    }
                }, hour, minute, true); // `true` để sử dụng định dạng 24 giờ, nếu muốn định dạng 12 giờ thì đổi thành `false`.

        // Hiển thị TimePickerDialog
        timePickerDialog.show();
    }


    public void showProvinceListPopup(EditText targetEditText) {
        // Lấy danh sách các tỉnh
        List<String> provinces = getProvincesList();

        View popupView = LayoutInflater.from(ManageTrip.this).inflate(R.layout.layout_recyclerview_popup, null);

        // Khởi tạo RecyclerView và Adapter
        RecyclerView recyclerView = popupView.findViewById(R.id.recyclerViewPopup);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        ProvinceAdapter adapter = new ProvinceAdapter(provinces, province -> {
            targetEditText.setText(province);
            if (popupWindow != null && popupWindow.isShowing()) {
                popupWindow.dismiss();
            }
        });
        recyclerView.setAdapter(adapter);

        // Khởi tạo PopupWindow
        popupWindow = new PopupWindow(
                popupView,
                ViewGroup.LayoutParams.MATCH_PARENT,
                ViewGroup.LayoutParams.WRAP_CONTENT
        );

        // Đặt thuộc tính cho PopupWindow
        popupWindow.setFocusable(true);
        popupWindow.setOutsideTouchable(true);

        // Hiển thị PopupWindow tại vị trí bên dưới EditText
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