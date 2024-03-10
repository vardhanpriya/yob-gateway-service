package com.yob.gatewayservice.controller;


import com.yob.gatewayservice.auth.TokenGenAndValidation;
import com.yob.gatewayservice.config.PropertyConfig;
import com.yob.gatewayservice.dto.Status;
import com.yob.gatewayservice.dto.TokenGenerationResponse;
import org.apache.el.parser.Token;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
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

    @Autowired
    private TokenGenAndValidation tokenGen;

    @GetMapping(path = "/generate-token", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<TokenGenerationResponse> generateAuthToken1(){

        TokenGenerationResponse response =tokenGen.generateAuthToken();

        return new ResponseEntity<>(response,HttpStatus.OK);
    }
}
