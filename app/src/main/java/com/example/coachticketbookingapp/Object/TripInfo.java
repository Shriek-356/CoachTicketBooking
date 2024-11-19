package com.example.coachticketbookingapp.Object;

public class TripInfo {
    private int tripID;
    private int coachID;
    private String firstLocation;
    private String secondLocation;
    private String departure;
    private String destination;
    private String depatureTime;
    private String depatetureDate;
    private String destinationTime;
    private String destinationDate;
    private int ticketAvailable;
    private double price;

    public TripInfo(int tripID, double price, int ticketAvailable, String destinationDate, String destinationTime, String depatetureDate, String depatureTime,String departure, String destination,String firstLocation, String secondLocation, int coachID) {
        this.tripID = tripID;
        this.price = price;
        this.ticketAvailable = ticketAvailable;
        this.destinationDate = destinationDate;
        this.destinationTime = destinationTime;
        this.depatetureDate = depatetureDate;
        this.depatureTime = depatureTime;
        this.destination = destination;
        this.secondLocation = secondLocation;
        this.departure = departure;
        this.firstLocation = firstLocation;
        this.coachID = coachID;
    }

    public int getTripID() {
        return tripID;
    }

    public void setTripID(int tripID) {
        this.tripID = tripID;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public int getTicketAvailable() {
        return ticketAvailable;
    }

    public void setTicketAvailable(int ticketAvailable) {
        this.ticketAvailable = ticketAvailable;
    }

    public String getDepatetureDate() {
        return depatetureDate;
    }

    public void setDepatetureDate(String depatetureDate) {
        this.depatetureDate = depatetureDate;
    }

    public String getDestinationTime() {
        return destinationTime;
    }

    public void setDestinationTime(String destinationTime) {
        this.destinationTime = destinationTime;
    }

    public String getDestinationDate() {
        return destinationDate;
    }

    public void setDestinationDate(String destinationDate) {
        this.destinationDate = destinationDate;
    }

    public String getDepatureTime() {
        return depatureTime;
    }

    public void setDepatureTime(String depatureTime) {
        this.depatureTime = depatureTime;
    }

    public String getDestination() {
        return destination;
    }

    public void setDestination(String destination) {
        this.destination = destination;
    }

    public String getSecondLocation() {
        return secondLocation;
    }

    public void setSecondLocation(String secondLocation) {
        this.secondLocation = secondLocation;
    }

    public String getFirstLocation() {
        return firstLocation;
    }

    public void setFirstLocation(String firstLocation) {
        this.firstLocation = firstLocation;
    }

    public int getCoachID() {
        return coachID;
    }

    public void setCoachID(int coachID) {
        this.coachID = coachID;
    }

    public String getDeparture() {
        return departure;
    }

    public void setDeparture(String departure) {
        this.departure = departure;
    }
}
