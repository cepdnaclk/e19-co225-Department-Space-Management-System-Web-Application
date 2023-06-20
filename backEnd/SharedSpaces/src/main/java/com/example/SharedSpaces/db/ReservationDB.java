package com.example.SharedSpaces.db;

import com.example.SharedSpaces.models.Reservation;
import com.example.SharedSpaces.models.User;
import com.example.SharedSpaces.repos.ReservationRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.Date;
import java.util.List;
import java.util.Optional;

@Service
public class ReservationDB {

    private ReservationRepository reservationRepository;

    @Autowired
    public ReservationDB(ReservationRepository reservationRepository){
        this.reservationRepository = reservationRepository;
    }

    public List<Reservation> getAllResevation() {
        return (List<Reservation>) reservationRepository.findAll();
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

            // In here, we are returning an empty optional
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

    @Scheduled(cron = "0 0 0 * * ?") // Run every day at midnight
    public void deleteExpiredReservations() {
        LocalDate expirationDate = LocalDate.now().minusDays(30);
        List<Reservation> expiredReservations = reservationRepository.findByReservationDateTimeBefore(expirationDate);
        reservationRepository.deleteAll(expiredReservations);
    }


}
