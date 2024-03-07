package com.exercise.projectschool.client;

import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
public class CountryClient {

    private final RestTemplate restTemplate;

    public CountryClient(RestTemplateBuilder restTemplateBuilder) {
        this.restTemplate = restTemplateBuilder.build();
    }

    public String callExternalService() {
        String externalServiceUrl = "https://restcountries.com/v3.1/name/italy"; // URL del servizio esterno
        ResponseEntity<String> response = restTemplate.getForEntity(externalServiceUrl, String.class);
        return response.getBody();
    }
}
