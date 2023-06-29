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
        this.jwtService = jwtService;
        this.userDB = userDB;
        this.responsiblePersonDB = responsiblePersonDB;
        this.adminDB = adminDB;
    }

    public LogResponse log(String credential) throws InvalidEmailException, InvalidDataException {

        try {
            User user = jwtService.extractClaimsGoogle(credential);

            if (isEmailValid(user.getEmail())) {

                Map<String, Object> map = new HashMap<>();

                // get role
                String role;

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
                    role = "user";
                    map.put("user", user);
                }

                map.put("role", role);

                String reFreshToken = jwtService.generateRefreshToken(map, user);

                LogResponse response = new LogResponse(reFreshToken);
                response.setValid(true);

                return response;

            } else {
                // LogResponse response = new LogResponse();
                // response.setValid(false);
                // return response;
                throw new InvalidEmailException("invalid");
            }

        } catch (InvalidEmailException e) {
            throw new InvalidEmailException("invalid");

        } catch (Exception e) {
//            LogResponse response = new LogResponse();
//            response.setValid(false);
//            return response;
            throw new InvalidDataException("invalid");
        }
    }

    public boolean isEmailValid(String email) {

        if (email.substring(email.length() - 14, email.length()).equals("@eng.pdn.ac.lk"))
            return true;
        else
            return false;

    }

}
