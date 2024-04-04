package com.ncba.miniapp.service;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.ncba.miniapp.configuration.HeadersConfig;
import com.ncba.miniapp.dto.request.BookingRequest;
import com.ncba.miniapp.model.booking.Booking;
import com.ncba.miniapp.model.booking.Passenger;
import com.ncba.miniapp.model.booking.Payments;
import com.ncba.miniapp.repository.BookingRepository;
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

import java.util.Objects;

@Slf4j
@Service
public class BookingService {
    private final RestTemplate restTemplate;
    private final HeadersConfig headersConfig;
    @Autowired
    BookingRepository bookingRepository;
    @Value("${createBookingUrl}")
    private String createBookingUrl;

    @Autowired
    public BookingService(RestTemplate restTemplate, HeadersConfig headersConfig) {
        this.restTemplate = restTemplate;
        this.headersConfig = headersConfig;
    }

    @Transactional
    public ResponseEntity<String> createBooking(BookingRequest bookingRequest) {
        log.info("Inside createBooking(): {} createBookingUrl: {}", bookingRequest, createBookingUrl);
        HttpHeaders headers = headersConfig.getHttpHeaders();
        HttpEntity<BookingRequest> entity = new HttpEntity<>(bookingRequest, headers);

        try {
            ResponseEntity<String> response = restTemplate.exchange(createBookingUrl, HttpMethod.POST, entity, String.class);
            saveEntityAsyncCreateBooking(bookingRequest, createBookingUrl, response);
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

    @Async
    public void saveEntityAsyncCreateBooking(BookingRequest bookingRequest, String createBookingUrl, ResponseEntity<String> response) throws Exception {
        log.info("Inside saveEntityAsyncCreateBooking()...bookingRequest: {} response: {}", bookingRequest, response);
        try {
            ObjectMapper objectMapper = new ObjectMapper();
            String jsonString = objectMapper.writeValueAsString(bookingRequest);
            Booking booking = new Booking();
            booking.setRequestUrl(createBookingUrl);
            booking.setRequestBody(jsonString);
            booking.setResponseBody(response.getBody());
            booking.setResponseStatus(response.getStatusCode().value());
            booking.setResultId(bookingRequest.getResultId());
            booking.setOfferId(bookingRequest.getOfferId());
            booking.getPassengers().addAll(bookingRequest.getPassengersList().stream()
                    .map(passenger -> mapToPassengerEntity(passenger, booking))
                    .filter(Objects::nonNull)
                    .toList());
            Payments payments = new Payments();
            payments.setType(bookingRequest.getPayments().getType());
            payments.setCurrency(bookingRequest.getPayments().getCurrency());
            payments.setAmount(bookingRequest.getPayments().getAmount());
            payments.setBooking(booking);
            booking.setPayments(payments);
            bookingRepository.save(booking);
        } catch (Exception e) {
            log.error("An error occurred inside saveEntityAsyncCreateBooking() {}", e.getMessage());
            throw e;
        }
    }

    private Passenger mapToPassengerEntity(com.ncba.miniapp.dto.request.Passenger passenger, com.ncba.miniapp.model.booking.Booking booking) {
        Passenger passengerEntity = new Passenger();
        passengerEntity.setPhoneNumber(passenger.getPhoneNumber());
        passengerEntity.setPhoneCode(passenger.getPhoneCode());
        passengerEntity.setEmail(passenger.getEmail());
        passengerEntity.setBornOn(passenger.getBornOn());
        passengerEntity.setTitle(passenger.getTitle());
        passengerEntity.setGender(passenger.getGender());
        passengerEntity.setFamilyName(passenger.getFamilyName());
        passengerEntity.setGivenName(passenger.getGivenName());
        passengerEntity.setType(passenger.getType());
        passengerEntity.setBooking(booking);
        return passengerEntity;
    }
}
