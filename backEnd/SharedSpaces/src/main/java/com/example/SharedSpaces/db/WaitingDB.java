package com.example.SharedSpaces.db;

import com.example.SharedSpaces.models.Waiting;
import com.example.SharedSpaces.repos.WaitingRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.*;

// The @Service annotation indicates that this class is a Spring service
@Service
public class WaitingDB {

    // The WaitingRepository used by this class
    private WaitingRepository waitingRepository;

    // The UserDB used by this class
    private UserDB userDB;

    // Constructor for creating a new WaitingDB object
    @Autowired
    public WaitingDB(WaitingRepository waitingRepository, UserDB userDB) {
        this.waitingRepository = waitingRepository;
        this.userDB = userDB;
    }

    // Returns a List of all Waiting objects in the database
    public List<Waiting> getAllWaiting() {
        return (List<Waiting>) waitingRepository.findAll();
    }

    // Returns a List of all Waiting objects in the database reserved by the user
    // with the provided email
    public List<Waiting> getAllWaiting(String email) {
        return (List<Waiting>) waitingRepository.findByReservedById(userDB.getUserByEmail(email).get().getId());
    }

    // Returns a List of all Waiting objects in the database where the user with the
    // provided email is the responsible person
    public List<Waiting> getAllResponsibleWaiting(String email) {
        return (List<Waiting>) waitingRepository.findByResponsiblePersonId(userDB.getUserByEmail(email).get().getId());
    }

    // Returns an Optional of a Waiting object with the provided id
    public Optional<Waiting> getWaiitingById(Long id) {
        return waitingRepository.findById(id);
    }

    // Creates a new Waiting object in the database and returns the created object
    public Waiting createWaiting(Waiting reservation) {
        return waitingRepository.save(reservation);
    }

    // Updates an existing Waiting object in the database with the provided id and
    // returns the updated object
    public Waiting updateWaiting(Long id, Waiting reservation) {
        reservation.setId(id);
        return waitingRepository.save(reservation);
    }

    // Deletes a Waiting object from the database with the provided id
    public void deleteWaiting(Long id) {
        waitingRepository.deleteById(id);
    }

    // Returns a List of all Waiting objects in the database that match the provided
    // details
    public List<Waiting> getWaitingByDetails(int spaceID, Date startDateTime, Date endDateTime) {
        if (startDateTime == null || endDateTime == null) {
            return null;
        }

        DateFormat dateFormat = new SimpleDateFormat("dd-MM-yyyy");
        List<Waiting> waitingList = waitingRepository.findBySpaceIDAndDate(spaceID, dateFormat.format(startDateTime));

        List<Waiting> waitings = new ArrayList<>();

        DateFormat dateFormatRes = new SimpleDateFormat("yyyy-MM-dd HH:mm");

        for (Waiting waiting : waitingList) {

            if ((waiting.getStartDateTime().before(startDateTime)
                    && waiting.getEndDateTime().after(startDateTime))
                    || (waiting.getStartDateTime().before(endDateTime)
                            && waiting.getEndDateTime().after(endDateTime))
                    || (dateFormatRes.format(waiting.getStartDateTime())
                            .equals(dateFormatRes.format(startDateTime))
                            && dateFormatRes.format(waiting.getEndDateTime())
                                    .equals(dateFormatRes.format(endDateTime)))
                    || (startDateTime.before(waiting.getStartDateTime())
                            && endDateTime.after(waiting.getStartDateTime()))
                    || (startDateTime.after(waiting.getStartDateTime())
                            && endDateTime.before(waiting.getEndDateTime()))
                    || (startDateTime.before(waiting.getEndDateTime())
                            && endDateTime.after(waiting.getEndDateTime()))
                    || (startDateTime.before(waiting.getStartDateTime())
                            && endDateTime.after(waiting.getEndDateTime()))) {

                waitings.add(waiting);
            }
        }

        if (waitings.isEmpty()) {

            // In here, we are returning null
            return null;
        }

        return waitings;
    }

    // Returns a Waiting object in the database that match the provided details and
    // reserved by the user with the provided email
    public Waiting getWaitingByDetails(int spaceID, Date startDateTime, Date endDateTime, String email) {
        if (startDateTime == null || endDateTime == null) {
            return null;
        }

        DateFormat dateFormat = new SimpleDateFormat("dd-MM-yyyy");
        Waiting waitingList = waitingRepository.findBySpaceIDAndDateAndReservedById(spaceID,
                dateFormat.format(startDateTime), userDB.getUserByEmail(email).get().getId());

        if (waitingList == null) {
            waitingList = waitingRepository.findBySpaceIDAndDateAndResponsiblePersonId(spaceID,
                    dateFormat.format(startDateTime), userDB.getUserByEmail(email).get().getId());
        }

        if (waitingList == null) {

            // In here, we are returning null
            return null;
        }

        return waitingList;
    }

    // Returns a Waiting object in the database that match the provided details and
    // reserved by the user with the provided id
    public Waiting getWaitingByDetails(int spaceID, Date startDateTime, Date endDateTime, long reserdById) {
        if (startDateTime == null || endDateTime == null) {
            return null;
        }

        DateFormat dateFormat = new SimpleDateFormat("dd-MM-yyyy");
        Waiting waitingList = waitingRepository.findBySpaceIDAndDateAndReservedById(spaceID,
                dateFormat.format(startDateTime), reserdById);

        if (waitingList == null) {

            // In here, we are returning null
            return null;
        }

        return waitingList;
    }

    // Returns a Waiting object in the database that match the provided details and
    // reserved by the user with the provided id
    public Waiting getWaitingByDetails(int spaceID, Date startDateTime, Date endDateTime, long reserdById,
            long responsible) {
        if (startDateTime == null || endDateTime == null) {
            return null;
        }

        Waiting waitingList = waitingRepository
                .findBySpaceIDAndStartDateTimeAndEndDateTimeAndAndReservedByIdAndResponsiblePersonId(spaceID,
                        startDateTime, endDateTime, reserdById, responsible);

        if (waitingList == null) {

            // In here, we are returning null
            return null;
        }

        return waitingList;
    }

    // Deletes all expired reservations from the database
    @Scheduled(cron = "0 0 0 * * ?") // Run every day at midnight
    public void deleteExpiredReservations() {
        Date to = new Date();
        Date from = new Date(to.getTime() - 1000 * 60 * 60 * 24 * 1);
        List<Waiting> expiredReservations = waitingRepository.findByReservationDateTimeBefore(from);
        waitingRepository.deleteAll(expiredReservations);
    }

}
