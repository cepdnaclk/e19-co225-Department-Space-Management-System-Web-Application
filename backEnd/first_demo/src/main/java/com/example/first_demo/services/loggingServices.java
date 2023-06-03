package com.example.first_demo.services;
import io.jsonwebtoken.Jwts;

import com.example.first_demo.models.Log;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;
import org.springframework.stereotype.Service;

import java.security.Key;

@Service
public class loggingServices {

    private String secretKey = "your-256-bit-secret";

    public Log checkToken(String token){
        System.out.println(Jwts.parserBuilder().setSigningKey(getSignInKey()).build().parseClaimsJws(token).getBody());

        return new Log("Kanishka", "Student", "e19129@email");
    }

    private Key getSignInKey() {
        byte[] keyBytes = Decoders.BASE64.decode(secretKey);
        return Keys.hmacShaKeyFor(keyBytes);
    }
}
