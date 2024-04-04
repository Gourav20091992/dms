package com.ncba.miniapp.util;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.ncba.miniapp.dto.request.BookingState;
import com.ncba.miniapp.dto.request.CancelBookingRequest;
import com.ncba.miniapp.dto.request.ChangeStatusRequestDto;
import com.ncba.miniapp.dto.request.SelectOfferRequestDto;
import com.ncba.miniapp.dto.request.travelduqa.createbooking.BookingChangeRequestDto;
import com.ncba.miniapp.model.*;
import com.ncba.miniapp.repository.*;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Component;

@Slf4j
@Component
public class ProcessAsync {

    private CancelBookingRepository cancelBookingRepository;
    private ChangeStatusRequestRepository changeStatusRequestRepository;
    private com.ncba.miniapp.repository.WalletStatusRepository walletStatusRepository;
    private WalletTransactionsRepository walletTransactionsRepository;
    private BookingStateRepository bookingStateRepository;
    private SelectOfferRequestRepository selectOfferRequestRepository;
    private BookingChangeRequestRepository bookingChangeRequestRepository;

    @Autowired
    public ProcessAsync(CancelBookingRepository cancelBookingRepository,
                        ChangeStatusRequestRepository changeStatusRequestRepository,
                        com.ncba.miniapp.repository.WalletStatusRepository walletStatusRepository,
                        WalletTransactionsRepository walletTransactionsRepository,
                        BookingStateRepository bookingStateRepository,
                        SelectOfferRequestRepository selectOfferRequestRepository,
                        BookingChangeRequestRepository bookingChangeRequestRepository) {
        this.cancelBookingRepository = cancelBookingRepository;
        this.changeStatusRequestRepository = changeStatusRequestRepository;
        this.walletTransactionsRepository = walletTransactionsRepository;
        this.bookingStateRepository = bookingStateRepository;
        this.selectOfferRequestRepository = selectOfferRequestRepository;
        this.bookingChangeRequestRepository = bookingChangeRequestRepository;
        this.walletStatusRepository = walletStatusRepository;
    }

    @Async
    public void saveEntityAsyncCancelBooking(CancelBookingRequest cancelBookingRequest, String requestUrl, ResponseEntity<String> response) throws JsonProcessingException {
        log.info("Inside saveEntityAsyncCancelBooking()...cancelBookingRequest: {} response: {}", cancelBookingRequest, response);
        try {
            ObjectMapper objectMapper = new ObjectMapper();
            String jsonString = objectMapper.writeValueAsString(cancelBookingRequest);
            CancelBooking cancelBooking = new CancelBooking();
            cancelBooking.setRequestUrl(requestUrl);
            cancelBooking.setRequestBody(jsonString);
            cancelBooking.setResponseBody(response.getBody());
            cancelBooking.setResponseStatus(response.getStatusCode().value());
            cancelBooking.setOrderId(cancelBookingRequest.getOrderId());
            cancelBookingRepository.save(cancelBooking);
        } catch (Exception e) {
            log.error("An error occurred inside saveEntityAsyncCancelBooking() {}", e.getMessage());
            throw e;
        }
    }

    @Async
    public void saveEntityAsyncGetBookingChangeStatus(ChangeStatusRequestDto changeStatusRequestDto, String getBookingChangeStatusUrl, ResponseEntity<String> response) throws Exception {
        log.info("Inside saveEntityAsyncGetBookingChangeStatus()...changeStatusRequestDto: {} response: {}", changeStatusRequestDto, response);
        try {
            ObjectMapper objectMapper = new ObjectMapper();
            String jsonString = objectMapper.writeValueAsString(changeStatusRequestDto);
            ChangeStatusRequest entity = new ChangeStatusRequest();
            entity.setChangeId(changeStatusRequestDto.getChangeId());
            entity.setRequestUrl(getBookingChangeStatusUrl);
            entity.setChangeId(changeStatusRequestDto.getChangeId());
            entity.setRequestBody(jsonString);
            entity.setResponseStatus(response.getStatusCode().value());
            entity.setResponseBody(response.getBody());
            changeStatusRequestRepository.save(entity);
        } catch (Exception e) {
            log.error("An error occurred inside saveEntityAsyncGetBookingChangeStatus() {}", e.getMessage());
            throw e;
        }
    }

