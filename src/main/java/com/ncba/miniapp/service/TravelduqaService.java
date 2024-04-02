package com.ncba.miniapp.service;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.ncba.miniapp.dto.request.BookingState;
import com.ncba.miniapp.dto.request.ChangeStatusRequestDto;
import com.ncba.miniapp.dto.request.SelectOfferRequestDto;
import com.ncba.miniapp.dto.request.travelduqa.createbooking.BookingChangeRequestDto;
import com.ncba.miniapp.model.*;
import com.ncba.miniapp.repository.*;
import com.ncba.miniapp.util.HeadersUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.*;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.HttpServerErrorException;
import org.springframework.web.client.RestTemplate;

@Service
@Slf4j
public class TravelduqaService {
    @Autowired
    private final RestTemplate restTemplate;
    @Autowired
    ChangeStatusRequestRepository changeStatusRequestRepository;
    @Autowired
    WalletStatusRepository walletStatusRepository;
    @Autowired
    WalletTransactionsRepository walletTransactionsRepository;
    @Autowired
    BookingStateRepository bookingStateRepository;
    @Autowired
    SelectOfferRequestRepository selectOfferRequestRepository;
    @Autowired
    BookingChangeRequestRepository bookingChangeRequestRepository;
    @Value("${getBookingChangeStatusUrl}")
    private String getBookingChangeStatusUrl;
    @Value("${getWalletStatusUrl}")
    private String getWalletStatusUrl;
    @Value("${getWalletTransactionsUrl}")
    private String getWalletTransactionsUrl;
    @Value("${updateBookingStateUrl}")
    private String updateBookingStateUrl;
    @Value("${getBookingStateUrl}")
    private String getBookingStateUrl;
    @Value("${selectOfferUrl}")
    private String selectOfferUrl;
    @Value("${bookingChangeUrl}")
    private String bookingChangeUrl;

    @Autowired
    public TravelduqaService(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }

    public ResponseEntity<String> getBookingChangeStatus(ChangeStatusRequestDto changeStatusRequestDto, String version, String token) {
        log.info("Inside getBookingChangeStatus()...changeStatusRequestDto: {} getBookingChangeStatusUrl: {}", changeStatusRequestDto, getBookingChangeStatusUrl);
        HttpHeaders headers = HeadersUtil.getHttpHeaders(version, token);
        HttpEntity<ChangeStatusRequestDto> entity = new HttpEntity<>(changeStatusRequestDto, headers);
        try {
            ResponseEntity<String> response = restTemplate.exchange(getBookingChangeStatusUrl, HttpMethod.POST, entity, String.class);
            saveEntityAsyncGetBookingChangeStatus(changeStatusRequestDto, getBookingChangeStatusUrl, response);
            return response;
        } catch (HttpClientErrorException e) {
            log.error("An error occurred inside getBookingChangeStatus() {}", e.getMessage());
            // Handle client-side HTTP errors (4xx)
            return ResponseEntity.status(e.getStatusCode()).body(e.getResponseBodyAsString());
        } catch (HttpServerErrorException e) {
            log.error("An error occurred inside getBookingChangeStatus() {}", e.getMessage());
            // Handle server-side HTTP errors (5xx)
            return ResponseEntity.status(e.getStatusCode()).body(e.getResponseBodyAsString());
        } catch (Exception e) {
            log.error("An error occurred inside getBookingChangeStatus() {}", e.getMessage());
            // Handle other exceptions
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("An error occurred: " + e.getMessage());
        }
    }

    public ResponseEntity<String> getWalletStatus(String version, String token) {
        log.info("Inside getWalletStatus()...getWalletStatusUrl: {}", getWalletStatusUrl);
        HttpHeaders headers = HeadersUtil.getHttpHeaders(version, token);
        HttpEntity<String> entity = new HttpEntity<>(headers);
        try {
            ResponseEntity<String> response = restTemplate.exchange(getWalletStatusUrl, HttpMethod.GET, entity, String.class);
            saveEntityAsyncGetWalletStatus(getWalletStatusUrl, response);
            return response;
        } catch (HttpClientErrorException e) {
            log.error("An error occurred inside getWalletStatus() {}", e.getMessage());
            // Handle client-side HTTP errors (4xx)
            return ResponseEntity.status(e.getStatusCode()).body(e.getResponseBodyAsString());
        } catch (HttpServerErrorException e) {
            log.error("An error occurred inside getWalletStatus() {}", e.getMessage());
            // Handle server-side HTTP errors (5xx)
            return ResponseEntity.status(e.getStatusCode()).body(e.getResponseBodyAsString());
        } catch (Exception e) {
            log.error("An error occurred inside getWalletStatus() {}", e.getMessage());
            // Handle other exceptions
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("An error occurred: " + e.getMessage());
        }
    }

