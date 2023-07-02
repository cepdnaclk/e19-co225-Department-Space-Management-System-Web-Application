package com.example.SharedSpaces.controller.RequestResponse;

import java.text.SimpleDateFormat;
import java.util.Date;

public class Slot {

    // The ID of the space being reserved
    private int spaceID;

    // The start date and time of the slot
    private Date startDateTime;

    // The end date and time of the slot
    private Date endDateTime;

    public Slot() {

    }

    // Constructor for creating a new slot object
    public Slot(int spaceID, Date startDateTime, Date endDateTime) {
        this.spaceID = spaceID;
        this.startDateTime = startDateTime;
        this.endDateTime = endDateTime;
    }

    // Constructor for creating a new slot object using a date string and start/end time integers
    public Slot(int spaceID, String date, int startTime, int endTime) {
        this.setSpaceID(spaceID);

        try {
            // Parse the date string using the "dd-MM-yyyy" format
            this.setStartDateTime(new SimpleDateFormat("yyyy-MM-dd").parse(date));
            this.setEndDateTime(new SimpleDateFormat("yyyy-MM-dd").parse(date));
        } catch (Exception e) {
            System.out.println(e);
        }

        // Set the hours and minutes of the start and end date/time based on the start/end time integers
        this.getStartDateTime().setHours(startTime / 100);
        this.getStartDateTime().setMinutes(startTime % 100);
        this.getEndDateTime().setHours(endTime / 100);
        this.getEndDateTime().setMinutes(endTime % 100);
    }

    // Getter and setter methods for spaceID
    public int getSpaceID() {
        return spaceID;
    }

    public void setSpaceID(int spaceID) {
        this.spaceID = spaceID;
    }

    // Getter and setter methods for startDateTime
    public Date getStartDateTime() {
        return startDateTime;
    }

    public void setStartDateTime(Date startDateTime) {
        this.startDateTime = startDateTime;
    }

    // Getter and setter methods for endDateTime
    public Date getEndDateTime() {
        return endDateTime;
    }

    public void setEndDateTime(Date endDateTime) {
        this.endDateTime = endDateTime;
    }

    // Override the toString() method to return a string representation of the object
    @Override
    public String toString() {
        return "Slot{" +
                "spaceID=" + spaceID +
                ", startDateTime=" + startDateTime +
                ", endDateTime=" + endDateTime +
                '}';
    }
}

