package com.example.SharedSpaces.db;

import com.example.SharedSpaces.models.Reservation;
import com.example.SharedSpaces.models.Waiting;
import com.example.SharedSpaces.repos.ReservationRepository;
import com.example.SharedSpaces.repos.WaitingRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.*;

@Service
public class WaitingDB {

    private WaitingRepository waitingRepository;
    private UserDB userDB;

    @Autowired
    public WaitingDB(WaitingRepository waitingRepository, UserDB userDB){
        this.waitingRepository = waitingRepository;
        this.userDB = userDB;
    }

    public List<Waiting> getAllWaiting() {
        return (List<Waiting>) waitingRepository.findAll();
    }

    public List<Waiting> getAllWaiting(String email) {
        return (List<Waiting>) waitingRepository.findByReservedById(userDB.getUserByEmail(email).get().getId());
    }

    public List<Waiting> getAllResponsibleWaiting(String email) {
        return (List<Waiting>) waitingRepository.findByResponsiblePersonId(userDB.getUserByEmail(email).get().getId());
    }

    public Optional<Waiting> getWaiitingById(Long id) {
        return waitingRepository.findById(id);
    }

    public Waiting createWaiting(Waiting reservation) {
        return waitingRepository.save(reservation);
    }

    public Waiting updateWaiting(Long id, Waiting reservation) {
        reservation.setId(id);
        return waitingRepository.save(reservation);
    }

    public void deleteWaiting(Long id) {
        waitingRepository.deleteById(id);
    }

    public List<Waiting> getWaitingByDetails(int spaceID, Date startDateTime, Date endDateTime) {
        if (startDateTime == null || endDateTime == null) {
            return null;
        }

        DateFormat dateFormat = new SimpleDateFormat("dd-MM-yyyy");
        List<Waiting> waitingList = waitingRepository.findBySpaceIDAndDate(spaceID, dateFormat.format(startDateTime));

        List<Waiting> waitings = new ArrayList<>();

        for (Waiting waiting: waitingList){
            if ((waiting.getStartDateTime().compareTo(startDateTime) >= 0 && waiting.getStartDateTime().compareTo(endDateTime) < 0) || (waiting.getEndDateTime().compareTo(startDateTime) > 0 && waiting.getEndDateTime().compareTo(endDateTime) <= 0)){
                waitings.add(waiting);
            }
        }

        if (waitings.isEmpty()) {

            // In here, we are returning an empty optional
            return null;
        }

        return waitings;
    }

    public Waiting getWaitingByDetails(int spaceID, Date startDateTime, Date endDateTime, String email) {
        if (startDateTime == null || endDateTime == null) {
            return null;
        }

        DateFormat dateFormat = new SimpleDateFormat("dd-MM-yyyy");
        Waiting waitingList = waitingRepository.findBySpaceIDAndDateAndReservedById(spaceID, dateFormat.format(startDateTime), userDB.getUserByEmail(email).get().getId());

        if (waitingList == null){
            waitingList = waitingRepository.findBySpaceIDAndDateAndResponsiblePersonId(spaceID, dateFormat.format(startDateTime), userDB.getUserByEmail(email).get().getId());
        }


        if (waitingList == null) {

            // In here, we are returning an empty optional
            return null;
        }

        return waitingList;
    }


}