    public ResponseEntity<String> getWalletTransactions(String version, String token) {
        log.info("Inside getWalletTransactions()...getWalletTransactionsUrl: {}", getWalletTransactionsUrl);
        HttpHeaders headers = HeadersUtil.getHttpHeaders(version, token);
        HttpEntity<String> entity = new HttpEntity<>(headers);
        try {
            ResponseEntity<String> response = restTemplate.exchange(getWalletTransactionsUrl, HttpMethod.GET, entity, String.class);
            saveEntityAsyncGetWalletTransactions(getWalletTransactionsUrl, response);
            return response;
        } catch (HttpClientErrorException e) {
            log.error("An error occurred inside getWalletTransactions() {}", e.getMessage());
            // Handle client-side HTTP errors (4xx)
            return ResponseEntity.status(e.getStatusCode()).body(e.getResponseBodyAsString());
        } catch (HttpServerErrorException e) {
            log.error("An error occurred inside getWalletTransactions() {}", e.getMessage());
            // Handle server-side HTTP errors (5xx)
            return ResponseEntity.status(e.getStatusCode()).body(e.getResponseBodyAsString());
        } catch (Exception e) {
            log.error("An error occurred inside getWalletTransactions() {}", e.getMessage());
            // Handle other exceptions
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("An error occurred: " + e.getMessage());
        }
    }

    public ResponseEntity<String> updateBookingState(BookingState bookingState, String version, String token) {
        log.info("Inside updateBookingState()...bookingState: {} updateBookingStateUrl: {}", bookingState, updateBookingStateUrl);
        HttpHeaders headers = HeadersUtil.getHttpHeaders(version, token);
        HttpEntity<BookingState> entity = new HttpEntity<>(bookingState, headers);
        try {
            ResponseEntity<String> response = restTemplate.exchange(updateBookingStateUrl, HttpMethod.POST, entity, String.class);
            saveEntityAsyncUpdateBookingState(bookingState, updateBookingStateUrl, response);
            return response;
        } catch (HttpClientErrorException e) {
            log.error("An error occurred inside updateBookingState() {}", e.getMessage());
            // Handle client-side HTTP errors (4xx)
            return ResponseEntity.status(e.getStatusCode()).body(e.getResponseBodyAsString());
        } catch (HttpServerErrorException e) {
            log.error("An error occurred inside updateBookingState() {}", e.getMessage());
            // Handle server-side HTTP errors (5xx)
            return ResponseEntity.status(e.getStatusCode()).body(e.getResponseBodyAsString());
        } catch (Exception e) {
            log.error("An error occurred inside updateBookingState() {}", e.getMessage());
            // Handle other exceptions
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("An error occurred: " + e.getMessage());
        }
    }

    public ResponseEntity<String> getBooking(BookingState bookingState, String version, String token) {
        log.info("Inside getBooking()...bookingState: {} getBookingStateUrl: {}", bookingState, getBookingStateUrl);
        HttpHeaders headers = HeadersUtil.getHttpHeaders(version, token);
        HttpEntity<BookingState> entity = new HttpEntity<>(bookingState, headers);
        try {
            ResponseEntity<String> response = restTemplate.exchange(getBookingStateUrl, HttpMethod.POST, entity, String.class);
            saveEntityAsyncUpdateBookingState(bookingState, getBookingStateUrl, response);
            return response;
        } catch (HttpClientErrorException e) {
            log.error("An error occurred inside getBooking() {}", e.getMessage());
            // Handle client-side HTTP errors (4xx)
            return ResponseEntity.status(e.getStatusCode()).body(e.getResponseBodyAsString());
        } catch (HttpServerErrorException e) {
            log.error("An error occurred inside getBooking() {}", e.getMessage());
            // Handle server-side HTTP errors (5xx)
            return ResponseEntity.status(e.getStatusCode()).body(e.getResponseBodyAsString());
        } catch (Exception e) {
            log.error("An error occurred inside getBooking() {}", e.getMessage());
            // Handle other exceptions
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("An error occurred: " + e.getMessage());
        }
    }

