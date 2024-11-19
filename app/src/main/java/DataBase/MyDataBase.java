package DataBase;

import android.annotation.SuppressLint;
import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import com.example.coachticketbookingapp.Object.User;

import java.util.ArrayList;
import java.util.List;

public class MyDataBase extends SQLiteOpenHelper {

    private static String dbName = "CoachTicketBookingDB.db";
    // Table
    // User
    public static String tbUser = "User";
    public static String tbUser_UserId = "UserID";
    public static String tbUser_Sex = "SEX";
    public static String tbUser_Phone = "Phone";
    public static String tbUser_Email = "Email";
    public static String tbUser_UserName = "UserName";
    public static String tbUser_Password = "Password";
    public static String tbUser_Role = "Role";

    // Admin
    public static String tbAdmin = "Admin";
    public static String tbAdmin_UserId = "UserID";

    // Customer
    public static String tbCustomer = "Customer";
    public static String tbCustomer_UserId = "UserID";

    // Payment
    public static String tbPayment = "Payment";
    public static String tbPayment_PaymentID = "PaymentID";
    public static String tbPayment_PaymentMethod = "PaymentMethod";
    public static String tbPayment_Date = "Date";
    public static String tbPayment_UserId = "UserID";
    public static String tbPayment_CartId = "CartID";

    // TrippingCart
    public static String tbTrippingCart = "TrippingCart";
    public static String tbTrippingCart_CartId = "CartID";
    public static String tbTrippingCart_UserId = "UserID";
    public static String tbTrippingCart_TripId = "TripID";
    public static String tbTrippingCart_BookingDate = "BookingDate";

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
    public static String tbFeedback_TripId = "TripID";
    public static String tbFeedback_FeedBackId = "FeedBackID";

    //Coach
    public static String tbCoach = "Coach";
    public static String tbCoach_CoachID="CoachID";
    public static String tbCoach_CoachBrand="CoachBrand";
    public static String tbCoach_TotalSeat="TotalSeat";
    public static String tbCoach_LicensePlate="LicensePlate";

    public MyDataBase(Context context) {
        super(context, dbName, null, 1);
    }

   /* public void addTripInfo(String departure, String destination, String departureTime, String departureDate, int ticketAvailable, String coachBrand, String price, int distance) {
        SQLiteDatabase db = this.getWritableDatabase();
        String query = "INSERT INTO " + tbTripInfo + " ("
                + tbTripInfo_Departure + ", "
                + tbTripInfo_Destination + ", "
                + tbTripInfo_DepartureTime + ", "
                + tbTripInfo_DepartureDate + ", "
                + tbTripInfo_TicketAvailable + ", "
                + tbTripInfo_CoachBrand + ", "
                + tbTripInfo_Price + ", "
                + tbTripInfo_Distance + ") VALUES (?, ?, ?, ?, ?, ?, ?, ?)";

        db.execSQL(query, new Object[]{departure, destination, departureTime, departureDate, ticketAvailable, coachBrand, price, distance});
        db.close();
    }
    */

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
                + tbUser_Role + " TEXT )";

        // Tạo bảng Admin
        String tbAdminString = "CREATE TABLE " + tbAdmin + " ( "
                + tbAdmin_UserId + " INTEGER PRIMARY KEY )";

        // Tạo bảng Customer
        String tbCustomerString = "CREATE TABLE " + tbCustomer + " ( "
                + tbCustomer_UserId + " INTEGER PRIMARY KEY )";

        // Tạo bảng Payment
        String tbPaymentString = "CREATE TABLE " + tbPayment + " ( "
                + tbPayment_PaymentID + " INTEGER PRIMARY KEY AUTOINCREMENT, "
                + tbPayment_PaymentMethod + " TEXT, "
                + tbPayment_Date + " TEXT, "
                + tbPayment_UserId + " INTEGER, "
                + tbPayment_CartId + " TEXT, "
                + "FOREIGN KEY (" + tbPayment_UserId + ") REFERENCES " + tbUser + " (" + tbUser_UserId + "), "
                + "FOREIGN KEY (" + tbPayment_CartId + ") REFERENCES " + tbTrippingCart + " (" + tbTrippingCart_CartId + ")"
                + ")";

