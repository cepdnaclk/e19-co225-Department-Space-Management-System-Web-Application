//package com.example.SharedSpaces.config;
//import org.springframework.beans.factory.annotation.Value;
//
//
//
//import org.springframework.context.annotation.Bean;
//import org.springframework.context.annotation.Configuration;
//import java.util.Base64;
//
//import org.springframework.util.Base64Utils;
//import org.springframework.web.client.RestTemplate;
//
//@Configuration
//public class MailchimpConfig {
//
//    @Value("${mailchimp.apikey}")
//    private String apiKey;
//
//    @Bean
//    public RestTemplate restTemplate() {
//        RestTemplate restTemplate = new RestTemplate();
//        restTemplate.getInterceptors().add((request, body, execution) -> {
//            request.getHeaders().set("Authorization", "Basic " + Base64Utils.encodeToString(("apikey:" + apiKey).getBytes()));
//            return execution.execute(request, body);
//        });
//        return restTemplate;
//    }
//}
//
