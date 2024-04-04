package com.ncba.miniapp.service;

import com.ncba.miniapp.configuration.HeadersConfig;
import com.ncba.miniapp.dto.request.*;
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
public class OrderService {
    private final RestTemplate restTemplate;
    private final HeadersConfig headersConfig;
    @Value("${paymentStatusCallbackUrl}")
    private String paymentStatusCallbackUrl;
    @Value("${placeOrderUrl}")
    private String placeOrderUrl;
    @Value("${queryOrderStatusUrl}")
    private String queryOrderStatusUrl;
    @Value("${queryReverseOrderUrl}")
    private String queryReverseOrderUrl;
    @Value("${reverseOrderUrl}")
    private String reverseOrderUrl;

    @Autowired
    public OrderService(RestTemplate restTemplate, HeadersConfig headersConfig) {
        this.restTemplate = restTemplate;
        this.headersConfig = headersConfig;
    }

    public ResponseEntity<String> paymentStatusCallback(PaymentInfo paymentInfo) {
        log.info("Inside  paymentStatusCallback()...paymentInfo: {}", paymentInfo);
        HttpHeaders headers = headersConfig.getHttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        HttpEntity<PaymentInfo> entity = new HttpEntity<>(paymentInfo, headers);
        try {
            return restTemplate.exchange(paymentStatusCallbackUrl, HttpMethod.POST, entity, String.class);
        } catch (HttpClientErrorException e) {
            log.error("An error occurred inside paymentStatusCallback() {}", e.getMessage());
            // Handle client-side HTTP errors (4xx)
            return ResponseEntity.status(e.getStatusCode()).body(e.getResponseBodyAsString());
        } catch (HttpServerErrorException e) {
            log.error("An error occurred inside paymentStatusCallback() {}", e.getMessage());
            // Handle server-side HTTP errors (5xx)
            return ResponseEntity.status(e.getStatusCode()).body(e.getResponseBodyAsString());
        } catch (Exception e) {
            log.error("An error occurred inside paymentStatusCallback() {}", e.getMessage());
            // Handle other exceptions
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("An error occurred: " + e.getMessage());
        }
    }

    public ResponseEntity<String> placeOrder(PreOrderData preOrderData) {
        log.info("Inside  placeOrder()...preOrderData: {}", preOrderData);
        HttpHeaders headers = headersConfig.getHttpHeaders();
        HttpEntity<PreOrderData> entity = new HttpEntity<>(preOrderData, headers);

        try {
            return restTemplate.exchange(placeOrderUrl, HttpMethod.POST, entity, String.class);
        } catch (HttpClientErrorException e) {
            log.error("An error occurred inside placeOrder() {}", e.getMessage());
            // Handle client-side HTTP errors (4xx)
            return ResponseEntity.status(e.getStatusCode()).body(e.getResponseBodyAsString());
        } catch (HttpServerErrorException e) {
            log.error("An error occurred inside placeOrder() {}", e.getMessage());
            // Handle server-side HTTP errors (5xx)
            return ResponseEntity.status(e.getStatusCode()).body(e.getResponseBodyAsString());
        } catch (Exception e) {
            log.error("An error occurred inside placeOrder() {}", e.getMessage());
            // Handle other exceptions
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("An error occurred: " + e.getMessage());
        }
    }

    public ResponseEntity<String> queryOrderStatus(OrderQueryRequest orderQueryRequest) {
        log.info("Inside  queryOrderStatus()...orderQueryRequest: {}", orderQueryRequest);
        HttpHeaders headers = headersConfig.getHttpHeaders();
        HttpEntity<OrderQueryRequest> entity = new HttpEntity<>(orderQueryRequest, headers);

        try {
            return restTemplate.exchange(queryOrderStatusUrl, HttpMethod.POST, entity, String.class);
        } catch (HttpClientErrorException e) {
            log.error("An error occurred inside queryOrderStatus() {}", e.getMessage());
            // Handle client-side HTTP errors (4xx)
            return ResponseEntity.status(e.getStatusCode()).body(e.getResponseBodyAsString());
        } catch (HttpServerErrorException e) {
            log.error("An error occurred inside queryOrderStatus() {}", e.getMessage());
            // Handle server-side HTTP errors (5xx)
            return ResponseEntity.status(e.getStatusCode()).body(e.getResponseBodyAsString());
        } catch (Exception e) {
            log.error("An error occurred inside queryOrderStatus() {}", e.getMessage());
            // Handle other exceptions
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("An error occurred: " + e.getMessage());
        }
    }

    public ResponseEntity<String> queryReverseOrder(ReverseOrderStatus reverseOrderStatus) {
        log.info("Inside  queryReverseOrder()...reverseOrderStatus: {}", reverseOrderStatus);
        HttpHeaders headers = headersConfig.getHttpHeaders();
        HttpEntity<ReverseOrderStatus> entity = new HttpEntity<>(reverseOrderStatus, headers);

        try {
            return restTemplate.exchange(queryReverseOrderUrl, HttpMethod.POST, entity, String.class);
        } catch (HttpClientErrorException e) {
            log.error("An error occurred inside queryReverseOrder() {}", e.getMessage());
            // Handle client-side HTTP errors (4xx)
            return ResponseEntity.status(e.getStatusCode()).body(e.getResponseBodyAsString());
        } catch (HttpServerErrorException e) {
            log.error("An error occurred inside queryReverseOrder() {}", e.getMessage());
            // Handle server-side HTTP errors (5xx)
            return ResponseEntity.status(e.getStatusCode()).body(e.getResponseBodyAsString());
        } catch (Exception e) {
            log.error("An error occurred inside queryReverseOrder() {}", e.getMessage());
            // Handle other exceptions
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("An error occurred: " + e.getMessage());
        }
    }

    public ResponseEntity<String> reverseOrder(ReverseOrderRequest reverseOrderRequest) {
        log.info("Inside  reverseOrder()...reverseOrderRequest: {}", reverseOrderRequest);
        HttpHeaders headers = headersConfig.getHttpHeaders();
        HttpEntity<ReverseOrderRequest> entity = new HttpEntity<>(reverseOrderRequest, headers);

        try {
            return restTemplate.exchange(reverseOrderUrl, HttpMethod.POST, entity, String.class);
        } catch (HttpClientErrorException e) {
            log.error("An error occurred inside reverseOrder() {}", e.getMessage());
            // Handle client-side HTTP errors (4xx)
            return ResponseEntity.status(e.getStatusCode()).body(e.getResponseBodyAsString());
        } catch (HttpServerErrorException e) {
            log.error("An error occurred inside reverseOrder() {}", e.getMessage());
            // Handle server-side HTTP errors (5xx)
            return ResponseEntity.status(e.getStatusCode()).body(e.getResponseBodyAsString());
        } catch (Exception e) {
            log.error("An error occurred inside reverseOrder() {}", e.getMessage());
            // Handle other exceptions
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("An error occurred: " + e.getMessage());
        }
    }
}
