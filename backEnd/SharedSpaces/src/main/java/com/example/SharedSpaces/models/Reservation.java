package com.example.SharedSpaces.models;

import jakarta.persistence.*;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Objects;

@Entity
@Table(name = "reservation")
// This class represents a reservation made by a user for a shared space.
public class Reservation {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;
    private int spaceID;
    private String title;
    private Date reservationDateTime;
    private Date startDateTime;
    private Date endDateTime;
    private String date;
    private long reservedById;
    private long responsiblePersonId;

    public Reservation() {

    }

    // Constructor that takes the space ID, reservation title, reservation date and time, start and end times, and user IDs for the user who reserved the space and the responsible person.
    public Reservation(int spaceID, String title, Date reservationDateTime, Date startDateTime, Date endDateTime,
                       long reservedById, long responsiblePersonId) {
        this.spaceID = spaceID;
        this.title = title;
        this.reservationDateTime = reservationDateTime;
        this.startDateTime = startDateTime;
        this.endDateTime = endDateTime;
        this.reservedById = reservedById;
        this.responsiblePersonId = responsiblePersonId;
        DateFormat dateFormat = new SimpleDateFormat("dd-MM-yyyy");
        this.date = dateFormat.format(this.startDateTime);
    }

    // Constructor that takes a Waiting object to create a Reservation object.
    public Reservation(Waiting waiting) {
        this.spaceID = waiting.getSpaceID();
        this.title = waiting.getTitle();
        this.reservationDateTime = waiting.getReservationDateTime();
        this.startDateTime = waiting.getStartDateTime();
        this.endDateTime = waiting.getEndDateTime();
        this.reservedById = waiting.getReservedById();
        this.responsiblePersonId = waiting.getResponsiblePersonId();
        DateFormat dateFormat = new SimpleDateFormat("dd-MM-yyyy");
        this.date = dateFormat.format(this.startDateTime);
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
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
        DateFormat dateFormat = new SimpleDateFormat("dd-MM-yyyy");
        this.date = dateFormat.format(this.startDateTime);
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

    @Override
    public String toString() {
        return "Reservation{" +
                "id=" + id +
                ", spaceID=" + spaceID +
                ", title='" + title + '\'' +
                ", reservationDateTime=" + reservationDateTime +
                ", startDateTime=" + startDateTime +
                ", endDateTime=" + endDateTime +
                ", date=" + date +
                ", reservedById=" + reservedById +
                ", responsiblePersonId=" + responsiblePersonId +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o)
            return true;
        if (o == null || getClass() != o.getClass())
            return false;
        Reservation that = (Reservation) o;
        return id == that.id && spaceID == that.spaceID && reservedById == that.reservedById
                && responsiblePersonId == that.responsiblePersonId && Objects.equals(title, that.title)
                && Objects.equals(reservationDateTime, that.reservationDateTime)
                && Objects.equals(startDateTime, that.startDateTime) && Objects.equals(endDateTime, that.endDateTime);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, spaceID, title, reservationDateTime, startDateTime, endDateTime, reservedById,
                responsiblePersonId);
    }
}