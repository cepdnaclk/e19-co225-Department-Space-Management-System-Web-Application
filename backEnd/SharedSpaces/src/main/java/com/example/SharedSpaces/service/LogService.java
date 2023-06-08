package com.example.SharedSpaces.service;

import com.example.SharedSpaces.controller.RequestResponse.LogResponse;
import com.example.SharedSpaces.models.User;
import com.example.SharedSpaces.security.JwtService;
import org.springframework.stereotype.Service;
import java.util.ArrayList;

import java.util.*;

@Service
public class LogService {

    private final JwtService jwtService;

    public LogService(JwtService jwtService) {
        this.jwtService = jwtService;
    }

    public User extractClaims(String token) {

        String payLoadToken = token.split("\\.")[1];

        Base64.Decoder decoder = Base64.getUrlDecoder();

        String payLoad = new String(decoder.decode(payLoadToken));
        Map<String, String> map = new HashMap<>();

        for (String element: payLoad.substring(1,payLoad.length()-1).split("\\,")){
            String[] elements = element.split("\\:");
            map.put(elements[0], elements[1]);
        }

        List<String> payLoadValues = new ArrayList<String>(map.values());

        return new User(payLoadValues.get(6).substring(1,payLoadValues.get(6).length()-1 ), payLoadValues.get(2).substring(1,payLoadValues.get(2).length()-1 ), payLoadValues.get(7).substring(1,payLoadValues.get(7).length()-1 ));

    }

    public LogResponse log(String credential){

        User user = extractClaims(credential);

        // get role
//        String =


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
    }

    public boolean isEmailValid(String email){

        if (email.substring(email.length()-14, email.length()).equals("@eng.pdn.ac.lk"))
            return true;
        else
            return false;

    }



}
