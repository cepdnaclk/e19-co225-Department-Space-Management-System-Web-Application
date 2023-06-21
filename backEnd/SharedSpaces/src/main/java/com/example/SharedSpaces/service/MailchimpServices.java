package com.example.SharedSpaces.service;

import lombok.Value;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

@Service
public class MailchimpServices {

    private final RestTemplate restTemplate;

    @Autowired
    public MailchimpServices(RestTemplate restTemplate){
        this.restTemplate = restTemplate;
    }

    private String campaignId = "8e7983514b33";

    private String listId = "e1f686760e";

    public void sendEmail(String[] recipients, String subject, String htmlContent) {
        Map<String, Object> requestBody = new HashMap<>();
        requestBody.put("recipients", Collections.singletonMap("list_id", listId));
        requestBody.put("settings", Collections.singletonMap("subject_line", subject));
        requestBody.put("html", htmlContent);
        restTemplate.postForEntity("/campaigns/{campaign_id}/actions/send", requestBody, Void.class, campaignId);
    }
}

