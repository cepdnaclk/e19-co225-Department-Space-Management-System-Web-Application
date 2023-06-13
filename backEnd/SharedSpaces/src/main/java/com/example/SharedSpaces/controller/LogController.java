package com.example.SharedSpaces.controller;

import com.example.SharedSpaces.controller.RequestResponse.LogRequest;
import com.example.SharedSpaces.controller.RequestResponse.LogResponse;
import com.example.SharedSpaces.models.User;
import com.example.SharedSpaces.service.LogService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("log")
public class LogController {

    private final LogService log;

    @Autowired
    public LogController(LogService log) {
        this.log = log;
    }

    @GetMapping
    public String getLogging() {
        return "log";
    }

    @PostMapping
    public LogResponse getLog(@RequestBody LogRequest logRequest) {


        return log.log(logRequest.getCredential());
    }


    @PutMapping()
    public void updateLogging(){

    }

    @DeleteMapping()
    public  void deleteLogging(){

    }


}
