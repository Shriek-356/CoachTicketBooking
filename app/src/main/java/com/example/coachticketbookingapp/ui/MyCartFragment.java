package com.example.coachticketbookingapp.ui;

import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.coachticketbookingapp.Object.TripBookingDetails;
import com.example.coachticketbookingapp.Object.TrippingCart;
import com.example.coachticketbookingapp.Object.User;
import com.example.coachticketbookingapp.R;

import java.util.ArrayList;
import java.util.List;

import DataBase.MyDataBase;

public class MyCartFragment extends Fragment {
    List<TrippingCart> dsTrippingCarts;
    MyDataBase myDataBase;
    User thisUser;
    RecyclerView recyclerView;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view_cart =inflater.inflate(R.layout.fragment_my_cart, container, false);

        dsTrippingCarts = new ArrayList<>();
        myDataBase = new MyDataBase(getContext());
        //NhanObjectUser;
        Bundle bundle = getArguments();
        if(bundle!=null){
            thisUser=(User) bundle.getSerializable("user");
        }
        if(thisUser!=null) {
            dsTrippingCarts=myDataBase.getTrippingCartList(thisUser.getUserID());
        }

        CartAdapter cartAdapter = new CartAdapter(getContext(),dsTrippingCarts,thisUser);
        recyclerView=view_cart.findViewById(R.id.recycleview_cart_list);
        LinearLayoutManager layoutManager = new LinearLayoutManager(getContext(), LinearLayoutManager.VERTICAL, false);
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setAdapter(cartAdapter);

        return view_cart;
    }
}