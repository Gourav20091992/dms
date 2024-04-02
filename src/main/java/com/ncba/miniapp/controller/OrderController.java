package com.ncba.miniapp.controller;

import com.ncba.miniapp.dto.request.*;
import com.ncba.miniapp.service.OrderService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/order")
@Slf4j
public class OrderController {
    @Autowired
    OrderService orderService;

    @PostMapping("/paymentStatusCallback")
    public ResponseEntity<String> paymentStatusCallback(
            @RequestHeader("Travelduqa-Version") String version,
            @RequestHeader("Authorization") String token,
            @RequestBody PaymentInfo paymentInfo) {
        log.info("Inside paymentStatusCallback()...paymentInfo: {}", paymentInfo);
        return orderService.paymentStatusCallback(paymentInfo, version, token);
    }

    @PostMapping("/placeOrder")
    public ResponseEntity<String> placeOrder(
            @RequestHeader("Travelduqa-Version") String version,
            @RequestHeader("Authorization") String token,
            @RequestBody PreOrderData preOrderData) {
        log.info("Inside placeOrder()...preOrderData: {}", preOrderData);
        return orderService.placeOrder(preOrderData, version, token);
    }

    @PostMapping("/queryOrderStatus")
    public ResponseEntity<String> queryOrderStatus(
            @RequestHeader("Travelduqa-Version") String version,
            @RequestHeader("Authorization") String token,
            @RequestBody OrderQueryRequest orderQueryRequest) {
        log.info("Inside queryOrderStatus()...orderQueryRequest: {}", orderQueryRequest);
        return orderService.queryOrderStatus(orderQueryRequest, version, token);
    }

    @PostMapping("/queryReverseOrder")
    public ResponseEntity<String> queryReverseOrder(
            @RequestHeader("Travelduqa-Version") String version,
            @RequestHeader("Authorization") String token,
            @RequestBody ReverseOrderStatus reverseOrderStatus) {
        log.info("Inside queryReverseOrder()...reverseOrderStatus: {}", reverseOrderStatus);
        return orderService.queryReverseOrder(reverseOrderStatus, version, token);
    }

    @PostMapping("/reverseOrder")
    public ResponseEntity<String> reverseOrder(
            @RequestHeader("Travelduqa-Version") String version,
            @RequestHeader("Authorization") String token,
            @RequestBody ReverseOrderRequest reverseOrderRequest) {
        log.info("Inside reverseOrder()...reverseOrderRequest: {}", reverseOrderRequest);
        return orderService.reverseOrder(reverseOrderRequest, version, token);
    }
}
