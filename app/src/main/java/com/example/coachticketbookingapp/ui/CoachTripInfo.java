package com.example.coachticketbookingapp.ui;

public class CoachTripInfo {
    private int coachID; // Thêm thuộc tính coachID
    private String coachBrand;
    private int totalSeat;
    private String licensePlate;
    private String type;
    private String departure;
    private String destination;
    private String departureTime;
    private String departureDate;

    // Constructor
    public CoachTripInfo(int coachID, String coachBrand, int totalSeat, String licensePlate, String type,
                         String departure, String destination, String departureTime, String departureDate) {
        this.coachID = coachID; // Khởi tạo coachID
        this.coachBrand = coachBrand;
        this.totalSeat = totalSeat;
        this.licensePlate = licensePlate;
        this.type = type;
        this.departure = departure;
        this.destination = destination;
        this.departureTime = departureTime;
        this.departureDate = departureDate;
    }

    // Getters and setters
    public int getCoachID() {
        return coachID; // Lấy coachID
    }

    public void setCoachID(int coachID) {
        this.coachID = coachID; // Đặt coachID
    }

    public String getCoachBrand() {
        return coachBrand;
    }

    public void setCoachBrand(String coachBrand) {
        this.coachBrand = coachBrand;
    }

    public int getTotalSeat() {
        return totalSeat;
    }

    public void setTotalSeat(int totalSeat) {
        this.totalSeat = totalSeat;
    }

    public String getLicensePlate() {
        return licensePlate;
    }

    public void setLicensePlate(String licensePlate) {
        this.licensePlate = licensePlate;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
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
}


