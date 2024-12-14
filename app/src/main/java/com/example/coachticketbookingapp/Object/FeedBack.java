package com.example.coachticketbookingapp.Object;

public class FeedBack {
    private int feedBackId;
    private String content;
    private int userId;
    private int coachId;
    private int tripId;
    private float rate;

    public FeedBack(int feedBackId, String content, int userId, int coachId, int tripId, float rate) {
        this.feedBackId = feedBackId;
        this.content = content;
        this.userId = userId;
        this.coachId = coachId;
        this.tripId = tripId;
        this.rate = rate;
    }

    public int getFeedBackId() {
        return feedBackId;
    }

    public void setFeedBackId(int feedBackId) {
        this.feedBackId = feedBackId;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public int getCoachId() {
        return coachId;
    }

    public void setCoachId(int coachId) {
        this.coachId = coachId;
    }

    public int getTripId() {
        return tripId;
    }

    public void setTripId(int tripId) {
        this.tripId = tripId;
    }

    public float getRate() {
        return rate;
    }

    public void setRate(float rate) {
        this.rate = rate;
    }
}
