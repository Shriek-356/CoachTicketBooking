package com.example.coachticketbookingapp.ui;

import android.annotation.SuppressLint;
import android.app.AlertDialog;
import android.app.DatePickerDialog;
import android.app.TimePickerDialog;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.PopupWindow;
import android.widget.TimePicker;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.coachticketbookingapp.R;

import java.util.Arrays;
import java.util.Calendar;
import java.util.List;

import DataBase.MyDataBase;

public class ManageCoach extends AppCompatActivity {
    private Button btnThoat, btnTimKiem, btnThem, btnSua, btnXoa;
    private EditText editTextCoachBrand, editTextTotalSeat, editTextLicensePlate, editTextType;
    private EditText editTextDeparture, editTextDestination, editTextDepartureTime, editTextDepartureDate;
    private ListView listViewCoach;
    private MyDataBase myDataBase;
    private List<CoachTripInfo> coachTripInfoList;
    private List<BusTripInfo> busTripInfoList;
    private ArrayAdapter adapter;
    private PopupWindow popupWindow;// Dùng một adapter chung
    private int selectedPosition = -1;

    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_manage_coach);

        // Khởi tạo các view
        btnThem = findViewById(R.id.btnThem);
        btnSua = findViewById(R.id.btnSua);
        btnXoa = findViewById(R.id.btnXoa);
        btnTimKiem = findViewById(R.id.btnTimKiem);
        btnThoat = findViewById(R.id.btnThoat);
        editTextCoachBrand = findViewById(R.id.editTextCoachBrand);
        editTextTotalSeat = findViewById(R.id.editTextTotalSeat);
        editTextLicensePlate = findViewById(R.id.editTextLicensePlate);
        editTextType = findViewById(R.id.editTextType);
        editTextDeparture = findViewById(R.id.editTextDeparture);
        editTextDestination = findViewById(R.id.editTextDestination);
        editTextDepartureTime = findViewById(R.id.editTextDepartureTime);
        editTextDepartureDate = findViewById(R.id.editTextDepartureDate);
        listViewCoach = findViewById(R.id.listView);

        // Lấy dữ liệu từ cơ sở dữ liệu
        myDataBase = new MyDataBase(this);
        coachTripInfoList = myDataBase.getCoachTripInfo(); // Hàm lấy dữ liệu chuyến xe coach

        // Đặt adapter mặc định cho listView
        adapter = new CoachTripAdapter(this, coachTripInfoList);
        listViewCoach.setAdapter(adapter);

        // Sự kiện khi chọn một chuyến xe từ listView
        listViewCoach.setOnItemClickListener((parent, view, position, id) -> {
            selectedPosition = position;
            CoachTripInfo selectedCoach = coachTripInfoList.get(position);

            // Điền thông tin chuyến xe vào các EditText
            editTextCoachBrand.setText(selectedCoach.getCoachBrand());
            editTextTotalSeat.setText(String.valueOf(selectedCoach.getTotalSeat()));
            editTextLicensePlate.setText(selectedCoach.getLicensePlate());
            editTextType.setText(selectedCoach.getType());
            editTextDeparture.setText(selectedCoach.getDeparture());
            editTextDestination.setText(selectedCoach.getDestination());
            editTextDepartureTime.setText(selectedCoach.getDepartureTime());
            editTextDepartureDate.setText(selectedCoach.getDepartureDate());
        });

        editTextDepartureTime.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showTimePickerDialog(editTextDepartureTime);
            }
        });

        editTextDepartureDate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showDatePickerDialog(editTextDepartureDate);
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

        editTextType.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showCoachTypePopup(editTextType);
            }
        });


        // Khi nhấn nút tìm kiếm
        btnTimKiem.setOnClickListener(v -> {
            // Lấy dữ liệu từ các EditText
            String departure = editTextDeparture.getText().toString();
            String destination = editTextDestination.getText().toString();
            String departureTime = editTextDepartureTime.getText().toString();
            String departureDate = editTextDepartureDate.getText().toString();

            if (departure.isEmpty() || destination.isEmpty() || departureTime.isEmpty() || departureDate.isEmpty()) {
                // Hiển thị thông báo yêu cầu nhập đầy đủ thông tin
                Toast.makeText(this, "Vui lòng nhập đầy đủ thông tin trước khi tìm kiếm!", Toast.LENGTH_SHORT).show();
                return; // Dừng thực hiện tìm kiếm
            }

            // Tìm kiếm các chuyến xe trong cơ sở dữ liệu
            busTripInfoList = myDataBase.searchBusTrips(departure, destination, departureTime, departureDate);

            // Kiểm tra và cập nhật ListView
            if (busTripInfoList != null && !busTripInfoList.isEmpty()) {
                // Cập nhật adapter cho ListView với dữ liệu tìm được
                adapter = new BusTripAdapter(this, busTripInfoList);
                listViewCoach.setAdapter(adapter);
            } else {
                // Nếu không có kết quả, hiển thị danh sách chuyến xe mặc định
                adapter = new CoachTripAdapter(this, coachTripInfoList);
                listViewCoach.setAdapter(adapter);
            }
        });


