package com.example.SharedSpaces.service;

import lombok.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

@Service
public class MailchimpService {

    private final RestTemplate restTemplate;

    @Value("${mailchimp.campaign_id}")
    private String campaignId;

    @Value("${mailchimp.list_id}")
    private String listId;

    public MailchimpService(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }

    public void sendEmail(String[] recipients, String subject, String htmlContent) {
        Map<String, Object> requestBody = new HashMap<>();
        requestBody.put("recipients", Collections.singletonMap("list_id", listId));
        requestBody.put("settings", Collections.singletonMap("subject_line", subject));
        requestBody.put("html", htmlContent);
        restTemplate.postForEntity("/campaigns/{campaign_id}/actions/send", requestBody, Void.class, campaignId);
    }
}

