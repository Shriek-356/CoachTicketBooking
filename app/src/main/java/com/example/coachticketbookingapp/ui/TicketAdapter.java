package com.example.coachticketbookingapp.ui;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.coachticketbookingapp.R;

import java.util.ArrayList;
import com.example.coachticketbookingapp.Object.Ticket;

public class TicketAdapter extends RecyclerView.Adapter<TicketAdapter.TicketViewHolder>  {
    Context context;
    private ArrayList<Ticket> dsTicket = new ArrayList<>();
    private TicketClickListener ticketClickListener;

    public interface TicketClickListener {//Ham de lang nghe su kien khi nguoi dung click vao ve
        void onTicketClick(int position);
    }

    public TicketAdapter(Context context, ArrayList<Ticket> dsTicket, TicketClickListener ticketClickListener) {
        this.context = context;
        this.dsTicket = dsTicket;
        this.ticketClickListener=ticketClickListener;
    }
    @NonNull
    @Override
    public TicketViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.ticket_cardview,parent,false);
        return new TicketViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull TicketViewHolder holder, int position) {
        Ticket ticket= dsTicket.get(position);
        holder.imv.setImageResource(R.drawable.ticketicon);
        holder.location1.setText(ticket.getLocation1());
        holder.location2.setText(ticket.getLocation2());
        holder.price.setText(ticket.getPrice());
        holder.itemView.setOnClickListener(v->{
            if (ticketClickListener != null) {
                ticketClickListener.onTicketClick(position);
            }
        });
    }

    @Override
    public int getItemCount() {
        if(dsTicket != null){
            return dsTicket.size();
        }
        return 0;
    }
    public class TicketViewHolder extends RecyclerView.ViewHolder{
        ImageView imv;
        TextView location1;
        TextView location2;
        TextView price;

        public TicketViewHolder (@NonNull View itemview){
            super(itemview);
            imv=itemview.findViewById(R.id.img_ticket);
            location1 = itemview.findViewById(R.id.text_ticket_name);
            location2 = itemview.findViewById(R.id.text_route);
            price=itemview.findViewById(R.id.text_price);
        }

    }
}
