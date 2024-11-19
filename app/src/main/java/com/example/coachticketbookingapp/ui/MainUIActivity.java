package com.example.coachticketbookingapp.ui;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.Switch;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.coachticketbookingapp.Object.User;
import com.example.coachticketbookingapp.R;
import com.google.android.material.bottomnavigation.BottomNavigationView;

import DataBase.MyDataBase;

public class MainUIActivity extends AppCompatActivity {
    Button btndangnhap, buttonTimChuyenXe, buttonVeCuaToi, buttonTaiKhoan;
    MyDataBase myDataBase;
    EditText editTextNgayDi;
    private EditText editTextNoiXuatPhat, editTextNoiDen;
    private RecyclerView recyclerView;
    private LinearLayout locationSelectionLayout;
    private Switch switchKhuhoi;
    private BottomNavigationView bnvMainMenu;
    private User presentUser;

    //Tao cac bien fragment de co the luu du lieu khi ma chuyen tab
    private HomePageFragment homePageFragment = new HomePageFragment();
    private MyTicketFragment myTicketFragment = new MyTicketFragment();
    private MyAccountFragment myAccountFragment=new MyAccountFragment();

    //Ham thay the fragment
    public void replaceFragment(Fragment fragment){
        FragmentManager fragmentManager = getSupportFragmentManager();
        fragmentManager.beginTransaction().replace(R.id.framelayout,fragment).commit();
    }
    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_mainui);
        bnvMainMenu=findViewById(R.id.bnvMainMenu);
        //Lay du lieu tu LoginActivity qua;

        presentUser = (User) getIntent().getSerializableExtra("thisUser");

        if(presentUser!=null){
            //Tao bundle de truyen du lieu
            Bundle bundle = new Bundle();
            bundle.putSerializable("thisUser",presentUser);

            homePageFragment.setArguments(bundle);
        }

        replaceFragment(homePageFragment);//Dat mac dinh la homepage
        /*
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

        editTextNgayDi.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                showDatePickerDialog();
            }
        });

        List<String> locations = Arrays.asList(
                "Hà Nội", "TP Hồ Chí Minh", "Đà Nẵng", "An Giang", "Bà Rịa-Vũng Tàu", "Bắc Giang", "Bắc Kạn",
                "Bắc Ninh", "Bến Tre", "Bình Dương", "Bình Định", "Bình Phước", "Bình Thuận", "Cà Mau",
                "Cao Bằng", "Cần Thơ", "Côn Đảo", "Đắk Lắk", "Đắk Nông", "Điện Biên", "Đồng Nai", "Đồng Tháp",
                "Gia Lai", "Hà Giang", "Hà Nam", "Hải Dương", "Hải Phòng", "Hậu Giang", "Hòa Bình", "Hồ Chí Minh",
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

        myDataBase = new MyDataBase(this);
        myDataBase.addAdminAccount();
        addDefaultTripInfo();

        //btndangnhap = findViewById(R.id.btndangnhap);
        btndangnhap.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent myIntent = new Intent(MainUIActivity.this, LoginActivity.class);
                startActivity(myIntent);
            }
        });

        buttonTimChuyenXe = findViewById(R.id.buttonTimChuyenXe);
        buttonTimChuyenXe.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainUIActivity.this, LoginActivity.class);
                startActivity(intent);
            }
        });

        buttonVeCuaToi = findViewById(R.id.buttonVeCuaToi);
        buttonVeCuaToi.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainUIActivity.this, NolgTicket.class);
                startActivity(intent);
            }
        });

        buttonTaiKhoan = findViewById(R.id.buttonTaiKhoan);
        buttonTaiKhoan.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainUIActivity.this, NolgAccount.class);
                startActivity(intent);
            }
        });

         */
    }



    /*private void addDefaultTripInfo() {
        addTripIfNotExists("Hà Nội", "TP Hồ Chí Minh", "19/12/2024", "08:00", 20, "Thaco", "200000", 1726);
        addTripIfNotExists("Hà Nội", "TP Hồ Chí Minh", "20/12/2024", "06:00", 20, "Hanh Cafe", "200000", 1726);
        addTripIfNotExists("Hà Nội", "TP Hồ Chí Minh", "20/12/2024", "07:00", 20, "Tracomeco", "200000", 1726);
        addTripIfNotExists("Hà Nội", "TP Hồ Chí Minh", "20/12/2024", "09:00", 20, "Thaco", "200000", 1726);
        addTripIfNotExists("Hà Nội", "Đà Nẵng", "21/12/2024", "10:00", 18, "Thaco", "180000", 1500);
        addTripIfNotExists("Hà Nội", "Hải Phòng", "22/12/2024", "11:00", 15, "Tracomeco", "150000", 120);

        addTripIfNotExists("Vĩnh Long", "Quảng Ngãi", "19/12/2024", "08:00", 20, "Tracomeco", "310000", 950);
        addTripIfNotExists("Quảng Ngãi", "Vĩnh Long", "20/12/2024", "09:00", 22, "Hanh Cafe", "320000", 950);
        addTripIfNotExists("Bắc Giang", "Hà Tĩnh", "21/12/2024", "10:00", 20, "Thaco", "330000", 1100);
        addTripIfNotExists("Hà Tĩnh", "Bắc Giang", "22/12/2024", "11:00", 22, "Tracomeco", "340000", 1100);

        addTripIfNotExists("Nam Định", "Lai Châu", "23/12/2024", "08:00", 20, "Hanh Cafe", "340000", 1250);
        addTripIfNotExists("Lai Châu", "Nam Định", "24/12/2024", "09:00", 22, "Thaco", "350000", 1250);
        addTripIfNotExists("Quảng Trị", "Đắk Lắk", "25/12/2024", "10:00", 20, "Tracomeco", "330000", 1200);
        addTripIfNotExists("Đắk Lắk", "Quảng Trị", "19/12/2024", "11:00", 22, "Hanh Cafe", "340000", 1200);

        addTripIfNotExists("Hải Dương", "Bình Phước", "20/12/2024", "08:00", 20, "Thaco", "330000", 1050);
        addTripIfNotExists("Bình Phước", "Hải Dương", "21/12/2024", "09:00", 22, "Tracomeco", "340000", 1050);
        addTripIfNotExists("Hà Giang", "Hồ Chí Minh", "22/12/2024", "10:00", 20, "Hanh Cafe", "350000", 1400);
        addTripIfNotExists("Hồ Chí Minh", "Hà Giang", "23/12/2024", "11:00", 22, "Thaco", "360000", 1400);

        addTripIfNotExists("Cao Bằng", "Phú Yên", "24/12/2024", "08:00", 20, "Tracomeco", "320000", 1100);
        addTripIfNotExists("Phú Yên", "Cao Bằng", "25/12/2024", "09:00", 22, "Hanh Cafe", "330000", 1100);
        addTripIfNotExists("Long An", "Quảng Bình", "19/12/2024", "10:00", 20, "Thaco", "330000", 1200);
        addTripIfNotExists("Quảng Bình", "Long An", "20/12/2024", "11:00", 22, "Tracomeco", "340000", 1200);

        addTripIfNotExists("Lâm Đồng", "Hà Nội", "21/12/2024", "08:00", 20, "Hanh Cafe", "350000", 1300);
        addTripIfNotExists("Hà Nội", "Lâm Đồng", "22/12/2024", "09:00", 22, "Thaco", "360000", 1300);
        addTripIfNotExists("Hà Nam", "Quảng Trị", "23/12/2024", "10:00", 20, "Tracomeco", "330000", 1100);
        addTripIfNotExists("Quảng Trị", "Hà Nam", "24/12/2024", "11:00", 22, "Hanh Cafe", "340000", 1100);

        addTripIfNotExists("Cần Thơ", "Vĩnh Phúc", "25/12/2024", "08:00", 20, "Thaco", "320000", 900);
        addTripIfNotExists("Vĩnh Phúc", "Cần Thơ", "19/12/2024", "09:00", 22, "Tracomeco", "330000", 900);
        addTripIfNotExists("Đà Nẵng", "Bắc Kạn", "20/12/2024", "10:00", 20, "Hanh Cafe", "340000", 1250);
        addTripIfNotExists("Bắc Kạn", "Đà Nẵng", "21/12/2024", "11:00", 22, "Thaco", "350000", 1250);

        addTripIfNotExists("Bình Thuận", "Vĩnh Long", "22/12/2024", "08:00", 20, "Tracomeco", "310000", 850);
        addTripIfNotExists("Vĩnh Long", "Bình Thuận", "23/12/2024", "09:00", 22, "Hanh Cafe", "320000", 850);
        addTripIfNotExists("Phú Thọ", "Sóc Trăng", "24/12/2024", "10:00", 20, "Thaco", "300000", 800);
        addTripIfNotExists("Sóc Trăng", "Phú Thọ", "25/12/2024", "11:00", 22, "Tracomeco", "310000", 800);

        addTripIfNotExists("Vĩnh Long", "Quảng Ngãi", "19/12/2024", "12:00", 20, "Tracomeco", "310000", 950);
        addTripIfNotExists("Quảng Ngãi", "Vĩnh Long", "20/12/2024", "13:00", 22, "Hanh Cafe", "320000", 950);
        addTripIfNotExists("Bắc Giang", "Hà Tĩnh", "21/12/2024", "14:00", 20, "Thaco", "330000", 1100);
        addTripIfNotExists("Hà Tĩnh", "Bắc Giang", "22/12/2024", "15:00", 22, "Tracomeco", "340000", 1100);

        addTripIfNotExists("Nam Định", "Lai Châu", "23/12/2024", "16:00", 20, "Hanh Cafe", "340000", 1250);
        addTripIfNotExists("Lai Châu", "Nam Định", "24/12/2024", "17:00", 22, "Thaco", "350000", 1250);
        addTripIfNotExists("Quảng Trị", "Đắk Lắk", "25/12/2024", "18:00", 20, "Tracomeco", "330000", 1200);
        addTripIfNotExists("Đắk Lắk", "Quảng Trị", "19/12/2024", "19:00", 22, "Hanh Cafe", "340000", 1200);

        addTripIfNotExists("Hải Dương", "Bình Phước", "20/12/2024", "20:00", 20, "Thaco", "330000", 1050);
        addTripIfNotExists("Bình Phước", "Hải Dương", "21/12/2024", "21:00", 22, "Tracomeco", "340000", 1050);
        addTripIfNotExists("Hà Giang", "Hồ Chí Minh", "22/12/2024", "22:00", 20, "Hanh Cafe", "350000", 1400);
        addTripIfNotExists("Hồ Chí Minh", "Hà Giang", "23/12/2024", "23:00", 22, "Thaco", "360000", 1400);

        addTripIfNotExists("Cao Bằng", "Phú Yên", "24/12/2024", "07:00", 20, "Tracomeco", "320000", 1100);
        addTripIfNotExists("Phú Yên", "Cao Bằng", "25/12/2024", "06:00", 22, "Hanh Cafe", "330000", 1100);
        addTripIfNotExists("Long An", "Quảng Bình", "19/12/2024", "05:00", 20, "Thaco", "330000", 1200);
        addTripIfNotExists("Quảng Bình", "Long An", "20/12/2024", "04:00", 22, "Tracomeco", "340000", 1200);

        addTripIfNotExists("Lâm Đồng", "Hà Nội", "21/12/2024", "03:00", 20, "Hanh Cafe", "350000", 1300);
        addTripIfNotExists("Hà Nội", "Lâm Đồng", "22/12/2024", "02:00", 22, "Thaco", "360000", 1300);
        addTripIfNotExists("Hà Nam", "Quảng Trị", "23/12/2024", "01:00", 20, "Tracomeco", "330000", 1100);
        addTripIfNotExists("Quảng Trị", "Hà Nam", "24/12/2024", "00:00", 22, "Hanh Cafe", "340000", 1100);

        addTripIfNotExists("Tuyên Quang", "Bắc Ninh", "19/12/2024", "07:00", 20, "Tracomeco", "310000", 850);
        addTripIfNotExists("Bắc Ninh", "Tuyên Quang", "20/12/2024", "08:00", 22, "Hanh Cafe", "320000", 850);
        addTripIfNotExists("Hòa Bình", "Yên Bái", "21/12/2024", "09:00", 20, "Thaco", "330000", 900);
        addTripIfNotExists("Yên Bái", "Hòa Bình", "22/12/2024", "10:00", 22, "Tracomeco", "340000", 900);

        addTripIfNotExists("Thái Bình", "Bắc Giang", "23/12/2024", "11:00", 20, "Hanh Cafe", "330000", 950);
        addTripIfNotExists("Bắc Giang", "Thái Bình", "24/12/2024", "12:00", 22, "Thaco", "340000", 950);
        addTripIfNotExists("Hải Phòng", "Quảng Nam", "25/12/2024", "13:00", 20, "Tracomeco", "350000", 1050);
        addTripIfNotExists("Quảng Nam", "Hải Phòng", "19/12/2024", "14:00", 22, "Hanh Cafe", "360000", 1050);

        addTripIfNotExists("Lạng Sơn", "Bắc Kạn", "20/12/2024", "15:00", 20, "Thaco", "330000", 1100);
        addTripIfNotExists("Bắc Kạn", "Lạng Sơn", "21/12/2024", "16:00", 22, "Tracomeco", "340000", 1100);
        addTripIfNotExists("Lào Cai", "Hà Nam", "22/12/2024", "17:00", 20, "Hanh Cafe", "350000", 1150);
        addTripIfNotExists("Hà Nam", "Lào Cai", "23/12/2024", "18:00", 22, "Thaco", "360000", 1150);

        addTripIfNotExists("Bình Dương", "Phú Thọ", "24/12/2024", "19:00", 20, "Tracomeco", "330000", 1000);
        addTripIfNotExists("Phú Thọ", "Bình Dương", "25/12/2024", "20:00", 22, "Hanh Cafe", "340000", 1000);
        addTripIfNotExists("Vĩnh Long", "Bà Rịa - Vũng Tàu", "19/12/2024", "21:00", 20, "Thaco", "320000", 900);
        addTripIfNotExists("Bà Rịa - Vũng Tàu", "Vĩnh Long", "20/12/2024", "22:00", 22, "Tracomeco", "330000", 900);

        addTripIfNotExists("Cà Mau", "Bến Tre", "21/12/2024", "23:00", 20, "Hanh Cafe", "310000", 950);
        addTripIfNotExists("Bến Tre", "Cà Mau", "22/12/2024", "07:00", 22, "Thaco", "320000", 950);
        addTripIfNotExists("Kiên Giang", "An Giang", "23/12/2024", "08:00", 20, "Tracomeco", "330000", 1100);
        addTripIfNotExists("An Giang", "Kiên Giang", "24/12/2024", "09:00", 22, "Hanh Cafe", "340000", 1100);

        addTripIfNotExists("Đắk Nông", "Sóc Trăng", "25/12/2024", "10:00", 20, "Thaco", "330000", 1000);
        addTripIfNotExists("Sóc Trăng", "Đắk Nông", "19/12/2024", "11:00", 22, "Tracomeco", "340000", 1000);
        addTripIfNotExists("Đồng Nai", "Tây Ninh", "20/12/2024", "12:00", 20, "Hanh Cafe", "310000", 950);
        addTripIfNotExists("Tây Ninh", "Đồng Nai", "21/12/2024", "13:00", 22, "Thaco", "320000", 950);

        addTripIfNotExists("Hà Tĩnh", "Lạng Sơn", "22/12/2024", "14:00", 20, "Tracomeco", "330000", 1200);
        addTripIfNotExists("Lạng Sơn", "Hà Tĩnh", "23/12/2024", "15:00", 22, "Hanh Cafe", "340000", 1200);
        addTripIfNotExists("Quảng Ngãi", "Gia Lai", "24/12/2024", "16:00", 20, "Thaco", "350000", 1250);
        addTripIfNotExists("Gia Lai", "Quảng Ngãi", "25/12/2024", "17:00", 22, "Tracomeco", "360000", 1250);

        addTripIfNotExists("Hà Nội", "TP Hồ Chí Minh", "19/12/2024", "08:00", 20, "Hanh Cafe", "350000", 1400);
        addTripIfNotExists("TP Hồ Chí Minh", "Hà Nội", "20/12/2024", "09:00", 22, "Tracomeco", "360000", 1400);

        addTripIfNotExists("Hà Nội", "TP Hồ Chí Minh", "21/12/2024", "10:00", 20, "Thaco", "370000", 1400);
        addTripIfNotExists("TP Hồ Chí Minh", "Hà Nội", "22/12/2024", "11:00", 22, "Hanh Cafe", "380000", 1400);

        addTripIfNotExists("Hà Nội", "TP Hồ Chí Minh", "23/12/2024", "12:00", 20, "Tracomeco", "390000", 1400);
        addTripIfNotExists("TP Hồ Chí Minh", "Hà Nội", "24/12/2024", "13:00", 22, "Thaco", "400000", 1400);

        addTripIfNotExists("Hà Nội", "TP Hồ Chí Minh", "25/12/2024", "14:00", 20, "Hanh Cafe", "410000", 1400);
        addTripIfNotExists("TP Hồ Chí Minh", "Hà Nội", "19/12/2024", "15:00", 22, "Tracomeco", "420000", 1400);

        addTripIfNotExists("Hà Nội", "TP Hồ Chí Minh", "20/12/2024", "16:00", 20, "Thaco", "430000", 1400);
        addTripIfNotExists("TP Hồ Chí Minh", "Hà Nội", "21/12/2024", "17:00", 22, "Hanh Cafe", "440000", 1400);

        addTripIfNotExists("Hà Nội", "TP Hồ Chí Minh", "22/12/2024", "18:00", 20, "Tracomeco", "450000", 1400);
        addTripIfNotExists("TP Hồ Chí Minh", "Hà Nội", "23/12/2024", "19:00", 22, "Thaco", "460000", 1400);

        addTripIfNotExists("Hà Nội", "TP Hồ Chí Minh", "19/12/2024", "06:00", 20, "Thaco", "470000", 1400);
        addTripIfNotExists("TP Hồ Chí Minh", "Hà Nội", "19/12/2024", "07:30", 22, "Hanh Cafe", "480000", 1400);

        addTripIfNotExists("Hà Nội", "TP Hồ Chí Minh", "20/12/2024", "09:00", 20, "Tracomeco", "490000", 1400);
        addTripIfNotExists("TP Hồ Chí Minh", "Hà Nội", "20/12/2024", "10:30", 22, "Thaco", "500000", 1400);

        addTripIfNotExists("Hà Nội", "TP Hồ Chí Minh", "21/12/2024", "11:00", 20, "Hanh Cafe", "510000", 1400);
        addTripIfNotExists("TP Hồ Chí Minh", "Hà Nội", "21/12/2024", "12:30", 22, "Tracomeco", "520000", 1400);

        addTripIfNotExists("Hà Nội", "TP Hồ Chí Minh", "22/12/2024", "13:00", 20, "Thaco", "530000", 1400);
        addTripIfNotExists("TP Hồ Chí Minh", "Hà Nội", "22/12/2024", "14:30", 22, "Hanh Cafe", "540000", 1400);

        addTripIfNotExists("Hà Nội", "TP Hồ Chí Minh", "23/12/2024", "15:00", 20, "Tracomeco", "550000", 1400);
        addTripIfNotExists("TP Hồ Chí Minh", "Hà Nội", "23/12/2024", "16:30", 22, "Thaco", "560000", 1400);

        addTripIfNotExists("Hà Nội", "TP Hồ Chí Minh", "24/12/2024", "17:00", 20, "Hanh Cafe", "570000", 1400);
        addTripIfNotExists("TP Hồ Chí Minh", "Hà Nội", "24/12/2024", "18:30", 22, "Tracomeco", "580000", 1400);

        addTripIfNotExists("Hà Nội", "TP Hồ Chí Minh", "25/12/2024", "19:00", 20, "Thaco", "590000", 1400);
        addTripIfNotExists("TP Hồ Chí Minh", "Hà Nội", "25/12/2024", "20:30", 22, "Hanh Cafe", "600000", 1400);

        addTripIfNotExists("Hà Nội", "Đà Nẵng", "19/12/2024", "06:00", 20, "Thaco", "300000", 750);
        addTripIfNotExists("Đà Nẵng", "Hà Nội", "19/12/2024", "07:30", 22, "Hanh Cafe", "310000", 750);

        addTripIfNotExists("Hà Nội", "Đà Nẵng", "20/12/2024", "08:00", 20, "Tracomeco", "320000", 750);
        addTripIfNotExists("Đà Nẵng", "Hà Nội", "20/12/2024", "09:30", 22, "Thaco", "330000", 750);

        addTripIfNotExists("Hà Nội", "Đà Nẵng", "21/12/2024", "10:00", 20, "Hanh Cafe", "340000", 750);
        addTripIfNotExists("Đà Nẵng", "Hà Nội", "21/12/2024", "11:30", 22, "Tracomeco", "350000", 750);

        addTripIfNotExists("Hà Nội", "Đà Nẵng", "22/12/2024", "12:00", 20, "Thaco", "360000", 750);
        addTripIfNotExists("Đà Nẵng", "Hà Nội", "22/12/2024", "13:30", 22, "Hanh Cafe", "370000", 750);

        addTripIfNotExists("Hà Nội", "Đà Nẵng", "23/12/2024", "14:00", 20, "Tracomeco", "380000", 750);
        addTripIfNotExists("Đà Nẵng", "Hà Nội", "23/12/2024", "15:30", 22, "Thaco", "390000", 750);

        addTripIfNotExists("Hà Nội", "Đà Nẵng", "24/12/2024", "16:00", 20, "Hanh Cafe", "400000", 750);
        addTripIfNotExists("Đà Nẵng", "Hà Nội", "24/12/2024", "17:30", 22, "Tracomeco", "410000", 750);

        addTripIfNotExists("Hà Nội", "Đà Nẵng", "25/12/2024", "18:00", 20, "Thaco", "420000", 750);
        addTripIfNotExists("Đà Nẵng", "Hà Nội", "25/12/2024", "19:30", 22, "Hanh Cafe", "430000", 750);

        addTripIfNotExists("Hà Nội", "Đà Nẵng", "19/12/2024", "05:30", 20, "Hanh Cafe", "330000", 750);
        addTripIfNotExists("Đà Nẵng", "Hà Nội", "19/12/2024", "06:30", 22, "Tracomeco", "340000", 750);

        addTripIfNotExists("Hà Nội", "Đà Nẵng", "20/12/2024", "07:30", 20, "Thaco", "350000", 750);
        addTripIfNotExists("Đà Nẵng", "Hà Nội", "20/12/2024", "08:30", 22, "Hanh Cafe", "360000", 750);

        addTripIfNotExists("Hà Nội", "Đà Nẵng", "21/12/2024", "09:00", 20, "Tracomeco", "370000", 750);
        addTripIfNotExists("Đà Nẵng", "Hà Nội", "21/12/2024", "10:00", 22, "Thaco", "380000", 750);

        addTripIfNotExists("Hà Nội", "Đà Nẵng", "22/12/2024", "11:00", 20, "Hanh Cafe", "390000", 750);
        addTripIfNotExists("Đà Nẵng", "Hà Nội", "22/12/2024", "12:00", 22, "Tracomeco", "400000", 750);

        addTripIfNotExists("Hà Nội", "Đà Nẵng", "23/12/2024", "13:00", 20, "Thaco", "410000", 750);
        addTripIfNotExists("Đà Nẵng", "Hà Nội", "23/12/2024", "14:00", 22, "Hanh Cafe", "420000", 750);

        addTripIfNotExists("Hà Nội", "Đà Nẵng", "24/12/2024", "15:00", 20, "Tracomeco", "430000", 750);
        addTripIfNotExists("Đà Nẵng", "Hà Nội", "24/12/2024", "16:00", 22, "Thaco", "440000", 750);

        addTripIfNotExists("Hà Nội", "Đà Nẵng", "25/12/2024", "17:00", 20, "Hanh Cafe", "450000", 750);
        addTripIfNotExists("Đà Nẵng", "Hà Nội", "25/12/2024", "18:00", 22, "Tracomeco", "460000", 750);

        addTripIfNotExists("TP Hồ Chí Minh", "Đà Nẵng", "19/12/2024", "06:00", 20, "Tracomeco", "420000", 1000);
        addTripIfNotExists("Đà Nẵng", "TP Hồ Chí Minh", "19/12/2024", "07:00", 22, "Hanh Cafe", "430000", 1000);

        addTripIfNotExists("TP Hồ Chí Minh", "Đà Nẵng", "20/12/2024", "08:00", 20, "Thaco", "440000", 1000);
        addTripIfNotExists("Đà Nẵng", "TP Hồ Chí Minh", "20/12/2024", "09:00", 22, "Tracomeco", "450000", 1000);

        addTripIfNotExists("TP Hồ Chí Minh", "Đà Nẵng", "21/12/2024", "10:00", 20, "Hanh Cafe", "460000", 1000);
        addTripIfNotExists("Đà Nẵng", "TP Hồ Chí Minh", "21/12/2024", "11:00", 22, "Thaco", "470000", 1000);

        addTripIfNotExists("TP Hồ Chí Minh", "Đà Nẵng", "22/12/2024", "12:00", 20, "Tracomeco", "480000", 1000);
        addTripIfNotExists("Đà Nẵng", "TP Hồ Chí Minh", "22/12/2024", "13:00", 22, "Hanh Cafe", "490000", 1000);

        addTripIfNotExists("TP Hồ Chí Minh", "Đà Nẵng", "23/12/2024", "14:00", 20, "Thaco", "500000", 1000);
        addTripIfNotExists("Đà Nẵng", "TP Hồ Chí Minh", "23/12/2024", "15:00", 22, "Tracomeco", "510000", 1000);

        addTripIfNotExists("TP Hồ Chí Minh", "Đà Nẵng", "24/12/2024", "16:00", 20, "Hanh Cafe", "520000", 1000);
        addTripIfNotExists("Đà Nẵng", "TP Hồ Chí Minh", "24/12/2024", "17:00", 22, "Thaco", "530000", 1000);

        addTripIfNotExists("TP Hồ Chí Minh", "Đà Nẵng", "25/12/2024", "18:00", 20, "Tracomeco", "540000", 1000);
        addTripIfNotExists("Đà Nẵng", "TP Hồ Chí Minh", "25/12/2024", "19:00", 22, "Hanh Cafe", "550000", 1000);

        addTripIfNotExists("TP Hồ Chí Minh", "Đà Nẵng", "19/12/2024", "06:00", 20, "Tracomeco", "420000", 1000);
        addTripIfNotExists("Đà Nẵng", "TP Hồ Chí Minh", "19/12/2024", "07:30", 22, "Hanh Cafe", "430000", 1000);

        addTripIfNotExists("TP Hồ Chí Minh", "Đà Nẵng", "20/12/2024", "08:00", 20, "Thaco", "440000", 1000);
        addTripIfNotExists("Đà Nẵng", "TP Hồ Chí Minh", "20/12/2024", "09:30", 22, "Tracomeco", "450000", 1000);

        addTripIfNotExists("TP Hồ Chí Minh", "Đà Nẵng", "21/12/2024", "10:00", 20, "Hanh Cafe", "460000", 1000);
        addTripIfNotExists("Đà Nẵng", "TP Hồ Chí Minh", "21/12/2024", "11:30", 22, "Thaco", "470000", 1000);

        addTripIfNotExists("TP Hồ Chí Minh", "Đà Nẵng", "22/12/2024", "12:00", 20, "Tracomeco", "480000", 1000);
        addTripIfNotExists("Đà Nẵng", "TP Hồ Chí Minh", "22/12/2024", "13:30", 22, "Hanh Cafe", "490000", 1000);

        addTripIfNotExists("TP Hồ Chí Minh", "Đà Nẵng", "23/12/2024", "14:00", 20, "Thaco", "500000", 1000);
        addTripIfNotExists("Đà Nẵng", "TP Hồ Chí Minh", "23/12/2024", "15:30", 22, "Tracomeco", "510000", 1000);

        addTripIfNotExists("TP Hồ Chí Minh", "Đà Nẵng", "24/12/2024", "16:00", 20, "Hanh Cafe", "520000", 1000);
        addTripIfNotExists("Đà Nẵng", "TP Hồ Chí Minh", "24/12/2024", "17:30", 22, "Thaco", "530000", 1000);

        addTripIfNotExists("TP Hồ Chí Minh", "Đà Nẵng", "25/12/2024", "18:00", 20, "Tracomeco", "540000", 1000);
        addTripIfNotExists("Đà Nẵng", "TP Hồ Chí Minh", "25/12/2024", "19:30", 22, "Hanh Cafe", "550000", 1000);

        addTripIfNotExists("Bắc Giang", "Hà Tĩnh", "19/12/2024", "06:00", 20, "Thaco", "330000", 1100);
        addTripIfNotExists("Hà Tĩnh", "Bắc Giang", "19/12/2024", "07:30", 22, "Tracomeco", "340000", 1100);

        addTripIfNotExists("Nam Định", "Lai Châu", "20/12/2024", "08:00", 20, "Hanh Cafe", "340000", 1250);
        addTripIfNotExists("Lai Châu", "Nam Định", "20/12/2024", "09:30", 22, "Thaco", "350000", 1250);

        addTripIfNotExists("Quảng Trị", "Đắk Lắk", "21/12/2024", "10:00", 20, "Tracomeco", "330000", 1200);
        addTripIfNotExists("Đắk Lắk", "Quảng Trị", "21/12/2024", "11:30", 22, "Hanh Cafe", "340000", 1200);

        addTripIfNotExists("Hải Dương", "Bình Phước", "22/12/2024", "12:00", 20, "Thaco", "330000", 1050);
        addTripIfNotExists("Bình Phước", "Hải Dương", "22/12/2024", "13:30", 22, "Tracomeco", "340000", 1050);

        addTripIfNotExists("Hà Giang", "Hồ Chí Minh", "23/12/2024", "14:00", 20, "Hanh Cafe", "350000", 1400);
        addTripIfNotExists("Hồ Chí Minh", "Hà Giang", "23/12/2024", "15:30", 22, "Thaco", "360000", 1400);

        addTripIfNotExists("Cao Bằng", "Phú Yên", "24/12/2024", "16:00", 20, "Tracomeco", "320000", 1100);
        addTripIfNotExists("Phú Yên", "Cao Bằng", "24/12/2024", "17:30", 22, "Hanh Cafe", "330000", 1100);

        addTripIfNotExists("Long An", "Quảng Bình", "25/12/2024", "18:00", 20, "Thaco", "330000", 1200);
        addTripIfNotExists("Quảng Bình", "Long An", "25/12/2024", "19:30", 22, "Tracomeco", "340000", 1200);

        addTripIfNotExists("Lâm Đồng", "Hà Nội", "19/12/2024", "06:00", 20, "Hanh Cafe", "350000", 1300);
        addTripIfNotExists("Hà Nội", "Lâm Đồng", "19/12/2024", "07:30", 22, "Thaco", "360000", 1300);

        addTripIfNotExists("Hà Nam", "Quảng Trị", "20/12/2024", "08:00", 20, "Tracomeco", "330000", 1100);
        addTripIfNotExists("Quảng Trị", "Hà Nam", "20/12/2024", "09:30", 22, "Hanh Cafe", "340000", 1100);

        addTripIfNotExists("Cần Thơ", "Vĩnh Phúc", "21/12/2024", "10:00", 20, "Thaco", "320000", 900);
        addTripIfNotExists("Vĩnh Phúc", "Cần Thơ", "21/12/2024", "11:30", 22, "Tracomeco", "330000", 900);

        addTripIfNotExists("Đà Nẵng", "Bắc Kạn", "22/12/2024", "12:00", 20, "Hanh Cafe", "340000", 1250);
        addTripIfNotExists("Bắc Kạn", "Đà Nẵng", "22/12/2024", "13:30", 22, "Thaco", "350000", 1250);

        addTripIfNotExists("Bình Thuận", "Vĩnh Long", "23/12/2024", "14:00", 20, "Tracomeco", "310000", 850);
        addTripIfNotExists("Vĩnh Long", "Bình Thuận", "23/12/2024", "15:30", 22, "Hanh Cafe", "320000", 850);

        addTripIfNotExists("Phú Thọ", "Sóc Trăng", "24/12/2024", "16:00", 20, "Thaco", "300000", 800);
        addTripIfNotExists("Sóc Trăng", "Phú Thọ", "24/12/2024", "17:30", 22, "Tracomeco", "310000", 800);

        addTripIfNotExists("Thanh Hóa", "Nghệ An", "19/12/2024", "06:30", 20, "Thaco", "300000", 550);
        addTripIfNotExists("Nghệ An", "Thanh Hóa", "19/12/2024", "08:00", 22, "Tracomeco", "310000", 550);

        addTripIfNotExists("Hà Tĩnh", "Quảng Bình", "20/12/2024", "07:00", 20, "Hanh Cafe", "320000", 600);
        addTripIfNotExists("Quảng Bình", "Hà Tĩnh", "20/12/2024", "08:30", 22, "Thaco", "330000", 600);

        addTripIfNotExists("Quảng Ngãi", "Phú Yên", "21/12/2024", "09:00", 20, "Tracomeco", "350000", 750);
        addTripIfNotExists("Phú Yên", "Quảng Ngãi", "21/12/2024", "10:30", 22, "Hanh Cafe", "360000", 750);

        addTripIfNotExists("Gia Lai", "Bình Định", "22/12/2024", "11:00", 20, "Thaco", "340000", 650);
        addTripIfNotExists("Bình Định", "Gia Lai", "22/12/2024", "12:30", 22, "Tracomeco", "350000", 650);

        addTripIfNotExists("Ninh Bình", "Sơn La", "23/12/2024", "13:00", 20, "Hanh Cafe", "310000", 700);
        addTripIfNotExists("Sơn La", "Ninh Bình", "23/12/2024", "14:30", 22, "Thaco", "320000", 700);

        addTripIfNotExists("Cao Bằng", "Hòa Bình", "24/12/2024", "15:00", 20, "Tracomeco", "330000", 680);
        addTripIfNotExists("Hòa Bình", "Cao Bằng", "24/12/2024", "16:30", 22, "Hanh Cafe", "340000", 680);

        addTripIfNotExists("Quảng Trị", "Bắc Kạn", "25/12/2024", "17:00", 20, "Thaco", "310000", 700);
        addTripIfNotExists("Bắc Kạn", "Quảng Trị", "25/12/2024", "18:30", 22, "Tracomeco", "320000", 700);

        addTripIfNotExists("Khánh Hòa", "Quảng Ninh", "19/12/2024", "06:00", 20, "Hanh Cafe", "330000", 800);
        addTripIfNotExists("Quảng Ninh", "Khánh Hòa", "19/12/2024", "07:30", 22, "Thaco", "340000", 800);

        addTripIfNotExists("Lào Cai", "Hà Nam", "20/12/2024", "08:00", 20, "Tracomeco", "320000", 750);
        addTripIfNotExists("Hà Nam", "Lào Cai", "20/12/2024", "09:30", 22, "Hanh Cafe", "330000", 750);

        addTripIfNotExists("Bình Dương", "Tây Ninh", "21/12/2024", "10:00", 20, "Thaco", "310000", 600);
        addTripIfNotExists("Tây Ninh", "Bình Dương", "21/12/2024", "11:30", 22, "Tracomeco", "320000", 600);

        addTripIfNotExists("An Giang", "Bạc Liêu", "22/12/2024", "12:00", 20, "Hanh Cafe", "300000", 500);
        addTripIfNotExists("Bạc Liêu", "An Giang", "22/12/2024", "13:30", 22, "Thaco", "310000", 500);

        addTripIfNotExists("Hải Phòng", "Vĩnh Long", "23/12/2024", "14:00", 20, "Tracomeco", "320000", 800);
        addTripIfNotExists("Vĩnh Long", "Hải Phòng", "23/12/2024", "15:30", 22, "Hanh Cafe", "330000", 800);

        addTripIfNotExists("Quảng Nam", "Quảng Ngãi", "24/12/2024", "16:00", 20, "Thaco", "310000", 700);
        addTripIfNotExists("Quảng Ngãi", "Quảng Nam", "24/12/2024", "17:30", 22, "Tracomeco", "320000", 700);

        addTripIfNotExists("Vĩnh Phúc", "Cà Mau", "25/12/2024", "18:00", 20, "Hanh Cafe", "330000", 850);
        addTripIfNotExists("Cà Mau", "Vĩnh Phúc", "25/12/2024", "19:30", 22, "Thaco", "340000", 850);

        addTripIfNotExists("Vĩnh Long", "Nghệ An", "19/12/2024", "06:00", 20, "Tracomeco", "340000", 900);
        addTripIfNotExists("Nghệ An", "Vĩnh Long", "19/12/2024", "07:30", 22, "Hanh Cafe", "350000", 900);

        addTripIfNotExists("Hà Giang", "Bình Định", "20/12/2024", "08:30", 20, "Thaco", "360000", 850);
        addTripIfNotExists("Bình Định", "Hà Giang", "20/12/2024", "10:00", 22, "Tracomeco", "370000", 850);

        addTripIfNotExists("Lâm Đồng", "Quảng Trị", "21/12/2024", "09:30", 20, "Hanh Cafe", "350000", 1000);
        addTripIfNotExists("Quảng Trị", "Lâm Đồng", "21/12/2024", "11:00", 22, "Thaco", "360000", 1000);

        addTripIfNotExists("Thái Bình", "Vĩnh Phúc", "22/12/2024", "10:00", 20, "Tracomeco", "320000", 950);
        addTripIfNotExists("Vĩnh Phúc", "Thái Bình", "22/12/2024", "11:30", 22, "Hanh Cafe", "330000", 950);

        addTripIfNotExists("Tuyên Quang", "Hà Nam", "23/12/2024", "12:00", 20, "Thaco", "330000", 800);
        addTripIfNotExists("Hà Nam", "Tuyên Quang", "23/12/2024", "13:30", 22, "Tracomeco", "340000", 800);

        addTripIfNotExists("Bắc Giang", "Quảng Ngãi", "24/12/2024", "14:00", 20, "Hanh Cafe", "340000", 700);
        addTripIfNotExists("Quảng Ngãi", "Bắc Giang", "24/12/2024", "15:30", 22, "Thaco", "350000", 700);

        addTripIfNotExists("Vĩnh Long", "Hà Tĩnh", "25/12/2024", "16:00", 20, "Tracomeco", "320000", 850);
        addTripIfNotExists("Hà Tĩnh", "Vĩnh Long", "25/12/2024", "17:30", 22, "Hanh Cafe", "330000", 850);

        addTripIfNotExists("Quảng Bình", "Cần Thơ", "19/12/2024", "06:30", 20, "Thaco", "340000", 750);
        addTripIfNotExists("Cần Thơ", "Quảng Bình", "19/12/2024", "08:00", 22, "Tracomeco", "350000", 750);

        addTripIfNotExists("Vĩnh Phúc", "Lào Cai", "20/12/2024", "07:30", 20, "Hanh Cafe", "320000", 800);
        addTripIfNotExists("Lào Cai", "Vĩnh Phúc", "20/12/2024", "09:00", 22, "Thaco", "330000", 800);

        addTripIfNotExists("Sơn La", "Hải Dương", "21/12/2024", "08:00", 20, "Tracomeco", "310000", 850);
        addTripIfNotExists("Hải Dương", "Sơn La", "21/12/2024", "09:30", 22, "Hanh Cafe", "320000", 850);

        addTripIfNotExists("Khánh Hòa", "Quảng Trị", "22/12/2024", "09:00", 20, "Thaco", "330000", 900);
        addTripIfNotExists("Quảng Trị", "Khánh Hòa", "22/12/2024", "10:30", 22, "Tracomeco", "340000", 900);

        addTripIfNotExists("Vĩnh Long", "Phú Thọ", "23/12/2024", "10:00", 20, "Hanh Cafe", "320000", 950);
        addTripIfNotExists("Phú Thọ", "Vĩnh Long", "23/12/2024", "11:30", 22, "Thaco", "330000", 950);

        addTripIfNotExists("Bắc Kạn", "Lâm Đồng", "24/12/2024", "12:30", 20, "Tracomeco", "330000", 900);
        addTripIfNotExists("Lâm Đồng", "Bắc Kạn", "24/12/2024", "14:00", 22, "Hanh Cafe", "340000", 900);

        addTripIfNotExists("Hà Nội", "Vĩnh Long", "25/12/2024", "15:00", 20, "Thaco", "350000", 1000);
        addTripIfNotExists("Vĩnh Long", "Hà Nội", "25/12/2024", "16:30", 22, "Tracomeco", "360000", 1000);

        addTripIfNotExists("Hà Tĩnh", "Quảng Ngãi", "19/12/2024", "06:30", 20, "Tracomeco", "320000", 1000);
        addTripIfNotExists("Quảng Ngãi", "Hà Tĩnh", "19/12/2024", "08:00", 22, "Hanh Cafe", "330000", 1000);

        addTripIfNotExists("Đắk Lắk", "Nghệ An", "20/12/2024", "07:30", 20, "Thaco", "330000", 1100);
        addTripIfNotExists("Nghệ An", "Đắk Lắk", "20/12/2024", "09:00", 22, "Tracomeco", "340000", 1100);

        addTripIfNotExists("Quảng Nam", "Bắc Giang", "21/12/2024", "08:00", 20, "Hanh Cafe", "340000", 950);
        addTripIfNotExists("Bắc Giang", "Quảng Nam", "21/12/2024", "09:30", 22, "Thaco", "350000", 950);

        addTripIfNotExists("Lai Châu", "Hải Dương", "22/12/2024", "10:00", 20, "Tracomeco", "330000", 900);
        addTripIfNotExists("Hải Dương", "Lai Châu", "22/12/2024", "11:30", 22, "Hanh Cafe", "340000", 900);

        addTripIfNotExists("Phú Yên", "Sơn La", "23/12/2024", "12:30", 20, "Thaco", "350000", 950);
        addTripIfNotExists("Sơn La", "Phú Yên", "23/12/2024", "14:00", 22, "Tracomeco", "360000", 950);

        addTripIfNotExists("Cao Bằng", "Quảng Trị", "24/12/2024", "08:30", 20, "Hanh Cafe", "330000", 800);
        addTripIfNotExists("Quảng Trị", "Cao Bằng", "24/12/2024", "10:00", 22, "Thaco", "340000", 800);

        addTripIfNotExists("Bình Phước", "Vĩnh Phúc", "25/12/2024", "09:00", 20, "Tracomeco", "320000", 950);
        addTripIfNotExists("Vĩnh Phúc", "Bình Phước", "25/12/2024", "10:30", 22, "Hanh Cafe", "330000", 950);

        addTripIfNotExists("Thái Nguyên", "Hồ Chí Minh", "19/12/2024", "10:00", 20, "Thaco", "360000", 1200);
        addTripIfNotExists("Hồ Chí Minh", "Thái Nguyên", "19/12/2024", "11:30", 22, "Tracomeco", "370000", 1200);

        addTripIfNotExists("Tây Ninh", "Hà Giang", "20/12/2024", "11:30", 20, "Hanh Cafe", "350000", 1100);
        addTripIfNotExists("Hà Giang", "Tây Ninh", "20/12/2024", "13:00", 22, "Thaco", "360000", 1100);

        addTripIfNotExists("Lâm Đồng", "Quảng Bình", "21/12/2024", "08:30", 20, "Tracomeco", "330000", 1000);
        addTripIfNotExists("Quảng Bình", "Lâm Đồng", "21/12/2024", "10:00", 22, "Hanh Cafe", "340000", 1000);

        addTripIfNotExists("Vĩnh Long", "Bắc Kạn", "22/12/2024", "09:30", 20, "Thaco", "320000", 850);
        addTripIfNotExists("Bắc Kạn", "Vĩnh Long", "22/12/2024", "11:00", 22, "Tracomeco", "330000", 850);

        addTripIfNotExists("Cần Thơ", "Nam Định", "23/12/2024", "07:00", 20, "Hanh Cafe", "310000", 950);
        addTripIfNotExists("Nam Định", "Cần Thơ", "23/12/2024", "08:30", 22, "Thaco", "320000", 950);

        addTripIfNotExists("Quảng Nam", "Vĩnh Phúc", "24/12/2024", "10:00", 20, "Tracomeco", "330000", 800);
        addTripIfNotExists("Vĩnh Phúc", "Quảng Nam", "24/12/2024", "11:30", 22, "Hanh Cafe", "340000", 800);

        addTripIfNotExists("Quảng Ngãi", "Thái Bình", "25/12/2024", "09:00", 20, "Thaco", "340000", 1000);
        addTripIfNotExists("Thái Bình", "Quảng Ngãi", "25/12/2024", "10:30", 22, "Tracomeco", "350000", 1000);

        addTripIfNotExists("Bắc Giang", "Hà Tĩnh", "19/12/2024", "06:30", 20, "Tracomeco", "320000", 1050);
        addTripIfNotExists("Hà Tĩnh", "Bắc Giang", "19/12/2024", "08:00", 22, "Hanh Cafe", "330000", 1050);

        addTripIfNotExists("Đắk Lắk", "Nghệ An", "20/12/2024", "07:30", 20, "Thaco", "330000", 1150);
        addTripIfNotExists("Nghệ An", "Đắk Lắk", "20/12/2024", "09:00", 22, "Tracomeco", "340000", 1150);

        addTripIfNotExists("Quảng Nam", "Bắc Giang", "21/12/2024", "08:00", 20, "Hanh Cafe", "340000", 950);
        addTripIfNotExists("Bắc Giang", "Quảng Nam", "21/12/2024", "09:30", 22, "Thaco", "350000", 950);

        addTripIfNotExists("Lai Châu", "Hải Dương", "22/12/2024", "10:00", 20, "Tracomeco", "330000", 900);
        addTripIfNotExists("Hải Dương", "Lai Châu", "22/12/2024", "11:30", 22, "Hanh Cafe", "340000", 900);

        addTripIfNotExists("Phú Yên", "Sơn La", "23/12/2024", "12:30", 20, "Thaco", "350000", 950);
        addTripIfNotExists("Sơn La", "Phú Yên", "23/12/2024", "14:00", 22, "Tracomeco", "360000", 950);

        addTripIfNotExists("Cao Bằng", "Quảng Trị", "24/12/2024", "08:30", 20, "Hanh Cafe", "330000", 800);
        addTripIfNotExists("Quảng Trị", "Cao Bằng", "24/12/2024", "10:00", 22, "Thaco", "340000", 800);

        addTripIfNotExists("Bình Phước", "Vĩnh Phúc", "25/12/2024", "09:00", 20, "Tracomeco", "320000", 950);
        addTripIfNotExists("Vĩnh Phúc", "Bình Phước", "25/12/2024", "10:30", 22, "Hanh Cafe", "330000", 950);

        addTripIfNotExists("Thái Nguyên", "Hồ Chí Minh", "19/12/2024", "10:00", 20, "Thaco", "360000", 1200);
        addTripIfNotExists("Hồ Chí Minh", "Thái Nguyên", "19/12/2024", "11:30", 22, "Tracomeco", "370000", 1200);

        addTripIfNotExists("Tây Ninh", "Hà Giang", "20/12/2024", "11:30", 20, "Hanh Cafe", "350000", 1100);
        addTripIfNotExists("Hà Giang", "Tây Ninh", "20/12/2024", "13:00", 22, "Thaco", "360000", 1100);

        addTripIfNotExists("Lâm Đồng", "Quảng Bình", "21/12/2024", "08:30", 20, "Tracomeco", "330000", 1000);
        addTripIfNotExists("Quảng Bình", "Lâm Đồng", "21/12/2024", "10:00", 22, "Hanh Cafe", "340000", 1000);

        addTripIfNotExists("Vĩnh Long", "Bắc Kạn", "22/12/2024", "09:30", 20, "Thaco", "320000", 850);
        addTripIfNotExists("Bắc Kạn", "Vĩnh Long", "22/12/2024", "11:00", 22, "Tracomeco", "330000", 850);

        addTripIfNotExists("Cần Thơ", "Nam Định", "23/12/2024", "07:00", 20, "Hanh Cafe", "310000", 950);
        addTripIfNotExists("Nam Định", "Cần Thơ", "23/12/2024", "08:30", 22, "Thaco", "320000", 950);

        addTripIfNotExists("Quảng Nam", "Vĩnh Phúc", "24/12/2024", "10:00", 20, "Tracomeco", "330000", 800);
        addTripIfNotExists("Vĩnh Phúc", "Quảng Nam", "24/12/2024", "11:30", 22, "Hanh Cafe", "340000", 800);

        addTripIfNotExists("Quảng Ngãi", "Thái Bình", "25/12/2024", "09:00", 20, "Thaco", "340000", 1000);
        addTripIfNotExists("Thái Bình", "Quảng Ngãi", "25/12/2024", "10:30", 22, "Tracomeco", "350000", 1000);

        addTripIfNotExists("Thanh Hóa", "Khánh Hòa", "19/12/2024", "06:00", 20, "Tracomeco", "330000", 1100);
        addTripIfNotExists("Khánh Hòa", "Thanh Hóa", "19/12/2024", "07:30", 22, "Hanh Cafe", "340000", 1100);

        addTripIfNotExists("Quảng Bình", "Nghệ An", "20/12/2024", "08:00", 20, "Thaco", "320000", 1200);
        addTripIfNotExists("Nghệ An", "Quảng Bình", "20/12/2024", "09:30", 22, "Tracomeco", "330000", 1200);

        addTripIfNotExists("Bình Định", "Vĩnh Long", "21/12/2024", "09:00", 20, "Hanh Cafe", "310000", 1000);
        addTripIfNotExists("Vĩnh Long", "Bình Định", "21/12/2024", "10:30", 22, "Thaco", "320000", 1000);

        addTripIfNotExists("Quảng Ngãi", "Long An", "22/12/2024", "10:00", 20, "Tracomeco", "330000", 1150);
        addTripIfNotExists("Long An", "Quảng Ngãi", "22/12/2024", "11:30", 22, "Hanh Cafe", "340000", 1150);

        addTripIfNotExists("Sơn La", "Đắk Nông", "23/12/2024", "08:00", 20, "Thaco", "340000", 1200);
        addTripIfNotExists("Đắk Nông", "Sơn La", "23/12/2024", "09:30", 22, "Tracomeco", "350000", 1200);

        addTripIfNotExists("Hải Phòng", "Hà Nam", "24/12/2024", "09:00", 20, "Hanh Cafe", "330000", 900);
        addTripIfNotExists("Hà Nam", "Hải Phòng", "24/12/2024", "10:30", 22, "Thaco", "340000", 900);

        addTripIfNotExists("Quảng Trị", "Hồ Chí Minh", "25/12/2024", "08:00", 20, "Tracomeco", "350000", 1300);
        addTripIfNotExists("Hồ Chí Minh", "Quảng Trị", "25/12/2024", "09:30", 22, "Hanh Cafe", "360000", 1300);

        addTripIfNotExists("Vĩnh Long", "Bắc Giang", "19/12/2024", "08:00", 20, "Thaco", "320000", 1000);
        addTripIfNotExists("Bắc Giang", "Vĩnh Long", "19/12/2024", "09:30", 22, "Tracomeco", "330000", 1000);

        addTripIfNotExists("Lạng Sơn", "Phú Yên", "20/12/2024", "09:00", 20, "Hanh Cafe", "310000", 1100);
        addTripIfNotExists("Phú Yên", "Lạng Sơn", "20/12/2024", "10:30", 22, "Thaco", "320000", 1100);

        addTripIfNotExists("Lai Châu", "Cần Thơ", "21/12/2024", "08:30", 20, "Tracomeco", "330000", 1150);
        addTripIfNotExists("Cần Thơ", "Lai Châu", "21/12/2024", "10:00", 22, "Hanh Cafe", "340000", 1150);

        addTripIfNotExists("Hà Giang", "Bình Thuận", "22/12/2024", "09:00", 20, "Thaco", "340000", 1200);
        addTripIfNotExists("Bình Thuận", "Hà Giang", "22/12/2024", "10:30", 22, "Tracomeco", "350000", 1200);

        addTripIfNotExists("Quảng Ngãi", "Tây Ninh", "23/12/2024", "08:00", 20, "Hanh Cafe", "350000", 900);
        addTripIfNotExists("Tây Ninh", "Quảng Ngãi", "23/12/2024", "09:30", 22, "Thaco", "360000", 900);

        addTripIfNotExists("Sơn La", "Hà Tĩnh", "24/12/2024", "09:00", 20, "Tracomeco", "330000", 1100);
        addTripIfNotExists("Hà Tĩnh", "Sơn La", "24/12/2024", "10:30", 22, "Hanh Cafe", "340000", 1100);

        addTripIfNotExists("Lâm Đồng", "Hải Phòng", "25/12/2024", "08:30", 20, "Thaco", "350000", 1250);
        addTripIfNotExists("Hải Phòng", "Lâm Đồng", "25/12/2024", "10:00", 22, "Tracomeco", "360000", 1250);

        addTripIfNotExists("Hà Nội", "Hải Phòng", "19/12/2024", "08:00", 20, "Hanh Cafe", "300000", 250);
        addTripIfNotExists("Hải Phòng", "Hà Nội", "19/12/2024", "10:00", 22, "Thaco", "310000", 250);

        addTripIfNotExists("Thanh Hóa", "Bình Định", "20/12/2024", "08:00", 20, "Tracomeco", "320000", 600);
        addTripIfNotExists("Bình Định", "Thanh Hóa", "20/12/2024", "10:00", 22, "Hanh Cafe", "330000", 600);

        addTripIfNotExists("Quảng Ngãi", "Lâm Đồng", "21/12/2024", "08:00", 20, "Thaco", "350000", 950);
        addTripIfNotExists("Lâm Đồng", "Quảng Ngãi", "21/12/2024", "10:00", 22, "Tracomeco", "360000", 950);

        addTripIfNotExists("Cà Mau", "Bắc Giang", "22/12/2024", "08:00", 20, "Hanh Cafe", "330000", 1000);
        addTripIfNotExists("Bắc Giang", "Cà Mau", "22/12/2024", "10:00", 22, "Thaco", "340000", 1000);

        addTripIfNotExists("Quảng Ninh", "Vĩnh Long", "23/12/2024", "08:00", 20, "Tracomeco", "310000", 850);
        addTripIfNotExists("Vĩnh Long", "Quảng Ninh", "23/12/2024", "10:00", 22, "Hanh Cafe", "320000", 850);

        addTripIfNotExists("Nghệ An", "Bình Dương", "24/12/2024", "08:00", 20, "Thaco", "340000", 1100);
        addTripIfNotExists("Bình Dương", "Nghệ An", "24/12/2024", "10:00", 22, "Tracomeco", "350000", 1100);

        addTripIfNotExists("Đắk Nông", "Quảng Trị", "25/12/2024", "08:00", 20, "Hanh Cafe", "330000", 1200);
        addTripIfNotExists("Quảng Trị", "Đắk Nông", "25/12/2024", "10:00", 22, "Thaco", "340000", 1200);

        addTripIfNotExists("Hà Nội", "Quảng Nam", "19/12/2024", "08:30", 20, "Hanh Cafe", "310000", 1200);
        addTripIfNotExists("Quảng Nam", "Hà Nội", "19/12/2024", "10:30", 22, "Thaco", "320000", 1200);

        addTripIfNotExists("Lai Châu", "Hồ Chí Minh", "20/12/2024", "08:30", 20, "Tracomeco", "350000", 1500);
        addTripIfNotExists("Hồ Chí Minh", "Lai Châu", "20/12/2024", "10:30", 22, "Hanh Cafe", "360000", 1500);

        addTripIfNotExists("Sơn La", "Quảng Ngãi", "21/12/2024", "08:30", 20, "Thaco", "340000", 1300);
        addTripIfNotExists("Quảng Ngãi", "Sơn La", "21/12/2024", "10:30", 22, "Tracomeco", "350000", 1300);

        addTripIfNotExists("Bắc Ninh", "Cần Thơ", "22/12/2024", "08:30", 20, "Hanh Cafe", "330000", 1100);
        addTripIfNotExists("Cần Thơ", "Bắc Ninh", "22/12/2024", "10:30", 22, "Thaco", "340000", 1100);

        addTripIfNotExists("Khánh Hòa", "Vĩnh Phúc", "23/12/2024", "08:30", 20, "Tracomeco", "320000", 950);
        addTripIfNotExists("Vĩnh Phúc", "Khánh Hòa", "23/12/2024", "10:30", 22, "Hanh Cafe", "330000", 950);

        addTripIfNotExists("An Giang", "Ninh Bình", "24/12/2024", "08:30", 20, "Thaco", "330000", 1150);
        addTripIfNotExists("Ninh Bình", "An Giang", "24/12/2024", "10:30", 22, "Tracomeco", "340000", 1150);

        addTripIfNotExists("Bà Rịa - Vũng Tàu", "Thái Nguyên", "25/12/2024", "08:30", 20, "Hanh Cafe", "340000", 1200);
        addTripIfNotExists("Thái Nguyên", "Bà Rịa - Vũng Tàu", "25/12/2024", "10:30", 22, "Thaco", "350000", 1200);

        addTripIfNotExists("Bắc Giang", "Ninh Thuận", "19/12/2024", "08:00", 20, "Tracomeco", "340000", 1150);
        addTripIfNotExists("Ninh Thuận", "Bắc Giang", "19/12/2024", "11:00", 22, "Hanh Cafe", "350000", 1150);

        addTripIfNotExists("Lào Cai", "Vĩnh Phúc", "20/12/2024", "09:30", 20, "Thaco", "330000", 1250);
        addTripIfNotExists("Vĩnh Phúc", "Lào Cai", "20/12/2024", "12:30", 22, "Tracomeco", "340000", 1250);

        addTripIfNotExists("Bình Dương", "Hải Dương", "21/12/2024", "08:00", 20, "Hanh Cafe", "320000", 1100);
        addTripIfNotExists("Hải Dương", "Bình Dương", "21/12/2024", "10:00", 22, "Thaco", "330000", 1100);

        addTripIfNotExists("Thái Nguyên", "Bà Rịa - Vũng Tàu", "22/12/2024", "09:00", 20, "Tracomeco", "330000", 1200);
        addTripIfNotExists("Bà Rịa - Vũng Tàu", "Thái Nguyên", "22/12/2024", "12:00", 22, "Hanh Cafe", "340000", 1200);

        addTripIfNotExists("Quảng Nam", "Phú Thọ", "23/12/2024", "08:30", 20, "Thaco", "310000", 1100);
        addTripIfNotExists("Phú Thọ", "Quảng Nam", "23/12/2024", "11:30", 22, "Tracomeco", "320000", 1100);

        addTripIfNotExists("Bắc Ninh", "Lâm Đồng", "24/12/2024", "08:30", 20, "Hanh Cafe", "340000", 1300);
        addTripIfNotExists("Lâm Đồng", "Bắc Ninh", "24/12/2024", "11:30", 22, "Thaco", "350000", 1300);

        addTripIfNotExists("Hà Nam", "Đắk Lắk", "25/12/2024", "09:30", 20, "Tracomeco", "320000", 1350);
        addTripIfNotExists("Đắk Lắk", "Hà Nam", "25/12/2024", "12:30", 22, "Hanh Cafe", "330000", 1350);

        addTripIfNotExists("Hà Nội", "Lạng Sơn", "19/12/2024", "08:00", 20, "Tracomeco", "300000", 1100);
        addTripIfNotExists("Lạng Sơn", "Hà Nội", "19/12/2024", "11:00", 22, "Hanh Cafe", "310000", 1100);

        addTripIfNotExists("Hải Phòng", "Cà Mau", "20/12/2024", "09:30", 20, "Thaco", "320000", 1400);
        addTripIfNotExists("Cà Mau", "Hải Phòng", "20/12/2024", "12:30", 22, "Tracomeco", "330000", 1400);

        addTripIfNotExists("Thái Bình", "Tây Ninh", "21/12/2024", "10:00", 20, "Hanh Cafe", "310000", 1200);
        addTripIfNotExists("Tây Ninh", "Thái Bình", "21/12/2024", "13:00", 22, "Thaco", "320000", 1200);

        addTripIfNotExists("Kon Tum", "Đà Nẵng", "22/12/2024", "08:00", 20, "Tracomeco", "340000", 1150);
        addTripIfNotExists("Đà Nẵng", "Kon Tum", "22/12/2024", "11:00", 22, "Hanh Cafe", "350000", 1150);

        addTripIfNotExists("Bến Tre", "Cao Bằng", "23/12/2024", "09:00", 20, "Thaco", "330000", 1250);
        addTripIfNotExists("Cao Bằng", "Bến Tre", "23/12/2024", "12:00", 22, "Tracomeco", "340000", 1250);

        addTripIfNotExists("Gia Lai", "Quảng Ngãi", "24/12/2024", "10:30", 20, "Hanh Cafe", "320000", 1300);
        addTripIfNotExists("Quảng Ngãi", "Gia Lai", "24/12/2024", "13:30", 22, "Thaco", "330000", 1300);

        addTripIfNotExists("Quảng Ninh", "Hà Tĩnh", "25/12/2024", "08:30", 20, "Tracomeco", "310000", 1200);
        addTripIfNotExists("Hà Tĩnh", "Quảng Ninh", "25/12/2024", "11:30", 22, "Hanh Cafe", "320000", 1200);

        addTripIfNotExists("Vĩnh Long", "Quảng Ngãi", "19/12/2024", "07:30", 20, "Thaco", "310000", 950);
        addTripIfNotExists("Quảng Ngãi", "Vĩnh Long", "19/12/2024", "10:30", 22, "Tracomeco", "320000", 950);

        addTripIfNotExists("Đà Nẵng", "Vĩnh Phúc", "20/12/2024", "08:30", 20, "Hanh Cafe", "300000", 900);
        addTripIfNotExists("Vĩnh Phúc", "Đà Nẵng", "20/12/2024", "11:30", 22, "Thaco", "310000", 900);

        addTripIfNotExists("Bắc Giang", "Cần Thơ", "21/12/2024", "09:00", 20, "Tracomeco", "320000", 1250);
        addTripIfNotExists("Cần Thơ", "Bắc Giang", "21/12/2024", "12:00", 22, "Hanh Cafe", "330000", 1250);

        addTripIfNotExists("Hải Dương", "Phú Thọ", "22/12/2024", "07:00", 20, "Thaco", "310000", 800);
        addTripIfNotExists("Phú Thọ", "Hải Dương", "22/12/2024", "10:00", 22, "Tracomeco", "320000", 800);

        addTripIfNotExists("Lai Châu", "Bình Thuận", "23/12/2024", "08:30", 20, "Hanh Cafe", "330000", 1100);
        addTripIfNotExists("Bình Thuận", "Lai Châu", "23/12/2024", "11:30", 22, "Thaco", "340000", 1100);

        addTripIfNotExists("Hà Giang", "Kon Tum", "24/12/2024", "09:00", 20, "Tracomeco", "320000", 1150);
        addTripIfNotExists("Kon Tum", "Hà Giang", "24/12/2024", "12:00", 22, "Hanh Cafe", "330000", 1150);

        addTripIfNotExists("Quảng Trị", "Lâm Đồng", "25/12/2024", "08:00", 20, "Thaco", "340000", 1300);
        addTripIfNotExists("Lâm Đồng", "Quảng Trị", "25/12/2024", "11:00", 22, "Tracomeco", "350000", 1300);

        addTripIfNotExists("Bắc Ninh", "Bến Tre", "19/12/2024", "07:30", 20, "Thaco", "320000", 1200);
        addTripIfNotExists("Bến Tre", "Bắc Ninh", "19/12/2024", "10:30", 22, "Tracomeco", "330000", 1200);

        addTripIfNotExists("Thanh Hóa", "Ninh Thuận", "20/12/2024", "08:00", 20, "Hanh Cafe", "310000", 950);
        addTripIfNotExists("Ninh Thuận", "Thanh Hóa", "20/12/2024", "11:00", 22, "Thaco", "320000", 950);

        addTripIfNotExists("Yên Bái", "Gia Lai", "21/12/2024", "09:00", 20, "Tracomeco", "330000", 1250);
        addTripIfNotExists("Gia Lai", "Yên Bái", "21/12/2024", "12:00", 22, "Hanh Cafe", "340000", 1250);

        addTripIfNotExists("Bà Rịa - Vũng Tàu", "Bắc Giang", "22/12/2024", "08:30", 20, "Thaco", "350000", 1150);
        addTripIfNotExists("Bắc Giang", "Bà Rịa - Vũng Tàu", "22/12/2024", "11:30", 22, "Tracomeco", "360000", 1150);

        addTripIfNotExists("Vĩnh Phúc", "Cà Mau", "23/12/2024", "09:00", 20, "Hanh Cafe", "320000", 1200);
        addTripIfNotExists("Cà Mau", "Vĩnh Phúc", "23/12/2024", "12:00", 22, "Thaco", "330000", 1200);

        addTripIfNotExists("Quảng Ngãi", "Ninh Bình", "24/12/2024", "08:30", 20, "Tracomeco", "310000", 1000);
        addTripIfNotExists("Ninh Bình", "Quảng Ngãi", "24/12/2024", "11:30", 22, "Hanh Cafe", "320000", 1000);

        addTripIfNotExists("Quảng Nam", "Sơn La", "25/12/2024", "09:00", 20, "Thaco", "340000", 1100);
        addTripIfNotExists("Sơn La", "Quảng Nam", "25/12/2024", "12:00", 22, "Tracomeco", "350000", 1100);

        addTripIfNotExists("Lào Cai", "Quảng Ngãi", "19/12/2024", "07:45", 20, "Thaco", "330000", 1100);
        addTripIfNotExists("Quảng Ngãi", "Lào Cai", "19/12/2024", "10:45", 22, "Tracomeco", "340000", 1100);

        addTripIfNotExists("Hải Phòng", "Bình Thuận", "20/12/2024", "08:00", 20, "Hanh Cafe", "320000", 1000);
        addTripIfNotExists("Bình Thuận", "Hải Phòng", "20/12/2024", "11:00", 22, "Thaco", "330000", 1000);

        addTripIfNotExists("Hòa Bình", "Hà Tĩnh", "21/12/2024", "09:30", 20, "Tracomeco", "330000", 1150);
        addTripIfNotExists("Hà Tĩnh", "Hòa Bình", "21/12/2024", "12:30", 22, "Hanh Cafe", "340000", 1150);

        addTripIfNotExists("Quảng Trị", "Cà Mau", "22/12/2024", "08:00", 20, "Thaco", "330000", 1250);
        addTripIfNotExists("Cà Mau", "Quảng Trị", "22/12/2024", "11:00", 22, "Tracomeco", "340000", 1250);

        addTripIfNotExists("Lâm Đồng", "Sóc Trăng", "23/12/2024", "09:30", 20, "Hanh Cafe", "310000", 900);
        addTripIfNotExists("Sóc Trăng", "Lâm Đồng", "23/12/2024", "12:30", 22, "Thaco", "320000", 900);

        addTripIfNotExists("Bình Dương", "Bến Tre", "24/12/2024", "08:30", 20, "Tracomeco", "310000", 950);
        addTripIfNotExists("Bến Tre", "Bình Dương", "24/12/2024", "11:30", 22, "Hanh Cafe", "320000", 950);

        addTripIfNotExists("Kiên Giang", "Quảng Nam", "25/12/2024", "09:00", 20, "Thaco", "330000", 1100);
        addTripIfNotExists("Quảng Nam", "Kiên Giang", "25/12/2024", "12:00", 22, "Tracomeco", "340000", 1100);

        addTripIfNotExists("Nghệ An", "Thanh Hóa", "19/12/2024", "10:00", 20, "Thaco", "320000", 950);
        addTripIfNotExists("Thanh Hóa", "Nghệ An", "19/12/2024", "13:00", 22, "Tracomeco", "330000", 950);

        addTripIfNotExists("Bắc Giang", "Hải Dương", "20/12/2024", "08:30", 20, "Hanh Cafe", "310000", 900);
        addTripIfNotExists("Hải Dương", "Bắc Giang", "20/12/2024", "11:30", 22, "Thaco", "320000", 900);

        addTripIfNotExists("Vĩnh Long", "An Giang", "21/12/2024", "09:30", 20, "Tracomeco", "340000", 1100);
        addTripIfNotExists("An Giang", "Vĩnh Long", "21/12/2024", "12:30", 22, "Hanh Cafe", "350000", 1100);

        addTripIfNotExists("Quảng Ninh", "Lạng Sơn", "22/12/2024", "08:00", 20, "Thaco", "330000", 1000);
        addTripIfNotExists("Lạng Sơn", "Quảng Ninh", "22/12/2024", "11:00", 22, "Tracomeco", "340000", 1000);

        addTripIfNotExists("Hà Giang", "Bắc Kạn", "23/12/2024", "08:30", 20, "Hanh Cafe", "320000", 1150);
        addTripIfNotExists("Bắc Kạn", "Hà Giang", "23/12/2024", "11:30", 22, "Thaco", "330000", 1150);

        addTripIfNotExists("Vĩnh Phúc", "Quảng Ngãi", "24/12/2024", "09:00", 20, "Tracomeco", "310000", 900);
        addTripIfNotExists("Quảng Ngãi", "Vĩnh Phúc", "24/12/2024", "12:00", 22, "Hanh Cafe", "320000", 900);

        addTripIfNotExists("Đắk Lắk", "Hà Nội", "25/12/2024", "08:00", 20, "Thaco", "350000", 1250);
        addTripIfNotExists("Hà Nội", "Đắk Lắk", "25/12/2024", "11:00", 22, "Tracomeco", "360000", 1250);

        addTripIfNotExists("Sơn La", "Hòa Bình", "19/12/2024", "10:00", 20, "Hanh Cafe", "300000", 850);
        addTripIfNotExists("Hòa Bình", "Sơn La", "19/12/2024", "13:00", 22, "Thaco", "310000", 850);

        addTripIfNotExists("Quảng Bình", "Lâm Đồng", "20/12/2024", "08:30", 20, "Tracomeco", "330000", 1050);
        addTripIfNotExists("Lâm Đồng", "Quảng Bình", "20/12/2024", "11:30", 22, "Hanh Cafe", "340000", 1050);

        addTripIfNotExists("Vĩnh Long", "Bình Thuận", "21/12/2024", "09:00", 20, "Thaco", "310000", 900);
        addTripIfNotExists("Bình Thuận", "Vĩnh Long", "21/12/2024", "12:00", 22, "Tracomeco", "320000", 900);

        addTripIfNotExists("Gia Lai", "Quảng Trị", "22/12/2024", "08:00", 20, "Hanh Cafe", "350000", 1100);
        addTripIfNotExists("Quảng Trị", "Gia Lai", "22/12/2024", "11:00", 22, "Thaco", "360000", 1100);

        addTripIfNotExists("Khánh Hòa", "Bà Rịa-Vũng Tàu", "23/12/2024", "09:30", 20, "Tracomeco", "340000", 1200);
        addTripIfNotExists("Bà Rịa-Vũng Tàu", "Khánh Hòa", "23/12/2024", "12:30", 22, "Hanh Cafe", "350000", 1200);

        addTripIfNotExists("Tây Ninh", "Bắc Giang", "24/12/2024", "08:30", 20, "Thaco", "330000", 1000);
        addTripIfNotExists("Bắc Giang", "Tây Ninh", "24/12/2024", "11:30", 22, "Tracomeco", "340000", 1000);

        addTripIfNotExists("Phú Yên", "Quảng Nam", "25/12/2024", "09:00", 20, "Hanh Cafe", "320000", 1100);
        addTripIfNotExists("Quảng Nam", "Phú Yên", "25/12/2024", "12:00", 22, "Thaco", "330000", 1100);

        addTripIfNotExists("Quảng Trị", "Hải Dương", "19/12/2024", "10:30", 20, "Thaco", "310000", 1000);
        addTripIfNotExists("Hải Dương", "Quảng Trị", "19/12/2024", "13:30", 22, "Tracomeco", "320000", 1000);

        addTripIfNotExists("Bình Dương", "Nghệ An", "20/12/2024", "08:00", 20, "Hanh Cafe", "330000", 1150);
        addTripIfNotExists("Nghệ An", "Bình Dương", "20/12/2024", "11:00", 22, "Thaco", "340000", 1150);

        addTripIfNotExists("Vĩnh Long", "Bình Phước", "21/12/2024", "09:00", 20, "Tracomeco", "320000", 950);
        addTripIfNotExists("Bình Phước", "Vĩnh Long", "21/12/2024", "12:00", 22, "Hanh Cafe", "330000", 950);

        addTripIfNotExists("Lào Cai", "Cà Mau", "22/12/2024", "08:30", 20, "Thaco", "350000", 1300);
        addTripIfNotExists("Cà Mau", "Lào Cai", "22/12/2024", "11:30", 22, "Tracomeco", "360000", 1300);

        addTripIfNotExists("Thái Bình", "Kon Tum", "23/12/2024", "09:00", 20, "Hanh Cafe", "340000", 1250);
        addTripIfNotExists("Kon Tum", "Thái Bình", "23/12/2024", "12:00", 22, "Thaco", "350000", 1250);

        addTripIfNotExists("Quảng Ninh", "Lâm Đồng", "24/12/2024", "08:00", 20, "Tracomeco", "330000", 1200);
        addTripIfNotExists("Lâm Đồng", "Quảng Ninh", "24/12/2024", "11:00", 22, "Hanh Cafe", "340000", 1200);

        addTripIfNotExists("Bắc Kạn", "Hà Nam", "25/12/2024", "09:00", 20, "Thaco", "320000", 1000);
        addTripIfNotExists("Hà Nam", "Bắc Kạn", "25/12/2024", "12:00", 22, "Tracomeco", "330000", 1000);
    
    }


    private void addTripIfNotExists(String departure, String destination, String departureDate, String departureTime, int ticketsAvailable, String coachBrand, String price, int distance) {
        if (!tripExists(departure, destination, departureDate, departureTime)) {
            myDataBase.addTripInfo(departure, destination, departureTime, departureDate, ticketsAvailable, coachBrand, price, distance);
        }
    }
    private boolean tripExists(String departure, String destination, String departureDate, String departureTime) {
        SQLiteDatabase db = myDataBase.getReadableDatabase();
        String query = "SELECT * FROM " + MyDataBase.tbTripInfo + " WHERE " +
                MyDataBase.tbTripInfo_Departure + " = ? AND " +
                MyDataBase.tbTripInfo_Destination + " = ? AND " +
                MyDataBase.tbTripInfo_DepartureDate + " = ? AND " +
                MyDataBase.tbTripInfo_DepartureTime + " = ? AND " +
                MyDataBase.tbTripInfo_CoachBrand + " = ?";
        Cursor cursor = db.rawQuery(query, new String[]{departure, destination, departureDate, departureTime});
        boolean exists = cursor.getCount() > 0;
        cursor.close();
        return exists;
    }

    private void showDatePickerDialog() {
        final Calendar calendar = Calendar.getInstance();
        int year = calendar.get(Calendar.YEAR);
        int month = calendar.get(Calendar.MONTH);
        int day = calendar.get(Calendar.DAY_OF_MONTH);

        DatePickerDialog datePickerDialog = new DatePickerDialog(MainUIActivity.this, new DatePickerDialog.OnDateSetListener() {
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

        DatePickerDialog datePickerDialog = new DatePickerDialog(MainUIActivity.this, new DatePickerDialog.OnDateSetListener() {
            @Override
            public void onDateSet(DatePicker view, int year, int monthOfYear, int dayOfMonth) {
                String selectedDate = String.format("%02d/%02d/%d", dayOfMonth, monthOfYear + 1, year);
            }
        }, year, month, day);

        datePickerDialog.show();
    }

     */
    @Override
    protected void onResume() {
        super.onResume();
        bnvMainMenu.setOnItemSelectedListener(item -> {
            if(item.getItemId()==R.id.home_page){
                replaceFragment(homePageFragment);
            }
            else if(item.getItemId()==R.id.my_ticket){
                replaceFragment(myTicketFragment);
            }
            else if(item.getItemId()==R.id.notification){
                replaceFragment(new NotificationFragment( ));
            }
            else if(item.getItemId()==R.id.my_account){
                replaceFragment(myAccountFragment);
            }
            return true;
        });
    }
}
