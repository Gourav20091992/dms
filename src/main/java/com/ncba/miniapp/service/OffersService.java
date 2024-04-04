package com.ncba.miniapp.service;

import com.ncba.miniapp.configuration.HeadersConfig;
import com.ncba.miniapp.dto.request.Journey;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.*;
import org.springframework.stereotype.Service;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.HttpServerErrorException;
import org.springframework.web.client.RestTemplate;

@Slf4j
@Service
public class OffersService {
    private final RestTemplate restTemplate;
    private final HeadersConfig headersConfig;
    @Value("${getOffersUrl}")
    private String getOffersUrl;

    @Autowired
    public OffersService(RestTemplate restTemplate, HeadersConfig headersConfig) {
        this.restTemplate = restTemplate;
        this.headersConfig = headersConfig;
    }

    public ResponseEntity<String> getOffers(Journey journey) {
        log.info("Inside getOffers()...journey: {} getOffersUrl: {}", journey, getOffersUrl);
        HttpHeaders headers = headersConfig.getHttpHeaders();
        HttpEntity<Journey> entity = new HttpEntity<>(journey, headers);

        try {
            return restTemplate.exchange(getOffersUrl, HttpMethod.POST, entity, String.class);
        } catch (HttpClientErrorException e) {
            log.error("An error occurred inside getOffers() {}", e.getMessage());
            // Handle client-side HTTP errors (4xx)
            return ResponseEntity.status(e.getStatusCode()).body(e.getResponseBodyAsString());
        } catch (HttpServerErrorException e) {
            log.error("An error occurred inside getOffers() {}", e.getMessage());
            // Handle server-side HTTP errors (5xx)
            return ResponseEntity.status(e.getStatusCode()).body(e.getResponseBodyAsString());
        } catch (Exception e) {
            log.error("An error occurred inside getOffers() {}", e.getMessage());
            // Handle other exceptions
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("An error occurred: " + e.getMessage());
        }
    }

}
