package com.example.SharedSpaces.controller;

import com.example.SharedSpaces.controller.RequestResponse.ReservationRequest;
import com.example.SharedSpaces.controller.RequestResponse.ReservationResponse;
import com.example.SharedSpaces.controller.RequestResponse.Slot;
import com.example.SharedSpaces.exception.AllReadyReservedException;
import com.example.SharedSpaces.exception.EmailException;
import com.example.SharedSpaces.exception.InvalidDataException;
import com.example.SharedSpaces.service.ReservationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;
import java.util.List;
import java.util.NoSuchElementException;
import org.springframework.security.core.context.SecurityContextHolder;

@CrossOrigin
@RestController
@RequestMapping("reservation")
public class ReservationController {

    private final ReservationService reservationService;

    @Autowired
    public ReservationController(ReservationService reservationService) {
        this.reservationService = reservationService;
    }

    @GetMapping
    public List<ReservationResponse> getAllReservations() {
        return reservationService.getAllResevations();
    }

    @PostMapping
    public ReservationResponse addResevation(ReservationRequest reservationRequest) throws ResponseStatusException {

        try {
            return reservationService.hadleReservation(reservationRequest);
        } catch (AllReadyReservedException e) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "reserved\n");
        } catch (NoSuchElementException e) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "wrongInput\n");
        } catch (EmailException e) {
            throw new ResponseStatusException(HttpStatus.SERVICE_UNAVAILABLE, "emailError\n");
        } catch (InvalidDataException e) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "invalidUser\n");
        }

    }

    @GetMapping("/user")
    public List<ReservationResponse> getUserReservationList(@RequestParam String email) throws ResponseStatusException {

        // if(!SecurityContextHolder.getContext().getAuthentication().getName().equals(email)){
        // return new ArrayList<>();
        // }
        try {
            return reservationService.getUserReservationList(email);
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
            return reservationService.getResponsibleReservationList(email);
        } catch (Exception e) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "invalid\n");
        }

    }

    @DeleteMapping()
    public String deleteResevation(@RequestParam int spaceID, @RequestParam String date, @RequestParam int startTime,
            @RequestParam int endTime, @RequestParam String email) throws ResponseStatusException {

        // if(!SecurityContextHolder.getContext().getAuthentication().getName().equals(email)){
        // return "Error";
        // }

        try {
            Slot slot = new Slot(spaceID, date, startTime, endTime);
            return reservationService.reservationDeleteBySlot(slot, email);
        } catch (InvalidDataException e) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "invalid\n");
        } catch (EmailException e) {
            throw new ResponseStatusException(HttpStatus.SERVICE_UNAVAILABLE, "emailError\n");
        }

    }

}
