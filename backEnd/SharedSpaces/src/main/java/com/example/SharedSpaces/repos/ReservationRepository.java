package com.example.SharedSpaces.repos;

import com.example.SharedSpaces.models.Admin;
import com.example.SharedSpaces.models.Reservation;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.Date;
import java.util.List;
import java.util.Optional;

@Repository
public interface ReservationRepository extends CrudRepository<Reservation, Long> {
    Optional<Reservation> findBySpaceIDAndStartDateTimeAndEndDateTime(int spaceID, Date startDateTime, Date endDateTime);

    List<Reservation> findByReservationDateTimeBefore(LocalDate expirationDate);

}