// Sự kiện thêm
        btnThem.setOnClickListener(v -> {
            String coachBrand = editTextCoachBrand.getText().toString();
            int totalSeat = Integer.parseInt(editTextTotalSeat.getText().toString());
            String licensePlate = editTextLicensePlate.getText().toString();
            String type = editTextType.getText().toString();

            // Kiểm tra thông tin nhập vào có hợp lệ không
            if (coachBrand.isEmpty() || licensePlate.isEmpty() || type.isEmpty()) {
                Toast.makeText(this, "Vui lòng nhập đầy đủ thông tin", Toast.LENGTH_SHORT).show();
                return;
            }

            // Kiểm tra xem biển số xe đã tồn tại trong cơ sở dữ liệu chưa
            if (myDataBase.isLicensePlateExist(licensePlate)) {
                Toast.makeText(this, "Biển số xe đã tồn tại", Toast.LENGTH_SHORT).show();
                return;
            }

            // Tạo một AlertDialog để xác nhận việc thêm xe
            new AlertDialog.Builder(this)
                    .setTitle("Xác nhận thêm loại xe")
                    .setMessage("Bạn có muốn thêm loại xe này không?")
                    .setPositiveButton("OK", (dialog, which) -> {
                        // Thêm vào cơ sở dữ liệu khi người dùng chọn "OK"
                        myDataBase.addCoach(coachBrand, totalSeat, licensePlate, type);

                        // Cập nhật lại danh sách trong ListView
                        coachTripInfoList = myDataBase.getCoachTripInfo();  // Cập nhật danh sách
                        adapter = new CoachTripAdapter(this, coachTripInfoList);
                        listViewCoach.setAdapter(adapter);

                        // Thông báo thành công
                        Toast.makeText(this, "Loại xe đã được thêm thành công", Toast.LENGTH_SHORT).show();
                    })
                    .setNegativeButton("Hủy", (dialog, which) -> {
                        // Hành động khi người dùng chọn "Hủy" (không làm gì)
                        dialog.dismiss();
                    })
                    .show(); // Hiển thị hộp thoại
        });



// Sự kiện sửa
        btnSua.setOnClickListener(v -> {
            // Kiểm tra xem người dùng có chọn chuyến xe không
            if (selectedPosition == -1) {
                Toast.makeText(this, "Vui lòng chọn một chuyến xe để sửa", Toast.LENGTH_SHORT).show();
                return;
            }

            String coachBrand = editTextCoachBrand.getText().toString();
            int totalSeat = Integer.parseInt(editTextTotalSeat.getText().toString());
            String licensePlate = editTextLicensePlate.getText().toString();
            String type = editTextType.getText().toString();

            // Kiểm tra thông tin nhập vào có hợp lệ không
            if (coachBrand.isEmpty() || licensePlate.isEmpty() || type.isEmpty()) {
                Toast.makeText(this, "Vui lòng nhập đầy đủ thông tin", Toast.LENGTH_SHORT).show();
                return;
            }

            // Lấy coachID từ chuyến xe được chọn
            int selectedCoachID = coachTripInfoList.get(selectedPosition).getCoachID();

            // Kiểm tra xem biển số xe có bị trùng với chuyến xe khác không
            if (myDataBase.isLicensePlateExistForUpdate(licensePlate, selectedCoachID)) {
                Toast.makeText(this, "Biển số xe đã tồn tại, không thể sửa", Toast.LENGTH_SHORT).show();
                return;
            }

            // Tạo một AlertDialog để xác nhận việc sửa xe
            new AlertDialog.Builder(this)
                    .setTitle("Xác nhận sửa loại xe")
                    .setMessage("Bạn có muốn sửa thông tin loại xe này không?")
                    .setPositiveButton("OK", (dialog, which) -> {
                        // Cập nhật cơ sở dữ liệu khi người dùng chọn "OK"
                        myDataBase.updateCoach(selectedCoachID, coachBrand, totalSeat, licensePlate, type);

                        // Cập nhật lại danh sách trong ListView
                        coachTripInfoList = myDataBase.getCoachTripInfo();  // Cập nhật danh sách
                        adapter = new CoachTripAdapter(this, coachTripInfoList);
                        listViewCoach.setAdapter(adapter);

                        // Thông báo thành công
                        Toast.makeText(this, "Loại xe đã được sửa thành công", Toast.LENGTH_SHORT).show();
                    })
                    .setNegativeButton("Hủy", (dialog, which) -> {
                        // Hành động khi người dùng chọn "Hủy" (không làm gì)
                        dialog.dismiss();
                    })
                    .show(); // Hiển thị hộp thoại
        });




