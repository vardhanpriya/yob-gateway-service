package com.yob.gatewayservice.controller;

import com.yob.gatewayservice.config.PropertyConfig;
import com.yob.gatewayservice.dto.Status;
import com.yob.gatewayservice.dto.TokenGenerationResponse;
import org.apache.el.parser.Token;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDateTime;

@RestController
@RequestMapping("/v1/token")
public class TokenGenValController {

    @Autowired
    private PropertyConfig propertyConfig;

    public ResponseEntity<TokenGenerationResponse> generateAuthToken(){
        LocalDateTime currentTime = LocalDateTime.now();
        System.out.println(currentTime);
        TokenGenerationResponse response= new TokenGenerationResponse();
        response.setToken(currentTime.toString());
        response.setStatus(Status.SUCCESS.toString());
        response.setExpireIn(propertyConfig.getTokenValidationDuration());
        return new ResponseEntity<>(response, HttpStatus.OK);

    }
}
