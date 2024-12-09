package com.example.coachticketbookingapp.ui;

public class Userr {
    private int userId;
    private String userName;
    private String email;
    private String password;
    private String phone;
    private String sex;
    private String role;

    // Constructor không tham số
    public Userr() {
    }

    // Constructor có tham số
    public Userr(int userId, String userName, String email, String phone, String sex, String role, String password) {
        this.userId = userId;
        this.userName = userName;
        this.email = email;
        this.password = password;
        this.phone = phone;
        this.sex = sex;
        this.role = role;
    }

    // Getter và Setter cho userId
    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    // Getter và Setter cho userName
    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    // Getter và Setter cho email
    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }


    // Getter và Setter cho phone
    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    // Getter và Setter cho sex
    public String getSex() {
        return sex;
    }

    public void setSex(String sex) {
        this.sex = sex;
    }

    // Getter và Setter cho role
    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }

    // Phương thức toString để hiển thị thông tin người dùng
    @Override
    public String toString() {
        return "User{" +
                "userId=" + userId +
                ", userName='" + userName + '\'' +
                ", email='" + email + '\'' +
                ", password='" + password + '\'' +
                ", phone='" + phone + '\'' +
                ", sex='" + sex + '\'' +
                ", role='" + role + '\'' +
                '}';
    }
}
