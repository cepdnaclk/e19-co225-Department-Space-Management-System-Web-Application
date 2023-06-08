package com.example.SharedSpaces.service;

import com.example.SharedSpaces.models.User;
import org.springframework.stereotype.Service;

import java.util.Base64;

@Service
public class LogService {

    public User extractClaims(String token) {

        String payLoadToken = token.split("\\.")[1];

        Base64.Decoder decoder = Base64.getUrlDecoder();

        String payLoad = new String(decoder.decode(payLoadToken));

        System.out.println(payLoad);

        return new User("name", "email");

    }

}
