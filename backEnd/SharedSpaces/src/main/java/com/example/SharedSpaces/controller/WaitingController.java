package com.example.SharedSpaces.controller;

import com.example.SharedSpaces.controller.RequestResponse.ReservationRequest;
import com.example.SharedSpaces.controller.RequestResponse.ReservationResponse;
import com.example.SharedSpaces.controller.RequestResponse.Slot;
import com.example.SharedSpaces.controller.RequestResponse.WaitingResponse;
import com.example.SharedSpaces.models.Waiting;
import com.example.SharedSpaces.service.WaitingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.text.SimpleDateFormat;
import java.util.List;

@CrossOrigin
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
        Slot slot = new Slot(spaceID, date, startTime, endTime);
        return waitingService.getWaitingList(slot);
    }

    @GetMapping("/user")
    public  List<ReservationResponse> getUserWaitingList(@RequestParam String email){
        System.out.println(email);
        return waitingService.getUserWaitingList(email);
    }

    @GetMapping("/responsible")
    public  List<ReservationResponse> getResponsibleWaitingList(@RequestParam String email){
        return waitingService.getResponsibleWaitingList(email);
    }

    @PostMapping
    public ReservationResponse createWaiting(ReservationRequest waiting){
        System.out.println(waiting);
        return waitingService.handleWaiting(waiting);
    }

}
