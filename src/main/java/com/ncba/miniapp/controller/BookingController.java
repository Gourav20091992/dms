package com.ncba.miniapp.controller;

import com.ncba.miniapp.dto.request.BookingRequest;
import com.ncba.miniapp.dto.request.FinalBookingDto;
import com.ncba.miniapp.dto.response.FinalBookingResponse;
import com.ncba.miniapp.service.BookingService;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.constraints.Pattern;
import lombok.extern.slf4j.Slf4j;
import org.json.JSONException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

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

    @PostMapping("/persistBooking")
    public ResponseEntity<String> persistBooking(@RequestBody FinalBookingDto finalBookingDto) {
        log.info("Inside persistBooking()...finalBookingDto: {}", finalBookingDto);
        return bookingService.persistBooking(finalBookingDto);
    }

    @GetMapping("/getPrevious10Bookings")
    public ResponseEntity<List<FinalBookingResponse>> getPrevious10Bookings(
            @RequestParam @Pattern(regexp = "^(7\\d{8}|1\\d{8}|07\\d{8}|01\\d{8}|254\\d{9}|\\+254\\d{9})$", message = "Invalid Kenyan phone number format.") String mblNo) throws JSONException {
        log.info("Inside getPrevious10Bookings()...mblNo: {}", mblNo);
        List<FinalBookingResponse> finalBookingResponse = bookingService.getPrevious10Bookings(mblNo);
        log.info("Inside getPrevious10Bookings()...finalBookingResponse {}", finalBookingResponse);
        return new ResponseEntity<>(finalBookingResponse, HttpStatus.CREATED);
    }
}
