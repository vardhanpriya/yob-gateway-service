package com.yob.gatewayservice.config;

import lombok.Data;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;

@Configuration
@Data
public class PropertyConfig {

    @Value("${TOKEN_VALIDATION_DURATION}")
    private long tokenValidationDuration;

    @Value("${TOKEN_AUTH_SECRET_KEY}")
    private String tokenAuthSecretKey;


    @Value("${REST_CALL_RETRY_COUNT}")
    private int restCallRetryCount;

}