    public ResponseEntity<String> selectOffer(SelectOfferRequestDto selectOfferRequestDto, String version, String token) {
        log.info("Inside selectOffer()...selectOfferRequestDto: {} selectOfferUrl: {}", selectOfferRequestDto, selectOfferUrl);
        HttpHeaders headers = HeadersUtil.getHttpHeaders(version, token);
        HttpEntity<SelectOfferRequestDto> entity = new HttpEntity<>(selectOfferRequestDto, headers);

        try {
            ResponseEntity<String> response = restTemplate.exchange(selectOfferUrl, HttpMethod.POST, entity, String.class);
            saveEntityAsyncSelectOffer(selectOfferRequestDto, selectOfferUrl, response);
            return response;
        } catch (HttpClientErrorException e) {
            log.error("An error occurred inside selectOffer() {}", e.getMessage());
            // Handle client-side HTTP errors (4xx)
            return ResponseEntity.status(e.getStatusCode()).body(e.getResponseBodyAsString());
        } catch (HttpServerErrorException e) {
            log.error("An error occurred inside selectOffer() {}", e.getMessage());
            // Handle server-side HTTP errors (5xx)
            return ResponseEntity.status(e.getStatusCode()).body(e.getResponseBodyAsString());
        } catch (Exception e) {
            log.error("An error occurred inside selectOffer() {}", e.getMessage());
            // Handle other exceptions
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("An error occurred: " + e.getMessage());
        }
    }

    public ResponseEntity<String> bookingChange(BookingChangeRequestDto bookingChangeRequestDto, String version, String token) {
        log.info("Inside bookingChange()...bookingChangeRequestDto: {} bookingChangeUrl: {}", bookingChangeRequestDto, bookingChangeUrl);
        HttpHeaders headers = HeadersUtil.getHttpHeaders(version, token);
        HttpEntity<BookingChangeRequestDto> entity = new HttpEntity<>(bookingChangeRequestDto, headers);
        try {
            ResponseEntity<String> response = restTemplate.exchange(bookingChangeUrl, HttpMethod.POST, entity, String.class);
            saveEntityAsyncBookingChangeRequest(bookingChangeRequestDto, bookingChangeUrl, response);
            return response;
        } catch (HttpClientErrorException e) {
            log.error("An error occurred inside bookingChange() {}", e.getMessage());
            // Handle client-side HTTP errors (4xx)
            return ResponseEntity.status(e.getStatusCode()).body(e.getResponseBodyAsString());
        } catch (HttpServerErrorException e) {
            log.error("An error occurred inside bookingChange() {}", e.getMessage());
            // Handle server-side HTTP errors (5xx)
            return ResponseEntity.status(e.getStatusCode()).body(e.getResponseBodyAsString());
        } catch (Exception e) {
            log.error("An error occurred inside bookingChange() {}", e.getMessage());
            // Handle other exceptions
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("An error occurred: " + e.getMessage());
        }
    }

    @Async
    public void saveEntityAsyncGetBookingChangeStatus(ChangeStatusRequestDto changeStatusRequestDto, String getBookingChangeStatusUrl, ResponseEntity<String> response) throws Exception {
        log.info("Inside saveEntityAsyncGetBookingChangeStatus()...changeStatusRequestDto: {} response: {}", changeStatusRequestDto, response);
        try {
            ObjectMapper objectMapper = new ObjectMapper();
            String jsonString = objectMapper.writeValueAsString(changeStatusRequestDto);
            ChangeStatusRequest entity = new ChangeStatusRequest();
            entity.setChangeId(changeStatusRequestDto.getChangeId());
            entity.setRequestUrl(getBookingChangeStatusUrl);
            entity.setChangeId(changeStatusRequestDto.getChangeId());
            entity.setRequestBody(jsonString);
            entity.setResponseStatus(response.getStatusCode().value());
            entity.setResponseBody(response.getBody());
            changeStatusRequestRepository.save(entity);
        } catch (Exception e) {
            log.error("An error occurred inside saveEntityAsyncGetBookingChangeStatus() {}", e.getMessage());
            throw e;
        }
    }

