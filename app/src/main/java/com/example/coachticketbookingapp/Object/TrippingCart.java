package com.example.coachticketbookingapp.Object;

public class TrippingCart {
    private int userID;
    private String bookingDate;
    private int TripID;
    private int ticketQuantity;
    private double totalPrice;

    public TrippingCart(int userID, String bookingDate, int tripID, int ticketQuantity, double totalPrice) {
        this.userID = userID;
        this.bookingDate = bookingDate;
        TripID = tripID;
        this.ticketQuantity = ticketQuantity;
        this.totalPrice = totalPrice;
    }

    public int getUserID() {
        return userID;
    }

    public void setUserID(int userID) {
        this.userID = userID;
    }

    public String getBookingDate() {
        return bookingDate;
    }

    public void setBookingDate(String bookingDate) {
        this.bookingDate = bookingDate;
    }

    public int getTripID() {
        return TripID;
    }

    public void setTripID(int tripID) {
        TripID = tripID;
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
