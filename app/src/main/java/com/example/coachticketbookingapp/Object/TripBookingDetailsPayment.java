package com.example.coachticketbookingapp.Object;

//Object nay de luu table TripBookingDetails khi nguoi dung xac nhan dat ve va sau do them vao db

import java.io.Serializable;

public class TripBookingDetailsPayment implements Serializable {
    private int tripBookingDetailsID;
    private int userId;
    private int tripId;
    private String bookingDate;
    private int ticketQuantity;
    private double totalPrice;
    private String fullName;
    private String phoneNumber;
    private String email;

    public TripBookingDetailsPayment(int userId, int tripId, String bookingDate, int ticketQuantity, double totalPrice,String fullName,String phoneNumber,String email) {
        this.userId = userId;
        this.tripId = tripId;
        this.bookingDate = bookingDate;
        this.ticketQuantity = ticketQuantity;
        this.totalPrice = totalPrice;
        this.fullName=fullName;
        this.phoneNumber=phoneNumber;
        this.email=email;
    }

    public String getFullName() {
        return fullName;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public int getTripBookingDetailsID() {
        return tripBookingDetailsID;
    }

    public void setTripBookingDetailsID(int tripBookingDetailsID) {
        this.tripBookingDetailsID = tripBookingDetailsID;
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public int getTripId() {
        return tripId;
    }

    public void setTripId(int tripId) {
        this.tripId = tripId;
    }

    public String getBookingDate() {
        return bookingDate;
    }

    public void setBookingDate(String bookingDate) {
        this.bookingDate = bookingDate;
    }

    public int getTicketQuantity() {
        return ticketQuantity;
    }

    public void setTicketQuantity(int ticketQuantity) {
        this.ticketQuantity = ticketQuantity;
    }

    public double getTotalPrice() {
        return totalPrice;
    }

    public void setTotalPrice(double totalPrice) {
        this.totalPrice = totalPrice;
    }
}
