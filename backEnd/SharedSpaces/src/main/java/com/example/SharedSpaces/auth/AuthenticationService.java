package com.example.SharedSpaces.auth;

import com.example.SharedSpaces.auth.RequestResponse.AuthenticationRequest;
import com.example.SharedSpaces.auth.RequestResponse.AuthenticationResponse;
import com.example.SharedSpaces.db.AdminDB;
import com.example.SharedSpaces.db.ResponsiblePersonDB;
import com.example.SharedSpaces.db.UserDB;
import com.example.SharedSpaces.models.User;
import com.example.SharedSpaces.security.JwtService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


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

    public AuthenticationResponse refreshToken(AuthenticationRequest request){
        User user = userDB.getUserByEmail(request.getEmail()).get();


        var jwtToken = "";
        var refreshToken = jwtService.generateRefreshToken(user);

        return new AuthenticationResponse(jwtToken, refreshToken);

    }

}
