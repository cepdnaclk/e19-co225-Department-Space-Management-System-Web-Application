package com.example.SharedSpaces.controller.RequestResponse;

import java.util.Objects;

public class ReservationRequest {

    // The ID of the space being reserved
    private int spaceID;

    // The title or name of the reservation
    private String title;

    // The date and time of the reservation
    private String reservationDateTime;

    // The start time of the reservation (in minutes since midnight)
    private int startTime;

    // The end time of the reservation (in minutes since midnight)
    private int endTime;

    // The date of the reservation (in yyyy-mm-dd format)
    private String date;

    // The user ID of the person making the reservation
    private int reservedBy;

    // The user ID of the person responsible for the reservation
    private int responsiblePerson;

    // The ID of the waiting list entry associated with the reservation (if any)
    private int waitingId;

    public ReservationRequest(int spaceID, String title, String reservationDateTime, int startTime, String date,
                              int endTime, int reservedBy, int responsiblePerson, int waitingId) {
        this.spaceID = spaceID;
        this.title = title;
        this.reservationDateTime = reservationDateTime;
        this.startTime = startTime;
        this.date = date;
        this.endTime = endTime;
        this.reservedBy = reservedBy;
        this.responsiblePerson = responsiblePerson;
        this.waitingId = waitingId;
    }

    // Getter and setter methods for spaceID
    public int getSpaceID() {
        return spaceID;
    }

    public void setSpaceID(int spaceID) {
        this.spaceID = spaceID;
    }

    // Getter and setter methods for title
    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    // Getter and setter methods for reservationDateTime
    public String getReservationDateTime() {
        return reservationDateTime;
    }

    public void setReservationDateTime(String reservationDateTime) {
        this.reservationDateTime = reservationDateTime;
    }

    // Getter and setter methods for startTime
    public int getStartTime() {
        return startTime;
    }

    public void setStartTime(int startTime) {
        this.startTime = startTime;
    }

    // Getter and setter methods for date
    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    // Getter and setter methods for endTime
    public int getEndTime() {
        return endTime;
    }

    public void setEndTime(int endTime) {
        this.endTime = endTime;
    }

    // Getter and setter methods for reservedBy
    public int getReservedBy() {
        return reservedBy;
    }

    public void setReservedBy(int reservedBy) {
        this.reservedBy = reservedBy;
    }

    // Getter and setter methods for responsiblePerson
    public int getResponsiblePerson() {
        return responsiblePerson;
    }

    public void setResponsiblePerson(int responsiblePerson) {
        this.responsiblePerson = responsiblePerson;
    }

    // Getter and setter methods for waitingId
    public int getWaitingId() {
        return waitingId;
    }

    public void setWaitingId(int waitingId) {
        this.waitingId = waitingId;
    }

    // Override the equals() method to compare objects based on their fields
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ReservationRequest that = (ReservationRequest) o;
        return spaceID == that.spaceID && startTime == that.startTime && endTime == that.endTime && reservedBy == that.reservedBy && responsiblePerson == that.responsiblePerson && waitingId == that.waitingId && Objects.equals(title, that.title) && Objects.equals(reservationDateTime, that.reservationDateTime) && Objects.equals(date, that.date);
    }

    // Override the hashCode() method to generate a hash code based on the object's fields
    @Override
    public int hashCode() {
        return Objects.hash(spaceID, title, reservationDateTime, startTime, endTime, date, reservedBy, responsiblePerson, waitingId);
    }

    // Override the toString() method to return a string representation of the object
    @Override
    public String toString() {
        return "ReservationRequest{" +
                "spaceID=" + spaceID +
                ", title='" + title + '\'' +
                ", reservationDateTime='" + reservationDateTime + '\'' +
                ", startTime=" + startTime +
                ", endTime=" + endTime +
                ", date='" + date + '\'' +
                ", reservedBy=" + reservedBy +
                ", responsiblePerson=" + responsiblePerson +
                ", waitingId=" +waitingId +
                '}';
    }
}

