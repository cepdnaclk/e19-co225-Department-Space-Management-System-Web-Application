package com.example.SharedSpaces.controller.RequestResponse;

import java.util.Objects;

public class ReservationRequest {

    private int spaceID;
    private String title;
    private String reservationDateTime;
    private int startTime;
    private int endTime;
    private String date;
    private int reservedBy;
    private int responsiblePerson;

    public ReservationRequest(int spaceID, String title, String reservationDateTime, int startTime, String date,
            int endTime, int reservedBy, int responsiblePerson) {
        this.spaceID = spaceID;
        this.title = title;
        this.reservationDateTime = reservationDateTime;
        this.startTime = startTime;
        this.date = date;
        this.endTime = endTime;
        this.reservedBy = reservedBy;
        this.responsiblePerson = responsiblePerson;
    }

    @Override
    public String toString() {
        return "ReservationRequest{" +
                "spaceID=" + spaceID +
                ", title='" + title + '\'' +
                ", reservationDateTime='" + reservationDateTime + '\'' +
                ", startTime=" + startTime +
                ", date='" + date + '\'' +
                ", endTime=" + endTime +
                ", reservedBy='" + reservedBy + '\'' +
                ", responsiblePerson='" + responsiblePerson + '\'' +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o)
            return true;
        if (o == null || getClass() != o.getClass())
            return false;
        ReservationRequest that = (ReservationRequest) o;
        return spaceID == that.spaceID && startTime == that.startTime && endTime == that.endTime
                && Objects.equals(title, that.title) && Objects.equals(reservationDateTime, that.reservationDateTime)
                && Objects.equals(date, that.date) && Objects.equals(reservedBy, that.reservedBy)
                && Objects.equals(responsiblePerson, that.responsiblePerson);
    }

    @Override
    public int hashCode() {
        return Objects.hash(spaceID, title, reservationDateTime, startTime, date, endTime, reservedBy,
                responsiblePerson);
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

    public String getReservationDateTime() {
        return reservationDateTime;
    }

    public void setReservationDateTime(String reservationDateTime) {
        this.reservationDateTime = reservationDateTime;
    }

    public int getStartTime() {
        return startTime;
    }

    public void setStartTime(int startTime) {
        this.startTime = startTime;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public int getEndTime() {
        return endTime;
    }

    public void setEndTime(int endTime) {
        this.endTime = endTime;
    }

    public int getReservedBy() {
        return reservedBy;
    }

    public void setReservedBy(int reservedBy) {
        this.reservedBy = reservedBy;
    }

    public int getResponsiblePerson() {
        return responsiblePerson;
    }

    public void setResponsiblePerson(int responsiblePerson) {
        this.responsiblePerson = responsiblePerson;
    }
}
