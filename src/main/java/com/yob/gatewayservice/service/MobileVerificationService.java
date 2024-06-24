package com.yob.gatewayservice.service;

import com.yob.gatewayservice.dto.UserDetails;
import com.yob.gatewayservice.dto.ValidateMobileRequest;
import com.yob.gatewayservice.dto.VerifyMobileRequest;
import com.yob.gatewayservice.dto.VerifyMobileResponse;

public interface MobileVerificationService {

    public VerifyMobileResponse generateMobileOtp(VerifyMobileRequest request) ;

    public UserDetails validateMobileOtp(ValidateMobileRequest request);

}

