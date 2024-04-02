package com.ncba.miniapp.controller;

import com.ncba.miniapp.dto.request.BookingState;
import com.ncba.miniapp.dto.request.ChangeStatusRequestDto;
import com.ncba.miniapp.dto.request.SelectOfferRequestDto;
import com.ncba.miniapp.dto.request.travelduqa.createbooking.BookingChangeRequestDto;
import com.ncba.miniapp.service.TravelduqaService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/duqa")
@Slf4j
public class TravelduqaController {
    @Autowired
    TravelduqaService travelduqaService;

    @PostMapping("/getBookingChangeStatus")
    public ResponseEntity<String> getBookingChangeStatus(@RequestBody ChangeStatusRequestDto changeStatusRequestDto, @RequestHeader("Travelduqa-Version") String version,
                                                         @RequestHeader("Authorization") String token) {
        log.info("Inside reverseOrder()...changeStatusRequestDto: {}", changeStatusRequestDto);
        return travelduqaService.getBookingChangeStatus(changeStatusRequestDto, version, token);
    }

    @GetMapping("/getWalletStatus")
    public ResponseEntity<String> getWalletStatus(@RequestHeader("Travelduqa-Version") String version,
                                                  @RequestHeader("Authorization") String token) {
        log.info("Inside reverseOrder()...");
        return travelduqaService.getWalletStatus(version, token);
    }

    @GetMapping("/getWalletTransactions")
    public ResponseEntity<String> getWalletTransactions(
            @RequestHeader("Travelduqa-Version") String version,
            @RequestHeader("Authorization") String token) {
        log.info("Inside reverseOrder()...");
        return travelduqaService.getWalletTransactions(version, token);
    }

    @PostMapping("/updateBookingState")
    public ResponseEntity<String> updateBookingState(
            @RequestHeader("Travelduqa-Version") String version,
            @RequestHeader("Authorization") String token,
            @RequestBody BookingState bookingState) {
        log.info("Inside reverseOrder()...bookingState: {}", bookingState);
        return travelduqaService.updateBookingState(bookingState, version, token);
    }

    @PostMapping("/getBooking")
    public ResponseEntity<String> getBooking(
            @RequestHeader("Travelduqa-Version") String version,
            @RequestHeader("Authorization") String token,
            @RequestBody BookingState bookingState) {
        log.info("Inside reverseOrder()...bookingState: {}", bookingState);
        return travelduqaService.getBooking(bookingState, version, token);
    }

    @PostMapping("/selectOffer")
    public ResponseEntity<String> selectOffer(
            @RequestHeader("Travelduqa-Version") String version,
            @RequestHeader("Authorization") String token,
            @RequestBody SelectOfferRequestDto selectOfferRequestDto) {
        log.info("Inside selectOffer()...selectOfferRequestDto: {}", selectOfferRequestDto);
        return travelduqaService.selectOffer(selectOfferRequestDto, version, token);
    }

    @PostMapping("/bookingChange")
    public ResponseEntity<String> bookingChange(@RequestBody BookingChangeRequestDto bookingChangeRequestDto, @RequestHeader("Travelduqa-Version") String version,
                                                @RequestHeader("Authorization") String token) {
        log.info("Inside bookingChange()...bookingChangeRequestDto: {}", bookingChangeRequestDto);
        return travelduqaService.bookingChange(bookingChangeRequestDto, version, token);
    }

}
