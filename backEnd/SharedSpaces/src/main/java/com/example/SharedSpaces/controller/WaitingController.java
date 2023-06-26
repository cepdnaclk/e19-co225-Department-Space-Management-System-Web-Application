package com.example.SharedSpaces.controller;

import com.example.SharedSpaces.controller.RequestResponse.ReservationRequest;
import com.example.SharedSpaces.controller.RequestResponse.ReservationResponse;
import com.example.SharedSpaces.controller.RequestResponse.Slot;
import com.example.SharedSpaces.controller.RequestResponse.WaitingResponse;
import com.example.SharedSpaces.exception.AllReadyWaitingException;
import com.example.SharedSpaces.exception.InvalidDataException;
import com.example.SharedSpaces.service.WaitingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;
import java.util.List;

@CrossOrigin
@RestController
@RequestMapping("waiting")
public class WaitingController {

    private final WaitingService waitingService;

    @Autowired
    public WaitingController(WaitingService waitingService) {
        this.waitingService = waitingService;
    }

    @GetMapping("/slot")
    public List<WaitingResponse> getWaitingList(@RequestParam int spaceID, @RequestParam String date,
            @RequestParam int startTime, @RequestParam int endTime) {
        Slot slot = new Slot(spaceID, date, startTime, endTime);
        return waitingService.getWaitingList(slot);
    }

    @GetMapping("/user")
    public List<ReservationResponse> getUserWaitingList(@RequestParam String email) throws ResponseStatusException {

        // if(!SecurityContextHolder.getContext().getAuthentication().getName().equals(email)){
        // return new ArrayList<>();
        // }

        try {
            return waitingService.getUserWaitingList(email);
        } catch (Exception e) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "invalid\n");
        }
    }

    @GetMapping("/responsible")
    public List<ReservationResponse> getResponsibleWaitingList(@RequestParam String email)
            throws ResponseStatusException {

        // if(!SecurityContextHolder.getContext().getAuthentication().getName().equals(email)){
        // return new ArrayList<>();
        // }

        try {
            return waitingService.getResponsibleWaitingList(email);
        } catch (Exception e) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "invalid\n");
        }
    }

    @PostMapping
    public ReservationResponse createWaiting(ReservationRequest waiting) throws ResponseStatusException {

        try {
            return waitingService.handleWaiting(waiting);
        } catch (AllReadyWaitingException e) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "invalid\n");
        }

    }

    @DeleteMapping()
    public String deleteWaiting(@RequestParam int spaceID, @RequestParam String date, @RequestParam int startTime,
            @RequestParam int endTime, @RequestParam String email) throws ResponseStatusException {

        // if(!SecurityContextHolder.getContext().getAuthentication().getName().equals(email)){
        // return "Error";
        // }

        try {
            Slot slot = new Slot(spaceID, date, startTime, endTime);
            return waitingService.waitingDeleteBySlot(slot, email);
        } catch (InvalidDataException e) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "invalid\n");
        }

    }

}
