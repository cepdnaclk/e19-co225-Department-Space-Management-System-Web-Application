package com.example.SharedSpaces.repos;

import com.example.SharedSpaces.models.Reservation;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.Date;
import java.util.List;
import java.util.Optional;

@Repository
// This interface provides CRUD operations for the Reservation entity.
public interface ReservationRepository extends CrudRepository<Reservation, Long> {

    // Finds a Reservation entity by space ID, start date/time, and end date/time.
    Optional<Reservation> findBySpaceIDAndStartDateTimeAndEndDateTime(int spaceID, Date startDateTime,
                                                                      Date endDateTime);

    // Finds a list of Reservation entities by space ID and date.
    List<Reservation> findBySpaceIDAndDate(int spaceID, String date);

    // Finds a Reservation entity by space ID, date, and reserved by ID.
    Reservation findBySpaceIDAndDateAndReservedById(int spaceID, String date, long reservedById);

    // Finds a Reservation entity by space ID, date, and responsible person ID.
    Reservation findBySpaceIDAndDateAndResponsiblePersonId(int spaceID, String date, long responsiblePersonId);

    // Finds a list of Reservation entities by reserved by ID.
    List<Reservation> findByReservedById(long id);

    // Finds a list of Reservation entities by reservation date/time before a given date/time.
    List<Reservation> findByReservationDateTimeBefore(Date reservationDateTime);

    // Finds a list of Reservation entities by responsible person ID.
    List<Reservation> findByResponsiblePersonId(long id);
}