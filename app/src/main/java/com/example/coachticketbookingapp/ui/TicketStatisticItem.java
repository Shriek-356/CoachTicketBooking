package com.example.coachticketbookingapp.ui;

public class TicketStatisticItem {
    private String userName;
    private String tripDetails;
    private String bookingDate;
    private String email; // Thêm email
    private String phone; // Thêm số điện thoại

    public TicketStatisticItem(String userName, String tripDetails, String bookingDate, String email, String phone) {
        this.userName = userName;
        this.tripDetails = tripDetails;
        this.bookingDate = bookingDate;
        this.email = email;
        this.phone = phone;
    }

    // Getter và setter cho userName
    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    // Getter và setter cho tripDetails
    public String getTripDetails() {
        return tripDetails;
    }

    public void setTripDetails(String tripDetails) {
        this.tripDetails = tripDetails;
    }

    // Getter và setter cho bookingDate
    public String getBookingDate() {
        return bookingDate;
    }

    public void setBookingDate(String bookingDate) {
        this.bookingDate = bookingDate;
    }

    // Getter và setter cho email
    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    // Getter và setter cho phone
    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }
}