        // Tạo bảng TrippingCart
        String tbTrippingCartString = "CREATE TABLE " + tbTrippingCart + " ( "
                + tbTrippingCart_CartId + " TEXT PRIMARY KEY, "
                + tbTrippingCart_UserId + " INTEGER, "
                + tbTrippingCart_BookingDate + " DATETIME, "
                + tbTrippingCart_TripId + " INTEGER, "
                + "FOREIGN KEY (" + tbTrippingCart_UserId + ") REFERENCES " + tbUser + " (" + tbUser_UserId + "), "
                + "FOREIGN KEY (" + tbTrippingCart_TripId + ") REFERENCES " + tbTripInfo + " (" + tbTripInfo_TripId + ")"
                + ")";

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

        // Tạo bảng Feedback
        String tbFeedBackString = "CREATE TABLE " + tbFeedback + " ( "
                + tbFeedback_FeedBackId + " TEXT PRIMARY KEY, "
                + tbFeedback_Content + " TEXT, "
                + tbFeedback_UserId + " INTEGER, "
                + tbFeedback_TripId + " INTEGER, "
                + "FOREIGN KEY (" + tbFeedback_UserId + ") REFERENCES " + tbUser + " (" + tbUser_UserId + "), "
                + "FOREIGN KEY (" + tbFeedback_TripId + ") REFERENCES " + tbTripInfo + " (" + tbTripInfo_TripId + ")"
                + ")";

        // Tạo bảng Coach
        String tbCoachString = "CREATE TABLE " + tbCoach + " ( "
                + tbCoach_CoachID + " INTEGER PRIMARY KEY AUTOINCREMENT, "
                + tbCoach_CoachBrand + " TEXT, "
                + tbCoach_TotalSeat + " INTEGER, "
                + tbCoach_LicensePlate + " TEXT )";

        // Thực thi các câu lệnh SQL tạo bảng
        sqLiteDatabase.execSQL(tbUserString);
        sqLiteDatabase.execSQL(tbAdminString);
        sqLiteDatabase.execSQL(tbCustomerString);
        sqLiteDatabase.execSQL(tbPaymentString);
        sqLiteDatabase.execSQL(tbTrippingCartString);
        sqLiteDatabase.execSQL(tbTripInfoString);
        sqLiteDatabase.execSQL(tbFeedBackString);
        sqLiteDatabase.execSQL(tbCoachString);
    }


    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {
        sqLiteDatabase.execSQL("DROP TABLE IF EXISTS " + tbUser);
        sqLiteDatabase.execSQL("DROP TABLE IF EXISTS " + tbAdmin);
        sqLiteDatabase.execSQL("DROP TABLE IF EXISTS " + tbCustomer);
        sqLiteDatabase.execSQL("DROP TABLE IF EXISTS " + tbPayment);
        sqLiteDatabase.execSQL("DROP TABLE IF EXISTS " + tbTrippingCart);
        sqLiteDatabase.execSQL("DROP TABLE IF EXISTS " + tbTripInfo);
        sqLiteDatabase.execSQL("DROP TABLE IF EXISTS " + tbFeedback);
        onCreate(sqLiteDatabase);
    }

    public SQLiteDatabase open() {
        return this.getWritableDatabase();
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

    public void addAdminAccount() {
        SQLiteDatabase db = this.getWritableDatabase();

        String checkAdminQuery = "SELECT * FROM " + tbAdmin + " WHERE " + tbAdmin_UserId + " = ?";
        Cursor cursor = db.rawQuery(checkAdminQuery, new String[]{"1"});

        if (cursor.getCount() == 0) {
            String userEmail = "admin@example.com";
            String userPassword = "admin@123";
            String userName = "Admin";
            String userRole = "admin";

            String queryUser = "INSERT INTO " + tbUser + " (" + tbUser_UserName + ", " + tbUser_Password + ", " + tbUser_Email + ", " + tbUser_Role + ") VALUES (?, ?, ?, ?)";
            db.execSQL(queryUser, new Object[]{userName, userPassword, userEmail, userRole});

            db.execSQL("INSERT INTO " + tbAdmin + " (" + tbAdmin_UserId + ") VALUES (?)", new Object[]{1}); // UserID là 1
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
            // Nếu giá trị trả về là -1, có lỗi xảy ra khi chèn dữ liệu
            System.out.println("Failed to add user");
        } else {
            // Nếu thành công, hiển thị ID của dòng mới
            System.out.println("User added with ID: " + newRowId);
        }
        db.close();
    }
}


