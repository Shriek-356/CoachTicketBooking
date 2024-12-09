package com.example.coachticketbookingapp.ui;

import android.annotation.SuppressLint;
import android.os.Bundle;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;

import com.example.coachticketbookingapp.Object.User;
import com.example.coachticketbookingapp.R;
import com.google.android.material.bottomnavigation.BottomNavigationView;

public class MainUIActivity extends AppCompatActivity {

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
        bnvMainMenu = findViewById(R.id.bnvMainMenu);

        //Lay du lieu tu LoginActivity qua;

        presentUser = (User) getIntent().getSerializableExtra("user");

        if (presentUser != null) {
            //Tao bundle de truyen du lieu
            Bundle bundle = new Bundle();
            bundle.putSerializable("user",presentUser);
            homePageFragment.setArguments(bundle);
        }
        replaceFragment(homePageFragment);//Dat mac dinh la homepage
    }
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
