package com.example.SharedSpaces.service;

import com.example.SharedSpaces.controller.RequestResponse.ReservationRequest;
import com.example.SharedSpaces.controller.RequestResponse.ReservationResponse;
import com.example.SharedSpaces.db.ReservationDB;
import com.example.SharedSpaces.db.UserDB;
import com.example.SharedSpaces.models.Reservation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.text.SimpleDateFormat;
import java.util.Date;

@Service
public class ReservationService {

    private final ReservationDB reservationDB;
    private final UserDB userDB;

    @Autowired
    public ReservationService(ReservationDB reservationDB, UserDB userDB){
        this.reservationDB = reservationDB;
        this.userDB = userDB;
    }

    public ReservationResponse hadleReservation(ReservationRequest reservationRequest) throws Exception{
        Reservation reservation = new Reservation();
        reservation.setTitle(reservationRequest.getTitle());
        reservation.setReservationDateTime(new Date(Long.parseLong(reservationRequest.getReservationDateTime())));

        reservation.setSpaceID(reservationRequest.getSpaceID());
        reservation.setStartDateTime(new SimpleDateFormat("dd-MM-yyyy").parse(reservationRequest.getDate()));
        reservation.setEndDateTime(new SimpleDateFormat("dd-MM-yyyy").parse(reservationRequest.getDate()));

        reservation.getStartDateTime().setHours(reservationRequest.getStartTime()/100);
        reservation.getStartDateTime().setMinutes(reservationRequest.getStartTime()%100);
        reservation.getEndDateTime().setHours(reservationRequest.getEndTime()/100);
        reservation.getEndDateTime().setMinutes(reservationRequest.getEndTime()%100);

        reservation.setReservedById(userDB.getUserByEmail(reservationRequest.getReservedBy()).get().getId());

        System.out.println(reservation);

        return new ReservationResponse();

    }

}
