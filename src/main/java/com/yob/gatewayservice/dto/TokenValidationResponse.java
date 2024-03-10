package com.yob.gatewayservice.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class TokenValidationResponse {

    private TokenValidationResp tokenValidationResp;

    @Data
    public static class TokenValidationResp {
        private TokenResMetaData metaData;
        private List<TokenValidationResourceData> resourceData;
    }

    @Data
    public static class TokenResMetaData {
        private String status;
        private String message;
        private String version;
        private String statusCode;
    }

    @Data
    public static class TokenValidationResourceData{
       private boolean tokenValidated;
       private String  code;
    }

}
