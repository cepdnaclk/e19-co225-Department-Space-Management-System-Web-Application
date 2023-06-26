package com.example.SharedSpaces.security;

import com.example.SharedSpaces.models.User;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.ExpiredJwtException;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

import java.security.Key;
import java.util.*;
import java.util.function.Function;

@Service
public class JwtService {

    @Value("${secret.key}")
    private String secretKey;
    @Value("${secret.key.r}")
    private String secretKeyR;

    private long expiration = 10000000;
    private long accessExpiration = 1000;

    public String extractUsername(String token, String key) {
        return extractClaim(token, Claims::getSubject, key);
    }

    public String extractUsername(String token) {
        return extractClaim(token, Claims::getSubject, secretKey);
    }

    public <T> T extractClaim(String token, Function<Claims, T> claimsResolver, String key) throws ExpiredJwtException {
        final Claims claims = extractAllClaims(token, key);
        return claimsResolver.apply(claims);
    }

    public String generateToken(UserDetails userDetails) {
        return generateToken(new HashMap<>(), userDetails, accessExpiration);
    }

    public String generateToken(
            Map<String, Object> extraClaims,
            UserDetails userDetails,
            long expiration) {
        return buildToken(extraClaims, userDetails, expiration, secretKey);
    }

    public String generateRefreshToken(
            UserDetails userDetails) {
        return buildToken(new HashMap<>(), userDetails, expiration, secretKey);
    }

    public String generateRefreshToken(
            Map<String, Object> extraClaims,
            UserDetails userDetails) {
        return buildToken(extraClaims, userDetails, expiration, secretKeyR);
    }

    private String buildToken(
            Map<String, Object> extraClaims,
            UserDetails userDetails,
            long expiration,
            String key) {
        return Jwts
                .builder()
                .setClaims(extraClaims)
                .setSubject(userDetails.getUsername())
                .setIssuedAt(new Date(System.currentTimeMillis()))
                .setExpiration(new Date(System.currentTimeMillis() + expiration))
                .signWith(getSignInKey(key), SignatureAlgorithm.HS256)
                .compact();
    }

    public boolean isTokenValid(String token, UserDetails userDetails) {
        final String username = extractUsername(token);
        return (username.equals(userDetails.getUsername())) && !isTokenExpired(token);
    }

    public boolean isTokenValid(String token, UserDetails userDetails, String key) {
        final String username = extractUsername(token, key);
        return (username.equals(userDetails.getUsername())) && !isTokenExpired(token, key);
    }

    public boolean isTokenExpired(String token) {
        try {
            return extractExpiration(token, secretKey).before(new Date());
        } catch (ExpiredJwtException e) {
            return true;
        }
    }

    public boolean isTokenExpired(String token, String key) {
        try {
            return extractExpiration(token, key).before(new Date());
        } catch (ExpiredJwtException e) {
            return true;
        }
    }

    private Date extractExpiration(String token, String key) throws ExpiredJwtException {
        return extractClaim(token, Claims::getExpiration, key);
    }

    private Claims extractAllClaims(String token, String key) throws ExpiredJwtException {
        return Jwts
                .parserBuilder()
                .setSigningKey(getSignInKey(key))
                .build()
                .parseClaimsJws(token)
                .getBody();
    }

    private Key getSignInKey(String secretKey) {
        byte[] keyBytes = Decoders.BASE64.decode(secretKey);
        return Keys.hmacShaKeyFor(keyBytes);
    }

    public User extractClaimsGoogle(String token) {

        String payLoadToken = token.split("\\.")[1];

        Base64.Decoder decoder = Base64.getUrlDecoder();

        String payLoad = new String(decoder.decode(payLoadToken));
        Map<String, String> map = new HashMap<>();

        for (String element : payLoad.substring(1, payLoad.length() - 1).split("\\,")) {
            String[] elements = element.split("\\:");
            map.put(elements[0], elements[1]);
        }

        List<String> payLoadValues = new ArrayList<String>(map.values());

        return new User(payLoadValues.get(6).substring(1, payLoadValues.get(6).length() - 1),
                payLoadValues.get(2).substring(1, payLoadValues.get(2).length() - 1),
                payLoadValues.get(7).substring(1, payLoadValues.get(7).length() - 1));

    }
}
