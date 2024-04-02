package com.ncba.miniapp.controller;

import com.ncba.miniapp.dto.request.Journey;
import com.ncba.miniapp.service.OffersService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/offers")
@Slf4j
public class GetOffersController {
    @Autowired
    OffersService offersService;

    @PostMapping("/getOffers")
    public ResponseEntity<String> getOffers(
            @RequestHeader("Travelduqa-Version") String version,
            @RequestHeader("Authorization") String token,
            @RequestBody Journey journey) {
        log.info("Inside getOffers()...journey: {}", journey);
        return offersService.getOffers(journey, version, token);
    }

}
