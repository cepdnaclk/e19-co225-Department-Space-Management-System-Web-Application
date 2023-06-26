package com.example.SharedSpaces.models;

import jakarta.persistence.*;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

@Entity
@Table(name = "waiting")
public class Waiting {

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

    public Waiting() {

    }

    public Waiting(Reservation reservation) {
        this.spaceID = reservation.getSpaceID();
        this.title = reservation.getTitle();
        this.reservationDateTime = reservation.getReservationDateTime();
        this.startDateTime = reservation.getStartDateTime();
        this.endDateTime = reservation.getEndDateTime();
        this.reservedById = reservation.getReservedById();
        this.responsiblePersonId = reservation.getResponsiblePersonId();
        DateFormat dateFormat = new SimpleDateFormat("dd-MM-yyyy");
        this.date = dateFormat.format(reservation.getStartDateTime());
    }

    public Waiting(long id, int spaceID, String title, Date reservationDateTime, Date startDateTime, Date endDateTime,
            long reservedById, long responsiblePersonId) {
        this.id = id;
        this.spaceID = spaceID;
        this.title = title;
        this.reservationDateTime = reservationDateTime;
        this.startDateTime = startDateTime;
        this.endDateTime = endDateTime;
        this.reservedById = reservedById;
        this.responsiblePersonId = responsiblePersonId;
        DateFormat dateFormat = new SimpleDateFormat("dd-MM-yyyy");
        this.date = dateFormat.format(startDateTime);
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

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    @Override
    public String toString() {
        return "Waiting{" +
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
}
