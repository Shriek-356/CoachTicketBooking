package com.example.coachticketbookingapp.ui;

public class Trip {
    private int tripID;
    private String departure;
    private String destination;
    private String departureTime;
    private String departureDate;

    // Constructor
    public Trip(String departure, String destination, String departureTime, String departureDate) {
        this.departure = departure;
        this.destination = destination;
        this.departureTime = departureTime;
        this.departureDate = departureDate;
    }

    public int getTripID() {
        return tripID;
    }

    public void setTripID(int tripID) {
        this.tripID = tripID;
    }

    // Getters
    public String getDeparture() {
        return departure;
    }

    public String getDestination() {
        return destination;
    }

    public String getDepartureTime() {
        return departureTime;
    }

    public String getDepartureDate() {
        return departureDate;
    }
}

