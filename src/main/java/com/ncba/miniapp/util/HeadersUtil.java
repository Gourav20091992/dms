package com.ncba.miniapp.util;

import io.micrometer.common.util.StringUtils;
import org.springframework.http.HttpHeaders;

import java.util.UUID;

public class HeadersUtil {
    public static HttpHeaders getHttpHeaders(String version, String token) {
        HttpHeaders headers = new HttpHeaders();

        headers.set("Content-Type", "application/json");
        headers.set("Accept", "application/json");
        if (StringUtils.isEmpty(version)) {
            version = "v1";
        }
        if (StringUtils.isEmpty(token)) {
            token = getToken();
        }
        headers.set("Travelduqa-Version", version);
        headers.set("Authorization", "Bearer " + token);
        return headers;
    }

    private static String getToken() {
        // Todo Need to find out the way to fetch token.
        UUID uuid = UUID.randomUUID();
        return uuid.toString();
    }
}
