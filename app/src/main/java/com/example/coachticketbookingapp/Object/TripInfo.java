package com.example.coachticketbookingapp.Object;

import java.io.Serializable;

public class TripInfo implements Serializable {
    private int tripID;
    private int coachID;
    private String firstLocation;
    private String secondLocation;
    private String departure;
    private String destination;
    private String departureTime;
    private String departureDate;
    private String destinationTime;
    private String destinationDate;
    private int ticketAvailable;
    private double price;
    private int distance;

    public TripInfo(int tripID, int coachID, String firstLocation, String secondLocation, String departure, String destination, String departureTime, String departureDate, String destinationTime, String destinationDate, int ticketAvailable, double price, int distance) {
        this.tripID = tripID;
        this.coachID = coachID;
        this.firstLocation = firstLocation;
        this.secondLocation = secondLocation;
        this.departure = departure;
        this.destination = destination;
        this.departureTime = departureTime;
        this.departureDate = departureDate;
        this.destinationTime = destinationTime;
        this.destinationDate = destinationDate;
        this.ticketAvailable = ticketAvailable;
        this.price = price;
        this.distance=distance;
    }

    public int getTripID() {
        return tripID;
    }

    public void setTripID(int tripID) {
        this.tripID = tripID;
    }

    public int getCoachID() {
        return coachID;
    }

    public void setCoachID(int coachID) {
        this.coachID = coachID;
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

    public String getDestination() {
        return destination;
    }

    public void setDestination(String destination) {
        this.destination = destination;
    }

    public String getDeparture() {
        return departure;
    }

    public void setDeparture(String departure) {
        this.departure = departure;
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

    public int getTicketAvailable() {
        return ticketAvailable;
    }

    public void setTicketAvailable(int ticketAvailable) {
        this.ticketAvailable = ticketAvailable;
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
}