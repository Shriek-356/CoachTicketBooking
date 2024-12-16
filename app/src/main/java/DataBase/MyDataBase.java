package DataBase;

import android.annotation.SuppressLint;
import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;
import android.widget.Toast;

import com.example.coachticketbookingapp.Object.FeedBack;
import com.example.coachticketbookingapp.Object.PopularTripInfo;
import com.example.coachticketbookingapp.Object.TripBookingDetails;
import com.example.coachticketbookingapp.Object.TripInfo;
import com.example.coachticketbookingapp.Object.TrippingCart;
import com.example.coachticketbookingapp.Object.User;
import com.example.coachticketbookingapp.ui.BusTripInfo;
import com.example.coachticketbookingapp.ui.CoachTripInfo;
import com.example.coachticketbookingapp.ui.Userr;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashSet;
import java.util.List;
import java.util.Locale;
import java.util.Set;

public class MyDataBase extends SQLiteOpenHelper {

    private static String dbName = "CoachTicketBookingDB.db";
    private static final int DATABASE_VERSION = 13;
    // Table

    // User
    public static String tbUser = "User";
    public static String tbUser_UserId = "UserID";
    public static String tbUser_Sex = "SEX";
    public static String tbUser_Phone = "Phone";
    public static String tbUser_Email = "Email";
    public static String tbUser_UserName = "UserName";
    public static String tbUser_Password = "Password";
    public static String tbUser_FullName = "FullName";
    public static String tbUser_Role = "Role";

    // Payment
    public static String tbPayment = "Payment";
    public static String tbPayment_PaymentID = "PaymentID";
    public static String tbPayment_PaymentMethod = "PaymentMethod";
    public static String tbPayment_Date = "Date";
    public static String tbPayment_TripBookingDetailsID = "TripBookingDetailsID";

    // TrippingCart
    public static String tbTrippingCart = "TrippingCart";
    public static String tbTrippingCart_CartId = "CartID";
    public static String tbTrippingCart_UserId = "UserID";
    public static String tbTrippingCart_TripId = "TripID";
    public static String tbTrippingCart_BookingDate = "BookingDate";
    public static String tbTrippingCart_TicketQuantity = "TicketQuantity";
    public static String tbTrippingCart_TotalPrice = "TotalPrice";

    // TripInfo
    public static String tbTripInfo = "TripInfo";
    public static String tbTripInfo_CoachID = "CoachID";
    public static String tbTripInfo_TripId = "TripID";
    public static String tbTripInfo_FirstLocation = "FirstLocation";
    public static String tbTripInfo_SecondLocation = "SecondLocation";
    public static String tbTripInfo_Departure = "Departure";
    public static String tbTripInfo_Destination = "Destination";
    public static String tbTripInfo_DepartureTime = "DepartureTime";
    public static String tbTripInfo_DepartureDate = "DepartureDate";
    public static String tbTripInfo_DestinationTime = "DestinationTime";
    public static String tbTripInfo_DestinationDate = "DestinationDate";
    public static String tbTripInfo_TicketAvailable = "TicketAvailable";
    public static String tbTripInfo_Price = "Price";
    public static String tbTripInfo_Distance = "Distance";

    // FeedBack
    public static String tbFeedback = "FeedBack";
    public static String tbFeedback_Content = "Content";
    public static String tbFeedback_UserId = "UserID";
    public static String tbFeedback_CoachId = "CoachID";
    public static String tbFeedback_TripId = "TripID";
    public static String tbFeedback_FeedBackId = "FeedBackID";
    public static String tbFeedback_Rate ="Rate";
    //Coach
    public static String tbCoach = "Coach";
    public static String tbCoach_CoachID="CoachID";
    public static String tbCoach_CoachBrand="CoachBrand";
    public static String tbCoach_TotalSeat="TotalSeat";
    public static String tbCoach_Type="Type";
    public static String tbCoach_LicensePlate="LicensePlate";

    //TripBookingDetails
    public static String tbTripBookingDetails = "TripBookingDetails";
    public static String tbTripBookingDetails_TripId = "TripID";
    public static String tbTripBookingDetails_UserId = "UserID";
    public static String tbTripBookingDetails_TripBookingDetailsId = "TripBookingDetailsID";
    public static String tbTripBookingDetails_BookingDate = "BookingDate";
    public static String tbTripBookingDetails_BookingTime = "BookingTime";
    public static String tbTripBookingDetails_TicketQuantity = "TicketQuantity";
    public static String tbTripBookingDetails_TotalPrice = "TotalPrice";
    public static String tbTripBookingDetails_FullName ="FullName";
    public static String tbTripBookingDetails_PhoneNumber ="PhoneNumber";
    public static String tbTripBookingDetails_Email ="Email";
    public static String tbTripBookingDetails_IsFeedBack="IsFeedBack";

    public MyDataBase(Context context) {
        super(context, dbName, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {

        // Bật kiểm tra ngoại khóa
        sqLiteDatabase.execSQL("PRAGMA foreign_keys=ON;");

        // Tạo bảng User
        String tbUserString = "CREATE TABLE " + tbUser + " ( "
                + tbUser_UserId + " INTEGER PRIMARY KEY AUTOINCREMENT, "
                + tbUser_UserName + " TEXT, "
                + tbUser_Password + " TEXT, "
                + tbUser_Email + " TEXT, "
                + tbUser_Phone + " TEXT, "
                + tbUser_Sex + " TEXT, "
                + tbUser_Role + " TEXT, "
                + tbUser_FullName + " TEXT )"
                ;

        // Tạo bảng Coach
        String tbCoachString = "CREATE TABLE " + tbCoach + " ( "
                + tbCoach_CoachID + " INTEGER PRIMARY KEY AUTOINCREMENT, "
                + tbCoach_CoachBrand + " TEXT, "
                + tbCoach_TotalSeat + " INTEGER, "
                + tbCoach_Type + " TEXT, "
                + tbCoach_LicensePlate + " TEXT )";

        // Tạo bảng TripInfo
        String tbTripInfoString = "CREATE TABLE " + tbTripInfo + " ( "
                + tbTripInfo_TripId + " INTEGER PRIMARY KEY AUTOINCREMENT, "
                + tbTripInfo_CoachID + " INTEGER, "
                + tbTripInfo_FirstLocation + " TEXT, "
                + tbTripInfo_SecondLocation + " TEXT, "
                + tbTripInfo_Departure + " TEXT, "
                + tbTripInfo_Destination + " TEXT, "
                + tbTripInfo_DepartureTime + " TEXT, "
                + tbTripInfo_DepartureDate + " DATE, "
                + tbTripInfo_DestinationTime + " TEXT, "
                + tbTripInfo_DestinationDate + " DATE, "
                + tbTripInfo_TicketAvailable + " INTEGER, "
                + tbTripInfo_Price + " DOUBLE, "
                + tbTripInfo_Distance + " INTEGER, "
                + "FOREIGN KEY (" + tbTripInfo_CoachID + ") REFERENCES " + tbCoach + " (" + tbCoach_CoachID + ")"
                + ")";

        // Tạo bảng TripBookingDetails
        String tbTripBookingDetailsString = "CREATE TABLE " + tbTripBookingDetails + " ( "
                + tbTripBookingDetails_TripBookingDetailsId + " INTEGER PRIMARY KEY AUTOINCREMENT, "
                + tbTripBookingDetails_UserId + " INTEGER, "
                + tbTripBookingDetails_TripId + " INTEGER, "
                + tbTripBookingDetails_BookingDate + " DATETIME, "
                + tbTripBookingDetails_BookingTime + " TEXT, "
                + tbTripBookingDetails_TicketQuantity + " INTEGER, "
                + tbTripBookingDetails_TotalPrice + " DOUBLE, "
                + tbTripBookingDetails_FullName + " TEXT, "
                + tbTripBookingDetails_PhoneNumber + " TEXT, "
                + tbTripBookingDetails_Email + " TEXT, "
                + tbTripBookingDetails_IsFeedBack + " INTEGER, "
                + "FOREIGN KEY (" + tbTripBookingDetails_UserId + ") REFERENCES " + tbUser + " (" + tbUser_UserId + "), "
                + "FOREIGN KEY (" + tbTripBookingDetails_TripId + ") REFERENCES " + tbTripInfo + " (" + tbTripInfo_TripId + ")"
                + ")";

        // Tạo bảng TrippingCart
        String tbTrippingCartString = "CREATE TABLE " + tbTrippingCart + " ( "
                + tbTrippingCart_CartId + " INTEGER PRIMARY KEY AUTOINCREMENT, "
                + tbTrippingCart_UserId + " INTEGER, "
                + tbTrippingCart_BookingDate + " DATETIME, "
                + tbTrippingCart_TripId + " INTEGER, "
                + tbTrippingCart_TicketQuantity + " INTEGER, "
                + tbTrippingCart_TotalPrice + " DOUBLE, "
                + "FOREIGN KEY (" + tbTrippingCart_UserId + ") REFERENCES " + tbUser + " (" + tbUser_UserId + "), "
                + "FOREIGN KEY (" + tbTrippingCart_TripId + ") REFERENCES " + tbTripInfo + " (" + tbTripInfo_TripId + ")"
                + ")";

        // Tạo bảng Feedback
        String tbFeedBackString = "CREATE TABLE " + tbFeedback + " ( "
                + tbFeedback_FeedBackId + " INTEGER PRIMARY KEY AUTOINCREMENT, "
                + tbFeedback_Content + " TEXT, "
                + tbFeedback_UserId + " INTEGER, "
                + tbFeedback_CoachId + " INTEGER, "
                + tbFeedback_TripId + " INTEGER, "
                + tbFeedback_Rate + " FLOAT, "
                + "FOREIGN KEY (" + tbFeedback_UserId + ") REFERENCES " + tbUser + " (" + tbUser_UserId + "), "
                + "FOREIGN KEY (" + tbFeedback_TripId + ") REFERENCES " + tbTripInfo + " (" + tbTripInfo_TripId + "), "
                + "FOREIGN KEY (" + tbFeedback_CoachId + ") REFERENCES " + tbCoach + " (" + tbCoach_CoachID + ")"
                + ")";


        // Tạo bảng Payment
        String tbPaymentString = "CREATE TABLE " + tbPayment + " ( "
                + tbPayment_PaymentID + " INTEGER PRIMARY KEY AUTOINCREMENT, "
                + tbPayment_PaymentMethod + " TEXT, "
                + tbPayment_Date + " DATETIME, "
                + tbPayment_TripBookingDetailsID + " INTEGER, "
                + "FOREIGN KEY (" + tbPayment_TripBookingDetailsID + ") REFERENCES " + tbTripBookingDetails + " (" + tbTripBookingDetails_TripBookingDetailsId + ")"
                + ")";

        // Thực thi các câu lệnh SQL tạo bảng
        sqLiteDatabase.execSQL(tbUserString);
        sqLiteDatabase.execSQL(tbTripBookingDetailsString);
        sqLiteDatabase.execSQL(tbPaymentString);
        sqLiteDatabase.execSQL(tbTrippingCartString);
        sqLiteDatabase.execSQL(tbTripInfoString);
        sqLiteDatabase.execSQL(tbFeedBackString);
        sqLiteDatabase.execSQL(tbCoachString);
    }


    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int oldVersion, int newVersion) {
        // Xóa các bảng cũ nếu cơ sở dữ liệu được nâng cấp
        /*sqLiteDatabase.execSQL("DROP TABLE IF EXISTS " + tbUser);
        sqLiteDatabase.execSQL("DROP TABLE IF EXISTS " + tbTripBookingDetails);
        sqLiteDatabase.execSQL("DROP TABLE IF EXISTS " + tbPayment);
        sqLiteDatabase.execSQL("DROP TABLE IF EXISTS " + tbTrippingCart);
        sqLiteDatabase.execSQL("DROP TABLE IF EXISTS " + tbTripInfo);
        sqLiteDatabase.execSQL("DROP TABLE IF EXISTS " + tbFeedback);
        sqLiteDatabase.execSQL("DROP TABLE IF EXISTS " + tbCoach);
        onCreate(sqLiteDatabase);  // Tạo lại các bảng mới
        */
        if (oldVersion < DATABASE_VERSION) {
            sqLiteDatabase.execSQL("DROP TABLE IF EXISTS " + tbCoach);
            // Tạo bảng Coach
            String tbCoachString = "CREATE TABLE " + tbCoach + " ( "
                    + tbCoach_CoachID + " INTEGER PRIMARY KEY AUTOINCREMENT, "
                    + tbCoach_CoachBrand + " TEXT, "
                    + tbCoach_TotalSeat + " INTEGER, "
                    + tbCoach_Type + " TEXT, "
                    + tbCoach_LicensePlate + " TEXT )";
            sqLiteDatabase.execSQL(tbCoachString);
        }
    }

    public SQLiteDatabase open() {
        return this.getWritableDatabase();
    }

    // Thêm một chuyến xe mới vào cơ sở dữ liệu
    public void addCoach(String coachBrand, int totalSeat, String licensePlate, String type) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(tbCoach_CoachBrand, coachBrand);
        values.put(tbCoach_TotalSeat, totalSeat);
        values.put(tbCoach_LicensePlate, licensePlate);
        values.put(tbCoach_Type, type);
        db.insert(tbCoach, null, values);
        db.close();
    }

