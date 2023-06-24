package com.example.SharedSpaces.config;

import com.example.SharedSpaces.db.UserDB;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.core.userdetails.UserDetailsService;

@Configuration
public class ApplicationConfig {

    private final UserDB userDB;

    @Autowired
    public ApplicationConfig(UserDB userDB) {
        this.userDB = userDB;
    }

    @Bean
    public UserDetailsService userDetailsService() {
        return useremail -> userDB.getUserByEmail(useremail).get();
    }

}
