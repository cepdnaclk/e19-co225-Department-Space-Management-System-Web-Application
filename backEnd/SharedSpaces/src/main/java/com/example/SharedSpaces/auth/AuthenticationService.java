package com.example.SharedSpaces.auth;

import com.example.SharedSpaces.auth.RequestResponse.AuthenticationRequest;
import com.example.SharedSpaces.auth.RequestResponse.AuthenticationResponse;
import com.example.SharedSpaces.auth.RequestResponse.RegisterRequest;
import com.example.SharedSpaces.models.User;
import com.example.SharedSpaces.models.token.Token;
import com.example.SharedSpaces.models.token.TokenType;
import com.example.SharedSpaces.security.JwtService;
import com.fasterxml.jackson.databind.ObjectMapper;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.stereotype.Service;
import jakarta.servlet.http.HttpServletRequest;

import java.io.IOException;

@Service
public class AuthenticationService {

//    private final UserRepository repository;
    private final JwtService jwtService;

    @Autowired
    public AuthenticationService(JwtService jwtService){
        this.jwtService = jwtService;
    }

    public AuthenticationResponse register(RegisterRequest request) {

        var user = new User(request.getFirstName(),request.getLastName(), request.getEmail());

//        var savedUser = repository.save(user);user
        var jwtToken = jwtService.generateToken(user);
        var refreshToken = jwtService.generateRefreshToken(user);

        return new AuthenticationResponse(jwtToken, refreshToken);

    }

    public AuthenticationResponse authenticate(AuthenticationRequest request) {
//        authenticationManager.authenticate(
//                new UsernamePasswordAuthenticationToken(
//                        request.getEmail(),
//                        request.getPassword()
//                )
//        );

//        var user = repository.findByEmail(request.getEmail())
//                .orElseThrow();

        var user = new User(request.getFirstName(), request.getLastName(), request.getEmail());

        var jwtToken = jwtService.generateToken(user);
        var refreshToken = jwtService.generateRefreshToken(user);

        revokeAllUserTokens(user);
        saveUserToken(user, jwtToken);

//        return AuthenticationResponse.builder()
//                .accessToken(jwtToken)
//                .refreshToken(refreshToken)
//                .build();
        return new AuthenticationResponse(jwtToken, refreshToken);
    }

    private void saveUserToken(User user, String jwtToken) {

        var token = new Token(user,jwtToken, TokenType.BEARER, false, false);

//        tokenRepository.save(token);
    }

    private void revokeAllUserTokens(User user) {

//        var validUserTokens = tokenRepository.findAllValidTokenByUser(user.getId());
//        if (validUserTokens.isEmpty())
//            return;
//        validUserTokens.forEach(token -> {
//            token.setExpired(true);
//            token.setRevoked(true);
//        });
//        tokenRepository.saveAll(validUserTokens);

    }

    public void refreshToken(
            HttpServletRequest request,
            HttpServletResponse response
    ) throws IOException {

        final String authHeader = request.getHeader(HttpHeaders.AUTHORIZATION);

        final String refreshToken;
        final String userEmail;

        if (authHeader == null ||!authHeader.startsWith("Bearer ")) {
            return;
        }

        refreshToken = authHeader.substring(7);
        userEmail = jwtService.extractUsername(refreshToken);

        if (userEmail != null) {

//            var user = this.repository.findByEmail(userEmail)
//                    .orElseThrow();

            var user = new User();

            if (jwtService.isTokenValid(refreshToken, user)) {
                var accessToken = jwtService.generateToken(user);
                revokeAllUserTokens(user);
                saveUserToken(user, accessToken);

                var authResponse = new AuthenticationResponse(accessToken, refreshToken);

                new ObjectMapper().writeValue(response.getOutputStream(), authResponse);
            }
        }
    }

}
