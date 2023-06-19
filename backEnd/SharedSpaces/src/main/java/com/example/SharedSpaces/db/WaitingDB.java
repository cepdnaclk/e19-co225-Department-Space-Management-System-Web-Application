package com.example.SharedSpaces.db;

import com.example.SharedSpaces.models.Reservation;
import com.example.SharedSpaces.models.Waiting;
import com.example.SharedSpaces.repos.ReservationRepository;
import com.example.SharedSpaces.repos.WaitingRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.Date;
import java.util.List;
import java.util.Optional;

@Service
public class WaitingDB {

    private WaitingRepository waitingRepository;

    @Autowired
    public WaitingDB(WaitingRepository waitingRepository){
        this.waitingRepository = waitingRepository;
    }

    public List<Waiting> getAllWaiting() {
        return (List<Waiting>) waitingRepository.findAll();
    }

    public Optional<Waiting> getWaiitingById(Long id) {
        return waitingRepository.findById(id);
    }

    public List<Waiting> getWaitingByDetails(int spaceID, Date startDateTime, Date endDateTime) {
        if (startDateTime == null || endDateTime == null) {
            return null;
        }

        List<Waiting> waitingList = waitingRepository.findBySpaceIDAndStartDateTimeAndEndDateTime(spaceID, startDateTime, endDateTime);

        if (waitingList.isEmpty()) {

            // In here, we are returning an empty optional
            return null;
        }

        return waitingList;
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

    @Scheduled(fixedRate = 60000) // runs every minute
    public void cleanWaitingList() {
        LocalDateTime currentDateTime = LocalDateTime.now();
        waitingRepository.deleteByReservationDateTimeBefore(currentDateTime);
    }


}
