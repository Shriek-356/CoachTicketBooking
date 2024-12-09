package com.example.coachticketbookingapp.ui;

public class BookingStatisticsItem {
    private String bookingDate;
    private int totalTickets;

    public BookingStatisticsItem(String bookingDate, int totalTickets) {
        this.bookingDate = bookingDate;
        this.totalTickets = totalTickets;
    }

    public String getBookingDate() {
        return bookingDate;
    }

    public void setBookingDate(String bookingDate) {
        this.bookingDate = bookingDate;
    }

    public int getTotalTickets() {
        return totalTickets;
    }

    public void setTotalTickets(int totalTickets) {
        this.totalTickets = totalTickets;
    }
}