// Sự kiện xóa
        btnXoa.setOnClickListener(v -> {
            // Lấy giá trị từ các EditText
            String coachBrand = editTextCoachBrand.getText().toString();
            String licensePlate = editTextLicensePlate.getText().toString();
            String type = editTextType.getText().toString();

            // Kiểm tra xem các EditText có trống không
            if (coachBrand.isEmpty() || licensePlate.isEmpty() || type.isEmpty()) {
                Toast.makeText(this, "Vui lòng nhập đầy đủ thông tin để xóa", Toast.LENGTH_SHORT).show();
                return;
            }

            // Lấy coachID từ chuyến xe được chọn
            int selectedCoachID = coachTripInfoList.get(selectedPosition).getCoachID(); // selectedPosition là vị trí đã chọn trong ListView

            // Kiểm tra xem thông tin trong các EditText có trùng khớp với thông tin của chuyến xe được chọn không
            if (!licensePlate.equals(coachTripInfoList.get(selectedPosition).getLicensePlate())) {
                Toast.makeText(this, "Thông tin không khớp. Không thể xóa.", Toast.LENGTH_SHORT).show();
                return;
            }

            // Tạo một AlertDialog để xác nhận việc xóa xe
            new AlertDialog.Builder(this)
                    .setTitle("Xác nhận xóa loại xe")
                    .setMessage("Bạn có chắc chắn muốn xóa loại xe này không?")
                    .setPositiveButton("OK", (dialog, which) -> {
                        // Xóa khỏi cơ sở dữ liệu khi người dùng chọn "OK"
                        myDataBase.deleteCoach(selectedCoachID);

                        // Cập nhật lại danh sách trong ListView
                        coachTripInfoList = myDataBase.getCoachTripInfo();  // Cập nhật danh sách
                        adapter = new CoachTripAdapter(this, coachTripInfoList);
                        listViewCoach.setAdapter(adapter);

                        // Thông báo xóa thành công
                        Toast.makeText(this, "Loại xe đã được xóa", Toast.LENGTH_SHORT).show();
                    })
                    .setNegativeButton("Hủy", (dialog, which) -> {
                        // Hành động khi người dùng chọn "Hủy" (không làm gì)
                        dialog.dismiss();
                    })
                    .show(); // Hiển thị hộp thoại
        });

        btnThoat.setOnClickListener(v -> finish());
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

        View popupView = LayoutInflater.from(ManageCoach.this).inflate(R.layout.layout_recyclerview_popup, null);

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

    public void showCoachTypePopup(EditText targetEditText) {
        // Danh sách các loại xe
        List<String> coachTypes = Arrays.asList("Ghế ngồi", "Giường nằm", "Limousine");

        // Tạo view popup từ layout
        View popupView = LayoutInflater.from(ManageCoach.this).inflate(R.layout.layout_recyclerview_popup, null);

        // Khởi tạo RecyclerView và Adapter
        RecyclerView recyclerView = popupView.findViewById(R.id.recyclerViewPopup);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        // Tạo adapter cho RecyclerView
        CoachTypeAdapter adapter = new CoachTypeAdapter(coachTypes, new CoachTypeAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(String coachType) {
                // Cập nhật text của editTextType với loại xe đã chọn
                targetEditText.setText(coachType);

                // Nếu popup đang mở thì đóng nó lại
                if (popupWindow != null && popupWindow.isShowing()) {
                    popupWindow.dismiss();
                }
            }
        });

        // Gắn adapter vào RecyclerView
        recyclerView.setAdapter(adapter);

        // Tạo PopupWindow
        popupWindow = new PopupWindow(
                popupView,
                ViewGroup.LayoutParams.MATCH_PARENT,
                ViewGroup.LayoutParams.WRAP_CONTENT
        );

        // Đặt thuộc tính cho PopupWindow
        popupWindow.setFocusable(true);
        popupWindow.setOutsideTouchable(true);

        // Hiển thị PopupWindow dưới EditText
        popupWindow.showAsDropDown(targetEditText, 0, 10);
    }

}
