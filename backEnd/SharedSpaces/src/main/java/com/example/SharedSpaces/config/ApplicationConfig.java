package com.example.SharedSpaces.config;

import com.example.SharedSpaces.db.UserDB;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.core.userdetails.UserDetailsService;

@Configuration
public class ApplicationConfig {

    // UserDB instance for interacting with the user database
    private final UserDB userDB;

    @Autowired
    public ApplicationConfig(UserDB userDB) {
        this.userDB = userDB;
    }

    // Bean definition for creating a UserDetailsService instance
    @Bean
    public UserDetailsService userDetailsService() {
        // Return a lambda function that retrieves the user from the database using the given email
        return useremail -> userDB.getUserByEmail(useremail).get();
    }

}