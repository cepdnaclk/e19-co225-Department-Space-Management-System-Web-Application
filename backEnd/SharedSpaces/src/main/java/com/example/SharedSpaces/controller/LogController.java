package com.example.SharedSpaces.controller;

import com.example.SharedSpaces.controller.RequestResponse.LogRequest;
import com.example.SharedSpaces.controller.RequestResponse.LogResponse;
import com.example.SharedSpaces.models.User;
import com.example.SharedSpaces.service.LogService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.*;

@CrossOrigin
@RestController
@RequestMapping("log")
public class LogController {

    private final LogService log;
    private final String clientId = "461418541066-5c9p2cf0d6d8qthhgh7n2tjrd28pf3t9.apps.googleusercontent.com";

    @Autowired
    public LogController(LogService log) {
        this.log = log;
    }

    @PostMapping
    public LogResponse getLog(@RequestBody LogRequest logRequest) {

        if (!clientId.equals(logRequest.getClientId()))
        {
            LogResponse response = new LogResponse();
            response.setValid(false);
            return response;
        }

        return log.log(logRequest.getCredential());
    }

}
