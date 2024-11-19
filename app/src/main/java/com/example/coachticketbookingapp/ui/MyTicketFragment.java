package com.example.coachticketbookingapp.ui;

import android.annotation.SuppressLint;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.example.coachticketbookingapp.R;
import com.example.coachticketbookingapp.Object.Ticket;
import java.util.ArrayList;

public class MyTicketFragment extends Fragment implements TicketAdapter.TicketClickListener{

    ArrayList<Ticket> dsTicket = new ArrayList<>();
    RecyclerView recyclerView;
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
        recyclerView = view_ticket.findViewById(R.id.recycleview_trip_list_found);

        dsTicket.add(new Ticket("123","100002","20/10/2024", "Vung Tau","Sai Gon"));
        dsTicket.add(new Ticket("12386","100002","20/10/2024","Vung Tau","Sai Gon"));
        dsTicket.add(new Ticket("12343","232333","20/10/2024","TP HCM","Sai Gon"));
        dsTicket.add(new Ticket("123343","99999","20/10/2024","Vung Taudd","Sai Gon"));
        dsTicket.add(new Ticket("123343","99999","20/10/2024","Vung Taudd","Sai Gon"));
        dsTicket.add(new Ticket("123343","99999","20/10/2024","Vung Taudd","Sai Gon"));
        dsTicket.add(new Ticket("123343","99999","20/10/2024","Vung Taudd","Sai Gon"));
        dsTicket.add(new Ticket("123343","99999","20/10/2024","Vung Taudd","Sai Gon"));
        dsTicket.add(new Ticket("123343","99999","20/10/2024","Vung Taudd","Sai Gon"));
        dsTicket.add(new Ticket("123343","99999","20/10/2024","Vung Taudd","Sai Gon"));
        dsTicket.add(new Ticket("123343","99999","20/10/2024","Vung Taudd","Sai Gon"));
        dsTicket.add(new Ticket("123343","99999","20/10/2024","Vung Taudd","Sai Gon"));
        dsTicket.add(new Ticket("123343","99999","20/10/2024","Vung Taudd","Sai Gon"));
        dsTicket.add(new Ticket("123343","99999","20/10/2024","Vung Taudd","Sai Gon"));

        LinearLayoutManager layoutManager = new LinearLayoutManager(getContext(), LinearLayoutManager.VERTICAL, false);//Dieu chinh cach hien thi
        recyclerView.setLayoutManager(layoutManager);
        TicketAdapter ticketAdapter = new TicketAdapter(getContext(),dsTicket,this);
        recyclerView.setAdapter(ticketAdapter);
        return view_ticket;
    }

    @Override
    public void onTicketClick(int position) {
        // Xử lý sự kiện nhấn vào item tại vị trí position
        Ticket clickedTicket = dsTicket.get(position);
        // Ví dụ: hiển thị thông báo
        Toast.makeText(getContext(), "Clicked ticket: " + clickedTicket.getLocation1() + " to " + clickedTicket.getLocation2(), Toast.LENGTH_SHORT).show();
    }
}