package com.example.coachticketbookingapp.ui;

public class TripInfoCoach {
    private int coachID;
    private String departure;
    private String destination;
    private String departureTime;
    private String departureDate;

    public TripInfoCoach(int coachID, String departure, String destination, String departureTime, String departureDate) {
        this.coachID = coachID;
        this.departure = departure;
        this.destination = destination;
        this.departureTime = departureTime;
        this.departureDate = departureDate;
    }

    public int getCoachID() {
        return coachID;
    }

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

