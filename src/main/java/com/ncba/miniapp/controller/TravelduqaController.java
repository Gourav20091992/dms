package com.ncba.miniapp.controller;

import com.ncba.miniapp.dto.request.BookingState;
import com.ncba.miniapp.dto.request.ChangeStatusRequestDto;
import com.ncba.miniapp.dto.request.SelectOfferRequestDto;
import com.ncba.miniapp.dto.request.travelduqa.createbooking.BookingChangeRequestDto;
import com.ncba.miniapp.service.TravelduqaService;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.constraints.Pattern;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/duqa")
@Slf4j
@Tag(name = "TravelduqaController", description = "Travelduqa Controller APIs")
@Validated
public class TravelduqaController {
    private final TravelduqaService travelduqaService;

    @Autowired
    public TravelduqaController(TravelduqaService travelduqaService) {
        this.travelduqaService = travelduqaService;
    }

    @PostMapping("/getBookingChangeStatus")
    public ResponseEntity<String> getBookingChangeStatus(@RequestBody ChangeStatusRequestDto changeStatusRequestDto) {
        log.info("Inside reverseOrder()...changeStatusRequestDto: {}", changeStatusRequestDto);
        return travelduqaService.getBookingChangeStatus(changeStatusRequestDto);
    }

    @GetMapping("/getWalletStatus")
    public ResponseEntity<String> getWalletStatus() {
        log.info("Inside reverseOrder()...");
        return travelduqaService.getWalletStatus();
    }

    @GetMapping("/getWalletTransactions")
    public ResponseEntity<String> getWalletTransactions() {
        log.info("Inside reverseOrder()...");
        return travelduqaService.getWalletTransactions();
    }

    @PostMapping("/updateBookingState")
    public ResponseEntity<String> updateBookingState(@RequestBody BookingState bookingState) {
        log.info("Inside reverseOrder()...bookingState: {}", bookingState);
        return travelduqaService.updateBookingState(bookingState);
    }

    @PostMapping("/getBooking")
    public ResponseEntity<String> getBooking(@RequestBody BookingState bookingState) {
        log.info("Inside reverseOrder()...bookingState: {}", bookingState);
        return travelduqaService.getBooking(bookingState);
    }

    @PostMapping("/selectOffer")
    public ResponseEntity<String> selectOffer(@RequestBody SelectOfferRequestDto selectOfferRequestDto) {
        log.info("Inside selectOffer()...selectOfferRequestDto: {}", selectOfferRequestDto);
        return travelduqaService.selectOffer(selectOfferRequestDto);
    }

    @PostMapping("/bookingChange")
    public ResponseEntity<String> bookingChange(@RequestBody BookingChangeRequestDto bookingChangeRequestDto) {
        log.info("Inside bookingChange()...bookingChangeRequestDto: {}", bookingChangeRequestDto);
        return travelduqaService.bookingChange(bookingChangeRequestDto);
    }

    @PostMapping("/getBusRefNo")
    public ResponseEntity<String> getBusRefNo(
            @RequestParam @Pattern(regexp = "^(7\\d{8}|1\\d{8}|07\\d{8}|01\\d{8}|254\\d{9}|\\+254\\d{9})$", message = "Invalid Kenyan phone number format.") String mblNo) {
        log.info("Inside getBusRefNo()...mblNo: {}", mblNo);
        return travelduqaService.getBusRefNo(mblNo);
    }

    @PostMapping("/validateBusRefNo")
    public ResponseEntity<String> validateBusRefNo(
            @RequestParam @Pattern(regexp = "^(7\\d{8}|1\\d{8}|07\\d{8}|01\\d{8}|254\\d{9}|\\+254\\d{9})$", message = "Invalid Kenyan phone number format.") String mblNo,
            @RequestParam @Pattern(regexp = "^\\d{11}$", message = "Invalid Bus Reference number format.") String busRefNo,
            @RequestParam @Pattern(regexp = "^\\d{6}$", message = "Invalid pin number format.") String otp) {
        log.info("Inside validateBusRefNo()...mblNo: {}, busRefNo : {}, otp : {}", mblNo, busRefNo, otp);
        return travelduqaService.validateBusRefNo(mblNo, busRefNo, otp);
    }

}
