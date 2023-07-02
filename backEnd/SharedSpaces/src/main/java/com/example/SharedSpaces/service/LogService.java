package com.example.SharedSpaces.service;

import com.example.SharedSpaces.controller.RequestResponse.LogResponse;
import com.example.SharedSpaces.db.AdminDB;
import com.example.SharedSpaces.db.ResponsiblePersonDB;
import com.example.SharedSpaces.db.UserDB;
import com.example.SharedSpaces.exception.InvalidDataException;
import com.example.SharedSpaces.exception.InvalidEmailException;
import com.example.SharedSpaces.models.Admin;
import com.example.SharedSpaces.models.ResponsiblePerson;
import com.example.SharedSpaces.models.User;
import com.example.SharedSpaces.security.JwtService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class LogService {

    private final JwtService jwtService;
    private final UserDB userDB;
    private final ResponsiblePersonDB responsiblePersonDB;
    private final AdminDB adminDB;

    @Autowired
    public LogService(JwtService jwtService, UserDB userDB, ResponsiblePersonDB responsiblePersonDB, AdminDB adminDB) {
        // Constructor for the LogService class that takes in a JwtService, UserDB, ResponsiblePersonDB, and AdminDB object.
        this.jwtService = jwtService;
        this.userDB = userDB;
        this.responsiblePersonDB = responsiblePersonDB;
        this.adminDB = adminDB;
    }

    // Logs a user in and returns a LogResponse object that contains a refresh token.
    public LogResponse log(String credential) throws InvalidEmailException, InvalidDataException {

        try {
            User user = jwtService.extractClaimsGoogle(credential); // Extract user information from the credential using the JwtService object.

            if (isEmailValid(user.getEmail())) { // Check if the user's email is valid.

                Map<String, Object> map = new HashMap<>();

                // get role
                String role;

                // Determine the user's role based on their email address.
                if (responsiblePersonDB.getResponsiblePersonByEmail(user.getEmail()).isPresent()) {
                    ResponsiblePerson responsiblePerson = responsiblePersonDB
                            .getResponsiblePersonByEmail(user.getEmail()).get();
                    role = "responsible_person";
                    map.put("user", responsiblePerson);
                } else if (adminDB.getAdminByEmail(user.getEmail()).isPresent()) {
                    role = "admin";
                    Admin admin = adminDB.getAdminByEmail(user.getUsername()).get();
                    map.put("user", admin);
                } else {
                    if (userDB.getUserByEmail(user.getEmail()).isEmpty())
                        userDB.createUser(user);
                    User use = userDB.getUserByEmail(user.getUsername()).get();
                    role = "user";
                    map.put("user", use);
                }

                map.put("role", role); // Add the user's role to the map.

                String reFreshToken = jwtService.generateRefreshToken(map, user); // Generate a new refresh token using the JwtService object.

                LogResponse response = new LogResponse(reFreshToken); // Create a new LogResponse object with the refresh token.
                response.setValid(true); // Set the response as valid.

                return response; // Return the LogResponse object.

            } else {
                throw new InvalidEmailException("invalid"); // Throw an exception if the user's email is invalid.
            }

        } catch (InvalidEmailException e) {
            throw new InvalidEmailException("invalid");

        } catch (Exception e) {
            throw new InvalidDataException("invalid"); // Throw an exception if there is an error with the data.
        }
    }

    // Checks if an email is valid.
    public boolean isEmailValid(String email) {

        if (email.substring(email.length() - 14, email.length()).equals("@eng.pdn.ac.lk"))
            return true;
        else
            return false;

    }

}