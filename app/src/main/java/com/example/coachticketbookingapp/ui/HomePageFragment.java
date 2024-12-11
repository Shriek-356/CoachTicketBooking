package com.example.coachticketbookingapp.ui;

import android.annotation.SuppressLint;
import android.app.DatePickerDialog;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;

import androidx.annotation.Nullable;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.util.Log;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.PopupWindow;
import android.widget.Switch;
import android.widget.TextView;
import android.widget.Toast;

import com.example.coachticketbookingapp.Object.TripInfo;
import com.example.coachticketbookingapp.Object.User;
import com.example.coachticketbookingapp.R;

import java.io.Serializable;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Calendar;
import java.util.List;
import java.util.Locale;

import com.example.coachticketbookingapp.Object.item;

import DataBase.MyDataBase;

public class HomePageFragment extends Fragment {
    private Switch switchTrip;
    private ConstraintLayout constraintLayout;
    private EditText editText;
    ArrayList<item> dsitem = new ArrayList<>();

    List<TripInfo> dsTrip = new ArrayList<>();

    RecyclerView recyclerView;
    private TextView txvHello;
    private EditText editTextNoiXuatPhat, editTextNoiDen;
    private PopupWindow popupWindow;
    private EditText editTextNgayVe;
    private EditText editTextNgayDi;

    MyDataBase myDataBase ;

    private User thisUser;
    private View view;
    private Button btnTimKiemChuyenDi;
    @SuppressLint("MissingInflatedId")
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        view = inflater.inflate(R.layout.fragment_home_page, container, false);
        switchTrip = view.findViewById(R.id.switchKhuhoi);
        constraintLayout=view.findViewById(R.id.csLayout3);
        editText = view.findViewById(R.id.editTextNgayVe);
        recyclerView = view.findViewById(R.id.recyclerview);
        txvHello=view.findViewById(R.id.txvHello);
        btnTimKiemChuyenDi=view.findViewById(R.id.btnTimKiemChuyenDi);

        editTextNoiXuatPhat = view.findViewById(R.id.editTextNoiXuatPhat);
        editTextNoiDen = view.findViewById(R.id.editTextNoiDen);
        editTextNgayDi = view.findViewById(R.id.editTextNgayDi);
        editTextNgayVe = view.findViewById(R.id.editTextNgayVe);

        myDataBase= new MyDataBase(getContext());

        editTextNoiXuatPhat.setOnClickListener(v -> showProvinceListPopup(editTextNoiXuatPhat));
        editTextNoiDen.setOnClickListener(v -> showProvinceListPopup(editTextNoiDen));

        editTextNgayDi.setOnClickListener(v -> showDatePicker(editTextNgayDi));
        editTextNgayVe.setOnClickListener(v -> showDatePicker(editTextNgayVe));

        LinearLayoutManager layoutManager = new LinearLayoutManager(getContext(), LinearLayoutManager.HORIZONTAL, false);//Dieu chinh cach hien thi
        recyclerView.setLayoutManager(layoutManager);
        dsitem.add(new item("100000","A","b",R.drawable.loginbackground));
        dsitem.add(new item("100000","x","b",R.drawable.loginbackground));
        dsitem.add(new item("100000","v","o",R.drawable.loginbackground));
        dsitem.add(new item("100000","j","i",R.drawable.loginbackground));
        dsitem.add(new item("100000","A","k",R.drawable.loginbackground));
        dsitem.add(new item("100000","A","k",R.drawable.loginbackground));

        ItemAdapter itemAdapter = new ItemAdapter(getContext(),dsitem);
        recyclerView.setAdapter(itemAdapter);

        //Nhan object user tu activity;

        Bundle bundle = getArguments();

        if(bundle!=null){
            thisUser = (User) bundle.getSerializable("user");
            if(thisUser!=null){
                txvHello.setText("Xin Chào " + thisUser.getUserName());
            }
        }
        return view;
    }
    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public void onResume() {
        super.onResume();
        
        //Xu li khi nguoi dung nhan switch
        switchTrip.setOnCheckedChangeListener((compoundButton, isChecked) -> {
            if (isChecked) {
                ConstraintLayout.LayoutParams layoutParams = (ConstraintLayout.LayoutParams) constraintLayout.getLayoutParams();
                layoutParams.height = dpToPx(306, getContext());
                constraintLayout.setLayoutParams(layoutParams);
                editText.setVisibility(View.VISIBLE);
            } else {
                ConstraintLayout.LayoutParams layoutParams = (ConstraintLayout.LayoutParams) constraintLayout.getLayoutParams();
                layoutParams.height = dpToPx(244, getContext());
                constraintLayout.setLayoutParams(layoutParams);
                editText.setVisibility(View.GONE);
            }
        });

        //Xu ly button tim kiem
        btnTimKiemChuyenDi.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                String noiXuatPhat = editTextNoiXuatPhat.getText().toString().trim();
                String noiDen = editTextNoiDen.getText().toString().trim();
                String ngayDi = editTextNgayDi.getText().toString().trim();
                dsTrip= myDataBase.FoundTrip(noiXuatPhat,noiDen,ngayDi);

                //Truyen dsTrip

                Intent intent = new Intent (getActivity(), TripListFoundActivity.class);
                intent.putExtra("dsTrip", (Serializable) dsTrip);
                startActivity(intent);
            }

        });


    }

    public int dpToPx(int dp, Context context) {
        float density = context.getResources().getDisplayMetrics().density;
        return Math.round(dp * density); // Làm tròn kết quả
    }

    public void showProvinceListPopup(EditText targetEditText) {
        List<String> provinces = getProvincesList();

        View popupView = LayoutInflater.from(getContext()).inflate(R.layout.layout_recyclerview_popup, null);

        RecyclerView recyclerView = popupView.findViewById(R.id.recyclerViewPopup);
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        ProvinceAdapter adapter = new ProvinceAdapter(provinces, province -> {
            targetEditText.setText(province);
            popupWindow.dismiss();
        });
        recyclerView.setAdapter(adapter);

        popupWindow = new PopupWindow(popupView, ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT);
        popupWindow.setFocusable(true);
        popupWindow.showAtLocation(targetEditText, Gravity.CENTER, 0, 0);
    }

    private List<String> getProvincesList() {
        return Arrays.asList(
                "Hà Nội", "TP Hồ Chí Minh", "Đà Nẵng", "Hải Phòng", "Cần Thơ",
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

    private void showDatePicker(EditText targetEditText) {
        final Calendar calendar = Calendar.getInstance();
        int year = calendar.get(Calendar.YEAR);
        int month = calendar.get(Calendar.MONTH);
        int day = calendar.get(Calendar.DAY_OF_MONTH);

        DatePickerDialog datePickerDialog = new DatePickerDialog(
                getContext(),
                (view, selectedYear, selectedMonth, selectedDay) -> {
                    // Chọn ngày từ DatePicker
                    Calendar selectedDate = Calendar.getInstance();
                    selectedDate.set(selectedYear, selectedMonth, selectedDay);

                    // Định dạng ngày tháng theo dd/MM/yyyy
                    SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy", Locale.getDefault());
                    String formattedDate = dateFormat.format(selectedDate.getTime());

                    // Hiển thị ngày trong EditText
                    targetEditText.setText(formattedDate);
                },
                year,
                month,
                day
        );
        datePickerDialog.show();
    }

}