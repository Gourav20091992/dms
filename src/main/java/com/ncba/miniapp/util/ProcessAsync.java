package com.ncba.miniapp.util;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.ncba.miniapp.configuration.HeadersConfig;
import com.ncba.miniapp.dto.request.BookingState;
import com.ncba.miniapp.dto.request.*;
import com.ncba.miniapp.dto.request.travelduqa.createbooking.BookingChangeRequestDto;
import com.ncba.miniapp.model.*;
import com.ncba.miniapp.model.booking.Booking;
import com.ncba.miniapp.model.booking.Passenger;
import com.ncba.miniapp.model.booking.Payments;
import com.ncba.miniapp.repository.*;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import java.util.Objects;

@Slf4j
@Component
public class ProcessAsync {

    private final HeadersConfig headersConfig;
    private final RestTemplate restTemplate;
    private final CancelBookingRepository cancelBookingRepository;
    private final ChangeStatusRequestRepository changeStatusRequestRepository;
    private final com.ncba.miniapp.repository.WalletStatusRepository walletStatusRepository;
    private final WalletTransactionsRepository walletTransactionsRepository;
    private final BookingStateRepository bookingStateRepository;
    private final SelectOfferRequestRepository selectOfferRequestRepository;
    private final BookingChangeRequestRepository bookingChangeRequestRepository;
    private final SMSRequestRepository smsRequestRepository;
    private final SMSTemplateRepository smsTemplateRepository;
    private final SMSLogRepository smsLogRepository;
    private final BookingRepository bookingRepository;
    private final FinalBookingRepository finalBookingRepository;

