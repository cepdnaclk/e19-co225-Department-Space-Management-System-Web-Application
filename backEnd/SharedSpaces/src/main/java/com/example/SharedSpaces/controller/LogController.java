package com.example.SharedSpaces.controller;

import com.example.SharedSpaces.models.User;
import com.example.SharedSpaces.service.LogService;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("log")
public class LogController {

    private final LogService log;

    public LogController(LogService log) {
        this.log = log;
    }

    @GetMapping
    public String getLogging() {
        return "log";
    }

    @PostMapping
    public User getLog(@RequestBody String data) {
        return log.checkToken(data.substring(7, data.length()));
    }


    @PutMapping()
    public void updateLogging(){

    }

    @DeleteMapping()
    public  void deleteLogging(){

    }


}
