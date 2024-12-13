package com.example.coachticketbookingapp.ui;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import com.example.coachticketbookingapp.Object.TripBookingDetails;
import com.example.coachticketbookingapp.Object.User;
import com.example.coachticketbookingapp.R;

import java.util.ArrayList;
import java.util.List;

import DataBase.MyDataBase;

public class MyTicketFragment extends Fragment implements TripBookingDetailsAdapter.OnItemClickListener{

    List<TripBookingDetails> dsTicket = new ArrayList<>();
    RecyclerView recyclerView;
    User myTicketFragmentUser ;
    MyDataBase myDataBase;
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public void onResume() {
        super.onResume();
    }

    @SuppressLint("MissingInflatedId")
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view_ticket =inflater.inflate(R.layout.fragment_my_ticket, container, false);
        myDataBase = new MyDataBase(getContext());

        //Nhan object User
        Bundle bundle = getArguments();
        if(bundle!=null){
            myTicketFragmentUser = (User)bundle.getSerializable("user");
        }

        dsTicket = myDataBase.getTripBookingList(myTicketFragmentUser.getUserID());
        TripBookingDetailsAdapter tripBookingDetailsAdapter = new TripBookingDetailsAdapter(getContext(),dsTicket,this);
        recyclerView = view_ticket.findViewById(R.id.recycleview_ticket_list);
        LinearLayoutManager layoutManager = new LinearLayoutManager(getContext(), LinearLayoutManager.VERTICAL, false);//Dieu chinh cach hien thi
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setAdapter(tripBookingDetailsAdapter);
        return view_ticket;
    }

    @Override
    public void onItemClick(TripBookingDetails tripInfo) {

        Intent intentDsTrip = new Intent(getActivity(),MyTicketDetailsActivity.class);
        intentDsTrip.putExtra("tripbookingdetails",tripInfo);
        intentDsTrip.putExtra("tripBookingDetailsUser",myTicketFragmentUser);
        startActivity(intentDsTrip);

    }
}