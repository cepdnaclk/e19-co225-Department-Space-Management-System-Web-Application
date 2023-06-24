package com.example.SharedSpaces.controller.RequestResponse;

public class ReservationResponse {

    private int spaceID;
    private String title;
    private String date;
    private int startTime;
    private int endTime;
    private String reservedBy;
    private String responsiblePerson;
    private String status;

    public ReservationResponse() {

    }

    public ReservationResponse(int spaceID, String title, String date, int startTime, int endTime, String reservedBy,
            String responsiblePerson) {
        this.spaceID = spaceID;
        this.title = title;
        this.date = date;
        this.startTime = startTime;
        this.endTime = endTime;
        this.reservedBy = reservedBy;
        this.responsiblePerson = responsiblePerson;
    }

    public int getSpaceID() {
        return spaceID;
    }

    public void setSpaceID(int spaceID) {
        this.spaceID = spaceID;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public int getStartTime() {
        return startTime;
    }

    public void setStartTime(int startTime) {
        this.startTime = startTime;
    }

    public int getEndTime() {
        return endTime;
    }

    public void setEndTime(int endTime) {
        this.endTime = endTime;
    }

    public String getReservedBy() {
        return reservedBy;
    }

    public void setReservedBy(String reservedBy) {
        this.reservedBy = reservedBy;
    }

    public String getResponsiblePerson() {
        return responsiblePerson;
    }

    public void setResponsiblePerson(String responsiblePerson) {
        this.responsiblePerson = responsiblePerson;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    @Override
    public String toString() {
        return "ReservationResponse{" +
                "spaceID=" + spaceID +
                ", title='" + title + '\'' +
                ", date='" + date + '\'' +
                ", startTime=" + startTime +
                ", endTime=" + endTime +
                ", reservedBy='" + reservedBy + '\'' +
                ", responsiblePerson='" + responsiblePerson + '\'' +
                '}';
    }
}
