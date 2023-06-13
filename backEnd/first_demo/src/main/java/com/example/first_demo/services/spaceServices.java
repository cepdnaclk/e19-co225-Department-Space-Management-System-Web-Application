package com.example.first_demo.services;

import com.example.first_demo.Db.spaceData;
import com.example.first_demo.models.Space;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class spaceServices {

    private final spaceData spacedata;

    @Autowired
    public spaceServices(spaceData spacedata){
        this.spacedata = spacedata;
    }

    public List<Space> getAllSpaces(){
        return spacedata.selectAllSpaces();
    }

}
