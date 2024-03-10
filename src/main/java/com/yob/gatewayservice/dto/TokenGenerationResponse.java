package com.yob.gatewayservice.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class TokenGenerationResponse {

    private TokenGenerationResp tokenGenerationResp;

    @Data
    public static class TokenGenerationResp {
        private TokenResMetaData metaData;
        private List<TokenRespResourceData> resourceData;
    }

    @Data
    public static class TokenResMetaData {
        private String status;
        private String message;
        private String version;
        private String code;
        private String time;
    }

    @Data
    public static class TokenRespResourceData{
        private String code;
        private String token;
        private String expiryInMin;


    }

















}