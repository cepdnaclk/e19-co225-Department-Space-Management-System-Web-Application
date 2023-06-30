package com.example.SharedSpaces.auth;

import com.example.SharedSpaces.auth.RequestResponse.AuthenticationResponse;
import com.example.SharedSpaces.db.AdminDB;
import com.example.SharedSpaces.db.ResponsiblePersonDB;
import com.example.SharedSpaces.db.UserDB;
import com.example.SharedSpaces.models.Admin;
import com.example.SharedSpaces.models.ResponsiblePerson;
import com.example.SharedSpaces.models.User;
import com.example.SharedSpaces.security.JwtService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import java.util.HashMap;
import java.util.Map;

@Service
public class AuthenticationService {

    // JwtService instance for generating and validating JWT tokens
    private final JwtService jwtService;
    // UserDB instance for interacting with the user database
    private final UserDB userDB;
    // ResponsiblePersonDB instance for interacting with the responsible person database
    private final ResponsiblePersonDB responsiblePersonDB;
    // AdminDB instance for interacting with the admin database
    private final AdminDB adminDB;

    @Autowired
    public AuthenticationService(JwtService jwtService, UserDB userDB, ResponsiblePersonDB responsiblePersonDB,
                                 AdminDB adminDB) {
        this.jwtService = jwtService;
        this.userDB = userDB;
        this.adminDB = adminDB;
        this.responsiblePersonDB = responsiblePersonDB;
    }

    // Authenticates the user and generates a new access token and refresh token
    public AuthenticationResponse authenticate() {

        // Retrieve the authenticated user
        User user = userDB.getUserByEmail(SecurityContextHolder.getContext().getAuthentication().getName()).get();

        // Generate a new JWT token for the user
        var jwtToken = jwtService.generateToken(user);
        // Set the refresh token to an empty string for now
        var refreshToken = "";

        return new AuthenticationResponse(jwtToken, refreshToken);
    }

    // Generates a new refresh token for the user
    public AuthenticationResponse refreshToken() {

        // Retrieve the authenticated user
        User user = userDB.getUserByEmail(SecurityContextHolder.getContext().getAuthentication().getName()).get();

        // Create a map to store the user and role information
        Map<String, Object> map = new HashMap<>();

        String role;

        // Check if the user is a responsible person
        if (responsiblePersonDB.getResponsiblePersonByEmail(user.getEmail()).isPresent()) {
            ResponsiblePerson responsiblePerson = responsiblePersonDB.getResponsiblePersonByEmail(user.getEmail())
                    .get();
            role = "responsible_person";
            map.put("user", responsiblePerson);
        }
        // Check if the user is an admin
        else if (adminDB.getAdminByEmail(user.getEmail()).isPresent()) {
            role = "admin";
            Admin admin = adminDB.getAdminByEmail(user.getUsername()).get();
            map.put("user", admin);
        }
        // If the user is not a responsible person or an admin, assume they are a regular user
        else {
            // Create the user in the database if it does not already exist
            if (userDB.getUserByEmail(user.getEmail()).isEmpty())
                userDB.createUser(user);
            role = "user";
            User currentUser = userDB.getUserByEmail(user.getEmail()).get();
            map.put("user", currentUser);
        }

        // Add the role to the map
        map.put("role", role);

        // Generate a new refresh token for the user
        String reFreshToken = jwtService.generateRefreshToken(map, user);
        // Set the JWT token to an empty string for now
        var jwtToken = "";

        return new AuthenticationResponse(jwtToken, reFreshToken);
    }
}