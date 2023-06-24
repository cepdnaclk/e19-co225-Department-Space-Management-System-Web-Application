package com.example.SharedSpaces.db;

import com.example.SharedSpaces.models.Reservation;
import com.example.SharedSpaces.models.User;
import com.example.SharedSpaces.models.Waiting;
import com.example.SharedSpaces.repos.ReservationRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;

@Service
public class ReservationDB {

    private ReservationRepository reservationRepository;
    private UserDB userDB;

    @Autowired
    public ReservationDB(ReservationRepository reservationRepository, UserDB userDB){
        this.reservationRepository = reservationRepository;
        this.userDB = userDB;
    }

    public List<Reservation> getAllResevation() {
        return (List<Reservation>) reservationRepository.findAll();
    }

    public List<Reservation> getAllResevation(String email) {
        return (List<Reservation>) reservationRepository.findByReservedById(userDB.getUserByEmail(email).get().getId());
    }

    public List<Reservation> getAllResponsibleWaiting(String email) {
        return (List<Reservation>) reservationRepository.findByResponsiblePersonId(userDB.getUserByEmail(email).get().getId());
    }

    public Optional<Reservation> getReservationById(Long id) {
        return reservationRepository.findById(id);
    }

    public Optional<Reservation> getReservationByDetails(int spaceID, Date startDateTime, Date endDateTime) {
        if (startDateTime == null || endDateTime == null) {
            return Optional.empty();
        }

        Optional<Reservation> optionalReservation = reservationRepository.findBySpaceIDAndStartDateTimeAndEndDateTime(spaceID, startDateTime, endDateTime);

        if (!optionalReservation.isPresent()) {

            return Optional.empty();
        }
        return optionalReservation;
    }

    public List<Reservation> getReservationsByDetails(int spaceID, Date startDateTime, Date endDateTime) {
        if (startDateTime == null || endDateTime == null) {
            return null;
        }

        DateFormat dateFormat = new SimpleDateFormat("dd-MM-yyyy");
        List<Reservation> waitingList = reservationRepository.findBySpaceIDAndDate(spaceID, dateFormat.format(startDateTime));

        List<Reservation> waitings = new ArrayList<>();

        for (Reservation waiting: waitingList){
            if ((waiting.getStartDateTime().compareTo(startDateTime) >= 0 && waiting.getStartDateTime().compareTo(endDateTime) < 0) || (waiting.getEndDateTime().compareTo(startDateTime) > 0 && waiting.getEndDateTime().compareTo(endDateTime) <= 0)){
                waitings.add(waiting);
            }
        }

        if (waitings.isEmpty()) {
            return null;
        }

        return waitings;
    }


    public Reservation createReservation(Reservation reservation) {
        return reservationRepository.save(reservation);
    }

    public Reservation updateReservation(Long id, Reservation reservation) {
        reservation.setId(id);
        return reservationRepository.save(reservation);
    }

    public void deleteReservation(Long id) {
        reservationRepository.deleteById(id);
    }

    @Scheduled(cron = "0 0 0 * * ?") // Run every day at midnight
    public void deleteExpiredReservations() {
        Date to = new Date();
        Date from = new Date(to.getTime()-1000*60*60*24*30);
        List<Reservation> expiredReservations = reservationRepository.findByReservationDateTimeBefore(from);
        reservationRepository.deleteAll(expiredReservations);
    }


}
