package com.ncba.miniapp.service;

import com.ncba.miniapp.dto.request.UserInfoRequestDto;
import com.ncba.miniapp.dto.request.UserSessionRequest;
import com.ncba.miniapp.util.HeadersUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.*;
import org.springframework.stereotype.Service;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.HttpServerErrorException;
import org.springframework.web.client.HttpStatusCodeException;
import org.springframework.web.client.RestTemplate;

@Slf4j
@Service
public class AccessService {
    @Autowired
    private final RestTemplate restTemplate;
    @Value("${userInfoRequestUrl}")
    private String userInfoRequestUrl;
    @Value("${accessTokenUrl}")
    private String accessTokenUrl;

    @Autowired
    public AccessService(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }

    public ResponseEntity<String> getHealthStatus() {
        try {
            log.info("Inside getHealthStatus()...");
            return ResponseEntity.ok("AccessController Service is Up");
        } catch (HttpStatusCodeException e) {
            log.error("An error occurred inside getHealthStatus() {}", e.getMessage());
            // Handle HTTP status code exceptions (e.g., 4xx, 5xx)
            HttpStatusCode statusCode = e.getStatusCode();
            String responseBody = e.getResponseBodyAsString();
            return ResponseEntity.status(statusCode).body(responseBody);
        } catch (Exception e) {
            log.error("An error occurred inside getHealthStatus() {}", e.getMessage());
            // Handle other exceptions
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("An error occurred");
        }
    }

    public ResponseEntity<String> getUserInfo(UserInfoRequestDto userInfoRequestDto, String version, String token) {
        log.info("Inside getUserInfo()...userInfoRequestDto: {} userInfoRequestUrl: {}", userInfoRequestDto, userInfoRequestUrl);
        HttpHeaders headers = HeadersUtil.getHttpHeaders(version, token);
        HttpEntity<UserInfoRequestDto> entity = new HttpEntity<>(userInfoRequestDto, headers);

        try {
            return restTemplate.exchange(userInfoRequestUrl, HttpMethod.POST, entity, String.class);
        } catch (HttpClientErrorException e) {
            log.error("An error occurred inside getUserInfo() {}", e.getMessage());
            // Handle client-side HTTP errors (4xx)
            return ResponseEntity.status(e.getStatusCode()).body(e.getResponseBodyAsString());
        } catch (HttpServerErrorException e) {
            log.error("An error occurred inside getUserInfo() {}", e.getMessage());
            // Handle server-side HTTP errors (5xx)
            return ResponseEntity.status(e.getStatusCode()).body(e.getResponseBodyAsString());
        } catch (Exception e) {
            log.error("An error occurred inside getUserInfo() {}", e.getMessage());
            // Handle other exceptions
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("An error occurred: " + e.getMessage());
        }
    }

    public ResponseEntity<String> getAccessToken(UserSessionRequest userSessionRequest, String version, String token) {
        log.info("Inside getAccessToken()...userSessionRequest: {} accessTokenUrl: {}", userSessionRequest, accessTokenUrl);
        HttpHeaders headers = HeadersUtil.getHttpHeaders(version, token);
        HttpEntity<UserSessionRequest> entity = new HttpEntity<>(userSessionRequest, headers);
        try {
            return restTemplate.exchange(accessTokenUrl, HttpMethod.POST, entity, String.class);
        } catch (HttpClientErrorException e) {
            log.error("An error occurred inside getAccessToken() {}", e.getMessage());
            // Handle client-side HTTP errors (4xx)
            return ResponseEntity.status(e.getStatusCode()).body(e.getResponseBodyAsString());
        } catch (HttpServerErrorException e) {
            log.error("An error occurred inside getAccessToken() {}", e.getMessage());
            // Handle server-side HTTP errors (5xx)
            return ResponseEntity.status(e.getStatusCode()).body(e.getResponseBodyAsString());
        } catch (Exception e) {
            log.error("An error occurred inside getAccessToken() {}", e.getMessage());
            // Handle other exceptions
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("An error occurred: " + e.getMessage());
        }
    }
}
