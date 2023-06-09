package com.example.SharedSpaces.security;

import com.example.SharedSpaces.models.User;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

import java.security.Key;
import java.util.*;
import java.util.function.Function;

@Service
public class JwtService {

    private String secretKey = "eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9";
    private long expiration = 1000;

    public String extractUsername(String token) {
        return extractClaim(token, Claims::getSubject);
    }

    public <T> T extractClaim(String token, Function<Claims, T> claimsResolver) {
        final Claims claims = extractAllClaims(token);
        return claimsResolver.apply(claims);
    }

    public String generateToken(UserDetails userDetails) {
        return generateToken(new HashMap<>(), userDetails);
    }

    public String generateToken(
            Map<String, Object> extraClaims,
            UserDetails userDetails
    ) {
        return buildToken(extraClaims, userDetails, expiration);
    }

    public String generateRefreshToken(
            UserDetails userDetails
    ) {
        return buildToken(new HashMap<>(), userDetails, expiration);
    }

    public String generateRefreshToken(
            Map<String, Object> extraClaims,
            UserDetails userDetails
    ) {
        return buildToken(extraClaims, userDetails, expiration);
    }

    private String buildToken(
            Map<String, Object> extraClaims,
            UserDetails userDetails,
            long expiration
    ) {
        return Jwts
                .builder()
                .setClaims(extraClaims)
                .setSubject(userDetails.getUsername())
                .setIssuedAt(new Date(System.currentTimeMillis()))
                .setExpiration(new Date(System.currentTimeMillis() + expiration))
                .signWith(getSignInKey(), SignatureAlgorithm.HS256)
                .compact();
    }



    public boolean isTokenValid(String token, UserDetails userDetails) {
        final String username = extractUsername(token);
        return (username.equals(userDetails.getUsername())) && !isTokenExpired(token);
    }

    private boolean isTokenExpired(String token) {
        return extractExpiration(token).before(new Date());
    }

    private Date extractExpiration(String token) {
        return extractClaim(token, Claims::getExpiration);
    }

    private Claims extractAllClaims(String token) {
        return Jwts
                .parserBuilder()
                .setSigningKey(getSignInKey())
                .build()
                .parseClaimsJws(token)
                .getBody();
    }

    private Key getSignInKey() {
        byte[] keyBytes = Decoders.BASE64.decode(secretKey);
        return Keys.hmacShaKeyFor(keyBytes);
    }

    public User extractClaimsGoogle(String token) {

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
}
