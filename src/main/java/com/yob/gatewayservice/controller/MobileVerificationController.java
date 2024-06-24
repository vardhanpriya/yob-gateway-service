package com.yob.gatewayservice.controller;

import com.yob.gatewayservice.service.MobileVerificationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class MobileVerificationController {


    @Autowired
    @Qualifier("mobileVerificationService")
    MobileVerificationService mobileVerificationService;


    @Autowired
    @Qualifier("mobileVerificationForResume")
    MobileVerificationService mobileVerificationServiceForResume;


}
