package com.example.SharedSpaces.service;

import com.example.SharedSpaces.models.ResponsiblePerson;
import com.example.SharedSpaces.models.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

import java.util.Date;

@Service
public class EmailService {

    @Autowired
    private JavaMailSender mailSender;
    private SimpleMailMessage message;

    public EmailService(JavaMailSender mailSender) {
        this.mailSender = mailSender;
        this.message = new SimpleMailMessage();
    }

    public void sendReservationNotificationUSer(User user, ResponsiblePerson responsiblePerson, String spaceName,
            Date start, Date end) {
        this.message.setFrom("sharedspaces225@gmail.com");
        this.message.setTo(user.getEmail());
        this.message.setSubject("New reservation placed");
        this.message
                .setText("A new reservation has been placed for " + spaceName + " - From : " + start + " To : " + end);
        mailSender.send(this.message);
    }

    public void sendReservationNotificationResponsible(User user, ResponsiblePerson responsiblePerson, String spaceName,
            Date start, Date end) {
        this.message.setFrom("sharedspaces225@gmail.com");
        this.message.setTo(responsiblePerson.getEmail());
        this.message.setSubject("Notify about Reservation");
        this.message.setText("A new reservation has been placed by " + user.getFullName() + " for " + spaceName
                + " - From : " + start + " To : " + end + " Selecting " + responsiblePerson.fullName()
                + " as the responsible person");
        mailSender.send(this.message);
    }

    public void sendReservationNotificationResponsible(ResponsiblePerson responsiblePerson, String spaceName,
            Date start, Date end) {
        this.message.setFrom("sharedspaces225@gmail.com");
        this.message.setTo(responsiblePerson.getEmail());
        this.message.setSubject("Notify about Reservation");
        this.message.setText("A new reservation has been placed by " + responsiblePerson.fullName() + " for "
                + spaceName + " - From : " + start + " To : " + end + " Selecting " + responsiblePerson.fullName()
                + " as the responsible person");
        mailSender.send(this.message);
    }

    public void sendFreeReservationNotification(User user, String spaceName, Date start, Date end) {
        this.message.setFrom("sharedspaces225@gmail.com");
        this.message.setTo(user.getEmail());
        this.message.setSubject("Reservation free");
        this.message.setText("Now you can reserve " + spaceName + " From: " + start + " To: " + end);
        mailSender.send(this.message);
    }

    public void sendDelecteeReservationNotification(User user, String spaceName, Date start, Date end,
            String deletePerson) {
        this.message.setFrom("sharedspaces225@gmail.com");
        this.message.setTo(user.getEmail());
        this.message.setSubject("Reservation deleted");
        this.message.setText("The reservation Space: " + spaceName + " From: " + start + " To: " + end
                + " has been deleted by " + deletePerson);
        mailSender.send(this.message);
    }

}
