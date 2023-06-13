package com.example.SharedSpaces.service;

import com.example.SharedSpaces.controller.RequestResponse.ReservationRequest;
import com.example.SharedSpaces.controller.RequestResponse.ReservationResponse;
import com.example.SharedSpaces.db.ReservationDB;
import com.example.SharedSpaces.db.UserDB;
import com.example.SharedSpaces.db.WaitingDB;
import com.example.SharedSpaces.models.Reservation;
import com.example.SharedSpaces.models.Waiting;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Service
public class ReservationService {

    private final ReservationDB reservationDB;
    private final WaitingDB waitingDB;
    private final UserDB userDB;

    @Autowired
    public ReservationService(ReservationDB reservationDB, UserDB userDB, WaitingDB waitingDB){
        this.reservationDB = reservationDB;
        this.userDB = userDB;
        this.waitingDB = waitingDB;
    }

    public List<ReservationResponse> getAllResevations(){
        List<ReservationResponse> reservationResponses = new ArrayList<>();

        System.out.println(reservationDB.getAllResevation());

        for (Reservation reservation: reservationDB.getAllResevation()){
            reservationResponses.add(reservationToRequest(reservation));
        }

        System.out.println(reservationResponses);
        return reservationResponses;
    }

    public ReservationResponse hadleReservation(ReservationRequest reservationRequest) {
        Reservation reservation = requestToReservation(reservationRequest);

        ReservationResponse reservationResponse = reservationToRequest(reservation);

        if (!reservationDB.getReservationByDetails(reservation.getSpaceID(), reservation.getStartDateTime(), reservation.getEndDateTime()).isPresent()) {
            reservationDB.createReservation(reservation);
            reservationResponse.setStatus("reserved");
        }
        else {
            waitingDB.createWaiting(new Waiting(reservation));
            reservationResponse.setStatus("waiting");
        }

        return reservationResponse;
    }

    public Reservation requestToReservation(ReservationRequest reservationRequest){
        Reservation reservation = new Reservation();

        reservation.setTitle(reservationRequest.getTitle());
        reservation.setReservationDateTime(new Date(Long.parseLong(reservationRequest.getReservationDateTime())));
        reservation.setSpaceID(reservationRequest.getSpaceID());

        try {
            reservation.setStartDateTime(new SimpleDateFormat("dd-MM-yyyy").parse(reservationRequest.getDate()));
            reservation.setEndDateTime(new SimpleDateFormat("dd-MM-yyyy").parse(reservationRequest.getDate()));
        } catch (Exception e){
            System.out.println(e);
        }

        reservation.getStartDateTime().setHours(reservationRequest.getStartTime()/100);
        reservation.getStartDateTime().setMinutes(reservationRequest.getStartTime()%100);
        reservation.getEndDateTime().setHours(reservationRequest.getEndTime()/100);
        reservation.getEndDateTime().setMinutes(reservationRequest.getEndTime()%100);

        reservation.setReservedById(userDB.getUserByEmail(reservationRequest.getReservedBy()).get().getId());
        reservation.setResponsiblePersonId(reservationRequest.getResponsiblePerson());

        return reservation;
    }

    public ReservationResponse reservationToRequest(Reservation reservation){
        ReservationResponse reservationResponse = new ReservationResponse();

        reservationResponse.setSpaceID(reservation.getSpaceID());
        reservationResponse.setTitle(reservation.getTitle());

        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("dd-MM-yyyy");
        reservationResponse.setDate(simpleDateFormat.format(reservation.getStartDateTime()));

        reservationResponse.setStartTime(reservation.getStartDateTime().getHours()*100 + reservation.getStartDateTime().getMinutes());
        reservationResponse.setEndTime(reservation.getEndDateTime().getHours()*100 + reservation.getEndDateTime().getMinutes());

        reservationResponse.setReservedBy(userDB.getUserById(reservation.getReservedById()).get().getEmail());
        reservationResponse.setResponsiblePerson(userDB.getUserById(reservation.getResponsiblePersonId()).get().getEmail());

        return reservationResponse;
    }

}
