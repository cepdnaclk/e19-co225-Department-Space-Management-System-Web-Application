package com.example.SharedSpaces.controller.RequestResponse;

import java.util.ArrayList;
import java.util.Date;
import java.util.Objects;

public class ReservationResponse {

    private int spaceID;
    private int reservationId;
    private String title;
    private String reservationDateTime;
    private String date;
    private int startTime;
    private int endTime;
    private String reservedBy;
    private String responsiblePerson;
    private ArrayList<ArrayList<String>> waitingList;
    private boolean reserved;

    public ReservationResponse(){

    }


}
