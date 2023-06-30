package com.example.SharedSpaces.security;

import com.example.SharedSpaces.db.UserDB;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.jetbrains.annotations.NotNull;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;

@Configuration
// This filter intercepts incoming requests and checks the JWT token for
// authentication.
public class JwtAuthenticationFilter extends OncePerRequestFilter {

    private final JwtService jwtService;
    private final UserDetailsService userDetailsService;
    private final UserDB userDB;

    // @Value("${secret.key}")
    @Value("${secret.key.r}")
    private String secretKeyR;

    @Autowired
    public JwtAuthenticationFilter(JwtService jwtService, UserDetailsService userDetailsService, UserDB userDB) {
        this.jwtService = jwtService;
        this.userDetailsService = userDetailsService;
        this.userDB = userDB;
    }

    @Override
    protected void doFilterInternal(@NotNull HttpServletRequest request, @NotNull HttpServletResponse response,
            @NotNull FilterChain filterChain) throws ServletException, IOException {

        // Allow requests to the /log endpoint.
        if (request.getServletPath().contains("/log")) {
            filterChain.doFilter(request, response);
            return;
        }

        // Check the JWT token for all other requests.
        if (!request.getServletPath().contains("/auth/authenticate")
                && !request.getServletPath().contains("/auth/refresh-token")) {
            final String authHeader = request.getHeader("Authorization");
            final String jwt;
            final String userEmail;

            // If there is no Authorization header or it does not start with "Bearer ",
            // allow the request.

            if (authHeader == null || !authHeader.startsWith("Bearer ")) {
                filterChain.doFilter(request, response);
                return;
            }
            // Extract the JWT token from the Authorization header.

            jwt = authHeader.substring(7);

            // If the JWT token is expired, allow the request.
            if (jwtService.isTokenExpired(jwt)) {
                filterChain.doFilter(request, response);
                return;
            }

            // Extract the email from the JWT token.
            userEmail = jwtService.extractUsername(jwt);

            // If the user with the email does not exist, allow the request.
            if (!userDB.getUserByEmail(userEmail).isPresent()) {
                filterChain.doFilter(request, response);
                return;
            }
            // Authenticate the user.
            if (userEmail != null && SecurityContextHolder.getContext().getAuthentication() == null) {

                UserDetails userDetails = this.userDetailsService.loadUserByUsername(userEmail);

                if (jwtService.isTokenValid(jwt, userDetails)) {
                    UsernamePasswordAuthenticationToken authToken = new UsernamePasswordAuthenticationToken(
                            userDetails,
                            null,
                            userDetails.getAuthorities());

                    authToken.setDetails(
                            new WebAuthenticationDetailsSource().buildDetails(request));

                    SecurityContextHolder.getContext().setAuthentication(authToken);
                }
            }

            filterChain.doFilter(request, response);
        } else {
            final String authHeader = request.getHeader("Authorization");
            final String jwt;
            final String userEmail;

            // If there is no Authorization header or it does not start with "Bearer ",
            // allow the request
            if (authHeader == null || !authHeader.startsWith("Bearer ")) {
                filterChain.doFilter(request, response);
                return;
            }
            // Extract the JWT token from the Authorization header.
            jwt = authHeader.substring(7);

            // If the JWT token is expired, allow the request
            if (jwtService.isTokenExpired(jwt, secretKeyR)) {
                filterChain.doFilter(request, response);
                return;
            }
            // Extract the email from the JWT token.
            userEmail = jwtService.extractUsername(jwt, secretKeyR);

            // If the user with the email does not exist, allow the request.
            if (!userDB.getUserByEmail(userEmail).isPresent()) {
                filterChain.doFilter(request, response);
                return;
            }

            // Authenticate the user.
            if (userEmail != null && SecurityContextHolder.getContext().getAuthentication() == null) {

                UserDetails userDetails = this.userDetailsService.loadUserByUsername(userEmail);

                if (jwtService.isTokenValid(jwt, userDetails, secretKeyR)) {
                    UsernamePasswordAuthenticationToken authToken = new UsernamePasswordAuthenticationToken(
                            userDetails,
                            null,
                            userDetails.getAuthorities());

                    authToken.setDetails(
                            new WebAuthenticationDetailsSource().buildDetails(request));

                    SecurityContextHolder.getContext().setAuthentication(authToken);
                }
            }

            filterChain.doFilter(request, response);
        }

    }

}
