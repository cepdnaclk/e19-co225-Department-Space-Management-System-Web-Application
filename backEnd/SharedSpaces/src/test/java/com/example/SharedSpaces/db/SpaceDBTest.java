package com.example.SharedSpaces.db;

import com.example.SharedSpaces.SharedSpacesApplication;
import com.example.SharedSpaces.models.Reservation;
import com.example.SharedSpaces.models.Space;

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
public class SpaceDBTest {

    @Autowired
    private SpaceDB spaceDB;

    @Test
    public void checkGetAllReservationDB() {

        List<Space> spaces = spaceDB.getAllSpacess();

        assertNotNull(spaces);

    }
}
