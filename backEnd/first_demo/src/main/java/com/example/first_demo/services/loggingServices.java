package com.example.first_demo.services;

import io.jsonwebtoken.Jwts;

import com.example.first_demo.models.Log;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;
import org.springframework.stereotype.Service;

import java.security.Key;

@Service
public class loggingServices {

    public Log checkToken(String token) {
        return new Log("Kanishka", "Student", "e19129@email");
    }

}
