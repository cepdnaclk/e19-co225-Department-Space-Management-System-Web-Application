package com.example.SharedSpaces.controller;

import com.example.SharedSpaces.controller.RequestResponse.LogRequest;
import com.example.SharedSpaces.controller.RequestResponse.LogResponse;
import com.example.SharedSpaces.exception.InvalidEmailException;
import com.example.SharedSpaces.service.LogService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

@CrossOrigin
@RestController
@RequestMapping("log")
public class LogController {

    private final LogService log;

    @Value("${client.id}")
    private String clientId = "461418541066-5c9p2cf0d6d8qthhgh7n2tjrd28pf3t9.apps.googleusercontent.com" ;

    @Autowired
    public LogController(LogService log) {
        this.log = log;

    }

    @PostMapping
    public LogResponse getLog(@RequestBody LogRequest logRequest) throws ResponseStatusException {

        if (!clientId.equals(logRequest.getClientId())) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "invalid\n");
        }

        try{
            return log.log(logRequest.getCredential());
        } catch (InvalidEmailException e){
            throw new ResponseStatusException(HttpStatus.FORBIDDEN, "invalidEmail\n");
        }

    }

}