    // Cập nhật chuyến xe trong cơ sở dữ liệu
    public void updateCoach(int coachID, String coachBrand, int totalSeat, String licensePlate, String type) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put("coachBrand", coachBrand);
        values.put("totalSeat", totalSeat);
        values.put("licensePlate", licensePlate);
        values.put("type", type);
        db.update("Coach", values, "coachID = ?", new String[]{String.valueOf(coachID)});
        db.close();
    }

    // Xóa chuyến xe khỏi cơ sở dữ liệu
    public void deleteCoach(int coachID) {
        SQLiteDatabase db = this.getWritableDatabase();
        db.delete("Coach", "coachID = ?", new String[]{String.valueOf(coachID)});
        db.close();
    }

    public boolean isLicensePlateExist(String licensePlate) {
        SQLiteDatabase db = this.getReadableDatabase();
        String query = "SELECT * FROM Coach WHERE licensePlate = ?";
        Cursor cursor = db.rawQuery(query, new String[]{licensePlate});

        // Nếu tìm thấy ít nhất 1 kết quả, tức là biển số xe đã tồn tại
        boolean exists = cursor.getCount() > 0;
        cursor.close();
        db.close();
        return exists;
    }

    public boolean isLicensePlateExistForUpdate(String licensePlate, int selectedCoachID) {
        SQLiteDatabase db = this.getReadableDatabase();
        String query = "SELECT * FROM Coach WHERE licensePlate = ? AND coachID != ?";
        Cursor cursor = db.rawQuery(query, new String[]{licensePlate, String.valueOf(selectedCoachID)});

        // Nếu tìm thấy ít nhất 1 kết quả, tức là biển số xe đã tồn tại
        boolean exists = cursor.getCount() > 0;
        cursor.close();
        db.close();

        return exists;
    }

    //Ham them thong tin ve khi nguoi dung dat ve;
    public void addTripBookingDetails(int userId, int tripId, String bookingDate, int ticketQuantity, double totalPrice,String fullName, String phoneNumber,String email, String bookingTime){
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(tbTripBookingDetails_UserId,userId);
        contentValues.put(tbTripBookingDetails_TripId,tripId);
        contentValues.put(tbTripBookingDetails_BookingDate,bookingDate);
        contentValues.put(tbTripBookingDetails_BookingTime,bookingTime);
        contentValues.put(tbTripBookingDetails_TicketQuantity,ticketQuantity);
        contentValues.put(tbTripBookingDetails_TotalPrice,totalPrice);
        contentValues.put(tbTripBookingDetails_FullName,fullName);
        contentValues.put(tbTripBookingDetails_PhoneNumber,phoneNumber);
        contentValues.put(tbTripBookingDetails_Email,email);
        db.insert(tbTripBookingDetails,null,contentValues);

        db.close();
    }
    
    //Hàm lấy chuyến xe được tìm thấy
    @SuppressLint("Range")
    public List<TripInfo> FoundTrip(String departure, String destination, String departuredate) {
        List<TripInfo> tripList = new ArrayList<>();
        SQLiteDatabase db = this.getReadableDatabase();


        // Câu truy vấn SQL để lấy tất cả các chuyến đi có Departure và Destination khớp
        String query = "SELECT * FROM " + tbTripInfo +
                " WHERE " + tbTripInfo_Departure + " = ? AND " + tbTripInfo_Destination + " = ?"
                + " AND " + tbTripInfo_DepartureDate + " = ?";

        // Thực thi câu truy vấn và trả về kết quả dưới dạng Cursor
        Cursor cursor = db.rawQuery(query, new String[] { departure, destination,departuredate });

        // Kiểm tra nếu Cursor có dữ liệu
        if (cursor != null && cursor.moveToFirst()) {
            do {
                // Lấy thông tin chuyến đi từ Cursor
                int tripID = cursor.getInt(cursor.getColumnIndex(tbTripInfo_TripId));
                int coachID = cursor.getInt(cursor.getColumnIndex(tbTripInfo_CoachID));
                String firstLocation = cursor.getString(cursor.getColumnIndex(tbTripInfo_FirstLocation));
                String secondLocation = cursor.getString(cursor.getColumnIndex(tbTripInfo_SecondLocation));
                String departureTime = cursor.getString(cursor.getColumnIndex(tbTripInfo_DepartureTime));
                String departureDate = cursor.getString(cursor.getColumnIndex(tbTripInfo_DepartureDate));
                String destinationTime = cursor.getString(cursor.getColumnIndex(tbTripInfo_DestinationTime));
                String destinationDate = cursor.getString(cursor.getColumnIndex(tbTripInfo_DestinationDate));
                int ticketAvailable = cursor.getInt(cursor.getColumnIndex(tbTripInfo_TicketAvailable));
                double price = cursor.getDouble(cursor.getColumnIndex(tbTripInfo_Price));
                int distance = cursor.getInt(cursor.getColumnIndex(tbTripInfo_Distance));

                // Tạo đối tượng TripInfo từ dữ liệu
                TripInfo trip = new TripInfo(
                        tripID, coachID, firstLocation, secondLocation,
                        departure, destination, departureTime, departureDate,
                        destinationTime,destinationDate, ticketAvailable, price, distance
                );

                // Thêm đối tượng TripInfo vào danh sách
                tripList.add(trip);

            } while (cursor.moveToNext());
        }

        // Đóng Cursor sau khi sử dụng
        if (cursor != null) {
            cursor.close();
        }

        // Trả về danh sách các chuyến đi
        return tripList;
    }


    public List<BusTripInfo> searchBusTrips(String departure, String destination, String departureTime, String departureDate) {
        List<BusTripInfo> busTripInfoList = new ArrayList<>();
        SQLiteDatabase db = this.getReadableDatabase();

        // Truy vấn bảng TripInfo và liên kết với bảng Coach
        String query = "SELECT c.CoachID, c.CoachBrand, c.TotalSeat, c.LicensePlate, c.Type, " +
                "t.Departure, t.Destination, t.DepartureTime, t.DepartureDate " +
                "FROM TripInfo t " +
                "INNER JOIN Coach c ON t.CoachID = c.CoachID " +
                "WHERE t.Departure = ? AND t.Destination = ? AND t.DepartureTime = ? AND t.DepartureDate = ?";

        // Thực thi truy vấn
        Cursor cursor = db.rawQuery(query, new String[]{departure, destination, departureTime, departureDate});

        // Lấy dữ liệu từ Cursor và tạo danh sách BusTripInfo
        if (cursor.moveToFirst()) {
            do {
                // Lấy giá trị từ các cột
                @SuppressLint("Range") String coachID = cursor.getString(cursor.getColumnIndex("CoachID"));
                @SuppressLint("Range") String coachBrand = cursor.getString(cursor.getColumnIndex("CoachBrand"));
                @SuppressLint("Range") int totalSeat = cursor.getInt(cursor.getColumnIndex("TotalSeat"));
                @SuppressLint("Range") String licensePlate = cursor.getString(cursor.getColumnIndex("LicensePlate"));
                @SuppressLint("Range") String type = cursor.getString(cursor.getColumnIndex("Type"));
                @SuppressLint("Range") String dep = cursor.getString(cursor.getColumnIndex("Departure"));
                @SuppressLint("Range") String des = cursor.getString(cursor.getColumnIndex("Destination"));
                @SuppressLint("Range") String depTime = cursor.getString(cursor.getColumnIndex("DepartureTime"));
                @SuppressLint("Range") String depDate = cursor.getString(cursor.getColumnIndex("DepartureDate"));

                // Thêm vào danh sách
                busTripInfoList.add(new BusTripInfo(coachID, coachBrand, totalSeat, licensePlate, type, dep, des, depTime, depDate));
            } while (cursor.moveToNext());
        }

        // Đóng Cursor và database
        cursor.close();
        db.close();

        return busTripInfoList;
    }

    public List<CoachTripInfo> getCoachTripInfo() {
        List<CoachTripInfo> list = new ArrayList<>();
        SQLiteDatabase db = this.getReadableDatabase();

        // Cập nhật lại câu lệnh SQL với tên bảng từ hằng số và thêm coachID vào SELECT
        String query = "SELECT c." + tbCoach_CoachID + ", c." + tbCoach_CoachBrand + ", c." + tbCoach_TotalSeat + ", c." + tbCoach_LicensePlate + ", c." + tbCoach_Type + ", "
                + "t." + tbTripInfo_Departure + ", t." + tbTripInfo_Destination + ", t." + tbTripInfo_DepartureTime + ", t." + tbTripInfo_DepartureDate + " "
                + "FROM " + tbCoach + " c "
                + "INNER JOIN " + tbTripInfo + " t ON c." + tbCoach_CoachID + " = t." + tbTripInfo_CoachID;

        Cursor cursor = db.rawQuery(query, null);
        if (cursor.moveToFirst()) {
            do {
                // Lấy giá trị từ cursor
                @SuppressLint("Range") int coachID = cursor.getInt(cursor.getColumnIndex(tbCoach_CoachID));
                @SuppressLint("Range") String coachBrand = cursor.getString(cursor.getColumnIndex(tbCoach_CoachBrand));
                @SuppressLint("Range") int totalSeat = cursor.getInt(cursor.getColumnIndex(tbCoach_TotalSeat));
                @SuppressLint("Range") String licensePlate = cursor.getString(cursor.getColumnIndex(tbCoach_LicensePlate));
                @SuppressLint("Range") String type = cursor.getString(cursor.getColumnIndex(tbCoach_Type));
                @SuppressLint("Range") String departure = cursor.getString(cursor.getColumnIndex(tbTripInfo_Departure));
                @SuppressLint("Range") String destination = cursor.getString(cursor.getColumnIndex(tbTripInfo_Destination));
                @SuppressLint("Range") String departureTime = cursor.getString(cursor.getColumnIndex(tbTripInfo_DepartureTime));
                @SuppressLint("Range") String departureDate = cursor.getString(cursor.getColumnIndex(tbTripInfo_DepartureDate));

                // Thêm đối tượng CoachTripInfo vào danh sách
                list.add(new CoachTripInfo(coachID, coachBrand, totalSeat, licensePlate, type, departure, destination, departureTime, departureDate));
            } while (cursor.moveToNext());
        }
        cursor.close();
        db.close();
        return list;
    }

    public boolean checkLogin(String email, String password) {
        SQLiteDatabase db = this.getReadableDatabase();

        String query = "SELECT * FROM " + tbUser + " WHERE " + tbUser_Email + " = ? AND " + tbUser_Password + " = ?";
        String[] selectionArgs = new String[]{email, password};

        Cursor cursor = db.rawQuery(query, selectionArgs);

        if (cursor != null && cursor.getCount() > 0) {
            cursor.close();
            return true;
        }

        if (cursor != null) {
            cursor.close();
        }
        return false;
    }

    @SuppressLint("Range")
    public String getUserName(String email) {
        SQLiteDatabase db = this.getReadableDatabase();
        String userName = null;

        String query = "SELECT " + tbUser_UserName + " FROM " + tbUser + " WHERE " + tbUser_Email + " = ?";
        String[] selectionArgs = new String[]{email};

        Cursor cursor = db.rawQuery(query, selectionArgs);

        if (cursor != null && cursor.moveToFirst()) {
            userName = cursor.getString(cursor.getColumnIndex(tbUser_UserName));
        }

        if (cursor != null) {
            cursor.close();
        }

        return userName;
    }
    @SuppressLint("Range")
    public User getUser(String email){
        //Dau tien phai mo database voi quyen doc
        SQLiteDatabase db = this.getReadableDatabase();

        //Cau lenh truy van
        String query = "SELECT * FROM " + tbUser + " Where " + tbUser_Email + " = ?";

        //thuc hien truy van bang cursor
        Cursor cursor = db.rawQuery(query,new String[]{email});

        if(cursor!=null&&cursor.moveToFirst()){
            //moveToNext tuc la con hang tiep theo trong bang du lieu thi con tro se di chuyen toi do
                int userID = cursor.getInt(cursor.getColumnIndex(tbUser_UserId));
                String username = cursor.getString(cursor.getColumnIndex(tbUser_UserName));
                String Email = cursor.getString(cursor.getColumnIndex(tbUser_Email));
                String passWord = cursor.getString(cursor.getColumnIndex(tbUser_Password));
                String sex = cursor.getString(cursor.getColumnIndex(tbUser_Sex));
                String phone = cursor.getString(cursor.getColumnIndex(tbUser_Phone));
                String role = cursor.getString(cursor.getColumnIndex(tbUser_Role));
                cursor.close();

                db.close();
                return new User(userID,role,passWord,phone,sex,Email,username);
        }
        cursor.close();
        db.close();
        return null;
    }

    public boolean checkAdminLogin(String username, String password) {
        SQLiteDatabase db = this.getReadableDatabase();

        String query = "SELECT * FROM " + tbUser + " WHERE " + tbUser_Email + " = ? AND " + tbUser_Password + " = ? AND " + tbUser_Role + " = ?";
        String[] selectionArgs = new String[]{username, password, "admin"};
        Cursor cursor = db.rawQuery(query, selectionArgs);

        boolean isAdmin = cursor.getCount() > 0;
        cursor.close();
        db.close();

        return isAdmin;
    }

    public void addAdminAccount() {
        SQLiteDatabase db = this.getWritableDatabase();

        String checkAdminQuery = "SELECT * FROM " + tbUser + " WHERE " + tbUser_UserId + " = ?";
        Cursor cursor = db.rawQuery(checkAdminQuery, new String[]{"1"});

        if (cursor.getCount() == 0) {
            String userEmail = "admin@example.com";
            String userPassword = "admin@123";
            String userName = "Admin";
            String userRole = "admin";

            String queryUser = "INSERT INTO " + tbUser + " (" + tbUser_UserName + ", " + tbUser_Password + ", " + tbUser_Email + ", " + tbUser_Role + ") VALUES (?, ?, ?, ?)";
            db.execSQL(queryUser, new Object[]{userName, userPassword, userEmail, userRole});

            db.execSQL("INSERT INTO " + tbUser + " (" + tbUser_UserId + ") VALUES (?)", new Object[]{1}); // UserID là 1
        }

        cursor.close();
        db.close();
    }

    public void addUser(String sex, String phone, String email, String userName, String passWord, String role){

        SQLiteDatabase db = this.getWritableDatabase();//Mo database voi quyen chinh sua

        //Tao doi tuong ContentValues de lay du lieu va put vao database
        ContentValues values = new ContentValues();
        //Cu phap la values.put("Ten cot",giatri)
        values.put(tbUser_Sex,sex);
        values.put(tbUser_Phone,phone);
        values.put(tbUser_Email,email);
        values.put(tbUser_UserName,userName);
        values.put(tbUser_Password,passWord);
        values.put(tbUser_Role,role);

        long newRowId = db.insert(tbUser, null, values);
        if (newRowId == -1) {
            System.out.println("Không thêm được người dùng");
        } else {
            System.out.println("Người dùng đã thêm ID: " + newRowId);
        }
        db.close();
    }

    public void addDefaultCoaches() {
        SQLiteDatabase db = this.getWritableDatabase();

        Cursor cursor = db.rawQuery("SELECT COUNT(*) FROM " + tbCoach, null);
        cursor.moveToFirst();
        int count = cursor.getInt(0);

        if (count == 0) { // chỉ thêm dữ liệu nếu bảng trống
            String[][] defaultCoaches = {
                    {"1", "Ford Transit", "16", "29A-12345", "Limousine"},
                    {"2", "Hyundai County", "16", "30B-54321", "Ghế ngồi"},
                    {"3", "Thaco Town", "16", "31C-98765", "Giường nằm"},
                    {"4", "Samco", "16", "32D-13579", "Ghế ngồi"},
                    {"5", "Mercedes-Benz", "16", "33E-24680", "Limousine"},
                    {"6", "Isuzu Samco", "16", "34F-11223", "Ghế ngồi"}
            };

            for (String[] coach : defaultCoaches) {
                ContentValues values = new ContentValues();
                values.put(tbCoach_CoachID, coach[0]);
                values.put(tbCoach_CoachBrand, coach[1]);
                values.put(tbCoach_TotalSeat, coach[2]);
                values.put(tbCoach_LicensePlate, coach[3]);
                values.put(tbCoach_Type, coach[4]);

                db.insert(tbCoach, null, values);
            }
            System.out.println("Các xe mặc định đã thêm");
        } else {
            System.out.println("Tồn tạo thì không thêm!!");
        }

        cursor.close();
        db.close();
    }

    public List<String> getCoachData() {
        List<String> coachList = new ArrayList<>();
        SQLiteDatabase db = this.getReadableDatabase();

        // Truy vấn bảng Coach
        Cursor cursor = db.query(tbCoach, new String[]{tbCoach_Type, tbCoach_CoachID}, null, null, null, null, null);

        if (cursor != null && cursor.moveToFirst()) {
            do {
                @SuppressLint("Range") String licensePlate = cursor.getString(cursor.getColumnIndex(tbCoach_Type));
                @SuppressLint("Range") String coachId = cursor.getString(cursor.getColumnIndex(tbCoach_CoachID));
                coachList.add(licensePlate + ": " + coachId + " (ID)");
            } while (cursor.moveToNext());
            cursor.close();
        }
        return coachList;
    }


    public Userr getUserById(int userId) {
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.query(MyDataBase.tbUser, null, MyDataBase.tbUser_UserId + " = ?",
                new String[]{String.valueOf(userId)}, null, null, null);
        if (cursor != null) {
            cursor.moveToFirst();
            @SuppressLint("Range") Userr user = new Userr(
                    cursor.getInt(cursor.getColumnIndex(MyDataBase.tbUser_UserId)),
                    cursor.getString(cursor.getColumnIndex(MyDataBase.tbUser_UserName)),
                    cursor.getString(cursor.getColumnIndex(MyDataBase.tbUser_Email)),
                    cursor.getString(cursor.getColumnIndex(MyDataBase.tbUser_Phone)),
                    cursor.getString(cursor.getColumnIndex(MyDataBase.tbUser_Sex)),
                    cursor.getString(cursor.getColumnIndex(MyDataBase.tbUser_Role)),
                    cursor.getString(cursor.getColumnIndex(MyDataBase.tbUser_Password))
            );
            cursor.close();
            return user;
        }
        cursor.close();
        return null;
    }

    public List<Userr> getAllUsers() {
        List<Userr> userList = new ArrayList<>();
        SQLiteDatabase db = this.getReadableDatabase();
        String query = "SELECT * FROM " + tbUser;
        Cursor cursor = db.rawQuery(query, null);

        if (cursor.moveToFirst()) {
            do {
                Userr user = new Userr();
                user.setUserId(cursor.getInt(cursor.getColumnIndexOrThrow(tbUser_UserId)));
                user.setUserName(cursor.getString(cursor.getColumnIndexOrThrow(tbUser_UserName)));
                user.setEmail(cursor.getString(cursor.getColumnIndexOrThrow(tbUser_Email)));
                user.setPhone(cursor.getString(cursor.getColumnIndexOrThrow(tbUser_Phone)));
                user.setSex(cursor.getString(cursor.getColumnIndexOrThrow(tbUser_Sex)));
                user.setRole(cursor.getString(cursor.getColumnIndexOrThrow(tbUser_Role)));
                user.setPassword(cursor.getString(cursor.getColumnIndexOrThrow(tbUser_Password))); // Thêm dòng này để lấy mật khẩu
                userList.add(user);
            } while (cursor.moveToNext());
        }

        cursor.close();
        db.close();
        return userList;
    }


    public void addTripInfo(int coachID, String firstLocation, String secondLocation, String departure, String destination,
                            String departureTime, String departureDate, String destinationTime, String destinationDate,
                            int ticketAvailable, double price, int distance) {
        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put(tbTripInfo_CoachID, coachID);
        values.put(tbTripInfo_FirstLocation, firstLocation);
        values.put(tbTripInfo_SecondLocation, secondLocation);
        values.put(tbTripInfo_Departure, departure);
        values.put(tbTripInfo_Destination, destination);
        values.put(tbTripInfo_DepartureTime, departureTime);
        values.put(tbTripInfo_DepartureDate, departureDate);
        values.put(tbTripInfo_DestinationTime, destinationTime);
        values.put(tbTripInfo_DestinationDate, destinationDate);
        values.put(tbTripInfo_TicketAvailable, ticketAvailable);
        values.put(tbTripInfo_Price, price);
        values.put(tbTripInfo_Distance, distance);

        long newRowId = db.insert(tbTripInfo, null, values);

        if (newRowId == -1) {
            System.out.println("Failed to add trip info");
        } else {
            System.out.println("Trip info added with ID: " + newRowId);
        }
        db.close();
    }

    public void addInitialTripInfo() {
        SQLiteDatabase db = this.getWritableDatabase();

        // kiểm tra xem bảng TripInfo đã có dữ liệu chưa
        String query = "SELECT COUNT(*) FROM " + tbTripInfo;
        Cursor cursor = db.rawQuery(query, null);

        // nếu bảng trống thì thêm dữ liệu
        if (cursor != null && cursor.moveToFirst() && cursor.getInt(0) == 0) {
            // Thêm các chuyến đi vào bảng
            addTripInfo(1, "Bến Xe Miền Tây", "Bến Xe Phương Trang", "Hồ Chí Minh", "Đồng Tháp",
                    "08:00", "23/12/2024", "10:00", "23/12/2024", 50, 200000, 100);
            addTripInfo(2, "Bến Xe Miền Tây", "Bến Xe Miền Tây", "Hồ Chí Minh", "Vũng Tàu",
                    "08:00", "23/12/2024", "11:00", "23/12/2024", 50, 150000, 60);
            addTripInfo(3, "Bến Xe Miền Tây", "Bến Xe Cần Thơ", "Hồ Chí Minh", "Cần Thơ",
                    "09:00", "24/12/2024", "12:00", "24/12/2024", 45, 180000, 80);
            addTripInfo(4, "Bến Xe Miền Đông", "Bến Xe Nha Trang", "Hồ Chí Minh", "Nha Trang",
                    "20:00", "25/12/2024", "06:00", "26/12/2024", 40, 300000, 70);
            addTripInfo(5, "Bến Xe Miền Đông", "Bến Xe Đà Lạt", "Hồ Chí Minh", "Đà Lạt",
                    "22:00", "26/12/2024", "05:00", "27/12/2024", 35, 350000, 50);
            addTripInfo(6, "Bến Xe Miền Tây", "Bến Xe Phương Trang", "Hồ Chí Minh", "Long An",
                    "07:30", "28/12/2024", "09:00", "28/12/2024", 50, 120000, 90);
            addTripInfo(1, "Bến Xe Miền Tây", "Bến Xe Đồng Nai", "Hồ Chí Minh", "Đồng Nai",
                    "10:00", "28/12/2024", "12:00", "28/12/2024",
                    50, 150000, 80);
            addTripInfo(2, "Bến Xe Miền Tây", "Bến Xe Phương Trang", "Hồ Chí Minh", "Tiền Giang",
                    "06:00", "23/12/2024", "08:00", "23/12/2024",
                    40, 100000, 60);
            addTripInfo(1, "Bến Xe Miền Tây", "Bến Xe Đà Lạt", "Hồ Chí Minh", "Đà Lạt",
                    "07:00", "23/12/2024", "13:00", "23/12/2024",
                    50, 300000, 80);
            addTripInfo(2, "Bến Xe Miền Đông", "Bến Xe Phan Thiết", "Hồ Chí Minh", "Phan Thiết",
                    "08:30", "24/12/2024", "12:00", "24/12/2024",
                    40, 250000, 70);
            addTripInfo(3, "Bến Xe Miền Đông", "Bến Xe Nha Trang", "Hồ Chí Minh", "Nha Trang",
                    "21:00", "24/12/2024", "06:00", "25/12/2024",
                    45, 350000, 100);
            addTripInfo(4, "Bến Xe Mỹ Đình", "Bến Xe Sapa", "Hà Nội", "Sapa",
                    "22:00", "25/12/2024", "05:00", "26/12/2024",
                    35, 400000, 50);
            addTripInfo(5, "Bến Xe Giáp Bát", "Bến Xe Hạ Long", "Hà Nội", "Hạ Long",
                    "06:00", "26/12/2024", "09:00", "26/12/2024",
                    50, 200000, 90);
            addTripInfo(6, "Bến Xe Miền Đông", "Bến Xe Quy Nhơn", "Hồ Chí Minh", "Quy Nhơn",
                    "19:30", "27/12/2024", "05:00", "28/12/2024",
                    40, 350000, 60);
            addTripInfo(1, "Bến Xe Miền Tây", "Bến Xe Cần Thơ", "Hồ Chí Minh", "Cần Thơ",
                    "10:00", "27/12/2024", "13:00", "27/12/2024",
                    50, 180000, 70);
            addTripInfo(2, "Bến Xe Mỹ Đình", "Bến Xe Đồng Văn", "Hà Nội", "Hà Giang",
                    "20:00", "27/12/2024", "04:00", "28/12/2024",
                    40, 300000, 50);
            addTripInfo(3, "Bến Xe Miền Tây", "Bến Xe Bạc Liêu", "Hồ Chí Minh", "Bạc Liêu",
                    "08:00", "28/12/2024", "14:00", "28/12/2024",
                    45, 300000, 80);
            addTripInfo(4, "Bến Xe Miền Đông", "Bến Xe Huế", "Hồ Chí Minh", "Huế",
                    "16:00", "28/12/2024", "06:00", "29/12/2024",
                    40, 400000, 50);
            addTripInfo(1, "Bến Xe Miền Tây", "Bến Xe Châu Đốc", "Hồ Chí Minh", "Châu Đốc",
                    "07:00", "23/12/2024", "12:00", "23/12/2024",
                    50, 250000, 100);
            addTripInfo(2, "Bến Xe Miền Tây", "Bến Xe Cà Mau", "Hồ Chí Minh", "Cà Mau",
                    "08:00", "24/12/2024", "16:00", "24/12/2024",
                    40, 300000, 80);
            addTripInfo(3, "Bến Xe Miền Đông", "Bến Xe Tây Ninh", "Hồ Chí Minh", "Tây Ninh",
                    "05:30", "25/12/2024", "09:00", "25/12/2024",
                    45, 150000, 90);
            addTripInfo(4, "Bến Xe Miền Đông", "Bến Xe Vĩnh Long", "Hồ Chí Minh", "Vĩnh Long",
                    "14:00", "25/12/2024", "17:00", "25/12/2024",
                    50, 180000, 100);
            addTripInfo(5, "Bến Xe Miền Đông", "Bến Xe Đà Nẵng", "Hồ Chí Minh", "Đà Nẵng",
                    "18:00", "23/12/2024", "09:00", "24/12/2024",
                    45, 400000, 80);
            addTripInfo(6, "Bến Xe Nha Trang", "Bến Xe Phú Yên", "Nha Trang", "Phú Yên",
                    "07:00", "24/12/2024", "10:00", "24/12/2024",
                    50, 200000, 100);
            addTripInfo(1, "Bến Xe Quy Nhơn", "Bến Xe Đà Nẵng", "Quy Nhơn", "Đà Nẵng",
                    "14:00", "25/12/2024", "20:00", "25/12/2024",
                    40, 250000, 60);
            addTripInfo(2, "Bến Xe Huế", "Bến Xe Quảng Bình", "Huế", "Quảng Bình",
                    "06:00", "26/12/2024", "10:00", "26/12/2024",
                    50, 220000, 90);
            addTripInfo(3, "Bến Xe Mỹ Đình", "Bến Xe Ninh Bình", "Hà Nội", "Ninh Bình",
                    "08:00", "23/12/2024", "11:00", "23/12/2024",
                    50, 150000, 80);
            addTripInfo(4, "Bến Xe Giáp Bát", "Bến Xe Cát Bà", "Hà Nội", "Cát Bà",
                    "07:30", "24/12/2024", "11:00", "24/12/2024",
                    40, 180000, 70);
            addTripInfo(5, "Bến Xe Gia Lâm", "Bến Xe Móng Cái", "Hà Nội", "Móng Cái",
                    "19:00", "24/12/2024", "04:00", "25/12/2024",
                    45, 350000, 100);
            addTripInfo(6, "Bến Xe Mỹ Đình", "Bến Xe Lạng Sơn", "Hà Nội", "Lạng Sơn",
                    "20:30", "25/12/2024", "02:30", "26/12/2024",
                    35, 200000, 50);
            addTripInfo(1, "Bến Xe Mỹ Đình", "Bến Xe Hải Dương", "Hà Nội", "Hải Dương",
                    "06:00", "26/12/2024", "08:00", "26/12/2024",
                    50, 100000, 90);
            addTripInfo(2, "Bến Xe Đà Nẵng", "Bến Xe Hội An", "Đà Nẵng", "Hội An",
                    "08:00", "27/12/2024", "09:00", "27/12/2024",
                    50, 70000, 50);
            addTripInfo(3, "Bến Xe Hà Giang", "Bến Xe Đồng Văn", "Hà Giang", "Đồng Văn",
                    "07:00", "27/12/2024", "10:00", "27/12/2024",
                    40, 150000, 40);
            addTripInfo(4, "Bến Xe Mỹ Đình", "Bến Xe Bắc Ninh", "Hà Nội", "Bắc Ninh",
                    "09:00", "28/12/2024", "10:00", "28/12/2024",
                    50, 80000, 100);
            addTripInfo(5, "Bến Xe Giáp Bát", "Bến Xe Thái Nguyên", "Hà Nội", "Thái Nguyên",
                    "10:30", "28/12/2024", "12:30", "28/12/2024",
                    40, 120000, 70);
            addTripInfo(6, "Bến Xe Mỹ Đình", "Bến Xe Bắc Giang", "Hà Nội", "Bắc Giang",
                    "11:00", "28/12/2024", "13:00", "28/12/2024",
                    45, 110000, 80);
            addTripInfo(1, "Bến Xe Miền Tây", "Bến Xe Long Xuyên", "Hồ Chí Minh", "Long Xuyên",
                    "07:00", "23/12/2024", "11:00", "23/12/2024",
                    45, 200000, 90);
            addTripInfo(2, "Bến Xe Miền Tây", "Bến Xe Cần Thơ", "Hồ Chí Minh", "Cần Thơ",
                    "08:00", "23/12/2024", "12:00", "23/12/2024",
                    50, 180000, 100);
            addTripInfo(3, "Bến Xe Miền Tây", "Bến Xe Sóc Trăng", "Hồ Chí Minh", "Sóc Trăng",
                    "09:30", "23/12/2024", "14:30", "23/12/2024",
                    40, 250000, 70);
            addTripInfo(4, "Bến Xe Miền Tây", "Bến Xe Bạc Liêu", "Hồ Chí Minh", "Bạc Liêu",
                    "12:00", "24/12/2024", "17:30", "24/12/2024",
                    50, 270000, 90);
            addTripInfo(5, "Bến Xe Miền Tây", "Bến Xe Phú Quốc", "Hồ Chí Minh", "Phú Quốc",
                    "06:00", "25/12/2024", "18:00", "25/12/2024",
                    35, 600000, 80);
            addTripInfo(6, "Bến Xe Nha Trang", "Bến Xe Buôn Ma Thuột", "Nha Trang", "Buôn Ma Thuột",
                    "08:00", "24/12/2024", "13:00", "24/12/2024",
                    45, 220000, 100);
            addTripInfo(1, "Bến Xe Quy Nhơn", "Bến Xe Kon Tum", "Quy Nhơn", "Kon Tum",
                    "06:30", "25/12/2024", "11:00", "25/12/2024",
                    40, 200000, 60);
            addTripInfo(2, "Bến Xe Đà Nẵng", "Bến Xe Đông Hà", "Đà Nẵng", "Đông Hà",
                    "14:00", "25/12/2024", "19:00", "25/12/2024",
                    50, 250000, 90);
            addTripInfo(3, "Bến Xe Huế", "Bến Xe Pleiku", "Huế", "Pleiku",
                    "07:00", "26/12/2024", "15:00", "26/12/2024",
                    40, 350000, 70);
            addTripInfo(4, "Bến Xe Đà Nẵng", "Bến Xe Quy Nhơn", "Đà Nẵng", "Quy Nhơn",
                    "16:00", "26/12/2024", "22:00", "26/12/2024",
                    45, 280000, 90);
            addTripInfo(5, "Bến Xe Mỹ Đình", "Bến Xe Tam Đảo", "Hà Nội", "Tam Đảo",
                    "07:00", "23/12/2024", "10:00", "23/12/2024",
                    50, 150000, 80);
            addTripInfo(6, "Bến Xe Gia Lâm", "Bến Xe Sapa", "Hà Nội", "Sapa",
                    "21:00", "24/12/2024", "05:00", "25/12/2024",
                    35, 400000, 70);
            addTripInfo(1, "Bến Xe Giáp Bát", "Bến Xe Thái Bình", "Hà Nội", "Thái Bình",
                    "08:00", "25/12/2024", "10:30", "25/12/2024",
                    50, 120000, 100);
            addTripInfo(2, "Bến Xe Mỹ Đình", "Bến Xe Hà Giang", "Hà Nội", "Hà Giang",
                    "22:00", "26/12/2024", "06:00", "27/12/2024",
                    40, 350000, 90);
            addTripInfo(3, "Bến Xe Gia Lâm", "Bến Xe Yên Bái", "Hà Nội", "Yên Bái",
                    "15:00", "27/12/2024", "18:00", "27/12/2024",
                    45, 150000, 70);
            addTripInfo(4, "Bến Xe Đà Lạt", "Bến Xe Phan Thiết", "Đà Lạt", "Phan Thiết",
                    "10:00", "23/12/2024", "15:00", "23/12/2024",
                    50, 300000, 100);
            addTripInfo(5, "Bến Xe Vũng Tàu", "Bến Xe Tây Ninh", "Vũng Tàu", "Tây Ninh",
                    "06:00", "24/12/2024", "11:00", "24/12/2024",
                    40, 270000, 80);
            addTripInfo(6, "Bến Xe Đà Nẵng", "Bến Xe Hải Phòng", "Đà Nẵng", "Hải Phòng",
                    "20:00", "24/12/2024", "08:00", "25/12/2024",
                    35, 550000, 60);
            addTripInfo(1, "Bến Xe Hà Nội", "Bến Xe Hạ Long", "Hà Nội", "Hạ Long",
                    "12:30", "25/12/2024", "16:30", "25/12/2024",
                    50, 200000, 90);
            addTripInfo(2, "Bến Xe Sapa", "Bến Xe Điện Biên", "Sapa", "Điện Biên",
                    "08:00", "26/12/2024", "15:00", "26/12/2024",
                    40, 350000, 70);
            addTripInfo(1, "Bến Xe Miền Tây", "Bến Xe Châu Đốc", "Hồ Chí Minh", "Châu Đốc",
                    "07:00", "23/12/2024", "12:00", "23/12/2024",
                    45, 250000, 100);
            addTripInfo(2, "Bến Xe Miền Tây", "Bến Xe Rạch Giá", "Hồ Chí Minh", "Rạch Giá",
                    "09:00", "24/12/2024", "15:00", "24/12/2024",
                    40, 300000, 90);
            addTripInfo(3, "Bến Xe Miền Tây", "Bến Xe Trà Vinh", "Hồ Chí Minh", "Trà Vinh",
                    "13:00", "25/12/2024", "17:00", "25/12/2024",
                    50, 200000, 70);
            addTripInfo(4, "Bến Xe Miền Đông", "Bến Xe Đà Lạt", "Hồ Chí Minh", "Đà Lạt",
                    "22:00", "25/12/2024", "06:00", "26/12/2024",
                    35, 400000, 80);
            addTripInfo(5, "Bến Xe Miền Đông", "Bến Xe Nha Trang", "Hồ Chí Minh", "Nha Trang",
                    "20:00", "26/12/2024", "04:00", "27/12/2024",
                    40, 350000, 100);
            addTripInfo(6, "Bến Xe Nha Trang", "Bến Xe Đà Lạt", "Nha Trang", "Đà Lạt",
                    "07:30", "23/12/2024", "11:30", "23/12/2024",
                    50, 250000, 100);
            addTripInfo(1, "Bến Xe Đà Nẵng", "Bến Xe Hội An", "Đà Nẵng", "Hội An",
                    "09:00", "24/12/2024", "10:30", "24/12/2024",
                    45, 100000, 60);
            addTripInfo(2, "Bến Xe Quy Nhơn", "Bến Xe Tuy Hòa", "Quy Nhơn", "Tuy Hòa",
                    "15:00", "24/12/2024", "18:00", "24/12/2024",
                    40, 150000, 70);
            addTripInfo(3, "Bến Xe Huế", "Bến Xe Đông Hà", "Huế", "Đông Hà",
                    "08:30", "25/12/2024", "11:30", "25/12/2024",
                    45, 180000, 80);
            addTripInfo(4, "Bến Xe Đà Nẵng", "Bến Xe Kon Tum", "Đà Nẵng", "Kon Tum",
                    "20:00", "26/12/2024", "04:00", "27/12/2024",
                    35, 400000, 90);
            addTripInfo(5, "Bến Xe Mỹ Đình", "Bến Xe Mộc Châu", "Hà Nội", "Mộc Châu",
                    "06:30", "23/12/2024", "12:00", "23/12/2024",
                    50, 250000, 70);
            addTripInfo(6, "Bến Xe Gia Lâm", "Bến Xe Cát Bà", "Hà Nội", "Cát Bà",
                    "08:00", "24/12/2024", "12:00", "24/12/2024",
                    45, 200000, 100);
            addTripInfo(1, "Bến Xe Giáp Bát", "Bến Xe Bắc Giang", "Hà Nội", "Bắc Giang",
                    "10:00", "25/12/2024", "11:30", "25/12/2024",
                    40, 120000, 90);
            addTripInfo(2, "Bến Xe Gia Lâm", "Bến Xe Điện Biên", "Hà Nội", "Điện Biên",
                    "19:00", "26/12/2024", "07:00", "27/12/2024",
                    35, 400000, 80);
            addTripInfo(3, "Bến Xe Mỹ Đình", "Bến Xe Thái Nguyên", "Hà Nội", "Thái Nguyên",
                    "14:00", "27/12/2024", "16:00", "27/12/2024",
                    50, 120000, 100);
            addTripInfo(4, "Bến Xe Đà Lạt", "Bến Xe Huế", "Đà Lạt", "Huế",
                    "18:00", "24/12/2024", "09:00", "25/12/2024",
                    40, 550000, 70);
            addTripInfo(5, "Bến Xe Phú Quốc", "Bến Xe Cần Thơ", "Phú Quốc", "Cần Thơ",
                    "20:00", "25/12/2024", "07:00", "26/12/2024",
                    35, 400000, 60);
            addTripInfo(6, "Bến Xe Đà Nẵng", "Bến Xe Hà Nội", "Đà Nẵng", "Hà Nội",
                    "21:00", "26/12/2024", "08:00", "27/12/2024",
                    50, 500000, 90);
            addTripInfo(1, "Bến Xe Sapa", "Bến Xe Đà Nẵng", "Sapa", "Đà Nẵng",
                    "18:00", "27/12/2024", "12:00", "28/12/2024",
                    40, 600000, 100);
            addTripInfo(2, "Bến Xe Hà Nội", "Bến Xe Vũng Tàu", "Hà Nội", "Vũng Tàu",
                    "17:00", "27/12/2024", "15:00", "28/12/2024",
                    30, 800000, 70);
            addTripInfo(1, "Bến Xe Miền Tây", "Bến Xe Cần Thơ", "Hồ Chí Minh", "Cần Thơ",
                    "07:00", "23/12/2024", "10:00", "23/12/2024",
                    50, 150000, 100);
            addTripInfo(2, "Bến Xe Miền Tây", "Bến Xe Long Xuyên", "Hồ Chí Minh", "Long Xuyên",
                    "09:00", "23/12/2024", "13:00", "23/12/2024",
                    45, 180000, 90);
            addTripInfo(3, "Bến Xe Miền Tây", "Bến Xe Sóc Trăng", "Hồ Chí Minh", "Sóc Trăng",
                    "13:00", "24/12/2024", "18:00", "24/12/2024",
                    40, 200000, 80);
            addTripInfo(4, "Bến Xe Miền Tây", "Bến Xe Bạc Liêu", "Hồ Chí Minh", "Bạc Liêu",
                    "21:00", "24/12/2024", "03:00", "25/12/2024",
                    35, 250000, 70);
            addTripInfo(5, "Bến Xe Miền Tây", "Bến Xe Cà Mau", "Hồ Chí Minh", "Cà Mau",
                    "22:00", "25/12/2024", "05:00", "26/12/2024",
                    30, 300000, 60);
            addTripInfo(6, "Bến Xe Cần Thơ", "Bến Xe Cà Mau", "Cần Thơ", "Cà Mau",
                    "07:30", "26/12/2024", "11:30", "26/12/2024",
                    40, 200000, 80);
            addTripInfo(1, "Bến Xe Long Xuyên", "Bến Xe Châu Đốc", "Long Xuyên", "Châu Đốc",
                    "08:00", "26/12/2024", "10:00", "26/12/2024",
                    45, 100000, 90);
            addTripInfo(2, "Bến Xe Cần Thơ", "Bến Xe Sóc Trăng", "Cần Thơ", "Sóc Trăng",
                    "14:00", "27/12/2024", "16:00", "27/12/2024",
                    50, 120000, 100);
            addTripInfo(3, "Bến Xe Bạc Liêu", "Bến Xe Cà Mau", "Bạc Liêu", "Cà Mau",
                    "18:00", "27/12/2024", "20:00", "27/12/2024",
                    45, 90000, 70);
            addTripInfo(4, "Bến Xe Miền Tây", "Cảng Hà Tiên", "Hồ Chí Minh", "Hà Tiên",
                    "20:00", "23/12/2024", "06:00", "24/12/2024",
                    40, 400000, 60);
            addTripInfo(5, "Bến Xe Miền Tây", "Cảng Rạch Giá", "Hồ Chí Minh", "Rạch Giá",
                    "19:00", "24/12/2024", "05:00", "25/12/2024",
                    35, 350000, 50);
            addTripInfo(6, "Bến Xe Cần Thơ", "Bến Xe Trà Vinh", "Cần Thơ", "Trà Vinh",
                    "06:00", "23/12/2024", "08:30", "23/12/2024",
                    50, 150000, 80);
            addTripInfo(1, "Bến Xe Sóc Trăng", "Bến Xe Bạc Liêu", "Sóc Trăng", "Bạc Liêu",
                    "09:00", "24/12/2024", "10:30", "24/12/2024",
                    40, 80000, 100);
            addTripInfo(2, "Bến Xe Châu Đốc", "Bến Xe Long Xuyên", "Châu Đốc", "Long Xuyên",
                    "13:00", "25/12/2024", "15:00", "25/12/2024",
                    45, 120000, 90);
            addTripInfo(3, "Bến Xe Cà Mau", "Bến Xe Cần Thơ", "Cà Mau", "Cần Thơ",
                    "15:00", "27/12/2024", "19:00", "27/12/2024",
                    50, 200000, 70);
            addTripInfo(4, "Bến Xe Miền Tây", "Bến Xe Đà Lạt", "Hồ Chí Minh", "Đà Lạt",
                    "22:00", "26/12/2024", "06:00", "27/12/2024",
                    35, 350000, 90);
            addTripInfo(5, "Bến Xe Miền Đông", "Bến Xe Quy Nhơn", "Hồ Chí Minh", "Quy Nhơn",
                    "20:00", "27/12/2024", "07:00", "28/12/2024",
                    30, 450000, 60);
            System.out.println("Tất cả thông tin chuyến đi đã được thêm thành công.");
        } else {
            System.out.println("Dữ liệu đã tồn tại. Không có thông tin chuyến đi được thêm vào.");
        }

        cursor.close();
        db.close();
    }
    @SuppressLint("Range")
    public List<TripBookingDetails> getTripBookingList(int userID){
        List<TripBookingDetails> list = new ArrayList<>();
        SQLiteDatabase db = this.getReadableDatabase();//Mo csdl
        //Cau truy van
        String query = "SELECT a.TripID, b.TripBookingDetailsID,a.Departure,a.Destination, a.FirstLocation, a.SecondLocation, b.BookingDate, a.DepartureTime, a.DepartureDate, a.Price, a.Distance, b.TicketQuantity, b.TotalPrice, b.IsFeedBack, b.BookingTime "
                + "FROM TripInfo a JOIN TripBookingDetails b "
                + "On a.TripID = b.TripID "
                + "WHERE b.UserID = ?";

        Cursor cursor = db.rawQuery(query,new String[]{String.valueOf(userID)});

        if(cursor!=null&&cursor.moveToFirst()){
            do{
                int tripID = cursor.getInt(cursor.getColumnIndex(tbTripInfo_TripId));
                int tripBookingDetailsId = cursor.getInt(cursor.getColumnIndex(tbTripBookingDetails_TripBookingDetailsId));
                String departure = cursor.getString(cursor.getColumnIndex(tbTripInfo_Departure));
                String destination  = cursor.getString(cursor.getColumnIndex(tbTripInfo_Destination));
                String firstLocation = cursor.getString(cursor.getColumnIndex(tbTripInfo_FirstLocation));
                String secondLocation = cursor.getString(cursor.getColumnIndex(tbTripInfo_SecondLocation));
                String bookingDate = cursor.getString(cursor.getColumnIndex(tbTripBookingDetails_BookingDate));
                String departureTime = cursor.getString(cursor.getColumnIndex(tbTripInfo_DepartureTime));
                String departureDate = cursor.getString(cursor.getColumnIndex(tbTripInfo_DepartureDate));
                double price = cursor.getDouble(cursor.getColumnIndex(tbTripInfo_Price));
                int distance = cursor.getInt(cursor.getColumnIndex(tbTripInfo_Distance));
                int ticketQuantity = cursor.getInt(cursor.getColumnIndex(tbTripBookingDetails_TicketQuantity));
                double totalPrice = cursor.getInt(cursor.getColumnIndex(tbTripBookingDetails_TotalPrice));
                int isFeedBack = cursor.getInt(cursor.getColumnIndex(tbTripBookingDetails_IsFeedBack));
                String bookingTime = cursor.getString(cursor.getColumnIndex(tbTripBookingDetails_BookingTime));
                TripBookingDetails tripBookingDetails = new TripBookingDetails(tripID,tripBookingDetailsId,departure,destination,firstLocation,secondLocation,bookingDate,departureTime,departureDate,price,distance,ticketQuantity,totalPrice,isFeedBack,bookingTime);
                list.add(tripBookingDetails);
            }while (cursor.moveToNext());
        }

        cursor.close();
        db.close();
        return list;
    }

    public boolean addTrippingCart(int userID, String bookingDate, int tripID, int ticketQuantity, double totalPrice) {
        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put(tbTrippingCart_UserId, userID);
        values.put(tbTrippingCart_BookingDate, bookingDate);
        values.put(tbTrippingCart_TripId, tripID);
        values.put(tbTrippingCart_TicketQuantity, ticketQuantity);
        values.put(tbTrippingCart_TotalPrice, totalPrice);

        // Kiểm tra kết quả của db.insert, nếu trả về -1 thì không thành công, nếu khác -1 thì thành công
        long result = db.insert(tbTrippingCart, null, values);
        db.close();

        return result != -1;  // Nếu result != -1 thì trả về true, ngược lại là false
    }

    //Ham kiem tra xem loai ve do da co trong gio hang hay chua
    public boolean isTrippingCartExists(int tripID, int userID){

        SQLiteDatabase db = this.getReadableDatabase();

        String query = "SELECT * FROM " + tbTrippingCart
                + " WHERE " + tbTrippingCart_TripId + " = ? AND " + tbTrippingCart_UserId + " = ? ";

        Cursor cursor = db.rawQuery(query, new String[]{String.valueOf(tripID),String.valueOf(userID)});

        if(cursor!=null&&cursor.getCount()>0){
            cursor.close();
            return true;
        }

        cursor.close();
        return false;
    }

    public void updateTrippingCart(int tripID, int userID, int ticketQuantity, double totalPrice){
        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put(tbTrippingCart_TicketQuantity,ticketQuantity);
        values.put(tbTrippingCart_TotalPrice,totalPrice);
        db.update(tbTrippingCart,values,tbTrippingCart_TripId + " = ? AND " + tbTrippingCart_UserId + " = ? ",new String[]{String.valueOf(tripID),String.valueOf(userID)});
        db.close();
    }

    @SuppressLint("Range")
    public TrippingCart getTrippingCart(int tripID, int userID){

        SQLiteDatabase db = this.getReadableDatabase();

        String query = "SELECT * "
                + " FROM " +tbTrippingCart
                + " WHERE " + tbTrippingCart_TripId + " = ? AND " + tbTrippingCart_UserId + " = ? ";

        Cursor cursor = db.rawQuery(query,new String[]{String.valueOf(tripID),String.valueOf(userID)});

        if(cursor!=null&&cursor.moveToFirst()){
                 int tripId = cursor.getInt(cursor.getColumnIndex(tbTrippingCart_TripId));
                 int userId = cursor.getInt(cursor.getColumnIndex(tbTrippingCart_UserId));
                 int ticketQuantity = cursor.getInt(cursor.getColumnIndex(tbTrippingCart_TicketQuantity));
                 double totalPrice = cursor.getDouble(cursor.getColumnIndex(tbTrippingCart_TotalPrice));
                 TrippingCart trippingCart = new TrippingCart(userId,null,tripId,ticketQuantity,totalPrice);
                 db.close();
                 return trippingCart;
        }
        db.close();
        return null;
    }

    @SuppressLint("Range")
    public TripInfo getTripInfo(int tripID) {
        SQLiteDatabase db = this.getReadableDatabase();

        // Định nghĩa câu truy vấn SQL để lấy thông tin chuyến đi theo tripID
        String query = "SELECT * FROM " + tbTripInfo + " WHERE " + tbTripInfo_TripId + " = ?";

        // Thực hiện câu truy vấn
        Cursor cursor = db.rawQuery(query, new String[]{String.valueOf(tripID)});

        // Kiểm tra nếu có dữ liệu trả về
        if (cursor != null && cursor.moveToFirst()) {
            // Lấy thông tin từ cursor và tạo đối tượng TripInfo
            int coachID = cursor.getInt(cursor.getColumnIndex(tbTripInfo_CoachID));
            String firstLocation = cursor.getString(cursor.getColumnIndex(tbTripInfo_FirstLocation));
            String secondLocation = cursor.getString(cursor.getColumnIndex(tbTripInfo_SecondLocation));
            String departure = cursor.getString(cursor.getColumnIndex(tbTripInfo_Departure));
            String destination = cursor.getString(cursor.getColumnIndex(tbTripInfo_Destination));
            String departureTime = cursor.getString(cursor.getColumnIndex(tbTripInfo_DepartureTime));
            String departureDate = cursor.getString(cursor.getColumnIndex(tbTripInfo_DepartureDate));
            String destinationTime = cursor.getString(cursor.getColumnIndex(tbTripInfo_DestinationTime));
            String destinationDate = cursor.getString(cursor.getColumnIndex(tbTripInfo_DestinationDate));
            int ticketAvailable = cursor.getInt(cursor.getColumnIndex(tbTripInfo_TicketAvailable));
            double price = cursor.getDouble(cursor.getColumnIndex(tbTripInfo_Price));
            int distance = cursor.getInt(cursor.getColumnIndex(tbTripInfo_Distance));

            // Tạo đối tượng TripInfo với dữ liệu vừa lấy
            TripInfo tripInfo = new TripInfo(
                    tripID,
                    coachID,
                    firstLocation,
                    secondLocation,
                    departure,
                    destination,
                    departureTime,
                    departureDate,
                    destinationTime,
                    destinationDate,
                    ticketAvailable,
                    price,
                    distance
            );

            cursor.close(); // Đóng cursor
            db.close(); // Đóng database
            return tripInfo; // Trả về đối tượng TripInfo
        }
        // Nếu không có dữ liệu
        cursor.close();
        db.close();
        return null; // Trả về null nếu không tìm thấy chuyến đi
    }

    @SuppressLint("Range")
    public List<TrippingCart> getTrippingCartList(int userID) {
        SQLiteDatabase db = this.getReadableDatabase();
        List<TrippingCart> cartList = new ArrayList<>();

        // Định nghĩa câu truy vấn SQL để lấy tất cả các giỏ hàng của một người dùng
        String query = "SELECT * FROM " + tbTrippingCart + " WHERE " + tbTrippingCart_UserId + " = ?";

        // Thực hiện câu truy vấn và trả về cursor
        Cursor cursor = db.rawQuery(query, new String[]{String.valueOf(userID)});

        // Kiểm tra nếu có dữ liệu trả về
        if (cursor != null && cursor.moveToFirst()) {
            do {
                // Lấy thông tin từ cursor và tạo đối tượng TrippingCart
                int cartID = cursor.getInt(cursor.getColumnIndex(tbTrippingCart_CartId));
                String bookingDate = cursor.getString(cursor.getColumnIndex(tbTrippingCart_BookingDate));
                int tripID = cursor.getInt(cursor.getColumnIndex(tbTrippingCart_TripId));
                int ticketQuantity = cursor.getInt(cursor.getColumnIndex(tbTrippingCart_TicketQuantity));
                double totalPrice = cursor.getDouble(cursor.getColumnIndex(tbTrippingCart_TotalPrice));

                // Tạo đối tượng TrippingCart với dữ liệu vừa lấy
                TrippingCart trippingCart = new TrippingCart(userID, bookingDate, tripID, ticketQuantity, totalPrice);

                // Thêm đối tượng vào danh sách
                cartList.add(trippingCart);
            } while (cursor.moveToNext()); // Lặp qua tất cả các bản ghi
        }

        // Đóng cursor và database
        cursor.close();
        db.close();

        // Trả về danh sách các giỏ hàng
        return cartList;
    }

    public void updateTicketAvailability(int tripId, int ticketQuantity) {
        SQLiteDatabase db = this.getWritableDatabase();
        String sql = "UPDATE TripInfo SET TicketAvailable = TicketAvailable - ? WHERE TripId = ?";
        db.execSQL(sql, new Object[]{ticketQuantity, tripId});
        db.close();
    }



    public void deleteTrippingCart(int userId, int tripId) {
        SQLiteDatabase db = this.getWritableDatabase();
        // Điều kiện để xóa dữ liệu
        String whereClause = tbTrippingCart_UserId + " = ? AND " + tbTrippingCart_TripId + " = ?";
        String[] whereArgs = new String[] { String.valueOf(userId), String.valueOf(tripId) };
        db.delete(tbTrippingCart, whereClause, whereArgs);
        db.close();
    }

    public void addFeedback(String content, int userId, int coachId,int tripId, float rate) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();

        values.put(tbFeedback_Content, content);
        values.put(tbFeedback_UserId, userId);
        values.put(tbFeedback_CoachId, coachId);
        values.put(tbFeedback_TripId,tripId);
        values.put(tbFeedback_Rate, rate);
        db.insert(tbFeedback, null, values);
        db.close(); // Đóng cơ sở dữ liệu
    }

    public void updateIsFeedBack(int tripBookingDetailsId) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put("IsFeedBack", 1); // Cập nhật IsFeedBack thành 1 sau khi đánh giá
        db.update(tbTripBookingDetails, values, tbTripBookingDetails_TripBookingDetailsId + " = ?", new String[]{String.valueOf(tripBookingDetailsId)});
        db.close();
    }

    @SuppressLint("Range")
    public List<FeedBack> getFeedbacksByCoachId(int coachId) {
        List<FeedBack> feedbackList = new ArrayList<>();
        SQLiteDatabase db = this.getReadableDatabase();

        // Truy vấn lấy các đánh giá của nhà xe
        Cursor cursor = db.rawQuery("SELECT * FROM FeedBack WHERE CoachId = ?", new String[]{String.valueOf(coachId)});

        if (cursor != null && cursor.moveToFirst()) {
            do {
                int feedBackID = cursor.getInt(cursor.getColumnIndex(tbFeedback_FeedBackId));
                String content = cursor.getString(cursor.getColumnIndex(tbFeedback_Content));
                int userID = cursor.getInt(cursor.getColumnIndex(tbFeedback_UserId));
                int coachid = cursor.getInt(cursor.getColumnIndex(tbFeedback_CoachId));
                int tripId = cursor.getInt(cursor.getColumnIndex(tbFeedback_TripId));
                float rate = cursor.getFloat(cursor.getColumnIndex(tbFeedback_Rate));
                
                FeedBack feedBack = new FeedBack(feedBackID,content,userID,coachid,tripId,rate);
                feedbackList.add(feedBack);
            } while (cursor.moveToNext());
            cursor.close();
        }
        return feedbackList;
    }
    @SuppressLint("Range")
    public List<Float> getListRate(int coachID){
        List<Float> getList = new ArrayList<>();

        SQLiteDatabase db = this.getReadableDatabase();

        String query = "SELECT Rate FROM " + tbFeedback
                + " WHERE " + tbFeedback_CoachId + " = ? ";

        Cursor cursor = db.rawQuery(query,new String[]{String.valueOf(coachID)});

        if(cursor!=null&&cursor.moveToFirst()){
            do{
                Float rate = cursor.getFloat(cursor.getColumnIndex(tbFeedback_Rate));
                getList.add(rate);
            }while(cursor.moveToNext());
        }
        cursor.close();
        return getList;
    }

    public float getAverageRating(int coachID) {
        List<Float> ratings = getListRate(coachID);  // Lấy danh sách các đánh giá

        if (ratings.isEmpty()) {
            return 0;  // Nếu không có đánh giá, trả về 0
        }

        float total = 0;
        for (Float rate : ratings) {
            total += rate;  // Tính tổng các giá trị đánh giá
        }

        return total / ratings.size();  // Tính và trả về điểm trung bình
    }



    @SuppressLint("Range")
    public String getCoachBrandById(int CoachID){

        SQLiteDatabase db = this.getReadableDatabase();
        String query = "SELECT " + tbCoach_CoachBrand
                + " FROM " + tbCoach
                + " WHERE " + tbCoach_CoachID + " = ?";
        String name ="";
        Cursor cursor = db.rawQuery(query, new String[]{String.valueOf(CoachID)});

        if(cursor!=null&&cursor.moveToFirst()){
             name =cursor.getString(cursor.getColumnIndex(tbCoach_CoachBrand));
        }
        cursor.close();
        db.close();
        return name;
    }

    //Lấy tổng lượt đánh giá của điểm đến và điểm đi
    @SuppressLint("Range")
    public List<PopularTripInfo> getPopularTrips() {
        SQLiteDatabase db = this.getReadableDatabase();
        List<PopularTripInfo> trips = new ArrayList<>();

        String query = "SELECT ti.TripID, ti.FirstLocation, ti.SecondLocation, ti.Departure, ti.Destination, " +
                "COUNT(f.FeedBackID) AS RatingCount " +
                "FROM FeedBack f " +
                "JOIN TripInfo ti ON f.TripID = ti.TripID " +
                "GROUP BY ti.Departure, ti.Destination " +
                "ORDER BY RatingCount DESC " +
                "LIMIT 5";

        Cursor cursor = db.rawQuery(query, null);

        if (cursor.moveToFirst()) {
            do {
                int tripId = cursor.getInt(cursor.getColumnIndex("TripID"));
                String firstLocation = cursor.getString(cursor.getColumnIndex("FirstLocation"));
                String secondLocation = cursor.getString(cursor.getColumnIndex("SecondLocation"));
                String departure = cursor.getString(cursor.getColumnIndex("Departure"));
                String destination = cursor.getString(cursor.getColumnIndex("Destination"));
                int ratingCount = cursor.getInt(cursor.getColumnIndex("RatingCount"));

                // Tạo đối tượng PopularTripInfo và thêm vào danh sách
                PopularTripInfo trip = new PopularTripInfo(tripId, firstLocation, secondLocation, departure, destination, ratingCount);
                trips.add(trip);
            } while (cursor.moveToNext());
        }
        cursor.close();
        return trips;
    }

    public boolean deleteTripBookingDetails(int bookingId) {
        SQLiteDatabase db = this.getWritableDatabase();
        // Xóa vé trong bảng tbTripBookingDetails dựa trên TripBookingDetailsId
        String whereClause = tbTripBookingDetails_TripBookingDetailsId + " = ?";
        String[] whereArgs = new String[] { String.valueOf(bookingId) };

        int rowsAffected = db.delete(tbTripBookingDetails, whereClause, whereArgs);

        return rowsAffected > 0;  // Trả về true nếu có ít nhất 1 bản ghi bị xóa
    }

    public void addPayment(String paymentMethod, String date, int tripBookingDetailsID ){
        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put(tbPayment_PaymentMethod,paymentMethod);
        values.put(tbPayment_Date,date);
        values.put(tbPayment_TripBookingDetailsID,tripBookingDetailsID);
        db.insert(tbPayment,null,values);
    }

    @SuppressLint("Range")
    public int getTripBookingDetailsID(int tripID, int userID, String bookingDate, String bookingTime){

        SQLiteDatabase db = this.getReadableDatabase();

        String query = "SELECT " + tbTripBookingDetails_TripBookingDetailsId
                + " FROM " + tbTripBookingDetails
                + " WHERE " + tbTripBookingDetails_TripId + " = ? "
                + " AND " + tbTripBookingDetails_UserId + " = ? "
                + " AND " + tbTripBookingDetails_BookingDate + " = ? "
                + " AND " + tbTripBookingDetails_BookingTime + " = ? ";

        Cursor cursor = db.rawQuery(query, new String[]{String.valueOf(tripID),String.valueOf(userID),bookingDate,bookingTime});
        int id =0;
        if(cursor!=null&& cursor.moveToFirst()){
            id = cursor.getInt(cursor.getColumnIndex(tbTripBookingDetails_TripBookingDetailsId));
        }
        cursor.close();
        db.close();
        return id;
    }


}


