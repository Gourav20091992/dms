package com.ncba.miniapp.controller;

import com.ncba.miniapp.dto.request.BookingRequest;
import com.ncba.miniapp.service.BookingService;
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
@RequestMapping("/api/v1/booking")
@Tag(name = "BookingController", description = "Booking Controller APIs")
@Slf4j
@Validated
public class BookingController {
    private final BookingService bookingService;

    @Autowired
    public BookingController(BookingService bookingService) {
        this.bookingService = bookingService;
    }

    @PostMapping("/createBooking")
    public ResponseEntity<String> createBooking(@RequestBody BookingRequest bookingRequest) {
        log.info("Inside createBooking()...bookingRequest: {}", bookingRequest);
        return bookingService.createBooking(bookingRequest);
    }
}
