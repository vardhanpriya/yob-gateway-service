package com.yob.gatewayservice.dto;

import lombok.Data;

@Data
public class VerifyMobileRequest {

    private String mobileNumber;
    private String appName;

}
