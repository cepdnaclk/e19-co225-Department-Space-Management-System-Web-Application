package com.example.SharedSpaces.controller;

import com.example.SharedSpaces.auth.RequestResponse.AuthenticationResponse;
import com.example.SharedSpaces.auth.AuthenticationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

// The @CrossOrigin annotation enables Cross-Origin Resource Sharing (CORS) for this controller
@CrossOrigin
// The @RestController annotation indicates that this class is a REST controller
@RestController
// The @RequestMapping annotation maps HTTP requests to a specific URL or URL pattern
@RequestMapping("/auth")
public class AuthenticationController {

    // The AuthenticationService used by this controller
    private final AuthenticationService service;

    // Constructor for creating a new AuthenticationController object
    @Autowired
    public AuthenticationController(AuthenticationService service) {
        this.service = service;
    }

    // The @PostMapping annotation maps HTTP POST requests to the /authenticate endpoint
    @CrossOrigin
    @PostMapping("/authenticate")
    public ResponseEntity<AuthenticationResponse> authenticate() {
        // Call the authenticate() method of the AuthenticationService and return the result as a ResponseEntity
        return ResponseEntity.ok(service.authenticate());
    }

    // The @PostMapping annotation maps HTTP POST requests to the /refresh-token endpoint
    @CrossOrigin
    @PostMapping("/refresh-token")
    public ResponseEntity<AuthenticationResponse> refreshToken() {
        // Call the refreshToken() method of the AuthenticationService and return the result as a ResponseEntity
        return ResponseEntity.ok(service.refreshToken());
    }

}

