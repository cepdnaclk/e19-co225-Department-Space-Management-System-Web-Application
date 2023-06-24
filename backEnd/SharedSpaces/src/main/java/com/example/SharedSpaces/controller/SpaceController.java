package com.example.SharedSpaces.controller;

import com.example.SharedSpaces.models.Space;
import com.example.SharedSpaces.service.SpaceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin
@RestController
@RequestMapping("space")
public class SpaceController {

    private final SpaceService spaceservices;

    @Autowired
    public SpaceController(SpaceService spaceservices) {
        this.spaceservices = spaceservices;
    }

    @GetMapping
    public List<Space> getAllSpaces(){
        return spaceservices.getAllSpaces();
    }


}
