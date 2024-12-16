package com.example.coachticketbookingapp.ui;

import android.annotation.SuppressLint;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.example.coachticketbookingapp.Object.TripBookingDetails;
import com.example.coachticketbookingapp.Object.TripInfo;
import com.example.coachticketbookingapp.Object.User;
import com.example.coachticketbookingapp.R;

import java.text.NumberFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

import DataBase.MyDataBase;

public class MyTicketDetailsActivity extends AppCompatActivity {
    TripBookingDetails tripBookingDetails;
    User myTicketDetailsActivityUser;
    TextView txvTenHanhKhach, txvDienThoai,txvEmail, txvDiemXuatPhat, txvDiemDen, txvNgayDatVe, txvNgayXuatPhat, txvGiaVe, txvSoVe, txvMaVe;
    Button btnDanhGia,btnHuyVe;
    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_my_ticket_details);

        Intent intentMyTicketDetailsActivity = getIntent();
        tripBookingDetails = (TripBookingDetails) intentMyTicketDetailsActivity.getSerializableExtra("tripbookingdetails");
        myTicketDetailsActivityUser = (User) intentMyTicketDetailsActivity.getSerializableExtra("tripBookingDetailsUser");

        txvTenHanhKhach = findViewById(R.id.txvTenHanhKhach);
        txvDienThoai = findViewById(R.id.txvDienThoai);
        txvEmail = findViewById(R.id.txvEmail);
        txvDiemXuatPhat=findViewById(R.id.txvDiemXuatPhat);
        txvDiemDen = findViewById(R.id.txvDiemDen);
        txvNgayDatVe = findViewById(R.id.txvNgayDatVe);
        txvNgayXuatPhat = findViewById(R.id.txvNgayXuatPhat);
        txvGiaVe = findViewById(R.id.txvGiaVe);
        txvSoVe = findViewById(R.id.txvSoVe);
        txvMaVe = findViewById(R.id.txvMaVe);
        btnDanhGia = findViewById(R.id.btnDanhGia);
        btnHuyVe = findViewById(R.id.btnHuyVe);

        if(tripBookingDetails!=null){
            txvTenHanhKhach.setText(myTicketDetailsActivityUser.getUserName());
            txvDienThoai.setText(myTicketDetailsActivityUser.getPhone());
            txvEmail.setText(myTicketDetailsActivityUser.getEmail());
            txvDiemXuatPhat.setText(tripBookingDetails.getFirstLocation());
            txvDiemDen.setText(tripBookingDetails.getSecondLocation());
            txvNgayDatVe.setText(tripBookingDetails.getBookingDate());
            txvNgayXuatPhat.setText(tripBookingDetails.getDepartureDate()+"-"+tripBookingDetails.getDepartureTime());
            double tonggia= tripBookingDetails.getPrice()* tripBookingDetails.getTicketQuantity();
            NumberFormat numberFormat = NumberFormat.getCurrencyInstance(new Locale("vi", "VN"));
            txvGiaVe.setText(numberFormat.format(tonggia));
            txvSoVe.setText(String.valueOf(tripBookingDetails.getTicketQuantity()));
            txvMaVe.setText(String.valueOf(tripBookingDetails.getTripBookingDetailsID()));

            checkCancelTicket(tripBookingDetails.getBookingTime());

            btnDanhGia.setOnClickListener(view -> {
                MyDataBase myDataBase = new MyDataBase(getApplicationContext());
                if(tripBookingDetails.getIsFeedBack()==0) {
                    TripInfo tripInfo = myDataBase.getTripInfo(tripBookingDetails.getTripID());

                    // Lấy ngày đến (destinationDate) của chuyến xe
                    String tripDestinationDate = tripInfo.getDestinationDate(); // Ngày đến của chuyến xe (dd/MM/yyyy)
                    // Chuyển đổi destinationDate thành milliseconds (chỉ tính ngày)
                    long tripDestinationDateInMillis = convertToMillis(tripDestinationDate);
                    // Lấy thời gian hiện tại và chỉ lấy phần ngày
                    long currentDateInMillis = System.currentTimeMillis();
                    // Đảm bảo rằng giờ, phút, giây được đặt về 0 để chỉ so sánh ngày
                    currentDateInMillis = currentDateInMillis - (currentDateInMillis % (24 * 60 * 60 * 1000));

                    // Kiểm tra nếu ngày đến đã qua chưa
                    if (tripDestinationDateInMillis > currentDateInMillis) {
                        // Nếu ngày đến > ngày hiện tại, chuyến xe chưa hoàn thành
                        Toast.makeText(MyTicketDetailsActivity.this, "Chuyến xe chưa hoàn thành, bạn không thể đánh giá ngay bây giờ!", Toast.LENGTH_SHORT).show();
                    } else {
                        // Nếu chuyến xe đã hoàn thành (ngày đến <= ngày hiện tại), cho phép đánh giá
                        Intent intent = new Intent(MyTicketDetailsActivity.this, FeedBackActivity.class);
                        intent.putExtra("tripbookingdetails", tripBookingDetails);
                        intent.putExtra("user", myTicketDetailsActivityUser);
                        startActivity(intent);
                    }
                }
                else{
                    AlertDialog.Builder builder = new AlertDialog.Builder(MyTicketDetailsActivity.this);
                    builder.setTitle("Thông báo")
                            .setMessage("Bạn đã đánh giá chuyến đi này rồi.")
                            .setPositiveButton("OK", (dialog, which) -> {
                                // Bạn có thể làm thêm hành động sau khi người dùng nhấn OK (nếu cần)
                                dialog.dismiss(); // Đóng hộp thoại
                            })
                            .create()
                            .show();
                }
            });

            btnHuyVe.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    // Tạo AlertDialog để xác nhận hủy vé
                    new AlertDialog.Builder(MyTicketDetailsActivity.this)
                            .setTitle("Xác nhận hủy vé")  // Tiêu đề của dialog
                            .setMessage("Bạn có chắc chắn muốn hủy vé này không? Bạn có thể hủy vé trong vòng 24h")  // Nội dung thông báo
                            .setPositiveButton("Có", new DialogInterface.OnClickListener() {
                                @Override
                                public void onClick(DialogInterface dialog, int which) {
                                    // Khi người dùng nhấn "Có", thực hiện xóa vé
                                    MyDataBase myDataBase = new MyDataBase(getApplicationContext());
                                    if (myDataBase.deleteTripBookingDetails(tripBookingDetails.getTripBookingDetailsID())) {
                                        // Thông báo xóa thành công (hoặc thực hiện hành động khác nếu cần)
                                        Toast.makeText(getApplicationContext(), "Hủy vé thành công", Toast.LENGTH_SHORT).show();
                                        finish();
                                    } else {
                                        // Thông báo thất bại nếu không thể xóa
                                        Toast.makeText(getApplicationContext(), "Có lỗi xảy ra, không thể hủy vé", Toast.LENGTH_SHORT).show();
                                    }
                                }
                            })
                            .setNegativeButton("Không", new DialogInterface.OnClickListener() {
                                @Override
                                public void onClick(DialogInterface dialog, int which) {
                                    // Khi người dùng nhấn "Không", chỉ đóng dialog mà không làm gì
                                    dialog.dismiss();
                                }
                            })
                            .show();  // Hiển thị Dialog
                }
            });
        }
    }

    public long convertToMillis(String dateString) {
        try {
            // Định dạng ngày theo định dạng dd/MM/yyyy
            SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy", Locale.getDefault());
            Date date = dateFormat.parse(dateString); // Chuyển chuỗi thành đối tượng Date
            return date.getTime(); // Trả về thời gian tính bằng milliseconds
        } catch (ParseException e) {
            e.printStackTrace();
            return 0; // Nếu lỗi trong quá trình phân tích, trả về 0
        }
    }

    public void checkCancelTicket(String bookingTimeString) {
        // Lấy thời gian hiện tại
        long currentTimeMillis = System.currentTimeMillis();

        // Kết hợp bookingDate và bookingTime thành chuỗi đầy đủ để tính toán chính xác
        String bookingDateTimeString = tripBookingDetails.getBookingDate() + " " + bookingTimeString;  // Ví dụ: "16/12/2024 08:11"

        // Chuyển chuỗi thành milliseconds
        long bookingDateTimeInMillis = convertToMillisWithTime(bookingDateTimeString);

        // Tính toán sự khác biệt giữa thời gian hiện tại và thời gian đặt vé
        long diffInMillis = currentTimeMillis - bookingDateTimeInMillis;

        // Kiểm tra nếu thời gian đặt vé trong vòng 24 giờ
        if (diffInMillis <= 24 * 60 * 60 * 1000) { // 24 giờ = 24 * 60 * 60 * 1000 milliseconds
            // Hiển thị nút hủy vé
            btnHuyVe.setVisibility(View.VISIBLE);  // Hiển thị nút hủy vé
        } else {
            // Ẩn nút hủy vé
            btnHuyVe.setVisibility(View.GONE);  // Ẩn nút hủy vé
        }
    }

    public long convertToMillisWithTime(String dateTimeString) {
        try {
            // Định dạng ngày giờ đầy đủ
            SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy HH:mm", Locale.getDefault());

            // Chuyển chuỗi thành đối tượng Date
            Date date = dateFormat.parse(dateTimeString);

            // Trả về thời gian tính bằng milliseconds
            return date != null ? date.getTime() : 0;
        } catch (ParseException e) {
            e.printStackTrace();
            return 0;  // Nếu có lỗi trong quá trình phân tích, trả về 0
        }
    }
}