package com.ncba.miniapp.controller;

import com.ncba.miniapp.configuration.RateLimited;
import com.ncba.miniapp.dto.request.UserInfoRequestDto;
import com.ncba.miniapp.dto.request.UserSessionRequest;
import com.ncba.miniapp.service.AccessService;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/access")
@Slf4j
@Tag(name = "AccessController", description = "Access Controller APIs")
@Validated
public class AccessController {
    private final AccessService accessService;

    @Autowired
    public AccessController(AccessService accessService) {
        this.accessService = accessService;
    }

    @GetMapping("/getHealthCheck")
    @RateLimited
    public ResponseEntity<String> getHealthStatus() {
        return accessService.getHealthStatus();
    }

    @PostMapping("/getUserInfo")
    @RateLimited
    public ResponseEntity<String> getUserInfo(@RequestBody UserInfoRequestDto userInfoRequestDto) {
        log.info("Inside getUserInfo()...userInfoRequestDto: {}", userInfoRequestDto);
        return accessService.getUserInfo(userInfoRequestDto);
    }

    @PostMapping("/getAccessToken")
    @RateLimited
    public ResponseEntity<String> getAccessToken(@RequestBody UserSessionRequest userSessionRequest) {
        log.info("Inside getAccessToken()...userSessionRequest: {}", userSessionRequest);
        return accessService.getAccessToken(userSessionRequest);
    }
}
