package com.example.coachticketbookingapp.Object;

import java.io.Serializable;

public class TripBookingDetails implements Serializable {
    private int tripID;
    private int tripBookingDetailsID;
    private String departure;
    private String destination;
    private String firstLocation;
    private String secondLocation;
    private String bookingDate;
    private String departureTime;
    private String departureDate;
    private double price;
    private int distance;
    private int ticketQuantity;
    private double totalPrice;

    public TripBookingDetails(int tripID, int tripBookingDetailsID,String departure,String destination, String firstLocation, String secondLocation, String bookingDate, String departureTime, String departureDate, double price, int distance, int ticketQuantity, double totalPrice) {
        this.tripID = tripID;
        this.tripBookingDetailsID = tripBookingDetailsID;
        this.departure=departure;
        this.destination=destination;
        this.firstLocation = firstLocation;
        this.secondLocation = secondLocation;
        this.bookingDate = bookingDate;
        this.departureTime = departureTime;
        this.departureDate = departureDate;
        this.price = price;
        this.distance = distance;
        this.ticketQuantity = ticketQuantity;
        this.totalPrice = totalPrice;
    }

    public String getDeparture() {
        return departure;
    }

    public void setDeparture(String departure) {
        this.departure = departure;
    }

    public String getDestination() {
        return destination;
    }

    public void setDestination(String destination) {
        this.destination = destination;
    }

    public int getTripID() {
        return tripID;
    }

    public void setTripID(int tripID) {
        this.tripID = tripID;
    }

    public int getTripBookingDetailsID() {
        return tripBookingDetailsID;
    }

    public void setTripBookingDetailsID(int tripBookingDetailsID) {
        this.tripBookingDetailsID = tripBookingDetailsID;
    }

    public String getFirstLocation() {
        return firstLocation;
    }

    public void setFirstLocation(String firstLocation) {
        this.firstLocation = firstLocation;
    }

    public String getSecondLocation() {
        return secondLocation;
    }

    public void setSecondLocation(String secondLocation) {
        this.secondLocation = secondLocation;
    }

    public String getBookingDate() {
        return bookingDate;
    }

    public void setBookingDate(String bookingDate) {
        this.bookingDate = bookingDate;
    }

    public String getDepartureTime() {
        return departureTime;
    }

    public void setDepartureTime(String departureTime) {
        this.departureTime = departureTime;
    }

    public String getDepartureDate() {
        return departureDate;
    }

    public void setDepartureDate(String departureDate) {
        this.departureDate = departureDate;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public int getDistance() {
        return distance;
    }

    public void setDistance(int distance) {
        this.distance = distance;
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
