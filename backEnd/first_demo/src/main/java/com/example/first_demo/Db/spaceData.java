package com.example.first_demo.Db;

import com.example.first_demo.models.Space;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;

@Repository
public class spaceData {

    private List<Space> spaceDb = new ArrayList<Space>();

    List<Space> getAllSpaces(){
        return spaceDb;
    }

}
