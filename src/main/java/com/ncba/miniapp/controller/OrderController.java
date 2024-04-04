package com.ncba.miniapp.controller;

import com.ncba.miniapp.dto.request.*;
import com.ncba.miniapp.service.OrderService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/order")
@Slf4j
public class OrderController {
    @Autowired
    OrderService orderService;

    @PostMapping("/paymentStatusCallback")
    public ResponseEntity<String> paymentStatusCallback(@RequestBody PaymentInfo paymentInfo) {
        log.info("Inside paymentStatusCallback()...paymentInfo: {}", paymentInfo);
        return orderService.paymentStatusCallback(paymentInfo);
    }

    @PostMapping("/placeOrder")
    public ResponseEntity<String> placeOrder(@RequestBody PreOrderData preOrderData) {
        log.info("Inside placeOrder()...preOrderData: {}", preOrderData);
        return orderService.placeOrder(preOrderData);
    }

    @PostMapping("/queryOrderStatus")
    public ResponseEntity<String> queryOrderStatus(@RequestBody OrderQueryRequest orderQueryRequest) {
        log.info("Inside queryOrderStatus()...orderQueryRequest: {}", orderQueryRequest);
        return orderService.queryOrderStatus(orderQueryRequest);
    }

    @PostMapping("/queryReverseOrder")
    public ResponseEntity<String> queryReverseOrder(@RequestBody ReverseOrderStatus reverseOrderStatus) {
        log.info("Inside queryReverseOrder()...reverseOrderStatus: {}", reverseOrderStatus);
        return orderService.queryReverseOrder(reverseOrderStatus);
    }

    @PostMapping("/reverseOrder")
    public ResponseEntity<String> reverseOrder(@RequestBody ReverseOrderRequest reverseOrderRequest) {
        log.info("Inside reverseOrder()...reverseOrderRequest: {}", reverseOrderRequest);
        return orderService.reverseOrder(reverseOrderRequest);
    }
}
