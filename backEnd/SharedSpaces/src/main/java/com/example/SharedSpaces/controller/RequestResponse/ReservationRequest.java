package com.example.SharedSpaces.controller.RequestResponse;

import java.util.Date;
import java.util.Objects;

public class ReservationRequest {

    private int spaceID;
    private String title;
    private Date reservationDateTime;
    private Date startTime;
    private Date endTime;
    private String reservedBy;
    private String responsiblePerson;

    @Override
    public String toString() {
        return "ReservationRequest{" +
                "spaceID=" + spaceID +
                ", title='" + title + '\'' +
                ", reservationDateTime=" + reservationDateTime +
                ", startTime=" + startTime +
                ", endTime=" + endTime +
                ", reservedBy='" + reservedBy + '\'' +
                ", responsiblePerson='" + responsiblePerson + '\'' +
                '}';
    }

    public ReservationRequest(int spaceID, String title, Date reservationDateTime, Date startTime, Date endTime, String reservedBy, String responsiblePerson) {
        this.spaceID = spaceID;
        this.title = title;
        this.reservationDateTime = reservationDateTime;
        this.startTime = startTime;
        this.endTime = endTime;
        this.reservedBy = reservedBy;
        this.responsiblePerson = responsiblePerson;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ReservationRequest that = (ReservationRequest) o;
        return spaceID == that.spaceID && Objects.equals(title, that.title) && Objects.equals(reservationDateTime, that.reservationDateTime) && Objects.equals(startTime, that.startTime) && Objects.equals(endTime, that.endTime) && Objects.equals(reservedBy, that.reservedBy) && Objects.equals(responsiblePerson, that.responsiblePerson);
    }

    @Override
    public int hashCode() {
        return Objects.hash(spaceID, title, reservationDateTime, startTime, endTime, reservedBy, responsiblePerson);
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

    public Date getReservationDateTime() {
        return reservationDateTime;
    }

    public void setReservationDateTime(Date reservationDateTime) {
        this.reservationDateTime = reservationDateTime;
    }

    public Date getStartTime() {
        return startTime;
    }

    public void setStartTime(Date startTime) {
        this.startTime = startTime;
    }

    public Date getEndTime() {
        return endTime;
    }

    public void setEndTime(Date endTime) {
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
}
