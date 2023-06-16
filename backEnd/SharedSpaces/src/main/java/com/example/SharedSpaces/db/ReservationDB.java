package com.example.SharedSpaces.db;

import com.example.SharedSpaces.models.Reservation;
import com.example.SharedSpaces.models.User;
import com.example.SharedSpaces.models.Waiting;
import com.example.SharedSpaces.repos.ReservationRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

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


}
