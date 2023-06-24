package com.example.SharedSpaces.repos;

import com.example.SharedSpaces.models.Reservation;
import com.example.SharedSpaces.models.Waiting;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.Date;
import java.util.List;
import java.util.Optional;

@Repository
public interface WaitingRepository extends CrudRepository<Waiting, Long> {
    List<Waiting> findBySpaceIDAndStartDateTimeAndEndDateTime(int spaceID, Date startDateTime, Date endDateTime);

    List<Waiting> findBySpaceIDAndDate(int spaceID, String date);

    Waiting findBySpaceIDAndDateAndReservedById(int spaceID, String date, long reservedById);
    Waiting findBySpaceIDAndDateAndResponsiblePersonId(int spaceID, String date, long responsiblePersonId);

    List<Waiting> findByReservedById(long id);

    List<Waiting> findByReservationDateTimeBefore(Date reservationDateTime);

    List<Waiting> findByResponsiblePersonId(long id);
}
