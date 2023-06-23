package com.example.SharedSpaces;

import com.example.SharedSpaces.service.EmailSenderService;
import jakarta.mail.MessagingException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.event.EventListener;

@SpringBootApplication
public class SharedSpacesApplication {

	@Autowired
	private EmailSenderService senderService;

	public static void main(String[] args) {
		SpringApplication.run(SharedSpacesApplication.class, args);

	}

	@EventListener(ApplicationReadyEvent.class)
	public void triggerMail() throws MessagingException {

		senderService.sendSimpleEmail("iammansitha@gmail.com", "Spring Mail Test", "ADOo");




	}
}

