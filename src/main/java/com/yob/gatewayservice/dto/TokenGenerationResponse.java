package com.yob.gatewayservice.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor

public class TokenGenerationResponse {

    private String token;
    private String expireIn;
    private String status;

}
