package com.ncba.miniapp.service;

import com.ncba.miniapp.configuration.HeadersConfig;
import com.ncba.miniapp.dto.request.BookingRequest;
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
public class BookingService {
    private final RestTemplate restTemplate;
    private final HeadersConfig headersConfig;
    private final ProcessAsync processAsync;
    @Value("${createBookingUrl}")
    private String createBookingUrl;

    @Autowired
    public BookingService(RestTemplate restTemplate, HeadersConfig headersConfig, ProcessAsync processAsync) {
        this.restTemplate = restTemplate;
        this.headersConfig = headersConfig;
        this.processAsync = processAsync;
    }

    @Transactional
    public ResponseEntity<String> createBooking(BookingRequest bookingRequest) {
        log.info("Inside createBooking(): {} createBookingUrl: {}", bookingRequest, createBookingUrl);
        HttpHeaders headers = headersConfig.getHttpHeaders();
        HttpEntity<BookingRequest> entity = new HttpEntity<>(bookingRequest, headers);

        try {
            ResponseEntity<String> response = restTemplate.exchange(createBookingUrl, HttpMethod.POST, entity, String.class);
            processAsync.saveEntityAsyncCreateBooking(bookingRequest, createBookingUrl, response);
            return response;
        } catch (HttpClientErrorException e) {
            log.error("An error occurred inside createBooking(): {}", e.getMessage());
            return ResponseEntity.status(e.getStatusCode()).body(e.getResponseBodyAsString());
        } catch (HttpServerErrorException e) {
            log.error("An error occurred inside createBooking(): {}", e.getMessage());
            return ResponseEntity.status(e.getStatusCode()).body(e.getResponseBodyAsString());
        } catch (Exception e) {
            log.error("An error occurred inside createBooking(): {}", e.getMessage());
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("An error occurred: " + e.getMessage());
        }
    }
}
