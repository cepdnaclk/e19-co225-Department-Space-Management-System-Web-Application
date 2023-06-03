package com.example.first_demo.controllers;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("user")
public class userController {

    record userDetails(String firstName, String lastName){};

    @GetMapping
    public userDetails getUser(){
        return new userDetails("Kanishka", "Harsha");
    }

}
