package com.example.coachticketbookingapp.Object;

import java.io.Serializable;

public class User implements Serializable {
    private int userID;
    private String sex;
    private String phone;
    private String email;
    private String userName;
    private String passWord;
    private String role;


    public User(int userID, String role, String passWord, String phone, String sex, String email, String userName) {
        this.userID = userID;
        this.role = role;
        this.passWord = passWord;
        this.phone = phone;
        this.sex = sex;
        this.email = email;
        this.userName = userName;
    }

    public int getUserID() {
        return userID;
    }

    public void setUserID(int userID) {
        this.userID = userID;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getPassWord() {
        return passWord;
    }

    public void setPassWord(String passWord) {
        this.passWord = passWord;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getSex() {
        return sex;
    }

    public void setSex(String sex) {
        this.sex = sex;
    }
}
