package com.example.SharedSpaces.service;

import com.example.SharedSpaces.controller.RequestResponse.LogResponse;
import com.example.SharedSpaces.models.User;
import com.example.SharedSpaces.security.JwtService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.ArrayList;

import java.util.*;

@Service
public class LogService {

    private final JwtService jwtService;

    @Autowired
    public LogService(JwtService jwtService) {
        this.jwtService = jwtService;
    }



    public LogResponse log(String credential){

        try {
            User user = jwtService.extractClaimsGoogle(credential);

            // get role
//          String = 

            if (isEmailValid(user.getEmail())) {

                Map<String, Object> map = new HashMap<>();
                map.put("user", user);
                map.put("role", "manager");

                String reFreshToken = jwtService.generateRefreshToken(map, user);

                LogResponse response = new LogResponse(reFreshToken);
                response.setValid(true);

                return response;
            }

            LogResponse response = new LogResponse();
            response.setValid(false);
            return response;

        } catch (Exception e){
            LogResponse response = new LogResponse();
            response.setValid(false);
            return response;
        }
    }

    public boolean isEmailValid(String email){

        if (email.substring(email.length()-14, email.length()).equals("@eng.pdn.ac.lk"))
            return true;
        else
            return false;

    }



}
