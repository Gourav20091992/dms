package com.ncba.miniapp.controller;

import com.ncba.miniapp.dto.request.AirRequestDto;
import com.ncba.miniapp.service.AirService;
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
@RequestMapping("/api/v1/airports")
@Slf4j
@Tag(name = "AirController", description = "Air Controller APIs")
@Validated
public class AirController {
    private final AirService airService;

    @Autowired
    public AirController(AirService airService) {
        this.airService = airService;
    }

    @PostMapping("/getLocation")
    public ResponseEntity<String> getLocation(@RequestBody AirRequestDto airRequestDto) {
        log.info("Inside getLocation()...airRequestDto: {}", airRequestDto);
        return airService.getLocation(airRequestDto);
    }

    @PostMapping("/getAirlines")
    public ResponseEntity<String> getAirlines() {
        log.info("Inside getAirlines()...");
        return airService.getAirlines();
    }
}
