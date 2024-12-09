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

import com.example.coachticketbookingapp.Object.item;


public class ItemAdapter extends RecyclerView.Adapter<ItemAdapter.ItemViewHolder> {
    Context context;
    private ArrayList<item> dsitem = new ArrayList<>();

    public ItemAdapter(Context context, ArrayList<item> dsitem) {
        this.context = context;
        this.dsitem = dsitem;
    }

    @NonNull
    @Override
    public ItemViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.cardview,parent,false);
        return new ItemViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ItemViewHolder holder, int position) {
        item itemm= dsitem.get(position);
        holder.imv.setImageResource(itemm.getImg());
        holder.location.setText(itemm.getContent());
        holder.price.setText(itemm.getPrice());
    }

    @Override
    public int getItemCount() {
        if(dsitem != null){
            return dsitem.size();
        }
        return 0;
    }
    public class ItemViewHolder extends RecyclerView.ViewHolder{
        ImageView imv;
        TextView location;
        TextView price;

        public ItemViewHolder (@NonNull View itemview){
            super(itemview);
            imv=itemview.findViewById(R.id.imageView4);
            location = itemview.findViewById(R.id.textView5);
            price=itemview.findViewById(R.id.textView7);
        }

    }
}
