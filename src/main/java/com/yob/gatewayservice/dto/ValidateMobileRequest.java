package com.yob.gatewayservice.dto;

import lombok.Data;

@Data
public class ValidateMobileRequest {

    private String otp;
    private String transactionId;
    private String mobileNumber;

}
