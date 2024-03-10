package com.yob.gatewayservice.auth;

import com.yob.gatewayservice.Constants.Constants;
import com.yob.gatewayservice.config.PropertyConfig;
import com.yob.gatewayservice.dto.Status;
import com.yob.gatewayservice.dto.TokenGenerationResponse;
import com.yob.gatewayservice.dto.TokenGenerationResponse.TokenGenerationResp;
import com.yob.gatewayservice.dto.TokenGenerationResponse.TokenRespResourceData;
import com.yob.gatewayservice.dto.TokenGenerationResponse.TokenResMetaData;

import com.yob.gatewayservice.dto.TokenValidationResponse;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.w3c.dom.ls.LSException;

import java.time.Duration;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
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
        DateTimeFormatter  formatter = DateTimeFormatter.ofPattern(Constants.DATE_TIME_FORMAT_AUTH);
        String dateTime = currentTime.format(formatter);
        String secretKey  = propertyConfig.getTokenAuthSecretKey();
        String tokenData = secretKey + dateTime;
        String token  = Base64.getEncoder().encodeToString(tokenData.getBytes());
        return token;
    }

    public TokenValidationResponse validateAuthToken(String token){
        byte[] byteByteToken  = Base64.getDecoder().decode(token);
        String decodedToken = new String(byteByteToken);
        LocalDateTime currentTime = LocalDateTime.now();
        DateTimeFormatter  formatter = DateTimeFormatter.ofPattern(Constants.DATE_TIME_FORMAT_AUTH);
        //Toke = "SAJFAFKASFK 7294724232434"

        long durationOfToken = propertyConfig.getTokenValidationDuration();
        String extractDateTime = decodedToken.substring(propertyConfig.getTokenAuthSecretKey().length());

        //parse the extractDateTime to LocalDateTime
        LocalDateTime finalDateTimeFromToken = LocalDateTime.parse(extractDateTime,formatter);
        Duration duration = Duration.between(finalDateTimeFromToken,currentTime);
        long durationInMinutes = duration.toMinutes();
        TokenValidationResponse response = new TokenValidationResponse();
        TokenValidationResponse.TokenValidationResp resp = new TokenValidationResponse.TokenValidationResp();
        TokenValidationResponse.TokenResMetaData metaData = new TokenValidationResponse.TokenResMetaData();
        List<TokenValidationResponse.TokenValidationResourceData> listRes = new ArrayList<>();
        TokenValidationResponse.TokenValidationResourceData resourceData = new TokenValidationResponse.TokenValidationResourceData();
        if(durationInMinutes <=  durationOfToken){
            metaData.setStatusCode(Constants.STATUS_CODE_SUCCESS);
            metaData.setVersion(Constants.API_VERSION_TOKEN);
            metaData.setMessage("Token Validated SuccessFully");
            metaData.setStatus(Status.SUCCESS.toString());
            resp.setMetaData(metaData);
            resourceData.setCode(Constants.STATUS_CODE_100);
            resourceData.setTokenValidated(true);
            listRes.add(resourceData);
            resp.setResourceData(listRes);
            response.setTokenValidationResp(resp);
            return response;
        }else {
            metaData.setStatusCode(Constants.STATUS_CODE_ERROR);
            metaData.setVersion(Constants.API_VERSION_TOKEN);
            metaData.setMessage("Token Validation Falied");
            metaData.setStatus(Status.ERROR.toString());
            resp.setMetaData(metaData);
            resourceData.setCode(Constants.STATUS_CODE_900);
            resourceData.setTokenValidated(false);
            listRes.add(resourceData);
            resp.setResourceData(listRes);
            response.setTokenValidationResp(resp);
            return response;
        }
    }
}
