package com.example.SharedSpaces.service;

import com.example.SharedSpaces.db.SpaceDB;
import com.example.SharedSpaces.models.Space;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class SpaceService {

    // Declare dependency
    private final SpaceDB spacedata;

    // Constructor injection
    @Autowired
    public SpaceService(SpaceDB spacedata) {
        this.spacedata = spacedata;
    }

    // Get all spaces
    public List<Space> getAllSpaces() {
        return spacedata.getAllSpacess();
    }

}