package com.example.coachticketbookingapp.ui;

import android.annotation.SuppressLint;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.RadioButton;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;

import com.example.coachticketbookingapp.R;

import java.util.List;

import DataBase.MyDataBase;

public class ManageUser extends AppCompatActivity {
    private ListView listViewUser;
    private MyDataBase myDataBase;
    private UserAdapter userAdapter;

    private EditText txtTenUser;
    private EditText txtEmailUser;
    private EditText txtPhoneUser;
    private EditText txtPassUser;  // Thêm trường mật khẩu
    private RadioButton radioButtonNam;
    private RadioButton radioButtonNu;
    private Button btnThem;
    private Button btnSua;
    private Button btnXoa;
    private Button btnChuyen;
    private Button btnback;

    private int selectedUserId = -1;


    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_manage_user);

        // anh xạ các view từ layout
        listViewUser = findViewById(R.id.lwuser);
        txtTenUser = findViewById(R.id.txtTenUser);
        txtEmailUser = findViewById(R.id.txtEmailUser);
        txtPhoneUser = findViewById(R.id.txtPhoneUser);
        txtPassUser = findViewById(R.id.txtPassUser); // Ánh xạ trường mật khẩu
        radioButtonNam = findViewById(R.id.radioButtonNam);
        radioButtonNu = findViewById(R.id.radioButtonNu);
        btnThem = findViewById(R.id.btnThem);
        btnSua = findViewById(R.id.btnSua);
        btnXoa = findViewById(R.id.btnXoa);
        btnChuyen = findViewById(R.id.btnChuyen);
        btnback = findViewById(R.id.btnback);

        myDataBase = new MyDataBase(this);

        // lay danh sách người dùng từ cơ sở dữ liệu
        List<Userr> userList = myDataBase.getAllUsers();
        userAdapter = new UserAdapter(this, userList);
        listViewUser.setAdapter(userAdapter);

        btnThem.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                new android.app.AlertDialog.Builder(ManageUser.this)
                        .setMessage("Bạn có chắc chắn muốn thêm người dùng này không?")
                        .setCancelable(false)
                        .setPositiveButton("Đồng ý", (dialog, id) -> {
                            addUser();
                        })
                        .setNegativeButton("Thoát", (dialog, id) -> {
                            dialog.dismiss();
                        })
                        .show();
            }
        });

        btnSua.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                new android.app.AlertDialog.Builder(ManageUser.this)
                        .setMessage("Bạn có chắc chắn muốn sửa đổi thông tin người dùng này không?")
                        .setCancelable(false)
                        .setPositiveButton("Đồng ý", (dialog, id) -> {
                            updateUser();
                        })
                        .setNegativeButton("Thoát", (dialog, id) -> {
                            dialog.dismiss();
                        })
                        .show();
            }
        });

        btnback.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });


        // xu ly sự kiện khi chọn một người dùng trong ListView
        listViewUser.setOnItemClickListener((parent, view, position, id) -> {
            Userr selectedUser = userList.get(position);

            selectedUserId = selectedUser.getUserId();


            // diền thông tin người dùng vào các edt và rdb
            txtTenUser.setText(selectedUser.getUserName());
            txtEmailUser.setText(selectedUser.getEmail());
            txtPhoneUser.setText(selectedUser.getPhone());
            txtPassUser.setText(selectedUser.getPassword()); // Điền mật khẩu

            // kiểm tra giới tính và chọn rdb tương ứng
            if ("Nam".equalsIgnoreCase(selectedUser.getSex())) {
                radioButtonNam.setChecked(true);
                radioButtonNu.setChecked(false);
            } else if ("Nữ".equalsIgnoreCase(selectedUser.getSex())) {
                radioButtonNam.setChecked(false);
                radioButtonNu.setChecked(true);
            }
            if ("admin".equalsIgnoreCase(selectedUser.getRole())) {
                radioButtonNam.setChecked(false);
                radioButtonNu.setChecked(false);
            }
        });

        btnXoa.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // kiểm tra xem người dùng có được chọn không
                if (selectedUserId == -1) {
                    Toast.makeText(ManageUser.this, "Vui lòng chọn người dùng để xóa!", Toast.LENGTH_SHORT).show();
                    return;
                }

                Userr selectedUser = myDataBase.getUserById(selectedUserId);  // Giả sử bạn có phương thức getUserById trong MyDataBase để lấy thông tin người dùng theo ID
                if ("Admin".equalsIgnoreCase(selectedUser.getUserName())) {
                    Toast.makeText(ManageUser.this, "Không thể xóa tài khoản Admin!", Toast.LENGTH_SHORT).show();
                    return;
                }

                // hiển thị hộp thoại xác nhận
                new android.app.AlertDialog.Builder(ManageUser.this)
                        .setMessage("Bạn có chắc chắn muốn xóa người dùng này không?")
                        .setCancelable(false)
                        .setPositiveButton("Đồng ý", (dialog, id) -> {
                            deleteUser();
                        })
                        .setNegativeButton("Thoát", (dialog, id) -> {
                            dialog.dismiss();
                        })
                        .show();
            }
        });

        btnChuyen.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (selectedUserId == -1) {
                    Toast.makeText(ManageUser.this, "Vui lòng chọn người dùng để chuyển vai trò!", Toast.LENGTH_SHORT).show();
                    return;
                }

                Userr selectedUser = myDataBase.getUserById(selectedUserId);

                if ("admin@example.com".equalsIgnoreCase(selectedUser.getEmail())) {
                    Toast.makeText(ManageUser.this, "Không thể thay đổi vai trò của người quản trị hệ thống!", Toast.LENGTH_SHORT).show();
                    return;
                }

                String message = "";

                // kểm tra vai trò của người dùng
                if ("user".equalsIgnoreCase(selectedUser.getRole())) {
                    message = "Bạn có muốn chuyển vai trò người dùng này từ user sang admin hay không?";
                } else if ("admin".equalsIgnoreCase(selectedUser.getRole())) {
                    message = "Bạn có muốn chuyển vai trò người dùng này từ admin sang user hay không?";
                }

                // hiển thị hộp thoại xác nhận
                new android.app.AlertDialog.Builder(ManageUser.this)
                        .setMessage(message)
                        .setCancelable(false)
                        .setPositiveButton("Đồng ý", (dialog, id) -> {
                            changeRole(selectedUser);
                        })
                        .setNegativeButton("Thoát", (dialog, id) -> {
                            dialog.dismiss();
                        })
                        .show();
            }
        });
    }

    private void deleteUser() {
        SQLiteDatabase db = myDataBase.open();

        // xóa người dùng trong cơ sở dữ liệu
        String deleteQuery = "DELETE FROM " + MyDataBase.tbUser + " WHERE " + MyDataBase.tbUser_UserId + " = ?";
        db.execSQL(deleteQuery, new Object[]{selectedUserId});

        Toast.makeText(this, "Xóa người dùng thành công!", Toast.LENGTH_SHORT).show();

        db.close();

        userAdapter.clear();
        userAdapter.addAll(myDataBase.getAllUsers());
        userAdapter.notifyDataSetChanged();

        // reset thông tin trên các edt và rdb
        txtTenUser.setText("");
        txtEmailUser.setText("");
        txtPhoneUser.setText("");
        txtPassUser.setText("");
        radioButtonNam.setChecked(false);
        radioButtonNu.setChecked(false);

        selectedUserId = -1;
    }


    private void updateUser() {
        // Kiểm tra xem đã chọn người dùng chưa
        if (selectedUserId == -1) {
            Toast.makeText(this, "Vui lòng chọn người dùng để cập nhật!", Toast.LENGTH_SHORT).show();
            return;
        }

        String name = txtTenUser.getText().toString().trim();
        String email = txtEmailUser.getText().toString().trim();
        String phone = txtPhoneUser.getText().toString().trim();
        String password = txtPassUser.getText().toString().trim();
        String sex = radioButtonNam.isChecked() ? "Nam" : "Nữ";

        if (name.isEmpty() || email.isEmpty() || phone.isEmpty() || password.isEmpty() || sex.isEmpty()) {
            Toast.makeText(this, "Vui lòng điền đầy đủ thông tin", Toast.LENGTH_SHORT).show();
            return;
        }

        SQLiteDatabase db = myDataBase.open();

        // kiểm tra xem email hoặc số điện thoại đã tồn tại trong tài khoản khác chưa
        String checkQuery = "SELECT * FROM " + MyDataBase.tbUser + " WHERE ("
                + MyDataBase.tbUser_Email + " = ? OR " + MyDataBase.tbUser_Phone + " = ?) "
                + "AND " + MyDataBase.tbUser_UserId + " != ?";
        Cursor cursor = db.rawQuery(checkQuery, new String[]{email, phone, String.valueOf(selectedUserId)});

        if (cursor.moveToFirst()) {
            Toast.makeText(this, "Email hoặc số điện thoại đã tồn tại trong tài khoản khác!", Toast.LENGTH_SHORT).show();
            cursor.close();
            db.close();
            return;
        }

        // Thực hiện câu lệnh cập nhật
        String updateQuery = "UPDATE " + MyDataBase.tbUser + " SET "
                + MyDataBase.tbUser_UserName + " = ?, "
                + MyDataBase.tbUser_Password + " = ?, "
                + MyDataBase.tbUser_Email + " = ?, "
                + MyDataBase.tbUser_Phone + " = ?, "
                + MyDataBase.tbUser_Sex + " = ? "
                + "WHERE " + MyDataBase.tbUser_UserId + " = ?";

        db.execSQL(updateQuery, new Object[]{name, password, email, phone, sex, selectedUserId});
        Toast.makeText(this, "Cập nhật người dùng thành công!", Toast.LENGTH_SHORT).show();

        cursor.close();
        db.close();

        // llàm mới danh sách người dùng
        userAdapter.clear();
        userAdapter.addAll(myDataBase.getAllUsers());
        userAdapter.notifyDataSetChanged();
    }


    private void addUser() {
        String name = txtTenUser.getText().toString().trim();
        String email = txtEmailUser.getText().toString().trim();
        String phone = txtPhoneUser.getText().toString().trim();
        String password = txtPassUser.getText().toString().trim();
        String sex = radioButtonNam.isChecked() ? "Nam" : radioButtonNu.isChecked() ? "Nữ" : "";

        if (name.isEmpty() || email.isEmpty() || phone.isEmpty() || password.isEmpty() || sex.isEmpty()) {
            Toast.makeText(this, "Vui lòng điền đầy đủ thông tin", Toast.LENGTH_SHORT).show();
            return;
        }

        SQLiteDatabase db = myDataBase.open();

        String checkQuery = "SELECT * FROM " + MyDataBase.tbUser + " WHERE " + MyDataBase.tbUser_Email + " = ? OR " + MyDataBase.tbUser_Phone + " = ?";
        Cursor cursor = db.rawQuery(checkQuery, new String[]{email, phone});

        if (cursor.moveToFirst()) {
            Toast.makeText(this, "Email hoặc số điện thoại đã tồn tại!", Toast.LENGTH_SHORT).show();
            cursor.close();
            db.close();
            return;
        }

        String insertUserQuery = "INSERT INTO " + MyDataBase.tbUser + " ("
                + MyDataBase.tbUser_UserName + ", "
                + MyDataBase.tbUser_Password + ", "
                + MyDataBase.tbUser_Email + ", "
                + MyDataBase.tbUser_Phone + ", "
                + MyDataBase.tbUser_Sex + ", "
                + MyDataBase.tbUser_Role + ") VALUES (?, ?, ?, ?, ?, 'user')";

        db.execSQL(insertUserQuery, new Object[]{name, password, email, phone, sex});

        Toast.makeText(this, "Thêm người dùng thành công!", Toast.LENGTH_SHORT).show();

        cursor.close();
        db.close();

        userAdapter.clear();
        userAdapter.addAll(myDataBase.getAllUsers());
        userAdapter.notifyDataSetChanged();
    }

    private void changeRole(Userr selectedUser) {
        SQLiteDatabase db = myDataBase.open();

        // kiểm tra nếu vai trò là "user" thì chuyển thành "admin"
        if ("user".equalsIgnoreCase(selectedUser.getRole())) {
            // Cập nhật
            String updateQuery = "UPDATE " + MyDataBase.tbUser + " SET "
                    + MyDataBase.tbUser_Role + " = 'admin' "
                    + "WHERE " + MyDataBase.tbUser_UserId + " = ?";
            db.execSQL(updateQuery, new Object[]{selectedUser.getUserId()});

            Toast.makeText(this, "Đã chuyển vai trò thành admin!", Toast.LENGTH_SHORT).show();
        }
        // Nếu vai trò là "admin", chuyển thành "user"
        else if ("admin".equalsIgnoreCase(selectedUser.getRole())) {
            // Cập nhật vai tro
            String updateQuery = "UPDATE " + MyDataBase.tbUser + " SET "
                    + MyDataBase.tbUser_Role + " = 'user' "
                    + "WHERE " + MyDataBase.tbUser_UserId + " = ?";
            db.execSQL(updateQuery, new Object[]{selectedUser.getUserId()});

            Toast.makeText(this, "Đã chuyển vai trò thành user!", Toast.LENGTH_SHORT).show();
        }

        db.close();

        userAdapter.clear();
        userAdapter.addAll(myDataBase.getAllUsers());
        userAdapter.notifyDataSetChanged();
    }

}