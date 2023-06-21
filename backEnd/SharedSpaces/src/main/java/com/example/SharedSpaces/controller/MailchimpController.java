package com.example.SharedSpaces.controller;

import com.example.SharedSpaces.models.EmailRequest;
import com.example.SharedSpaces.service.MailchimpServices;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class MailchimpController {

    private final MailchimpServices mailchimpService;

    public MailchimpController(MailchimpServices mailchimpService) {
        this.mailchimpService = mailchimpService;
    }

    @PostMapping("/send-email")
    public void sendEmail(@RequestBody EmailRequest emailRequest) {
        // Convert emailRequest to recipients, subject, and htmlContent
        String[] recipients = emailRequest.getRecipients();
        String subject = emailRequest.getSubject();
        String htmlContent = emailRequest.getHtmlContent();

        mailchimpService.sendEmail(recipients, subject, htmlContent);
    }
}
