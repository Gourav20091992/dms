package com.ncba.miniapp.controller;

import com.ncba.miniapp.dto.request.JourneyDto;
import com.ncba.miniapp.service.OffersService;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/offers")
@Slf4j
@Tag(name = "GetOffersController", description = "Get Offers Controller APIs")
public class GetOffersController {
    @Autowired
    OffersService offersService;

    @PostMapping("/getOffers")
    public ResponseEntity<String> getOffers(@RequestBody JourneyDto journeyDto) {
        log.info("Inside getOffers()...journeyDto: {}", journeyDto);
        return offersService.getOffers(journeyDto);
    }

}
