package com.example.SharedSpaces.controller;

import com.example.SharedSpaces.models.Space;
import com.example.SharedSpaces.service.SpaceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

// The @CrossOrigin annotation enables Cross-Origin Resource Sharing (CORS) for this controller
@CrossOrigin
// The @RestController annotation indicates that this class is a REST controller
@RestController
// The @RequestMapping annotation maps HTTP requests to a specific URL or URL pattern
@RequestMapping("space")
public class SpaceController {

    // The SpaceService used by this controller
    private final SpaceService spaceService;

    // Constructor for creating a new SpaceController object
    @Autowired
    public SpaceController(SpaceService spaceService) {
        this.spaceService = spaceService;
    }

    // The @GetMapping annotation maps HTTP GET requests to the /space endpoint
    @GetMapping
    public List<Space> getAllSpaces() {
        // Call the getAllSpaces() method of the SpaceService and return the result as a List of Space objects
        return spaceService.getAllSpaces();
    }

}

