package com.example.SharedSpaces.controller;

import com.example.SharedSpaces.service.ReservationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("reservation")
public class ReservationController {

    private final ReservationService reservationService;

    @Autowired
    public ReservationController(ReservationService reservationService) {
        this.reservationService = reservationService;
    }

    @GetMapping
    public String getAllReservations(){
        return null;
    }

    @PostMapping
    public void addResevation(){

    }

    @PutMapping()
    public void updateResevation(){

    }

    @DeleteMapping()
    public  void deleteResevation(){

    }


}
