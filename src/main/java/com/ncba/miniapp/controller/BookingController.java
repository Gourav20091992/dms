package com.ncba.miniapp.controller;

import com.ncba.miniapp.dto.request.BookingRequest;
import com.ncba.miniapp.service.BookingService;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/booking")
@Tag(name = "BookingController", description = "Booking Controller APIs")
@Slf4j
public class BookingController {
    @Autowired
    BookingService bookingService;

    @PostMapping("/createBooking")
    public ResponseEntity<String> createBooking(
            @RequestHeader("Travelduqa-Version") String version,
            @RequestHeader("Authorization") String token,
            @RequestBody BookingRequest bookingRequest) {
        log.info("Inside createBooking()...bookingRequest: {}", bookingRequest);
        return bookingService.createBooking(bookingRequest, version, token);
    }
}
