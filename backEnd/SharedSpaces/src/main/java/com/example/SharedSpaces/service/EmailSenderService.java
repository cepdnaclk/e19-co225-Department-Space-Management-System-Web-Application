package com.example.SharedSpaces.service;



import com.example.SharedSpaces.models.Reservation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

@Service
public class EmailSenderService {
    @Autowired
    private JavaMailSender mailSender;


    public void sendSimpleEmail(String toEmail, String subject, String body) {
        SimpleMailMessage message = new SimpleMailMessage();
        message.setFrom("sender@gmail.com");
        message.setTo(toEmail);
        message.setText(body);
        message.setSubject(subject);
        mailSender.send(message);
        System.out.println("Mail Send...");


    }
    public void sendReservationNotification(Reservation reservation) {
        SimpleMailMessage message = new SimpleMailMessage();
        message.setFrom("sender@gmail.com");
        message.setTo(reservation.getResponsiblePersonEmail());
        message.setSubject("New reservation placed");
        message.setText("A new reservation has been placed for " + reservation.getSpaceID() + ".");
        mailSender.send(message);
    }

    public void sendConfirmationNotification(Reservation reservation) {
        SimpleMailMessage message = new SimpleMailMessage();
        message.setFrom("sender@gmail.com");
        message.setTo(reservation.getPersonEmail());
        message.setSubject("Reservation confirmation");
        message.setText("Your reservation for " + reservation.getSpaceName() + " has been confirmed.");
        mailSender.send(message);
    }

    public void sendDeletionNotification(Reservation reservation) {
        SimpleMailMessage message = new SimpleMailMessage();
        message.setFrom("sender@gmail.com");
        message.setTo(reservation.getPersonEmail());
        message.setSubject("Reservation deletion");
        message.setText("Your reservation for " + reservation.getSpaceName() + " has been deleted.");
        mailSender.send(message);
    }

    public void sendWaitingListNotification(Reservation reservation) {
        SimpleMailMessage message = new SimpleMailMessage();
        message.setFrom("sender@gmail.com");
        message.setTo(reservation.getWaitingListPersonEmail());
        message.setSubject("Slot available");
        message.setText("A slot is now available for " + reservation.getSpaceName() + ". Please confirm within 24 hours if you wish to book this space.");
        mailSender.send(message);
    }
}


