package com.yob.gatewayservice.controller;

import com.yob.gatewayservice.dto.VerifyMobileRequest;
import com.yob.gatewayservice.dto.VerifyMobileResponse;
import com.yob.gatewayservice.service.MobileVerificationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class MobileVerificationController {


    @Autowired
    @Qualifier("mobileVerificationService")
    MobileVerificationService mobileVerificationService;


    @Autowired
    @Qualifier("mobileVerificationForResume")
    MobileVerificationService mobileVerificationServiceForResume;
 /*
   /v1/mobile/verify

   /v1/mobile/validateOtp

   /v1/resume/mobile/verify

   /v1/resume/mobile/validateOtp
     */

    @PostMapping(path = "/v1/resume/mobile/verify")
    public ResponseEntity<VerifyMobileResponse> verifyMobile(@RequestBody VerifyMobileRequest request)  {
        VerifyMobileResponse response = mobileVerificationServiceForResume.generateMobileOtp(request);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

}
