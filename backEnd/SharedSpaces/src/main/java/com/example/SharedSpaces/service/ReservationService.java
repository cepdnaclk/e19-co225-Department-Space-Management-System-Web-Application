package com.example.SharedSpaces.service;

import com.example.SharedSpaces.controller.RequestResponse.Slot;
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
import java.util.*;

@Service
public class ReservationService {

    private final ReservationDB reservationDB;
    private final WaitingDB waitingDB;
    private final UserDB userDB;
    private final WaitingService waitingService;

    @Autowired
    public ReservationService(ReservationDB reservationDB, UserDB userDB, WaitingDB waitingDB, WaitingService waitingService){
        this.reservationDB = reservationDB;
        this.userDB = userDB;
        this.waitingDB = waitingDB;
        this.waitingService = waitingService;
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

    public List<ReservationResponse> getUserReservationList(String email){

        List<ReservationResponse> respons = new ArrayList<>();

        for (Reservation waiting: reservationDB.getAllResevation(email)){
            respons.add(reservationToRequest(waiting));
        }

        return respons;
    }

    public List<ReservationResponse> getResponsibleReservationList(String email){

        List<ReservationResponse> respons = new ArrayList<>();

        for (Reservation waiting: reservationDB.getAllResponsibleWaiting(email)){
            respons.add(reservationToRequest(waiting));
        }

        return respons;
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

    public String reservationDeleteBySlot(Slot slot, String email){
        Optional<Reservation> optional = reservationDB.getReservationByDetails(slot.getSpaceID(), slot.getStartDateTime(), slot.getEndDateTime());

        if (optional.isEmpty())
            return "Bad Request";

        Reservation reservation = optional.get();

        if (!email.equals(userDB.getUserById(reservation.getReservedById()).get().getEmail()) && !email.equals(userDB.getUserById(reservation.getResponsiblePersonId()).get().getEmail()))
            return "Bad Request";

        List<Waiting> waitingList = waitingDB.getWaitingByDetails(slot.getSpaceID(), slot.getStartDateTime(), slot.getEndDateTime());

        try {
            waitingList.sort(Comparator.comparing(Waiting::getReservationDateTime));
        } catch (Exception e){
            System.out.println();
        }

        reservationDB.deleteReservation(reservation.getId());

//        if (waitingList != null){
//            reservationDB.createReservation(new Reservation(waitingList.get(0)));
//            waitingDB.deleteWaiting(waitingList.get(0).getId());
//        }

        return "Deleted";
    }



}
