package com.example.SharedSpaces.repos;

import com.example.SharedSpaces.models.Waiting;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.Date;
import java.util.List;
import java.util.Optional;

@Repository
public interface WaitingRepository extends CrudRepository<Waiting, Long> {
    List<Waiting> findBySpaceIDAndStartDateTimeAndEndDateTime(int spaceID, Date startDateTime, Date endDateTime);
    void deleteByReservationDateTimeBefore(LocalDateTime maxReservationDateTime);
}
