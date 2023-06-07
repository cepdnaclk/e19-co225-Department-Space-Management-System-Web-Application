package com.example.SharedSpaces.db;

import com.example.SharedSpaces.models.Space;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@Repository
public class SpaceDB {

    private List<Space> spaceDb = new ArrayList<Space>();

    public List<Space> selectAllSpaces() {
        spaceDb.add(new Space(1, "Lab1", "floor 1", 50, new ArrayList<String>(Arrays.asList("AC")), "image"));
        spaceDb.add(new Space(2, "Lab1", "floor 1", 50, new ArrayList<String>(Arrays.asList("AC")), "image"));
        spaceDb.add(new Space(3, "Lab1", "floor 1", 50, new ArrayList<String>(Arrays.asList("AC")), "image"));
        return spaceDb;
    }

}
