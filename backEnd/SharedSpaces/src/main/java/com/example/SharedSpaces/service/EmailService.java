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
        // Constructor for the EmailService class that sets up a new JavaMailSender and SimpleMailMessage object.
        this.mailSender = mailSender;
        this.message = new SimpleMailMessage();
    }

    // Sends a notification email to the user who made a new reservation.
    public void sendReservationNotificationUSer(User user, ResponsiblePerson responsiblePerson, String spaceName,
                                                Date start, Date end) {
        this.message.setFrom("sharedspaces225@gmail.com"); // Set the email address that the email will be sent from.
        this.message.setTo(user.getEmail()); // Set the email address that the email will be sent to.
        this.message.setSubject("New reservation placed"); // Set the subject of the email.
        this.message.setText("A new reservation has been placed for " + spaceName + " - From : " + start + " To : " + end); // Set the body of the email.
        mailSender.send(this.message); // Send the email using the JavaMailSender object.
    }

    // Sends a notification email to the responsible person for a new reservation.
    public void sendReservationNotificationResponsible(User user, ResponsiblePerson responsiblePerson, String spaceName,
                                                       Date start, Date end) {
        this.message.setFrom("sharedspaces225@gmail.com"); // Set the email address that the email will be sent from.
        this.message.setTo(responsiblePerson.getEmail()); // Set the email address that the email will be sent to.
        this.message.setSubject("Notify about Reservation"); // Set the subject of the email.
        this.message.setText("A new reservation has been placed by " + user.getFullName() + " for " + spaceName
                + " - From : " + start + " To : " + end + " Selecting " + responsiblePerson.fullName()
                + " as the responsible person"); // Set the body of the email.
        mailSender.send(this.message); // Send the email using the JavaMailSender object.
    }

    // Sends a notification email to the responsible person for a new reservation when the user making the reservation is also the responsible person.
    public void sendReservationNotificationResponsible(ResponsiblePerson responsiblePerson, String spaceName,
                                                       Date start, Date end) {
        this.message.setFrom("sharedspaces225@gmail.com"); // Set the email address that the email will be sent from.
        this.message.setTo(responsiblePerson.getEmail()); // Set the email address that the email will be sent to.
        this.message.setSubject("Notify about Reservation"); // Set the subject of the email.
        this.message.setText("A new reservation has been placed by " + responsiblePerson.fullName() + " for "
                + spaceName + " - From : " + start + " To : " + end + " Selecting " + responsiblePerson.fullName()
                + " as the responsible person"); // Set the body of the email.
        mailSender.send(this.message); // Send the email using the JavaMailSender object.
    }

    // Sends a notification email to a user when a previously reserved space becomes available again.
    public void sendFreeReservationNotification(User user, String spaceName, Date start, Date end) {
        this.message.setFrom("sharedspaces225@gmail.com"); // Set the email address that the email will be sent from.
        this.message.setTo(user.getEmail()); // Set the email address that the email will be sent to.
        this.message.setSubject("Reservation free"); // Set the subject of the email.
        this.message.setText("Now you can reserve " + spaceName + " From: " + start + " To: " + end); // Set the body of the email.
        mailSender.send(this.message); // Send the email using the JavaMailSender object.
    }

    // Sends a notification email to a user when a previously reserved space is deleted.
    public void sendDelecteeReservationNotification(User user, String spaceName, Date start, Date end,
                                                    String deletePerson) {
        this.message.setFrom("sharedspaces225@gmail.com"); // Set the email address that the email will be sent from.
        this.message.setTo(user.getEmail()); // Set the email address that the email will be sent to.
        this.message.setSubject("Reservation deleted"); // Set the subject of the email.
        this.message.setText("The reservation Space: " + spaceName + " From: " + start + " To: " + end
                + " has been deleted by " + deletePerson); // Set the body of the email.
        mailSender.send(this.message); // Send the email using the JavaMailSender object.
    }

}