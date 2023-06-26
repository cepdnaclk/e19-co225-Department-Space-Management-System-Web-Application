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

    public AuthenticationResponse authenticate() {

        User user = userDB.getUserByEmail(SecurityContextHolder.getContext().getAuthentication().getName()).get();

        var jwtToken = jwtService.generateToken(user);
        var refreshToken = "";

        return new AuthenticationResponse(jwtToken, refreshToken);
    }

    public AuthenticationResponse refreshToken() {

        User user = userDB.getUserByEmail(SecurityContextHolder.getContext().getAuthentication().getName()).get();

        Map<String, Object> map = new HashMap<>();

        String role;

        if (responsiblePersonDB.getResponsiblePersonByEmail(user.getEmail()).isPresent()) {
            ResponsiblePerson responsiblePerson = responsiblePersonDB.getResponsiblePersonByEmail(user.getEmail())
                    .get();
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
            User currentUser = userDB.getUserByEmail(user.getEmail()).get();
            map.put("user", currentUser);
        }

        map.put("role", role);

        String reFreshToken = jwtService.generateRefreshToken(map, user);
        var jwtToken = "";

        return new AuthenticationResponse(jwtToken, reFreshToken);
    }

}
