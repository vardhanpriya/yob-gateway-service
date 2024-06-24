package com.yob.gatewayservice.service.impl;

import com.google.gson.Gson;
import com.yob.gatewayservice.config.PropertyConfig;
import com.yob.gatewayservice.dto.UserDetails;
import com.yob.gatewayservice.dto.ValidateMobileRequest;
import com.yob.gatewayservice.dto.VerifyMobileRequest;
import com.yob.gatewayservice.dto.VerifyMobileResponse;
import com.yob.gatewayservice.utility.RestClientUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Service;

import java.util.Arrays;


@Service("mobileVerificationForResume")
public class MobileVerificationServiceImplForResumeJourney extends  MobileVerificationServiceImpl{


    @Autowired
    RestClientUtil restClientUtil;

    @Autowired
    PropertyConfig propertyConfig;


    @Override
    public VerifyMobileResponse generateMobileOtp(VerifyMobileRequest request) {
        VerifyMobileResponse res = null;
        HttpHeaders httpHeaders = new HttpHeaders();
        httpHeaders.setAccept(Arrays.asList(MediaType.APPLICATION_JSON));

        HttpEntity<?> httpEntity = new HttpEntity<>(request,httpHeaders);

        String response = restClientUtil.invokeRestTemplate(propertyConfig.getResumeMobileVerifyUrl(),httpEntity, HttpMethod.POST);
        System.out.println("Response from user-mgmnt : " + response);
      Gson gson = new Gson();
        if(response!=null){
          res  = gson.fromJson(response,VerifyMobileResponse.class);
        }

        return res;
    }

    @Override
    public UserDetails validateMobileOtp(ValidateMobileRequest request) {
        return new UserDetails();
    }
}
