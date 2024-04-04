package com.ncba.miniapp.service;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.ncba.miniapp.configuration.HeadersConfig;
import com.ncba.miniapp.dto.request.AirRequestDto;
import com.ncba.miniapp.mapper.request.AirRequestMapper;
import com.ncba.miniapp.model.AirRequest;
import com.ncba.miniapp.repository.AirRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.*;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.HttpServerErrorException;
import org.springframework.web.client.RestTemplate;

@Slf4j
@Service
public class AirService {
    private final RestTemplate restTemplate;
    private final HeadersConfig headersConfig;
    @Autowired
    AirRepository airRepo;
    @Autowired
    AirRequestMapper airRequestMapper;
    @Value("${getLocationUrl}")
    private String getLocationUrl;
    @Value("${getAirlinesUrl}")
    private String getAirlinesUrl;

    @Autowired
    public AirService(RestTemplate restTemplate, HeadersConfig headersConfig) {
        this.restTemplate = restTemplate;
        this.headersConfig = headersConfig;
    }

    public ResponseEntity<String> getLocation(AirRequestDto dto) {
        HttpHeaders headers = headersConfig.getHttpHeaders();
        HttpEntity<AirRequestDto> entity = new HttpEntity<>(dto, headers);
        try {
            ResponseEntity<String> response = restTemplate.exchange(getLocationUrl, HttpMethod.POST, entity, String.class);
            saveEntityAsyncGetLocation(dto, getLocationUrl, response);
            return response;
        } catch (HttpClientErrorException e) {
            log.error("An error occurred inside getLocation(): {}", e.getMessage());
            // Handle client-side HTTP errors (4xx)
            return ResponseEntity.status(e.getStatusCode()).body(e.getResponseBodyAsString());
        } catch (HttpServerErrorException e) {
            log.error("An error occurred inside getLocation(): {}", e.getMessage());
            // Handle server-side HTTP errors (5xx)
            return ResponseEntity.status(e.getStatusCode()).body(e.getResponseBodyAsString());
        } catch (Exception e) {
            log.error("An error occurred inside getLocation(): {}", e.getMessage());
            // Handle other exceptions
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("An error occurred: " + e.getMessage());
        }
    }

    @Async
    public void saveEntityAsyncGetLocation(AirRequestDto dto, String getLocationUrl, ResponseEntity<String> response) throws Exception {
        log.info("Inside saveEntityAsyncGetLocation()...dto is : {} response is : {}", dto, response);
        try {
            ObjectMapper objectMapper = new ObjectMapper();
            String jsonString = objectMapper.writeValueAsString(dto);
            AirRequest mapperEntity = airRequestMapper.mapToEntity(dto);
            mapperEntity.setRequestUrl(getLocationUrl);
            mapperEntity.setFilter(dto.getFilter());
            mapperEntity.setValue(dto.getValue());
            mapperEntity.setRequestBody(jsonString);
            mapperEntity.setResponseStatus(response.getStatusCode().value());
            mapperEntity.setResponseBody(response.getBody());
            airRepo.save(mapperEntity);
        } catch (Exception e) {
            log.error("An error occurred inside saveEntityAsyncGetLocation(): {}", e.getMessage());
            throw e;
        }
    }

    @Async
    public void saveEntityAsyncGetAirlines(String getAirlinesUrl, ResponseEntity<String> response) throws Exception {
        log.info("Inside saveEntityAsyncGetAirlines()...response is : {}", response);
        try {
            AirRequest mapperEntity = new AirRequest();
            mapperEntity.setRequestUrl(getAirlinesUrl);
            mapperEntity.setResponseStatus(response.getStatusCode().value());
            mapperEntity.setResponseBody(response.getBody());
            airRepo.save(mapperEntity);
        } catch (Exception e) {
            log.error("An error occurred inside saveEntityAsyncGetAirlines(): {}", e.getMessage());
            throw e;
        }
    }

    public ResponseEntity<String> getAirlines() {
        log.info("Inside getAirlines()...");
        HttpHeaders headers = headersConfig.getHttpHeaders();
        HttpEntity<String> entity = new HttpEntity<>(headers);

        try {
            ResponseEntity<String> response = restTemplate.exchange(getAirlinesUrl, HttpMethod.POST, entity, String.class);
            saveEntityAsyncGetAirlines(getAirlinesUrl, response);
            return response;
        } catch (HttpClientErrorException e) {
            log.error("An error occurred inside getAirlines() {}", e.getMessage());
            // Handle client-side HTTP errors (4xx)
            return ResponseEntity.status(e.getStatusCode()).body(e.getResponseBodyAsString());
        } catch (HttpServerErrorException e) {
            log.error("An error occurred inside getAirlines() {}", e.getMessage());
            // Handle server-side HTTP errors (5xx)
            return ResponseEntity.status(e.getStatusCode()).body(e.getResponseBodyAsString());
        } catch (Exception e) {
            log.error("An error occurred inside getAirlines() {}", e.getMessage());
            // Handle other exceptions
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("An error occurred: " + e.getMessage());
        }
    }
}
