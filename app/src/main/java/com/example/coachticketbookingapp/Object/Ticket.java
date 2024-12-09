package com.example.coachticketbookingapp.Object;

public class Ticket {
    private String ticketID;
    private String bookingDate;
    private String price;
    private String location1;
    private String location2;

    public Ticket(String ticketID, String price, String bookingDate, String departure, String destination) {
        this.ticketID = ticketID;
        this.price = price;
        this.bookingDate = bookingDate;
        this.location1= "Vé Xe " +departure+" - " +destination;
        this.location2 = departure + " -> " +destination;
    }

    public String getTicketID() {
        return ticketID;
    }

    public void setTicketID(String ticketID) {
        this.ticketID = ticketID;
    }

    public String getBookingDate() {
        return bookingDate;
    }

    public void setBookingDate(String bookingDate) {
        this.bookingDate = bookingDate;
    }

    public String getPrice() {
        return price;
    }

    public void setPrice(String price) {
        this.price = price;
    }

    public String getLocation1() {
        return location1;
    }

    public void setLocation1(String location1) {
        this.location1 = location1;
    }

    public String getLocation2() {
        return location2;
    }

    public void setLocation2(String location2) {
        this.location2 = location2;
    }
}
