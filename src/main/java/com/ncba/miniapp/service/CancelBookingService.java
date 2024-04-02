package com.ncba.miniapp.service;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.ncba.miniapp.dto.request.CancelBookingRequest;
import com.ncba.miniapp.model.CancelBooking;
import com.ncba.miniapp.repository.CancelBookingRepository;
import com.ncba.miniapp.util.HeadersUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.*;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.HttpServerErrorException;
import org.springframework.web.client.RestTemplate;

@Slf4j
@Service
public class CancelBookingService {
    private final RestTemplate restTemplate;
    @Autowired
    CancelBookingRepository cancelBookingRepository;
    @Value("${cancelBookingUrl}")
    private String cancelBookingUrl;

    @Autowired
    public CancelBookingService(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }

    @Transactional
    public ResponseEntity<String> cancelBooking(CancelBookingRequest cancelBookingRequest, String version, String token) {
        log.info("Inside cancelBooking()...cancelBookingRequest: {} cancelBookingUrl: {}", cancelBookingRequest, cancelBookingUrl);
        HttpHeaders headers = HeadersUtil.getHttpHeaders(version, token);
        HttpEntity<CancelBookingRequest> entity = new HttpEntity<>(cancelBookingRequest, headers);
        try {
            ResponseEntity<String> response = restTemplate.exchange(cancelBookingUrl, HttpMethod.POST, entity, String.class);
            saveEntityAsyncCancelBooking(cancelBookingRequest, cancelBookingUrl, response);
            return response;
        } catch (HttpClientErrorException e) {
            log.error("An error occurred inside cancelBooking(): {}", e.getMessage());
            return ResponseEntity.status(e.getStatusCode()).body(e.getResponseBodyAsString());
        } catch (HttpServerErrorException e) {
            log.error("An error occurred inside cancelBooking(): {}", e.getMessage());
            return ResponseEntity.status(e.getStatusCode()).body(e.getResponseBodyAsString());
        } catch (Exception e) {
            log.error("An error occurred inside cancelBooking(): {}", e.getMessage());
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("An error occurred: " + e.getMessage());
        }
    }

    @Async
    public void saveEntityAsyncCancelBooking(CancelBookingRequest cancelBookingRequest, String requestUrl, ResponseEntity<String> response) throws JsonProcessingException {
        log.info("Inside saveEntityAsyncCancelBooking()...cancelBookingRequest: {} response: {}", cancelBookingRequest, response);
        try {
            ObjectMapper objectMapper = new ObjectMapper();
            String jsonString = objectMapper.writeValueAsString(cancelBookingRequest);
            CancelBooking cancelBooking = new CancelBooking();
            cancelBooking.setRequestUrl(requestUrl);
            cancelBooking.setRequestBody(jsonString);
            cancelBooking.setResponseBody(response.getBody());
            cancelBooking.setResponseStatus(response.getStatusCode().value());
            cancelBooking.setOrderId(cancelBookingRequest.getOrderId());
            cancelBookingRepository.save(cancelBooking);
        } catch (Exception e) {
            log.error("An error occurred inside saveEntityAsyncCancelBooking() {}", e.getMessage());
            throw e;
        }
    }

}
