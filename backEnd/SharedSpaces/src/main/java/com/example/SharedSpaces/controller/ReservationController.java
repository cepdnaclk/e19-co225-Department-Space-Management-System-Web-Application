package com.example.SharedSpaces.controller;

import com.example.SharedSpaces.controller.RequestResponse.ReservationRequest;
import com.example.SharedSpaces.controller.RequestResponse.ReservationResponse;
import com.example.SharedSpaces.controller.RequestResponse.Request;
import com.example.SharedSpaces.service.ReservationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.text.SimpleDateFormat;
import java.util.List;

@CrossOrigin
@RestController
@RequestMapping("reservation")
public class ReservationController {

//    private final ReservationService reservationService;
//
//    @Autowired
//    public ReservationController(ReservationService reservationService) {
//        this.reservationService = reservationService;
//    }

//    @GetMapping
//    public List<ReservationResponse> getAllReservations(){
//        return reservationService.getAllResevations();
//    }
//
//    @PostMapping
//    public ReservationResponse addResevation(ReservationRequest reservationRequest){
//        return  reservationService.hadleReservation(reservationRequest);
//    }
//
////    @PutMapping()
////    public void updateResevation(){
////
////    }
////
//
//    @DeleteMapping()
//    public  String deleteResevation(@RequestParam int spaceID, @RequestParam String date, @RequestParam int startTime, @RequestParam int endTime, @RequestParam String email){
//
//        Request request = new Request();
//        request.setSpaceID(spaceID);
//
//        try {
//            request.setStartDateTime(new SimpleDateFormat("dd-MM-yyyy").parse(date));
//            request.setEndDateTime(new SimpleDateFormat("dd-MM-yyyy").parse(date));
//        } catch (Exception e){
//            System.out.println(e);
//        }
//
//        request.getStartDateTime().setHours(startTime/100);
//        request.getStartDateTime().setMinutes(startTime%100);
//        request.getEndDateTime().setHours(endTime/100);
//        request.getEndDateTime().setMinutes(endTime%100);
//
//        return reservationService.reservationDeleteByRequest(request, email);
//
//    }

}
