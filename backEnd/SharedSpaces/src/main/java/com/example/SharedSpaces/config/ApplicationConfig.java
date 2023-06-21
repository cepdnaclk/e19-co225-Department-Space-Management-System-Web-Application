package com.example.SharedSpaces.config;

import com.example.SharedSpaces.db.UserDB;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.JavaMailSenderImpl;
import org.springframework.security.core.userdetails.UserDetailsService;

import java.util.Properties;

@Configuration
public class ApplicationConfig {

    private final UserDB userDB;

    @Autowired
    public ApplicationConfig(UserDB userDB){
        this.userDB = userDB;
    }

    @Bean
    public UserDetailsService userDetailsService() {
        return useremail -> userDB.getUserByEmail(useremail).get();
    }

    @Bean
    public JavaMailSender getJavaMailSender() {
        JavaMailSenderImpl mailSender = new JavaMailSenderImpl();
        mailSender.setHost("smtp.gmail.com");
        mailSender.setPort(587);

        mailSender.setUsername("kanishkagunawarthana@gamil.com");
        mailSender.setPassword("ibsinvdfvzrppybs");

        Properties props = mailSender.getJavaMailProperties();
        props.put("mail.transport.protocol", "smtp");
        props.put("mail.smtp.auth", "true");
        props.put("mail.smtp.starttls.enable", "true");
        props.put("mail.debug", "true");

        return mailSender;
    }
}
