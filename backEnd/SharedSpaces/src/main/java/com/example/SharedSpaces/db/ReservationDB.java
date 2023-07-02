package com.example.SharedSpaces.db;

import com.example.SharedSpaces.models.Reservation;
import com.example.SharedSpaces.repos.ReservationRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;

// The @Service annotation indicates that this class is a Spring service
@Service
public class ReservationDB {

    // The ReservationRepository used by this class
    private ReservationRepository reservationRepository;

    // The UserDB used by this class
    private UserDB userDB;

    // Constructor for creating a new ReservationDB object
    @Autowired
    public ReservationDB(ReservationRepository reservationRepository, UserDB userDB) {
        this.reservationRepository = reservationRepository;
        this.userDB = userDB;
    }

    // Returns a List of all Reservation objects in the database
    public List<Reservation> getAllResevation() {
        return (List<Reservation>) reservationRepository.findAll();
    }

    // Returns a List of all Reservation objects in the database for the given email
    public List<Reservation> getAllResevation(String email) {
        List<Reservation> reservations = (List<Reservation>) reservationRepository
                .findByReservedById(userDB.getUserByEmail(email).get().getId());

        if (reservations == null) {
            return new ArrayList<>();
        }

        return reservations;
    }

    // Returns a List of all Reservation objects in the database where the given
    // email is responsible for
    public List<Reservation> getAllResponsibleReservation(String email) {
        List<Reservation> reservations = (List<Reservation>) reservationRepository
                .findByResponsiblePersonId(userDB.getUserByEmail(email).get().getId());

        if (reservations == null) {
            return new ArrayList<>();
        }

        return reservations;
    }

    // Returns an Optional of a Reservation object with the provided id
    public Optional<Reservation> getReservationById(Long id) {
        return reservationRepository.findById(id);
    }

    // Returns an Optional of a Reservation object with the given space id, start
    // date and end date
    public Optional<Reservation> getReservationByDetails(int spaceID, Date startDateTime, Date endDateTime) {
        if (startDateTime == null || endDateTime == null) {
            return Optional.empty();
        }

        Optional<Reservation> optionalReservation = reservationRepository
                .findBySpaceIDAndStartDateTimeAndEndDateTime(spaceID, startDateTime, endDateTime);

        if (!optionalReservation.isPresent()) {
            return Optional.empty();
        }
        return optionalReservation;
    }

    // Returns a List of all Reservation objects in the database with the given
    // space id, start date and end date
    public List<Reservation> getReservationsByDetails(int spaceID, Date startDateTime, Date endDateTime) {
        if (startDateTime == null || endDateTime == null) {
            return null;
        }

        DateFormat dateFormat = new SimpleDateFormat("dd-MM-yyyy");
        List<Reservation> waitingList = reservationRepository.findBySpaceIDAndDate(spaceID,
                dateFormat.format(startDateTime));

        List<Reservation> waitings = new ArrayList<>();

        DateFormat dateFormatRes = new SimpleDateFormat("yyyy-MM-dd HH:mm");

        for (Reservation waiting : waitingList) {

            if ((waiting.getStartDateTime().before(startDateTime)
                    && waiting.getEndDateTime().after(startDateTime))
                    || (waiting.getStartDateTime().before(endDateTime)
                            && waiting.getEndDateTime().after(endDateTime))
                    || (dateFormatRes.format(waiting.getStartDateTime())
                            .equals(dateFormatRes.format(startDateTime))
                            && dateFormatRes.format(waiting.getEndDateTime())
                                    .equals(dateFormatRes.format(endDateTime)))
                    || (startDateTime.before(waiting.getStartDateTime())
                            && endDateTime.after(waiting.getEndDateTime()))) {

                waitings.add(waiting);
            }
        }

        if (waitings.isEmpty()) {
            return null;
        }

        return waitings;
    }

    // Creates a new Reservation object in the database and returns the created
    // object
    public Reservation createReservation(Reservation reservation) {
        return reservationRepository.save(reservation);
    }

    // Updates an existing Reservation object in the database with the provided id
    // and returns the updated object
    public Reservation updateReservation(Long id, Reservation reservation) {
        reservation.setId(id);
        return reservationRepository.save(reservation);
    }

    // Deletes a Reservation object from the database with the provided id
    public void deleteReservation(Long id) {
        reservationRepository.deleteById(id);
    }

    // Deletes expired reservations from the database every day at midnight
    @Scheduled(cron = "0 0 0 * * ?")
    public void deleteExpiredReservations() {
        Date to = new Date();
        Date from = new Date(to.getTime() - 1000 * 60 * 60 * 24 * 30);
        List<Reservation> expiredReservations = reservationRepository.findByReservationDateTimeBefore(from);
        reservationRepository.deleteAll(expiredReservations);
    }
}
