package com.ncba.miniapp.controller;

import com.ncba.miniapp.dto.request.CancelBookingRequest;
import com.ncba.miniapp.service.CancelBookingService;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/duqa")
@Slf4j
@Tag(name = "CancelBookingController", description = "Cancel Booking Controller APIs")
@Validated
public class CancelBookingController {
    private final CancelBookingService cancelBookingService;

    @Autowired
    public CancelBookingController(CancelBookingService cancelBookingService) {
        this.cancelBookingService = cancelBookingService;
    }

    @PostMapping("/cancelBooking")
    public ResponseEntity<String> cancelBooking(@RequestBody CancelBookingRequest cancelBookingRequest) {
        log.info("Inside cancelBooking()...cancelBookingRequest: {}", cancelBookingRequest);
        return cancelBookingService.cancelBooking(cancelBookingRequest);
    }
}