    @Async
    public void saveEntityAsyncGetWalletStatus(String getWalletStatusUrl, ResponseEntity<String> response) throws Exception {
        log.info("Inside saveEntityAsyncGetWalletStatus()...getWalletStatusUrl: {} response: {}", getWalletStatusUrl, response);
        try {
            WalletStatus entity = new WalletStatus();
            entity.setRequestUrl(getWalletStatusUrl);
            entity.setResponseStatus(response.getStatusCode().value());
            entity.setResponseBody(response.getBody());
            walletStatusRepository.save(entity);
        } catch (Exception e) {
            log.error("An error occurred inside saveEntityAsyncGetWalletStatus() {}", e.getMessage());
            throw e;
        }
    }

    @Async
    public void saveEntityAsyncGetWalletTransactions(String getWalletTransactionsUrl, ResponseEntity<String> response) {
        log.info("Inside saveEntityAsyncGetWalletTransactions()...getWalletTransactionsUrl: {} response: {}", getWalletTransactionsUrl, response);
        try {
            WalletTransactions entity = new WalletTransactions();
            entity.setRequestUrl(getWalletTransactionsUrl);
            entity.setResponseStatus(response.getStatusCode().value());
            entity.setResponseBody(response.getBody());
            walletTransactionsRepository.save(entity);
        } catch (Exception e) {
            log.error("An error occurred inside saveEntityAsyncGetWalletTransactions() {}", e.getMessage());
            throw e;
        }
    }

    @Async
    public void saveEntityAsyncUpdateBookingState(BookingState bookingState, String requestUrl, ResponseEntity<String> response) throws Exception {
        log.info("Inside saveEntityAsyncUpdateBookingState()...bookingState: {} response: {}", bookingState, response);
        try {
            ObjectMapper objectMapper = new ObjectMapper();
            String jsonString = objectMapper.writeValueAsString(bookingState);
            com.ncba.miniapp.model.BookingState entity = new com.ncba.miniapp.model.BookingState();
            entity.setRequestUrl(requestUrl);
            entity.setType(bookingState.getPayments().getType());
            entity.setRequestBody(jsonString);
            entity.setBookingId(bookingState.getId());
            entity.setResponseBody(response.getBody());
            entity.setResponseStatus(response.getStatusCode().value());
            bookingStateRepository.save(entity);
        } catch (Exception e) {
            log.error("An error occurred inside saveEntityAsyncUpdateBookingState() {}", e.getMessage());
            throw e;
        }
    }

    @Async
    public void saveEntityAsyncSelectOffer(SelectOfferRequestDto selectOfferRequestDto, String selectOfferUrl, ResponseEntity<String> response) throws Exception {
        log.info("Inside saveEntityAsyncSelectOffer()...selectOfferRequestDto: {} response: {}", selectOfferRequestDto, response);
        try {
            ObjectMapper objectMapper = new ObjectMapper();
            String jsonString = objectMapper.writeValueAsString(selectOfferRequestDto);
            SelectOfferRequest entity = new SelectOfferRequest();
            entity.setRequestUrl(selectOfferUrl);
            entity.setOfferId(selectOfferRequestDto.getOfferId());
            entity.setResultId(selectOfferRequestDto.getOfferId());
            entity.setRequestBody(jsonString);
            entity.setResponseBody(response.getBody());
            selectOfferRequestRepository.save(entity);
        } catch (Exception e) {
            log.error("An error occurred inside saveEntityAsyncSelectOffer() {}", e.getMessage());
            throw e;
        }
    }

    @Async
    public void saveEntityAsyncBookingChangeRequest(BookingChangeRequestDto bookingChangeRequestDto, String bookingChangeUrl, ResponseEntity<String> response) throws Exception {
        log.info("Inside saveEntityAsyncBookingChangeRequest()...bookingChangeRequestDto: {} response: {}", bookingChangeRequestDto, response);
        try {
            ObjectMapper objectMapper = new ObjectMapper();
            String jsonString = objectMapper.writeValueAsString(bookingChangeRequestDto);
            BookingChangeRequest entity = new BookingChangeRequest();
            entity.setChangeRequest(bookingChangeRequestDto.getChangeRequest());
            entity.setRequestUrl(bookingChangeUrl);
            entity.setOrderId(bookingChangeRequestDto.getOrderId());
            entity.setRequestBody(jsonString);
            entity.setResponseStatus(response.getStatusCode().value());
            entity.setResponseBody(response.getBody());

            bookingChangeRequestRepository.save(entity);

        } catch (Exception e) {
            log.error("An error occurred inside saveEntityAsyncBookingChangeRequest() {}", e.getMessage());
            throw e;
        }
    }
}
