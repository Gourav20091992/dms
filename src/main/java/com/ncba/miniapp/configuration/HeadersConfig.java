package com.ncba.miniapp.configuration;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpHeaders;
import org.springframework.stereotype.Component;

@Component
@Configuration
public class HeadersConfig {
    private final FetchToken fetchToken;

    @Autowired
    public HeadersConfig(FetchToken fetchToken) {
        this.fetchToken = fetchToken;
    }

    public HttpHeaders getHttpHeaders() {
        HttpHeaders headers = new HttpHeaders();
        headers.set("Content-Type", "application/json");
        headers.set("Accept", "application/json");
        headers.set("Travelduqa-Version", fetchToken.getVersion());
        headers.set("Authorization", "Bearer " + fetchToken.getToken());
        return headers;
    }
}
