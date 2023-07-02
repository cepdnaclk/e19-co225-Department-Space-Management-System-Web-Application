package com.example.SharedSpaces.controller;

import com.example.SharedSpaces.controller.RequestResponse.ReservationRequest;
import com.example.SharedSpaces.controller.RequestResponse.ReservationResponse;
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

// The @CrossOrigin annotation enables Cross-Origin Resource Sharing (CORS) for this controller
@CrossOrigin
// The @RestController annotation indicates that this class is a REST controller
@RestController
// The @RequestMapping annotation maps HTTP requests to a specific URL or URL
// pattern
@RequestMapping("reservation")
public class ReservationController {

    // The ReservationService used by this controller
    private final ReservationService reservationService;

    // Constructor for creating a new ReservationController object
    @Autowired
    public ReservationController(ReservationService reservationService) {
        this.reservationService = reservationService;
    }

    // The @GetMapping annotation maps HTTP GET requests to the /reservation
    // endpoint
    @GetMapping
    public List<ReservationResponse> getAllReservations() {
        // Call the getAllReservations() method of the ReservationService and return the
        // result as a List of ReservationResponse objects
        return reservationService.getAllResevations();
    }

    // The @PostMapping annotation maps HTTP POST requests to the /reservation
    // endpoint
    @CrossOrigin
    @PostMapping
    public ReservationResponse addResevation(@RequestBody ReservationRequest reservationRequest)
            throws ResponseStatusException {

        try {
            // Call the hadleReservation() method of the ReservationService and return the
            // result as a ReservationResponse object
            return reservationService.hadleReservation(reservationRequest);
        } catch (AllReadyReservedException e) {
            // Throw a ResponseStatusException with HTTP status code 400 (Bad Request) if
            // the reservation is already reserved
            throw new ResponseStatusException(HttpStatus.UNAUTHORIZED, "reserved\n");
        } catch (NoSuchElementException e) {
            // Throw a ResponseStatusException with HTTP status code 400 (Bad Request) if
            // the input is wrong
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "wrongInput\n");
        } catch (EmailException e) {
            // Throw a ResponseStatusException with HTTP status code 503 (Service
            // Unavailable) if there is an email error
            throw new ResponseStatusException(HttpStatus.SERVICE_UNAVAILABLE, "emailError\n");
        } catch (InvalidDataException e) {
            // Throw a ResponseStatusException with HTTP status code 400 (Bad Request) if
            // the user data is invalid
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "invalidUser\n");
        } catch (Exception e) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "error\n");
        }

    }

    // The @GetMapping annotation maps HTTP GET requests to the /reservation/user
    // endpoint
    @CrossOrigin
    @GetMapping("/user")
    public List<ReservationResponse> getUserReservationList(@RequestParam String email) throws ResponseStatusException {

        try {
            // Call the getUserReservationList() method of the ReservationService and return
            // the result as a List of ReservationResponse objects
            return reservationService.getUserReservationList(email);
        } catch (Exception e) {
            // Throw a ResponseStatusException with HTTP status code 400 (Bad Request) if
            // the input is invalid
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "invalid\n");
        }
    }

    // The @GetMapping annotation maps HTTP GET requests to the
    // /reservation/responsible endpoint
    @CrossOrigin
    @GetMapping("/responsible")
    public List<ReservationResponse> getResponsibleWaitingList(@RequestParam String email)
            throws ResponseStatusException {

        try {
            // Call the getResponsibleReservationList() method of the ReservationService and
            // return the result as a List of ReservationResponse objects
            return reservationService.getResponsibleReservationList(email);
        } catch (Exception e) {
            // Throw a ResponseStatusException with HTTP status code 400 (Bad Request) if
            // the input is invalid
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "invalid\n");
        }

    }

    // The @DeleteMapping annotation maps HTTP DELETE requests to the /reservation
    // endpoint
    @CrossOrigin
    @DeleteMapping()
    public String deleteResevation(@RequestParam int id) throws ResponseStatusException {

        try {
            // Create a new Slot object with the specified space ID, date, start time, and
            // end time
            String email = SecurityContextHolder.getContext().getAuthentication().getName();
            // Call the reservationDeleteBySlot() method of the ReservationService and
            // return the result as a String
            return reservationService.reservationDeleteBySlot(id, email);
        } catch (InvalidDataException e) {
            // Throw a ResponseStatusException with HTTP status code 400 (Bad Request) if
            // the input is invalid
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "invalid\n");
        } catch (EmailException e) {
            // Throw a ResponseStatusException with HTTP status code 503 (Service
            // Unavailable) if there is an email error
            throw new ResponseStatusException(HttpStatus.SERVICE_UNAVAILABLE, "emailError\n");
        }

    }

    // The @DeleteMapping annotation maps HTTP DELETE requests to the /reservation
    // endpoint
    // @CrossOrigin
    // @DeleteMapping()
    // public String deleteResevation(@RequestParam int spaceID, @RequestParam
    // String date, @RequestParam int startTime,
    // @RequestParam int endTime, @RequestParam String email) throws
    // ResponseStatusException {

    // try {
    // // Create a new Slot object with the specified space ID, date, start time,
    // and
    // // end time
    // Slot slot = new Slot(spaceID, date, startTime, endTime);
    // // Call the reservationDeleteBySlot() method of the ReservationService and
    // // return the result as a String
    // return reservationService.reservationDeleteBySlot(slot, email);
    // } catch (InvalidDataException e) {
    // // Throw a ResponseStatusException with HTTP status code 400 (Bad Request) if
    // // the input is invalid
    // throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "invalid\n");
    // } catch (EmailException e) {
    // // Throw a ResponseStatusException with HTTP status code 503 (Service
    // // Unavailable) if there is an email error
    // throw new ResponseStatusException(HttpStatus.SERVICE_UNAVAILABLE,
    // "emailError\n");
    // }

    // }

}
