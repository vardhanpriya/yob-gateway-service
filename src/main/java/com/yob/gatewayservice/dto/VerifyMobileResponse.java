package com.yob.gatewayservice.dto;

public class VerifyMobileResponse {
    private String refNum;
    private String mobileNumber;
    private boolean isPost = false;     // will check later
    private boolean isPost72 = false;
    private boolean eligibleForResume = false;
    private boolean isPostAccount = false;
}
