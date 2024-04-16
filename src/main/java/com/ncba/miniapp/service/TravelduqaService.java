package com.ncba.miniapp.service;

import com.ncba.miniapp.configuration.HeadersConfig;
import com.ncba.miniapp.dto.request.BookingState;
import com.ncba.miniapp.dto.request.ChangeStatusRequestDto;
import com.ncba.miniapp.dto.request.SelectOfferRequestDto;
import com.ncba.miniapp.dto.request.travelduqa.createbooking.BookingChangeRequestDto;
import com.ncba.miniapp.model.SMSLog;
import com.ncba.miniapp.repository.SMSLogRepository;
import com.ncba.miniapp.util.ProcessAsync;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.*;
import org.springframework.stereotype.Service;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.HttpServerErrorException;
import org.springframework.web.client.RestTemplate;

import java.security.SecureRandom;

@Service
@Slf4j
public class TravelduqaService {
    private final ProcessAsync processAsync;
    private final RestTemplate restTemplate;
    private final HeadersConfig headersConfig;
    private final SMSLogRepository smsLogRepository;
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
    @Value("${smsUrl}")
    private String smsUrl;

    @Autowired
    public TravelduqaService(RestTemplate restTemplate, HeadersConfig headersConfig, ProcessAsync processAsync, SMSLogRepository smsLogRepository) {
        this.restTemplate = restTemplate;
        this.headersConfig = headersConfig;
        this.processAsync = processAsync;
        this.smsLogRepository = smsLogRepository;
    }

