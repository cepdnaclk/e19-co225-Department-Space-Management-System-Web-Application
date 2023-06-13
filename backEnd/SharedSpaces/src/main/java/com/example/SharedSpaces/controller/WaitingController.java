package com.example.SharedSpaces.controller;

import com.example.SharedSpaces.controller.RequestResponse.Request;
import com.example.SharedSpaces.controller.RequestResponse.WaitingResponse;
import com.example.SharedSpaces.service.WaitingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.text.SimpleDateFormat;
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

        Request request = new Request();
        request.setSpaceID(spaceID);

        try {
            request.setStartDateTime(new SimpleDateFormat("dd-MM-yyyy").parse(date));
            request.setEndDateTime(new SimpleDateFormat("dd-MM-yyyy").parse(date));
        } catch (Exception e){
            System.out.println(e);
        }

        request.getStartDateTime().setHours(startTime/100);
        request.getStartDateTime().setMinutes(startTime%100);
        request.getEndDateTime().setHours(endTime/100);
        request.getEndDateTime().setMinutes(endTime%100);

        return waitingService.getWaitingList(request);
    }

}
