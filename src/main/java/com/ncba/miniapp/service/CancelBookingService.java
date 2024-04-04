package com.ncba.miniapp.service;

import com.ncba.miniapp.configuration.HeadersConfig;
import com.ncba.miniapp.dto.request.CancelBookingRequest;
import com.ncba.miniapp.util.ProcessAsync;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.*;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.HttpServerErrorException;
import org.springframework.web.client.RestTemplate;

@Slf4j
@Service
public class CancelBookingService {
    private final String cancelBookingUrl;
    private final RestTemplate restTemplate;
    private final HeadersConfig headersConfig;
    private final ProcessAsync processAsync;

    @Autowired
    public CancelBookingService(RestTemplate restTemplate, HeadersConfig headersConfig, @Value("${cancelBookingUrl}") String cancelBookingUrl, ProcessAsync processAsync) {
        this.restTemplate = restTemplate;
        this.headersConfig = headersConfig;
        this.cancelBookingUrl = cancelBookingUrl;
        this.processAsync = processAsync;
    }

    @Transactional
    public ResponseEntity<String> cancelBooking(CancelBookingRequest cancelBookingRequest) {
        log.info("Inside cancelBooking()...cancelBookingRequest: {} cancelBookingUrl: {}", cancelBookingRequest, cancelBookingUrl);
        HttpHeaders headers = headersConfig.getHttpHeaders();
        HttpEntity<CancelBookingRequest> entity = new HttpEntity<>(cancelBookingRequest, headers);
        try {
            ResponseEntity<String> response = restTemplate.exchange(cancelBookingUrl, HttpMethod.POST, entity, String.class);
            processAsync.saveEntityAsyncCancelBooking(cancelBookingRequest, cancelBookingUrl, response);
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
}