    public ResponseEntity<String> getBookingChangeStatus(ChangeStatusRequestDto changeStatusRequestDto) {
        log.info("Inside getBookingChangeStatus()...changeStatusRequestDto: {} getBookingChangeStatusUrl: {}", changeStatusRequestDto, getBookingChangeStatusUrl);
        HttpHeaders headers = headersConfig.getHttpHeaders();
        HttpEntity<ChangeStatusRequestDto> entity = new HttpEntity<>(changeStatusRequestDto, headers);
        try {
            ResponseEntity<String> response = restTemplate.exchange(getBookingChangeStatusUrl, HttpMethod.POST, entity, String.class);
            processAsync.saveEntityAsyncGetBookingChangeStatus(changeStatusRequestDto, getBookingChangeStatusUrl, response);
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

    public ResponseEntity<String> getWalletStatus() {
        log.info("Inside getWalletStatus()...getWalletStatusUrl: {}", getWalletStatusUrl);
        HttpHeaders headers = headersConfig.getHttpHeaders();
        HttpEntity<String> entity = new HttpEntity<>(headers);
        try {
            ResponseEntity<String> response = restTemplate.exchange(getWalletStatusUrl, HttpMethod.GET, entity, String.class);
            processAsync.saveEntityAsyncGetWalletStatus(getWalletStatusUrl, response);
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

    public ResponseEntity<String> getWalletTransactions() {
        log.info("Inside getWalletTransactions()...getWalletTransactionsUrl: {}", getWalletTransactionsUrl);
        HttpHeaders headers = headersConfig.getHttpHeaders();
        HttpEntity<String> entity = new HttpEntity<>(headers);
        try {
            ResponseEntity<String> response = restTemplate.exchange(getWalletTransactionsUrl, HttpMethod.GET, entity, String.class);
            processAsync.saveEntityAsyncGetWalletTransactions(getWalletTransactionsUrl, response);
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

    public ResponseEntity<String> updateBookingState(BookingState bookingState) {
        log.info("Inside updateBookingState()...bookingState: {} updateBookingStateUrl: {}", bookingState, updateBookingStateUrl);
        HttpHeaders headers = headersConfig.getHttpHeaders();
        HttpEntity<BookingState> entity = new HttpEntity<>(bookingState, headers);
        try {
            ResponseEntity<String> response = restTemplate.exchange(updateBookingStateUrl, HttpMethod.POST, entity, String.class);
            processAsync.saveEntityAsyncUpdateBookingState(bookingState, updateBookingStateUrl, response);
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

    public ResponseEntity<String> getBooking(BookingState bookingState) {
        log.info("Inside getBooking()...bookingState: {} getBookingStateUrl: {}", bookingState, getBookingStateUrl);
        HttpHeaders headers = headersConfig.getHttpHeaders();
        HttpEntity<BookingState> entity = new HttpEntity<>(bookingState, headers);
        try {
            ResponseEntity<String> response = restTemplate.exchange(getBookingStateUrl, HttpMethod.POST, entity, String.class);
            processAsync.saveEntityAsyncUpdateBookingState(bookingState, getBookingStateUrl, response);
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

    public ResponseEntity<String> selectOffer(SelectOfferRequestDto selectOfferRequestDto) {
        log.info("Inside selectOffer()...selectOfferRequestDto: {} selectOfferUrl: {}", selectOfferRequestDto, selectOfferUrl);
        HttpHeaders headers = headersConfig.getHttpHeaders();
        HttpEntity<SelectOfferRequestDto> entity = new HttpEntity<>(selectOfferRequestDto, headers);

        try {
            ResponseEntity<String> response = restTemplate.exchange(selectOfferUrl, HttpMethod.POST, entity, String.class);
            processAsync.saveEntityAsyncSelectOffer(selectOfferRequestDto, selectOfferUrl, response);
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

    public ResponseEntity<String> bookingChange(BookingChangeRequestDto bookingChangeRequestDto) {
        log.info("Inside bookingChange()...bookingChangeRequestDto: {} bookingChangeUrl: {}", bookingChangeRequestDto, bookingChangeUrl);
        HttpHeaders headers = headersConfig.getHttpHeaders();
        HttpEntity<BookingChangeRequestDto> entity = new HttpEntity<>(bookingChangeRequestDto, headers);
        try {
            ResponseEntity<String> response = restTemplate.exchange(bookingChangeUrl, HttpMethod.POST, entity, String.class);
            processAsync.saveEntityAsyncBookingChangeRequest(bookingChangeRequestDto, bookingChangeUrl, response);
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

    public ResponseEntity<String> getBusRefNo(String mblNo) {
        log.info("Inside getBusRefNo()...mblNo: {}", mblNo);
        try {
            int randomSixDigitNo = generateRandomSixDigitNumber();
            long randomElevenDigitNo = generateRandomElevenDigitNumber();
            processAsync.saveEntitySMSTransaction(randomSixDigitNo, randomElevenDigitNo, mblNo, smsUrl);
            String jsonResponse = String.format("{\"busRefNo\": \"%s\"}", randomElevenDigitNo);
            log.info("Inside getBusRefNo()...jsonResponse: {}", jsonResponse);
            return new ResponseEntity<>(jsonResponse, HttpStatus.OK);
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

    public ResponseEntity<String> validateBusRefNo(String mblNo, String busRefNo, String otp) {
        log.info("Inside validateBusRefNo()...mblNo: {}, busRefNo : {}, otp : {}", mblNo, busRefNo, otp);
        try {
            SMSLog smsLog = smsLogRepository.findFirstByBusRefNoAndMblNoOrderByCreatedDateDesc(busRefNo, mblNo);
            long currentTime = System.currentTimeMillis();
            if (smsLog == null) {
                log.info("SMSLog is null");
                return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("SMS Log is null");
            }
            log.info("currentTime is : {}, generatedTime is : {} and difference is : {}", currentTime, smsLog.getGenerationTime(), (currentTime - smsLog.getGenerationTime()));
            if ((currentTime - smsLog.getGenerationTime()) <= 60000 && otp.equals(smsLog.getSixDigitNo())) {
                String jsonResponse = String.format("{\"success\": \"%s\"}", "Request is Valid");
                return new ResponseEntity<>(jsonResponse, HttpStatus.OK);
            } else {
                String errorResponse = String.format("{\"error\": \"%s\"}", "Invalid request, Please try again");
                return new ResponseEntity<>(errorResponse, HttpStatus.BAD_REQUEST);
            }
        } catch (HttpClientErrorException e) {
            log.error("An error occurred inside validateBusRefNo() {}", e.getMessage());
            // Handle client-side HTTP errors (4xx)
            return ResponseEntity.status(e.getStatusCode()).body(e.getResponseBodyAsString());
        } catch (HttpServerErrorException e) {
            log.error("An error occurred inside validateBusRefNo() {}", e.getMessage());
            // Handle server-side HTTP errors (5xx)
            return ResponseEntity.status(e.getStatusCode()).body(e.getResponseBodyAsString());
        } catch (Exception e) {
            log.error("An error occurred inside validateBusRefNo() {}", e.getMessage());
            // Handle other exceptions
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("An error occurred: " + e.getMessage());
        }
    }

    private long generateRandomElevenDigitNumber() {
        SecureRandom secureRandom = new SecureRandom();
        return 10000000000L + secureRandom.nextLong(90000000000L);
    }

    private int generateRandomSixDigitNumber() {
        SecureRandom secureRandom = new SecureRandom();
        return 100000 + secureRandom.nextInt(900000);
    }
}

