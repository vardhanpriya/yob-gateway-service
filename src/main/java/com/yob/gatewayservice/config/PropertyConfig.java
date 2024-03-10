package com.yob.gatewayservice.config;

import lombok.Data;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;

@Configuration
@Data
public class PropertyConfig {

    @Value("${TOKEN_VALIDATION_DURATION}")
    private String tokenValidationDuration;

}
