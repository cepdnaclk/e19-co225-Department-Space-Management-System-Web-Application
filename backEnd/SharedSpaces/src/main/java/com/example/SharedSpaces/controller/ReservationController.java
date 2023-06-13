package com.example.SharedSpaces.controller;

import com.example.SharedSpaces.controller.RequestResponse.ReservationRequest;
import com.example.SharedSpaces.controller.RequestResponse.ReservationResponse;
import com.example.SharedSpaces.service.ReservationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("reservation")
public class ReservationController {

    private final ReservationService reservationService;

    @Autowired
    public ReservationController(ReservationService reservationService) {
        this.reservationService = reservationService;
    }

    @GetMapping
    public List<ReservationResponse> getAllReservations(){
        return reservationService.getAllResevations();
    }

    @PostMapping
    public ReservationResponse addResevation(ReservationRequest reservationRequest){
        return  reservationService.hadleReservation(reservationRequest);
    }

//    @PutMapping()
//    public void updateResevation(){
//
//    }
//
//    @DeleteMapping()
//    public  void deleteResevation(){
//
//    }

}