    @Autowired
    public ProcessAsync(CancelBookingRepository cancelBookingRepository,
                        ChangeStatusRequestRepository changeStatusRequestRepository,
                        com.ncba.miniapp.repository.WalletStatusRepository walletStatusRepository,
                        WalletTransactionsRepository walletTransactionsRepository,
                        BookingStateRepository bookingStateRepository,
                        SelectOfferRequestRepository selectOfferRequestRepository,
                        BookingChangeRequestRepository bookingChangeRequestRepository,
                        SMSRequestRepository smsRequestRepository,
                        SMSTemplateRepository smsTemplateRepository,
                        SMSLogRepository smsLogRepository, HeadersConfig headersConfig, RestTemplate restTemplate, BookingRepository bookingRepository, FinalBookingRepository finalBookingRepository) {
        this.cancelBookingRepository = cancelBookingRepository;
        this.changeStatusRequestRepository = changeStatusRequestRepository;
        this.walletTransactionsRepository = walletTransactionsRepository;
        this.bookingStateRepository = bookingStateRepository;
        this.selectOfferRequestRepository = selectOfferRequestRepository;
        this.bookingChangeRequestRepository = bookingChangeRequestRepository;
        this.walletStatusRepository = walletStatusRepository;
        this.smsRequestRepository = smsRequestRepository;
        this.smsTemplateRepository = smsTemplateRepository;
        this.smsLogRepository = smsLogRepository;
        this.headersConfig = headersConfig;
        this.restTemplate = restTemplate;
        this.bookingRepository = bookingRepository;
        this.finalBookingRepository = finalBookingRepository;
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
    public void saveEntityAsyncGetWalletStatus(String getWalletStatusUrl, ResponseEntity<String> response) {
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
            if (bookingState.getPayments() != null) {
                entity.setType(bookingState.getPayments().getType());
            }
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

    @Async
    public void saveEntitySMSTransaction(int randomSixDigitNo, long randomElevenDigitNo, String mblNo, String smsUrl) throws JsonProcessingException {
        log.info("Inside saveEntitySMSTransaction()...randomSixDigitNo: {}, randomElevenDigitNo: {}, mblNo : {}, smsUrl : {}", randomSixDigitNo, randomElevenDigitNo, mblNo, smsUrl);
        try {
            saveSMSLog(mblNo, randomSixDigitNo, randomElevenDigitNo, "NOTVERIFIED");
            SMSRequestDto smsRequestDto = createSMSRequestDto(mblNo, randomSixDigitNo, randomElevenDigitNo);
            log.info("Inside saveEntitySMSTransaction()...smsRequestDto: {}", smsRequestDto);
            ResponseEntity<String> response = sendSMSRequest(smsRequestDto, smsUrl);
            log.info("Inside saveEntitySMSTransaction()...response: {}", response);
            saveSMSRequestAndResponse(smsRequestDto, smsUrl, response);
        } catch (Exception e) {
            log.error("An error occurred inside saveEntitySMSTransaction() {}", e.getMessage());
            throw e;
        }
    }

    @Async
    public void updateSMSLogStatus(SMSLog smsLog) {
        log.info("Inside updateSMSLogStatus()...smsLog: {}", smsLog);
        try {
            smsLog.setVerifiedStatus("VERIFIED");
            log.info("Inside updateSMSLogStatus()...after updating status smsLog: {}", smsLog);
            smsLogRepository.save(smsLog);
        } catch (Exception e) {
            log.error("An error occurred inside updateSMSLogStatus() {}", e.getMessage());
            throw e;
        }
    }

    private void saveSMSLog(String mblNo, int randomSixDigitNo, long randomElevenDigitNo, String status) {
        log.info("Inside saveSMSLog()... mblNo : {}, randomSixDigitNo : {}, randomElevenDigitNo : {}", mblNo, randomSixDigitNo, randomElevenDigitNo);
        SMSLog smsLog = new SMSLog();
        smsLog.setMblNo(mblNo);
        smsLog.setSixDigitNo(String.valueOf(randomSixDigitNo));
        smsLog.setBusRefNo(String.valueOf(randomElevenDigitNo));
        smsLog.setVerifiedStatus(status);
        smsLog.setGenerationTime(System.currentTimeMillis());
        smsLogRepository.save(smsLog);
    }

    public SMSLog findByMblNo(String mblNo) {
        log.info("Inside findFromSMSLog()... mblNo : {}", mblNo);
        return smsLogRepository.findTop1ByMblNoOrderByCreatedDateDesc(mblNo);
    }

    private SMSRequestDto createSMSRequestDto(String mblNo, int randomSixDigitNo, long randomElevenDigitNo) {
        log.info("Inside createSMSRequestDto()... mblNo : {}, randomSixDigitNo : {}, randomElevenDigitNo : {}", mblNo, randomSixDigitNo, randomElevenDigitNo);
        SMSRequestDto smsRequestDto = new SMSRequestDto();
        smsRequestDto.setBusRefNo(String.valueOf(randomElevenDigitNo));
        smsRequestDto.setMblNo(mblNo);
        smsRequestDto.setTmpId("BAP001");
        smsRequestDto.setSysCnl("CRM");

        GdaDto gdaDto = new GdaDto();
        gdaDto.setBusCnl("SDK");
        gdaDto.setJrnNo(String.valueOf(randomElevenDigitNo));
        smsRequestDto.setGda(gdaDto);
        String smsTemplateMsg = "Dear customer, Default Verification code is {OTP}, valid for 1 minute.";
        String templateId = "T001";
        try {
            SMSTemplate smsTemplate = smsTemplateRepository.findByTemplateId(templateId);
            // Check if the template is found and its message is not null or empty
            if (smsTemplate != null && smsTemplate.getTemplateMsg() != null && !smsTemplate.getTemplateMsg().isEmpty()) {
                smsTemplateMsg = smsTemplate.getTemplateMsg();
            } else {
                log.warn("Template not found or empty for template ID: {}", templateId);
            }
        } catch (DataAccessException e) {
            log.error("Database error while retrieving template ID: {}", templateId, e);
        }
        String finalTemplateMessage = smsTemplateMsg.replace("{OTP}", String.valueOf(randomSixDigitNo));
        log.info("Inside createSMSRequestDto()...smsTemplateMsg : {}, finalTemplateMessage : {}", smsTemplateMsg, finalTemplateMessage);
        ReplaceValsDto replaceValsDto = new ReplaceValsDto();
        replaceValsDto.setNtfDesc(finalTemplateMessage);
        smsRequestDto.setReplaceVals(replaceValsDto);

        return smsRequestDto;
    }

    private ResponseEntity<String> sendSMSRequest(SMSRequestDto smsRequestDto, String smsUrl) {
        try {
            log.info("Inside sendSMSRequest()...smsRequestDto : {}, smsUrl : {}", smsRequestDto, smsUrl);
            HttpEntity<SMSRequestDto> httpEntity = new HttpEntity<>(smsRequestDto, headersConfig.getHttpHeaders());
            return restTemplate.exchange(smsUrl, HttpMethod.POST, httpEntity, String.class);
        } catch (Exception e) {
            log.error("Error Inside sendSMSRequest()...{}", e.getMessage());
            return null;
        }
    }

    private void saveSMSRequestAndResponse(SMSRequestDto smsRequestDto, String smsUrl, ResponseEntity<String> response) throws JsonProcessingException {
        log.info("Inside saveSMSRequestAndResponse()...smsRequestDto :{}, smsUrl : {}, response : {}", smsRequestDto, smsUrl, response);
        SMSRequest smsRequest = new SMSRequest();
        smsRequest.setBusRefNo(smsRequestDto.getBusRefNo());
        smsRequest.setMblNo(smsRequestDto.getMblNo());
        smsRequest.setTmpId("BAP001");
        smsRequest.setSysCnl("CRM");
        smsRequest.setRequestUrl(smsUrl);
        Gda gda = new Gda();
        gda.setBusCnl(smsRequestDto.getGda().getBusCnl());
        gda.setJrnNo(smsRequestDto.getGda().getJrnNo());
        smsRequest.setGda(gda);
        ReplaceVals replaceVals = new ReplaceVals();
        replaceVals.setNtfDesc(smsRequestDto.getReplaceVals().getNtfDesc());
        smsRequest.setReplaceVals(replaceVals);
        ObjectMapper objectMapper = new ObjectMapper();
        String jsonString = objectMapper.writeValueAsString(smsRequestDto);
        smsRequest.setRequestBody(jsonString);
        if (response != null) {
            smsRequest.setResponseStatus(response.getStatusCode().value());
            smsRequest.setResponseBody(response.getBody());
        }
        smsRequestRepository.save(smsRequest);
    }

    @Async
    public void saveEntityAsyncCreateBooking(BookingRequest bookingRequest, String createBookingUrl, ResponseEntity<String> response) throws Exception {
        log.info("Inside saveEntityAsyncCreateBooking()...bookingRequest: {} response: {}", bookingRequest, response);
        try {
            ObjectMapper objectMapper = new ObjectMapper();
            String jsonString = objectMapper.writeValueAsString(bookingRequest);
            Booking booking = new Booking();
            booking.setRequestUrl(createBookingUrl);
            booking.setRequestBody(jsonString);
            booking.setResponseBody(response.getBody());
            booking.setResponseStatus(response.getStatusCode().value());
            booking.setResultId(bookingRequest.getResultId());
            booking.setOfferId(bookingRequest.getOfferId());
            booking.getPassengers().addAll(bookingRequest.getPassengerList().stream()
                    .map(passengerDto -> mapToPassengerEntity(passengerDto, booking))
                    .filter(Objects::nonNull)
                    .toList());
            com.ncba.miniapp.model.booking.Payments payments = new Payments();
            if (bookingRequest.getPayments() != null) {
                payments.setType(bookingRequest.getPayments().getType());
                payments.setCurrency(bookingRequest.getPayments().getCurrency());
                payments.setAmount(bookingRequest.getPayments().getAmount());
                payments.setBooking(booking);
                booking.setPayments(payments);
            }
            bookingRepository.save(booking);
        } catch (Exception e) {
            log.error("An error occurred inside saveEntityAsyncCreateBooking() {}", e.getMessage());
            throw e;
        }
    }

    @Async
    public void saveEntityAsyncFinalBooking(FinalBookingDto finalBookingDto) throws Exception {
        log.info("Inside saveEntityAsyncFinalBooking()...finalBookingDto: {}", finalBookingDto);
        try {
            ObjectMapper objectMapper = new ObjectMapper();
            String jsonString = objectMapper.writeValueAsString(finalBookingDto);
            FinalBooking booking = new FinalBooking();
            booking.setRequestUrl(null);
            booking.setRequestBody(jsonString);
            booking.setResponseStatus(200);
            booking.setOfferId(finalBookingDto.getOfferId());
            booking.setMblNo(finalBookingDto.getMblNo());
            booking.setRequestBody(jsonString);
            booking.setResponseBody(objectMapper.writeValueAsString(finalBookingDto.getPaymentResp()));
            booking.setPaymentResp(objectMapper.writeValueAsString(finalBookingDto.getPaymentResp()));
            finalBookingRepository.save(booking);
        } catch (Exception e) {
            log.error("An error occurred inside saveEntityAsyncCreateBooking() {}", e.getMessage());
            throw e;
        }
    }

    private Passenger mapToPassengerEntity(PassengerDto passengerDto, com.ncba.miniapp.model.booking.Booking booking) {
        Passenger passengerEntity = new Passenger();
        passengerEntity.setPhoneNumber(passengerDto.getPhoneNumber());
        passengerEntity.setPhoneCode(passengerDto.getPhoneCode());
        passengerEntity.setEmail(passengerDto.getEmail());
        passengerEntity.setBornOn(passengerDto.getBornOn());
        passengerEntity.setTitle(passengerDto.getTitle());
        passengerEntity.setGender(passengerDto.getGender());
        passengerEntity.setFamilyName(passengerDto.getFamilyName());
        passengerEntity.setGivenName(passengerDto.getGivenName());
        passengerEntity.setType(passengerDto.getType());
        passengerEntity.setBooking(booking);
        return passengerEntity;
    }
}
