package com.example.SharedSpaces.models;

import jakarta.persistence.*;

import java.util.Date;
import java.util.Objects;

@Entity
@Table(name = "reservation")
public class Reservation {

    public Reservation(){

    }

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;

    private int spaceID;
    private String title;
    private Date reservationDateTime;
    private Date startDateTime;
    private Date endDateTime;
    private long reservedById;
    private long responsiblePersonId;

    public Reservation(int spaceID, String title, Date reservationDateTime, Date startDateTime, Date endDateTime, long reservedById, long responsiblePersonId) {
        this.spaceID = spaceID;
        this.title = title;
        this.reservationDateTime = reservationDateTime;
        this.startDateTime = startDateTime;
        this.endDateTime = endDateTime;
        this.reservedById = reservedById;
        this.responsiblePersonId = responsiblePersonId;
    }

    @Override
    public String toString() {
        return "Reservation{" +
                "id=" + id +
                ", spaceID=" + spaceID +
                ", title='" + title + '\'' +
                ", reservationDateTime=" + reservationDateTime +
                ", startDateTime=" + startDateTime +
                ", endDateTime=" + endDateTime +
                ", reservedById=" + reservedById +
                ", responsiblePersonId=" + responsiblePersonId +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Reservation that = (Reservation) o;
        return id == that.id && spaceID == that.spaceID && reservedById == that.reservedById && responsiblePersonId == that.responsiblePersonId && Objects.equals(title, that.title) && Objects.equals(reservationDateTime, that.reservationDateTime) && Objects.equals(startDateTime, that.startDateTime) && Objects.equals(endDateTime, that.endDateTime);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, spaceID, title, reservationDateTime, startDateTime, endDateTime, reservedById, responsiblePersonId);
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
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

    public Date getStartDateTime() {
        return startDateTime;
    }

    public void setStartDateTime(Date startDateTime) {
        this.startDateTime = startDateTime;
    }

    public Date getEndDateTime() {
        return endDateTime;
    }

    public void setEndDateTime(Date endDateTime) {
        this.endDateTime = endDateTime;
    }

    public long getReservedById() {
        return reservedById;
    }

    public void setReservedById(long reservedById) {
        this.reservedById = reservedById;
    }

    public long getResponsiblePersonId() {
        return responsiblePersonId;
    }

    public void setResponsiblePersonId(long responsiblePersonId) {
        this.responsiblePersonId = responsiblePersonId;
    }
}
