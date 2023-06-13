package com.example.SharedSpaces.controller;

import com.example.SharedSpaces.controller.RequestResponse.WaitingRequest;
import com.example.SharedSpaces.controller.RequestResponse.WaitingResponse;
import com.example.SharedSpaces.service.WaitingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@RestController
@RequestMapping("waiting")
public class WaitingController {

    private final WaitingService waitingService;

    @Autowired
    public WaitingController(WaitingService waitingService){
        this.waitingService = waitingService;
    }

    @GetMapping
    public List<WaitingResponse> getWaitingList(@RequestParam int spaceID, @RequestParam String date, @RequestParam int startTime, @RequestParam int endTime){

        WaitingRequest waitingRequest = new WaitingRequest();
        waitingRequest.setSpaceID(spaceID);

        try {
            waitingRequest.setStartDateTime(new SimpleDateFormat("dd-MM-yyyy").parse(date));
            waitingRequest.setEndDateTime(new SimpleDateFormat("dd-MM-yyyy").parse(date));
        } catch (Exception e){
            System.out.println(e);
        }

        waitingRequest.getStartDateTime().setHours(startTime/100);
        waitingRequest.getStartDateTime().setMinutes(startTime%100);
        waitingRequest.getEndDateTime().setHours(endTime/100);
        waitingRequest.getEndDateTime().setMinutes(endTime%100);

        return waitingService.getWaitingList(waitingRequest);
    }

}
