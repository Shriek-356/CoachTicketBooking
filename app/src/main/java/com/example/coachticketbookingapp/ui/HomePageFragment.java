package com.example.coachticketbookingapp.ui;

import android.content.Context;
import android.os.Bundle;
import com.example.coachticketbookingapp.Object.User;
import androidx.annotation.Nullable;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.Switch;
import android.widget.TextView;

import com.example.coachticketbookingapp.R;

import java.util.ArrayList;

import com.example.coachticketbookingapp.Object.item;

public class HomePageFragment extends Fragment {
    private Switch switchTrip;
    private ConstraintLayout constraintLayout;
    private EditText editText;
    ArrayList<item> dsitem = new ArrayList<>();
    RecyclerView recyclerView;
    private TextView txvHello;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_home_page, container, false);
        switchTrip = view.findViewById(R.id.switch1);
        constraintLayout=view.findViewById(R.id.csLayout3);
        editText = view.findViewById(R.id.editTextText9);
        recyclerView = view.findViewById(R.id.recyclerview);
        txvHello=view.findViewById(R.id.txvHello);
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
            User thisUser = (User) bundle.getSerializable("thisUser");
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


    }

    public int dpToPx(int dp, Context context) {
        float density = context.getResources().getDisplayMetrics().density;
        return Math.round(dp * density); // Làm tròn kết quả
    }
}