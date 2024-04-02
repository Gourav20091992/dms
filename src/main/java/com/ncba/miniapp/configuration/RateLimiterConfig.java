package com.ncba.miniapp.configuration;

import com.google.common.util.concurrent.RateLimiter;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class RateLimiterConfig {

    @Value("${rate.limit.duration}")
    private String rateLimitDuration;

    @Bean
    public RateLimiter rateLimiter() {
        // Define your rate limiter configuration
        return RateLimiter.create(Double.valueOf(rateLimitDuration));
    }
}
