package com.example.SharedSpaces.service;

import com.example.SharedSpaces.models.User;
import org.springframework.stereotype.Service;

@Service
public class LogService {

    public User checkToken(String token) {
        return new User("Kanishka", "Student");
    }

}
