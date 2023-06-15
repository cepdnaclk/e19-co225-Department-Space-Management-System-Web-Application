package com.example.SharedSpaces.auth;

import com.example.SharedSpaces.auth.RequestResponse.AuthenticationRequest;
import com.example.SharedSpaces.auth.RequestResponse.AuthenticationResponse;
import com.example.SharedSpaces.auth.RequestResponse.RegisterRequest;
import com.example.SharedSpaces.db.AdminDB;
import com.example.SharedSpaces.db.ResponsiblePersonDB;
import com.example.SharedSpaces.db.UserDB;
import com.example.SharedSpaces.models.User;
import com.example.SharedSpaces.models.token.Token;
import com.example.SharedSpaces.models.token.TokenType;
import com.example.SharedSpaces.security.JwtService;
import com.fasterxml.jackson.databind.ObjectMapper;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import jakarta.servlet.http.HttpServletRequest;

import java.io.IOException;

@Service
public class AuthenticationService {

    // private final UserRepository repository;
    private final JwtService jwtService;
    private final UserDB userDB;
    private final ResponsiblePersonDB responsiblePersonDB;
    private final AdminDB adminDB;

    @Autowired
    public AuthenticationService(JwtService jwtService, UserDB userDB, ResponsiblePersonDB responsiblePersonDB,
            AdminDB adminDB) {
        this.jwtService = jwtService;
        this.userDB = userDB;
        this.adminDB = adminDB;
        this.responsiblePersonDB = responsiblePersonDB;
    }

    public AuthenticationResponse authenticate(AuthenticationRequest request) {

        User user = userDB.getUserByEmail(request.getEmail()).get();
        

        var jwtToken = jwtService.generateToken(user);
        var refreshToken = "";

        return new AuthenticationResponse(jwtToken, refreshToken);
    }

    public void refreshToken(
            HttpServletRequest request,
            HttpServletResponse response) throws IOException {

        final String authHeader = request.getHeader(HttpHeaders.AUTHORIZATION);

        final String refreshToken;
        final String userEmail;

        if (authHeader == null || !authHeader.startsWith("Bearer ")) {
            return;
        }

        refreshToken = authHeader.substring(7);
        userEmail = jwtService.extractUsername(refreshToken);

        if (userEmail != null) {

            var user = new User();

            if (jwtService.isTokenValid(refreshToken, user)) {
                var accessToken = jwtService.generateToken(user);

                var authResponse = new AuthenticationResponse(accessToken, refreshToken);

                new ObjectMapper().writeValue(response.getOutputStream(), authResponse);
            }
        }
    }

}
