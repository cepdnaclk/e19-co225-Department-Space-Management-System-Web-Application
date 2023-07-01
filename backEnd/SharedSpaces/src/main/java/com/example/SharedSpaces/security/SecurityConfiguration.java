package com.example.SharedSpaces.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

@Configuration
@EnableWebSecurity
@EnableMethodSecurity
public class SecurityConfiguration {

    // get the jwtfilter
    private final JwtAuthenticationFilter jwtAuthFilter;

    @Autowired
    public SecurityConfiguration(JwtAuthenticationFilter jwtAuthFilter) {
        this.jwtAuthFilter = jwtAuthFilter;
    }

    // Configure the security filter chain.
    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http
                .cors(AbstractHttpConfigurer::disable) // Disable CORS (Cross-Origin Resource Sharing) protection.
                .csrf(AbstractHttpConfigurer::disable) // Disable CSRF (Cross-Site Request Forgery) protection.
                .authorizeHttpRequests(auth -> auth
                        .requestMatchers(HttpMethod.GET, "/reservation", "/waiting/slot", "reservation/user", "reservation/responsible").permitAll() // Allow GET
                                                                                                      // requests to
                                                                                                      // these URLs
                                                                                                      // without
                                                                                                      // authentication.
                        .requestMatchers(
                                "/**", // Allow all requests to these URLs without authentication.
                                "/log",
                                "/space",
                                "/responsible",
                                "/v2/api-docs",
                                "/v3/api-docs",
                                "/v3/api-docs/**",
                                "/swagger-resources",
                                "/swagger-resources/**",
                                "/configuration/ui",
                                "/configuration/security",
                                "/swagger-ui/**",
                                "/webjars/**",
                                "/swagger-ui.html")
                        .permitAll()
                        .anyRequest().authenticated()) // Require authentication for all other requests.
                .sessionManagement(sess -> sess.sessionCreationPolicy(SessionCreationPolicy.STATELESS)) // Use stateless
                                                                                                        // sessions.
                .addFilterBefore(jwtAuthFilter, UsernamePasswordAuthenticationFilter.class); // Add the JWT
                                                                                             // authentication filter
                                                                                             // before the
                                                                                             // username/password
                                                                                             // authentication filter.

        return http.build(); // Build and return the security filter chain.
    }

}