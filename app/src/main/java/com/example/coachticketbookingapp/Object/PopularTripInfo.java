package com.example.coachticketbookingapp.Object;

public class PopularTripInfo {

    // Các thuộc tính của chuyến đi
    private int tripId;
    private String firstLocation;
    private String secondLocation;
    private String departure;
    private String destination;
    private int ratingCount;
    private float averageRate;

    public PopularTripInfo() {}

    // Constructor với tất cả các tham số
    public PopularTripInfo(int tripId, String firstLocation, String secondLocation,
                           String departure, String destination, int ratingCount) {
        this.tripId = tripId;
        this.firstLocation = firstLocation;
        this.secondLocation = secondLocation;
        this.departure = departure;
        this.destination = destination;
        this.ratingCount = ratingCount;
    }

    // Getters và Setters cho các thuộc tính
    public int getTripId() {
        return tripId;
    }

    public void setTripId(int tripId) {
        this.tripId = tripId;
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

    public int getRatingCount() {
        return ratingCount;
    }

    public void setRatingCount(int ratingCount) {
        this.ratingCount = ratingCount;
    }

    public float getAverageRate() {
        return averageRate;
    }

    public void setAverageRate(float averageRate) {
        this.averageRate = averageRate;
    }

    // Phương thức hiển thị thông tin chuyến đi (optional)
    @Override
    public String toString() {
        return "PopularTripInfo{" +
                "tripId=" + tripId +
                ", firstLocation='" + firstLocation + '\'' +
                ", secondLocation='" + secondLocation + '\'' +
                ", departure='" + departure + '\'' +
                ", destination='" + destination + '\'' +
                ", ratingCount=" + ratingCount +
                ", averageRate=" + averageRate +
                '}';
    }
}
