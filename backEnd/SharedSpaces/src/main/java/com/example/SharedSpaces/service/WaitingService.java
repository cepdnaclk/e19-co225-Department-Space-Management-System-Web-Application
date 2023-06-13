package com.example.SharedSpaces.service;

import com.example.SharedSpaces.controller.RequestResponse.WaitingRequest;
import com.example.SharedSpaces.controller.RequestResponse.WaitingResponse;
import com.example.SharedSpaces.db.ResponsiblePersonDB;
import com.example.SharedSpaces.db.UserDB;
import com.example.SharedSpaces.db.WaitingDB;
import com.example.SharedSpaces.models.Waiting;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

@Service
public class WaitingService {

    private final WaitingDB waitingDB;
    private final UserDB userDB;
    private final ResponsiblePersonDB responsiblePersonDB;

    @Autowired
    public WaitingService( WaitingDB waitingDB, UserDB userDB, ResponsiblePersonDB responsiblePersonDB){
        this.waitingDB = waitingDB;
        this.userDB = userDB;
        this.responsiblePersonDB = responsiblePersonDB;
    }

    public List<WaitingResponse> getWaitingList(WaitingRequest waitingRequest){

        List<Waiting> waitingList = waitingDB.getWaitingByDetails(waitingRequest.getSpaceID(), waitingRequest.getStartDateTime(), waitingRequest.getEndDateTime());

        if (waitingList == null)
            return new ArrayList<>();

        waitingList.sort(Comparator.comparing(Waiting::getReservationDateTime));

        List<WaitingResponse> waitingResponses = new ArrayList<>();

        for (Waiting waiting: waitingList){
            waitingResponses.add(new WaitingResponse(userDB.getUserFullName(waiting.getReservedById()), responsiblePersonDB.getUserFullName(waiting.getResponsiblePersonId())));
        }

        return waitingResponses;
    }

}
