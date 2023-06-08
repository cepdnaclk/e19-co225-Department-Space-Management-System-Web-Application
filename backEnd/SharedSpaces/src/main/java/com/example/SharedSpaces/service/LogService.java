package com.example.SharedSpaces.service;

import com.example.SharedSpaces.models.User;
import org.springframework.stereotype.Service;
import java.util.ArrayList;

import java.util.*;

@Service
public class LogService {

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

        return new User(payLoadValues.get(6), payLoadValues.get(2), payLoadValues.get(7));

    }

}
