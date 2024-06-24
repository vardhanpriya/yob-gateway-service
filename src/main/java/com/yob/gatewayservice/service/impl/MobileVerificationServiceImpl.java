package com.yob.gatewayservice.service.impl;

import com.yob.gatewayservice.dto.UserDetails;
import com.yob.gatewayservice.dto.ValidateMobileRequest;
import com.yob.gatewayservice.dto.VerifyMobileRequest;
import com.yob.gatewayservice.dto.VerifyMobileResponse;
import com.yob.gatewayservice.service.MobileVerificationService;
import org.springframework.stereotype.Service;

@Service("mobileVerificationService")
public class MobileVerificationServiceImpl implements MobileVerificationService {
    @Override
    public VerifyMobileResponse generateMobileOtp(VerifyMobileRequest request) {
        return null;
    }

    @Override
    public UserDetails validateMobileOtp(ValidateMobileRequest request) {
        return null;
    }
}
