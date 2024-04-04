package com.ncba.miniapp.configuration;

import jakarta.annotation.PostConstruct;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;

@Configuration
@Slf4j
public class EnvironmentConfig {

    @Autowired
    private FetchToken fetchToken;
    @Value("${appToken}")
    private String appToken;

    @Value("${travelduqaVersion}")
    private String travelduqaVersion;

    @PostConstruct
    public void init() {
        fetchToken.setToken(appToken);
        fetchToken.setVersion(travelduqaVersion);
        log.info("Inside init()...Token value is {} and travelduqaVersion is {}", appToken, travelduqaVersion);
    }
}
