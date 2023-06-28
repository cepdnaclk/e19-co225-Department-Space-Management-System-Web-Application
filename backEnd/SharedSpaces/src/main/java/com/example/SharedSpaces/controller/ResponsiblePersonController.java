package com.example.SharedSpaces.controller;

import com.example.SharedSpaces.models.ResponsiblePerson;
import com.example.SharedSpaces.service.ResponsiblePersonService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

// The @CrossOrigin annotation enables Cross-Origin Resource Sharing (CORS) for this controller
@CrossOrigin
// The @RestController annotation indicates that this class is a REST controller
@RestController
// The @RequestMapping annotation maps HTTP requests to a specific URL or URL pattern
@RequestMapping("responsible")
public class ResponsiblePersonController {

    // The ResponsiblePersonService used by this controller
    private final ResponsiblePersonService responsiblePersonService;

    // Constructor for creating a new ResponsiblePersonController object
    @Autowired
    public ResponsiblePersonController(ResponsiblePersonService responsiblePersonService) {
        this.responsiblePersonService = responsiblePersonService;
    }

    // The @GetMapping annotation maps HTTP GET requests to the /responsible endpoint
    @GetMapping
    public List<ResponsiblePerson> getAllResponsiblePerson() {
        // Call the getAllResponsiblePersons() method of the ResponsiblePersonService and return the result as a List of ResponsiblePerson objects
        return responsiblePersonService.getAllResponsiblePersons();
    }

}

