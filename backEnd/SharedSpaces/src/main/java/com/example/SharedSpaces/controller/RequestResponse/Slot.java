package com.example.SharedSpaces.controller.RequestResponse;

import java.text.SimpleDateFormat;
import java.util.Date;

public class Slot {

    private int spaceID;
    private Date startDateTime;
    private Date endDateTime;

    public Slot() {

    }

    public Slot(int spaceID, Date startDateTime, Date endDateTime) {
        this.spaceID = spaceID;
        this.startDateTime = startDateTime;
        this.endDateTime = endDateTime;
    }

    public Slot(int spaceID, String date, int startTime, int endTime) {
        this.setSpaceID(spaceID);

        try {
            this.setStartDateTime(new SimpleDateFormat("dd-MM-yyyy").parse(date));
            this.setEndDateTime(new SimpleDateFormat("dd-MM-yyyy").parse(date));
        } catch (Exception e) {
            System.out.println(e);
        }

        this.getStartDateTime().setHours(startTime / 100);
        this.getStartDateTime().setMinutes(startTime % 100);
        this.getEndDateTime().setHours(endTime / 100);
        this.getEndDateTime().setMinutes(endTime % 100);
    }

    public int getSpaceID() {
        return spaceID;
    }

    public void setSpaceID(int spaceID) {
        this.spaceID = spaceID;
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

    @Override
    public String toString() {
        return "WaitingRequest{" +
                "spaceID=" + spaceID +
                ", startDateTime=" + startDateTime +
                ", endDateTime=" + endDateTime +
                '}';
    }
}
