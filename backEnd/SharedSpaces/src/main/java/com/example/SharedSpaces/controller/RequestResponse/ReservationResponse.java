package com.example.SharedSpaces.controller.RequestResponse;

public class ReservationResponse {

    // The ID of the reservation
    private long id;

    // The ID of the space being reserved
    private int spaceId;

    // The title or name of the reservation
    private String title;

    // The date of the reservation (in yyyy-mm-dd format)
    private String date;

    // The start time of the reservation (in minutes since midnight)
    private int startTime;

    // The end time of the reservation (in minutes since midnight)
    private int endTime;

    // The name of the person making the reservation
    private String reservedBy;

    // The name of the person responsible for the reservation
    private String responsiblePerson;

    // The status of the reservation (e.g. "confirmed", "pending", "cancelled")
    private String status;

    public ReservationResponse() {

    }

    // Constructor for creating a new reservation response object
    public ReservationResponse(int spaceId, String title, String date, int startTime, int endTime, String reservedBy,
                               String responsiblePerson) {
        this.spaceId = spaceId;
        this.title = title;
        this.date = date;
        this.startTime = startTime;
        this.endTime = endTime;
        this.reservedBy = reservedBy;
        this.responsiblePerson = responsiblePerson;
    }

    // Getter and setter methods for spaceId
    public int getSpaceId() {
        return spaceId;
    }

    public void setSpaceId(int spaceId) {
        this.spaceId = spaceId;
    }

    // Getter and setter methods for title
    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    // Getter and setter methods for date
    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    // Getter and setter methods for startTime
    public int getStartTime() {
        return startTime;
    }

    public void setStartTime(int startTime) {
        this.startTime = startTime;
    }

    // Getter and setter methods for endTime
    public int getEndTime() {
        return endTime;
    }

    public void setEndTime(int endTime) {
        this.endTime = endTime;
    }

    // Getter and setter methods for reservedBy
    public String getReservedBy() {
        return reservedBy;
    }

    public void setReservedBy(String reservedBy) {
        this.reservedBy = reservedBy;
    }

    // Getter and setter methods for responsiblePerson
    public String getResponsiblePerson() {
        return responsiblePerson;
    }

    public void setResponsiblePerson(String responsiblePerson) {
        this.responsiblePerson = responsiblePerson;
    }

    // Getter and setter methods for status
    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    // Getter and setter methods for id
    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    // Override the toString() method to return a string representation of the object
    @Override
    public String toString() {
        return "ReservationResponse{" +
                "id=" + id +
                ", spaceId=" + spaceId +
                ", title='" + title + '\'' +
                ", date='" + date + '\'' +
                ", startTime=" + startTime +
                ", endTime=" + endTime +
                ", reservedBy='" + reservedBy + '\'' +
                ", responsiblePerson='" + responsiblePerson + '\'' +
                ", status='" + status + '\'' +
                '}';
    }
}

