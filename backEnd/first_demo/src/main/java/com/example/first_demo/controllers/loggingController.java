package com.example.first_demo.controllers;


import com.example.first_demo.models.Log;
import com.example.first_demo.services.loggingServices;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("log")
public class loggingController {

    private final loggingServices log;

    @Autowired
    public loggingController(loggingServices log){
        this.log = log;
    }

    @GetMapping
    public String getLogging(){
        return "log";
    }

    @PostMapping
    public Log getLog(@RequestBody String data){
        System.out.println(data);
        return log.checkToken(data);
    }

}
