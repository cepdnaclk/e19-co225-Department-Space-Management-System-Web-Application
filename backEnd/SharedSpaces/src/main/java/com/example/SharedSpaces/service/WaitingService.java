package com.example.SharedSpaces.service;

import com.example.SharedSpaces.controller.RequestResponse.ReservationRequest;
import com.example.SharedSpaces.controller.RequestResponse.ReservationResponse;
import com.example.SharedSpaces.controller.RequestResponse.Slot;
import com.example.SharedSpaces.controller.RequestResponse.WaitingResponse;
import com.example.SharedSpaces.db.AdminDB;
import com.example.SharedSpaces.db.ResponsiblePersonDB;
import com.example.SharedSpaces.db.UserDB;
import com.example.SharedSpaces.db.WaitingDB;
import com.example.SharedSpaces.exception.AllReadyWaitingException;
import com.example.SharedSpaces.exception.InvalidDataException;
import com.example.SharedSpaces.models.Waiting;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.*;

@Service
public class WaitingService {

    private final WaitingDB waitingDB;
    private final UserDB userDB;
    private final ResponsiblePersonDB responsiblePersonDB;
    private final AdminDB adminDB;

    @Autowired
    public WaitingService(WaitingDB waitingDB, UserDB userDB, ResponsiblePersonDB responsiblePersonDB,
            AdminDB adminDB) {
        this.waitingDB = waitingDB;
        this.userDB = userDB;
        this.responsiblePersonDB = responsiblePersonDB;
        this.adminDB = adminDB;
    }

    public List<WaitingResponse> getWaitingList(Slot slot) {

        List<Waiting> waitingList = waitingDB.getWaitingByDetails(slot.getSpaceID(), slot.getStartDateTime(),
                slot.getEndDateTime());

        if (waitingList == null)
            return new ArrayList<>();

        waitingList.sort(Comparator.comparing(Waiting::getReservationDateTime));

        List<WaitingResponse> respons = new ArrayList<>();

        for (Waiting waiting : waitingList) {
            respons.add(new WaitingResponse(userDB.getUserFullName(waiting.getReservedById()),
                    responsiblePersonDB.getUserFullName(waiting.getResponsiblePersonId())));
        }

        return respons;
    }

    public List<WaitingResponse> getUserWaitingList(Slot slot, String email) {
        List<WaitingResponse> waitingList = new ArrayList<>();

        for (WaitingResponse waitingResponse : getWaitingList(slot)) {
            if (waitingResponse.getName().equals(userDB.getUserFullName(userDB.getUserByEmail(email).get().getId()))) {
                waitingList.add(waitingResponse);
            }
        }

        return waitingList;
    }

    public List<ReservationResponse> getUserWaitingList(String email) {

        List<ReservationResponse> respons = new ArrayList<>();

        for (Waiting waiting : waitingDB.getAllWaiting(email)) {
            respons.add(WaitingToRequest(waiting));
        }

        return respons;
    }

    public List<ReservationResponse> getResponsibleWaitingList(String email) {

        List<ReservationResponse> respons = new ArrayList<>();

        for (Waiting waiting : waitingDB.getAllResponsibleWaiting(email)) {
            respons.add(WaitingToRequest(waiting));
        }

        return respons;
    }

    public ReservationResponse handleWaiting(ReservationRequest reservationRequest) throws AllReadyWaitingException {

        Waiting reservation = requestToWaiting(reservationRequest);
        ReservationResponse reservationResponse = WaitingToRequest(reservation);

        Waiting waiting = waitingDB.getWaitingByDetails(reservation.getSpaceID(), reservation.getStartDateTime(),
                reservation.getEndDateTime(), reservation.getReservedById());

        if (waiting != null)
            throw new AllReadyWaitingException("invalid");

        waitingDB.createWaiting(reservation);
        reservationResponse.setStatus("Waiting");

        return reservationResponse;
    }

    public String waitingDeleteBySlot(Slot slot, String email) throws InvalidDataException {
        Waiting waiting = waitingDB.getWaitingByDetails(slot.getSpaceID(), slot.getStartDateTime(),
                slot.getEndDateTime(), email);

        if (waiting == null)
            throw new InvalidDataException("invalid");

        if (!email.equals(userDB.getUserById(waiting.getReservedById()).get().getEmail())
                && !email.equals(userDB.getUserById(waiting.getResponsiblePersonId()).get().getEmail())
                && !adminDB.getAdminByEmail(email).isPresent())
            throw new InvalidDataException("invalid");

        waitingDB.deleteWaiting(waiting.getId());

        return "Deleted";
    }

    public ReservationResponse WaitingToRequest(Waiting reservation) {
        ReservationResponse reservationResponse = new ReservationResponse();

        reservationResponse.setSpaceId(reservation.getSpaceID());
        reservationResponse.setTitle(reservation.getTitle());

        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("dd-MM-yyyy");
        reservationResponse.setDate(simpleDateFormat.format(reservation.getStartDateTime()));

        reservationResponse.setStartTime(
                reservation.getStartDateTime().getHours() * 100 + reservation.getStartDateTime().getMinutes());
        reservationResponse
                .setEndTime(reservation.getEndDateTime().getHours() * 100 + reservation.getEndDateTime().getMinutes());

        if (responsiblePersonDB.getResponsiblePersonById(reservation.getReservedById()).isPresent()) {
            reservationResponse.setReservedBy(
                    responsiblePersonDB.getResponsiblePersonById(reservation.getReservedById()).get().fullName());
        } else
            reservationResponse.setReservedBy(userDB.getUserById(reservation.getReservedById()).get().getFullName());

        reservationResponse.setResponsiblePerson(
                responsiblePersonDB.getResponsiblePersonById(reservation.getResponsiblePersonId()).get().fullName());

        return reservationResponse;
    }

    public Waiting requestToWaiting(ReservationRequest reservationRequest) {
        Waiting reservation = new Waiting();

        reservation.setTitle(reservationRequest.getTitle());
        reservation.setReservationDateTime(new Date(Long.parseLong(reservationRequest.getReservationDateTime())));
        reservation.setSpaceID(reservationRequest.getSpaceID());

        try {
            reservation.setStartDateTime(new SimpleDateFormat("dd-MM-yyyy").parse(reservationRequest.getDate()));
            reservation.setEndDateTime(new SimpleDateFormat("dd-MM-yyyy").parse(reservationRequest.getDate()));
        } catch (Exception e) {
            System.out.println(e);
        }

        reservation.getStartDateTime().setHours(reservationRequest.getStartTime() / 100);
        reservation.getStartDateTime().setMinutes(reservationRequest.getStartTime() % 100);
        reservation.getEndDateTime().setHours(reservationRequest.getEndTime() / 100);
        reservation.getEndDateTime().setMinutes(reservationRequest.getEndTime() % 100);

        reservation.setReservedById(reservationRequest.getReservedBy());
        reservation.setResponsiblePersonId(reservationRequest.getResponsiblePerson());

        DateFormat dateFormat = new SimpleDateFormat("dd-MM-yyyy");
        reservation.setDate(dateFormat.format(reservation.getStartDateTime()));

        return reservation;
    }

}
