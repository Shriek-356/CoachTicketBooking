package com.example.coachticketbookingapp.ui;

public class BusTripInfo {
    private String coachID;      // Mã định danh của xe khách
    private String busBrand;
    private int totalSeat;
    private String licensePlate;
    private String type;
    private String departure;
    private String destination;
    private String departureTime;
    private String departureDate;

    // Constructor
    public BusTripInfo(String coachID, String busBrand, int totalSeat, String licensePlate, String type,
                       String departure, String destination, String departureTime, String departureDate) {
        this.coachID = coachID;
        this.busBrand = busBrand;
        this.totalSeat = totalSeat;
        this.licensePlate = licensePlate;
        this.type = type;
        this.departure = departure;
        this.destination = destination;
        this.departureTime = departureTime;
        this.departureDate = departureDate;
    }

    // Getters
    public String getCoachID() { return coachID; }
    public String getBusBrand() { return busBrand; }
    public int getTotalSeat() { return totalSeat; }
    public String getLicensePlate() { return licensePlate; }
    public String getType() { return type; }
    public String getDeparture() { return departure; }
    public String getDestination() { return destination; }
    public String getDepartureTime() { return departureTime; }
    public String getDepartureDate() { return departureDate; }

    // Setters (optional nếu cần thay đổi)
    public void setCoachID(String coachID) { this.coachID = coachID; }
    public void setBusBrand(String busBrand) { this.busBrand = busBrand; }
    public void setTotalSeat(int totalSeat) { this.totalSeat = totalSeat; }
    public void setLicensePlate(String licensePlate) { this.licensePlate = licensePlate; }
    public void setType(String type) { this.type = type; }
    public void setDeparture(String departure) { this.departure = departure; }
    public void setDestination(String destination) { this.destination = destination; }
    public void setDepartureTime(String departureTime) { this.departureTime = departureTime; }
    public void setDepartureDate(String departureDate) { this.departureDate = departureDate; }
}
