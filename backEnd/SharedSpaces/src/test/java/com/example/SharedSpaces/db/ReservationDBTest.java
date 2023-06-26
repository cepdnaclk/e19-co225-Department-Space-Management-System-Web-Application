package com.example.SharedSpaces.db;

import com.example.SharedSpaces.SharedSpacesApplication;
import com.example.SharedSpaces.models.Reservation;
import java.util.*;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest(classes = SharedSpacesApplication.class)
public class ReservationDBTest {

    @Autowired
    private ReservationDB reservationDB;

    @Test
    public void checkReservationDBFull() {
        Reservation reservation = new Reservation();
        int spaceId = 9999;
        reservation.setSpaceID(spaceId);
        Date date = new Date();
        reservation.setStartDateTime(date);
        reservation.setEndDateTime(date);

        reservationDB.createReservation(reservation);

        Reservation getReservation = reservationDB.getReservationByDetails(spaceId, date, date).get();

        reservationDB.deleteReservation(getReservation.getId());

        assertNotNull(getReservation);
        assertEquals(getReservation.getSpaceID(), spaceId);
    }

    @Test
    public void checkGetAllReservationDB() {

        List<Reservation> reservations = reservationDB.getAllResevation();

        assertNotNull(reservations);

    }
}
