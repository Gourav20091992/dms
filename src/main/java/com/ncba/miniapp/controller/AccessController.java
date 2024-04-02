package com.ncba.miniapp.controller;

import com.ncba.miniapp.configuration.RateLimited;
import com.ncba.miniapp.dto.request.UserInfoRequestDto;
import com.ncba.miniapp.dto.request.UserSessionRequest;
import com.ncba.miniapp.service.AccessService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/access")
@Slf4j
public class AccessController {
    @Autowired
    AccessService accessService;

    @GetMapping("/getHealthCheck")
    @RateLimited
    public ResponseEntity<String> getHealthStatus() {
        return accessService.getHealthStatus();
    }

    @PostMapping("/getUserInfo")
    @RateLimited
    public ResponseEntity<String> getUserInfo(
            @RequestHeader("Travelduqa-Version") String version,
            @RequestHeader("Authorization") String token,
            @RequestBody UserInfoRequestDto userInfoRequestDto) {
        log.info("Inside getUserInfo()...userInfoRequestDto: {}", userInfoRequestDto);
        return accessService.getUserInfo(userInfoRequestDto, version, token);
    }

    @PostMapping("/getAccessToken")
    @RateLimited
    public ResponseEntity<String> getAccessToken(
            @RequestHeader("Travelduqa-Version") String version,
            @RequestHeader("Authorization") String token,
            @RequestBody UserSessionRequest userSessionRequest) {
        log.info("Inside getAccessToken()...userSessionRequest: {}", userSessionRequest);
        return accessService.getAccessToken(userSessionRequest, version, token);
    }
}
