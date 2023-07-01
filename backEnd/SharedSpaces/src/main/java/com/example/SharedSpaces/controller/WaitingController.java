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

// The @CrossOrigin annotation enables Cross-Origin Resource Sharing (CORS) for this controller
@CrossOrigin
// The @RestController annotation indicates that this class is a REST controller
@RestController
// The @RequestMapping annotation maps HTTP requests to a specific URL or URL
// pattern
@RequestMapping("waiting")
public class WaitingController {

    // The WaitingService used by this controller
    private final WaitingService waitingService;

    // Constructor for creating a new WaitingController object
    @Autowired
    public WaitingController(WaitingService waitingService) {
        this.waitingService = waitingService;
    }

    // The @GetMapping annotation maps HTTP GET requests to the /waiting/slot
    // endpoint
    @CrossOrigin
    @GetMapping("/slot")
    public List<WaitingResponse> getWaitingList(@RequestParam int spaceID, @RequestParam String date,
            @RequestParam int startTime, @RequestParam int endTime) {
        // Create a new Slot object with the provided parameters and call the
        // getWaitingList() method of the WaitingService to return a List of
        // WaitingResponse objects
        Slot slot = new Slot(spaceID, date, startTime, endTime);
        return waitingService.getWaitingList(slot);
    }

    // The @GetMapping annotation maps HTTP GET requests to the /waiting/user
    // endpoint
    @CrossOrigin
    @GetMapping("/user")
    public List<ReservationResponse> getUserWaitingList(@RequestParam String email) throws ResponseStatusException {

        // Check if the email in the request parameters matches the email in the
        // SecurityContext (commented out)
        // If not, return an empty ArrayList (commented out)

        try {
            // Call the getUserWaitingList() method of the WaitingService with the provided
            // email and return a List of ReservationResponse objects
            return waitingService.getUserWaitingList(email);
        } catch (Exception e) {
            // If an exception is caught, throw a new ResponseStatusException with an HTTP
            // status of 400 and an error message
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "invalid\n");
        }
    }

    // The @GetMapping annotation maps HTTP GET requests to the /waiting/responsible
    // endpoint
    @CrossOrigin
    @GetMapping("/responsible")
    public List<ReservationResponse> getResponsibleWaitingList(@RequestParam String email)
            throws ResponseStatusException {

        // Check if the email in the request parameters matches the email in the
        // SecurityContext (commented out)
        // If not, return an empty ArrayList (commented out)

        try {
            // Call the getResponsibleWaitingList() method of the WaitingService with the
            // provided email and return a List of ReservationResponse objects
            return waitingService.getResponsibleWaitingList(email);
        } catch (Exception e) {
            // If an exception is caught, throw a new ResponseStatusException with an HTTP
            // status of 400 and an error message
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "invalid\n");
        }
    }

    // The @PostMapping annotation maps HTTP POST requests to the /waiting endpoint
    @CrossOrigin
    @PostMapping
    public ReservationResponse createWaiting(@RequestBody ReservationRequest waiting) throws ResponseStatusException {

        try {
            // Call the handleWaiting() method of the WaitingService with the provided
            // ReservationRequest object and return a ReservationResponse object
            return waitingService.handleWaiting(waiting);
        } catch (AllReadyWaitingException e) {
            // If an AllReadyWaitingException is caught, throw a new ResponseStatusException
            // with an HTTP status of 400 and an error message
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "invalid\n");
        }

    }

    // The @DeleteMapping annotation maps HTTP DELETE requests to the /waiting
    // endpoint
    @CrossOrigin
    @DeleteMapping("/id")
    public String deleteWaiting(@RequestParam int id) throws ResponseStatusException {

        try {
            // Create a new Slot object with the provided parameters and call the
            // waitingDeleteBySlot() method of the WaitingService with the email and return
            // a String message
            // SecurityContextHolder.getContext().getAuthentication().getName();
            String email = "e19129@eng.pdn.ac.lk";
            return waitingService.waitingDeleteBySlot(id, email);
        } catch (InvalidDataException e) {
            // If an InvalidDataException is caught, throw a new ResponseStatusException
            // with an HTTP status of 400 and an error message
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "invalid\n");
        }

    }

    // The @DeleteMapping annotation maps HTTP DELETE requests to the /waiting
    // endpoint
    // @CrossOrigin
    // @DeleteMapping()
    // public String deleteWaiting(@RequestParam int spaceID, @RequestParam String
    // date, @RequestParam int startTime,
    // @RequestParam int endTime, @RequestParam String email) throws
    // ResponseStatusException {

    // try {
    // // Create a new Slot object with the provided parameters and call the
    // // waitingDeleteBySlot() method of the WaitingService with the email and
    // return
    // // a String message
    // Slot slot = new Slot(spaceID, date, startTime, endTime);
    // return waitingService.waitingDeleteBySlot(slot, email);
    // } catch (InvalidDataException e) {
    // // If an InvalidDataException is caught, throw a new ResponseStatusException
    // // with an HTTP status of 400 and an error message
    // throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "invalid\n");
    // }

    // }

}
