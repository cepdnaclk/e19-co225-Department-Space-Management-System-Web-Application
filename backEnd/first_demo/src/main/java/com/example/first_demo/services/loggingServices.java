package com.example.first_demo.services;

import com.example.first_demo.models.Log;
import org.springframework.stereotype.Service;

@Service
public class loggingServices {

    public Log checkToken(String token){
        return new Log("Kanishka", "Student", "e19129@email");
    }
}
