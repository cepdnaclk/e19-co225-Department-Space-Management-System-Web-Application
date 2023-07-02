package com.example.SharedSpaces.repos;

import com.example.SharedSpaces.models.Waiting;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.Date;
import java.util.List;

@Repository
// This interface provides CRUD operations for the Waiting entity.
public interface WaitingRepository extends CrudRepository<Waiting, Long> {

    // Finds a list of Waiting entities by space ID, start date/time, and end date/time.
    List<Waiting> findBySpaceIDAndStartDateTimeAndEndDateTime(int spaceID, Date startDateTime, Date endDateTime);

    // Finds a list of Waiting entities by space ID and date.
    List<Waiting> findBySpaceIDAndDate(int spaceID, String date);

    // Finds a Waiting entity by space ID, date, and reserved by ID.
    Waiting findBySpaceIDAndDateAndReservedById(int spaceID, String date, long reservedById);
    Waiting findBySpaceIDAndDateAndReservedByIdAndResponsiblePersonId(int spaceID, String date, long reservedById, long responsiblePersonId);

    // Finds a Waiting entity by space ID, date, and responsible person ID.
    Waiting findBySpaceIDAndDateAndResponsiblePersonId(int spaceID, String date, long responsiblePersonId);

    // Finds a list of Waiting entities by reserved by ID.
    List<Waiting> findByReservedById(long id);

    // Finds a list of Waiting entities by reservation date/time before a given date/time.
    List<Waiting> findByReservationDateTimeBefore(Date reservationDateTime);

    // Finds a list of Waiting entities by responsible person ID.
    List<Waiting> findByResponsiblePersonId(long id);
}