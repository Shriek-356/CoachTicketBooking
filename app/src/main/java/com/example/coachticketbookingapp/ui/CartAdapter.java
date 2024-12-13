package com.example.coachticketbookingapp.ui;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.coachticketbookingapp.Object.TripInfo;
import com.example.coachticketbookingapp.Object.TrippingCart;
import com.example.coachticketbookingapp.Object.User;
import com.example.coachticketbookingapp.R;

import java.text.NumberFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

import DataBase.MyDataBase;

public class CartAdapter extends RecyclerView.Adapter<CartAdapter.CartViewHolder>{
    private MyDataBase myDataBase;
    private Context context;
    private List<TrippingCart> dsTrippingCart;
    private User user;

    public CartAdapter(Context context) {
        this.context = context;
    }

    public void setData(List<TrippingCart> dsTrippingCart, User user){
        this.dsTrippingCart=dsTrippingCart;
        this.user=user;
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public CartViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.cart_cardview,parent,false);
        return new CartViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull CartViewHolder holder, int position) {

        TrippingCart trippingCart = dsTrippingCart.get(position);

        if(trippingCart==null){
            return;
        }
        myDataBase=new MyDataBase(context);
        //Lay TripInfo;
        TripInfo tripInfo = myDataBase.getTripInfo(trippingCart.getTripID());

        holder.txvCartLocation.setText("Vé xe " + tripInfo.getDeparture() + " - " +tripInfo.getDestination());
        holder.txvCartFromTo.setText(tripInfo.getDeparture()+" -> " + tripInfo.getDestination());
        NumberFormat numberFormat = NumberFormat.getCurrencyInstance(new Locale("vi", "VN"));
        holder.txvCartPrice.setText(numberFormat.format(tripInfo.getPrice()));

        holder.btnDecrease.setOnClickListener(view -> {

            int number = Integer.parseInt(holder.txvTicketQuantity.getText().toString());
            if(number==1){
                return;
            }
            number=number-1;
            holder.txvTicketQuantity.setText(String.valueOf(number));
        });

        holder.btnIncrease.setOnClickListener(view -> {
            int number = Integer.parseInt(holder.txvTicketQuantity.getText().toString());
            if(number==tripInfo.getTicketAvailable()){
                return;
            }
            number=number+1;
            holder.txvTicketQuantity.setText(String.valueOf(number));
        });

        holder.btnCartDatVe.setOnClickListener(view -> {
            Intent intent = new Intent(context, BookingUserInfoActivity.class);
            intent.putExtra("tripinfoo",tripInfo);
            intent.putExtra("userr",user);
            intent.putExtra("ticketQuantity",Integer.parseInt(holder.txvTicketQuantity.getText().toString()));
            context.startActivity(intent);
        });
    }

    @Override
    public int getItemCount() {
        if(dsTrippingCart!=null){
            return dsTrippingCart.size();
        }
        return 0;
    }

    public class CartViewHolder extends RecyclerView.ViewHolder{
        TextView txvCartLocation,txvCartFromTo,txvCartPrice,txvTicketQuantity;
        Button btnCartDatVe,btnDecrease,btnIncrease;

        @SuppressLint("WrongViewCast")
        public CartViewHolder(@NonNull View itemView) {
            super(itemView);
            txvCartLocation = itemView.findViewById(R.id.txvCartLocation);
            txvCartFromTo = itemView.findViewById(R.id.txvCartFromTo);
            txvCartPrice = itemView.findViewById(R.id.txvCartPrice);
            btnCartDatVe = itemView.findViewById(R.id.btnCartDatVe);
            btnDecrease = itemView.findViewById(R.id.btnDecrease);
            btnIncrease = itemView.findViewById(R.id.btnIncrease);
            txvTicketQuantity = itemView.findViewById(R.id.txvCartTicketQuantity);
        }
    }
}
