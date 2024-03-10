package com.yob.gatewayservice.auth;

import com.yob.gatewayservice.Constants.Constants;
import com.yob.gatewayservice.config.PropertyConfig;
import com.yob.gatewayservice.dto.Status;
import com.yob.gatewayservice.dto.TokenGenerationResponse;
import com.yob.gatewayservice.dto.TokenGenerationResponse.TokenGenerationResp;
import com.yob.gatewayservice.dto.TokenGenerationResponse.TokenRespResourceData;
import com.yob.gatewayservice.dto.TokenGenerationResponse.TokenResMetaData;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Base64;
import java.util.List;


@Component
public class TokenGenAndValidation {

    @Autowired
    private  PropertyConfig propertyConfig;

    public TokenGenerationResponse generateAuthToken(){
        String token = getAuthToken();

        TokenGenerationResponse response= new TokenGenerationResponse();
        TokenGenerationResp resp = new TokenGenerationResp();
        TokenResMetaData metaData = new TokenResMetaData();
        List<TokenRespResourceData> listOfResData = new ArrayList<>();
        TokenRespResourceData respResourceData = new TokenRespResourceData();
        metaData.setCode(Constants.STATUS_CODE_SUCCESS);
        metaData.setStatus(Status.SUCCESS.toString());
        metaData.setVersion(Constants.API_VERSION_TOKEN);
        metaData.setMessage("Token Generated Successfully");
        resp.setMetaData(metaData);
        respResourceData.setToken(token);
        respResourceData.setCode(Constants.STATUS_CODE_100);
        respResourceData.setExpiryInMin(propertyConfig.getTokenValidationDuration());
        listOfResData.add(respResourceData);
        resp.setResourceData(listOfResData);
        response.setTokenGenerationResp(resp);
        return response;

    }

    protected String getAuthToken(){
        LocalDateTime currentTime = LocalDateTime.now();
        String secretKey  = propertyConfig.getTokenAuthSecretKey();
        String tokenData = secretKey + currentTime.toString();
        String token  = Base64.getEncoder().encodeToString(tokenData.getBytes());
        return token;
    }
}
