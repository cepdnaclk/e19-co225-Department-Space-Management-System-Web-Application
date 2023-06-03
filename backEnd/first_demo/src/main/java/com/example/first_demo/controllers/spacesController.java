package com.example.first_demo.controllers;

import com.example.first_demo.models.Space;
import com.example.first_demo.services.spaceServices;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("space")
public class spacesController {

    private final spaceServices spaceservices;

    @Autowired
    public spacesController(spaceServices spaceservices){
        this.spaceservices = spaceservices;
    }

    @GetMapping
    public List<Space> getAllSpaces(){
        return spaceservices.getAllSpaces();
    }

    @PostMapping
    public void addSpace(){

    }

    @PutMapping(path = "{spaceId}")
    public void updateSopace(){

    }

    @DeleteMapping(path = "{spaceId}")
    public  void deleteSpace(){

    }

}
