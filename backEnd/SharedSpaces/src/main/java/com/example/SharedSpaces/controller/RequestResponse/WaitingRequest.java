package com.example.SharedSpaces.controller.RequestResponse;


import java.util.Date;

public class WaitingRequest {

    private int spaceID;
    private Date startDateTime;
    private Date endDateTime;

    public WaitingRequest(){

    }

    public WaitingRequest(int spaceID, Date startDateTime, Date endDateTime) {
        this.spaceID = spaceID;
        this.startDateTime = startDateTime;
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
}
