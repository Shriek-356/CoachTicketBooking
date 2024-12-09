package com.example.coachticketbookingapp.ui;

public class TripStatistic {
    private String departure;
    private String destination;
    private int totalTickets;

    public TripStatistic(String departure, String destination, int totalTickets) {
        this.departure = departure;
        this.destination = destination;
        this.totalTickets = totalTickets;
    }

    public String getDeparture() {
        return departure;
    }

    public String getDestination() {
        return destination;
    }

    public int getTotalTickets() {
        return totalTickets;
    }
}