    @Async
    public void saveEntityAsyncGetWalletStatus(String getWalletStatusUrl, ResponseEntity<String> response) throws Exception {
        log.info("Inside saveEntityAsyncGetWalletStatus()...getWalletStatusUrl: {} response: {}", getWalletStatusUrl, response);
        try {
            WalletStatus entity = new WalletStatus();
            entity.setRequestUrl(getWalletStatusUrl);
            entity.setResponseStatus(response.getStatusCode().value());
            entity.setResponseBody(response.getBody());
            walletStatusRepository.save(entity);
        } catch (Exception e) {
            log.error("An error occurred inside saveEntityAsyncGetWalletStatus() {}", e.getMessage());
            throw e;
        }
    }

    @Async
    public void saveEntityAsyncGetWalletTransactions(String getWalletTransactionsUrl, ResponseEntity<String> response) {
        log.info("Inside saveEntityAsyncGetWalletTransactions()...getWalletTransactionsUrl: {} response: {}", getWalletTransactionsUrl, response);
        try {
            WalletTransactions entity = new WalletTransactions();
            entity.setRequestUrl(getWalletTransactionsUrl);
            entity.setResponseStatus(response.getStatusCode().value());
            entity.setResponseBody(response.getBody());
            walletTransactionsRepository.save(entity);
        } catch (Exception e) {
            log.error("An error occurred inside saveEntityAsyncGetWalletTransactions() {}", e.getMessage());
            throw e;
        }
    }

    @Async
    public void saveEntityAsyncUpdateBookingState(BookingState bookingState, String requestUrl, ResponseEntity<String> response) throws Exception {
        log.info("Inside saveEntityAsyncUpdateBookingState()...bookingState: {} response: {}", bookingState, response);
        try {
            ObjectMapper objectMapper = new ObjectMapper();
            String jsonString = objectMapper.writeValueAsString(bookingState);
            com.ncba.miniapp.model.BookingState entity = new com.ncba.miniapp.model.BookingState();
            entity.setRequestUrl(requestUrl);
            entity.setType(bookingState.getPayments().getType());
            entity.setRequestBody(jsonString);
            entity.setBookingId(bookingState.getId());
            entity.setResponseBody(response.getBody());
            entity.setResponseStatus(response.getStatusCode().value());
            bookingStateRepository.save(entity);
        } catch (Exception e) {
            log.error("An error occurred inside saveEntityAsyncUpdateBookingState() {}", e.getMessage());
            throw e;
        }
    }

    @Async
    public void saveEntityAsyncSelectOffer(SelectOfferRequestDto selectOfferRequestDto, String selectOfferUrl, ResponseEntity<String> response) throws Exception {
        log.info("Inside saveEntityAsyncSelectOffer()...selectOfferRequestDto: {} response: {}", selectOfferRequestDto, response);
        try {
            ObjectMapper objectMapper = new ObjectMapper();
            String jsonString = objectMapper.writeValueAsString(selectOfferRequestDto);
            SelectOfferRequest entity = new SelectOfferRequest();
            entity.setRequestUrl(selectOfferUrl);
            entity.setOfferId(selectOfferRequestDto.getOfferId());
            entity.setResultId(selectOfferRequestDto.getOfferId());
            entity.setRequestBody(jsonString);
            entity.setResponseBody(response.getBody());
            selectOfferRequestRepository.save(entity);
        } catch (Exception e) {
            log.error("An error occurred inside saveEntityAsyncSelectOffer() {}", e.getMessage());
            throw e;
        }
    }

    @Async
    public void saveEntityAsyncBookingChangeRequest(BookingChangeRequestDto bookingChangeRequestDto, String bookingChangeUrl, ResponseEntity<String> response) throws Exception {
        log.info("Inside saveEntityAsyncBookingChangeRequest()...bookingChangeRequestDto: {} response: {}", bookingChangeRequestDto, response);
        try {
            ObjectMapper objectMapper = new ObjectMapper();
            String jsonString = objectMapper.writeValueAsString(bookingChangeRequestDto);
            BookingChangeRequest entity = new BookingChangeRequest();
            entity.setChangeRequest(bookingChangeRequestDto.getChangeRequest());
            entity.setRequestUrl(bookingChangeUrl);
            entity.setOrderId(bookingChangeRequestDto.getOrderId());
            entity.setRequestBody(jsonString);
            entity.setResponseStatus(response.getStatusCode().value());
            entity.setResponseBody(response.getBody());
            bookingChangeRequestRepository.save(entity);
        } catch (Exception e) {
            log.error("An error occurred inside saveEntityAsyncBookingChangeRequest() {}", e.getMessage());
            throw e;
        }
    }

}
