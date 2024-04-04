package com.ncba.miniapp.controller;

import com.ncba.miniapp.dto.request.Journey;
import com.ncba.miniapp.service.OffersService;
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
public class GetOffersController {
    @Autowired
    OffersService offersService;

    @PostMapping("/getOffers")
    public ResponseEntity<String> getOffers(@RequestBody Journey journey) {
        log.info("Inside getOffers()...journey: {}", journey);
        return offersService.getOffers(journey);
    }

}
