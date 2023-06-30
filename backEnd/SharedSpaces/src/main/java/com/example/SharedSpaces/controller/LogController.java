package com.example.SharedSpaces.controller;

import com.example.SharedSpaces.controller.RequestResponse.LogRequest;
import com.example.SharedSpaces.controller.RequestResponse.LogResponse;
import com.example.SharedSpaces.exception.InvalidDataException;
import com.example.SharedSpaces.exception.InvalidEmailException;
import com.example.SharedSpaces.service.LogService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

// The @CrossOrigin annotation enables Cross-Origin Resource Sharing (CORS) for this controller
@CrossOrigin
// The @RestController annotation indicates that this class is a REST controller
@RestController
// The @RequestMapping annotation maps HTTP requests to a specific URL or URL pattern
@RequestMapping("log")
public class LogController {

    // The LogService used by this controller
    private final LogService log;

    // The client ID used for authentication
    @Value("${client.id}")
    private String clientId;

    // Constructor for creating a new LogController object
    @Autowired
    public LogController(LogService log) {
        this.log = log;
    }

    // The @PostMapping annotation maps HTTP POST requests to the /log endpoint
    @PostMapping
    public LogResponse getLog(@RequestBody LogRequest logRequest) throws ResponseStatusException {

        // Check if the client ID in the request matches the configured client ID
        if (!clientId.equals(logRequest.getClientId())) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "invalid\n");
        }

        try {
            // Call the log() method of the LogService and return the result as a LogResponse object
            return log.log(logRequest.getCredential());
        } catch (InvalidEmailException e) {
            // Throw a ResponseStatusException with HTTP status code 403 (Forbidden) if the email is invalid
            throw new ResponseStatusException(HttpStatus.FORBIDDEN, "invalidEmail\n");
        } catch (InvalidDataException e){
            // Throw a ResponseStatusException with HTTP status code 400 (Bad Request) if the data is invalid
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "invalid\n");
        }

    }

}

