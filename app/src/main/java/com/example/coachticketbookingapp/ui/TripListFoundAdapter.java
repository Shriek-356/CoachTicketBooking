package com.example.coachticketbookingapp.ui;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.coachticketbookingapp.Object.TripInfo;
import com.example.coachticketbookingapp.R;

import java.text.NumberFormat;
import java.util.List;
import java.util.Locale;

import DataBase.MyDataBase;

public class TripListFoundAdapter extends RecyclerView.Adapter<TripListFoundAdapter.TripListFoundViewHolder>  {
    private Context mContext;
    private List<TripInfo> dsTrip;
    private OnItemClickListener mListener;

    public interface OnItemClickListener {
        void onItemClick(TripInfo tripInfo);
    }

    public TripListFoundAdapter(Context mContext, OnItemClickListener listener) {
        this.mContext = mContext;
        this.mListener = listener;
    }

    public void setData(List<TripInfo> dsTrip){
        this.dsTrip=dsTrip;
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public TripListFoundViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.trip_list__found_cardview,parent,false);
        return new TripListFoundViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull TripListFoundViewHolder holder, int position) {
        TripInfo tripInfo = dsTrip.get(position);
        if(tripInfo==null){
            return;
        }

        holder.txvStartTime.setText(tripInfo.getDepartureTime());
        holder.txvEndTime.setText(tripInfo.getDestinationTime());
        holder.txvDeparture.setText(tripInfo.getDeparture());
        holder.txvDestination.setText(tripInfo.getDestination());
        NumberFormat numberFormat = NumberFormat.getCurrencyInstance(new Locale("vi", "VN"));
        holder.txvPrice.setText(numberFormat.format(tripInfo.getPrice()));
        String s = "Từ " + tripInfo.getDeparture() + " đến " + tripInfo.getDestination();
        holder.txvLocation.setText(s);
        MyDataBase myDataBase = new MyDataBase(mContext);

        float totalRate = myDataBase.getAverageRating(tripInfo.getCoachID());
        if(totalRate==0){
            holder.txvAvgRate.setText("Chưa có đánh giá");
        }
        else{
            List<Float> dsRate = myDataBase.getListRate(tripInfo.getCoachID());
            int quantityFeedBack = dsRate.size();
            String formattedRate = String.format(Locale.getDefault(), "%.1f", totalRate);
            holder.txvAvgRate.setText(formattedRate +" (" + String.valueOf(quantityFeedBack)+") ");
        }
        // Set sự kiện click cho item
        holder.itemView.setOnClickListener(v -> {
            // Khi click vào item, gọi callback
            if (mListener != null) {
                mListener.onItemClick(tripInfo);
            }
        });
    }

    @Override
    public int getItemCount() {
        if(dsTrip!=null){
            return dsTrip.size();
        }
        return 0;
    }

    public class TripListFoundViewHolder extends RecyclerView.ViewHolder {
    //Phần này hiểu đơn giản là lấy các giá trị của cái view item, sau đó gán các giá trị tương ứng
    TextView txvStartTime;
    TextView txvEndTime;
    TextView txvDeparture;
    TextView txvDestination;
    TextView txvPrice;
    TextView txvLocation;
    TextView txvAvgRate;

    public TripListFoundViewHolder(@NonNull View itemView) {
        super(itemView);

        txvStartTime = itemView.findViewById(R.id.txvStartTime);
        txvEndTime = itemView.findViewById(R.id.txvEndTime);
        txvDeparture = itemView.findViewById(R.id.txvDeparture);
        txvDestination = itemView.findViewById(R.id.txvDestination);
        txvPrice = itemView.findViewById(R.id.txvTicketPrice);
        txvLocation = itemView.findViewById(R.id.txvTicketLocation);
        txvAvgRate = itemView.findViewById(R.id.txvAvgRate);
    }
  }
}
