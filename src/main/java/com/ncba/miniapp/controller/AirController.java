package com.ncba.miniapp.controller;

import com.ncba.miniapp.dto.request.AirRequestDto;
import com.ncba.miniapp.service.AirService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/airports")
@Slf4j
public class AirController {
    @Autowired
    AirService airService;

    @PostMapping("/getLocation")
    public ResponseEntity<String> getLocation(
            @RequestHeader("Travelduqa-Version") String version,
            @RequestHeader("Authorization") String token,
            @RequestBody AirRequestDto airRequestDto) {
        log.info("Inside getLocation()...airRequestDto: {}", airRequestDto);
        return airService.getLocation(airRequestDto, version, token);
    }

    @PostMapping("/getAirlines")
    public ResponseEntity<String> getAirlines(
            @RequestHeader("Travelduqa-Version") String version,
            @RequestHeader("Authorization") String token) {
        log.info("Inside getAirlines()...");
        return airService.getAirlines(version, token);
    }
}
