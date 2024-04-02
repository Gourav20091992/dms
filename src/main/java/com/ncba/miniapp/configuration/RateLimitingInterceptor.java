package com.ncba.miniapp.configuration;

import com.google.common.util.concurrent.RateLimiter;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.HandlerInterceptor;

@Component
@Slf4j
public class RateLimitingInterceptor implements HandlerInterceptor {

    @Autowired
    private RateLimiter rateLimiter;

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        if (handler instanceof HandlerMethod) {
            HandlerMethod handlerMethod = (HandlerMethod) handler;
            // Apply rate limiting only to controller methods annotated with @RateLimited
            if (handlerMethod.hasMethodAnnotation(RateLimited.class) && !rateLimiter.tryAcquire()) {
                log.error("Too many requests, Please try again..");
                response.setStatus(HttpStatus.TOO_MANY_REQUESTS.value());
                return false;
            }
        }
        return true;
    }
}
